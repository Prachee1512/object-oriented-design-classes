package pl.agh.edu.dp.main;

import pl.agh.edu.dp.labirynth.Builders.StandardMazeBuilder;
import pl.agh.edu.dp.labirynth.Exceptions.NoCommonWallException;
import pl.agh.edu.dp.labirynth.Factories.BombedMazeFactory;
import pl.agh.edu.dp.labirynth.Factories.EnchantedMazeFactory;
import pl.agh.edu.dp.labirynth.Factories.MazeFactory;
import pl.agh.edu.dp.labirynth.MazeGame;
import pl.agh.edu.dp.labirynth.Models.Direction;
import pl.agh.edu.dp.labirynth.Models.GameState;
import pl.agh.edu.dp.labirynth.Models.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MazeGame mazeGame = new MazeGame(new Player());
        try{
            mazeGame.createMaze(new StandardMazeBuilder(),
                    MazeFactory.getInstance(),
                    BombedMazeFactory.getInstance(),
                    EnchantedMazeFactory.getInstance());
        }catch (NoCommonWallException e){
            System.out.println(e.toString());
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        Map<Character, Direction> moves = new HashMap<>();
        moves.put('a', Direction.West);
        moves.put('d', Direction.East);
        moves.put('w', Direction.North);
        moves.put('s', Direction.South);

        System.out.println("Move: w/s/a/d | Quit game: q");
        System.out.print("> ");
        String s;
        Scanner in = new Scanner(System.in);
        while(!(s = in.nextLine()).equals("q")){
            if(s.length() == 0){
                continue;
            }
            Character c = s.charAt(0);
            if(!moves.containsKey(c)){
                System.out.println("Wrong option!");
                System.out.println("Move: w/s/a/d | Quit game: q");
            }else{
                mazeGame.movePlayer(moves.get(c));
                GameState state = mazeGame.getState();
                if(state != GameState.PENDING && state != GameState.NONE){
                    break;
                }
            }
            System.out.print("> ");
        }

        System.out.println("End of the game: " + mazeGame.getState().toString());
    }
}



