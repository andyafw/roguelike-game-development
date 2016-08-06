/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.transport;

import java.util.ArrayList;
import roguelike_game.entity.item.Item;

/**
 * @version 07-13-16
 * @author andyafw
 */
public class Inventory {
    protected int items_can_wear = 10;		//helmet, hands, chest armor, belt, ring, pants, left-hand sword, amulet, boots, and right-hand sword
    protected int items_can_hold;
    protected int inv_width;
    protected int inv_height;
    
    protected ArrayList<Item> equippedItems;
    protected ArrayList<Item> inventoryItems;
    
    public Inventory(int width, int height) {
        inv_width = width;
        inv_height = height;
        items_can_hold = inv_width * inv_height;
        equippedItems = new ArrayList<Item>(items_can_wear);
        for(int i = 0; i < items_can_wear; i++) {
        	equippedItems.add(Item.SWORD);
        }
        inventoryItems = new ArrayList<Item>(items_can_hold);
        for(int i = 0; i < items_can_hold; i++) {
        	inventoryItems.add(Item.WAND);
        }
    }
    
    public int getInventoryWidth()  { return inv_width;}
    public int getInventoryHeight() { return inv_height;}
    public int getInventoryMax()    { return items_can_hold;}
    
    public boolean addItem(Item item) {
        if(inventoryItems.size() < items_can_hold) {
            inventoryItems.add(item);
            return true;
        }
    	System.out.println("Inventory is full");
    	return false;
        //TODO: need to figure out a way to tell user inventory is full
    }
    
    public Item getItem(int id) {
        if(inventoryItems.size() > id) {
            if(inventoryItems.get(id) != null) {
                return inventoryItems.get(id);
            }
        } 
        return null;
    }
    
    public boolean addEquip(Item item) {
        if(equippedItems.size() < items_can_wear) {
            equippedItems.add(item);
            return true;
        }
        System.out.println("Can not equip. too many items equipped.");
        return false;
    }
    
    public Item getEquip(int pos) {
        if(pos < equippedItems.size()) {
            return equippedItems.get(pos);
        } else {
            return null;
        }
    }
}
