package pl.agh.edu.dp.labirynth.Models;

public class BombedRoom extends Room {
    public BombedRoom(int number) {
        super(number);
    }

    @Override
    public void Enter(Player p) {
        System.out.println("It's a trap!");
        p.kill();
    }
}
