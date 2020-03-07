package Stratego.View.SetupScreen;

import Stratego.Model.Stratego;
import Stratego.View.GameScreen.GameScreenPresenter;
import Stratego.View.GameScreen.GameScreenView;
import Stratego.View.UISettings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.MalformedURLException;

public class SetupScreenPresenter {

    private Stratego model;
    private SetupScreenView view;
    UISettings uiSettings;
    private Stage stage;



    public SetupScreenPresenter(Stratego model, SetupScreenView view, UISettings uiSettings,Stage stage) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        this.stage = stage;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        // Koppelt event handlers (anon. inner klassen)
        view.getReady().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameScreenView gameView = new GameScreenView(uiSettings);
                GameScreenPresenter stpPresenter = new GameScreenPresenter(model, gameView, uiSettings, stage);
                view.getScene().setRoot(gameView);
                try {
                    gameView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                } catch (MalformedURLException ex) {
                    // // do nothing, if toURL-conversion fails, program can continue
                }
                stage.setMaximized(true);
                stpPresenter.windowsHandler();

            }
        });
        // aan de controls uit de view.
        // Event handlers: roepen methodes aan uit het
        // model en zorgen voor een update van de view.
    }

    private void updateView() {
        // Vult de view met data uit model
    }

    public void addWindowEventHandlers() {
        // Window event handlers (anon. inner klassen)
        // Koppeling via view.getScene().getWindow()
    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) { handleCloseEvent(event); }});
    }
    private void handleCloseEvent(Event event){
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