package Temperature.Converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONObject;

/**
 * @author Armen
 *
 */
public class FileReader {

	/**
	 * Get the temperature from file and converts it into a ArrayList with
	 * temperature objects.
	 * 
	 * @return ArrayList<Temperature>
	 */
	public static ArrayList<Temperature> temperatureList() {

		JSONObject object1 = readFrom();

		ArrayList<Temperature> temperatureList = new ArrayList<Temperature>();

		for (int i = 0; i < object1.getJSONArray("temperature").length(); i++) {

			JSONObject object2 = object1.getJSONArray("temperature").getJSONObject(i);

			Temperature myObj = new Temperature((String) object2.get("unit"),
					Double.valueOf(object2.get("temperaturevalue").toString()));
			temperatureList.add(myObj);
		}

		return temperatureList;
	}

	/**
	 * Reads data from file.
	 * 
	 * @return JsonObject
	 */
	static JSONObject readFrom() {
		String file = "src\\main\\java\\TemperatureData";
		String tmpStr;
		String svar = "";

		try {
			BufferedReader br = Files.newBufferedReader(Paths.get(file));
			while ((tmpStr = br.readLine()) != null) {
				svar += tmpStr;
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject jsobj = new JSONObject(svar);
		return jsobj;
	}

}
