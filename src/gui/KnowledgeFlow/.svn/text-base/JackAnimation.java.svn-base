/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 * AnimationLabel.java
 *
 * Created on 28 de mayo de 2006, 09:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.KnowledgeFlow;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import java.awt.event.ActionListener;

/**
 * This class extends <code>AnimationLabel</code> and display a Duke mining
 * animation.
 * The Duke logo is trademark of Sun Microsystems, Inc.
 * @author and
 */
public class JackAnimation extends AnimationLabel{
    /** Array of ImageIcon with the animation images*/
    ImageIcon[] images = new ImageIcon[7];
    /** Control delay time */
    Timer timer = new Timer(50, this);
    int n = 0;
    /** Creates a new instance of AnimationLabel */
    public JackAnimation() {
        for(int i = 0; i < 7; i++){
            images[i] = new ImageIcon(getClass().getResource(
                    "/images/animation/j" + (i + 1) + ".png"));
        }
        this.setName("MyIcon");
    }
    /**
     *Set visible true and run the animation.
     */
    public void run() {
        if(!timer.isRunning()){
            this.setVisible(true);
            timer.start();
        }
    }
    /**
     *Stop and hide the animation
     */
    public void stop(){
        this.setVisible(false);
        timer.stop();
        this.getParent().getParent().repaint();
    }
    /**
     *Add animation to cantainer in (x,y) position.
     *
     *@param container The <code>Container</code> add in
     *@param x the x position within container
     *@param y the y position within container
     */
    public void addAnimation(Container container, int x, int y){
        container.add(this);
        this.setBounds(x, y, 36, 36);
    }
    /**
     *Implementation interface ActionListener.  Change the image here.
     *@param e The ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        this.setIcon(images[n]);
        n++;
        if(n > 6) n = 0;
    }
}
