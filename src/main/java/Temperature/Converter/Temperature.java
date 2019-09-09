package Temperature.Converter;

/**
 * @author Chjun-chi
 *
 */
public class Temperature implements TemperatureInterface {
	private String tempUnit, icon, time, location, summary, windspeed,apparentTemperature;
	@Override
	public String getApparentTemperature() {
		return apparentTemperature;
	}

	@Override
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

	@Override
	public String getIcon() {
		return icon;
	}

	@Override
	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String getTime() {
		return time;
	}

	@Override
	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String getSummary() {
		return summary;
	}

	@Override
	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		return "Temperature [tempUnit=" + tempUnit + ", icon=" + icon + ", time=" + time + ", location=" + location
				+ ", summary=" + summary + ", windspeed=" + windspeed + ", apparentTemperature=" + apparentTemperature
				+ ", temp=" + temp + "]";
	}

	@Override
	public String getWindspeed() {
		return windspeed;
	}

	@Override
	public void setWindspeed(String windspeed) {
		this.windspeed = windspeed;
	}

	/**
	 * @return String
	 */
	@Override
	public String getTempUnit() {
		return tempUnit;
	}

	/**
	 * @param tempUnit
	 */
	@Override
	public void setTempUnit(String tempUnit) {
		this.tempUnit = tempUnit;
	}

	/**
	 * @return double
	 */
	@Override
	public double getTemp() {
		return temp;
	}

	/**
	 * @param temp
	 */
	@Override
	public void setTemp(double temp) {
		this.temp = temp;
	}

}
