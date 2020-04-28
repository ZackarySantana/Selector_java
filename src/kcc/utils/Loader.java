package kcc.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kcc.Controller;
import kcc.KCC;
import kcc.KCC.Settings;

/**
 * This class is used to load FXML files to a stage
 *
 * @author Zackary Santana
 */
public class Loader {

	private static Method _INIT_METHOD;
	private static KCC _MAIN_PROGRAM;

	static {
		try {
			_INIT_METHOD = kcc.Controller.class.getDeclaredMethod("init", KCC.class, Stage.class);
			_INIT_METHOD.setAccessible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void setMainProgram(KCC program) {
		_MAIN_PROGRAM = program;
	}

	private final Stage _STAGE;
	private final Settings _SETTINGS;

	/**
	 * @param stage The stage to load a FXML file on to
	 */
	public Loader(Stage stage) {
		this._STAGE = stage;
		this._SETTINGS = _MAIN_PROGRAM.getSettings();
	}

	/**
	 * Loads the FXML file to the stage
	 *
	 * @param path the path of the FXML file
	 */
	public void loadFXML(String path) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + path + ".fxml"));
		try {
			this._STAGE.setScene(new Scene((Parent) loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (this._SETTINGS.getDefaultStyleSheet() != null) {
			loadStyleSheet(this._SETTINGS.getDefaultStyleSheet());
		}
	}

	/**
	 * Loads the FXML file to the stage
	 *
	 * @param path       the path of the FXML file
	 * @param controller the controller object
	 */
	public void loadFXML(String path, Controller controller) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + path + ".fxml"));
		loader.setController(controller);
		try {
			this._STAGE.setScene(new Scene((Parent) loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			_INIT_METHOD.invoke(controller, _MAIN_PROGRAM, this._STAGE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (this._SETTINGS.getDefaultStyleSheet() != null) {
			loadStyleSheet(this._SETTINGS.getDefaultStyleSheet());
		}
	}

	/**
	 * Loads the style sheet file (CSS ONLY) to the stage Must have loaded a scene
	 * (loaded scene if you loaded FXML already)
	 *
	 * @param path the path of the Style sheet file
	 */
	public void loadStyleSheet(String path) {
		this._STAGE.getScene().getStylesheets().add(getClass().getResource("/" + path + ".css").toExternalForm());
	}
}