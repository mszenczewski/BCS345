package szenczewski.bcs345.hwk.movies.business;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Class to hold Studio data
 * @author Michael Szenczewski
 * @version 1.0
 * @since 11/5/2017
 */
public class Studio
{
	private String name;
	private Movie[] movieArray;
	private int numMovies;
	
	/**
	 * Default constructor
	 */
	public Studio()
	{
		name = "NO_NAME";
		movieArray = new Movie[4];
		
		for (int i = 0; i < 4; i++)
		{
			movieArray[i] = new Movie();
		}
	}
	
	/**
	 * Set method for name
	 * @param name Name
	 */
	public void SetName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Get method for name
	 * @return name
	 */
	public String GetName()
	{
		return name;
	}
	
	/**
	 * Get method for numMovies
	 * @return number of movies
	 */
	public int GetNumMovies()
	{
		return numMovies;
	}
	
	/**
	 * Reads studio data from a file
	 * @param s Scanner to read data from
	 */
	public void Read(Scanner s)
	{
		name = s.nextLine();
		
		numMovies = s.nextInt();
		s.nextLine();
		
		//create new array
		movieArray = new Movie[numMovies];
		
		//populate array
		for (int i = 0; i < numMovies; i++)
		{			
			movieArray[i] = new Movie();
			movieArray[i].Read(s);
		}
	}
	
	/**
	 * Writes studio data to a file
	 * @param ps PrintStream to write data to
	 */
	public void Write(PrintStream ps)
	{
		ps.println(name);
		ps.println(numMovies);
		
		//print array
		for (int i = 0; i < numMovies; i++)
		{
			movieArray[i].Write(ps);
		}
	}
	
	/**
	 * Prints a report of studio data
	 * @param ps PrintStream to print report to
	 */
	public void Report(PrintStream ps)
	{
		double totalBudget = 0.0;
		double totalGross = 0.0;
		double totalNet = 0.0;

		ps.println("Movie Studio Report");
		ps.println("-------------------");
		ps.printf("Studio: %s\n", name);
		ps.printf("Movie Count: %d\n\n", numMovies);
		
		ps.println("Movie Statistics\n");
		
		ps.printf("Title                      Mth  Day  Year   Theaters  Gross (mil) Budget (mil)  Net (mil)\n");
		ps.printf("-----                      ---  ---  ----   --------  ----------- ------------  ---------\n");

		//print each member of array
		for (int i = 0; i < numMovies; i++)
		{
			movieArray[i].Report(ps);
			
			totalBudget += movieArray[i].GetBudget();
			totalGross += movieArray[i].GetGross();
			totalNet += movieArray[i].CalculateNet();
		}
		
		ps.printf("-----                      ---  ---  ----   --------  ----------- ------------    -------\n");
		ps.printf("Total");
		ps.printf("%60.1f", totalGross);
		ps.printf("%13.1f", totalBudget);
		ps.printf("%11.1f\n", totalNet);
	}
	
	/**
	 * Returns studio data as JSON-formatted string
	 * @return JSON-formatted string
	 */
	public String GetJSON()
	{
		String s = "";
		
		s += "{ \"name\" : \"";
		s += name;
		s += "\", \"movieArray\" : [ ";
		
		//print each element
		for (int i = 0; i < numMovies; i++)
		{
			s += movieArray[i].GetJSON();
			
			if (i != numMovies - 1) 
			{
				s += ", ";
			}
		}
		
		s += " ] }";
		
		return s;
	}
	
	/**
	 * Overrides toString method to return studio data as a readable string
	 * @return Studio data as a readable string
	 */
	@Override
	public String toString()
	{
		String s = "";
		
		s += "Studio Name: " + name + "\n";

		//add each element to string
		for (int i = 0; i < numMovies; i++)
		{
			s += movieArray[i].toString();
		}
		
		return s;
	}
	
	/**
	 * Returns movie data of given index from movie array
	 * @param index Index of movie array
	 * @return movie data at index
	 */
	public Movie GetByIndex(int index)
	{
		try
		{
			return movieArray[index];
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			throw e;
		}
	}
	
	/**
	 * Returns the movie with the highest net profit
	 * @return the movie with the highest net
	 */
	public Movie GetHighestNetMovie()
	{
		double highest = 0;
		int index = 0;
		
		for	(int i = 0; i < numMovies; i++)
		{
			if (movieArray[i].CalculateNet() > highest)
			{
				highest = movieArray[i].CalculateNet();
				index = i;
			}
		}
		
		return movieArray[index];
	}
	
	/**
	 * Calculates the net of the studio
	 * @return net of studio
	 */
	public double CalculateNet()
	{
		double net = 0;
		
		for (int i = 0; i < numMovies; i++)
		{
			net += movieArray[i].CalculateNet();
		}
		
		return net;
	}
}
