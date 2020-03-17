package Stratego.View.GameScreen;

import Stratego.Model.gamePlay.army.ArmyColor;
import Stratego.Model.gamePlay.army.RankType;

public class LocationInfo {

    private ArmyColor armyColor;
    private RankType rankType;
    private boolean loctionInRange;


    public LocationInfo(RankType rankType, ArmyColor armyColor) {
        this.armyColor = armyColor;
        this.rankType = rankType;
    }

    public ArmyColor getArmyColor() {
        return armyColor;
    }

    public RankType getRankType() {
        return rankType;
    }

    public void setLoctionInRange(boolean loctionInRange) {
        this.loctionInRange = loctionInRange;
    }

    public boolean isLoctionInRange() {
        return loctionInRange;
    }
}
