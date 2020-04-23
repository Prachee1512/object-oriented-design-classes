package pl.agh.edu.dp.labirynth.Models;

public class Wall extends MapSite {
    public Wall(){

    }

    @Override
    public void Enter(Player p){
        System.out.println("You can't move through wall.");
    }
}
