package szenczewski.bcs345.hwk.movies.presentation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import szenczewski.bcs345.hwk.movies.business.*;

/**
 * Presents a menu for dealing with Studio data
 * @author Michael Szenczewski
 * @version 1.0
 * @since 11/5/2017
 */
public class StudioConsoleUI
{
	/**
	 * Presents a menu for dealing with Studio data
	 */
	public void ShowUI()
	{
		int menuChoice = 0;
		int index = -1;
		Studio studio = new Studio();
		Movie movie;
		Scanner console = new Scanner(System.in);
		Scanner inputFromFile = null;
		String inputFileName = "";
		String outputFileName = "";
		
		while(menuChoice != 8)
		{
			//print menu
			System.out.println("Studio Console UI");
			System.out.println("-----------------");
			System.out.println("1 - Read studio info from file");
			System.out.println("2 - Write studio info to file");
			System.out.println("3 - Show movie by index");
			System.out.println("4 - Show movie with highest net");
			System.out.println("5 - Show studio report on screen");
			System.out.println("6 - Show studio as JSON string on screen");
			System.out.println("7 - Show studio toString on screen");
			System.out.println("8 - Exit\n");
			System.out.print("Enter Choice: ");
			
			while (true)
			{
				try
				{
					//get menu choice
					menuChoice = console.nextInt();
					console.nextLine();
					
					//exit loop if a valid choice was recieved
					if (menuChoice >= 1 && menuChoice <= 8)
					{
						break; 
					}
					else
					{
						System.out.print("Please choose a number between one and eight (1-8): ");
					}
				}
				catch (InputMismatchException e)
				{
					System.out.print("Please choose a number between one and eight (1-8): ");
					console.nextLine();
				}
			} 
			
			//act on user choice
			switch (menuChoice)
			{
				case 1: //Read studio info from file
					while (true)
					{
						try
						{
							//get input file name
							System.out.print("Enter input file: ");
							inputFileName = console.next();
							
							inputFromFile = new Scanner(new FileReader(inputFileName));
							
							//read from file
							studio.Read(inputFromFile);
							
							System.out.println("\nReading studio data from file...\n");
							
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
					
				case 2: //Write studio info to file
					while (true)
					{
						try
						{
							//get output file name
							System.out.print("Enter output file: ");
							outputFileName = console.next();
							
							PrintStream outputFile = new PrintStream(outputFileName);
							
							//write to file
							studio.Write(outputFile);
							
							System.out.printf("\nWriting studio data to file %s...\n\n", outputFileName);
							
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
					
				case 3: //Show movie by index
					while (true)
					{
						try
						{							
							//get index 
							System.out.print("Enter index: ");
							index = console.nextInt();
							console.nextLine();
														
							//get index
							movie = studio.GetByIndex(index);
							
							//print movie
							System.out.printf("\n%s\n", movie.toString());
							
							//exit loop if no exception
							break; 
						}
						catch (InputMismatchException e)
						{
							System.out.println("\nPlease enter an integer.");
							console.nextLine();
						}
						catch (ArrayIndexOutOfBoundsException e)
						{
							System.out.println("\nIndex does not exist. Try again.");
						}
					} 
					break;
					
				case 4: //Show movie with highest net
					movie = studio.GetHighestNetMovie();
					
					System.out.print("\n" + movie.toString());
					System.out.print("Net: " + movie.CalculateNet() + "\n\n");
					
					break;
				case 5: //Show studio report on screen
					System.out.println();
					
					studio.Report(System.out);
					
					System.out.println();

					break;
					
				case 6: //Show studio as JSON string on screen
					System.out.println("\n" + studio.GetJSON() + "\n");
					break;
					
				case 7: //Show studio toString on screen
					System.out.println("\n" + studio.toString());
					break;
					
				case 8: //Exit
					System.out.println("\nExiting...\n");
					break;
			}
		}
	}
}
