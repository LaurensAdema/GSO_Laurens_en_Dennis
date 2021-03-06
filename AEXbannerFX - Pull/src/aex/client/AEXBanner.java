/**
 * ***** AEXBanner.java **************************************
 */
package aex.client;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AEXBanner extends Application {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 100;
    public static final int NANO_TICKS = 20000000;
    // FRAME_RATE = 1000000000/NANO_TICKS = 50;
    private Text text;
    private double textLength;
    private double textPosition;
    private BannerController controller;
    private AnimationTimer animationTimer;
    
    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage primaryStage)
    {

        controller = new BannerController(this);

        Font font = new Font("Arial", HEIGHT);
        text = new Text();
        text.setFont(font);
        text.setFill(Color.YELLOW);

        Pane root = new Pane();
        root.getChildren().add(text);
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);

        primaryStage.setTitle("AEX banner");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.toFront();

        // Start animation: text moves from right to left
        animationTimer = new AnimationTimer() {
            private long prevUpdate;

            @Override
            public void handle(long now)
            {
                long lag = now - prevUpdate;
                if (lag >= NANO_TICKS)
                {
                    // calculate new location of text
                    // TODO
                    textPosition -= 5;
                    if (textPosition < 0 - textLength)
                    {
                        textPosition = 0;
                    }
                    text.relocate(textPosition, 0);
                    prevUpdate = now;
                }
            }

            @Override
            public void start()
            {
                prevUpdate = System.nanoTime();
                textPosition = 0;
                text.relocate(textPosition, 0);
                setKoersen("Nothing to display");
                super.start();
            }
        };
        animationTimer.start();

        primaryStage.setOnCloseRequest(event ->
        {
            System.exit(0);
        });
    }

    public void setKoersen(String koersen)
    {
        text.setText(koersen);
        textLength = text.getLayoutBounds().getWidth();
        text.setText(koersen + " " + koersen);
    }

    @Override
    public void stop()
    {
        animationTimer.stop();
    }
}
