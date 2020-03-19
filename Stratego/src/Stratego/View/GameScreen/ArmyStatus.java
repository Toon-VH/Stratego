package Stratego.View.GameScreen;

import Stratego.Model.gamePlay.army.RankType;

import java.util.HashMap;
import java.util.Map;

public class ArmyStatus {

    private Map<RankType, Integer> armyStatusRed;
    private Map<RankType, Integer> armyStatusBlue;

    public ArmyStatus() {

        this.armyStatusRed = new HashMap<>();
        this.armyStatusBlue = new HashMap<>();

    }

    public Map<RankType, Integer> getArmyStatusRed() {
        return armyStatusRed;
    }

    public Map<RankType, Integer> getArmyStatusBlue() {
        return armyStatusBlue;
    }

    public int getBluePower() {
        return powerCounter(armyStatusBlue);
    }

    public int getRedPower() {
        return powerCounter(armyStatusRed);
    }

    private int powerCounter(Map<RankType, Integer> map) {
        int totalPower = 2000;
        for (RankType key : map.keySet()) {
            int power = 0;
            int deads = map.get(key);
            switch (key) {
                case Marshal:
                    power = 100;
                    break;

                case General:
                    power = 90;
                    break;

                case Colonel:
                    power = 80;
                    break;

                case Major:

                case Spy:
                    power = 70;
                    break;

                case Captain:
                    power = 60;
                    break;

                case Lieutenant:

                case Minor:
                    power = 50;
                    break;

                case Bomb:
                case Sergeant:
                    power = 40;
                    break;

                case Scout:
                    power = 30;
                    break;

                case Flag:
                    totalPower = 0;
                    break;

            }
            totalPower -= power * deads;
        }
        return totalPower;
    }
}

