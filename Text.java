import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actor
{
    /**
     * Act - do whatever the Text wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int width, height;
    private String text;
    private GreenfootImage image;
    public Text(String text, int w, int h){
        this.text = text;
        image = new GreenfootImage(w,h);
        width = w;
        height = h;
        image.setColor(Color.BLACK);
        image.fill();
        image.setColor(Color.RED);
        image.setFont(new Font(h));
        image.drawString(text,10,h);
        
        setImage(image);
    }
    public void act() 
    {
        // Add your action code here.
    }  
    public void update(String text){
        image.clear();
        image.setColor(Color.BLACK);
        image.fill();
        image.setColor(Color.RED);
        image.drawString(text,10,height);
        
        setImage(image);
    }
}
