package kcc;

import java.lang.reflect.Method;

import javafx.application.Application;
import javafx.stage.Stage;

import kcc.utils.FXMLInformation;
import kcc.utils.Loader;

public abstract class KCC extends Application {

	private StageHandler _mainStageHandler;
	private Settings _settings;

	/**
	 * Used when the javafx application is already started
	 */
	public void startExternally(Stage overide, FXMLInformation information) {
		double x = this.getMainStageHandler().getStage().getX() + this.getMainStageHandler().getStage().getWidth() / 2;
		double y = this.getMainStageHandler().getStage().getY();
		this.getMainStageHandler().getStage().close();
		this._settings = new Settings();
		try {
			Method setMainProgram = Loader.class.getDeclaredMethod("setMainProgram", KCC.class);
			setMainProgram.setAccessible(true);
			setMainProgram.invoke(null, this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (information != null) {
			if (information.getStylesPath() != null && !information.getStylesPath().equals("")) {
				this._settings.setDefaultStyleSheet(information.getStylesPath());
			}
			this._mainStageHandler = new StageHandler(overide);
			this._mainStageHandler.setController(information.getController());
			this._mainStageHandler.getLoader().loadFXML(information.getFXMLPath(), information.getController());
		} else {
			this._mainStageHandler = new StageHandler(overide);
		}
		try {
			Method setMainStage = SubStage.class.getDeclaredMethod("setMainStage", Stage.class);
			setMainStage.setAccessible(true);
			setMainStage.invoke(null, this._mainStageHandler.getStage());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		this._mainStageHandler.getStage().setTitle(information.getTitle());
		overide.setResizable(false);
		overide.show();
		overide.setX(x - overide.getWidth() / 2);
		overide.setY(y);
		this._mainStageHandler.getStage().setOnCloseRequest(event -> {
			System.out.println("Stage is closing");
			_mainStageHandler.getController().unload();
		});
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		this._settings = new Settings();
		try {
			Method setMainProgram = Loader.class.getDeclaredMethod("setMainProgram", KCC.class);
			setMainProgram.setAccessible(true);
			setMainProgram.invoke(null, this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		FXMLInformation information = load();
		if (information != null) {
			if (information.getStylesPath() != null && !information.getStylesPath().equals("")) {
				this._settings.setDefaultStyleSheet(information.getStylesPath());
			}
			this._mainStageHandler = new StageHandler(mainStage);
			this._mainStageHandler.setController(information.getController());
			this._mainStageHandler.getLoader().loadFXML(information.getFXMLPath(), information.getController());
		} else {
			this._mainStageHandler = new StageHandler(mainStage);
		}
		try {
			Method setMainStage = SubStage.class.getDeclaredMethod("setMainStage", Stage.class);
			setMainStage.setAccessible(true);
			setMainStage.invoke(null, this._mainStageHandler.getStage());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		this._mainStageHandler.getStage().setTitle(information.getTitle());
		mainStage.setResizable(false);
		mainStage.centerOnScreen();
		mainStage.show();
		this._mainStageHandler.getStage().setOnCloseRequest(event -> {
			System.out.println("Stage is closing");
			_mainStageHandler.getController().unload();
		});
	}

	/**
	 * @return The main stage handler of the program
	 */
	public StageHandler getMainStageHandler() {
		return this._mainStageHandler;
	}

	/**
	 * @return Default settings of the program
	 */
	public Settings getSettings() {
		return this._settings;
	}

	/**
	 * This is ran before the stage is set with values
	 * 
	 * @return Returns the path to the FXML file, relative to file
	 */
	public abstract FXMLInformation load();

	/**
	 * This is ran after the stage is set with values
	 */
	public abstract void start();

	public class Settings {

		private String _defaultStyleSheet;

		/**
		 * @param path The path to default style sheet
		 */
		public void setDefaultStyleSheet(String path) {
			this._defaultStyleSheet = path;
		}

		/**
		 * @return The default stylesheet
		 */
		public String getDefaultStyleSheet() {
			return this._defaultStyleSheet;
		}
	}
}