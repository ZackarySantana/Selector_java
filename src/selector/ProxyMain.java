package selector;

import java.io.IOException;

public class ProxyMain {

	public static void main(String[] args) {
		try {
			Runtime.getRuntime().exec(
					"java --module-path \"C:\\Program Files\\Java\\javafx-sdk-13.0.1\\lib\" --add-modules javafx.controls,javafx.fxml -cp KCC.jar selector.Main");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}