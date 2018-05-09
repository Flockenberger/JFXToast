package at.neonartworks.widgets.toast;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * <h1>JFXToast</h1>
 * <p>
 * This Toast class is a port from the android Toast. It is an widget and it's
 * purpose is to show the user a message for a comparable short amount of time.
 * 
 * </p>
 * 
 * @author Florian Wagner
 *
 */
public class Toast {

	private static Text tx_msg;
	private static Scene scene;

	/**
	 * <h1>public static void makeToast({@link Stage}, {@link String},
	 * {@link ToastTime})</h1>
	 * 
	 * This static method creates a toast message widget with the given
	 * {@link String} msg for a given {@link ToastTime}. It will automatically
	 * animate in and out. The toast will be positioned at the {@link ToastPosition}
	 * relative to the stage provided with sc. {@link ToastTime}.TIME_LONG animates
	 * in and out for 250 ms and stays for 4s. {@link ToastTime}.TIME_SHORT animates
	 * in and out for 250 ms and stays for 1.5s.
	 * 
	 * @param sc
	 *            The main stage of the program
	 * @param msg
	 *            The message you want to show
	 * @param time
	 *            The time of the toast
	 * @param pos
	 *            The position of the toast
	 */
	public static void makeToast(Stage sc, String msg, ToastTime time, ToastPosition pos) {
		int delay = 0, inDelay = 0, outDelay = 0;

		if (time.equals(ToastTime.TIME_LONG)) {
			delay = 4000;
			inDelay = 250;
			outDelay = 250;

		} else if (time.equals(ToastTime.TIME_SHORT)) {
			delay = 1500;
			inDelay = 250;
			outDelay = 250;
		}

		Stage stage = new Stage();
		stage.initOwner(sc);
		stage.setResizable(false);

		tx_msg = new Text(msg);
		StackPane root = new StackPane(tx_msg);
		scene = new Scene(root);

		stage.initStyle(StageStyle.TRANSPARENT);
		scene.setFill(Color.TRANSPARENT);
		tx_msg.setFill(Color.WHITE);

		root.setStyle("-fx-background-radius: 15; -fx-background-color: rgba(0, 0, 0, 0.4); -fx-padding: 10px;");

		root.setOpacity(0);
		stage.setScene(scene);
		stage.show();

		if (pos.equals(ToastPosition.BOTTOM)) {
			stage.setX((sc.getX() + sc.getWidth() / 2) - stage.getWidth() / 2);
			stage.setY((sc.getY() + sc.getHeight() / 1.1) - stage.getHeight() / 2);

		} else if (pos.equals(ToastPosition.CENTER)) {
			stage.setX((sc.getX() + sc.getWidth() / 2) - stage.getWidth() / 2);
			stage.setY((sc.getY() + sc.getHeight() / 2) - stage.getHeight() / 2);

		} else if (pos.equals(ToastPosition.TOP)) {
			stage.setX((sc.getX() + sc.getWidth() / 2) - stage.getWidth() / 2);
			stage.setY((sc.getY() + sc.getHeight() / 10) - stage.getHeight() / 2);

		} else if (pos.equals(ToastPosition.LEFT)) {
			stage.setX((sc.getX() + sc.getWidth() / 10) - stage.getWidth() / 2);
			stage.setY((sc.getY() + sc.getHeight() / 2) - stage.getHeight() / 2);

		} else {
			stage.setX((sc.getX() + sc.getWidth() / 1.1) - stage.getWidth() / 2);
			stage.setY((sc.getY() + sc.getHeight() / 2) - stage.getHeight() / 2);
		}
		ToastAnimator.animateToast(stage, delay, inDelay, outDelay);
	}

}