package Stratego.View.HomeScreen;

import Stratego.View.UISettings;
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

public class HomeScreenView extends BorderPane {

    // private Node attributen (controls)
    private Button single_player;
    private Button classic_battle;
    private ImageView imgInfo;
    private ImageView saveImg;
    private UISettings uiSettings;

    public HomeScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        this.initialiseNodes();
        this.layoutNodes();

    }

    private void initialiseNodes() {
        // Initialisatie van de Nodes
        try {
            this.saveImg = new ImageView(new Image(uiSettings.getSaveImg().toUri().toURL().toString()));
            this.imgInfo = new ImageView(new Image(uiSettings.getInfoImg().toUri().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.single_player = new Button("Single Player");
        this.classic_battle = new Button("Classic Battle");

    }

    private void layoutNodes() {
        // Layout van de Nodes
        BorderPane top = new BorderPane();
        ImageView banner = new ImageView();
        HBox center = new HBox();



        try {
            banner.setImage(new Image(uiSettings.getStartScreenImagePath().toUri().toURL().toString()));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        top.setLeft(saveImg);
        top.setRight(imgInfo);
        imgInfo.setFitWidth(70);
        imgInfo.setFitHeight(70);
        saveImg.setFitWidth(70);
        saveImg.setFitHeight(70);
        center.getChildren().addAll(single_player, classic_battle);
        setCenter(center);
        setTop(top);
        top.setCenter(banner);
        single_player.setPrefSize(300, 200);
        classic_battle.setPrefSize(300, 200);
        center.setAlignment(Pos.CENTER);
        center.setSpacing(600);
        this.setPadding(new Insets(20));


    }



    public Button getSingle_player() {
        return single_player;
    }

    public Button getClassic_battle() {
        return classic_battle;
    }

    public ImageView getImgInfo() {
        return imgInfo;
    }
}

