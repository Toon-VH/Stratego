package Stratego.View.SetupScreen;

import Stratego.View.UISettings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

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
        GridPane grid = new GridPane();
//        for (int i = 0; i <= 40 ; i++) {
//            grid.add(new ImageView(new Image( )));
//        }

        if (Files.exists(uiSettings.getSetupFileImg())) {
            try {
                fileImg = new ImageView(new Image(uiSettings.getSetupFileImg().toUri().toURL().toString()));
                fileImg.setPreserveRatio(true);
                fileImg.setFitWidth(50);
                fileImg.setSmooth(true);
                this.setTop(fileImg);
            } catch (MalformedURLException ex) {
                // do nothing, if toURL-conversion fails, program can continue
            }
        } else { // do nothing, if StartScreenImage is not available, program can continue
        }
        setCenter(grid);
        setTop(top);
        top.setLeft(fileImg);
        top.setRight(info);
    }
    // implementatie van de nodige
    // package-private Getters
}

