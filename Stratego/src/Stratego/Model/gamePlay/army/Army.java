package Stratego.Model.gamePlay.army;

import java.util.ArrayList;
import java.util.List;


public class Army {
    //leger per kleur
    private List<Pawn> pawns;
    private ArmyColor color;

    public Army(ArmyColor color) {
        this.color = color;
        this.pawns = new ArrayList<>();
        pawns.add(new Pawn(this, RankType.Marshal, MoveType.Single));
        pawns.add(new Pawn(this, RankType.General, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Colonel, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Colonel, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Major, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Major, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Major, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Captain, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Captain, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Captain, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Captain, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Lieutenant, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Lieutenant, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Lieutenant, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Lieutenant, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Sergeant, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Sergeant, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Sergeant, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Sergeant, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Minor, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Minor, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Minor, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Minor, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Minor, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Scout, MoveType.Multiple));
        pawns.add(new Pawn(this, RankType.Scout, MoveType.Multiple));
        pawns.add(new Pawn(this, RankType.Scout, MoveType.Multiple));
        pawns.add(new Pawn(this, RankType.Scout, MoveType.Multiple));
        pawns.add(new Pawn(this, RankType.Scout, MoveType.Multiple));
        pawns.add(new Pawn(this, RankType.Scout, MoveType.Multiple));
        pawns.add(new Pawn(this, RankType.Scout, MoveType.Multiple));
        pawns.add(new Pawn(this, RankType.Scout, MoveType.Multiple));
        pawns.add(new Pawn(this, RankType.Spy, MoveType.Single));
        pawns.add(new Pawn(this, RankType.Bomb, MoveType.None));
        pawns.add(new Pawn(this, RankType.Bomb, MoveType.None));
        pawns.add(new Pawn(this, RankType.Bomb, MoveType.None));
        pawns.add(new Pawn(this, RankType.Bomb, MoveType.None));
        pawns.add(new Pawn(this, RankType.Bomb, MoveType.None));
        pawns.add(new Pawn(this, RankType.Bomb, MoveType.None));
        pawns.add(new Pawn(this, RankType.Flag, MoveType.None));
    }

    public List<Pawn> getPawns() {
        return pawns;
    }

    public ArmyColor getColor() {
        return color;
    }
}
