package Temperature.Converter;

/**
 * @author Chjun-chi
 *
 */
public class Temperature {
	private String tempUnit;
	private double temp;

	/**
	 * @param tempUnit
	 * @param temp
	 */
	public Temperature(String tempUnit, double temp) {

		this.tempUnit = tempUnit;
		this.temp = temp;
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
