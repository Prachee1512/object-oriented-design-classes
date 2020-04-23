package pl.agh.edu.dp.labirynth.Models;

public class EnchantedWall extends Wall {

    public EnchantedWall() {
        super();
    }

    @Override
    public void Enter(Player p) {
        System.out.println("You are in enchanted wall");
    }
}
