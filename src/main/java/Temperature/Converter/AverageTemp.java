package Temperature.Converter;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
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
	private URL link;
	private String result = "";
	private String loop = "";

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
	public String Calculate() throws IOException {
		getValues();
		link = new URL("https://api.mathjs.org/v4/?expr=" + path);

		try {
			Scanner scanner = new Scanner(link.openStream(), StandardCharsets.UTF_8.toString());

			scanner.useDelimiter("\\A");
			loop = scanner.hasNext() ? scanner.next() : "";
			scanner.close();
			result = Math.round(Float.parseFloat(loop)) + TempList.get(0).getTempUnit() + "";

		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			System.out.println(e.toString());
		}

		return result;

	}

}
