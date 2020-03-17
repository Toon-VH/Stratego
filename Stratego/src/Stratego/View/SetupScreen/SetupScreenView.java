package Stratego.View.SetupScreen;

import Stratego.Model.gamePlay.army.ArmyColor;
import Stratego.Model.gamePlay.army.RankType;
import Stratego.Model.gameSetup.AvailableSoldiers;
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

public class SetupScreenView extends BaseView {

    // private Node attributen (controls)
    private Button ready;
    private ImageView imgInfo;
    private ImageView fileImg;
    private BorderPaneRankType[] army;
    private BorderPanePosition[] positions;

    public SetupScreenView(UISettings uiSettings) {
        this.armyC = ArmyColor.Blue;
        this.army = new BorderPaneRankType[12];
        this.positions = new BorderPanePosition[40];
        this.uiSettings = uiSettings;
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        // Initialisatie van de Nodes
        this.ready = new Button("Ready");
        try {
            this.imgInfo = new ImageView(new Image(uiSettings.getInfoImg().toUri().toURL().toString()));
            this.fileImg = new ImageView(new Image(uiSettings.getSetupFileImg().toUri().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void layoutNodes() {
        Label title = new Label("Setup your Army");
        GridPane gridPlace = new GridPane();
        GridPane gridTake = new GridPane();
        BorderPane center = new BorderPane();
        BorderPane right = new BorderPane();
        BorderPane left = new BorderPane();

        try {
            int counter = 0;

            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 5; y++) {

                    Image img;
                    if (y == 0 && (x == 2 || x == 3 || x == 6 || x == 7)) {
                        img = new Image(uiSettings.getWater1().toUri().toURL().toString());
                    } else {
                        img = new Image(uiSettings.getGrassImg().toUri().toURL().toString());
                    }


                    BorderPanePosition position = new BorderPanePosition(x, y - 1);

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
                    if (y > 0) {
                        positions[counter] = position;
                        counter++;

                    }
                    gridPlace.add(position, x, y);

                }
            }

            RankType soldierType = null;
            ImageView img = new ImageView();
            String number = "0";
            counter = 0;
            for (int x = 0; x < 6; x++) {
                for (int y = 0; y < 2; y++) {

                    switch (y) {
                        case 0:
                            switch (x) {
                                case 0:
                                    soldierType = RankType.Marshal;
                                    number = "1";
                                    break;
                                case 1:
                                    soldierType = RankType.General;
                                    number = "1";
                                    break;
                                case 2:
                                    soldierType = RankType.Colonel;
                                    number = "2";
                                    break;
                                case 3:
                                    soldierType = RankType.Major;
                                    number = "3";
                                    break;
                                case 4:
                                    soldierType = RankType.Captain;
                                    number = "4";
                                    break;
                                case 5:
                                    soldierType = RankType.Lieutenant;
                                    number = "4";
                                    break;
                            }
                            break;
                        case 1:
                            switch (x) {
                                case 0:
                                    soldierType = RankType.Sergeant;
                                    number = "4";
                                    break;
                                case 1:
                                    soldierType = RankType.Minor;
                                    number = "5";
                                    break;
                                case 2:
                                    soldierType = RankType.Scout;
                                    number = "8";
                                    break;
                                case 3:
                                    soldierType = RankType.Spy;
                                    number = "1";
                                    break;
                                case 4:
                                    soldierType = RankType.Flag;
                                    number = "1";
                                    break;
                                case 5:
                                    soldierType = RankType.Bomb;
                                    number = "6";
                                    break;
                            }
                            break;
                    }

                    img = new ImageView(getArmyImage(soldierType, armyC));

                    BorderPaneRankType borderPane = new BorderPaneRankType();
                    borderPane.setRankType(soldierType);
                    borderPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));

                    Label name = new Label(soldierType.name());
                    Label count = new Label(number);

                    borderPane.setTop(name);
                    borderPane.setBottom(count);
                    borderPane.setCenter(img);
                    gridTake.add(borderPane, x, y);

                    count.setMaxWidth(Double.MAX_VALUE);
                    count.setAlignment(Pos.CENTER);
                    name.setMaxWidth(Double.MAX_VALUE);
                    name.setAlignment(Pos.CENTER);

                    img.setFitWidth(70);
                    img.setFitHeight(70);

                    army[counter] = borderPane;
                    counter++;
                }
            }
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        }
        gridPlace.setHgap(15);
        gridPlace.setVgap(15);
        imgInfo.setFitWidth(70);
        imgInfo.setFitHeight(70);
        fileImg.setPreserveRatio(true);
        fileImg.setFitWidth(70);
        fileImg.setSmooth(true);
        setRight(right);
        setLeft(left);
        right.setTop(imgInfo);
        left.setTop(fileImg);
        setCenter(center);
        center.setTop(title);
        center.setCenter(gridPlace);
        center.setBottom(gridTake);
        right.setBottom(ready);
        title.setFont(Font.font("Cambria", 32));
        setMargin(gridPlace, new Insets(50, 0, 0, 0));
        setMargin(title, new Insets(50, 0, 0, 0));
        ready.setPrefSize(100,100);
        ready.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);
        gridPlace.setAlignment(Pos.BASELINE_CENTER);
        gridTake.setAlignment(Pos.BASELINE_CENTER);
        gridTake.setHgap(20);
        gridTake.setVgap(10);
    }

    // implementatie van de nodige
    // package-private Getters

    public void refresh(AvailableSoldiers availableSoldiers, RankType[][] setup, ArmyColor armyColor) {
        int number = 0;

        this.armyC = armyColor;

        for (BorderPaneRankType rank : army) {


            Label label = (Label) rank.getBottom();

            ImageView image = (ImageView) rank.getCenter();
            image.setImage(getArmyImage(rank.getRankType(), armyC));

            switch (rank.getRankType()) {
                case Sergeant:
                    number = availableSoldiers.getSergeant();
                    label.setText(Integer.toString(number));
                    break;
                case General:
                    number = availableSoldiers.getGeneral();
                    label.setText(Integer.toString(number));
                    break;
                case Captain:
                    number = availableSoldiers.getCaptain();
                    label.setText(Integer.toString(number));
                    break;
                case Major:
                    number = availableSoldiers.getMajor();
                    label.setText(Integer.toString(number));
                    break;
                case Marshal:
                    number = availableSoldiers.getMarshal();
                    label.setText(Integer.toString(number));
                    break;
                case Lieutenant:
                    number = availableSoldiers.getLieutenant();
                    label.setText(Integer.toString(number));
                    break;
                case Minor:
                    number = availableSoldiers.getMinor();
                    label.setText(Integer.toString(number));
                    break;
                case Scout:
                    number = availableSoldiers.getScout();
                    label.setText(Integer.toString(number));
                    break;
                case Spy:
                    number = availableSoldiers.getSpy();
                    label.setText(Integer.toString(number));
                    break;
                case Bomb:
                    number = availableSoldiers.getBomb();
                    label.setText(Integer.toString(number));
                    break;
                case Flag:
                    number = availableSoldiers.getFlag();
                    label.setText(Integer.toString(number));
                    break;
                case Colonel:
                    number = availableSoldiers.getColonel();
                    label.setText(Integer.toString(number));
                    break;
            }

            for (BorderPanePosition position : positions) {
                RankType rankType = setup[position.getX()][position.getY()];
                ImageView imageView = (ImageView) position.getCenter();

                if (rankType != null) {
                    imageView.setImage(getArmyImage(rankType, armyC));
                } else imageView.setImage(null);
            }
        }
    }

    public Button getReady() {
        return ready;
    }

    public BorderPaneRankType[] getArmy() {
        return army;
    }

    public BorderPanePosition[] getPositions() {
        return positions;
    }

    public ImageView getImgInfo() {
        return imgInfo;
    }

    public ImageView getFileImg() {
        return fileImg;
    }
}

