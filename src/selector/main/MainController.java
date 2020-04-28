package selector.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import kcc.Controller;
import kcc.utils.FXMLInformation;

public class MainController extends Controller {

	@FXML
	public VBox buttons;

	@Override
	public void load() {
		this._kcc.getMainStageHandler().setIcon("selector/main/images/icon.png");
		makeProgram("To Do List", new FXMLInformation("selector/todolist/resources/main", "selector/todolist/resources/styles",
				"To Do List", new selector.todolist.MainController()));
		makeProgram("To Do Lfsdfsdfsdfdist", new FXMLInformation("selector/todolist/resources/main", "selector/todolist/resources/styles",
				"To Do List", new selector.todolist.MainController()));
		makeProgram("To Do fList", new FXMLInformation("selector/todolist/resources/main", "selector/todolist/resources/styles",
				"To Do List", new selector.todolist.MainController()));
		makeProgram("TList", new FXMLInformation("selector/todolist/resources/main", "selector/todolist/resources/styles",
				"To Do List", new selector.todolist.MainController()));
		makeProgram("To Dodddd List", new FXMLInformation("selector/todolist/resources/main", "selector/todolist/resources/styles",
				"To Do List", new selector.todolist.MainController()));
		
		
		makeGame("Test Game", new FXMLInformation("selector/testgame/resources/main", "selector/testgame/resources/styles",
				"Test Game ", null));
	}

	private void makeProgram(String text, FXMLInformation path) {
		Button button = new Button(text);

		button.setOnAction((event) -> {
			_kcc.getMainStageHandler().getStage().close();
			_kcc.startExternally(_kcc.getMainStageHandler().getStage(), path);
		});

		this.buttons.getChildren().add(this.buttons.getChildren().size() - 1, button);
	}

	private void makeGame(String text, FXMLInformation path) {
		Button button = new Button(text);

		button.setOnAction((event) -> {
			_kcc.getMainStageHandler().getStage().close();
			_kcc.startExternally(_kcc.getMainStageHandler().getStage(), path);
		});

		this.buttons.getChildren().add(this.buttons.getChildren().size(), button);
	}
}