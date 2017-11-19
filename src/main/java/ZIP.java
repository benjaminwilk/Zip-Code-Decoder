package src.main.java;

import src.main.java.GUI;
import src.main.java.LogCreator;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.util.Scanner;
import java.net.*;
import org.apache.commons.io.IOUtils;
import javax.swing.*;
import java.awt.event.*;

public class ZIP{

  private static String frontURL = "http://api.zippopotam.us/us/";
  private BufferedReader br;
  private InputStream is = null;
  private String line;
  private String userSetZIPCode;
  private String createdCity;
  private String createdState;
  private String longLat;
  private String createdZIP;

  public ZIP(){
    
  }

  public ZIP(String userZipCode){
    this.userSetZIPCode = userZipCode;
  }

  public void setZIPCode(String userZipCode){
    this.userSetZIPCode = userZipCode;
  }

  private String pullRawLocationData(){
    BufferedReader bm = null;
    try {
      URL url = new URL(this.frontURL + this.userSetZIPCode);
      is = url.openStream();  // throws an IOException
      br = new BufferedReader(new InputStreamReader(is));
      String message = org.apache.commons.io.IOUtils.toString(br);
      return message;
    } catch(Exception ex){
      //System.err.println("Uh oh, something broke... " + ex);
      //return "" + ex;
      new LogCreator().writeLogContents("" + ex);
      return null;
    }
  }

  private boolean getBufferedReaderStatus(){
    String rawData = pullRawLocationData();
    try{
      String message = org.apache.commons.io.IOUtils.toString(br);
      if(message.isEmpty()){
        return false;
      }else{
        return true;
      }
    }catch(Exception ex){
      new LogCreator().writeLogContents("Something broke in getBufferedReaderStatus method.");
      //System.err.println();
      return false;
    }
  }

  private boolean getBuffRead(){
    try{
      if(this.br.readLine() != null){
        return false;
      }else{
        return true;
      }
    }catch(Exception ex){
      new LogCreator().writeLogContents("Something broke in getBuffRead method.");
      //System.err.println();
      return false;
    }
  }

  public void delimitedData(){
    Scanner s = new Scanner(pullRawLocationData()).useDelimiter("\\s*\"\\s*");
    String line = s.nextLine();
    String[] zipData = line.split("\"");
    this.longLat = zipData[33] + " / " + zipData[21];
    for(int i = 0; i < zipData.length; i++){
      if(i == 17){
        this.createdCity = zipData[i];
      }
      if(i == 29){
        this.createdState = zipData[i];
      }
      if(i == 3){
        this.createdZIP = zipData[i];
      }
    }
  }

  public String displayCity(){
    return this.createdCity;
  }

  public String displayState(){
    return this.createdState;
  }

  public String displayLongatudeLatitude(){
    return this.longLat;
  }

  public String displayZIPCode(){
    return this.createdZIP;
  }

}
