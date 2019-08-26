package Temperature.Converter;

import java.io.IOException;
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

	private static String soapEndpointUrl = "https://www.w3schools.com/xml/tempconvert.asmx";

	public static void main(String args[]) throws IOException {
		ArrayList<Temperature> temp = new ArrayList<Temperature>();
		temp.add(new Temperature("f", 22d));
		temp.add(new Temperature("f", 21d));
		temp.add(new Temperature("f", 42d));

		ArrayList<Temperature> temp2 = convertFahrenheitToCelsius(temp);

		for (Temperature temperature : temp2) {
			System.out.println(temperature.getTemp() + temperature.getTempUnit());
		}
	}

	public static ArrayList<Temperature> convertCelsiusToFahrenheit(ArrayList<Temperature> tpList) {
		String soapAction = "https://www.w3schools.com/xml/CelsiusToFahrenheit";
		ArrayList<Temperature> fahrenheitList = new ArrayList<Temperature>();
		for (Temperature temperature : tpList) {
			if (temperature.getTempUnit().contentEquals("c")) {
				Double d = callSoapWebService(soapEndpointUrl, soapAction, temperature);
				fahrenheitList.add(new Temperature("f", d));
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
			if (temperature.getTempUnit().contentEquals("f")) {
				Double d = callSoapWebService(soapEndpointUrl, soapAction, temperature);
				celsiusList.add(new Temperature("c", d));
			} else {
				celsiusList.add(temperature);
			}
		}
		return celsiusList;
	}

	private static void createSoapEnvelope(SOAPMessage soapMessage, Temperature temperature) throws SOAPException {
		SOAPPart soapPart = soapMessage.getSOAPPart();

		String myNamespace = "temperature";
		String myNamespaceURI = "https://www.w3schools.com/xml/";

		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
		/*
		 * Constructed SOAP Request Message: <SOAP-ENV:Envelope
		 * xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
		 * xmlns:myNamespace="https://www.w3schools.com/xml/"> <SOAP-ENV:Header/>
		 * <SOAP-ENV:Body> <myNamespace:CelsiusToFahrenheit>
		 * <myNamespace:Celsius>100</myNamespace:Celsius>
		 * </myNamespace:CelsiusToFahrenheit> </SOAP-ENV:Body> </SOAP-ENV:Envelope>
		 */

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem;
		SOAPElement soapBodyElem1;
		if (temperature.getTempUnit().contentEquals("c")) {
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
//			System.out.println("Response SOAP Message:");
		//	soapResponse.writeTo(System.out);
//			System.out.println(soapResponse.getSOAPBody().getFirstChild().getTextContent());
			// Get the temperature and cast it to double
			convertedTemp = Double.valueOf(soapResponse.getSOAPBody().getFirstChild().getTextContent());
			
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

		createSoapEnvelope(soapMessage, temperature);
		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", soapAction);
		soapMessage.saveChanges();

		return soapMessage;
	}

}
