package org.example.game2d_v1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lablepause;

    @FXML
    private ImageView bg1, bg2, player, enemy;

    private final int bG_Width = 705;
    private ParallelTransition parallelTransition;
    private TranslateTransition enemyTransition;
    public static boolean jump = false;
    public static boolean right = false;
    public static boolean left = false;
    public static boolean isPause = false;
    private int playerSpeed = 3, jumptDownSpeed = 5;

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {

        if (jump && player.getLayoutY() > 90f)
            player.setLayoutY(player.getLayoutY() - playerSpeed);
        else if (player.getLayoutY() <= 181f)
        {
            jump = false;
            player.setLayoutY(player.getLayoutY() + playerSpeed);
        }
        if (right && player.getLayoutX() < 500f)
            player.setLayoutX(player.getLayoutX() + playerSpeed);
        if (left && player.getLayoutX() > 105f)
            player.setLayoutX(player.getLayoutX() - jumptDownSpeed);

        if (isPause && !lablepause.isVisible())
        {
            playerSpeed = 0;
            jumptDownSpeed = 0;
            parallelTransition.pause();
            enemyTransition.pause();
            lablepause.setVisible(true);
        }
        else if (!isPause && lablepause.isVisible())
        {
            lablepause.setVisible((false));
            playerSpeed = 3;
            jumptDownSpeed = 5;
            parallelTransition.play();
            enemyTransition.play();

        }
        }

    };
    @FXML
    void initialize() {
        TranslateTransition bgOneTransition = new TranslateTransition(Duration.millis(5000), bg1);
        bgOneTransition.setFromX(0);
        bgOneTransition.setToX(bG_Width * -1);
        bgOneTransition.setInterpolator(Interpolator.LINEAR);
        bgOneTransition.play();

        TranslateTransition bgTwoTransition = new TranslateTransition(Duration.millis(5000), bg2);
        bgTwoTransition.setFromX(0);
        bgTwoTransition.setToX(bG_Width * -1);
        bgTwoTransition.setInterpolator(Interpolator.LINEAR);
        bgTwoTransition.play();

        enemyTransition = new TranslateTransition(Duration.millis(3500), enemy);
        enemyTransition.setFromX(0);
        enemyTransition.setToX(bG_Width * -1 - 100);
        enemyTransition.setInterpolator(Interpolator.LINEAR);
        enemyTransition.setCycleCount(Animation.INDEFINITE);
        enemyTransition.play();

        parallelTransition = new ParallelTransition(bgOneTransition, bgTwoTransition);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();

        timer.start();
    }

}
