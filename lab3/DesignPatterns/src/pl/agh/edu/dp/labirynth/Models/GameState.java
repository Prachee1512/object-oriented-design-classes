package pl.agh.edu.dp.labirynth.Models;

public enum GameState{
    NONE, WIN, LOSE, PENDING;

    public String toString(){
        switch (this){
            case NONE: return "Game not started";
            case WIN: return "You won :)";
            case LOSE: return "You lost :(";
            case PENDING: return "Game in progress";
        }
        return "???";
    }
}