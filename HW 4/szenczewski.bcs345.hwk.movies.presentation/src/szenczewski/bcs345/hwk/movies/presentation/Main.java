package szenczewski.bcs345.hwk.movies.presentation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import szenczewski.bcs345.hwk.movies.business.*;

/**
 * Presents a menu for dealing with Movie and Studio data
 * @author Michael Szenczewski
 * @version 2.0
 * @since 11/5/2017
 */
public class Main 
{
	/**
	 * Presents a menu for dealing with Movie and Studio data
	 */
	public static void main(String[] args) 
	{
		Scanner console = new Scanner(System.in);
		int menuChoice = 0;
		
		while(menuChoice != 3)
		{
			//print menu
			System.out.println("Choose UI");
			System.out.println("---------");
			System.out.println("1 - Movie Console UI");
			System.out.println("2 - Studio Console UI");
			System.out.println("3 - Exit\n");
			System.out.print("Enter Choice: ");

			while (true)
			{
				try
				{
					//get menu choice
					menuChoice = console.nextInt();
					console.nextLine();
					
					//exit loop if a valid choice was recieved
					if (menuChoice >= 1 && menuChoice <= 3)
					{
						break; 
					}
					else
					{
						System.out.print("Please choose a number between one and three (1-3): ");
					}
				}
				catch (InputMismatchException e)
				{
					System.out.print("Please choose a number between one and three (1-3): ");
					console.nextLine();
				}
			}
			
			//act on user choice
			switch (menuChoice)
			{
				case 1: //Movie Console UI
					MovieConsoleUI movie = new MovieConsoleUI();
					System.out.println();
					movie.ShowUI();
					break;
				case 2: //Studio Console UI
					StudioConsoleUI studio = new StudioConsoleUI();
					System.out.println();
					studio.ShowUI();
					break;
				case 3: //Exit
					System.out.println("\nExiting...\n");
					break;
			}


		}
	}
}
