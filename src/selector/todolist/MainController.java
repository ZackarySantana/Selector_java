package selector.todolist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import kcc.Controller;
import kcc.SubStage;
import kcc.utils.Container;
import kcc.utils.FXMLInformation;
import kcc.utils.Misc;

public class MainController extends Controller {

	@FXML
	private VBox sidebaritems;

	@FXML
	private Label content_header, content;

	private final EventHandler<MouseEvent> _SIDERBAR_ITEM_CLICK;
	private List<Container<String, String>> _infos;
	private File _saveFile;
	private int _selected;

	{
		this._SIDERBAR_ITEM_CLICK = new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent event) {
				sidebar_item(event);
			}
		};
	}

	@Override
	public void load() {
		this.setSaveFile(Misc.getJarContainingFolder());
		
		this._kcc.getMainStageHandler().setIcon("selector/todolist/images/icon.png");
		this._infos = new ArrayList<>();
		this._infos.add(new Container<>("Sidebar Item1 Header", "Sidebar Item1 Content"));
		this.sidebaritems.getChildren().get(0).setStyle(
				"-fx-background-color: #f03d53;-fx-border-color: transparent transparent #5C3E84 transparent;");
		addSideBarItem("test1", "headersedr", "co9ntent");
	}

	@Override
	public void unload() {
		save();
	}

	@FXML
	protected void sidebar_item(MouseEvent event) {
		for (int i = 0; i < this.sidebaritems.getChildren().size(); ++i) {
			if (event.getSource() == this.sidebaritems.getChildren().get(i)) {
				if (i == this.sidebaritems.getChildren().size() - 1) {
					this.content_header.setText("Adding Item...");
					this.content.setText("Adding Item.....");
					SubStage sub = new SubStage(
							new FXMLInformation("selector/todolist/resources/add_item", "", "Add Item", new AddItem(this)));
					sub.getStageHandler().setIcon("selector/todolist/images/icon.png");
				} else {
					this.content_header.setText(this._infos.get(i).getValue1());
					this.content.setText(this._infos.get(i).getValue2());
					this.sidebaritems.getChildren().get(i).setStyle(
							"-fx-background-color: #f03d53;-fx-border-color: transparent transparent #5C3E84 transparent;");
					this._selected = i;
				}
			} else {
				if (i < this.sidebaritems.getChildren().size() - 1) {
					this.sidebaritems.getChildren().get(i).setStyle(
							"-fx-background-color: #F35C6E;-fx-border-color: transparent #5C3E84 #5C3E84 transparent;");
				}
			}
		}
	}

	@FXML
	protected void settings(ActionEvent event) {
		SubStage sub = new SubStage(
				new FXMLInformation("selector/todolist/resources/settings", "", "Settings", new Settings(this)));
		sub.getStageHandler().setIcon("selector/todolist/images/icon.png");
	}

	@FXML
	protected void goBack(ActionEvent event) {
		save();
		this._kcc.startExternally(_kcc.getMainStageHandler().getStage(),
				new FXMLInformation("selector/main/resources/main", "selector/main/resources/styles", "Selector",
						new selector.main.MainController()));
	}

	@FXML
	protected void delete(ActionEvent event) {
		if (this._selected > -1) {
			this.sidebaritems.getChildren().remove(this._selected);
			this._infos.remove(this._selected);
			selectNewest();
		}
	}

	public void addSideBarItem(String text, String header, String content) {
		Node label = new Label(text);
		this.sidebaritems.getChildren().add(sidebaritems.getChildren().size() - 1, label);
		this._infos.add(new Container<>(header, content));
		label.setOnMouseClicked(this._SIDERBAR_ITEM_CLICK);
	}

	public File getSaveFile() {
		return this._saveFile;
	}

	public void setSaveFile(File saveFile) {
		this._saveFile = saveFile;
	}

	public void save() {
		File file = new File(this._saveFile, "To-Do-List.data");
		try {
			file.createNewFile();
			// TODO: Save items
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void selectNewest() {
		if (this.sidebaritems.getChildren().size() > 1) {
			int i = this.sidebaritems.getChildren().size() - 2;
			this.content_header.setText(this._infos.get(i).getValue1());
			this.content.setText(this._infos.get(i).getValue2());
			this.sidebaritems.getChildren().get(i).setStyle(
					"-fx-background-color: #f03d53;-fx-border-color: transparent transparent #5C3E84 transparent;");
			this._selected = i;
			for (int k = 0; k < this.sidebaritems.getChildren().size(); ++k) {
				if (k != i) {
					this.sidebaritems.getChildren().get(k).setStyle(
							"-fx-background-color: #F35C6E;-fx-border-color: transparent #5C3E84 #5C3E84 transparent;");
				}
			}
		} else {
			this.content_header.setText("No Item Selected!");
			this.content.setText("Create an item to select one.");
			this._selected = -1;
		}
	}
}