package selector.todolist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import kcc.Controller;

public class AddItem extends Controller {

	private final MainController _CONTROLLER;

	@FXML
	private TextField content, content_header, label;

	public AddItem(MainController controller) {
		this._CONTROLLER = controller;
	}

	@FXML
	protected void additembtn(ActionEvent event) {
		this._CONTROLLER.addSideBarItem(label.getText(), content_header.getText(), content.getText());
		this._CONTROLLER.selectNewest();
		this._stage.close();
	}

	@FXML
	protected void closebtn(ActionEvent event) {
		this._CONTROLLER.selectNewest();
		this._stage.close();
	}

	@Override
	public void load() {
	}
}