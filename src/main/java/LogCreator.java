package src.main.java;

import src.main.java.GUI;
import src.main.java.ZIP;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.*;

public class LogCreator{

  /**** VV Change this to move the log file somewhere else. VV ****/
  private final static String frontFileName = "C:\\log-files\\";
  private final static String rearFileName = "-log-file.log";
  private boolean logWriter = true;

  public boolean getLogWriterStatus(){
    return logWriter;
  }

  public void setLogWriterStatus(){
    if(logWriter == true){
      logWriter = false;
    } else {
      logWriter = true;
    }
  }

  private Date createDateInstance(){
    Date today = Calendar.getInstance().getTime();
    return today;
  }

  public String getDateHour(){
    SimpleDateFormat formatter = null;
    formatter = new SimpleDateFormat("HH:mm:ss");
    return formatter.format(createDateInstance());
  }

  public String getDateMonth(){
    SimpleDateFormat formatter = null;
    formatter = new SimpleDateFormat("MM-dd-yyyy");
    return formatter.format(createDateInstance());
  }

  private String logFileNameCreation(){
    String logFileName = frontFileName + getDateMonth() + rearFileName;
    return logFileName;
  }

  public String logFileValidation(){
    String logNameTitle = logFileNameCreation();
    File fileLog = new File(logNameTitle);
    if(fileLog.exists() && !fileLog.isDirectory()){
      return logNameTitle;
    } else{
      try{
        PrintWriter write = new PrintWriter(fileLog, "UTF-8");
        return logNameTitle;
      }catch(Exception ex){
        writeLogContents("Something broke in logFileValidation function " + ex);
        return null;
        //return "Something broke...: " + ex;
      }
    }
  }

  public void writeLogContents(String userDefinedLog){
    logFileWriter(userDefinedLog);
  }

  public void formatLogContents(ArrayList passedContents){
    /*** VVV This is currently appending text to the end of the other lines.  Sort of broken, I'll fix it later VVV ***/
    String formattedContents = null;
    formattedContents += getDateHour();
    for(int i = 0; i < passedContents.size(); i++){
      formattedContents += passedContents.get(i) + " -- ";
    }
    formattedContents += "\n";
    logFileWriter(formattedContents);
  }

  private void logFileWriter(String userDefinedMessage){
    String fileName = logFileValidation();
    try{
      PrintWriter logWriter = new PrintWriter(new FileOutputStream(new File(fileName), true));
      logWriter.append(userDefinedMessage);
      logWriter.println();
      logWriter.close();
    }catch(Exception ex){
      System.out.println(ex);
    }
  }


}
