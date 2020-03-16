package Stratego.View.HomeScreen;

import Stratego.Model.gamePlay.Stratego;
import Stratego.Model.gameSetup.StrategoSetup;
import Stratego.View.SetupScreen.SetupScreenPresenter;
import Stratego.View.SetupScreen.SetupScreenView;
import Stratego.View.UISettings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class HomeScreenPresenter {

    private Stratego model;
    private StrategoSetup strategoSetup;
    private HomeScreenView view;
    UISettings uiSettings;
    private Stage stage;

    public HomeScreenPresenter(Stratego model, StrategoSetup strategoSetup, HomeScreenView view, UISettings uiSettings, Stage stage) {
        this.strategoSetup = strategoSetup;
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        this.addEventHandlers();
        this.updateView();
        this.stage = stage;
    }

    private void addEventHandlers() {
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
        view.getClassic_battle().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SetupScreenView stpView = new SetupScreenView(uiSettings);
                SetupScreenPresenter stpPresenter = new SetupScreenPresenter(model,strategoSetup, stpView, uiSettings, stage);
                view.getScene().setRoot(stpView);
                try {
                    stpView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                } catch (MalformedURLException ex) {
                    // // do nothing, if toURL-conversion fails, program can continue
                }
                stage.setMaximized(true);
                stpPresenter.windowsHandler();

            }
        });
        view.getSingle_player().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SetupScreenView stpView = new SetupScreenView(uiSettings);
                SetupScreenPresenter stpPresenter = new SetupScreenPresenter(model,strategoSetup, stpView, uiSettings, stage);
                view.getScene().setRoot(stpView);
                try {
                    stpView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                } catch (MalformedURLException ex) {
                    // // do nothing, if toURL-conversion fails, program can continue
                }
                stage.setMaximized(true);
                stpPresenter.windowsHandler();
            }
        });
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
