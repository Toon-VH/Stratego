package Stratego.View.HomeScreen;

import Stratego.View.UISettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.awt.*;
import java.net.MalformedURLException;

public class HomeScreenView extends BorderPane {

    // private Node attributen (controls)
    private Button single_player;
    private Button classic_battle;
    UISettings uiSettings;

    public HomeScreenView(UISettings uiSettings) {
        this.uiSettings=uiSettings;
        this.initialiseNodes();
        this.layoutNodes();

    }

    private void initialiseNodes() {
        // Initialisatie van de Nodes
        this.single_player = new Button("Single Player");
        this.classic_battle = new Button("Classic Battle");

    }

    private void layoutNodes() {
        // Layout van de Nodes
        BorderPane top = new BorderPane();
        Label title = new Label("Welcome to Stratego");
        HBox center = new HBox();
        ImageView imgInfo = null;
        try {
            imgInfo = new ImageView(new Image(uiSettings.getInfoImg().toUri().toURL().toString()));;
            top.setRight(imgInfo);
            imgInfo.setFitWidth(70);
            imgInfo.setFitHeight(70);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        center.getChildren().addAll(single_player, classic_battle);
        setCenter(center);
        setTop(top);
        top.setCenter(title);


        // add… methodes (of set…)

        // Insets, padding, alignment, …
        single_player.setPrefSize(300, 200);
        classic_battle.setPrefSize(300, 200);
        title.setFont(Font.font("Cambria", 48));
        title.setAlignment(Pos.BASELINE_CENTER);
        center.setAlignment(Pos.CENTER);
        center.setSpacing(600);
        setMargin(title, new Insets(100, 0, 0, 0));


        this.setPadding(new Insets(20));


    }
    // implementatie van de nodige

    public Button getSingle_player() {
        return single_player;
    }

    public Button getClassic_battle() {
        return classic_battle;
    }
}

