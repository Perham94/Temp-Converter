package Temperature.Converter;

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

	public String getTempUnit() {
		return tempUnit;
	}

	public void setTempUnit(String tempUnit) {
		this.tempUnit = tempUnit;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

}
