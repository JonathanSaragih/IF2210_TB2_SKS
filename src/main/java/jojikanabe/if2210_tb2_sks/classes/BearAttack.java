package jojikanabe.if2210_tb2_sks.classes;

import javafx.application.Platform;
import jojikanabe.if2210_tb2_sks.SceneController;
import jojikanabe.if2210_tb2_sks.classes.kartu.Kartu;

import java.util.Random;

public class BearAttack {
    private static final Random random = new Random();
    private boolean bearAttackOngoing = false;
    private Thread bearAttackThread;
    private int[][] bearSubgrid;

    public void startBearAttack(SceneController controller) {
        if (random.nextInt(100) < 20) { // 20% chance for bear attack
            bearAttackOngoing = true;
            bearSubgrid = getRandomSubgrid();
            int duration = 30 + random.nextInt(31); // 30 to 60 seconds

            bearAttackThread = new Thread(() -> {
                int timeLeft = duration * 10;
                try {
                    while (timeLeft > 0) {
                        Thread.sleep(100); // Sleep for 0.1 seconds
                        int finalTimeLeft = timeLeft;
                        Platform.runLater(() -> controller.updateTimerUI(finalTimeLeft / 10.0));
                        timeLeft--;
                    }
                    endBearAttack(controller);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            bearAttackThread.setDaemon(true); // Ensure the thread doesn't prevent the application from exiting
            bearAttackThread.start();
        }
    }

    private int[][] getRandomSubgrid() {
        int startX = random.nextInt(2); // Assuming grid size is 5x5
        int startY = random.nextInt(3);
        return new int[][]{
                {startX, startY},
                {startX + 1, startY},
                {startX, startY + 1},
                {startX + 1, startY + 1},
                {startX + 2, startY},
                {startX, startY + 2}
        };
    }

    private synchronized void endBearAttack(SceneController controller) {
        for (int[] cell : bearSubgrid) {
            int x = cell[0];
            int y = cell[1];
            Ladang ladang = GameState.getInstance().getPemain().get(GameState.getInstance().giliran - 1).getLadang();
            Kartu kartu = ladang.getKartu(x, y);
            if (kartu != null) {
                ladang.removeKartu(x, y);
            }
        }
        bearAttackOngoing = false;
        Platform.runLater(controller::refreshGame);
    }

    public boolean isBearAttackOngoing() {
        return bearAttackOngoing;
    }

    public Thread getBearAttackThread() {
        return this.bearAttackThread;
    }
}
