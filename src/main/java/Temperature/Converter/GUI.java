
package Temperature.Converter;


import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GUI extends Application {
	
	private static final String ERROR_NO_DATA = "INGA MÄTVÄRDEN HITTADES!";
	private static ArrayList<Temperature> tempSeries;
	private static ArrayList<Temperature> tempSeriesCelsius;
	private static ArrayList<Temperature> tempSeriesFahrenheit;
	private static DecimalFormat numberFormat = new DecimalFormat("#.00"); // Antal decimaler
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Hello world!");
		
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

        VBox vBox = new VBox(menuBar);
		
		//StackPane root = new StackPane();
		//primaryStage.setScene(new Scene(root, 300, 250));

        Scene scene = new Scene(vBox, 960, 600);

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
        
        
        choice8Item.setOnAction(e -> {        	
            primaryStage.close();        	
        });
	}
	
}



