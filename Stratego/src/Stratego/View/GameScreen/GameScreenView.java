package Stratego.View.GameScreen;

import Stratego.Model.gamePlay.army.ArmyColor;
import Stratego.Model.gamePlay.army.RankType;
import Stratego.View.SetupScreen.BorderPaneRankType;
import Stratego.View.UISettings;
import Stratego.View.common.BaseView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.MalformedURLException;
import java.nio.file.Files;


public class GameScreenView extends BaseView {

    // private Node attributen (controls)

    private Button ready;


    public GameScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        // Initialisatie van de Nodes
        this.ready = new Button("Ready");
    }

    private void layoutNodes() {
        ImageView fileImg = null;
        BorderPane left = new BorderPane();
        BorderPane right = new BorderPane();
        BorderPane rightTop = new BorderPane();
        BorderPane leftTop = new BorderPane();
        GridPane gridPlace = new GridPane();
        Button redButton = new Button("Ready");
        Button blueButton = new Button("Ready");
        Label red = new Label("100");
        Label blue = new Label("100");
        gridPlace.setHgap(7);
        gridPlace.setVgap(7);

        ImageView imgInfo = null;
        ImageView imgSave = null;

        try {
            imgSave = new ImageView(new Image(uiSettings.getSaveImg().toUri().toURL().toString()));
            imgInfo = new ImageView(new Image(uiSettings.getInfoImg().toUri().toURL().toString()));
            imgSave.setFitWidth(70);
            imgSave.setFitHeight(70);
            imgInfo.setFitWidth(70);
            imgInfo.setFitHeight(70);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    ImageView img;
                    if ((i == 4 || i == 5) && (j == 2 || j == 3 || j == 6 || j == 7)) {
                        img = new ImageView(new Image(uiSettings.getWater1().toUri().toURL().toString()));
                    } else {
                        img = new ImageView(new Image(uiSettings.getGrassImg().toUri().toURL().toString()));
                    }
                    img.setFitWidth(70);
                    img.setFitHeight(70);
                    gridPlace.add(img, j, i);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
        right.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        left.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        rightTop.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        leftTop.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        red.setFont(Font.font("Cambria", 30));
        blue.setFont(Font.font("Cambria", 30));

        setRight(right);
        setLeft(left);

        drawDeadArmy(left,ArmyColor.Blue);
        drawDeadArmy(right,ArmyColor.Red);

        right.setTop(rightTop);
        rightTop.setRight(imgInfo);
        rightTop.setLeft(red);
        right.setBottom(redButton);

        left.setTop(leftTop);
        leftTop.setRight(blue);
        leftTop.setLeft(imgSave);
        left.setBottom(blueButton);

        setCenter(gridPlace);
        gridPlace.setAlignment(Pos.BASELINE_CENTER);
    }
    // implementatie van de nodige
    // package-private Getters

    private BorderPane[] drawDeadArmy(BorderPane pane, ArmyColor armyColor) {

        BorderPane[] deadArmy = new BorderPane[12];
        GridPane gridDead = new GridPane();
        RankType soldierType = null;
        ImageView img = new ImageView();
        String number = "0";
        int counter = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {

                switch (i) {
                    case 0:
                        switch (j) {
                            case 0:
                                soldierType = RankType.Marshal;
                                break;
                            case 1:
                                soldierType = RankType.General;
                                break;
                        }
                    case 1:
                        switch (j) {
                            case 0:
                                soldierType = RankType.Colonel;
                                break;
                            case 1:
                                soldierType = RankType.Major;
                                break;
                        }
                    case 2:
                        switch (j) {
                            case 0:
                                soldierType = RankType.Captain;
                                break;
                            case 1:
                                soldierType = RankType.Lieutenant;
                                break;
                        }
                    case 3:
                        switch (j) {
                            case 0:
                                soldierType = RankType.Sergeant;
                                break;
                            case 1:
                                soldierType = RankType.Minor;
                                break;
                        }
                    case 4:
                        switch (j) {

                            case 0:
                                soldierType = RankType.Scout;
                                break;
                            case 1:
                                soldierType = RankType.Spy;
                                break;
                        }
                    case 5:
                        switch (j) {
                            case 0:
                                soldierType = RankType.Flag;
                                break;
                            case 1:
                                soldierType = RankType.Bomb;
                                break;
                        }
                }
                img = new ImageView(getArmyImage(soldierType, armyColor));

                BorderPaneRankType borderPane = new BorderPaneRankType();
                borderPane.setRankType(soldierType);
                borderPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

                Label name = new Label(soldierType.name());
                borderPane.setTop(name);

                Label count = new Label(number);
                borderPane.setBottom(count);

                borderPane.setCenter(img);
                img.setFitWidth(70);
                img.setFitHeight(70);
                gridDead.add(borderPane, j, i);

                deadArmy[counter] = borderPane;
                counter++;


            }
        }
        pane.setCenter(gridDead);
        return deadArmy;
    }

    public Button getReady() {
        return ready;
    }
}

