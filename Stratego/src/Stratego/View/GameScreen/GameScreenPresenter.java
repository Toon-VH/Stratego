package Stratego.View.GameScreen;

import Stratego.Model.gamePlay.Stratego;
import Stratego.Model.gamePlay.army.Army;
import Stratego.Model.gamePlay.army.ArmyColor;
import Stratego.Model.gamePlay.army.Pawn;
import Stratego.Model.gamePlay.army.RankType;
import Stratego.Model.gamePlay.player.Player;
import Stratego.Model.gamePlay.playground.Location;
import Stratego.Model.gamePlay.playground.PawnLocation;
import Stratego.Model.gamePlay.playground.Playground;
import Stratego.Model.gameSetup.StrategoSetup;
import Stratego.View.HomeScreen.HomeScreenPresenter;
import Stratego.View.HomeScreen.HomeScreenView;
import Stratego.View.SetupScreen.BorderPanePosition;
import Stratego.View.UISettings;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.text.View;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;
import java.util.Random;

public class GameScreenPresenter {

    private Stratego model;
    private GameScreenView view;
    UISettings uiSettings;
    private Stage stage;
    private boolean pauze;
    private SelectedSoldier selectedSoldier;
    private SelectedSoldier targetSoldier;


    public GameScreenPresenter(Stratego model, GameScreenView view, UISettings uiSettings, Stage stage) {
        selectedSoldier = null;
        targetSoldier = null;
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        this.stage = stage;
        model.loadArmySetup(ArmyColor.Red, uiSettings.getRedS());
        model.loadArmySetup(ArmyColor.Blue, uiSettings.getBlueS());
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        // Koppelt event handlers (anon. inner klassen)
        // aan de controls uit de view.
        // Event handlers: roepen methodes aan uit het
        // model en zorgen voor een update van de view.
        view.getImgInfo().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    File myFile = new File(uiSettings.getInfoTextPath().toString());
                    Desktop.getDesktop().open(myFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        BorderPanePosition[] positions = view.getPositions();
        for (BorderPanePosition position : positions) {
            position.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int x = position.getX();
                    int y = position.getY();

                    if (!pauze) {

                        PawnLocation pawnLocation = (PawnLocation) model.getPlayground().getLocations()[x][y];

                        if (pawnLocation.getStandOn() != null && selectedSoldier == null) {
                            //first soldier selected
                            if (pawnLocation.getStandOn().getParent().getColor() == model.getActivePlayer().getArmy().getColor()) {
                                takeOn(x, y, pawnLocation);
                            } else {
                                //wrong army do nothing
                            }
                        } else if (pawnLocation.getStandOn() != null && selectedSoldier != null) {
                            //two soldiers selected
                            if (pawnLocation.getStandOn() != selectedSoldier.getFrom().getStandOn()) {
                                //two diffrend soldiers
                                if (pawnLocation.getStandOn().getParent().getColor() == model.getActivePlayer().getArmy().getColor()) {
                                    //new soldier same army
                                    takeOn(x, y, pawnLocation);
                                } else {
                                    //other army
                                    placeOn(x, y, pawnLocation);
                                }
                            } else {
                                //same soldier selected,do nothing!
                            }
                        } else if (pawnLocation.getStandOn() == null && selectedSoldier == null) {
                            //only grass selected,do nothing
                        } else {
                            //grass selected and soldier
                            placeOn(x, y, pawnLocation);
                        }
                        updateView();
                    }
                }
            });

            view.getBlueButton().

                    setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if (pauze) {
                                play();
                            }
                        }
                    });
            view.getRedButton().

                    setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if (pauze) {
                                play();
                            }
                        }
                    });
        }
    }

    private void takeOn(int x, int y, PawnLocation pawnLocation) {
        selectedSoldier = new SelectedSoldier();
        selectedSoldier.setY(y);
        selectedSoldier.setX(x);
        selectedSoldier.setSelected(pawnLocation);
        try {
            model.getActivePlayer().take(pawnLocation, model.getPlayground(), model);
        } catch (Exception e) {
            selectedSoldier = null;
        }
    }

    private void placeOn(int x, int y, PawnLocation pawnLocation) {
        try {
            model.getActivePlayer().place(pawnLocation, model.getPlayground());
            if (model.getPlayground().isFlagCaptured()) {
                String string = "";
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if (model.getActivePlayer().getArmy().getColor() == ArmyColor.Red) {
                    string = "Red is the WINNER!";
                } else string = "Blue is the WINNER!";
                alert.setTitle("Victory!");
                alert.setHeaderText(string);
                alert.setContentText("Choose your option.");
                ButtonType exit = new ButtonType("Exit");
                ButtonType replay = new ButtonType("Replay");
                alert.getButtonTypes().setAll(replay, exit);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == exit) {
                    Platform.exit();
                } else if (result.get() == replay) {
                    Stratego newModel = new Stratego("Speler1", "Speler2");
                    StrategoSetup newStrategoSetup = new StrategoSetup();
                    HomeScreenView msView = new HomeScreenView(uiSettings);
                    HomeScreenPresenter msPresenter = new HomeScreenPresenter(newModel, newStrategoSetup, msView, uiSettings, stage);
                    view.getScene().setRoot(msView);
                    try {
                        msView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                    } catch (MalformedURLException ex) {
                        // // do nothing, if toURL-conversion fails, program can continue
                    }
                    stage.setMaximized(true);
                    msPresenter.windowsHandler();
                }
            }

            targetSoldier = new SelectedSoldier(x, y, pawnLocation);

            nextRun();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You made a mistake!");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void nextRun() {
        selectedSoldier = null;
        pauze = true;
        targetSoldier = null;
    }

    private void play() {
        model.switchPlayer();
        pauze = false;
        updateView();
    }

    private void updateView() {
        // Vult de view met data uit model
        LocationInfo[][] setup = new LocationInfo[10][10];
        Location[][] locations = model.getPlayground().getLocations();

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                RankType rankType = null;
                ArmyColor armyColor = null;
                if (locations[x][y] instanceof PawnLocation) {
                    PawnLocation pawnLocation = (PawnLocation) locations[x][y];

                    if (pawnLocation.getStandOn() != null) {
                        Pawn pawn = pawnLocation.getStandOn();
                        rankType = pawn.getRank();
                        armyColor = pawn.getParent().getColor();
                    }
                    setup[x][y] = new LocationInfo(rankType, armyColor);
                    if (pawnLocation.isInRange()) {
                        setup[x][y].setLoctionInRange(true);
                    }
                }
            }
        }
        Turn turn = null;
        if (pauze) {
            turn = Turn.None;
        } else {
            if (model.getActivePlayer().getArmy().getColor() == ArmyColor.Red) {
                turn = Turn.Red;
            } else {
                turn = Turn.Blue;
            }
        }

        Turn nextPlayer;
        if (pauze) {
            if (model.getActivePlayer().getArmy().getColor() == ArmyColor.Blue) {
                nextPlayer = Turn.Red;
            } else
                nextPlayer = Turn.Blue;
        } else nextPlayer = Turn.None;
        view.refresh(turn, setup, selectedSoldier, nextPlayer,targetSoldier);
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