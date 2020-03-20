package Stratego.Model.gameAI;

import Stratego.Model.gamePlay.Stratego;
import Stratego.Model.gamePlay.army.ArmyColor;
import Stratego.Model.gamePlay.army.RankType;
import Stratego.Model.gamePlay.playground.Location;
import Stratego.Model.gamePlay.playground.PawnLocation;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class AI {


    private Map<RankType, Integer> aISetup;
    private Map<RankType, Integer> aIAllowPawns;
    private Stratego stratego;

    public AI(Stratego stratego) {
        this.stratego = stratego;
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

    public AIMove nextMove() throws Exception {
        List<PawnLocation> moveAbles = getMoveAbles();
        int index = new Random().nextInt(moveAbles.size());
        PawnLocation take = moveAbles.get(index);
        stratego.getPlayground().getRedArmy().take(take, stratego.getPlayground(), stratego);
        List<PawnLocation> targets = getTargets();
        index = new Random().nextInt(targets.size());
        PawnLocation place = targets.get(index);
        AIMove move = new AIMove(take, place);
        return move;
    }

    private List<PawnLocation> getTargets() {
        List<PawnLocation> targets = new ArrayList<>();
        Location[][] locations = stratego.getPlayground().getLocations();
        for (Location[] locationrow : locations) {
            for (Location location : locationrow) {
                if (location instanceof PawnLocation) {
                    PawnLocation pawnLocation = (PawnLocation) location;
                    if (pawnLocation.isInRange()) {
                        targets.add(pawnLocation);
                    }
                }
            }
        }
        return targets;
    }

    private List<PawnLocation> getMoveAbles() {
        List<PawnLocation> moveAbles = new ArrayList();
        Location[][] locations = stratego.getPlayground().getLocations();
        for (Location[] locationrow : locations) {
            for (Location location : locationrow) {
                if (location instanceof PawnLocation) {
                    PawnLocation pawnLocation = (PawnLocation) location;
                    if (pawnLocation.getStandOn() != null) {
                        if (pawnLocation.getStandOn().getRank() != RankType.Bomb || pawnLocation.getStandOn().getRank() != RankType.Flag) {
                            ArmyColor color = pawnLocation.getStandOn().getParent().getColor();
                            if (color == ArmyColor.Red) {
                                if (canMove(pawnLocation)) {
                                    moveAbles.add(pawnLocation);
                                }
                            }
                        }
                    }
                }
            }
        }
        return moveAbles;
    }

    private boolean canMove(PawnLocation pawnLocation) {
        boolean result = true;
        Location location = pawnLocation.getNord();
        if (!isFree(location)) {
            location = pawnLocation.getEast();
            if (!isFree(location)) {
                location = pawnLocation.getSouth();
                if (!isFree(location)) {
                    location = pawnLocation.getWest();
                    if (!isFree(location)) {
                        result = false;
                    }
                }
            }
        }
        return result;

    }


    private boolean isFree(Location location) {
        boolean result = false;
        if (location instanceof PawnLocation) {
            PawnLocation pawnLocation = (PawnLocation) location;
            if (pawnLocation.getStandOn() == null) {
                result = true;
            } else if (pawnLocation.getStandOn().getParent().getColor() == ArmyColor.Blue) {
                result = true;
            }
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



