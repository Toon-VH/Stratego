package Stratego.View.GameScreen;

import Stratego.Model.gamePlay.army.ArmyColor;
import Stratego.Model.gamePlay.army.RankType;
import Stratego.View.SetupScreen.BorderPanePosition;
import Stratego.View.SetupScreen.BorderPaneRankType;
import Stratego.View.UISettings;
import Stratego.View.common.BaseView;
import com.sun.jdi.Value;
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
import java.time.Instant;


public class GameScreenView extends BaseView {

    // private Node attributen (controls)

    private Button ready;
    private ImageView[][] imageViews;


    public GameScreenView(UISettings uiSettings) {
        this.imageViews = new ImageView[10][10];
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
        Label dead1 = new Label("Dead");
        Label dead2 = new Label("Dead");
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
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                try {
                    Image img;
                    if ((y == 4 || y == 5) && (x == 2 || x == 3 || x == 6 || x == 7)) {
                        img = new Image(uiSettings.getWater1().toUri().toURL().toString());
                    } else {
                        img = new Image(uiSettings.getGrassImg().toUri().toURL().toString());
                    }
                    BorderPane position = new BorderPane();

                    position.setPrefSize(65, 65);
                    ImageView imageView = new ImageView();
                    position.setCenter(imageView);
                    imageView.setFitHeight(65);
                    imageView.setFitWidth(65);
                    position.setBackground(new Background(new BackgroundImage(img,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.CENTER,
                            new BackgroundSize(65, 65, false, false, false, false))));

                    position.setBorder(new Border(new BorderStroke(Color.BLACK,
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

                    imageViews[x][y] = imageView;

                    gridPlace.add(position, x, y);
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
        setCenter(gridPlace);

        drawDeadArmy(left, ArmyColor.Blue);
        drawDeadArmy(right, ArmyColor.Red);

        right.setTop(rightTop);
        rightTop.setRight(imgInfo);
        rightTop.setLeft(red);
        rightTop.setBottom(dead1);
        right.setBottom(redButton);

        left.setTop(leftTop);
        leftTop.setRight(blue);
        leftTop.setLeft(imgSave);
        leftTop.setBottom(dead2);
        left.setBottom(blueButton);

        dead1.setMaxWidth(Double.MAX_VALUE);
        dead1.setAlignment(Pos.CENTER);
        dead1.setFont(Font.font("Cambria", 24));
        dead2.setMaxWidth(Double.MAX_VALUE);
        dead2.setAlignment(Pos.CENTER);
        dead2.setFont(Font.font("Cambria", 24));

        blueButton.setMaxWidth(Double.MAX_VALUE);
        blueButton.setPrefSize(50, 30);
        redButton.setMaxWidth(Double.MAX_VALUE);
        redButton.setPrefSize(50, 30);

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
                        break;
                    case 1:
                        switch (j) {
                            case 0:
                                soldierType = RankType.Colonel;
                                break;
                            case 1:
                                soldierType = RankType.Major;
                                break;
                        }
                        break;
                    case 2:
                        switch (j) {
                            case 0:
                                soldierType = RankType.Captain;
                                break;
                            case 1:
                                soldierType = RankType.Lieutenant;
                                break;
                        }
                        break;
                    case 3:
                        switch (j) {
                            case 0:
                                soldierType = RankType.Sergeant;
                                break;
                            case 1:
                                soldierType = RankType.Minor;
                                break;
                        }
                        break;
                    case 4:
                        switch (j) {

                            case 0:
                                soldierType = RankType.Scout;
                                break;
                            case 1:
                                soldierType = RankType.Spy;
                                break;
                        }
                        break;
                    case 5:
                        switch (j) {
                            case 0:
                                soldierType = RankType.Flag;
                                break;
                            case 1:
                                soldierType = RankType.Bomb;
                                break;
                        }
                        break;
                }

                img = new ImageView(getArmyImage(soldierType, armyColor));

                BorderPaneRankType borderPane = new BorderPaneRankType();
                borderPane.setRankType(soldierType);
                borderPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

                Label name = new Label(soldierType.name());
                Label count = new Label(number);

                borderPane.setTop(name);
                borderPane.setBottom(count);
                borderPane.setCenter(img);

                gridDead.add(borderPane, j, i);

                count.setMaxWidth(Double.MAX_VALUE);
                count.setAlignment(Pos.CENTER);
                name.setMaxWidth(Double.MAX_VALUE);
                name.setAlignment(Pos.CENTER);

                img.setFitWidth(60);
                img.setFitHeight(60);

                deadArmy[counter] = borderPane;
                counter++;

            }
        }
        pane.setCenter(gridDead);
        gridDead.setHgap(20);
        gridDead.setVgap(10);
        return deadArmy;
    }

    public void refresh(Turn turn, ArmyRank[][] armyRank) {

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (armyRank[x][y] != null) {
                    RankType rankType = armyRank[x][y].getRankType();
                    ImageView selected = imageViews[x][y];

                    try {

                        if (turn == Turn.None) {
                            if (rankType != null) {
                                if (armyRank[x][y].getArmyColor() == ArmyColor.Blue) {
                                    selected.setImage(new Image(uiSettings.getbBack().toUri().toURL().toString()));
                                } else if (armyRank[x][y].getArmyColor() == ArmyColor.Red) {
                                    selected.setImage(new Image(uiSettings.getrBack().toUri().toURL().toString()));
                                }

                            }

                        } else if (turn == Turn.Blue) {

                            if (rankType != null) {
                                if (armyRank[x][y].getArmyColor() == ArmyColor.Blue) {
                                    selected.setImage(getArmyImage(rankType, ArmyColor.Blue));
                                } else selected.setImage(new Image(uiSettings.getrBack().toUri().toURL().toString()));

                            }
                        } else if (turn == Turn.Red) {

                            if (rankType != null) {
                                if (armyRank[x][y].getArmyColor() == ArmyColor.Red) {
                                    selected.setImage(getArmyImage(rankType, ArmyColor.Red));
                                } else selected.setImage(new Image(uiSettings.getbBack().toUri().toURL().toString()));

                            }
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public Button getReady() {
        return ready;
    }
}

