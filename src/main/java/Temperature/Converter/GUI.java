package Temperature.Converter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

//import com.sun.javafx.geom.Rectangle;
import javafx.scene.shape.Rectangle;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; 

public class GUI{
	
	private static final String ERROR_NO_DATA = "INGA MÄTVÄRDEN HITTADES!";
	private static final String STYLE_SHEET = "/resource/CSS/stylesheet.css";
	private static ArrayList<Temperature> tempSeries;
	private static ArrayList<Temperature> tempSeriesCelsius;
	private static ArrayList<Temperature> tempSeriesFahrenheit;
	private static DecimalFormat numberFormat = new DecimalFormat("#.00"); // Antal decimaler
	NumberAxis xAxis = new NumberAxis(0, 10, 1);
	NumberAxis yAxis = new NumberAxis();
	LineChart<Number, Number> sc = new LineChart<Number, Number>(xAxis, yAxis);
    private Scene scene;

    ObservableList sPaneList01, sPaneList02;
    
    StackPane stackPane01, stackPane02;
	
	
	//public static void main(String[] args) {
	//	launch(args);
	//}
	
	
	
	public Stage start() throws FileNotFoundException {
		
		
		Stage primaryStage = new Stage();
		
		Image image = null;
		
		// LÄS MÄTVÄRDEN FRÅN FIL
               	
        tempSeries = FileReader.temperatureList();
        if (tempSeries.size() == 0) {
			System.err.println(ERROR_NO_DATA);
		} else {
			System.out.println("\n" + tempSeries.size() + " MÄTVÄRDEN HAR LÄSTS IN.");
		}        
		
		primaryStage.setTitle("Temperatur");		
		
		// ***** MENUBAR ************************
		MenuBar menuBar = new MenuBar();
        
        Menu menu = new Menu("Menu 1");

        RadioMenuItem choice1Item = new RadioMenuItem("VISA MÄTVÄRDEN I CELSIUS");
        RadioMenuItem choice2Item = new RadioMenuItem("VISA MÄTVÄRDEN I FAHRENHEIT"); 
        RadioMenuItem choice3Item = new RadioMenuItem("STÄNG FÖNSTER"); 
        
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().add(choice1Item);
        toggleGroup.getToggles().add(choice2Item);
        toggleGroup.getToggles().add(choice3Item);        

        menu.getItems().add(choice1Item);
        menu.getItems().add(choice2Item);
        menu.getItems().add(choice3Item);

        menuBar.getMenus().add(menu);        

        
        // ***** IMAGE **************************************
      //Creating an image 
        try {
            String current = new java.io.File( "." ).getCanonicalPath();
            System.out.println("Current dir:"+ current);
            image = new Image(new FileInputStream("src/main/java/resource/sky.jpeg"));           
        } catch(Exception E) {
        	System.out.println("Error");
            E.printStackTrace(); 
        }
        
      //Setting the image view 
        ImageView imageView = new ImageView(image); 
        
        //Setting the position of the image 
        imageView.setX(50); 
        imageView.setY(25); 
        
        //setting the fit height and width of the image view 
        imageView.setFitHeight(800); 
        imageView.setFitWidth(1200); 
        
        //Setting the preserve ratio of the image view 
        imageView.setPreserveRatio(true);
        
        VBox vBox = new VBox(menuBar, imageView);
        
        //***** RECTANGLE ********************************
      //Drawing a Rectangle 
        Rectangle rectangle = new Rectangle();  
        
        //Setting the properties of the rectangle 
        rectangle.setX(150.0f); 
        rectangle.setY(75.0f); 
        rectangle.setWidth(1100.0f); 
        rectangle.setHeight(700.0f);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.rgb(46,62,102,0.6));
        rectangle.setStrokeWidth(0);       
        
        stackPane01 = new StackPane();       
        sPaneList01 = stackPane01.getChildren(); 
        sPaneList01.addAll(vBox, rectangle);
          
        //Creating a scene object 
        scene = new Scene(stackPane01);        
         
        scene.getStylesheets().add(STYLE_SHEET); 
        //scene.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
        
        primaryStage.setScene(scene);
        primaryStage.show();               
         
