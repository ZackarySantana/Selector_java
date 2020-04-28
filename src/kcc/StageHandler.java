package kcc;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import kcc.utils.Loader;

public class StageHandler {

	private final Stage _STAGE;
	private final Loader _LOADER;

	private Controller _controller;

	public StageHandler(Stage stage) {
		this._STAGE = stage;
		this._LOADER = new Loader(stage);
	}

	public Stage getStage() {
		return this._STAGE;
	}

	public Loader getLoader() {
		return this._LOADER;
	}

	public Controller getController() {
		return this._controller;
	}

	public void setController(Controller controller) {
		this._controller = controller;
	}

	public void setIcon(String path) {
		this._STAGE.getIcons().clear();
		this._STAGE.getIcons().add(new Image("/" + path));
	}

	public void setIcons(String... paths) {
		this._STAGE.getIcons().clear();
		for (String path : paths) {
			this._STAGE.getIcons().add(new Image("/" + path));
		}
	}
}