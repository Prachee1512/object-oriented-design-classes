import org.junit.Before;
import org.junit.Test;
import pl.agh.edu.dp.labirynth.Factories.MazeFactory;

import static org.junit.Assert.assertEquals;

public class MazeFactoryTester {
    @Test
    public void MazeFactoryTest(){
        MazeFactory m = MazeFactory.getInstance();
        MazeFactory m2 = MazeFactory.getInstance();
        assertEquals(m, m2);
        assertEquals(MazeFactory.getInstance(), m);
        assertEquals(MazeFactory.getInstance(), m2);
    }
}
