package Stratego.View.common;

import Stratego.Model.gamePlay.army.Army;
import Stratego.Model.gamePlay.army.ArmyColor;
import Stratego.Model.gamePlay.army.RankType;
import Stratego.View.UISettings;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

import java.net.MalformedURLException;

public class BaseView extends BorderPane {

    protected ArmyColor armyC;
    protected UISettings uiSettings;

    protected Image getArmyImage(RankType rankType,ArmyColor armyC) {

        String path = "";
        try {
            if (armyC == ArmyColor.Blue) {
                switch (rankType) {
                    case Sergeant:
                        path = uiSettings.getB4().toUri().toURL().toString();
                        break;
                    case General:
                        path = uiSettings.getB9().toUri().toURL().toString();
                        break;
                    case Captain:
                        path = uiSettings.getB6().toUri().toURL().toString();
                        break;
                    case Major:
                        path = uiSettings.getB7().toUri().toURL().toString();
                        break;
                    case Marshal:
                        path = uiSettings.getB10().toUri().toURL().toString();
                        break;
                    case Lieutenant:
                        path = uiSettings.getB5().toUri().toURL().toString();
                        break;
                    case Minor:
                        path = uiSettings.getB3().toUri().toURL().toString();
                        break;
                    case Scout:
                        path = uiSettings.getB2().toUri().toURL().toString();
                        break;
                    case Spy:
                        path = uiSettings.getbS().toUri().toURL().toString();
                        break;
                    case Bomb:
                        path = uiSettings.getbB().toUri().toURL().toString();
                        break;
                    case Flag:
                        path = uiSettings.getbF().toUri().toURL().toString();
                        break;
                    case Colonel:
                        path = uiSettings.getB8().toUri().toURL().toString();
                        break;

                }

            } else {
                switch (rankType) {
                    case Sergeant:
                        path = uiSettings.getR4().toUri().toURL().toString();
                        break;
                    case General:
                        path = uiSettings.getR9().toUri().toURL().toString();
                        break;
                    case Captain:
                        path = uiSettings.getR6().toUri().toURL().toString();
                        break;
                    case Major:
                        path = uiSettings.getR7().toUri().toURL().toString();
                        break;
                    case Marshal:
                        path = uiSettings.getR10().toUri().toURL().toString();
                        break;
                    case Lieutenant:
                        path = uiSettings.getR5().toUri().toURL().toString();
                        break;
                    case Minor:
                        path = uiSettings.getR3().toUri().toURL().toString();
                        break;
                    case Scout:
                        path = uiSettings.getR2().toUri().toURL().toString();
                        break;
                    case Spy:
                        path = uiSettings.getrS().toUri().toURL().toString();
                        break;
                    case Bomb:
                        path = uiSettings.getrB().toUri().toURL().toString();
                        break;
                    case Flag:
                        path = uiSettings.getrF().toUri().toURL().toString();
                        break;
                    case Colonel:
                        path = uiSettings.getR8().toUri().toURL().toString();
                        break;

                }
            }
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        }

        Image image = new Image(path);
        return image;

    }
}
