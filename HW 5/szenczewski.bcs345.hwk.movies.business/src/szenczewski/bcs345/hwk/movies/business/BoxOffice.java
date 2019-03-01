package szenczewski.bcs345.hwk.movies.business;

import java.io.PrintStream;
import java.util.Scanner;
/**
 * Class to contain Box Office data
 * @author Michael Szenczewski
 * @version 1.0
 * @since 10/2/2017
 */
public class BoxOffice 
{
	//member variables
	private double gross;
	private int theatreCount;
	
	/**
	 * Default Constructor
	 */
	public BoxOffice()
	{
		gross = 0.0;
		theatreCount = 0;
	}
	
	/**
	 * Constructor which takes two parameters
	 * @param gross Gross
	 * @param theatreCount Theatre Count
	 */
	public BoxOffice(double gross, int theatreCount)
	{
		this.gross = gross;
		this.theatreCount = theatreCount;
	}
	
	/**
	 * Setter for gross
	 * @param gross Gross
	 */
	public void SetGross(double gross)
	{
		this.gross = gross;
	}
	
	/**
	 * Setter for theatreCount
	 * @param theatreCount Number of theatres
	 */
	public void SetTheatreCount(int theatreCount)
	{
		this.theatreCount = theatreCount;
	}
	
	/**
	 * Getter for gross
	 * @return gross 
	 */
	public double GetGross()
	{
		return gross;
	}
	
	/**
	 * Getter for theatreCount
	 * @return number of theatres
	 */
	public int GetTheatreCount()
	{
		return theatreCount;
	}
	
	/**
	 * Writes box office data to a PrintStream object
	 * @param ps PrintStream object to output data to
	 */
	public void Write(PrintStream ps)
	{
		ps.println(gross);
		ps.println(theatreCount);
	}
	
	/**
	 * Reads box office data from a Scanner object
	 * @param s Scanner object to input data from
	 */
	public void Read(Scanner s)
	{
		gross = s.nextDouble();
		s.nextLine();
		
		theatreCount = s.nextInt();
		s.nextLine();
	}
	
	/**
	 * Returns JSON-formatted data
	 * @return date in JSON-formatted string
	 */
	public String GetJSON()
	{
		return "{ \"gross\" : " + gross + ", \"theatreCount\" : " + theatreCount + " }";
	}
	
	/**
	 * Returns data in readable format
	 * @return data in readable format
	 */
	@Override
	public String toString()
	{
		return "Gross: " + gross + "\nNumber of Theatres: " + theatreCount;
	}
	
}	


