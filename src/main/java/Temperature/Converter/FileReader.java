package Temperature.Converter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import org.json.JSONObject;

import java.io.*;


public class FileReader {
	
	public static void main(String[] args) {
		
		//System.out.println(readFrom());
		JSONObject object1 = readFrom();
		
		//System.out.println(object1.getJSONArray("temperature"));
		
		ArrayList<Temperature> temperatureList = new ArrayList<Temperature>();
		
		for(int i = 0; i < 4; i++) {
			
			JSONObject object2 = object1.getJSONArray("temperature").getJSONObject(i);
			//temperatureList.add(object2.get("temperaturevalue").toString() + " " + object2.get("unit").toString());
			
			
		
			Temperature myObj = new Temperature((String) object2.get("unit"),Double.valueOf(object2.get("temperaturevalue").toString()));
			temperatureList.add(myObj);
		}
		
		for (Temperature temperature : temperatureList) {
			System.out.println(temperature.getTemp() + temperature.getTempUnit());
		}
		

		}
	
	
	static JSONObject readFrom() {
		String file = "src\\main\\java\\TemperatureData";
		String tmpStr;
		String svar = "";
		
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get(file));
			while ((tmpStr = br.readLine()) != null) {
				svar += tmpStr;
			}
			br.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		JSONObject jsobj = new JSONObject(svar);
		return jsobj;
	}

}
