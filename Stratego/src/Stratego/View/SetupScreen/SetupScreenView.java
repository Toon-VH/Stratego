package Stratego.View.SetupScreen;

import Stratego.View.UISettings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.net.MalformedURLException;
import java.nio.file.Files;


public class SetupScreenView extends BorderPane {

    // private Node attributen (controls)

    private Button ready;
    private Button info;
    private UISettings uiSettings;

    public SetupScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        // Initialisatie van de Nodes
        this.ready = new Button("Ready");
        this.info = new Button("Info");
    }

    private void layoutNodes() {
        int ImageSize = uiSettings.getLowestRes() / 5;
        ImageView fileImg = null;
        BorderPane top = new BorderPane();
        Label title = new Label("Setup your Army");
        GridPane gridPlace = new GridPane();
        GridPane gridTake = new GridPane();
        gridPlace.setHgap(15);
        gridPlace.setVgap(15);

        if (Files.exists(uiSettings.getSetupFileImg())) {
            try {
                fileImg = new ImageView(new Image(uiSettings.getSetupFileImg().toUri().toURL().toString()));
                fileImg.setPreserveRatio(true);
                fileImg.setFitWidth(70);
                fileImg.setSmooth(true);
                this.setTop(fileImg);
            } catch (MalformedURLException ex) {
                // do nothing, if toURL-conversion fails, program can continue
            }
        } else { // do nothing, if StartScreenImage is not available, program can continue
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    ImageView img;
                    if (i == 3 && (j == 2 || j == 3 || j == 6 || j == 7)) {
                        img = new ImageView(new Image(uiSettings.getWater1().toUri().toURL().toString()));
                    } else{
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
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {


                try {
                    ImageView takeImg = new ImageView(new Image(uiSettings.getGeneral().toUri().toURL().toString()));
                    BorderPane borderPane = new BorderPane();
                    borderPane.setTop(new Label("Generaal"));
                    borderPane.setCenter(takeImg);
                    takeImg.setFitWidth(70);
                    takeImg.setFitHeight(70);
                    borderPane.setBottom(new Label("5"));
                    gridTake.add(borderPane, j, i);


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }


            }
        }
        setTop(top);
        top.setLeft(fileImg);
        top.setRight(info);
        top.setCenter(title);
        setCenter(gridPlace);
        setBottom(gridTake);
        title.setFont(Font.font("Cambria", 32));
        title.setAlignment(Pos.BASELINE_CENTER);
        gridPlace.setAlignment(Pos.BASELINE_CENTER);
        gridTake.setAlignment(Pos.BASELINE_CENTER);
    }
    // implementatie van de nodige
    // package-private Getters
}

