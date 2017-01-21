/*Vigenere Cipher Decryption by Subtracting Letters
 * 1. take first letter of ciphertext (encoded text)
 * 2. take first letter of keyword
 * 3. find the numeric value of position in alphabet of each (note: ASCII starts at 65, 
 * therefore the index position is # - 65) 
 * 4. find index number of ciphertext - index number of keyword 
 * 5. if less than 0, add 26
 * 6. this difference is the rank of the plain text (decoded text)
 * 7. convert the index number of the plain text back to a character
 */


/*Vigenere Cipher Encryption by Adding Letters
 * 1. take first letter of plaintext
 * 2. take first letter of keyword
 * 3. find the numeric value of position in alphabet of each
 * 4. add the index number of plaintext + index number of keyword
 * 5. take the number mod 26
 * 6. use this number as rank of new letter of ciphertext
 * 7. convert the number to a character
 */

import java.util.Scanner;

public class cipher {
  
//display a message on console and obtain following info:
  public static void chooseFunction() {
    Scanner s = new Scanner(System.in);
    System.out.println("Would you like to encipher or decipher your text?");
    //stores the String literal answer in 'method', then pushes to upper case
    String method = s.nextLine();
    method = method.toUpperCase();
    
    
    if(method.equals("ENCIPHER")) {
      String message = getMessage();
      String keyword = getKey(true);
      encipherText(message, keyword);
    }
    
    else if(method.equals("DECIPHER")){
      String message = getMessage();
      String keyword = getKey(false);
      decipherText(message, keyword);
    }
    
    else {
      System.out.println("ERROR: please enter 'encipher' or 'decipher' into the console.");
    }         
  }
      
  //gets the message to be encoded or decoded
  public static String getMessage() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Please input your message to the console without any punctuation.");
    
    //store coded message in String 'message'
    String message = sc.nextLine();
    message = message.toUpperCase();
    sc.close();
    return message;
  }
  
  //keyword of the cipher
  public static String getKey(boolean b) {
    Scanner sc=new Scanner(System.in);
    System.out.println("What is the keyword?");
    
    //store coded keyword in String 'key'
    String key = sc.next();
    key = key.toUpperCase();
    
    //if getKey() was called to encipher, will accept any key without message
    if (b) {
      sc.close();
      return key;
    }
    
    //if getKey() was called to decipher, prints warning note if key is wrong but still returns key 
    else {
      if (key.equals("WOLFE") == false) {
        System.out.println("Bzzt! You guessed wrong and the following message will not make sense.");
      }
      sc.close(); 
      return key;
    }
  }
  
  public static int getLength(String message) {
    int length = message.length();
    System.out.println("There are: " + length + " characters in this message.");  
    return length;
  }
  
  public static char runDecipher(int i, int y, String message, String keyword) {
    if (y == -1) {
      y = i;
    }
    char firstKeyLetter = keyword.charAt(y);
    char firstLetter = message.charAt(i);
    int indexMessage = (int)firstLetter;
    int indexKey = (int)firstKeyLetter;
    
    if(firstLetter == ' ') {
      char newLetter = ' ';
      return newLetter;
    }
    
    else {
    int newRank = indexMessage - indexKey;
    newRank = newRank + 65;
      if (newRank < 65) {
        newRank = newRank + 26;
      }
    char newLetter = (char)newRank; 
        
     /*Error spotting: uncomment to print out each step's result
     System.out.println("i = " + i);
     System.out.print("The key letter: " + firstKeyLetter + "  ");
     System.out.println("The code letter: " + firstLetter);
     System.out.print("The ASCII code: " + indexMessage + "  ");
     System.out.println("The ASCII code key: " +indexKey);
     System.out.print("The new ASCII code: " + newRank + "  ");
     System.out.println("The new letter: " + newLetter);  
     */
    
    return newLetter;
    }
  }
  
  //write a method that will decipher a Vigenere encrypted cipher given message and keyword
  public static void decipherText(String message, String keyword) {
    //find the length of the message
    int length = getLength(message);
    
    //use StringBuilder to create a new, empty string (size chars)
    StringBuilder plainText = new StringBuilder(1000);
    
    for(int i = 0; i < length; i++) {
      //charAt() finds the letter at position ()
      if (i <= 4) {
        //passing it '-1' as placeholder; has no purpose in this method
        char newLetter = runDecipher(i, -1, message, keyword);
        plainText.append(newLetter);
      }    
              
      else {
        //System.out.println("i = " + i);
        int x = i/5;
        int y = i - 5*x;
        
        char newLetter = runDecipher(i, y, message, keyword);
        plainText.append(newLetter);  
      }
    }
    
    System.out.println(plainText);
  }
  
  
  //method to encipher text with a codeword
  public static void encipherText(String message, String keyword) {
    int length = getLength(message);
    StringBuilder cipherText = new StringBuilder(1000);
    
    for(int i = 0; i < length; i++) {
      if(i <= 4) {
        char newLetter = runEncipher(i, -1, message, keyword);
        cipherText.append(newLetter);
      }
      
      else {
        int x = i/5;
        int y = i - 5*x;
        
        char newLetter = runEncipher(i, y, message, keyword);
        cipherText.append(newLetter); 
      } 
    }
    
    System.out.println(cipherText);
  }
  
  public static char runEncipher(int i, int y, String message, String keyword) {
    //based on argument passed to runEncipher, will check condition of if (i <= 4)
    if(y == -1) {
      y = i;
    }
    
    char firstKeyLetter = keyword.charAt(y);
    char firstLetter = message.charAt(i);
    int indexMessage = (int)firstLetter;
    int indexKey = (int)firstKeyLetter;
    
    if(indexMessage == 32) {
      char newLetter = ' ';
      return newLetter;
    }
    
    else {
    int newRank = indexMessage + indexKey;
    newRank = newRank%26 + 65;
    char newLetter = (char)newRank;
   
    /*Error spotting: uncomment to print out each step's result
    System.out.println("i = " + i);
    System.out.print("The key letter: " + firstKeyLetter + "  ");
    System.out.println("The message letter: " + firstLetter);
    System.out.print("The ASCII code: " + indexMessage + "  ");
    System.out.println("The ASCII code for key: " + indexKey);
    System.out.print("The new ASCII code for letter: " + newRank + "  ");
    System.out.println("The new letter: " + newLetter);  
    */
    return newLetter;
    }
  }
    
  public static void main(String[] args) {
    chooseFunction();
  }
 
}
