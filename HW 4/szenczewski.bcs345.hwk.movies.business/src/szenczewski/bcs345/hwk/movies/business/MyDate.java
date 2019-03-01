package szenczewski.bcs345.hwk.movies.business;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Class which contains a date
 * @author Michael Szenczewski
 * @version 1.0
 * @since 10/2/2017
 */
public class MyDate
{
	//member variables
	private int month;
	private int day;
	private int year;

	/**
	 * Default constructor
	 */
	public MyDate()
	{
		month = 0;
		day = 0;
		year = 0;
	}

	/**
	 * Constructor which takes three parameters
	 * @param month Month
	 * @param day Day
	 * @param year Year
	 */
	public MyDate(int month, int day, int year)
	{
		this.month = month;
		this.day = day;
		this.year = year;
	}

	/**
	 * Setter for month
	 * @param month Month
	 */
	public void SetMonth(int month)
	{
		this.month = month;
	}

	/**
	 * Setter for day
	 * @param day Day
	 */
	public void SetDay(int day)
	{
		this.day = day;
	}

	/**
	 * Setter for year
	 * @param year Year 
	 */
	public void SetYear(int year)
	{
		this.year = year;
	}

	/**
	 * Getter for month
	 * @return month
	 */
	public int GetMonth()
	{
		return month;
	}

	/**
	 * Getter for day
	 * @return day
	 */
	public int GetDay()
	{
		return day;
	}

	/**
	 * Getter for year
	 * @return year
	 */
	public int GetYear()
	{
		return year;
	}

	/**
	 * Reads date from a Scanner object
	 * @param s Scanner object to input date from
	 */
	public void Read(Scanner s)
	{
		month = s.nextInt();
		s.nextLine();

		day = s.nextInt();
		s.nextLine();

		year = s.nextInt();
		s.nextLine();
	}

	/**
	 * Writes date to a PrintStream object
	 * @param ps PrintStream object to output data to
	 */
	public void Write(PrintStream ps)
	{
		ps.println(month);
		ps.println(day);
		ps.println(year);
	}
	
	/**
	 * Returns JSON-formatted date
	 * @return date in JSON-formatted string
	 */
	public String GetJSON()
	{
		return "{ \"month\" : " + month + ", \"day\" : " + day + ", \"year\" : " + year + " }";
	}
	
	/**
	 * Returns date in D/M/Y format
	 * @return date in D/M/Y format
	 */
	@Override
	public String toString()
	{
		return month + "/" + day + "/" + year;
	}
}
