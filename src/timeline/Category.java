
package timeline;

import java.awt.Color;

/**
 * Datatype in which to categories.
 * 
 * @author Daniel
 * @author Kayley
 */
class Category{
	private String name;
	private Color catColor; // GUI Color of the category.
        
        /**
         * Constructor
         * 
         * @param name The name of the category.
         */
        public Category(String name){
		this.name = name;
                catColor = new Color(51, 255, 51);
	}
        /**
         * Constructor
         * 
         * @param name The name of the category.
         * @param catColor The color of the category.
         */
	public Category(String name, Color catColor){
		this.name = name;
		this.catColor = catColor;
	}
        /**
         * Returns the name of the category.
         * 
         * @return The name.
         */
	public String getName(){
		return name;
	}
        /**
         * Returns the color of the category.
         * 
         * @return The color.
         */
	public Color getColor(){
		return catColor;
	}
        /**
         * Sets the name of the category.
         * 
         * @param name The name to use.
         */
	public void setName(String name){
		this.name = name;
	}
        /**
         * Sets the color of the category.
         * 
         * @param catColor The color to use.
         */
	public void setColor(Color catColor){
		this.catColor = catColor;
	}
}