package szenczewski.bcs345.hwk.movies.presentation;

import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import szenczewski.bcs345.hwk.movies.business.Studio;

/**
 * Presents a GUI for dealing with studio data
 * @author Michael Szencewski
 * @version 1.0
 * @since 11/26/2017
 */
public class StudioApplication extends Application
{
	private Stage primaryStage;
	private Studio studio = new Studio();
	private TextField nameText = new TextField();
	private TextField netText = new TextField();
	private TextField highestText = new TextField();
	private 	ObservableList<String> movies = FXCollections.observableArrayList();
	
	/**
	 * Presents a GUI for dealing with studio data
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		BorderPane root = new BorderPane();

		MenuBar menuBar = menuBar();
		
		TabPane tabPane = tabPane();
				
		root.setTop(menuBar);
		root.setCenter(tabPane);
		
		Scene scene = new Scene(root, 500, 500);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Movie Studio Application");
		primaryStage.show();
	}
	
	/**
	 * Helper function to create the menu bar
	 * @return menubar
	 */
	private MenuBar menuBar()
	{
		MenuItem openMenuItem = new MenuItem("Open...");
		MenuItem saveAsMenuItem = new MenuItem("Save As...");
		MenuItem saveReportMenuItem = new MenuItem("Save Report...");
		MenuItem exportJSONMenuItem = new MenuItem("Export As JSON...");
		MenuItem exitMenuItem = new MenuItem("Exit...");
		
		Menu file = new Menu("File", null, openMenuItem, new SeparatorMenuItem(), saveAsMenuItem, saveReportMenuItem, new SeparatorMenuItem(), exportJSONMenuItem, new SeparatorMenuItem(), exitMenuItem);
		
		MenuBar menuBar = new MenuBar(file);
		
		openMenuItem.setOnAction(openFile);
		saveAsMenuItem.setOnAction(saveAs);
		saveReportMenuItem.setOnAction(saveReport);
		exportJSONMenuItem.setOnAction(exportJSON);
		exitMenuItem.setOnAction(exit); 
		
		return menuBar;
	}
	
	/**
	 * Helper function to create studio data 
	 * @return Studio data as a VBox
	 */
	private VBox studioData()
	{
		Label nameLabel = new Label("Studio Name");
		Label netLabel = new Label("Total Net");
		Label highestLabel = new Label("Highest Net Movie");
		
		nameLabel.setPrefWidth(150);
		netLabel.setPrefWidth(150);
		highestLabel.setPrefWidth(150);
								
		nameText.setEditable(false);
		netText.setEditable(false);
		highestText.setEditable(false);
		
		HBox name = new HBox(nameLabel, nameText);
		HBox net = new HBox(netLabel, netText);
		HBox highest = new HBox(highestLabel, highestText);
		
		//						top right bottom left
		Insets padding = new Insets(12, 0, 0, 12);
		
		name.setPadding(padding);
		net.setPadding(padding);
		highest.setPadding(padding);
		
		VBox studioData = new VBox(name, net, highest);
		
		return studioData;
	}
	
	/**
	 * Helper function to create list of movies
	 * @return movies as a ListView
	 */
	private ListView<String> movieData()
	{			
		ListView<String> listView = new ListView<String>(movies);

		return listView;
	}
	
	/**
	 * Helper function to create tab bar
	 * @return tab bar as a TabPane
	 */
	private TabPane tabPane()
	{
		VBox studioData = studioData();
		ListView<String> movieData = movieData();
		
		movies.add("No movie data available."); //default value
		
		Tab studioDataTab = new Tab("Studio Data", studioData);
		Tab movieDataTab = new Tab("Movie Data", movieData);
				
		TabPane tabPane = new TabPane(studioDataTab, movieDataTab);
		
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); //prevents closing tabs
		
		return tabPane;
	}
	
	private EventHandler<ActionEvent> exit = new EventHandler<ActionEvent>() 
	{
		public void handle(ActionEvent t) 
		{
			System.exit(0);
		}
	};
	
	private EventHandler<ActionEvent> openFile = new EventHandler<ActionEvent>() 
	{
		public void handle(ActionEvent t) 
		{
			FileChooser fc = new FileChooser();
			
			fc.setTitle("Open Studio File");
			fc.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
			
			File inputFile = fc.showOpenDialog(primaryStage);
			
			try
			{
				//declare new studio
				Studio newStudio = new Studio();
				
				//try to read in data
				Scanner inputFromFile = new Scanner(new FileReader(inputFile));
				newStudio.Read(inputFromFile);
				
				//if no exception, replace member variable data
				studio = newStudio;
				
				//update Studio Data tab
				nameText.setText(studio.GetName());
				netText.setText(Double.toString(studio.CalculateNet()));
				highestText.setText(studio.GetHighestNetMovie().GetTitle());
				
				//update Movie Data tab
				int numMovies = studio.GetNumMovies();
				
				movies.clear(); //removes "No data available"

				for (int i = 0; i < numMovies; i++)
				{		
					movies.add(studio.GetByIndex(i).toString());
				}
			}
			catch(Exception e)
			{
				System.out.println("ERROR: File not read.");
			}
		}
	};
	
	private EventHandler<ActionEvent> saveAs = new EventHandler<ActionEvent>() 
	{
		public void handle(ActionEvent t) 
		{
			FileChooser fc = new FileChooser();

			fc.setTitle("Save As (Studio)");
			fc.setInitialFileName("output.txt");
			
			File outputFile = fc.showSaveDialog(primaryStage);
			
			try
			{		
				PrintStream outputToFile = new PrintStream(outputFile);
				studio.Write(outputToFile);
			}
			catch(Exception e)
			{
				System.out.println("ERROR: File not written.");
			}
		}
	};
	
	private EventHandler<ActionEvent> saveReport = new EventHandler<ActionEvent>() 
	{
		public void handle(ActionEvent t) 
		{
			FileChooser fc = new FileChooser();

			fc.setTitle("Save As (Report)");
			fc.setInitialFileName("report.txt");
			
			File outputFile = fc.showSaveDialog(primaryStage);
			
			try
			{		
				PrintStream outputToFile = new PrintStream(outputFile);
				studio.Report(outputToFile);
			}
			catch(Exception e)
			{
				System.out.println("ERROR: File not written.");
			}		
		}
	};
	
	private EventHandler<ActionEvent> exportJSON = new EventHandler<ActionEvent>() 
	{
		public void handle(ActionEvent t) 
		{
			FileChooser fc = new FileChooser();

			fc.setTitle("Export JSON");
			fc.setInitialFileName("studio.json");
			
			File outputFile = fc.showSaveDialog(primaryStage);
			
			try
			{		
				PrintStream outputToFile = new PrintStream(outputFile);
				outputToFile.println(studio.GetJSON());
			}
			catch(Exception e)
			{
				System.out.println("ERROR: File not written.");
			}	
		}
	};
	

}
