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

public class GUI extends Application {
	
	private static final String ERROR_NO_DATA = "INGA MÄTVÄRDEN HITTADES!";
	private static final String STYLE_SHEET = "/stylesheet.css";
	private static ArrayList<Temperature> tempSeries;
	private static ArrayList<Temperature> tempSeriesCelsius;
	private static ArrayList<Temperature> tempSeriesFahrenheit;
	private static DecimalFormat numberFormat = new DecimalFormat("#.00"); // Antal decimaler
	final NumberAxis xAxis = new NumberAxis(0, 10, 1);
	final NumberAxis yAxis = new NumberAxis();
	final LineChart<Number, Number> sc = new LineChart<Number, Number>(xAxis, yAxis);
    //final XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
    private Scene scene;
    //private Scene scene2;

    ObservableList sPaneList01, sPaneList02;
    
    StackPane stackPane01, stackPane02;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		
		
		primaryStage.setTitle("Hello world!");
		
		Label first_name=new Label("Temperatur");
		//first_name.setStyle("-fx-text-fill: white;");

		
		Image image = null;
		
		// ***** MENUBAR ************************
		MenuBar menuBar = new MenuBar();
        
        Menu menu = new Menu("Menu 1");

        RadioMenuItem choice1Item = new RadioMenuItem("LÄS MÄTVÄRDEN FRÅN FIL");
        RadioMenuItem choice2Item = new RadioMenuItem("LÄS MÄTVÄRDEN FRÅN INTERNET");
        RadioMenuItem choice3Item = new RadioMenuItem("VISA MÄTVÄRDEN");
        RadioMenuItem choice4Item = new RadioMenuItem("VISA MÄTVÄRDEN I CELSIUS");
        RadioMenuItem choice5Item = new RadioMenuItem("VISA MÄTVÄRDEN I FAHRENHEIT");
        RadioMenuItem choice6Item = new RadioMenuItem("BERÄKNA MEDELVÄRDE OCH VISA I CELSIUS");
        RadioMenuItem choice7Item = new RadioMenuItem("BERÄKNA MEDELVÄRDE OCH VISA I FAHRENHEIT");
        RadioMenuItem choice8Item = new RadioMenuItem("AVSLUTA");
        
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().add(choice1Item);
        toggleGroup.getToggles().add(choice2Item);
        toggleGroup.getToggles().add(choice3Item);
        toggleGroup.getToggles().add(choice4Item);
        toggleGroup.getToggles().add(choice5Item);
        toggleGroup.getToggles().add(choice6Item);
        toggleGroup.getToggles().add(choice7Item);
        toggleGroup.getToggles().add(choice8Item);

        menu.getItems().add(choice1Item);
        menu.getItems().add(choice2Item);
        menu.getItems().add(choice3Item);
        menu.getItems().add(choice4Item);
        menu.getItems().add(choice5Item);
        menu.getItems().add(choice6Item);
        menu.getItems().add(choice7Item);
        menu.getItems().add(choice8Item);

        menuBar.getMenus().add(menu);        

        
        // ***** IMAGE **************************************
      //Creating an image 
        try {
            String current = new java.io.File( "." ).getCanonicalPath();
            System.out.println("Current dir:"+ current);
            image = new Image(new FileInputStream("src/ressource/images/sky.jpeg"));           
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
        sPaneList01.addAll(vBox, rectangle, first_name);
          
        //Creating a scene object 
        scene = new Scene(stackPane01);        
         
        scene.getStylesheets().add(STYLE_SHEET); 
        //scene.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());

        
        primaryStage.setScene(scene);
        primaryStage.show();        
        
        // LÄS MÄTVÄRDEN FRÅN FIL
        choice1Item.setOnAction(e -> {        	
        	System.out.println("Läser in mätvärden");
        	tempSeries = FileReader.temperatureList();
        	if (tempSeries.size() == 0) {
				System.err.println(ERROR_NO_DATA);
			} else {
				System.out.println("\n" + tempSeries.size() + " MÄTVÄRDEN HAR LÄSTS IN.");
			}
        });        
         
        // VISA MÄTVÄRDEN I CELSIUS
        choice4Item.setOnAction(e -> {        	
        	System.out.println("Visa mätvärden i Celsius");
        	tempSeriesCelsius = Converter.convertFahrenheitToCelsius(tempSeries);
        	
 		    xAxis.setLabel("Mätning");
   		    yAxis.setLabel("Temperatur");
   		    sc.setTitle("Mätningar av temperatur");
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
 		    averageTempLine.setName("Medeltemperatur");
 		    
        	int temp_num = 0;
        	for(Temperature tempObject: tempSeriesCelsius) {
        		temp_num ++;
        		Double temp = tempObject.getTemp();
        		System.out.println("mätning nr "+ temp_num + ": Mätvärde: " + tempObject.getTemp());
     		    series.getData().add(new XYChart.Data(temp_num, tempObject.getTemp()));
        	}
        	
        	Double averageTemp = calculateAverage(tempSeriesCelsius);
 		    averageTempLine.getData().add(new XYChart.Data(0, averageTemp));
 		    averageTempLine.getData().add(new XYChart.Data(tempSeries.size(), averageTemp));
 		    
 		    averageTempLine.getData().add(new XYChart.Data(0, averageTemp));

 		    
 		    
        	
    		sc.getData().addAll(series, averageTempLine, medianTempMark);
    		
    		sPaneList01 = stackPane01.getChildren();           
            sPaneList01.addAll(sc);
        });
        
        choice5Item.setOnAction(e -> {
        	
        	
        	stackPane01.getChildren().remove(sc);
        	System.out.println("Visa mätvärden i Celsius");
        	tempSeriesCelsius = Converter.convertFahrenheitToCelsius(tempSeries);
        	final NumberAxis xAxis = new NumberAxis(0, 10, 1);
 		    final NumberAxis yAxis = new NumberAxis();
 		    final LineChart<Number, Number> sc = new LineChart<Number, Number>(xAxis, yAxis);
 		    xAxis.setLabel("Mätning");
   		    yAxis.setLabel("Temperatur");
   		    sc.setTitle("Mätningar med medianvärde");
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
 		    medianTempMark.setName("Median");
 		    
        	int temp_num = 0;
        	for(Temperature tempObject: tempSeriesCelsius) {
        		temp_num ++;
        		Double temp = tempObject.getTemp();
        		System.out.println("mätning nr "+ temp_num + ": Mätvärde: " + tempObject.getTemp());
     		    series.getData().add(new XYChart.Data(temp_num, tempObject.getTemp()));
        	}
        	
        	        	
        	Double median = calculateMedian(tempSeriesCelsius);       	
        	
        	Double averageTemp = calculateAverage(tempSeriesCelsius);
 		    averageTempLine.getData().add(new XYChart.Data(0, averageTemp));
 		    averageTempLine.getData().add(new XYChart.Data(tempSeries.size(), averageTemp));
 		    
 		    averageTempLine.getData().add(new XYChart.Data(0, averageTemp)); 
 		    
    		sc.getData().addAll(series, averageTempLine, medianTempMark);
    		
    		sPaneList01 = stackPane01.getChildren();           
            sPaneList01.addAll(sc);
            
        });       
        
        
        choice8Item.setOnAction(e -> {        	
            primaryStage.close();        	
        });        
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
	
	private Double calculateMedian(ArrayList<Temperature> tempSeries) {
		return 14.0;
		
	}
}



