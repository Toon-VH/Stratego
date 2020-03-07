package Stratego.View.GameScreen;

import Stratego.View.UISettings;
import javafx.geometry.Insets;
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


public class GameScreenView extends BorderPane {

    // private Node attributen (controls)

    private Button ready;
    private UISettings uiSettings;

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
        BorderPane top = new BorderPane();
        Label title = new Label("Red its youre turn");
        GridPane gridPlace = new GridPane();
        gridPlace.setHgap(15);
        gridPlace.setVgap(15);

        ImageView imgInfo = null;
        try {
            imgInfo = new ImageView(new Image(uiSettings.getInfoImg().toUri().toURL().toString()));
            ;
            top.setRight(imgInfo);
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

        setTop(top);
        top.setLeft(fileImg);
        top.setRight(imgInfo);
        top.setCenter(title);
        setCenter(gridPlace);
        setMargin(gridPlace, new Insets(100, 0, 0, 0));
        title.setFont(Font.font("Cambria", 48));
        setMargin(title, new Insets(100, 0, 0, 0));
        title.setAlignment(Pos.BASELINE_CENTER);
        gridPlace.setAlignment(Pos.BASELINE_CENTER);
    }
    // implementatie van de nodige
    // package-private Getters


    public Button getReady() {
        return ready;
    }
}

