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
    public Label lablepause, lose, move, goodgame;

    @FXML
    private ImageView bg1, bg2, player, enemy;

    private final int bG_Width = 705;
    private ParallelTransition parallelTransition;
    private TranslateTransition enemyTransition;
    public static boolean jump = false;
    public static boolean right = false;
    public static boolean left = false;
    public static boolean isPause = false;
    public static boolean isGoodGame = false;
    private int playerSpeed = 3, jumptDownSpeed = 5;
    int finaGame = 2000;
    private int moveIncrement = 0;

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

        if (moveIncrement == finaGame)
        {
            isGoodGame = true;
        }

        if (isGoodGame)
        {
            playerSpeed = 0;
            jumptDownSpeed = 0;
            parallelTransition.pause();
            enemyTransition.pause();
            goodgame.setVisible(true);
        }
            double playerCenterX = player.getBoundsInParent().getMinX() + player.getBoundsInParent().getWidth() / 2;
            double playerCenterY = player.getBoundsInParent().getMinY() + player.getBoundsInParent().getHeight() / 2;
            double enemyCenterX = enemy.getBoundsInParent().getMinX() + enemy.getBoundsInParent().getWidth() / 2;
            double enemyCenterY = enemy.getBoundsInParent().getMinY() + enemy.getBoundsInParent().getHeight() / 2;

            double distance = Math.sqrt(Math.pow(playerCenterX - enemyCenterX, 2) + Math.pow(playerCenterY - enemyCenterY, 2));


            if (distance <=75)
            {
                lose.setVisible(true);
                playerSpeed = 0;
                jumptDownSpeed = 0;
                parallelTransition.pause();
                enemyTransition.pause();
            }
            else
            {
                incrementMove();
            }

        }
        private void incrementMove() {
            if (moveIncrement <= finaGame)
            {
                moveIncrement++;
            }
            move.setText("Puncte:" + moveIncrement);
            System.out.println("Move: " + moveIncrement);
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
