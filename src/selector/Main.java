package selector;

import kcc.KCC;
import kcc.utils.FXMLInformation;
import selector.main.MainController;

public class Main extends KCC {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start() {
	}

	@Override
	public FXMLInformation load() {
		return new FXMLInformation("selector/main/resources/main", "selector/main/resources/styles", "Selector",
				new MainController());
	}
}