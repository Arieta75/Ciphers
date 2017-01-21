/*Caesar Cipher Decryption
 * 1. determine the message and shift key
 * 2. convert each letter of the message to an ASCII number
 * 3. add or subtract the shift key to the ASCII value 
 * >(rank - 65) if it goes over, and (rank + 26) if it goes under)
 * 4. convert this new number rank into a letter
 * 5. append the letter to a StringBuilder
 */


import java.util.Scanner;

public class CaesarCipher {
  public static void main(String[] args) {
    String message = getMessage();
    message = message.toUpperCase();
    int shift = getShift();
    String direction = getDirection();
    
    if(direction.equals("right")){
      decipherText(message, shift, true);
    }
    else {
      decipherText(message, shift, false);
    }       
  } 
  
  public static String getMessage() {
    Scanner input = new Scanner(System.in);
    System.out.println("What message would you like to encode/decode?");
    String message = input.nextLine();
    return message;
  }
  
  public static int getShift() {
    Scanner scan = new Scanner(System.in);
    System.out.println("By how many positions does the alphabet shift? Enter a numerical value: ");
    String answer = scan.nextLine();
    int shift = Integer.parseInt(answer); 
    shift = shift%26;
    return shift;
  }    
  
  public static String getDirection() {
    Scanner input = new Scanner(System.in);
    System.out.println("Please enter the direction of shift: 'right' or 'left'.");
    String direction = input.next();
    direction = direction.toLowerCase();
    
    while(direction.equals("right") == false && direction.equals("left") == false) {
      System.out.println("Are you sure you entered that correctly? Try again: please type in 'left' or 'right'.");
      direction = input.next();  
    }
    //returns either 'left' or 'right'
    return direction;
  }
      
  public static void decipherText(String message, int shift, boolean direction) {
    int length = message.length(); 
    
    StringBuilder newMessage = new StringBuilder(1000);
    
    for(int i = 0; i < length; i++) {
      //get the next letter of the message
      char letter = message.charAt(i);
      //convert it to its ASCII code
      int rank = (int)letter;
      //System.out.println("The initial rank is: " + rank); 
      
      //if the initial rank is not in the range of capital letters, it is a symbol
      if(rank < 65 || rank > 90) {   
        char symbol = getSymbol(rank);
        newMessage.append(symbol);
      }
      
      else {
      //if boolean passed was true, the alphabet shifts right
      if(direction) {
        //assign it a new number based on the shift's value, adjusted to remain in range 
        //of upper case ASCII alphabetical characters
        int newRank = rank + shift;
        //System.out.println("The new rank is: " + newRank);
        
        if (newRank > 90) {
          newRank = newRank - 26;
          //System.out.println("The new rank had to be adjusted to: " + newRank);
        }
        
        //turn the new rank into a new letter
        char newLetter = (char)newRank;
        newMessage.append(newLetter);
      }
      
      //if boolean passed was false, the alphabet shifts left
      else {
        int newRank = rank - shift;
        //adjust ASCII value to keep within range if needed
        if (newRank < 65){
          newRank = newRank + 26;
        }
        //turn the new rank into a new letter
        char newLetter = (char)newRank;
        newMessage.append(newLetter);
      } 
      }
    }
    System.out.println(newMessage);
  }
  
  public static char getSymbol(int rank) {
    return (char)rank;    
  }
  
}