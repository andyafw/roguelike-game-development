/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.JPanel;
import roguelike_game.Game;

/**
 * @version 09/17/2016
 * @author Andy Walser
 */

@SuppressWarnings("serial")
public class Render extends JPanel {
	public int size, width, height,res_width, res_height;//int for game variables
    public int inv_window_start, inv_width, inv_height, inv_size, inventory_start; //init inventory variables
    public int armor_start, arm_width, arm_height; //init armor variables 
    private Game game;
    
    //positions for armor menu
    public Point[] positions = {new Point(3, 1),   /*helmet       0*/ new Point(2, 2),   /*left sword   1*/
			                    new Point(3, 2),   /*chest armor  2*/ new Point(4, 2),   /*right sword  3*/
			                    new Point(3, 3),   /*pants        4*/ new Point(1, 4),   /*amulet       5*/
			                    new Point(3, 4),   /*boots        6*/ new Point(5, 4),   /*gloves       7*/
			                    new Point(1, 5),   /*ring         8*/ new Point(5, 5)};  /*belt         9*/
    
    public Render(Game game) {
        this.game = game;
        initGame();
        initInventory();
        setPreferredSize(new Dimension(res_width, res_height));
    }
    
   public void initGame() {	
	    size = 32;//sets size of tiles 
	    width = 100;//how many tiles in a row
	    height = 100;//how many tiles in a column
	    res_width = 1100;//how big the screen is width wise
	    res_height = 600; //how big the game is lengthwise
   }
    
   public void initInventory() {
	    inv_width = 7; //sets width of the inventory of the player
	    inv_height = 7;//sets height of the inventory of the player
	    arm_width = 6;//width of armor menu
	    arm_height = 6;//height of armor menu
	    inventory_start = 352;//position where the inventory starts rendering
	    armor_start = 140;//position where the armor screen starts rendering
	    inv_window_start = 874;//position where the map, inventory, and armor screen is rendered
   }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(game.mainmenu.menuon) {
            game.mainmenu.paint(g);
        } else {    
            render(g, game.cam.x, game.cam.y);
        }
    }
    
    public void render(Graphics g, int scrollx, int scrolly) {
        int startx = Math.max(scrollx / size, 0);//position where tiles can start being rendered on screen
        int starty = Math.max(scrolly / size, 0);//^^
        int limitx = Math.min((scrollx + res_width) / 30 + 1, width);//position where tiles stop being rendered on the screen
        int limity = Math.min((scrolly + res_height) / 30 + 1, height);//^^
        drawBackground(g);
        renderScreen(g, startx, starty, limitx, limity, scrollx, scrolly);
        renderInventory(g);
    }
    
    public void drawBackground(Graphics g) {
        //for(int y = 0; y < 19; y++) {
        //    for (int x = 0; x < 35; x++) {
        //        g.drawImage(Sprite.SECOND_FLOOR.getImage(), x * size, y * size, size, size, null);
        //    }
        //}
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 0, res_width, res_height);
    }
    
    public void renderScreen(Graphics g, int startx, int starty, int limitx, int limity, int scrollx, int scrolly) {
        for(int y = starty; y < limity; y++) {
            for(int x = startx; x < limitx; x++) {
                //draw tilemap
            	g.drawImage(findImage(game.tilemap.tiles[y][x]), x * size - scrollx, y * size - scrolly, size, size, null);
                
                //draw items
                if(game.tilemap.items[y][x] == null) {
                    continue;
                } else {
                    Point p = new Point(x * size, y * size);
                    g.drawImage(game.tilemap.items[y][x].getSpriteImage(), p.x - scrollx, p.y - scrolly, size, size, null);
                }   
            }
        }
        
        //draw player
        Point p = new Point(game.player.getX() * size, game.player.getY() * size);
        g.drawImage(game.player.getSprite().getImage(), p.x - scrollx, p.y - scrolly, size, size, null);
    }
    
    public void renderInventory(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(inv_window_start, 0, 226, 600);
        
        g.setColor(Color.red);
        g.fillArc(inv_window_start, 0, 226, 140, 0, 360);
        
        //create armor inventory 
        int next = 0;
        for(int y = 0; y < arm_height; y++) {
            for (int x = 0; x < arm_width; x++) {
                if(next < 10) {
                    if(positions[next].x == x && positions[next].y == y) {
                    	int xx = x * size + inv_window_start;
                    	int yy = y * size + armor_start;
                        g.setColor(Color.black);
                        g.fillRect(xx, yy, size, size);
                        g.setColor(Color.green);
                        g.drawRect(xx, yy, size, size);
                        if(game.player.getInventory().getEquip(next) != null) {
                        	game.player.getInventory().getEquip(next).render(g, xx, yy, 0);
                        }
                        next++;
                    }
                }
            }
        }
        
        //create player inventory
        for(int y = 0; y < inv_height; y++) {
            for (int x = 0; x < inv_width; x++) {
            	int xx = x * size + inv_window_start;
            	int yy = y * size + inventory_start;
                g.setColor(Color.black);
                g.fillRect(xx, yy, size, size);
                g.setColor(Color.yellow);
                g.drawRect(xx, yy, size, size);
                if(game.player.getInventory().getItem(y * inv_width + x) != null) {
                    game.player.getInventory().getItem(y * inv_width + x).render(g, xx, yy, 0);
                }
            }
        }
    }
    
    private Image findImage(int i) {
        switch(i) {
            case 0:
                return Sprite.NEW_WALL.getImage();
            default:
                //if don't know number inputed then send floor image
                return Sprite.EMPTY.getImage();
        }
    }
}
