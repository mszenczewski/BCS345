package szenczewski.bcs345.hwk.movies.presentation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import szenczewski.bcs345.hwk.movies.business.*;

/**
 * Presents a menu for dealing with Movie data
 * @author Michael Szenczewski
 * @version 1.0
 * @since 10/15/2017
 */
public class MovieConsoleUI 
{
	/**
	 * Presents a menu for dealing with Movie data
	 */
	public void ShowUI()
	{
		int menuChoice = 0;
		String inputFileName = "";
		String outputFileName = "";
		Movie movie = new Movie();
		Scanner console = new Scanner(System.in);
		Scanner inputFromFile = null;
		
		while(menuChoice != 5)
		{
			//print menu
			System.out.println("Movie Console UI");
			System.out.println("----------------");
			System.out.println("1 - Read movie from file");
			System.out.println("2 - Write movie to file");
			System.out.println("3 - Show movie data with descriptive text on screen");
			System.out.println("4 - Show movie JSON on screen");
			System.out.println("5 - Exit\n");
			System.out.print("Enter Choice: ");
			
			while (true)
			{
				try
				{
					//get menu choice
					menuChoice = console.nextInt();
					console.nextLine();
					
					//exit loop if a valid choice was recieved
					if (menuChoice >= 1 && menuChoice <= 5)
					{
						break; 
					}
					else
					{
						System.out.print("Please choose a number between one and five (1-5): ");
					}
				}
				catch (InputMismatchException e)
				{
					System.out.print("Please choose a number between one and five (1-5): ");
					console.nextLine();
				}
			} 
			
			//act on user choice
			switch (menuChoice)
			{
				case 1: //Read movie from file
					while (true)
					{
						try
						{
							//get input file name
							System.out.print("Enter input file: ");
							inputFileName = console.next();
							
							inputFromFile = new Scanner(new FileReader(inputFileName));
							
							//read from file
							movie.Read(inputFromFile);
							
							System.out.println("\nReading movie data from file...\n");
							
							//exit loop if no exception
							break; 
						}
						catch (FileNotFoundException e)
						{
							System.out.print("\nFile not found. Please try again.\n\n");
							console.nextLine();
						}
					} 
					break;
				case 2: //Write movie to file
					while (true)
					{
						try
						{
							//get output file name
							System.out.print("Enter output file: ");
							outputFileName = console.next();
							
							PrintStream outputFile = new PrintStream(outputFileName);
							
							//write to file
							movie.Write(outputFile);
							
							System.out.printf("\nWriting movie data to file %s...\n\n", outputFileName);
							
							//exit loop if no exception
							break; 
						}
						catch (FileNotFoundException e)
						{
							System.out.print("\nFile not found. Please try again.\n\n");
							console.nextLine();
						}
					} 
					break;
				case 3: //Show movie data with descriptive text on screen
					System.out.println(movie);					
					break;
				case 4: //Show movie JSON on screen
					System.out.println("\nMovie JSON data:\n");
					System.out.println(movie.GetJSON());
					System.out.println();
					break;
				case 5:
					System.out.println("\nExiting...\n");
			}	
		}
	}
}
