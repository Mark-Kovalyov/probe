/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mayton.game.go;

import java.util.Properties;

public interface IGobanEngine {

    public GoCell getGoCell(int x,int y);

    public int getWidth();

    public int getHeight();

    public boolean onOpponentMove(int x,int y);

    public int init(Properties prop);

    public String getEngineInfo();

}
