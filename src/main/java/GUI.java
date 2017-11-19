package src.main.java;

import src.main.java.LogCreator;
import src.main.java.ZIP;

import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class GUI{

  private JFrame frame;
  private JMenuBar jmb;
  private JMenuItem quit;
  private JMenuItem view;
  private JTextField zipInput;
  private JButton calculateButton;
  private JLabel cityLabel, stateLabel, lonLat, zipCode;

  static final String unavailable = "N/A";
  private String userInputZIPCode;
  private boolean successState;
  private ArrayList logArray = new ArrayList();

  public void GUI(){
    frame = new JFrame("ZIP Code Decoder");
    jmb = new JMenuBar();
    quit = new JMenuItem("Quit");
    view = new JMenuItem("View");
    zipInput = new JTextField("Input ZIP Code");
    zipInput.setBounds(50, 40, 100, 30);
    calculateButton = new JButton("Calculate");
    calculateButton.setBounds(50, 70, 100, 30);
    cityLabel = new JLabel("City: ");
    cityLabel.setBounds(20, 110, 200, 30);
    stateLabel = new JLabel("State: ");
    stateLabel.setBounds(20, 130, 200, 30);
    lonLat = new JLabel("Lon/Lat: ");
    lonLat.setBounds(20, 150, 200, 30);
    zipCode = new JLabel("Zip Code: ");
    zipCode.setBounds(20, 170, 200, 30);

    frame.setJMenuBar(jmb); frame.add(quit); frame.add(view); jmb.add(quit); jmb.add(view);
    frame.add(calculateButton);
    frame.add(zipInput);
    frame.add(cityLabel); frame.add(stateLabel);frame.add(lonLat); frame.add(zipCode);

    frame.setSize(220, 260);
    frame.setLayout(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

    quit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e){
        System.exit(0);
      }
    });

    view.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(frame, "This application logs searches for future fixes.\n Contact Ben Wilk for additional information.");
      }
    });
  }



  public void calculateButtonClick(){
    ZIP zipmessage = new ZIP();
    //new zip().displayData(packageLabels());
    calculateButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        displayClearMessage();
        userInputZIPCode = zipInput.getText();

        if(userInputZIPCode.length() != 5){
          calculateButtonClick();
        }
        /*** VVV This portion of the code will be cleaned up eventually.  This should be split into multiple smaller methods. VVV ***/
        zipmessage.setZIPCode(userInputZIPCode);
        logArray.add(userInputZIPCode);
        zipmessage.delimitedData();
        if(zipmessage.displayZIPCode() != null){
          displaySuccessMessage(zipmessage);
          //successState = true;
          logArray.add(successState = true);
          logArray.add(zipmessage.displayCity());
          logArray.add(zipmessage.displayState());
          logArray.add(zipmessage.displayLongatudeLatitude());
          logArray.add(zipmessage.displayZIPCode());
        } else {
          //successState = false;
          logArray.add(successState = false);
        }
        if(new LogCreator().getLogWriterStatus()){
          new LogCreator().formatLogContents(logArray);
        }
      }
    });
  }

  private void displaySuccessMessage(ZIP zipmessage){
    cityLabel.setText(cityLabel.getText() + zipmessage.displayCity());
    stateLabel.setText(stateLabel.getText() + zipmessage.displayState());
    lonLat.setText(lonLat.getText() + zipmessage.displayLongatudeLatitude());
    zipCode.setText(zipCode.getText() + zipmessage.displayZIPCode());
  }

  private void displayFailureMessage(){
    cityLabel.setText(cityLabel.getText() + unavailable);
    stateLabel.setText(stateLabel.getText() + unavailable);
    lonLat.setText(lonLat.getText() + unavailable);
    zipCode.setText(zipCode.getText() + unavailable);
  }

  private void displayClearMessage(){
    cityLabel.setText("");
    cityLabel.setText("City: ");
    stateLabel.setText("");
    stateLabel.setText("State: ");
    lonLat.setText("");
    lonLat.setText("Lon/Lat: ");
    zipCode.setText("");
    zipCode.setText("Zip Code: ");
  }

  /** VVV This currently does nothing. VVV ***/
  public JLabel[] packageLabels(){
    JLabel[] labels = {cityLabel, stateLabel, lonLat, zipCode};
    return labels;
  }

  private String getZipCode(){
    return this.userInputZIPCode;
  }
  /*@Override   VVV This doesn't work at all VVV
  public void keyPressed(KeyEvent evt){
    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
      calculateButton.getModel().isPressed();
      //textAreaInput.getText("Key Pressed");
    }
    if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
      System.exit(0);
    }
  }*/

}
