package Temperature.Converter;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

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

		for (int i = 0; i <= TempList.size(); i++) {

			path = TempList.get(i).getTemp() + "%2B";

		}

		path = "(" + path + ")" + "%2F" + "(" + TempList.size() + ")";

	}

	public void Calculate() throws IOException {
		getValues();
		link = "https://api.mathjs.org/v4/?expr=" + path;
		try {
			url = new URL(link);
			https = (HttpsURLConnection) url.openConnection();
			https.setRequestMethod("POST");
			https.getResponseMessage();
			https.getOutputStream();
			System.out.println(https.getOutputStream());

		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
