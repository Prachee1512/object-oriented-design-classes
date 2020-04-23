package pl.agh.edu.dp.labirynth.Models;

public class BombedWall extends Wall {
    public BombedWall() {
        super();
    }

    @Override
    public void Enter(Player p) {
        System.out.println("Bombed wall!");
        p.kill();
    }
}
