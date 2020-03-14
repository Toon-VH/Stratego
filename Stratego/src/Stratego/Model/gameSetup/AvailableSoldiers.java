package Stratego.Model.gameSetup;

import Stratego.Model.gamePlay.army.RankType;

public class AvailableSoldiers {

    private int marshal = 1;
    private int general = 1;
    private int colonel = 2;
    private int major = 3;
    private int captain = 4;
    private int lieutenant = 4;
    private int sergeant = 4;
    private int minor = 5;
    private int scout = 8;
    private int spy = 1;
    private int flag = 1;
    private int bomb = 6;

    public void remove(RankType soldierType) {

        if (soldierType != null) {

            switch (soldierType) {
                case Flag:
                    flag--;
                    break;
                case Bomb:
                    bomb--;
                    break;
                case Spy:
                    spy--;
                    break;
                case Scout:
                    scout--;
                    break;
                case Minor:
                    minor--;
                    break;
                case Lieutenant:
                    lieutenant--;
                    break;
                case Marshal:
                    marshal--;
                    break;
                case Major:
                    major--;
                    break;
                case Captain:
                    captain--;
                    break;
                case Colonel:
                    colonel--;
                    break;
                case General:
                    general--;
                    break;
                case Sergeant:
                    sergeant--;
                    break;
            }
        }
    }

    public boolean isValid() {
        boolean isvalid = true;
        if (this.getBomb() < 0) {
            isvalid = false;
        }
        if (this.getCaptain() < 0) {
            isvalid = false;
        }
        if (this.getColonel() < 0) {
            isvalid = false;
        }
        if (this.getFlag() < 0) {
            isvalid = false;
        }
        if (this.getGeneral() < 0) {
            isvalid = false;
        }
        if (this.getLieutenant() < 0) {
            isvalid = false;
        }
        if (this.getMajor() < 0) {
            isvalid = false;
        }
        if (this.getMarshal() < 0) {
            isvalid = false;
        }
        if (this.getMinor() < 0) {
            isvalid = false;
        }
        if (this.getScout() < 0) {
            isvalid = false;
        }
        if (this.getSergeant() < 0) {
            isvalid = false;
        }
        if (this.getSpy() < 0) {
            isvalid = false;
        }
        return isvalid;
    }

    public boolean allSet() {
        boolean isvalid = true;
        if (this.getBomb() != 0) {
            isvalid = false;
        }
        if (this.getCaptain() != 0) {
            isvalid = false;
        }
        if (this.getColonel() != 0) {
            isvalid = false;
        }
        if (this.getFlag() != 0) {
            isvalid = false;
        }
        if (this.getGeneral() != 0) {
            isvalid = false;
        }
        if (this.getLieutenant() != 0) {
            isvalid = false;
        }
        if (this.getMajor() != 0) {
            isvalid = false;
        }
        if (this.getMarshal() != 0) {
            isvalid = false;
        }
        if (this.getMinor() != 0) {
            isvalid = false;
        }
        if (this.getScout() != 0) {
            isvalid = false;
        }
        if (this.getSergeant() != 0) {
            isvalid = false;
        }
        if (this.getSpy() != 0) {
            isvalid = false;
        }
        return isvalid;
    }

    public int getMarshal() {
        return marshal;
    }

    public int getGeneral() {
        return general;
    }

    public int getColonel() {
        return colonel;
    }

    public int getMajor() {
        return major;
    }

    public int getCaptain() {
        return captain;
    }

    public int getLieutenant() {
        return lieutenant;
    }

    public int getSergeant() {
        return sergeant;
    }

    public int getMinor() {
        return minor;
    }

    public int getScout() {
        return scout;
    }

    public int getSpy() {
        return spy;
    }

    public int getFlag() {
        return flag;
    }

    public int getBomb() {
        return bomb;
    }
}
