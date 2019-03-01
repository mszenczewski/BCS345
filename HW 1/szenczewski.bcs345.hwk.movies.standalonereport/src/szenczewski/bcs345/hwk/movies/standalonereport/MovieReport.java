package szenczewski.bcs345.hwk.movies.standalonereport;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Generates a movie report based on an input file
 * 
 * @author Michael Szenczewski
 * @version 1.0
 * @since 9/9/2017
 *
 */
public class MovieReport
{
	/**
	 * Generates a movie report based on an input file
	 * 
	 */
	public static void main(String[] args)
	{
		int numMovies;
		int releaseMonth;
		int releaseDay;
		int releaseYear;
		int numTheatres;
		double budget;
		double gross;
		double net;
		double totalBudget = 0.0;
		double totalGross = 0.0;
		double totalNet = 0.0;
		String inputFile;
		String outputFile;
		String studio;
		String title;
		Scanner console;
		Scanner inputFromFile;
		FileReader input = null;
		PrintStream output = null;

		console = new Scanner(System.in);

		// Get input file name
		System.out.printf("Enter name of input file: ");
		inputFile = console.nextLine();

		// Get output file name
		System.out.printf("Enter name of output file: ");
		outputFile = console.nextLine();

		System.out.printf("\nUsing input file %s...\nUsing output file %s...\n\n", inputFile, outputFile);

		// Open input file
		try
		{
			input = new FileReader(inputFile);
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}

		inputFromFile = new Scanner(input);

		// Open output file
		try
		{
			output = new PrintStream(outputFile);
		}
		catch (Exception e)
		{
			System.out.println("ERROR. Could not open file!");
		}

		// Input general data from file
		studio = inputFromFile.nextLine();

		numMovies = inputFromFile.nextInt();
		inputFromFile.nextLine();

		// Print general information
		output.printf("Movie Studio Report\n");
		output.printf("-------------------\n");
		output.printf("Studio: %s\n", studio);
		output.printf("Movie Count: %d\n\n", numMovies);
		output.printf("Movie Statistics\n\n");
		output.printf("Title                      Mth  Day  Year   Theaters  Gross (mil) Budget (mil)  Net (mil)\n");
		output.printf("-----                      ---  ---  ----   --------  ----------- ------------  ---------\n");

		// Run until empty line is reached
		while (inputFromFile.hasNext())
		{
			// Input each movie data from file
			title = inputFromFile.nextLine();

			releaseMonth = inputFromFile.nextInt();
			inputFromFile.nextLine();

			releaseDay = inputFromFile.nextInt();
			inputFromFile.nextLine();

			releaseYear = inputFromFile.nextInt();
			inputFromFile.nextLine();

			budget = inputFromFile.nextDouble();
			inputFromFile.nextLine();

			gross = inputFromFile.nextDouble();
			inputFromFile.nextLine();

			numTheatres = inputFromFile.nextInt();
			inputFromFile.nextLine();

			net = gross - budget;

			totalGross += gross;
			totalBudget += budget;
			totalNet += net;

			System.out.printf("Generating report for %s...\n", title);

			// Output to file
			output.printf("%-27s", title);
			output.printf("%3d", releaseMonth);
			output.printf("%5d", releaseDay);
			output.printf("%6d", releaseYear);
			output.printf("%11d", numTheatres);
			output.printf("%13.1f", gross);
			output.printf("%13.1f", budget);
			output.printf("%11.1f\n", net);
		}

		// Print totals
		output.printf("-----                      ---  ---  ----   --------  ----------- ------------    -------\n");
		output.printf("Total");
		output.printf("%60.1f", totalGross);
		output.printf("%13.1f", totalBudget);
		output.printf("%11.1f\n", totalNet);

		System.out.printf("\nReport complete.");
	}
}
