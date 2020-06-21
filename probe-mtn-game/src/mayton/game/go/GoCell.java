/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mayton.game.go;

public enum GoCell {

    FREE(0),
    BLACK(1),
    WHITE(2);

    int cell;

    GoCell(int cell)
    {
        this.cell=cell;
    }

    @Override
    public String toString()
    {
        if (cell==0) return "FREE";
        if (cell==1) return "BLACK";
        return "WHITE";
    }

}
