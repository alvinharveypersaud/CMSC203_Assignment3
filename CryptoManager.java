/*
 * Class: CMSC203 
 * Instructor: Professor Eivazi
 * Description: (Give a brief description for each Class)
 * Due: 03/17/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming  assignment independently. 
*  I have not copied the code from a student or any source. 
*  I have not given my code to any student.
*  Print your Name here: Alvin Persaud
*/

package CryptoManager;

/**
 * This is a utility class that encrypts and decrypts a phrase using two
 * different approaches. The first approach is called the Caesar Cipher and is a
 * simple “substitution cipher” where characters in a message are replaced by a
 * substitute character. The second approach, due to Giovan Battista Bellaso,
 * uses a key word, where each character in the word specifies the offset for
 * the corresponding character in the message, with the key word wrapping around
 * as needed.
 * 
 * @author Farnaz Eivazi
 * @version 7/16/2022
 */
public class CryptoManager
{
	
	private static final char LOWER_RANGE = ' ';
	private static final char UPPER_RANGE = '_';
	private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_RANGE and UPPER_RANGE characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean isStringInBounds (String plainText)
	{
		//Iterates through each character to check if the string is in bounds
		for(int i = 0; i < plainText.length(); i++)
		{
			if(plainText.charAt(i) < LOWER_RANGE || plainText.charAt(i) > UPPER_RANGE)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String caesarEncryption(String plainText, int key)
	{
		//Checks to see if the string is in bounds
		for(int i = 0; i < plainText.length(); i++)
		{
			if(plainText.charAt(i) > UPPER_RANGE || plainText.charAt(i) < LOWER_RANGE)
			{
				return "The selected string is not in bounds, Try again.";
			}
		}
		
		String newString = "";
		//Loops through each character
		for(int i = 0; i < plainText.length(); i++)
		{
			if((plainText.charAt(i)+key) > UPPER_RANGE)
			{
				
				int newKey = key;
				/*If the character plus the key is greater than the upper range, its subtracted
				 * by the range until its within bounds
				 */
				while(newKey+plainText.charAt(i) > UPPER_RANGE)
				{
					newKey -= RANGE;
				}
				//the new key value is added to the char
				char tempChar = plainText.charAt(i);
				tempChar += newKey;
				newString += tempChar;
			}
			else
			{
				//the key value is added to the char
				char tempChar = plainText.charAt(i);
				tempChar += key;
				newString += tempChar;
			}
			
		}
		return newString;
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String bellasoEncryption (String plainText, String bellasoStr)
	{
		String newString = "";
		int j = 0;
		/*if the bellaso key is shorter than the plain text, the bellaso string is "stretched out"
		 * until its the same size as the plain text
		 */
		while(plainText.length() > bellasoStr.length())
		{
			bellasoStr += bellasoStr.charAt(j);
			j++;
			if(j > plainText.length())
			{
				j = 0;
			}
		}
		
		for(int i = 0; i < plainText.length(); i++)
		{
			//The key value is set to the index of the bellaso key
			int key = bellasoStr.charAt(i);
			if((plainText.charAt(i)+key) > UPPER_RANGE)
			{
				int newKey = key;
				/*If the character plus the bellaso key is greater than the upper range, its
				 * subtracted by the range until its within bounds
				 */
				while(newKey+plainText.charAt(i) > UPPER_RANGE)
				{
					newKey -= RANGE;
				}
				//the new key value is added to the char
				char tempChar = plainText.charAt(i);
				tempChar += newKey;
				newString += tempChar;
			}
			else
			{
				//the key value is added to the char
				char tempChar = plainText.charAt(i);
				tempChar += key;
				newString += tempChar;
			}
			
		}
		return newString;
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String caesarDecryption (String encryptedText, int key)
	{
		//throw new RuntimeException("method not implemented");
		String newString = "";
		for(int i = 0; i < encryptedText.length(); i++)
		{
			if((encryptedText.charAt(i)-key) < LOWER_RANGE)
			{
				int newKey = key;
				while(newKey > LOWER_RANGE)
				{
					newKey -= RANGE;
				}
				if((encryptedText.charAt(i)-newKey) > UPPER_RANGE)
				{
					newKey += RANGE;
				}
				if((encryptedText.charAt(i)-newKey) < LOWER_RANGE)
				{
					newKey -= RANGE;
				}
				char tempChar = encryptedText.charAt(i);
				tempChar -= newKey;
				newString += tempChar;
			}
			else
			{
				char tempChar = encryptedText.charAt(i);
				tempChar -= key;
				newString += tempChar;
			}
			
		}
		return newString;
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String bellasoDecryption(String encryptedText, String bellasoStr)
	{
		//throw new RuntimeException("method not implemented");
		String newString = "";
		int j = 0;
		while(encryptedText.length() > bellasoStr.length())
		{
			bellasoStr += bellasoStr.charAt(j);
			j++;
			if(j > encryptedText.length())
			{
				j = 0;
			}
		}
		
		for(int i = 0; i < encryptedText.length(); i++)
		{
			int key = bellasoStr.charAt(i);
			//key -= 2*key;
			if((encryptedText.charAt(i)-key) < LOWER_RANGE)
			{
				int newKey = key;
				while(newKey > LOWER_RANGE)
				{
					newKey -= RANGE;
				}
				
				char tempChar = encryptedText.charAt(i);
				tempChar -= newKey;
				if(tempChar < LOWER_RANGE)
				{
					tempChar += RANGE;
				}
				newString += tempChar;
				//newString += newKey + " ";
			}
			else
			{
				char tempChar = encryptedText.charAt(i);
				tempChar -= key;
				newString += tempChar;
			}
			
		}
		return newString;
	}
}
