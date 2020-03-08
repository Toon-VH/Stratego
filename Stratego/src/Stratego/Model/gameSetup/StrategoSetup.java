package Stratego.Model.gameSetup;

import Stratego.Model.gamePlay.army.ArmyColor;
import Stratego.Model.gamePlay.army.RankType;

public class StrategoSetup {

    RankType[][] setup;

    public StrategoSetup() {
        this.setup = new RankType[4][10];

    }

    public void SetSoldier(RankType rankType, int x, int y) {
        //aantal van type toegelaten
        AvailableSoldiers availableSoldiers = getAvailableSoldiers();
        availableSoldiers.remove(rankType);
        if (!availableSoldiers.isValid()){
            return;
            //exeption
        }

        //geldig veld


        //ander
        setup[x][y] = rankType;
    }

    public AvailableSoldiers getAvailableSoldiers() {
        AvailableSoldiers availableSoldiers = new AvailableSoldiers();
        for (RankType[] solderrow : setup) {
            for (RankType soldier : solderrow) {
                availableSoldiers.remove(soldier);
            }
        }
        return availableSoldiers;
    }

    public boolean allSet() {
        return getAvailableSoldiers().allSet();
    }

    public void saveConfig(ArmyColor armyColor){

    }

    public RankType[][] getSetup() {
        return setup;
    }
}
