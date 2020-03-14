package Stratego.Model.gamePlay;
import Stratego.Model.gamePlay.army.ArmyColor;
import Stratego.Model.gamePlay.player.Player;
import Stratego.Model.gamePlay.playground.Playground;


import java.nio.file.Path;
import java.util.Random;

public class Stratego {

    protected Playground playground;
    protected Player activePlayer;

    public Stratego(String redNaam, String blueName) {
        Player p1 = new Player(redNaam, ArmyColor.Red);
        Player p2 = new Player(blueName, ArmyColor.Blue);
        this.playground = new Playground(p1, p2);
        startPlayer();
    }

    public void loadArmySetup(ArmyColor armyColor, Path file) {
        playground.loadArmySetup(armyColor, file);
    }

    private void startPlayer() {
        Player player;
        int rnd = new Random().nextInt(2);
        if (rnd == 0) {
            player = playground.getRedArmy();
        } else player = playground.getBlueArmy();
        activePlayer = player;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Playground getPlayground() {
        return playground;
    }

    public void switchPlayer() {
        playground.resetIsInRange();
        if (getActivePlayer() == playground.getRedArmy()) {
            activePlayer = playground.getBlueArmy();
        }else {
            activePlayer = playground.getRedArmy();
        }
    }
}
