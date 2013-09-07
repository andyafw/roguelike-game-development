/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import roguelike_game.Roguelike_game;

/**
 *
 * @author andyafw
 */
public class Movement implements KeyListener, MouseListener, MouseMotionListener {
    public boolean UP        = false;
    public boolean DOWN      = false;
    public boolean LEFT      = false;
    public boolean RIGHT     = false;
    
    public boolean OPEN_DEV = false;
    
    private boolean prevUP = false;
    private boolean prevDOWN = false;
    private boolean prevLEFT = false;
    private boolean prevRIGHT = false;
    
    public int MOSX = 0;
    public int MOSY = 0;
    
    private boolean[] key = new boolean[500];
    
    public Movement() {
        for(int i = 0; i < key.length; i++) {
            key[i] = false;
        }
    }
    
    public void update() {
        UP = key[KeyEvent.VK_UP];
        DOWN = key[KeyEvent.VK_DOWN];
        LEFT = key[KeyEvent.VK_LEFT];
        RIGHT = key[KeyEvent.VK_RIGHT];
        
        if (UP != prevUP) {
            System.out.println("UP changed! UP: " + UP);
            prevUP = UP;
        }
        if (DOWN != prevDOWN) {
            System.out.println("DOWN changed! DOWN: " + DOWN);
            prevDOWN = DOWN;
        }
        if (LEFT != prevLEFT) {
            System.out.println("LEFT changed! LEFT: " + LEFT);
            prevLEFT = LEFT;
        }
        if (RIGHT != prevRIGHT) {
            System.out.println("RIGHT changed! RIGHT: " + RIGHT);
            prevRIGHT = RIGHT;
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        key[ke.getKeyCode()] = true;
        
        if (ke.getKeyCode() == KeyEvent.VK_BACK_QUOTE) {// && ke.getModifiersEx() == KeyEvent.SHIFT_DOWN_MASK) {
            System.out.println("tilda " + OPEN_DEV);
            OPEN_DEV = !OPEN_DEV;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        key[ke.getKeyCode()] = false;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {}    

    @Override
    public void mouseClicked(MouseEvent me) {}

    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}

    @Override
    public void mouseDragged(MouseEvent me) {}

    @Override
    public void mouseMoved(MouseEvent me) {
        MOSX = me.getX();
        MOSY = me.getY();
    }
    
    public void mouseUpdate(Roguelike_game game) {
        
    }
}
