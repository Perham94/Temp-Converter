package Temperature.Converter;

import java.util.ArrayList;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

public class Converter {
	//Webadress to API
	private static String soapEndpointUrl = "https://www.w3schools.com/xml/tempconvert.asmx";

	public static ArrayList<Temperature> convertCelsiusToFahrenheit(ArrayList<Temperature> tpList) {
		String soapAction = "https://www.w3schools.com/xml/CelsiusToFahrenheit";
		ArrayList<Temperature> fahrenheitList = new ArrayList<Temperature>();
		for (Temperature temperature : tpList) {
			//Checks if the temperature is in celsius else add it to the list without sending it to the converter.
			if (temperature.getTempUnit().contentEquals("C")) {
				Double d = callSoapWebService(soapEndpointUrl, soapAction, temperature);
				fahrenheitList.add(new Temperature("F", d));
			} else {
				fahrenheitList.add(temperature);
			}

		}
		return fahrenheitList;
	}

	public static ArrayList<Temperature> convertFahrenheitToCelsius(ArrayList<Temperature> tpList) {
		String soapAction = "https://www.w3schools.com/xml/FahrenheitToCelsius";
		ArrayList<Temperature> celsiusList = new ArrayList<Temperature>();
		for (Temperature temperature : tpList) {
			if (temperature.getTempUnit().contentEquals("F")) {
				Double d = callSoapWebService(soapEndpointUrl, soapAction, temperature);
				celsiusList.add(new Temperature("C", d));
			} else {
				celsiusList.add(temperature);
			}
		}
		return celsiusList;
	}

	private static void createSoapMessage(SOAPMessage soapMessage, Temperature temperature) throws SOAPException {
		SOAPPart soapPart = soapMessage.getSOAPPart();
		String myNamespace = "temperature";
		String myNamespaceURI = "https://www.w3schools.com/xml/";
		//Create the XML soapmessage by building it upp element by element.
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem;
		SOAPElement soapBodyElem1;
		if (temperature.getTempUnit().contentEquals("C")) {
			soapBodyElem = soapBody.addChildElement("CelsiusToFahrenheit", myNamespace);
			soapBodyElem1 = soapBodyElem.addChildElement("Celsius", myNamespace);

		} else {
			soapBodyElem = soapBody.addChildElement("FahrenheitToCelsius", myNamespace);
			soapBodyElem1 = soapBodyElem.addChildElement("Fahrenheit", myNamespace);

		}
		soapBodyElem1.addTextNode(String.valueOf(temperature.getTemp()));
	}

	private static Double callSoapWebService(String soapEndpointUrl, String soapAction, Temperature temperature) {
		Double convertedTemp = 0d;
		try {
			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			// Send SOAP Message to SOAP Server
			SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, temperature), soapEndpointUrl);
			// Print the SOAP Response
			// System.out.println("Response SOAP Message:");
		    // soapResponse.writeTo(System.out);

			// Get the temperature from the response and cast it to double
			convertedTemp = Double.valueOf(soapResponse.getSOAPBody().getTextContent());
			
		} catch (Exception e) {
			System.err.println(
					"\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
			e.printStackTrace();
		}
		return convertedTemp;
	}

	private static SOAPMessage createSOAPRequest(String soapAction, Temperature temperature) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		createSoapMessage(soapMessage, temperature);
		
		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", soapAction);
		soapMessage.saveChanges();

		return soapMessage;
	}

}
