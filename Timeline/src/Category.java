import java.awt.Color;

/**
 *
 * @author Daniel
 * and Kayley
 */
// A very simple class created to easily use all the variables we would need to describe a category. 
class Category{
	private String name;
	private Color catColor; // GUI Color of the category.

        public Category(String name){
		this.name = name;
	}
	Category(String name, Color catColor){
		this.name = name;
		this.catColor = catColor;
	}

	public String getName(){
		return name;
	}
	public Color getColor(){
		return catColor;
	}

	public void setName(String name){
		this.name = name;
	}
	public void setColor(Color catColor){
		this.catColor = catColor;
	}
}