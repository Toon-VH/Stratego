package Stratego.View.SetupScreen;

import Stratego.Model.gamePlay.army.RankType;
import javafx.scene.layout.BorderPane;

public class BorderPaneRankType extends BorderPane {

    private RankType rankType;

    public RankType getRankType() {
        return rankType;
    }

    public void setRankType(RankType rankType) {
        this.rankType = rankType;
    }
}
