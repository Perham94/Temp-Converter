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
	
	public double getMedian() {
		ArrayList<Temperature> sortedList = SortTempList.sortList(TempList);
		Double median = 0.0;
		result = num_elements % 2;
		
		if(result == 0) { // Om jämnt antal mätvärden

			int indexMedianLow = (num_elements/2) - 1;
			int indexMedianHigh = (num_elements/2);
			
			double medianLow = sortedList.get(indexMedianLow).getTemp();
			double medianHigh = sortedList.get(indexMedianHigh).getTemp();
			
			median = (medianHigh + medianLow) / 2;
		}
		else { // Om udda antal mätvärden
			
			int indexMedian = (int) Math.floor(num_elements/2);
			median = sortedList.get(indexMedian).getTemp();
		}
		
		return median;
	}
}
