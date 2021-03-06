package Stratego.View.StartScreen;


import Stratego.Model.gameAI.AI;
import Stratego.Model.gamePlay.Stratego;
import Stratego.Model.gameSetup.StrategoSetup;
import Stratego.View.GameScreen.GameScreenPresenter;
import Stratego.View.GameScreen.GameScreenView;
import Stratego.View.HomeScreen.HomeScreenPresenter;
import Stratego.View.HomeScreen.HomeScreenView;
import Stratego.View.UISettings;
import javafx.event.*;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.MalformedURLException;

public class StartScreenPresenter {

    private Stratego model;
    private StrategoSetup strategoSetup;
    private AI ai;
    private StartScreenView view;
    private UISettings uiSettings;
    private Stage stage;

    public StartScreenPresenter(Stratego model, StrategoSetup strategoSetup,AI ai, StartScreenView view, UISettings uiSettings, Stage stage) {
        this.ai = ai;
        this.model = model;
        this.strategoSetup = strategoSetup;
        this.view = view;
        this.uiSettings = uiSettings;
        this.stage = stage;
        updateView();
        EventHandlers();
    }

    private void updateView() {
    }

    private void EventHandlers() {
        view.getTransition().setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeScreenView msView = new HomeScreenView(uiSettings);
                HomeScreenPresenter msPresenter = new HomeScreenPresenter(model,strategoSetup, ai, msView, uiSettings,stage);
                view.getScene().setRoot(msView);
                try {
                    msView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                } catch (MalformedURLException ex) {
                    // // do nothing, if toURL-conversion fails, program can continue
                }
                stage.setMaximized(true);
                msPresenter.windowsHandler();
            }
        });
    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
             @Override
             public void handle(WindowEvent event) {
                 final Alert stopWindow = new Alert(Alert.AlertType.ERROR);
                 stopWindow.setHeaderText("You can not yet close the application.");
                 stopWindow.setContentText("Try again after the program has started");
                 stopWindow.showAndWait();
                 event.consume(); } });
    }
}
