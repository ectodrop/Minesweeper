import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Reset here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Reset extends Actor
{
    /**
     * Act - do whatever the Reset wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage normal;
    private MouseInfo mouse;
    public Reset(){
        normal = new GreenfootImage("reset.png");
        setImage(normal);
    }
    public void act() 
    {
        // Add your action code here.
        mouse = Greenfoot.getMouseInfo();
        if(mouse!=null){
            if(Greenfoot.mousePressed(this)){
                if(mouse.getButton() == 1){
                    Greenfoot.setWorld(new MyWorld());
                }
            }
            
        }
    }    
}
