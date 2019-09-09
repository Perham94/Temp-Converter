package Temperature.Converter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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

		Date date = new Date(Long.parseLong(temp.getTime()) * 1000);

	;
		time.setText(date.toGMTString());
		Text temperature = (Text) root.lookup("#temperature");
		temperature.setText(String.valueOf(temp.getTemp()));
		
		Text feelsLike = (Text) root.lookup("#feelslike");
		feelsLike.setText(temp.getApparentTemperature());
		
		Text windSpeed = (Text) root.lookup("#windspeed");
		windSpeed.setText(temp.getWindspeed());
		
		Text summary = (Text) root.lookup("#summary");
		summary.setText(temp.getSummary());
		
		WebView icon = (WebView) root.lookup("#icon");
		URL url = getClass().getResource("/resource/climacons-master/SVG/Cloud.svg");
		File file = new File(url.getPath());

		icon.getEngine().loadContent("<img src=\""+file.toURI()+"\" width='100'>");
		return root;
	}

}
