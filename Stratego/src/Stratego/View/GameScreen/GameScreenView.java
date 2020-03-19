package Stratego.View.GameScreen;

import Stratego.Model.gamePlay.army.ArmyColor;
import Stratego.Model.gamePlay.army.RankType;
import Stratego.View.SetupScreen.BorderPanePosition;
import Stratego.View.SetupScreen.BorderPaneRankType;
import Stratego.View.UISettings;
import Stratego.View.common.BaseView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.MalformedURLException;


public class GameScreenView extends BaseView {

    // private Node attributen (controls)
    private BorderPaneRankType[] reDead;
    private BorderPaneRankType[] blueDead;
    private Button redButton;
    private Button blueButton;
    private BorderPanePosition[] positions;
    private ImageView imgInfo;
    private ImageView imgSave;
    private Label redPower;
    private Label bluePower;


    public GameScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        // Initialisatie van de Nodes
        this.redPower = new Label();
        this.bluePower = new Label();
        this.redButton = new Button("Play");
        this.blueButton = new Button("Play");
        try {
            this.imgInfo = new ImageView(new Image(uiSettings.getInfoImg().toUri().toURL().toString()));
            this.imgSave = new ImageView(new Image(uiSettings.getSaveImg().toUri().toURL().toString()));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.positions = new BorderPanePosition[100];

    }

    private void layoutNodes() {
        ImageView fileImg = null;
        BorderPane left = new BorderPane();
        BorderPane right = new BorderPane();
        BorderPane rightTop = new BorderPane();
        BorderPane leftTop = new BorderPane();
        GridPane gridPlace = new GridPane();

        Label dead1 = new Label("Dead");
        Label dead2 = new Label("Dead");

        gridPlace.setHgap(7);
        gridPlace.setVgap(7);

        this.imgSave.setFitWidth(70);
        this.imgSave.setFitHeight(70);
        this.imgInfo.setFitWidth(70);
        this.imgInfo.setFitHeight(70);

        int counter = 0;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                try {
                    Image img;
                    if ((y == 4 || y == 5) && (x == 2 || x == 3 || x == 6 || x == 7)) {
                        if (y == 4) {
                            if (x == 2 || x == 6) {
                                img = new Image(uiSettings.getWater1().toUri().toURL().toString());
                            } else img = new Image(uiSettings.getWater2().toUri().toURL().toString());
                        } else {
                            if (x == 2 || x == 6) {
                                img = new Image(uiSettings.getWater4().toUri().toURL().toString());
                            } else img = new Image(uiSettings.getWater3().toUri().toURL().toString());
                        }
                    } else {
                        img = new Image(uiSettings.getGrassImg().toUri().toURL().toString());
                    }
                    BorderPanePosition position = new BorderPanePosition(x, y);

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
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));

                    positions[counter] = position;
                    counter++;

