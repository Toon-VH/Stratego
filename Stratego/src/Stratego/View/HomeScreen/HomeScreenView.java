package Stratego.View.HomeScreen;

import Stratego.View.UISettings;
import com.sun.javafx.scene.layout.region.Margins;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HomeScreenView extends BorderPane {

    // private Node attributen (controls)
    private Button single_player;
    private Button classic_battle;
    private Button info;
    UISettings uiSettings;

    public HomeScreenView(UISettings uiSettings) {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        // Initialisatie van de Nodes
        this.single_player = new Button("Single Player");
        this.classic_battle = new Button("Classic Battle");
        this.info = new Button("Info");
    }

    private void layoutNodes() {
        // Layout van de Nodes
        BorderPane top = new BorderPane();
        Label title = new Label("Welcome to Stratego");
        setRight(classic_battle);
        setLeft(single_player);
        setTop(top);
        top.setCenter(title);
        top.setRight(info);

        // add… methodes (of set…)

        // Insets, padding, alignment, …
        single_player.setPrefSize(150, 100);
        classic_battle.setPrefSize(150, 100);
        title.setFont(Font.font("Cambria", 32));
        title.setAlignment(Pos.BASELINE_CENTER);
        info.setAlignment(Pos.BASELINE_RIGHT);
        BorderPane.setMargin(title, new Insets(120));
        BorderPane.setMargin(single_player, new Insets(0, 0, 0, 300));
        BorderPane.setMargin(classic_battle, new Insets(0, 300, 0, 0));


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

