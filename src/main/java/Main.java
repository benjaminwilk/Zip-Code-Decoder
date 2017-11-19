/* Zip code Decoder
* Created by Ben
* Date: 10/26/2017
* Version: 1.1
* Notes: Now significantly less awful!  Refactored the design of the application. Logging works minimally, but it could definitely be improved.
*/
package src.main.java;

import src.main.java.GUI;
import src.main.java.LogCreator;
import src.main.java.ZIP;

public class Main{
  public static void main(String[] args){
    GUI visuals = new GUI();
    visuals.GUI();
    visuals.calculateButtonClick();
  }
}
