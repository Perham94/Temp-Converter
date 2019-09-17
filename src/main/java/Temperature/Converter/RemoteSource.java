package Temperature.Converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

/**
 * Get weather data from the Internet.
 * 
 * @author Chjun-chi
 *
 */
public class RemoteSource {

	/**
	 * Get remote temperatures from all over the world.
	 * 
	 * @return ArrayList<Temperature>
	 */
	public static ArrayList<Temperature> getRemoteTemperatures() {
		ArrayList<Temperature> array = new ArrayList<Temperature>();

		Double time = Double.parseDouble(String.valueOf(System.currentTimeMillis())) / 1000d;
		
		// New York Manhattan
		array.add(connectToAdress(
				"https://api.darksky.net/forecast/684f8208ee34774d4bf2a0cdcc360072/40.795943,-73.959821,"
						+ time.intValue() + "?units=us&exclude=[minutely,hourly,daily]",
				"F"));
		// Stockholm
		array.add(connectToAdress(
				"https://api.darksky.net/forecast/684f8208ee34774d4bf2a0cdcc360072/59.332848, 18.011651,"
						+ time.intValue() + "?units=si&exclude=[minutely,hourly,daily]",
				"C"));
		// Shanghai
		array.add(connectToAdress(
				"https://api.darksky.net/forecast/684f8208ee34774d4bf2a0cdcc360072/31.225731, 121.467777,"
						+ time.intValue() + "?units=si&exclude=[minutely,hourly,daily]",
				"C"));
		// Los angeles
		array.add(connectToAdress(
				"https://api.darksky.net/forecast/684f8208ee34774d4bf2a0cdcc360072/34.031152, -118.270027,"
						+ time.intValue() + "?units=us&exclude=[minutely,hourly,daily]",
				"F"));
		// Tokyo
		array.add(connectToAdress(
				"https://api.darksky.net/forecast/684f8208ee34774d4bf2a0cdcc360072/35.684164, 139.274418,"
						+ time.intValue() + "?units=si&exclude=[minutely,hourly,daily]",
				"C"));

		// Danmark
		array.add(connectToAdress(
				"https://api.darksky.net/forecast/684f8208ee34774d4bf2a0cdcc360072/57.039751, 9.916065,"
						+ time.intValue() + "?units=si&exclude=[minutely,hourly,daily]",
				"C"));
		return array;

	}

	/**
	 * Connets to API to get temperatures.
	 * 
	 * @param adress
	 * @param unit
	 * @return Temperature
	 */
	private static Temperature connectToAdress(String adress, String unit) {
		URL url;
		try {
			url = new URL(adress);// Creates the URL object
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();// Create the instance does not establish
																				// // connection with the network.
			con.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String input;
			StringBuilder sb = new StringBuilder();
			while ((input = br.readLine()) != null) {
				System.out.println(input); // Write out line directly from inputstream.
				sb.append(input); // Builds a string from the inputstream
			}
			br.close();
			// Parse the string to a jsonobject
			JSONObject js = new JSONObject(sb.toString());
			Temperature temp = new Temperature();
			temp.setLocation(js.getString("timezone"));
			JSONObject jsCurrently = js.getJSONObject("currently");
			temp.setTempUnit(unit);

			double roundedTwoDigitX = Math.round(jsCurrently.getDouble("temperature") * 10) / 10.0;
			temp.setTemp(roundedTwoDigitX);

			temp.setApparentTemperature(jsCurrently.get("apparentTemperature").toString());
			temp.setSummary(jsCurrently.get("summary").toString());
			temp.setTime(jsCurrently.get("time").toString());

			temp.setWindspeed(jsCurrently.get("windSpeed").toString() + "m/s");

			temp.setIcon(jsCurrently.get("icon").toString());
			return temp;

		} catch (MalformedURLException e) {// Catch errors
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.err.print("ERROR");
		return null;
	}

}
