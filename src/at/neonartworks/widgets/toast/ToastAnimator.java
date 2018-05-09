package at.neonartworks.widgets.toast;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * <h1>ToastAnimator</h1> <br>
 * The Code for this class is borrowed from my JFXAnimator class! This class is
 * responsible to animate the Toast into the scene.
 * 
 * @author Florian Wagner
 *
 */
public class ToastAnimator {
	private static Timeline intTimeline = new Timeline();
	private static Timeline outTImeline = new Timeline();
	private static boolean isFinished = true;

	private static void resetTimeline() {
		intTimeline = new Timeline();
		outTImeline = new Timeline();
		isFinished = false;
	}

	/**
	 * Animates the toast..
	 * 
	 * @param stage
	 * @param duration
	 * @param startDuration
	 * @param endDuration
	 */
	public static void animateToast(Stage stage, int duration, int startDuration, int endDuration) {
		resetTimeline();
		KeyFrame inKey = new KeyFrame(Duration.millis(startDuration),
				new KeyValue(stage.getScene().getRoot().opacityProperty(), 1));

		intTimeline.getKeyFrames().add(inKey);
		intTimeline.setOnFinished((actionEvent) -> {
			new Thread(() -> {
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				KeyFrame outKey = new KeyFrame(Duration.millis(endDuration),
						new KeyValue(stage.getScene().getRoot().opacityProperty(), 0));

				outTImeline.getKeyFrames().add(outKey);
				outTImeline.setOnFinished((finishActionEvent) -> stage.close());
				outTImeline.play();
				isFinished = true;
			}).start();
		});

		intTimeline.play();

	}
}
