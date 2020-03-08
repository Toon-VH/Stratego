package Stratego.View.SetupScreen;

import Stratego.Model.gamePlay.army.RankType;
import Stratego.Model.gameSetup.AvailableSoldiers;
import Stratego.View.UISettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.net.MalformedURLException;

public class SetupScreenView extends BorderPane {

    // private Node attributen (controls)

    private Button ready;
    private UISettings uiSettings;
    private ImageView imgInfo;
    private ImageView fileImg;
    private BorderPaneRankType[] army;
    private BorderPanePosition[] positions;

    public SetupScreenView(UISettings uiSettings) {
        this.army = new BorderPaneRankType[12];
        this.positions = new BorderPanePosition[40];
        this.uiSettings = uiSettings;
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        // Initialisatie van de Nodes
        this.ready = new Button("Ready");
        this.imgInfo = null;
    }

    private void layoutNodes() {
        ImageView fileImg = null;
        BorderPane top = new BorderPane();
        Label title = new Label("Setup your Army");
        GridPane gridPlace = new GridPane();
        GridPane gridTake = new GridPane();
        BorderPane bottem = new BorderPane();


        try {

            imgInfo = new ImageView(new Image(uiSettings.getInfoImg().toUri().toURL().toString()));
            fileImg = new ImageView(new Image(uiSettings.getSetupFileImg().toUri().toURL().toString()));

            int counter = 0;

            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 10; y++) {

                    ImageView img;
                    if (x == 0 && (y == 2 || y == 3 || y == 6 || y == 7)) {
                        img = new ImageView(new Image(uiSettings.getWater1().toUri().toURL().toString()));
                    } else {
                        img = new ImageView(new Image(uiSettings.getGrassImg().toUri().toURL().toString()));
                    }

                    BorderPanePosition position = new BorderPanePosition(x - 1, y);
                    position.setCenter(img);img.setFitWidth(65);
                    img.setFitHeight(65);
                    if (x > 0) {
                        positions[counter] = position;
                        counter++;
                    }
                    gridPlace.add(position, y, x);

                }
            }


