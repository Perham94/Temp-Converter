package Temperature.Converter;

/**
 * @author Chjun-chi
 *
 */
public class Temperature {
	private String tempUnit, icon, time, location, summary, windspeed, apparentTemperature;
	public String getApparentTemperature() {
		return apparentTemperature;
	}

	public void setApparentTemperature(String apparentTemperature) {
		this.apparentTemperature = apparentTemperature;
	}

	private double temp;

	public Temperature() {
	}

	/**
	 * @param tempUnit
	 * @param temp
	 */
	public Temperature(String tempUnit, double temp) {

		this.tempUnit = tempUnit;
		this.temp = temp;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		return "Temperature [tempUnit=" + tempUnit + ", icon=" + icon + ", time=" + time + ", location=" + location
				+ ", summary=" + summary + ", windspeed=" + windspeed + ", apparentTemperature=" + apparentTemperature
				+ ", temp=" + temp + "]";
	}

	public String getWindspeed() {
		return windspeed;
	}

	public void setWindspeed(String windspeed) {
		this.windspeed = windspeed;
	}

	/**
	 * @return String
	 */
	public String getTempUnit() {
		return tempUnit;
	}

	/**
	 * @param tempUnit
	 */
	public void setTempUnit(String tempUnit) {
		this.tempUnit = tempUnit;
	}

	/**
	 * @return double
	 */
	public double getTemp() {
		return temp;
	}

	/**
	 * @param temp
	 */
	public void setTemp(double temp) {
		this.temp = temp;
	}

}
