//****************************
// Rohan More
// Load a dictionary from a text file and sort it in its canonical form, then scramble a word given by the user and show vible real words it turns into.
//*****************************
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ClientMain {

	public static void main(String[] args) {

		//Variables and ArrayLists
		ArrayList<String> dict = new ArrayList<String>();
		ArrayList<String> sortedDict = new ArrayList<String>();
		String filename;
		String userInput;
		String copyInput;

		//Initiate Variable Scanner
		Scanner scan = new Scanner(System.in);

		//Introduction and ask for filename
		System.out.println("Welcome to Rohan More's anagram solver.");
		System.out.print("Please enter the dictionary file you want to use: ");
		filename = scan.next();

		//try to read the file provided line by line
		//if the file does not exist throw an error and ask for the file until it is found
		do {
			try {
				Scanner readLine = new Scanner(new File(filename));
				while (readLine.hasNext()) {
					String word = readLine.next();
					//add the current word to the original dictionary list
					dict.add(word);
					//add the current word in its canonical to the sorted dictionary
					sortedDict.add(canonical(word));
				}
				readLine.close();
				break;
			} catch (FileNotFoundException error) {
				System.out.println(error);
				System.out.print("\nPlease enter the dictionary file you want to use: ");
				filename = scan.next();
			}
		} while (true);
		
		//Main loop to ask for the words and display anagrams
		while (true) {
			System.out.print("\nWord to scramble (Enter N/n to quit): ");
			userInput = scan.next();
			copyInput = userInput;
			userInput = canonical(userInput);
			
			//if the input is n or N, exit
			if (userInput.equalsIgnoreCase("n")) {
				System.out.println("\nThank you for using Rohan More's anagram solver!\nHave a nice day!");
				scan.close();
				System.exit(0);
			}
			
			//If the word is not found in the sorted dictionary (canonical ordered dictionary), ask for another word
			if (!sortedDict.contains(userInput)) {
				System.out.println("That word does not exist in the dictionary!");
				continue;
			}
			
			System.out.println("\nAll words found for \"" + copyInput + "\":");
			for (int i = 0; i < sortedDict.size(); i++) {
				if (sortedDict.get(i).equalsIgnoreCase(userInput)) {
					System.out.println(dict.get(i));
				}
			}
		}
		
	}
	
	//canonical form
	public static String canonical(String word) {
		
		//convert the current word to a char array
		char arr[] = word.toCharArray();
		//sort the char array alphabetically
		Arrays.sort(arr);
		//convert the char array back to a normal string
		String str = String.valueOf(arr);
		
		//return the word in canonical form
		return str;
	}

}
