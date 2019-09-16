package Temperature.Converter;

import java.util.ArrayList;

public class MedianTemp {
	private ArrayList<Temperature> TempList;
	private String result;
	private double value;	
	private int resultD;
	private int num_elements;

	/**
	 * @param tempList
	 * @param link
	 * @param result
	 */

	public MedianTemp(ArrayList<Temperature> tempList) {

		TempList = tempList;
		sortList();
		num_elements = TempList.size();

	}

	public String getMedian() {

		if (TempList.size() % 2 == 0) {
			value = (TempList.get(TempList.size() / 2).getTemp() + TempList.get((TempList.size() / 2) - 1).getTemp())
					/ 2;
			double roundedOneDigitX = Math.round(value * 10) / 10.0;
			result = roundedOneDigitX + "";

		} else {
			result = (Math.round(TempList.get(TempList.size() / 2).getTemp() * 10) / 10.0) + "";
		}

		return result;
	}

	public double getMedianD() {
		ArrayList<Temperature> sortedList = SortTempList.sortList(TempList);
		Double median = 0.0;
		resultD = num_elements % 2;
		
		if(resultD == 0) { // Om jämnt antal mätvärden

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
	
	private void sortList() {

		TempList.sort((Temperature c1, Temperature c2) -> Double.compare(c1.getTemp(), c2.getTemp()));
	}
}