        // VISA MÄTVÄRDEN I CELSIUS
        choice1Item.setOnAction(e -> {   
        	
        	stackPane01.getChildren().remove(sc);
        	tempSeriesCelsius = Converter.convertFahrenheitToCelsius(tempSeries);
        	ArrayList<Temperature> sortedSeries = SortTempList.sortList(tempSeriesCelsius);
        	NumberAxis xAxis = new NumberAxis(0, 10, 1);
 		    NumberAxis yAxis = new NumberAxis();
 		    sc = new LineChart<Number, Number>(xAxis, yAxis);
 		    xAxis.setLabel("Mätning");
   		    yAxis.setLabel("Temperatur i Celsius");
   		    sc.setTitle("Temperaturmätningar i Celsius");
	   		sc.setPrefSize(900, 700);
	        sc.setMinSize(900, 700);
	        sc.setMaxSize(900, 700);
	        sc.setStyle("-fx-horizontal-grid-lines-visible: false;");
	        sc.setStyle("-fx-text-fill: white;");
	        xAxis.setStyle("-fx-text-fill: white;");       
 		   
 		    XYChart.Series series = new XYChart.Series();
 		    XYChart.Series averageTempLine = new XYChart.Series();
 		    XYChart.Series medianTempMark = new XYChart.Series();

 		    series.setName("Mätvärden i Celsius");
 		    averageTempLine.setName("Medelvärde");
 		    medianTempMark.setName("Median");
 		    
        	int temp_num = 0;
        	for(Temperature tempObject: sortedSeries) {
        		temp_num ++;
        		Double temp = tempObject.getTemp();
        		System.out.println("mätning nr "+ temp_num + ": Mätvärde: " + tempObject.getTemp());
     		    series.getData().add(new XYChart.Data(temp_num, tempObject.getTemp()));
        	}       	
        	
        	double averageTemp = calculateAverage(sortedSeries);
 		    averageTempLine.getData().add(new XYChart.Data(0, averageTemp));
 		    averageTempLine.getData().add(new XYChart.Data(tempSeries.size(), averageTemp));
 		    
 		    double medianMarkXPos = getMedianMarkXPos(sortedSeries);
 		    double median = new MedianTemp(sortedSeries).getMedianD();
 		    
 		    medianTempMark.getData().add(new XYChart.Data(medianMarkXPos, median)); 		    
 		    
 		    System.out.println("### GUI ### median: " + median);
       	
       		sc.getData().addAll(medianTempMark, averageTempLine, series);
    		
    		sPaneList01 = stackPane01.getChildren();           
            sPaneList01.add(sc);
        });
        
        choice2Item.setOnAction(e -> {        	
        	
        	// VISA MÄTVÄRDEN I FAHRENHEIT
        	stackPane01.getChildren().remove(sc);
        	tempSeriesFahrenheit = Converter.convertCelsiusToFahrenheit(tempSeries);
        	ArrayList<Temperature> sortedSeries = SortTempList.sortList(tempSeriesFahrenheit);
        	NumberAxis xAxis = new NumberAxis(0, 10, 1);
 		    NumberAxis yAxis = new NumberAxis();
 		    sc = new LineChart<Number, Number>(xAxis, yAxis);
 		    xAxis.setLabel("Mätning");
   		    yAxis.setLabel("Temperatur");
   		    sc.setTitle("Temperaturmätningar i Celsius");
	   		sc.setPrefSize(900, 700);
	        sc.setMinSize(900, 700);
	        sc.setMaxSize(900, 700);
	        sc.setStyle("-fx-horizontal-grid-lines-visible: false;");
	        sc.setStyle("-fx-text-fill: white;");
	        xAxis.setStyle("-fx-text-fill: white;");        
 		   
 		    XYChart.Series series = new XYChart.Series();
 		    XYChart.Series averageTempLine = new XYChart.Series();
 		    XYChart.Series medianTempMark = new XYChart.Series();

 		    series.setName("Mätvärden i Fahrenheit");
 		    averageTempLine.setName("Medelvärde");
 		    medianTempMark.setName("Median");
 		    
        	int temp_num = 0;
        	for(Temperature tempObject: sortedSeries) {
        		temp_num ++;
        		Double temp = tempObject.getTemp();
        		System.out.println("mätning nr "+ temp_num + ": Mätvärde: " + tempObject.getTemp());
     		    series.getData().add(new XYChart.Data(temp_num, tempObject.getTemp()));
        	}       	
        	
        	double averageTemp = calculateAverage(sortedSeries);
 		    averageTempLine.getData().add(new XYChart.Data(0, averageTemp));
 		    averageTempLine.getData().add(new XYChart.Data(tempSeries.size(), averageTemp));
 		    
 		    double medianMarkXPos = getMedianMarkXPos(sortedSeries);
 		    double median = new MedianTemp(sortedSeries).getMedianD();
 		    
 		    medianTempMark.getData().add(new XYChart.Data(medianMarkXPos, median)); 		    
 		    
 		    System.out.println("### GUI ### median: " + median);
       	
       		sc.getData().addAll(medianTempMark, averageTempLine, series);
    		
    		sPaneList01 = stackPane01.getChildren();           
            sPaneList01.add(sc);            
        });               
        
        choice3Item.setOnAction(e -> {         	
            primaryStage.close();        	
        });  
        
        return primaryStage;
	}
	
	private Double calculateAverage(ArrayList<Temperature> tempSeries) {
	      int sum = 0;
	      for (int i=0; i< tempSeries.size(); i++) {
	    	  Double temp = tempSeries.get(i).getTemp();
	    	  System.out.println("inside calculateAVerage. temp: " + temp);
	            sum += temp;
	      }
	      Double sumDouble = Double.valueOf(sum);
	      Double a = sumDouble / tempSeries.size();
	      System.out.println(sumDouble + " / " + tempSeries.size() + " = " + a);
	      return a;
	}
	
	private double getMedianMarkXPos(ArrayList<Temperature> tempSeries) {
		double xPos;
			if(tempSeries.size() % 2 == 1) {
				xPos = (tempSeries.size() + 1) / 2;
			}
			else {
				xPos = tempSeries.size() / 2 + 0.5;
			}
			return xPos;
	}
}



