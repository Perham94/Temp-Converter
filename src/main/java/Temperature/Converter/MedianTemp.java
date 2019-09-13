package Temperature.Converter;

import java.util.ArrayList;

public class MedianTemp {
	private ArrayList<Temperature> TempList;
	private int result;
	private int num_elements;

	/**
	 * @param tempList
	 * @param link
	 * @param result
	 */

	public MedianTemp(ArrayList<Temperature> tempList) {

		TempList = new ArrayList<Temperature>(tempList);
		num_elements = TempList.size();

	}

	public String Calculate() {

		result = TempList.size() / 2;

		return TempList.get(Math.round(result)).getTemp() + TempList.get(Math.round(result)).getTempUnit();
	}
	
	public Double getMedian() {
		Double median = 0.0;
		result = num_elements % 2;
		System.out.println("result: " + result);
		//return median;
		return median;
	}

}
