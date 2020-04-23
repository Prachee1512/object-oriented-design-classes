package pl.agh.edu.dp.labirynth.Models;

public enum Direction {
    North, South, East, West;

    public Direction getOpposite(){
        switch (this){
            case East: return West;
            case West: return East;
            case North: return South;
            case South: return North;
        }
        return null;
    }
}