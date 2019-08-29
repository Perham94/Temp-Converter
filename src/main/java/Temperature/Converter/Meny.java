package Temperature.Converter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */

public class Meny {
	public static void main(String[] args) throws IOException {

		ArrayList<Temperature> temp = new ArrayList<Temperature>();
		temp.add(new Temperature("c", 22d));
		temp.add(new Temperature("c", 21d));
		temp.add(new Temperature("c", 42d));

		AverageTemp test = new AverageTemp(temp);

		test.Calculate();

	}
}
