package Temperature.Converter;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.junit.jupiter.api.Test;

public class AverageTemp {

	private URL url;

	private HttpsURLConnection https;
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
	 * @return the url
	 */
	public URL getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(URL url) {
		this.url = url;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	public void getValues() {

		for (int i = 0; i < TempList.size(); i++) {

			if (i == TempList.size() - 1) {
				path += "" + TempList.get(i).getTemp() + "";
				break;
			}
			path += TempList.get(i).getTemp() + "%2B";

		}

		path = "(" + path + ")" + "%2F" + "(" + TempList.size() + ")";

	}

	@Test
	public void Calculate() throws IOException {
		getValues();
		link = "https://api.mathjs.org/v4/?expr=" + path;
		String result = "";
		try {

			// https = (HttpsURLConnection) url.openConnection();

			Scanner scanner = new Scanner(new URL(link).openStream(), StandardCharsets.UTF_8.toString());

			scanner.useDelimiter("\\A");
			result = scanner.hasNext() ? scanner.next() : "";

			/*
			 * while (scanner.hasNextLine()) { result += scanner.nextLine();
			 * 
			 * }
			 * 
			 * scanner.close();
			 * 
			 */
			scanner.close();
			System.out.println(result);

		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
