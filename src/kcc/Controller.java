package kcc;

import java.awt.Desktop;
import java.net.URI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class Controller {

	private final static EventHandler<ActionEvent> _HYPER_LINK;

	static {
		_HYPER_LINK = (action) -> {
			try {
				Desktop.getDesktop().browse(new URI(((Node) action.getSource()).getAccessibleHelp()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

	protected KCC _kcc;
	protected Stage _stage;

	/**
	 * This is ran after the scene is initialized, it loads events and other stuff
	 * to nodes
	 *
	 * @param mainProgram The main program
	 * @param mainStage   The stage it initializes on
	 */
	@SuppressWarnings("unused")
	private void init(KCC mainProgram, Stage stage) {
		this._kcc = mainProgram;
		this._stage = stage;
		fixChildren(_stage.getScene().getRoot());
		load();
	}

	/**
	 * Fixes stuff, like hyperlinks (is recursive to get all children)
	 *
	 * @param parent The parent you want to start from
	 */
	private void fixChildren(Node parent) {
		for (Node node : ((Pane) parent).getChildren()) {
			if (node instanceof Hyperlink) {
				final Hyperlink link = (Hyperlink) node;
				link.setOnAction(_HYPER_LINK);
			} else if (node instanceof Pane) {
				fixChildren((Pane) node);
			}
		}
	}

	/**
	 * Ran when the fxml is loaded on the scene, use of any variable is allowed
	 */
	public abstract void load();

	/**
	 * Ran when the fxml is unloaded on the scene, use of any variable is allowed
	 */
	public void unload() {
	}
}