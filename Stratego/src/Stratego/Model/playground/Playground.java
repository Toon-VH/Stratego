package Stratego.Model.playground;



import Stratego.Model.army.*;
import Stratego.Model.player.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Playground {

    protected Location[][] locations;
    protected Player redArmy;
    protected Player blueArmy;
    protected List<Pawn> dead;
    protected boolean isFlagCaptured;


    public Playground(Player redArmy, Player blueArmy) {
        this.isFlagCaptured = false;
        this.redArmy = redArmy;
        this.blueArmy = blueArmy;
        this.dead = new ArrayList<>();
        this.locations = new Location[10][10];
        locations[2][4] = new Location();
        locations[2][5] = new Location();
        locations[3][4] = new Location();
        locations[3][5] = new Location();
        locations[6][4] = new Location();
        locations[6][5] = new Location();
        locations[7][4] = new Location();
        locations[7][5] = new Location();
        for (int x = 0; x <= 9; x++) {
            for (int y = 0; y <= 9; y++) {
                if (locations[x][y] == null) {
                    locations[x][y] = new PawnLocation();
                }
            }

        }
        for (int x = 0; x <= 9; x++) {
            for (int y = 0; y <= 9; y++) {
                if (locations[x][y] instanceof PawnLocation) {
                    if (x == 0) {
                        locations[x][y].setWest(locations[x + 1][y]);
                    } else if (x == 9) {
                        locations[x][y].setEast(locations[x - 1][y]);

                    } else {
                        locations[x][y].setWest(locations[x + 1][y]);
                        locations[x][y].setEast(locations[x - 1][y]);
                    }
                    if (y != 9) {
                        locations[x][y].setNord(locations[x][y + 1]);
                    }
                    if (y != 0) {
                        locations[x][y].setSouth(locations[x][y - 1]);
                    }
                }
            }
        }
    }

    public Location[][] getLocations() {
        return locations;
    }

    public Player getRedArmy() {
        return redArmy;
    }

    public Player getBlueArmy() {
        return blueArmy;
    }


    public void setPawn(int x, int y, Pawn pawn) {
        try {
            PawnLocation pawnLocation = (PawnLocation) locations[x][y];
            pawnLocation.setStandOn(pawn);
            pawn.setLocation(pawnLocation);
        } catch (Exception ex) {
            System.out.println("x: " + x + " y: " + y + " pawn: " + pawn.getRank());
        }
    }

    public boolean isFlagCaptured() {
        return isFlagCaptured;
    }

    public void loadArmySetup(ArmyColor armyColor, Path file) {
        int direction = 0;
        int startX = 0;
        int y = 0;
        Army army = null;
        switch (armyColor) {
            case Red:
                startX = 0;
                y = 3;
                direction = 1;
                army = redArmy.getArmy();
                break;
            case Blue:
                startX = 9;
                y = 6;
                direction = -1;
                army = blueArmy.getArmy();
                break;
        }

        try (Stream<String> stream = Files.lines(file)) {
            for (String l : stream.collect(Collectors.toList())) {
                int x = startX;
                for (String s : l.split(",")) {
                    Pawn pawn = getPawnFromString(s, army);
                    setPawn(x, y, pawn);
                    x += direction;
                }
                y -= direction;
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private Pawn getPawnFromString(String str, Army army) {

        RankType rankType = null;
        switch (str.toLowerCase()) {
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
        for (Pawn pawn : army.getPawns()) {
            if (!pawn.isSet() && pawn.getRank() == rankType) {
                return pawn;
            }
        }
        return null;
    }

    public void fight(Pawn attacker, Pawn defender) {
        FightResult fightResult = null;
        switch (attacker.getRank()) {
            case Marshal:
                switch (defender.getRank()) {
                    case Flag:
                        fightResult = FightResult.FlagCaptured;
                        break;
                    case Marshal:
                        fightResult = FightResult.BothDead;
                        break;
                    case General:
                    case Colonel:
                    case Major:
                    case Captain:
                    case Lieutenant:
                    case Sergeant:
                    case Minor:
                    case Scout:
                    case Spy:
                        fightResult = FightResult.DefendDead;
                        break;
                    case Bomb:
                        fightResult = FightResult.BothDead;
                        break;
                }
                break;
            case General:
                switch (defender.getRank()) {
                    case Flag:
                        fightResult = FightResult.FlagCaptured;
                        break;
                    case Marshal:
                        fightResult = FightResult.AttackDead;
                        break;
                    case General:
                        fightResult = FightResult.BothDead;
                        break;
                    case Colonel:
                    case Major:
                    case Captain:
                    case Lieutenant:
                    case Sergeant:
                    case Minor:
                    case Scout:
                    case Spy:
                        fightResult = FightResult.DefendDead;
                        break;
                    case Bomb:
                        fightResult = FightResult.BothDead;
                        break;
                }
                break;
            case Colonel:
                switch (defender.getRank()) {
                    case Flag:
                        fightResult = FightResult.FlagCaptured;
                        break;
                    case Marshal:
                    case General:
                        fightResult = FightResult.AttackDead;
                        break;
                    case Colonel:
                        fightResult = FightResult.BothDead;
                        break;
                    case Major:
                    case Captain:
                    case Lieutenant:
                    case Sergeant:
                    case Minor:
                    case Scout:
                    case Spy:
                        fightResult = FightResult.DefendDead;
                        break;
                    case Bomb:
                        fightResult = FightResult.BothDead;
                        break;
                }
                break;
            case Major:
                switch (defender.getRank()) {
                    case Flag:
                        fightResult = FightResult.FlagCaptured;
                        break;
                    case Marshal:
                    case General:
                    case Colonel:
                        fightResult = FightResult.AttackDead;
                        break;
                    case Major:
                        fightResult = FightResult.BothDead;
                        break;
                    case Captain:
                    case Lieutenant:
                    case Sergeant:
                    case Minor:
                    case Scout:
                    case Spy:
                        fightResult = FightResult.DefendDead;
                        break;
                    case Bomb:
                        fightResult = FightResult.BothDead;
                        break;
                }
                break;
            case Captain:
                switch (defender.getRank()) {
                    case Flag:
                        fightResult = FightResult.FlagCaptured;
                        break;
                    case Marshal:
                    case General:
                    case Colonel:
                    case Major:
                        fightResult = FightResult.AttackDead;
                        break;
                    case Captain:
                        fightResult = FightResult.BothDead;
                        break;
                    case Lieutenant:
                    case Sergeant:
                    case Minor:
                    case Scout:
                    case Spy:
                        fightResult = FightResult.DefendDead;
                        break;
                    case Bomb:
                        fightResult = FightResult.BothDead;
                        break;
                }
                break;
            case Lieutenant:
                switch (defender.getRank()) {
                    case Flag:
                        fightResult = FightResult.FlagCaptured;
                        break;
                    case Marshal:
                    case General:
                    case Colonel:
                    case Major:
                    case Captain:
                        fightResult = FightResult.AttackDead;
                        break;
                    case Lieutenant:
                        fightResult = FightResult.BothDead;
                        break;
                    case Sergeant:
                    case Minor:
                    case Scout:
                    case Spy:
                        fightResult = FightResult.DefendDead;
                        break;
                    case Bomb:
                        fightResult = FightResult.BothDead;
                        break;
                }
                break;
            case Sergeant:
                switch (defender.getRank()) {
                    case Flag:
                        fightResult = FightResult.FlagCaptured;
                        break;
                    case Marshal:
                    case General:
                    case Colonel:
                    case Major:
                    case Captain:
                    case Lieutenant:
                        fightResult = FightResult.AttackDead;
                        break;
                    case Sergeant:
                        fightResult = FightResult.BothDead;
                        break;
                    case Minor:
                    case Scout:
                    case Spy:
                        fightResult = FightResult.DefendDead;
                        break;
                    case Bomb:
                        fightResult = FightResult.BothDead;
                        break;
                }
                break;
            case Minor:
                switch (defender.getRank()) {
                    case Flag:
                        fightResult = FightResult.FlagCaptured;
                        break;
                    case Marshal:
                    case General:
                    case Colonel:
                    case Major:
                    case Captain:
                    case Lieutenant:
                    case Sergeant:
                        fightResult = FightResult.AttackDead;
                        break;
                    case Minor:
                        fightResult = FightResult.BothDead;
                        break;
                    case Scout:
                    case Spy:
                    case Bomb:
                        fightResult = FightResult.DefendDead;
                        break;
                }
                break;
            case Scout:
                switch (defender.getRank()) {
                    case Flag:
                        fightResult = FightResult.FlagCaptured;
                        break;
                    case Marshal:
                    case General:
                    case Colonel:
                    case Major:
                    case Captain:
                    case Lieutenant:
                    case Sergeant:
                    case Minor:
                        fightResult = FightResult.AttackDead;
                        break;
                    case Scout:
                        fightResult = FightResult.BothDead;
                        break;
                    case Spy:
                        fightResult = FightResult.DefendDead;
                        break;
                    case Bomb:
                        fightResult = FightResult.BothDead;
                        break;
                }
                break;
            case Spy:
                switch (defender.getRank()) {
                    case Flag:
                        fightResult = FightResult.FlagCaptured;
                        break;
                    case Marshal:
                        fightResult = FightResult.DefendDead;
                        break;
                    case General:
                    case Colonel:
                    case Major:
                    case Captain:
                    case Lieutenant:
                    case Sergeant:
                    case Minor:
                    case Scout:
                        fightResult = FightResult.AttackDead;
                        break;
                    case Spy:
                    case Bomb:
                        fightResult = FightResult.BothDead;
                        break;
                }
                break;
        }

        PawnLocation pawnLocationA = (PawnLocation) attacker.getLocation();
        PawnLocation pawnLocationD = (PawnLocation) defender.getLocation();

        switch (fightResult) {
            case BothDead:
                dead.add(attacker);
                dead.add(defender);
                pawnLocationA.setStandOn(null);
                pawnLocationD.setStandOn(null);
                break;
            case DefendDead:
                attacker.setLocation(pawnLocationD);
                pawnLocationD.setStandOn(pawnLocationA.getStandOn());
                dead.add(defender);
                pawnLocationA.setStandOn(null);
                break;
            case AttackDead:
                attacker.setLocation(null);
                dead.add(attacker);
                pawnLocationA.setStandOn(null);
                break;
            case FlagCaptured:
                isFlagCaptured = true;
                break;
        }

    }

    public void calcPossibleLocations(PawnLocation from) {
        MoveType moveType = from.standOn.getMove();
        resetIsInRange();
        switch (moveType) {
            case Single:

                if (getTargetLocationAnalysis(from, from.nord) != 0) {
                    ((PawnLocation) from.nord).setInRange(true);
                }
                if (getTargetLocationAnalysis(from, from.east) != 0) {
                    ((PawnLocation) from.east).setInRange(true);
                }
                if (getTargetLocationAnalysis(from, from.south) != 0) {
                    ((PawnLocation) from.south).setInRange(true);
                }
                if (getTargetLocationAnalysis(from, from.west) != 0) {
                    ((PawnLocation) from.west).setInRange(true);
                }
                break;
            case Multiple:
                List<PawnLocation> pawnLocationInRange = new ArrayList<>();
                multiMovePossibleLocation(from, pawnLocationInRange);
                for (PawnLocation pawnLocation : pawnLocationInRange) {
                    pawnLocation.setInRange(true);
                }
                break;
            case None:
                break;
        }


    }

    private void multiMovePossibleLocation(PawnLocation startLocation, List<PawnLocation> pawnLocationList) {
        multiMovePossibleLocation(startLocation,startLocation,pawnLocationList);}
    private void multiMovePossibleLocation(PawnLocation startLocation, PawnLocation prevLocation, List<PawnLocation> pawnLocationList) {

        int northAnalysis = getTargetLocationAnalysis(startLocation, prevLocation.nord);
        int eastAnalysis = getTargetLocationAnalysis(startLocation, prevLocation.east);
        int southAnalysis = getTargetLocationAnalysis(startLocation, prevLocation.south);
        int westAnalysis = getTargetLocationAnalysis(startLocation, prevLocation.west);

        if ( northAnalysis != 0 && !pawnLocationList.contains(prevLocation.nord)) {
            pawnLocationList.add((PawnLocation) prevLocation.nord);

            if(northAnalysis == 1)
            multiMovePossibleLocation(startLocation, (PawnLocation) prevLocation.nord, pawnLocationList);
        }

        if (eastAnalysis != 0 && !pawnLocationList.contains(prevLocation.east)) {
            pawnLocationList.add((PawnLocation) prevLocation.east);

            if(eastAnalysis == 1)
            multiMovePossibleLocation(startLocation,(PawnLocation) prevLocation.east, pawnLocationList);
        }
        if (southAnalysis != 0 && !pawnLocationList.contains(prevLocation.south)) {
            pawnLocationList.add((PawnLocation) prevLocation.south);

            if(southAnalysis == 1)
            multiMovePossibleLocation(startLocation,(PawnLocation) prevLocation.south, pawnLocationList);
        }
        if (westAnalysis != 0 && !pawnLocationList.contains(prevLocation.west)) {
            pawnLocationList.add((PawnLocation) prevLocation.west);

            if(westAnalysis == 1)
            multiMovePossibleLocation(startLocation,(PawnLocation) prevLocation.west, pawnLocationList);
        }

    }

    private int getTargetLocationAnalysis(PawnLocation startLocation, Location targetLocation) {
        if (targetLocation instanceof PawnLocation) {
            PawnLocation pawnLocation = (PawnLocation) targetLocation;
            if (pawnLocation.getStandOn() == null ) {
                return 1;
            }
            if (pawnLocation.getStandOn().getParent() != startLocation.getStandOn().getParent()){
                return 2;
            }
        }
        return 0;
    }

    public void resetIsInRange() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Location l = locations[x][y];
                if (l instanceof PawnLocation) {
                    ((PawnLocation) l).setInRange(false);
                }
            }
        }
    }
}