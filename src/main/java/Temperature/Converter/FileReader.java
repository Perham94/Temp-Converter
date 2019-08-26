package Temperature.Converter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import java.io.*;


public class FileReader {
	
	public static void main(String[] args) {
		
		//System.out.println(readFrom());
		JSONObject object1 = readFrom();
		System.out.println(object1.getJSONArray("temperature").getJSONObject(0));
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
