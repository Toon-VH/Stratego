package Stratego;

import Stratego.Model.gamePlay.Stratego;
import Stratego.Model.gamePlay.army.ArmyColor;
import Stratego.Model.gamePlay.army.Pawn;
import Stratego.Model.gamePlay.player.Player;
import Stratego.Model.gamePlay.playground.Location;
import Stratego.Model.gamePlay.playground.PawnLocation;
import Stratego.Model.gamePlay.playground.Playground;
import Stratego.Model.gameSetup.StrategoSetup;
import Stratego.View.StartScreen.StartScreenPresenter;
import Stratego.View.StartScreen.StartScreenView;
import Stratego.View.UISettings;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class StrategoMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        UISettings uiSettings = new UISettings();
        Stratego model = new Stratego("Speler1","Speler2");
        StrategoSetup strategoSetup = new StrategoSetup();
        StartScreenView view = new StartScreenView(uiSettings);
        StartScreenPresenter presenter = new StartScreenPresenter(model,strategoSetup, view, uiSettings, primaryStage);
        Scene scene = new Scene(view);
        if (uiSettings.styleSheetAvailable()){
            try {
                scene.getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
            } catch (MalformedURLException ex) {
                // do nothing, if toURL-conversion fails, program can continue
            }
        }
        primaryStage.setScene(scene);
        primaryStage.setHeight(uiSettings.getLowestRes() / 4);
        primaryStage.setWidth(uiSettings.getLowestRes() / 4);
        primaryStage.setTitle(uiSettings.getApplicationName());
        if (Files.exists(uiSettings.getApplicationIconPath())) {
             try {
                 primaryStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
             }
             catch (MalformedURLException ex) {
                 // do nothing, if toURL-conversion fails, program can continue
             }
        } else { // do nothing, if ApplicationIcon is not available, program can continue
        }
        presenter.windowsHandler();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);


        boolean finish = false;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("                                                                                                                                              \n" +
                "                                                                                                                                                      \n" +
                "                                                                                                                                                      \n" +
                "                                                                                                                                                      \n" +
                "                                                                                                                                                      \n" +
                "                      .lxxxkkkxxxkkkx,                                                                                                                \n" +
                "                    .:OXXXXNXXXXXXXXd.  ,dxxx:                                  ;dodd,                                                                \n" +
                "                   ,xKXX00000000KXX0,   :KXN0,                                  oXXNO'                                                                \n" +
                "                 .lKXXNO,........,ll. ,cdKXX0occ' .:c:c;',:c;.  .;::::::::;'..,lkXXNOl::. .:::::::::::'    .:ccccccccc' 'cc::::::::::'                \n" +
                "                 .kXKXXx.            .oOO0KXK00Xd..dXXX0xkK0: .ckKXK0O0KKXo'..d00KKXK0KXo ,OKKKK000KXXO' .cOKXK000KXNk. lXXXXXXXXKKX0: .              \n" +
                "                 .xKKKKk;.            ..cO0Xk,.;;. :0KKk,.',..oOOKx,..l0K0;   ..o00Xx,.;, .x00Kd'.,x00k'.lOKXk;..cOKKl  l0KXXx,:O00XO'                \n" +
                "                 .dKK0OOxolllllll:,.    :O0Kx.     ;O0Kx.    'xO0Kl   cOK0;     cOOXd.    .x000l..:dOko..x0KXo.  l0KKc  l00XK: .x00Xk'                \n" +
                "                  'd000OOkkkkkkkkkkdc.  :OOKx.     :kO0x'    'xkO0c  .ck0O;     :dx0o.    'dkOOolddc;.  .dO0Ko. .lO0Kc  l00K0: .x00Kk'                \n" +
                "                    ,coxkkkkkkkkxddxdl. :ddkd.     ;odxo.    'oodkc  .cdxx;    .:clxl.    .odxxl:'.     .ldxkc. .:dxk: .lxkOO; .oxxOx'                \n" +
                "                       .......':lllddl. :ooxd'     ;odkx,    'dddkl. .okkk;    .cooOd.    'dxkOc.       .dxkOo. .cxkOc .lkkOx; .looxd'                \n" +
                "                               .cdoxOd. ;xxkkl.    ,dkOOd;.  'xOkxdc:okOOkc.    ckxOOc.   .xO0Ox:..     .oOOkkocokO0Kc  l000Oo;cdxO0k'                \n" +
                "                    ..'''..    .cxxk0x.  ,okOOko,   .:xOOOl.  'lk0OOkdx0K0OOo'  .;dO00ko'  ;x0KK0Oxddo,  .:xOO0K0O0KXc  .ok0K000KKKk:                 \n" +
                "                 .;codddddol:'..cxk00l.    'ldl'      .;c,      .:dd,  ,oxl;'      ,oxl'     ':lxkOOx,     .;od:'l0XXc    .,cx00dc'.                  \n" +
                "               .:dxkOOO0OOkkkkdcd00x;                                                             ..       ...   lKKKl.       ..                      \n" +
                "               .'...',;cok0K000K0d,                                                                ....':lxkkdoc,c0KK0o'                              \n" +
                "                         .'cx0Oo'                                                                  ,ox0KK0OOKXXXK0KXOl.                               \n" +
                "                            .'.                                                                      .','....;lx0Kx:.                                 \n" +
                "                                                                                                                ..                                    \n" +
                "                                                                                                                                                     ");
        System.out.println("Welcome to Stratego \n Enter RedArmy name: ");
        String nameP1 = keyboard.nextLine();
        System.out.println("Enter BlueArmy name: ");
        String nameP2 = keyboard.nextLine();

        Stratego game = new Stratego(nameP1, nameP2);
        Playground playground = game.getPlayground();

        Path setupfile = Paths.get("Stratego/resources/setups/setupA.txt");

        System.out.println(Files.exists(setupfile));
        System.out.println(setupfile.toAbsolutePath().toString());

        game.loadArmySetup(ArmyColor.Red, setupfile);
        game.loadArmySetup(ArmyColor.Blue, setupfile);

        switch (game.getActivePlayer().getArmy().getColor()) {
            case Red:
                System.out.println("Ready to play, Red Player may start");
                break;
            case Blue:
                System.out.println("Ready to play, Blue Player may start");
                break;
        }


        while (!playground.isFlagCaptured()) {
            try {
                //TODO: gevecht laten zien!
                printPlayground(playground, game.getActivePlayer());
                String moveCommand;
                System.out.println("Please enter pawn location you want to move x,y");
                String moveFrom = keyboard.nextLine();
                PawnLocation locationFrom = (PawnLocation) getLocationFromCoordinates(moveFrom, playground);
                game.getActivePlayer().take(locationFrom, playground,game);
                printPlayground(playground, game.getActivePlayer());
                System.out.println("Please enter pawn location you want to move to x,y");
                String moveTo = keyboard.nextLine();
                PawnLocation locationTo = (PawnLocation) getLocationFromCoordinates(moveTo, playground);
                game.getActivePlayer().place(locationTo, playground);
                printPlayground(playground, game.getActivePlayer());
                Thread.sleep(3000);
                for (int i = 0; i < 100; i++) {
                    System.out.println();
                }
                System.out.println("Next player!");
                Thread.sleep(3000);
                game.switchPlayer();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    private static Location getLocationFromCoordinates(String command, Playground playground) {
        String[] commandSplit = command.split(",");
        int x = Integer.parseInt(commandSplit[0]);
        int y = Integer.parseInt(commandSplit[1]);
        return playground.getLocations()[x][y];
    }

    private static void printPlayground(Playground playground, Player player) {
        Location[][] locations = playground.getLocations();
        String result = "";
        Pawn pawn = null;
        for (int y = 11; y >= 0; y--) {
            for (int x = 0; x <= 11; x++) {
                if (x == 0) {
                    if (y >= 2) {
                        result += y - 2;
                    } else result += "";

                } else if (x == 1 && y > 1) {
                    result += "▕";
                } else if (y == 1) {
                    result += "▔";
                } else if (y == 0) {
                    if (x > 1) {
                        result += x - 2;
                    } else result += "";
                } else if (locations[x - 2][y - 2] instanceof PawnLocation) {
                    if (((PawnLocation) locations[x - 2][y - 2]).isInRange()) {
                        result += "\u001B[33m";
                    } else if (((PawnLocation) locations[x - 2][y - 2]).getStandOn() == player.getActivePawn() && player.getActivePawn() != null) {
                        result += "\u001B[35m";
                    }
                    pawn = ((PawnLocation) locations[x - 2][y - 2]).getStandOn();
                    if (pawn != null) {
                        if (pawn.getParent().getColor() != player.getArmy().getColor()) {

                            if (pawn.getParent().getColor() == ArmyColor.Red){
                                if (((PawnLocation) locations[x - 2][y - 2]).isInRange()) {
                                    result += "\u001B[33m";
                                }else result +="\u001B[31m";
                                result += "X";
                            }else if (pawn.getParent().getColor() == ArmyColor.Blue){
                                if (((PawnLocation) locations[x - 2][y - 2]).isInRange()) {
                                    result += "\u001B[33m";
                                }else result +="\u001B[34m";
                                result += "X";
                            }

                        }
                        else {
                            switch (pawn.getRank()) {
                                case Marshal:
                                    result += "10";
                                    break;
                                case General:
                                    result += "9";
                                    break;
                                case Colonel:
                                    result += "8";
                                    break;
                                case Major:
                                    result += "7";
                                    break;
                                case Captain:
                                    result += "6";
                                    break;
                                case Lieutenant:
                                    result += "5";
                                    break;
                                case Sergeant:
                                    result += "4";
                                    break;
                                case Minor:
                                    result += "3";
                                    break;
                                case Scout:
                                    result += "2";
                                    break;
                                case Spy:
                                    result += "1";
                                    break;
                                case Bomb:
                                    result += "B";
                                    break;
                                case Flag:
                                    result += "F";
                            }
                        }
                    } else result += "0";
                    result += "\u001B[37m";
                } else if (locations[x - 2][y - 2] != null) {
                    result += "~";
                }
                result += "\t";
            }
            result += "\n";
        }

        System.out.println(result);
    }
}
