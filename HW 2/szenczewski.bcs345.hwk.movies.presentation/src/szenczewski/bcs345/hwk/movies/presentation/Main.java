package szenczewski.bcs345.hwk.movies.presentation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

import szenczewski.bcs345.hwk.movies.business.*;

/**
 * Performs automated unit testing on BoxOffice and MyDate classes
 * @author Michael Szenczewski
 * @version 1.0
 * @since 10/2/2017
 */
public class Main 
{
	/**
	 * Main. Performs automated unit testing on BoxOffice and MyDate classes
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int month = 9;
		int day = 15;
		int year = 1992;
		int theatre = 74;
		double gross = 425.2;
		MyDate testDate = new MyDate();
		BoxOffice testBox = new BoxOffice();
		String dateFile = "date.txt";
		String boxFile = "box.txt";
		Scanner dateInput = null;
		Scanner boxInput = null;
		PrintStream dateOutput = null;
		PrintStream boxOutput = null;

		//open MyDate input file
		try
		{
			dateInput = new Scanner(new FileReader(dateFile));
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}

		//open MyDate output file
		try
		{
			dateOutput = new PrintStream(dateFile);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		//open BoxOffice input file
		try
		{
			boxInput = new Scanner(new FileReader(boxFile));
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}

		//open BoxOffice output file
		try
		{
			boxOutput = new PrintStream(boxFile);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		//test MyDate month set/get
		testDate.SetMonth(month);
		
		if (testDate.GetMonth() == month)
		{
			System.out.println("MyDate Month Set/Get: Pass");
		}
		else
		{
			System.out.println("MyDate Month Set/Get: Fail");
		}
		
		//test MyDate day set/get
		testDate.SetDay(day);
		
		if (testDate.GetDay() == day)
		{
			System.out.println("MyDate Day Set/Get: Pass");
		}
		else
		{
			System.out.println("MyDate Day Set/Get: Fail");
		}
		
		//test MyDate year set/get
		testDate.SetYear(year);
		
		if (testDate.GetYear() == year)
		{
			System.out.println("MyDate Year Set/Get: Pass");
		}
		else
		{
			System.out.println("MyDate Year Set/Get: Fail");
		}
		
		//test MyDate constructor
		MyDate testDate2 = new MyDate(month, day, year);
		
		if (testDate2.GetMonth() == month &&
			testDate2.GetDay() == day &&
			testDate2.GetYear() == year)
		{
			System.out.println("MyDate Constructor: Pass");
		}
		else
		{
			System.out.println("MyDate Constructor: Fail");
		}
		
		//test MyDate Read/Write
		testDate.Write(dateOutput);
		testDate.Read(dateInput);
		
		if (testDate.GetMonth() == month && 
			testDate.GetDay() == day && 
			testDate.GetYear() == year)
		{
			System.out.println("MyDate Read/Write: Pass");
		}
		else
		{
			System.out.println("MyDate Read/Write: Fail");
		}
		
		//test MyDate GetJSON
		String dateJSON = "{ \"month\" : " + month + ", \"day\" : " + day +", \"year\" : " + year + " }";
		
		if (testDate.GetJSON().equals(dateJSON))
		{
			System.out.println("MyDate GetJSON: Pass");		
		}
		else
		{
			System.out.println("MyDate GetJSON: Fail");		
		}
		
		//test MyDate toString
		String dateString = month + "/" + day + "/" + year;
		
		if (testDate.toString().equals(dateString))
		{
			System.out.println("MyDate toString: Pass");
		}
		else
		{
			System.out.println("MyDate toString: Fail");
		}
		
		//test BoxOffice gross set/get
		testBox.SetGross(gross);
		
		if (testBox.GetGross() == gross)
		{
			System.out.println("BoxOffice Gross Set/Get: Pass");
		}
		else
		{
			System.out.println("BoxOffice Gross Set/Get: Fail");
		}		
		
		//test BoxOffice theatre set/get
		testBox.SetTheatreCount(theatre);
		
		if (testBox.GetTheatreCount() == theatre)
		{
			System.out.println("BoxOffice TheatreCount Set/Get: Pass");
		}
		else
		{
			System.out.println("BoxOffice TheatreCount Set/Get: Fail");
		}

		//test BoxOffice constructor
		BoxOffice testBox2 = new BoxOffice(gross, theatre);
		
		if (testBox2.GetGross() == gross &&
			testBox2.GetTheatreCount() == theatre)
		{
			System.out.println("BoxOffice Constructor: Pass");
		}
		else
		{
			System.out.println("BoxOffice Constructor: Fail");
		}
		
		//test BoxOffice Read/Write
		testBox.Write(boxOutput);
		testBox.Read(boxInput);
		
		if (testBox.GetGross() == gross && 
			testBox.GetTheatreCount() == theatre)
		{
			System.out.println("BoxOffice Read/Write: Pass");
		}
		else
		{
			System.out.println("BoxOffice Read/Write: Fail");
		}
		
		//test BoxOffice GetJSON
		String boxJSON = "{ \"gross\" : " + gross +", \"theatreCount\" : " + theatre + " }";
		
		if (testBox.GetJSON().equals(boxJSON))
		{
			System.out.println("BoxOffice GetJSON: Pass");		
		}
		else
		{
			System.out.println("BoxOffice GetJSON: Fail");		
		}

		//test BoxOffice toString
		String boxString = "Gross: " + gross + "\nNumber of Theatres: " + theatre;
		
		if (testBox.toString().equals(boxString))
		{
			System.out.println("BoxOffice toString: Pass");
		}
		else
		{
			System.out.println("BoxOffice toString: Fail");
		}	
	}
}