            RankType soldierType = null;
            ImageView img = null;
            String number = "0";
            counter = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 6; j++) {

                    img = new ImageView(new Image(uiSettings.getGeneral().toUri().toURL().toString()));

                    switch (i) {
                        case 0:
                            switch (j) {
                                case 0:
                                    soldierType = RankType.Marshal;
                                    img = new ImageView(new Image(uiSettings.getB10().toUri().toURL().toString()));
                                    number = "1";
                                    break;
                                case 1:
                                    soldierType = RankType.General;
                                    img = new ImageView(new Image(uiSettings.getB9().toUri().toURL().toString()));
                                    number = "1";
                                    break;
                                case 2:
                                    soldierType = RankType.Colonel;
                                    img = new ImageView(new Image(uiSettings.getB8().toUri().toURL().toString()));
                                    number = "2";
                                    break;
                                case 3:
                                    soldierType = RankType.Major;
                                    img = new ImageView(new Image(uiSettings.getB7().toUri().toURL().toString()));
                                    number = "3";
                                    break;
                                case 4:
                                    soldierType = RankType.Captain;
                                    img = new ImageView(new Image(uiSettings.getB6().toUri().toURL().toString()));
                                    number = "4";
                                    break;
                                case 5:
                                    soldierType = RankType.Lieutenant;
                                    img = new ImageView(new Image(uiSettings.getB5().toUri().toURL().toString()));
                                    number = "4";
                                    break;
                            }
                            break;
                        case 1:
                            switch (j) {
                                case 0:
                                    soldierType = RankType.Sergeant;
                                    img = new ImageView(new Image(uiSettings.getB4().toUri().toURL().toString()));
                                    number = "4";
                                    break;
                                case 1:
                                    soldierType = RankType.Minor;
                                    img = new ImageView(new Image(uiSettings.getB3().toUri().toURL().toString()));
                                    number = "5";
                                    break;
                                case 2:
                                    soldierType = RankType.Scout;
                                    img = new ImageView(new Image(uiSettings.getB2().toUri().toURL().toString()));
                                    number = "8";
                                    break;
                                case 3:
                                    soldierType = RankType.Spy;
                                    img = new ImageView(new Image(uiSettings.getbS().toUri().toURL().toString()));
                                    number = "1";
                                    break;
                                case 4:
                                    soldierType = RankType.Flag;
                                    img = new ImageView(new Image(uiSettings.getbF().toUri().toURL().toString()));
                                    number = "1";
                                    break;
                                case 5:
                                    soldierType = RankType.Bomb;
                                    img = new ImageView(new Image(uiSettings.getbB().toUri().toURL().toString()));
                                    number = "6";
                                    break;
                            }
                            break;
                    }

                    BorderPaneRankType borderPane = new BorderPaneRankType();
                    borderPane.setRankType(soldierType);

                    Label name = new Label(soldierType.name());
                    borderPane.setTop(name);

                    Label count = new Label(number);
                    borderPane.setBottom(count);

                    borderPane.setCenter(img);
                    img.setFitWidth(70);
                    img.setFitHeight(70);
                    gridTake.add(borderPane, j, i);

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
        this.setTop(fileImg);
        top.setRight(imgInfo);
        setTop(top);
        top.setLeft(fileImg);
        top.setRight(imgInfo);
        top.setCenter(title);
        setCenter(gridPlace);
        setBottom(bottem);
        bottem.setCenter(gridTake);
        bottem.setRight(ready);
        title.setFont(Font.font("Cambria", 40));
        setMargin(gridPlace, new Insets(50, 0, 0, 0));
        setMargin(title, new Insets(50, 0, 0, 0));
        title.setAlignment(Pos.BASELINE_CENTER);
        gridPlace.setAlignment(Pos.BASELINE_CENTER);
        gridTake.setAlignment(Pos.BASELINE_CENTER);
        gridTake.setHgap(20);
        gridTake.setVgap(10);
    }

    // implementatie van de nodige
    // package-private Getters

    public void refresh(AvailableSoldiers availableSoldiers, RankType[][] setup) {
        int number = 0;


//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j <10 ; j++) {
//                RankType[i][j]
//            }
//
//        }
        for (BorderPaneRankType rank : army) {

            Label label = (Label) rank.getBottom();

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

                try {
                    ImageView imageView = (ImageView) position.getCenter();
                    if (rankType != null
                    ) {
                        switch (rankType) {
                            case Sergeant:
                                imageView.setImage(new Image(uiSettings.getB4().toUri().toURL().toString()));
                                break;
                            case General:
                                imageView.setImage(new Image(uiSettings.getB9().toUri().toURL().toString()));
                                break;
                            case Captain:
                                imageView.setImage(new Image(uiSettings.getB6().toUri().toURL().toString()));
                                break;
                            case Major:
                                imageView.setImage(new Image(uiSettings.getB7().toUri().toURL().toString()));
                                break;
                            case Marshal:
                                imageView.setImage(new Image(uiSettings.getB10().toUri().toURL().toString()));
                                break;
                            case Lieutenant:
                                imageView.setImage(new Image(uiSettings.getB5().toUri().toURL().toString()));
                                break;
                            case Minor:
                                imageView.setImage(new Image(uiSettings.getB3().toUri().toURL().toString()));
                                break;
                            case Scout:
                                imageView.setImage(new Image(uiSettings.getB2().toUri().toURL().toString()));
                                break;
                            case Spy:
                                imageView.setImage(new Image(uiSettings.getbS().toUri().toURL().toString()));
                                break;
                            case Bomb:
                                imageView.setImage(new Image(uiSettings.getbB().toUri().toURL().toString()));
                                break;
                            case Flag:
                                imageView.setImage(new Image(uiSettings.getbF().toUri().toURL().toString()));
                                break;
                            case Colonel:
                                imageView.setImage(new Image(uiSettings.getB8().toUri().toURL().toString()));
                                break;
                        }
                    }else imageView.setImage(new Image(uiSettings.getGrassImg().toUri().toURL().toString()));


                } catch (
                        MalformedURLException e) {
                    e.printStackTrace();
                }
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
}

