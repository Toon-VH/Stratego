package Stratego.View.GameScreen;

import Stratego.Model.gamePlay.Stratego;
import Stratego.Model.gamePlay.army.Army;
import Stratego.Model.gamePlay.army.ArmyColor;
import Stratego.Model.gamePlay.army.Pawn;
import Stratego.Model.gamePlay.army.RankType;
import Stratego.Model.gamePlay.playground.Location;
import Stratego.Model.gamePlay.playground.PawnLocation;
import Stratego.View.UISettings;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameScreenPresenter {

    private Stratego model;
    private GameScreenView view;
    UISettings uiSettings;
    private Stage stage;


    public GameScreenPresenter(Stratego model, GameScreenView view, UISettings uiSettings, Stage stage) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        this.stage = stage;
        model.loadArmySetup(ArmyColor.Red, uiSettings.getRedS());
        model.loadArmySetup(ArmyColor.Blue, uiSettings.getBlueS());
        updateView();
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        // Koppelt event handlers (anon. inner klassen)
        // aan de controls uit de view.
        // Event handlers: roepen methodes aan uit het
        // model en zorgen voor een update van de view.
    }

    private void updateView() {
        // Vult de view met data uit model
        ArmyRank[][] setup = new ArmyRank[10][10];
        Location[][] locations = model.getPlayground().getLocations();
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (locations[x][y] instanceof PawnLocation) {
                    PawnLocation pawnLocation = (PawnLocation) locations[x][y];
                    if (pawnLocation != null) {
                        if (pawnLocation.getStandOn() != null) {
                            Pawn pawn = pawnLocation.getStandOn();
                            RankType rankType = pawn.getRank();
                            ArmyColor armyColor = pawn.getParent().getColor();
                            setup[x][y] = new ArmyRank(rankType, armyColor);
                        }

                    }
                }


            }
        }
        view.refresh(Turn.Red,setup);
    }

    public void addWindowEventHandlers() {
        // Window event handlers (anon. inner klassen)
        // Koppeling via view.getScene().getWindow()
    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                handleCloseEvent(event);
            }
        });
    }

    private void handleCloseEvent(Event event) {
        final Alert stopWindow = new Alert(Alert.AlertType.CONFIRMATION);
        stopWindow.setHeaderText("You're closing the application.");
        stopWindow.setContentText("Are you sure? Unsaved data may be lost.");
        stopWindow.setTitle("WARNING!");
        stopWindow.getButtonTypes().clear();
        ButtonType noButton = new ButtonType("No");
        ButtonType yesButton = new ButtonType("Yes");
        stopWindow.getButtonTypes().addAll(yesButton, noButton);
        stopWindow.showAndWait();
        if (stopWindow.getResult() == null || stopWindow.getResult().equals(noButton)) {
            event.consume();
        } else {
            view.getScene().getWindow().hide();
        }
    }
}