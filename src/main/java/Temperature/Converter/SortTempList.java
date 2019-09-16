package Temperature.Converter;
import java.util.ArrayList;


public class SortTempList {
	
	public SortTempList() {	}

	// Sorterar mätvärden i stigande ordning
	public static ArrayList<Temperature> sortList(ArrayList<Temperature> unsortedList) {
			ArrayList<Temperature> sortedList = new ArrayList<>();
			
			// söker igenom $unsortedList $i antal gånger
			int iterationCount = unsortedList.size();
			for(int i = 0; i < iterationCount; i++) {
				double minTemp = 0.0;
				int minTempIndex = 0;
				// söker igenom $unsortedList efter minsta värde
				for(int j = 0; j < unsortedList.size(); j++) {
					if(j == 0) { // första iterationen?
						minTemp = unsortedList.get(0).getTemp();
						minTempIndex = 0;
					}
					else if(unsortedList.get(j).getTemp() < minTemp) {					
						minTemp = unsortedList.get(j).getTemp();
						minTempIndex = j;
					}				
				}				
				unsortedList.remove(minTempIndex); // värdet som sorterats ut raderas från lista
				
				// lägger till minsta värdet sist i $sortedList
				Temperature tempObj = new Temperature();
				tempObj.setTemp(minTemp);
				sortedList.add(tempObj);
			}
					
			return sortedList;
		}	
}
