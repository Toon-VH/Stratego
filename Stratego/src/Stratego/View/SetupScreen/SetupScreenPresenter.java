package Stratego.View.SetupScreen;

import Stratego.Model.gameAI.AI;
import Stratego.Model.gamePlay.Stratego;
import Stratego.Model.gamePlay.army.ArmyColor;
import Stratego.Model.gamePlay.army.RankType;
import Stratego.Model.gameSetup.StrategoSetup;
import Stratego.View.GameScreen.GameScreenPresenter;
import Stratego.View.GameScreen.GameScreenView;
import Stratego.View.UISettings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SetupScreenPresenter {

    private Stratego strategoModel;
    private StrategoSetup strategoSetup;
    private AI ai;
    private SetupScreenView view;
    private UISettings uiSettings;
    private Stage stage;
    private RankType selected;
    private ArmyColor armyC;
    private boolean aIPlayer;

    public SetupScreenPresenter(Stratego strategoModel, StrategoSetup strategoSetup, AI ai, SetupScreenView view, UISettings uiSettings, Stage stage, boolean aIPlayer) {
        this.ai = ai;
        this.aIPlayer = aIPlayer;
        this.armyC = ArmyColor.Blue;
        this.strategoSetup = strategoSetup;
        this.strategoModel = strategoModel;
        this.view = view;
        this.uiSettings = uiSettings;
        this.stage = stage;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        // Koppelt event handlers (anon. inner klassen)
        BorderPaneRankType[] army = view.getArmy();
        for (BorderPaneRankType rankType : army) {
            rankType.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {
                    selected = rankType.getRankType();
                }
            });
        }
        BorderPanePosition[] positions = view.getPositions();
        for (BorderPanePosition position : positions) {
            position.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int x = position.getX();
                    int y = position.getY();
                    strategoSetup.SetSoldier(selected, x, y);
                    updateView();

                }
            });
        }

        view.getFileImg().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Chose your setup file!");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Textfiles", "*.txt"));
                File selectedFile = fileChooser.showOpenDialog(stage);
                if ((selectedFile != null && Files.isReadable(Paths.get(selectedFile.toURI())))) {
                    // implementeren wegschrijven model-gegevens vb:
                    strategoSetup.loadConfiguration(selectedFile);
                    updateView();
                } else {
                    Alert errorWindow = new Alert(Alert.AlertType.ERROR);
                    errorWindow.setHeaderText("Problem with selected file");
                    errorWindow.setContentText("File is not Readable");
                    errorWindow.showAndWait();
                }


            }
        });

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

        view.getReady().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {

                if (strategoSetup.allSet()) {

                    strategoSetup.saveConfig(uiSettings.getBlueS());

                    if (armyC == ArmyColor.Red) {
                        strategoSetup.saveConfig(uiSettings.getRedS());
                        openGameScreen();

                    } else {
                        if (aIPlayer) {
                            ai.saveAIConfig(uiSettings.getRedS());
                            openGameScreen();

                        } else {
                            armyC = ArmyColor.Red;
                            strategoSetup.clearSetup();
                            updateView();
                        }

                    }
                } else {
                    Alert errorWindow = new Alert(Alert.AlertType.ERROR);
                    errorWindow.setHeaderText("You diden't complete your setup! ");
                    errorWindow.setContentText("please take a look.");
                    errorWindow.showAndWait();
                }
            }

        });
        // aan de controls uit de view.
        // Event handlers: roepen methodes aan uit het
        // model en zorgen voor een update van de view.
    }

    private void openGameScreen() {
        GameScreenView gameView = new GameScreenView(uiSettings);
        GameScreenPresenter stpPresenter = new GameScreenPresenter(strategoModel, gameView,ai, uiSettings, stage ,aIPlayer);
        view.getScene().setRoot(gameView);
        try {
            gameView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
        } catch (MalformedURLException ex) {
            // // do nothing, if toURL-conversion fails, program can continue
        }
        stage.setMaximized(true);
        stpPresenter.windowsHandler();

    }

    private void updateView() {
        // Vult de view met data uit model
        view.refresh(strategoSetup.getAvailableSoldiers(), strategoSetup.getSetup(), armyC);
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