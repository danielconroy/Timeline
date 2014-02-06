/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

import java.awt.Color;

/**
 *
 * @author Daniel
 */
// A very simple class created to easily use all the variables we would need to describe a category. 
class Category{
	private String name;
	private int pos; // The position of the category in relation to all the categories so it can be moved.
	private Color catColor; // GUI Color of the category.
	
        public Category(String name){
		this.name = name;
	}
	public Category(String name, int pos){
		this.name = name;
		this.pos = pos;
	}
	Category(String name, int pos, Color catColor){
		this.name = name;
		this.pos = pos;
		this.catColor = catColor;
	}
	
	public String getName(){
		return name;
	}
	public int getPos(){
		return pos;
	}
	public Color getColor(){
		return catColor;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public void setPos(int pos){
		this.pos = pos;
	}
	public void setColor(Color catColor){
		this.catColor = catColor;
	}
}
