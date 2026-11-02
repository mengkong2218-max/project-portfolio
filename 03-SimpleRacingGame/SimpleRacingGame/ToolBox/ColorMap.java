package SimpleRacingGame.ToolBox;

import java.util.HashMap;
import java.awt.Color;

public class ColorMap {

    private static final HashMap<String, Color> colourMaps = new HashMap<>();

    static {
        colourMaps.put("BLUE", Color.BLUE);
        colourMaps.put("GREEN", Color.GREEN);
        colourMaps.put("GREY", Color.GRAY);
        colourMaps.put("RED", Color.RED);
        colourMaps.put("YELLOW", Color.YELLOW);
        colourMaps.put("PURPLE", Color.MAGENTA);
        colourMaps.put("ORANGE", Color.ORANGE);
    }

    public static Color getColor(String colorInString) {
        return colourMaps.get(colorInString.toUpperCase());
    }
}
