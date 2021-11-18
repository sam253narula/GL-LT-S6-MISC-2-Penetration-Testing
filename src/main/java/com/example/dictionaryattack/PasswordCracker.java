package com.example.dictionaryattack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Samarth Narula
 * 
 *         Important Note:- I have created this code only for education purpose,
 *         I will not be responsible if this code will be used for any hacking
 *         purpose by anyone
 */

// online Tool for encrypting and Decrypting : https://md5.gromweb.com/
public class PasswordCracker {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		String wordlist = "passwords.txt";

		String hashtocrack = "21232f297a57a5a743894a0e4a801fc3";
		// BruteForce Attack: Load hash to crack and try all combinations
		// admin - 21232f297a57a5a743894a0e4a801fc3
		// root@123 - 9a69e50114a30c4c5c1d455a2cfb87d1

		String algorithm = "MD5";
		boolean trying = true;
		try (BufferedReader br = new BufferedReader(new FileReader(wordlist))) {
			System.out.println("[*] Wordlist read. Starting the cracking process... [*]\n");
			String line;
			// Load dictionary of passwords from passwords.txt - newline separated
			while ((line = br.readLine()) != null) {
				line = line.replace("\n", "");

				// Encrypt password with md5
				byte[] bytesOfMessage = line.getBytes("UTF-8");
				MessageDigest md = MessageDigest.getInstance(algorithm);
				byte[] thedigest = md.digest(bytesOfMessage);
				BigInteger bigInt = new BigInteger(1, thedigest);
				String hashtext = bigInt.toString(16);
				if (trying == true) {
					System.out.println("Trying >" + hashtext);
				}

				if (hashtocrack.contains(hashtext)) {
					System.out.println("##### PASSWORD CRACKED ##### : " + hashtext + " -> " + line);
				}

			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

	}
}