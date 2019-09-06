package Temperature.Converter;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Johan
 *
 */
public class Meny {

	private static final String ERROR_NO_DATA = "INGA MÄTVÄRDEN HITTADES!";
	private static ArrayList<Temperature> tempSeries;
	private static ArrayList<Temperature> tempSeriesCelsius;
	private static ArrayList<Temperature> tempSeriesFahrenheit;
	private static DecimalFormat numberFormat = new DecimalFormat("#.00"); // Antal decimaler

	/**
	 * Main application.
	 * 
	 * @param args
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {

		tempSeries = null;
		new ArrayList<>();
		tempSeriesCelsius = new ArrayList<>();
		tempSeriesFahrenheit = new ArrayList<>();

		Scanner sc = new Scanner(System.in);
		int menyval = 0;

		do {
			System.out.println("\n\n### MENY ###################################");
			System.out.println("1 = LÄS MÄTVÄRDEN FRÅN FIL");
			System.out.println("2 = LÄS MÄTVÄRDEN FRÅN Internet");
			System.out.println("3 = VISA MÄTVÄRDEN");
			System.out.println("4 = VISA MÄTVÄRDEN I CELSIUS");
			System.out.println("5 = VISA MÄTVÄRDEN I FAHRENHEIT");
			System.out.println("6 = BERÄKNA MEDELVÄRDE OCH VISA I CELSIUS");
			System.out.println("7 = BERÄKNA MEDELVÄRDE OCH VISA I FAHRENHEIT");
			System.out.println("8 = AVSLUTA");
			System.out.print("\nAnge menyval: ");
			menyval = sc.nextInt();
			sc.nextLine();
			System.out.println();
			switch (menyval) {

			case 1:
				tempSeries = FileReader.temperatureList();
				if (tempSeries.size() == 0) {
					System.err.println(ERROR_NO_DATA);
				} else {
					System.out.println("\n" + tempSeries.size() + " MÄTVÄRDEN HAR LÄSTS IN.");
				}
				break;
			case 2:
				tempSeries = RemoteSource.getRemoteTemperatures();
				if (tempSeries.size() == 0) {
					System.err.println(ERROR_NO_DATA);
				} else {
					System.out.println("\n" + tempSeries.size() + " MÄTVÄRDEN HAR LÄSTS IN.");
				}
				break;

			case 3:
				if (tempSeries != null && tempSeries.size() != 0) {
					System.out.println("\n### MÄTVÄRDEN ###");
					showSeries(tempSeries);
				} else {
					System.err.println(ERROR_NO_DATA);
				}
				break;

			case 4:
				if (tempSeries != null && tempSeries.size() != 0) {
					tempSeriesCelsius = Converter.convertFahrenheitToCelsius(tempSeries);
					showSeries(tempSeriesCelsius);

				} else {
					System.err.println(ERROR_NO_DATA);
				}
				break;

			case 5:
				if (tempSeries != null && tempSeries.size() != 0) {
					tempSeriesFahrenheit = Converter.convertCelsiusToFahrenheit(tempSeries);
					showSeries(tempSeriesFahrenheit);
				} else {
					System.err.println(ERROR_NO_DATA);
				}
				break;

			case 6:
				if (tempSeries != null && tempSeries.size() != 0) {
					AverageTemp averageTempObj = new AverageTemp(Converter.convertFahrenheitToCelsius(tempSeries));
					System.out.println("\nMedeltemperatur i Celsius:");
					System.out.println(averageTempObj.Calculate());
				} else {
					System.err.println(ERROR_NO_DATA);
				}
				break;

			case 7:
				if (tempSeries != null && tempSeries.size() != 0) {
					AverageTemp averageTempObj = new AverageTemp(Converter.convertCelsiusToFahrenheit(tempSeries));
					System.out.println("\nMedeltemperatur i Fahrenheit:");
					System.out.println(averageTempObj.Calculate());
				} else {
					System.err.println(ERROR_NO_DATA);
				}
				break;

			case 8:
				System.exit(0);
			}
		} while (menyval != 8);

		sc.close();

	}

	/**
	 * Prints the temperatures in the list to the console.
	 * 
	 * @param series
	 */
	private static void showSeries(ArrayList<Temperature> series) {
		for (Temperature obj : series) {
			String temperature = (numberFormat.format(obj.getTemp()));
			System.out.println(temperature + " °" + obj.getTempUnit().toUpperCase());
		}
	}

}
