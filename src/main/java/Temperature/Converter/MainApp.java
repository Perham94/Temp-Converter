package Temperature.Converter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class MainApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("MainMeny.fxml"));

			Scene scene = new Scene(root);
			SplitPane p = (SplitPane) root.lookup("#splitPane");
			scene.setFill(Color.TRANSPARENT);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			final ArrayList<Temperature> arrayTemps = RemoteSource.getRemoteTemperatures();

			Temperature temp = arrayTemps.get(0);
			TemperatureView tpv = new TemperatureView(temp);
			Parent root2 = tpv.getTempView();
			p.getItems().addAll(root2.lookup("#tempView"));

			primaryStage.setTitle("SolKlart Väder");
			scene.getStylesheets().add("/resource/CSS/MainMeny.css");

			primaryStage.setScene(scene);
			primaryStage.show();

			/*
			 * Average Temp and Median
			 */
			Text averageTempText = (Text) root.lookup("#averagetemp");
			Text medianText = (Text) root.lookup("#median");
			AverageTemp avgTemp = new AverageTemp(Converter.convertFahrenheitToCelsius(arrayTemps));
			MedianTemp medianTemp = new MedianTemp(Converter.convertFahrenheitToCelsius(arrayTemps));
			averageTempText.setText("Medeltemperatur: " + avgTemp.Calculate());
			medianText.setText("Mediantemperatur: " + medianTemp.getMedian() + "C");

			/*
			 * Populate the ComboBox and add event to change location and scene
			 */
			@SuppressWarnings("unchecked")
			ComboBox<String> comboBox = (ComboBox<String>) root.lookup("#comboBox");
			for (Temperature temperature : arrayTemps) {
				comboBox.getItems().add(temperature.getLocation());
			}
			EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					for (Temperature temperature : arrayTemps) {
						if (temperature.getLocation().contentEquals(comboBox.getValue())) {
							TemperatureView t = new TemperatureView(temperature);
							try {
								Parent root3 = t.getTempView();
								p.getItems().set(1, root3.lookup("#tempView"));

							} catch (IOException e1) {
								e1.printStackTrace();
							}
							break;
						}
					}
				}
			};
			comboBox.setOnAction(event);

			Button exit = (Button) root.lookup("#end");

			EventHandler<ActionEvent> eventExit = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					Platform.exit();
					System.exit(0);
				}
			};
			exit.setOnAction(eventExit);

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent e) {
					Platform.exit();
					System.exit(0);
				}
			});

			Button graf = (Button) root.lookup("#graf");
			EventHandler<ActionEvent> eventShowGraf = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					GUI g = new GUI();
					Stage stage;
					try {
						stage = g.start();
						stage.show();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			};
			graf.setOnAction(eventShowGraf);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
