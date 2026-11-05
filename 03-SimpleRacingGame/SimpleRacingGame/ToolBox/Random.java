package SimpleRacingGame.ToolBox;

public class Random {
    public static int inRange(int start, int end) {
        return (int) ((Math.random() * (end - start + 1)) + start);
    }
}
