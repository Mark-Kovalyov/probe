

package mayton.game.chess;


public class Figure
{


    public boolean isFirstmove() {
        return firstmove;
    }

    boolean firstmove;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    int x;
    int y;
}
