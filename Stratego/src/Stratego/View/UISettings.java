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
    private Path r10 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"red_10.png");
    private Path r9 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"red_09.png");
    private Path r8 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"red_08.png");
    private Path r7 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"red_07.png");
    private Path r6 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"red_06.png");
    private Path r5 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"red_05.png");
    private Path r4 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"red_04.png");
    private Path r3 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"red_03.png");
    private Path r2 = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"red_02.png");
    private Path rS = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"red_spy.png");
    private Path rF = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"red_flag.png");
    private Path rB = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"red_bomb.png");
    private Path redS = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"setups"+FILE_SEPARATOR+"redS.txt");
    private Path blueS = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"setups"+FILE_SEPARATOR+"blueS.txt");
    private Path saveImg  = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"save.png");
    private Path infoImg = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"info-01.png");
    private Path setupFileImg = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"file.png");
    private Path infoTextPath = Paths.get("Stratego"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"other"+FILE_SEPARATOR+"regels.pdf");




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

    public Path getR10() {
        return r10;
    }

    public Path getR9() {
        return r9;
    }

    public Path getR8() {
        return r8;
    }

    public Path getR7() {
        return r7;
    }

    public Path getR6() {
        return r6;
    }

    public Path getR5() {
        return r5;
    }

    public Path getR4() {
        return r4;
    }

    public Path getR3() {
        return r3;
    }

    public Path getR2() {
        return r2;
    }

    public Path getrS() {
        return rS;
    }

    public Path getrF() {
        return rF;
    }

    public Path getrB() {
        return rB;
    }

    public Path getSaveImg() {
        return saveImg;
    }

    public Path getRedS() {
        return redS;
    }

    public Path getBlueS() {
        return blueS;
    }

    public Path getInfoTextPath () {return this.infoTextPath;}

    public String getApplicationName () {return this.ApplicationName;}

}