                    gridPlace.add(position, x, y);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }
        }
        redPower.setFont(Font.font("Cambria", 30));
        bluePower.setFont(Font.font("Cambria", 30));

        setRight(right);
        setLeft(left);
        setCenter(gridPlace);

        this.blueDead = drawDeadArmy(left, ArmyColor.Blue);
        this.reDead = drawDeadArmy(right, ArmyColor.Red);

        right.setTop(rightTop);
        rightTop.setRight(imgInfo);
        rightTop.setLeft(redPower);
        rightTop.setBottom(dead1);
        right.setBottom(redButton);

        left.setTop(leftTop);
        leftTop.setRight(bluePower);
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

    private BorderPaneRankType[] drawDeadArmy(BorderPane pane, ArmyColor armyColor) {

        BorderPaneRankType[] deadArmy = new BorderPaneRankType[12];
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

                img.setFitWidth(70);
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

    public void refresh(Turn turn, LocationInfo[][] locationInfo, SelectedSoldier selectedSoldier, Turn nextPlayer, SelectedSoldier targetsoldier, ArmyStatus armyStatus) {

        switch (nextPlayer) {
            case Red:
                redButton.setDisable(false);
                blueButton.setDisable(true);
                break;
            case Blue:
                redButton.setDisable(true);
                blueButton.setDisable(false);
                break;
            case None:
                redButton.setDisable(true);
                blueButton.setDisable(true);
                break;
        }

        for (BorderPanePosition position : positions) {

            int x = position.getX();
            int y = position.getY();


            if (locationInfo[x][y] != null) {

                LocationInfo info = locationInfo[x][y];
                ImageView imgV = (ImageView) position.getCenter();

                if (selectedSoldier != null) {
                    if (x == selectedSoldier.getX() && y == selectedSoldier.getY()) {
                        position.setBorder(new Border(new BorderStroke(Color.GREEN,
                                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
                    } else if (info.isLoctionInRange()) {
                        position.setBorder(new Border(new BorderStroke(Color.YELLOW,
                                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
                    } else position.setBorder(new Border(new BorderStroke(Color.BLACK,
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
                } else position.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));

                try {

                    switch (turn) {
                        case Red:
                            if (info.getRankType() != null) {
                                if (locationInfo[x][y].getArmyColor() == ArmyColor.Red) {
                                    imgV.setImage(getArmyImage(info.getRankType(), ArmyColor.Red));
                                } else imgV.setImage(new Image(uiSettings.getbBack().toUri().toURL().toString()));
                            } else imgV.setImage(null);

                            break;
                        case Blue:
                            if (info.getRankType() != null) {
                                if (locationInfo[x][y].getArmyColor() == ArmyColor.Blue) {
                                    imgV.setImage(getArmyImage(info.getRankType(), ArmyColor.Blue));
                                } else imgV.setImage(new Image(uiSettings.getrBack().toUri().toURL().toString()));
                            } else imgV.setImage(null);
                            break;
                        case None:
                            if (info.getRankType() != null) {
                                if (locationInfo[x][y].getArmyColor() == ArmyColor.Blue) {
                                    imgV.setImage(new Image(uiSettings.getbBack().toUri().toURL().toString()));
                                } else if (locationInfo[x][y].getArmyColor() == ArmyColor.Red) {
                                    imgV.setImage(new Image(uiSettings.getrBack().toUri().toURL().toString()));
                                }
                            } else imgV.setImage(null);
                            break;
                    }
                    if (targetsoldier != null) {
                        if (x == targetsoldier.getX() && y == targetsoldier.getY() && info.getRankType() != null) {
                            if (turn == Turn.Blue) {
                                imgV.setImage(getArmyImage(info.getRankType(), ArmyColor.Red));
                            } else if (turn == Turn.Red) {
                                imgV.setImage(getArmyImage(info.getRankType(), ArmyColor.Blue));
                            }
                        }
                    }

                    for (BorderPaneRankType borderPaneRankType : this.reDead) {
                        Label label = (Label) borderPaneRankType.getBottom();
                        if (armyStatus.getArmyStatusRed().containsKey(borderPaneRankType.getRankType())) {
                            int dead = armyStatus.getArmyStatusRed().get(borderPaneRankType.getRankType());
                            label.setText(Integer.toString(dead));
                        } else {
                            label.setText("0");
                        }
                    }
                    for (BorderPaneRankType borderPaneRankType : this.blueDead) {
                        Label label = (Label) borderPaneRankType.getBottom();
                        if (armyStatus.getArmyStatusBlue().containsKey(borderPaneRankType.getRankType())) {
                            int dead = armyStatus.getArmyStatusBlue().get(borderPaneRankType.getRankType());
                            label.setText(Integer.toString(dead));
                        } else {
                            label.setText("0");
                        }
                    }

                    redPower.setText(Integer.toString(armyStatus.getRedPower()));
                    bluePower.setText(Integer.toString(armyStatus.getBluePower()));

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public Button getRedButton() {
        return redButton;
    }

    public Button getBlueButton() {
        return blueButton;
    }

    public BorderPanePosition[] getPositions() {
        return positions;
    }

    public ImageView getImgInfo() {
        return imgInfo;
    }

    public ImageView getImgSave() {
        return imgSave;
    }
}

