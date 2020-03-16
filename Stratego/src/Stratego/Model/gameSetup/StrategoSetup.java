package Stratego.Model.gameSetup;

import Stratego.Model.gamePlay.army.RankType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StrategoSetup {

    RankType[][] setup;

    public StrategoSetup() {
        this.setup = new RankType[10][4];
    }

    public void SetSoldier(RankType rankType, int x, int y) {
        //aantal van type toegelaten
        RankType target = setup[x][y];
        setup[x][y] = null;
        if (target != rankType) {
            AvailableSoldiers availableSoldiers = getAvailableSoldiers();

            availableSoldiers.remove(rankType);

            if (!availableSoldiers.isValid()) {
                return;
                //exeption
            }

            setup[x][y] = rankType;
        }
    }

    public AvailableSoldiers getAvailableSoldiers() {
        AvailableSoldiers availableSoldiers = new AvailableSoldiers();
        for (RankType[] solderrow : setup) {
            for (RankType soldier : solderrow) {
                availableSoldiers.remove(soldier);
            }
        }
        return availableSoldiers;
    }

    public void loadConfiguration(File file) {
        int indexX = 0;
        int indexY = 0;
        try (Stream<String> stream = Files.lines(file.toPath())) {
            for (String l : stream.collect(Collectors.toList())) {

                for (String s : l.split(",")) {
                    setup[indexX][indexY] = stringToRankType(s);
                    indexX++;
                }
                indexX = 0;
                indexY++;

            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    private RankType stringToRankType(String rank) {
        RankType rankType = null;

        switch (rank.toLowerCase()) {
            case "1":
                rankType = RankType.Spy;
                break;
            case "2":
                rankType = RankType.Scout;
                break;
            case "3":
                rankType = RankType.Minor;
                break;
            case "4":
                rankType = RankType.Sergeant;
                break;
            case "5":
                rankType = RankType.Lieutenant;
                break;
            case "6":
                rankType = RankType.Captain;
                break;
            case "7":
                rankType = RankType.Major;
                break;
            case "8":
                rankType = RankType.Colonel;
                break;
            case "9":
                rankType = RankType.General;
                break;
            case "10":
                rankType = RankType.Marshal;
                break;
            case "b":
                rankType = RankType.Bomb;
                break;
            case "f":
                rankType = RankType.Flag;
        }
        return rankType;
    }

    public void clearSetup() {
        setup = new RankType[10][4];
    }

    public boolean allSet() {
        return getAvailableSoldiers().allSet();
    }

    public void saveConfig(Path path) {
        try {
            String config = createOutPut();
            FileWriter out = new FileWriter(path.toFile());
            out.write(config);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String createOutPut() {
        String result = "";

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 10; x++) {
                RankType rank = setup[x][y];

                switch (rank) {
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
                if (x < 9) result += ",";
                else result += "\n";

            }
        }
        return result;
    }


    public RankType[][] getSetup() {
        return setup;
    }
}