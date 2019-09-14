package Temperature.Converter;

import java.util.ArrayList;

public class MedianTemp {
	private ArrayList<Temperature> TempList;
	private int result;

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

		result = TempList.size() / 2;

		return TempList.get(result).getTemp() + "";
	}

	private void sortList() {

		TempList.sort((Temperature c1, Temperature c2) -> Double.compare(c1.getTemp(), c2.getTemp()));
	}
}
