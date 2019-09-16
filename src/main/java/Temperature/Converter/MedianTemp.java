package Temperature.Converter;

import java.util.ArrayList;

public class MedianTemp {
	private ArrayList<Temperature> TempList;
	private String result;
	private double value;

	/**
	 * @param tempList
	 * @param link
	 * @param result
	 */

	public MedianTemp(ArrayList<Temperature> tempList) {

		TempList = tempList;
		sortList();
	}

	public String getMedian() {

		if (TempList.size() % 2 == 0) {
			value = TempList.get(TempList.size() / 2).getTemp() + TempList.get((TempList.size() / 2) + 1).getTemp();

			result = value + "";

		} else {

			result = TempList.get(TempList.size() / 2).getTemp() + "";
		}

		return result;
	}

	private void sortList() {

		TempList.sort((Temperature c1, Temperature c2) -> Double.compare(c1.getTemp(), c2.getTemp()));
	}
}
