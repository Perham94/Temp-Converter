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

		LocalDateTime dateTime = LocalDateTime.ofEpochSecond(Long.parseLong(temp.getTime()), 0, ZoneOffset.ofHours(2));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

		time.setText(dateTime.format(formatter));
		Text temperature = (Text) root.lookup("#temperature");
		temperature.setText("Temperatur: " + String.valueOf(temp.getTemp() + " " + temp.getTempUnit()));

		Text feelsLike = (Text) root.lookup("#feelslike");
		feelsLike.setText("Känns som: " + temp.getApparentTemperature() + " " + temp.getTempUnit());

		Text windSpeed = (Text) root.lookup("#windspeed");
		windSpeed.setText("Vindhastighet: " + temp.getWindspeed());

		Text summary = (Text) root.lookup("#summary");
		summary.setText("Väder: " + temp.getSummary());

		WebView icon = (WebView) root.lookup("#icon");

		URL url;
		switch (temp.getIcon()) {
		case "clear-day":
			url = getClass().getResource("/resource/climacons-master/SVG/Sun.svg");
			break;
		case "clear-night":
			url = getClass().getResource("/resource/climacons-master/SVG/Moon.svg");
			break;
		case "partly-cloudy-day":
			url = getClass().getResource("/resource/climacons-master/SVG/Cloud-Sun.svg");
			break;
		case "partly-cloudy-night":
			url = getClass().getResource("/resource/climacons-master/SVG/Cloud-Moon.svg");
			break;
		case "cloudy":
			url = getClass().getResource("/resource/climacons-master/SVG/Cloud.svg");
			break;
		case "rain":
			url = getClass().getResource("/resource/climacons-master/SVG/Cloud-Rain.svg");
			break;
		case "sleet":
			url = getClass().getResource("/resource/climacons-master/SVG/Cloud-Rain-Alt.svg");
			break;
		case "snow":
			url = getClass().getResource("/resource/climacons-master/SVG/Cloud-Snow.svg");
			break;
		case "wind":
			url = getClass().getResource("/resource/climacons-master/SVG/Wind.svg");
			break;
		case "fog":
			url = getClass().getResource("/resource/climacons-master/SVG/Cloud-Fog.svg");
			break;
		default:
			url = getClass().getResource("/resource/climacons-master/SVG/Compass.svg");
			break;
		}

		File file = new File(url.getPath());
	
		icon.getEngine().loadContent("<img src=\"" + file.toURI() + "\">");
		icon.getEngine().setUserStyleSheetLocation(getClass().getResource("/resource/CSS/WebView.css").toString());
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
