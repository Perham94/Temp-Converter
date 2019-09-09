package Temperature.Converter;

public interface TemperatureInterface {

	String getApparentTemperature();

	void setApparentTemperature(String apparentTemperature);

	String getIcon();

	void setIcon(String icon);

	String getTime();

	void setTime(String time);

	String getLocation();

	void setLocation(String location);

	String getSummary();

	void setSummary(String summary);

	String toString();

	String getWindspeed();

	void setWindspeed(String windspeed);

	/**
	 * @return String
	 */
	String getTempUnit();

	/**
	 * @param tempUnit
	 */
	void setTempUnit(String tempUnit);

	/**
	 * @return double
	 */
	double getTemp();

	/**
	 * @param temp
	 */
	void setTemp(double temp);

}