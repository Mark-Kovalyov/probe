/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mayton.newral.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author mayton
 */
public class NNActionListener implements ActionListener
{
    NNCodekControlPanel nncp;

    NNActionListener(NNCodekControlPanel nncp)
    {
        this.nncp=nncp;
    }

    public void actionPerformed(ActionEvent e)
    {
        nncp.NNCodekMainObj.onLoadSourceImage();
    }
}
