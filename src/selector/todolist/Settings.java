package selector.todolist;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import kcc.Controller;

public class Settings extends Controller {

	private final MainController _CONTROLLER;

	@FXML
	private Label directory;

	public Settings(MainController controller) {
		this._CONTROLLER = controller;
	}

	@Override
	public void load() {
		this.directory.setText(this._CONTROLLER.getSaveFile().getPath());
	}

	@FXML
	protected void selectDirectory(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setInitialDirectory(this._CONTROLLER.getSaveFile().getParentFile());
		directoryChooser.setTitle("Choose Save Directory");
		File selectedDirectory = directoryChooser.showDialog(this._stage);
		if (selectedDirectory != null) {
			if (!selectedDirectory.isFile()) {
				this._CONTROLLER.setSaveFile(selectedDirectory);
				this.directory.setText(selectedDirectory.getPath());
			}
		}
	}

	@FXML
	protected void closebtn(ActionEvent event) {
		this._stage.close();
	}
}