package Stratego.View;

import javafx.stage.Screen;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UISettings {

    private int resX;
    private int resY;
    private int insetsMargin;
    public static final char FILE_SEPARATOR = System.getProperties().getProperty("file.separator").charAt(0);
    private String ApplicationName;
    private String homeDir;
    private String defaultCss = "themes02.css";
    private Path styleSheetPath = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"stylesheets"+FILE_SEPARATOR+defaultCss);
    private Path AboutImagePath = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"AboutImage.png");
    private Path applicationIconPath = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"Logo.png");
    private Path startScreenImagePath = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"StartScreenImage.png");
    private Path General = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"GeneraalB.png");
    private Path grassImg = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"grass1.png");
    private Path water1 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"water1.png");
    private Path b10 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"blue_10.png");
    private Path b9 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"blue_09.png");
    private Path b8 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"blue_08.png");
    private Path b7 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"blue_07.png");
    private Path b6 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"blue_06.png");
    private Path b5 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"blue_05.png");
    private Path b4 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"blue_04.png");
    private Path b3 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"blue_03.png");
    private Path b2 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"blue_02.png");
    private Path bS = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"blue_spy.png");
    private Path bF = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"blue_flag.png");
    private Path bB = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"blue_bomb.png");
    private Path infoImg = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"info-01.png");
    private Path setupFileImg = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"file.png");
    private Path infoTextPath = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"other"+FILE_SEPARATOR+"info.txt");



    public UISettings() {
        this.resX= (int) Screen.getPrimary().getVisualBounds().getWidth();
        this.resY = (int) Screen.getPrimary().getVisualBounds().getHeight();
        this.insetsMargin = this.getLowestRes()/100;
        this.homeDir = System.getProperties().getProperty("user.dir");
        this.ApplicationName = "Stratego";
    };

    public int getResX () {return this.resX;}

    public int getResY () {return this.resY;}

    public int getInsetsMargin () {return this.insetsMargin;}

    public int getLowestRes () {return (resX>resY?resX:resY);}

    public boolean styleSheetAvailable (){return Files.exists(styleSheetPath);}

    public Path getStyleSheetPath () {return this.styleSheetPath;}

        public void setStyleSheetPath (Path styleSheetPath) {this.styleSheetPath = styleSheetPath;}

    public String getHomeDir () {return this.homeDir;}

    public Path getApplicationIconPath () {return this.applicationIconPath;}

    public Path getStartScreenImagePath () {return this.startScreenImagePath;}

    public Path getAboutImagePath () {return this.AboutImagePath;}

    public Path getSetupFileImg() {
        return setupFileImg;
    }

    public Path getGrassImg() {
        return grassImg;
    }

    public Path getGeneral() {
        return General;
    }

    public Path getWater1() {
        return water1;
    }

    public Path getInfoImg() {
        return infoImg;
    }

    public Path getB10() {
        return b10;
    }

    public Path getB9() {
        return b9;
    }

    public Path getB8() {
        return b8;
    }

    public Path getB7() {
        return b7;
    }

    public Path getB6() {
        return b6;
    }

    public Path getB5() {
        return b5;
    }

    public Path getB4() {
        return b4;
    }

    public Path getB3() {
        return b3;
    }

    public Path getB2() {
        return b2;
    }

    public Path getbS() {
        return bS;
    }

    public Path getbF() {
        return bF;
    }

    public Path getbB() {
        return bB;
    }

    public Path getInfoTextPath () {return this.infoTextPath;}

    public String getApplicationName () {return this.ApplicationName;}

}
