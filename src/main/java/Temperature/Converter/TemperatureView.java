package Temperature.Converter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

public class TemperatureView {

	Temperature temp;

	public TemperatureView(Temperature temp) {

		this.temp = temp;

	}


	public Parent getTempView() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("TemperatureView.fxml"));
		Text time = (Text) root.lookup("#time");

//		Date date = new Date(Long.parseLong(temp.getTime()) * 1000);

		LocalDateTime dateTime = LocalDateTime.ofEpochSecond(Long.parseLong(temp.getTime()), 0,  ZoneOffset.UTC);
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("uuuu-MM-dd HH:mm:ss");
		
	
		time.setText(dateTime.format(formatter));
		Text temperature = (Text) root.lookup("#temperature");
		temperature.setText(String.valueOf(temp.getTemp() + " " + temp.getTempUnit()));

		Text feelsLike = (Text) root.lookup("#feelslike");
		feelsLike.setText(temp.getApparentTemperature() + " " + temp.getTempUnit());

		Text windSpeed = (Text) root.lookup("#windspeed");
		windSpeed.setText(temp.getWindspeed());

		Text summary = (Text) root.lookup("#summary");
		summary.setText(temp.getSummary());

		WebView icon = (WebView) root.lookup("#icon");
		URL url = getClass().getResource("/resource/climacons-master/SVG/Cloud.svg");
		File file = new File(url.getPath());

		icon.getEngine().loadContent("<img src=\"" + file.toURI() + "\">");
		icon.getEngine().getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
			if (newState == Worker.State.SUCCEEDED) {
				int width = (Integer) icon.getEngine().executeScript("document.body.scrollWidth");
				int height = (Integer) icon.getEngine().executeScript("document.body.scrollHeight");
				icon.setPrefSize(width, height);
			}
		});
		return root;
	}

}
