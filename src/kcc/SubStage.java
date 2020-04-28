package kcc;

import javafx.stage.Modality;
import javafx.stage.Stage;

import kcc.utils.FXMLInformation;

public class SubStage {
	
	private static Stage _MAIN_STAGE;

	@SuppressWarnings("unused")
	private static void setMainStage(Stage mainStage) {
		_MAIN_STAGE = mainStage;
	}

	private final StageHandler _STAGE_HANDLER;
	private final Stage _OWNER_STAGE;

	public SubStage(FXMLInformation information) {
		this._OWNER_STAGE = _MAIN_STAGE;
		this._STAGE_HANDLER = new StageHandler(new Stage());
		load(information);
	}

	public SubStage(Stage ownerStage, FXMLInformation information) {
		this._OWNER_STAGE = ownerStage;
		this._STAGE_HANDLER = new StageHandler(new Stage());
		load(information);
	}
	
	private void load(FXMLInformation information) {
		if (information != null) {
			if (information.getFXMLPath() != null && !information.getFXMLPath().equals("")) {
				if (information.getController() != null) {
					this._STAGE_HANDLER.getLoader().loadFXML(information.getFXMLPath(), information.getController());
				} else {
					this._STAGE_HANDLER.getLoader().loadFXML(information.getFXMLPath());
				}
			}
			if (information.getStylesPath() != null && !information.getStylesPath().equals("")) {
				this._STAGE_HANDLER.getLoader().loadStyleSheet(information.getStylesPath());
			}
			this._STAGE_HANDLER.getStage().setTitle(information.getTitle());
		}

		this._STAGE_HANDLER.getStage().initModality(Modality.WINDOW_MODAL);
		this._STAGE_HANDLER.getStage().initOwner(this._OWNER_STAGE);
		this._STAGE_HANDLER.getStage().setResizable(false);
		this._STAGE_HANDLER.getStage().show();
		this._STAGE_HANDLER.getStage().setX(this._OWNER_STAGE.getX() + this._OWNER_STAGE.getWidth() / 2
				- this._STAGE_HANDLER.getStage().getWidth() / 2);
		this._STAGE_HANDLER.getStage().setY(this._OWNER_STAGE.getY() + this._OWNER_STAGE.getHeight() / 2
				- this._STAGE_HANDLER.getStage().getHeight() / 2);
	}

	public StageHandler getStageHandler() {
		return this._STAGE_HANDLER;
	}

	public Stage getOwnerStage() {
		return this._OWNER_STAGE;
	}
}