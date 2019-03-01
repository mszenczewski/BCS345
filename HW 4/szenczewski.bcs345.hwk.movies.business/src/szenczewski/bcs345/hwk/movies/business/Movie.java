package szenczewski.bcs345.hwk.movies.business;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Class to hold Movie data
 * @author Michael Szenczewski
 * @version 1.0
 * @since 10/15/2017
 */
public class Movie
{
	//member variables
	private double budget;
	private String title;
	private MyDate openDate;
	private BoxOffice boxOfficeRevenue;
	
	/**
	 * Default constructor
	 */
	public Movie()
	{
		budget = 0;
		title = "NO_TITLE";
		openDate = new MyDate(0, 0, 0);
		boxOfficeRevenue = new BoxOffice(0, 0);
	}
	
	/**
	 * Set method for budget
	 * @param budget Budget
	 */
	public void SetBudget(double budget)
	{
		this.budget = budget;
	}
	
	/**
	 * Set method for title
	 * @param title Title of movie
	 */
	public void SetTitle(String title)
	{
		this.title = title;
	}
	
	/**
	 * Set method for openDate
	 * @param openDate Opening date as MyDate object
	 */
	public void SetOpenDate(MyDate openDate)
	{
		this.openDate = openDate;
	}
	
	/**
	 * Set method for boxOfficeRevenue
	 * @param boxOfficeRevenue Box Office revenue as BoxOffice object
	 */
	public void SetBoxOfficeRevenue(BoxOffice boxOfficeRevenue)
	{
		this.boxOfficeRevenue = boxOfficeRevenue;
	}
	
	/**
	 * Get method for budget
	 * @return budget
	 */
	public double GetBudget()
	{
		return budget;
	}
	
	/**
	 * Get method for title
	 * @return title
	 */
	public String GetTitle()
	{
		return title;
	}
	
	/**
	 * Get method for openDate
	 * @return openDate
	 */
	public MyDate GetOpenDate()
	{
		return openDate;
	}
	
	/**
	 * Get method for boxOfficeRevenue
	 * @return boxOfficeRevenue
	 */
	public BoxOffice GetBoxOfficeRevenue()
	{
		return boxOfficeRevenue;
	}
	
	/**
	 * Returns the gross of the movie
	 * @return gross
	 */
	public double GetGross()
	{
		return boxOfficeRevenue.GetGross();
	}
	
	/**
	 * Returns the net profit of the movie
	 * @return gross - budget
	 */
	public double CalculateNet()
	{
		return boxOfficeRevenue.GetGross() - budget;
	}
	
	/**
	 * Writes the movie data to a file
	 * @param ps PrintStream for writing data
	 */
	public void Write(PrintStream ps)
	{
		ps.println(title);
		ps.println(openDate.GetMonth());
		ps.println(openDate.GetDay());
		ps.println(openDate.GetYear());
		ps.println(budget);
		ps.println(boxOfficeRevenue.GetGross());
		ps.println(boxOfficeRevenue.GetTheatreCount());
	}
	
	/**
	 * Reads the movie data from a file
	 * @param s Scanner to read data from
	 */
	public void Read(Scanner s)
	{
		title = s.nextLine();
		
		openDate.SetMonth(s.nextInt());
		s.nextLine();
		
		openDate.SetDay(s.nextInt());
		s.nextLine();
		
		openDate.SetYear(s.nextInt());
		s.nextLine();
		
		budget = s.nextDouble();
		s.nextLine();
		
		boxOfficeRevenue.SetGross(s.nextDouble());
		s.nextLine();
		
		boxOfficeRevenue.SetTheatreCount(s.nextInt());
		s.nextLine();
	}
	
	/**
	 * Prints report of a movie file
	 * @param output PrintStream to print report to
	 */
	public void Report(PrintStream output)
	{
		output.printf("%-27s", title);
		output.printf("%3d", openDate.GetMonth());
		output.printf("%5d", openDate.GetDay());
		output.printf("%6d", openDate.GetYear());
		output.printf("%11d", boxOfficeRevenue.GetTheatreCount());
		output.printf("%13.1f", boxOfficeRevenue.GetGross());
		output.printf("%13.1f", budget);
		output.printf("%11.1f\n", CalculateNet());
	}
	
	/**
	 * Returns movie data in JSON-formatted string
	 * @return JSON-formatted string
	 */
	public String GetJSON()
	{
		return "{ \"title\" : \"" + title + "\", \"budget\" : " + budget + ", \"opendate\" : " + openDate.GetJSON() + ", \"boxoffice\" : " + boxOfficeRevenue.GetJSON() + " }";
	}
	
	/**
	 * Overrides toString method to return movie data in a readable format
	 * @return movie data as a string in readable format
	 */
	@Override
	public String toString()
	{
		String s = "";
		
		s += "Title: " + title + "\n";
		s += "Open Date: " + openDate + "\n";
		s += "Budget: " + budget + "\n";
		s += "Gross: " + boxOfficeRevenue.GetGross() + "\n";
		s += "Theatre Count: " + boxOfficeRevenue.GetTheatreCount() + "\n";
		
		return s;
	}

}
