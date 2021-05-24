import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cell here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cell extends Actor
{
    /**
     * Act - do whatever the Cell wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int danger;
    private boolean clicked = false;
    private boolean flagged = false;
    private GreenfootImage flag;
    private GreenfootImage starter;
    private GreenfootImage pressed;
    
    private MyWorld world;
    private MouseInfo mouse;
    public Cell(GreenfootImage pressed, int danger){
        this.pressed = pressed;
        this.danger = danger;
        flag = new GreenfootImage("flag.png");
        starter = new GreenfootImage("starter.png");
    }
    public void addedToWorld(World w){
        world = (MyWorld)w;
    }
    public void reveal(){
        setImage(pressed);
    }
    public void act() 
    {
        // Add your action code here.
        mouse = Greenfoot.getMouseInfo();
        if(mouse != null && !world.gameOver()){
            if(Greenfoot.mouseClicked(this)){
                world.start();
                int button = mouse.getButton();
                if(button== 1 && mouse.getActor() == this && !flagged){
                    if(danger == -1){
                        world.endGame();
                        return;
                    }
                    clearEdges();
                }
                if(button == 3 && mouse.getActor() == this && !clicked){
                    if(flagged){
                        setImage(starter);
                        world.setFlags(world.getFlags()+1);
                        flagged = false;
                    }else{
                        setImage(flag);
                        world.setFlags(world.getFlags()-1);
                        flagged = true;
                    }
                }
            }
        }
    }    
    public boolean clicked(){
        return clicked;
    }
    public void clearEdges(){
        clicked = true;
        setImage(pressed);
        if(danger != 0){
            return;
        }
        
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                
                Cell cell = (Cell)getOneObjectAtOffset(i,j, Cell.class);
                if(cell!= null){
                    if(!cell.clicked()){
                        cell.clearEdges();
                    }
                    
                }
            }
        }
    }
}
