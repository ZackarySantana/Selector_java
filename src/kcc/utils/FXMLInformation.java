package kcc.utils;

import kcc.Controller;

public class FXMLInformation {
	
	private final String _PATH_TO_FXML, _PATH_TO_STYLES, _TITLE;
	private final Controller _CONTROLLER;
	
	public FXMLInformation(String pathToFXML, String pathToStyles, String title, Controller controller) {
		this._PATH_TO_FXML = pathToFXML;
		this._PATH_TO_STYLES = pathToStyles;
		this._TITLE = title;
		this._CONTROLLER = controller;
	}
	
	public String getFXMLPath() {
		return this._PATH_TO_FXML;
	}
	
	public String getStylesPath() {
		return this._PATH_TO_STYLES;
	}
	
	public String getTitle() {
		return this._TITLE;
	}
	
	public Controller getController() {
		return this._CONTROLLER;
	}
}