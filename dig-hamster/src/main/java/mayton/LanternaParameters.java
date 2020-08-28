package mayton;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class LanternaParameters {

    public int row;
    public int column;
    public String formatExpr;
    public Screen screen;
    public TextGraphics textGraphics;
    public String placeholder;
    public String result;


    public LanternaParameters(int row, int column, String formatExpr, Screen screen, TextGraphics textGraphics, String placeholder) {
        this.row = row;
        this.column = column;
        this.formatExpr = formatExpr;
        this.screen = screen;
        this.textGraphics = textGraphics;
        this.placeholder = placeholder;
    }
}
