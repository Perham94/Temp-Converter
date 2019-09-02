package Temperature.Converter;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Perham
 *
 */
public class AverageTemp {

	private ArrayList<Temperature> TempList;
	private String path = "";
	private String link;

	/**
	 * @param constructor
	 * @throws IOException
	 */
	public AverageTemp(ArrayList<Temperature> tempList) {
		TempList = new ArrayList<Temperature>(tempList);
	}

	/**
	 * Creates the String that is going to be sent to the Math API to be calculated.
	 */
	private void getValues() {
		for (int i = 0; i < TempList.size(); i++) {
			if (i == TempList.size() - 1) {
				path += "" + TempList.get(i).getTemp() + "";
				break;
			}
			path += TempList.get(i).getTemp() + "%2B";
		}
		path = "(" + path + ")" + "%2F" + "(" + TempList.size() + ")";
	}

	/**
	 * Calculates the average temperature of the arraylist by sending it to the math
	 * rest api.
	 * 
	 * @throws IOException
	 */
	public void Calculate() throws IOException {
		getValues();
		link = "https://api.mathjs.org/v4/?expr=" + path;
		String result = "";
		try {
			Scanner scanner = new Scanner(new URL(link).openStream(), StandardCharsets.UTF_8.toString());
			scanner.useDelimiter("\\A");
			result = scanner.hasNext() ? scanner.next() : "";
			scanner.close();
			System.out.println(Math.round(Float.parseFloat(result)) + TempList.get(0).getTempUnit());
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

	}

}
