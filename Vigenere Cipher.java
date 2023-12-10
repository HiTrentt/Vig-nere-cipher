/*=============================================================================
| Assignment: pa01 - Encrypting a plaintext file using the Vigenere cipher
|
| Author: Trent Gaymore
| Language:Java
|
| To Compile: javac pa01.java
| 
|
| To Execute: java -> java pa01 kX.txt pX.txt
| 
| where kX.txt is the keytext file
| and pX.txt is plaintext file
|
| Note: All input files are simple 8 bit ASCII input
|
| Class: CIS3360 - Security in Computing - Fall 2022
| Instructor: McAlpin
| Due Date: 10/16/2022
|
+============================================================================= */
import java.io.*;
import java.util.*;

public class pa01 {
	/* setting up all the arrays we will need, and initializing variables. */

    String plainText = "";
    String key = "";
   
	/* this reads plantext and throws away any faults, while skipping over things that
	 are not characters */
	public static String readPlaintext(String plaintext) throws IOException {

		String textArray = "";
		File file = new File(plaintext);
        Scanner fr = new Scanner(file);

        /*  Scan all lines from the file  */
        while (fr.hasNextLine()) {
            textArray += fr.nextLine();
        }
        fr.close();

        /*  Replaces all characters that are not in the set {A-Z} || {a-z} */
        textArray = textArray.replaceAll("[^A-Za-z]", "");



        /*  Adds x to the end of the string until length is < 512  */
        while (textArray.length() < 512) { 
            textArray += "x";
        }

        /*  Prevents the text from being too long */
        if (textArray.length() > 512) {
            textArray = textArray.substring(0, 512);
        }

	

        return textArray.toLowerCase(); /* Returns lowercase version of string */
	}
	
	public static String readKey(String key) throws IOException {

		String keyArray = "";
		File file = new File(key);
        Scanner fr = new Scanner(file);

		
        
        /* this converts what the user will enter to lowercase so no issues will happen
           Scan all lines from the file  */
        while (fr.hasNextLine()) {
            keyArray += fr.nextLine();
        }
        fr.close();

        keyArray = keyArray.replaceAll("[^A-Za-z]", "");

	
		
		return keyArray.toLowerCase();
	}
		
	/* does the math for printing out the encrypted key */
	public static void calculation(String plain, String key) {
		int keyCounter = 0;
	
		System.out.println("\n\nVigenere Key:");
		
		for(int i = 0; i < key.length(); i ++) {
			if(i%80 == 0) {
				System.out.println();
			}

            System.out.print(key.charAt(i));
		
		}
		System.out.println();

		System.out.println("\n\nPlaintext:");
		/* shows the plantext of what the user enters */
		
		for(int j = 0; j < plain.length(); j++) {
			if(j % 80 == 0) { 
				System.out.println();
			}
			
            System.out.print(plain.charAt(j));
	
		}
		System.out.println("");
	
		System.out.println("\n\nCiphertext:");
		/* prints out ciphertext with encytped stuff */
		
        char ciphertext;
		for(int k = 0; k < 512; k++) {
			if(keyCounter % key.length() == 0) {
				keyCounter = 0;
			}
			if(k % 80 == 0) {
				System.out.println();
			}

		
            int valOne = plain.charAt(k) - 97;
            int valTwo = key.charAt(keyCounter) - 97;
            int finalVal = (valOne + valTwo) % 26 + 97;

			ciphertext = (char) finalVal;
            System.out.print(ciphertext);
	
			keyCounter++;
		}
		
        System.out.println("");
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		/* This prompts the user to enter the two command lines */
        String plain = readPlaintext(args[1]);
        String key = readKey(args[0]);

        calculation(plain, key);	
	}
}


/*=============================================================================
| I Trent Gaymore (tr791260) affirm that this program is
| entirely my own work and that I have neither developed my code together with
| any another person, nor copied any code from any other person, nor permitted
| my code to be copied or otherwise used by any other person, nor have I
| copied, modified, or otherwise used programs created by others. I acknowledge
| that any violation of the above terms will be treated as academic dishonesty.
+=============================================================================*/
