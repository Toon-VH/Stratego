package Stratego.Model.gameAI;

import Stratego.Model.gamePlay.army.RankType;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AI {

    private Map<RankType, Integer> aISetup;
    private Map<RankType, Integer> aIAllowPawns;

    public AI() {
        this.aIAllowPawns = new HashMap<>();
        aIAllowPawns.put(RankType.Marshal, 1);
        aIAllowPawns.put(RankType.General, 1);
        aIAllowPawns.put(RankType.Colonel, 2);
        aIAllowPawns.put(RankType.Major, 3);
        aIAllowPawns.put(RankType.Captain, 4);
        aIAllowPawns.put(RankType.Lieutenant, 4);
        aIAllowPawns.put(RankType.Sergeant, 4);
        aIAllowPawns.put(RankType.Minor, 5);
        aIAllowPawns.put(RankType.Scout, 8);
        aIAllowPawns.put(RankType.Spy, 1);
        aIAllowPawns.put(RankType.Bomb, 6);
        aIAllowPawns.put(RankType.Flag, 1);
    }

    private String generateSetup() {
        this.aISetup = new HashMap<>();
        String aISetup = "";
        boolean found = false;
        int x = 0;
        while (!found) {
            int random = new Random().nextInt(12);
            RankType rankType = RankType.values()[random];
            if (addToAISetup(rankType)) {
                aISetup += rankTypeToString(rankType);
                if (x < 9) {
                    aISetup += ",";
                    x++;
                } else {
                    x = 0;
                    aISetup += "\n";
                }

            }

            found = isSetupReady();
        }
        return aISetup;
    }

    private boolean addToAISetup(RankType rankType) {
        boolean result = false;
        int count = 0;
        if (aISetup.containsKey(rankType)) {
            count = aISetup.get(rankType);
        }
        count++;

        int max = aIAllowPawns.get(rankType);
        if (count <= max) {
            aISetup.put(rankType, count);
            result = true;
        }
        return result;
    }

    private boolean isSetupReady() {

        for (RankType rankType : aIAllowPawns.keySet()) {
            int max = aIAllowPawns.get(rankType);
            if (aISetup.containsKey(rankType)) {
                int count = aISetup.get(rankType);
                if (count < max) {
                    return false;
                }
            } else return false;
        }
        return true;

    }

    private String rankTypeToString(RankType rankType) {
        //zet ranktype om in string
        String result = "";
        switch (rankType) {
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
        return result;
    }

    public void saveAIConfig(Path path) {
        try {
            String config = generateSetup();
            FileWriter out = new FileWriter(path.toFile());
            out.write(config);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



