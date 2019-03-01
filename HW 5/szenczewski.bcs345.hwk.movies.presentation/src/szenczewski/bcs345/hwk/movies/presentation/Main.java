package szenczewski.bcs345.hwk.movies.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Presents a menu for dealing with Movie and Studio data
 * @author Michael Szenczewski
 * @version 3.0
 * @since 11/26/2017
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
		int exitChoice = 4;
				
		while(menuChoice != exitChoice)
		{
			//print menu
			System.out.println("Choose UI");
			System.out.println("---------");
			System.out.println("1 - Movie Console");
			System.out.println("2 - Studio Console");
			System.out.println("3 - Studio GUI");
			System.out.println("4 - Exit\n");
			System.out.print("Enter Choice: ");

			while (true) 
			{
				try 
				{
					//get menu choice
					menuChoice = console.nextInt();
					console.nextLine();
					
					//exit loop if a valid choice was recieved
					if (menuChoice >= 1 && menuChoice <= exitChoice) 
					{
						break; 
					}
					else 
					{
						System.out.print("Please enter a number between 1 and " + exitChoice + ": "); 
					}
				}
				catch (InputMismatchException e) 
				{
					System.out.print("Please enter a number between 1 and " + exitChoice + ": "); 
					console.nextLine();
				}
			}
			
			//act on user choice
			switch (menuChoice)
			{
				case 1: //Movie Console 
					MovieConsoleUI movie = new MovieConsoleUI();
					System.out.println();
					movie.ShowUI();
					break;
				case 2: //Studio Console 
					StudioConsoleUI studioConsole = new StudioConsoleUI();
					System.out.println();
					studioConsole.ShowUI();
					break;
				case 3: //Studio GUI
					StudioGraphicalUI studioGUI = new StudioGraphicalUI();
					studioGUI.ShowUI();
					break;
				case 4: //Exit
					System.out.println("\nExiting...\n");
					break;
			}


		}
	}
}
