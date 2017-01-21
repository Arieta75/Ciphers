/*Vignere Cipher Decryption by Subtracting Letters
 * 1. take first letter of ciphertext (encoded text)
 * 2. take first letter of keyword
 * 3. find the numeric value of position in alphabet of each (note: ASCII starts at 65, 
 * therefore the index position is # - 65) 
 * 4. find index number of ciphertext - index number of keyword 
 * 5. if less than 0, add 26
 * 6. this difference is the rank of the plain text (decoded text)
 * 7. convert the index number of the plain text back to a character
 */

import java.util.Scanner;

public class cipher2 {
  
//display a message on console and obtain following info:
  //1. message to be decoded
  public static String getMessage() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Please input your message to the console.");
    
    //store coded message in String 'message'
    String message = sc.nextLine();
    sc.close();
    return message.toUpperCase();
  }
  
  //2. keyword of the cipher
  public static String getKey() {
    Scanner sc=new Scanner(System.in);
    System.out.println("What is the keyword?");
    //store coded keyword in String 'key'
    String key = sc.next();
    sc.close(); 
    return key.toUpperCase();
  }
  
  //write a method that will decipher a Vignere encrypted cipher if
  //given keyword and text
  public static void decipherText(String code, String keyword) {
    //find the length of the message
    int length = code.length();
    System.out.println("There are: " + length + " characters in this message.");
    //use StringBuilder to create a new, empty string (size chars)
    StringBuilder plainText = new StringBuilder(1000);
    
    for(int i = 0; i < length; i++) {
      //charAt() finds the letter at position ()
      //starts at 0, ends at (length - 1)
      //PROBLEM! Doesn't know to repeat the code word once it is used up
      if (i > 4) {
        char newLetter = stepsToDecode(keyword.charAt(i), code.charAt(i), (int)firstLetter, (int)firstKeyLetter, indexCode - indexKey);
        plainText.append(newLetter);   
      }
      
      else {
        char firstKeyLetter = keyword.charAt(i);
        char firstLetter = code.charAt(i);
        
        //find the numeric ASCII value of each letter which is rank in alphabet + 65
        int indexCode = (int)firstLetter;
        int indexKey = (int)firstKeyLetter;
        //System.out.println(indexCode);
        //System.out.println(indexKey);
        
        //find the difference in index number of ciphertext and keyword
        //this is the new position of letter; but add 65 to convert to ASCII
        int newRank = indexCode - indexKey;
        newRank = newRank + 65;
        
        if (newRank < 65) {
          newRank = newRank + 26;
        }
        
        //the decrypted letter is stored in newLetter
        char newLetter = (char)newRank;
        
        plainText.append(newLetter);      
      }
    }
    
    System.out.println(plainText);
    
  }
  
  public static char stepsToDecode(char firstKeyLetter, char firstLetter, int indexCode, int indexKey, int newRank){
      firstKeyLetter = keyword.charAt(i%4-1);
      firstLetter = code.charAt(i);
      indexCode = (int)firstLetter;
      indexKey = (int)firstKeyLetter;
      newRank = indexCode - indexKey;
      newRank = newRank + 65;
      if (newRank < 65) {
        newRank = newRank + 26;
      }
      char newLetter = (char)newRank;
      return newLetter;
    }
    
  public static void main(String[] args) {
    //store the returns of getMessage and getKey into message and key
    //pass those values into the decipherText method
    String message = getMessage();
    String key = getKey();
    decipherText(message, key);
  }
 
}