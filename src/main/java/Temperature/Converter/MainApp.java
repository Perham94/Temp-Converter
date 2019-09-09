package Temperature.Converter;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
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

			ArrayList<Temperature> arrayTemps = new ArrayList<Temperature>();
			arrayTemps = RemoteSource.getRemoteTemperatures();

			Temperature temp = arrayTemps.get(1);
			TemperatureView tpv = new TemperatureView(temp);
			Parent root2 = tpv.getTempView();

			p.getItems().addAll(root2.lookup("#tempView"));

			primaryStage.setTitle("SolKlart Väder");
			scene.getStylesheets().add("/resource/CSS/MainMeny.css");
			primaryStage.setScene(scene);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				Platform.exit();
				System.exit(0);
			}
		});
	}

}
