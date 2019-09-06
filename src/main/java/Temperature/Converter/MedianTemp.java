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

		TempList = new ArrayList<Temperature>(tempList);

	}

	public String Calculate() {

		result = TempList.size() / 2;

		return TempList.get(Math.round(result)).getTemp() + TempList.get(Math.round(result)).getTempUnit();
	}

}
