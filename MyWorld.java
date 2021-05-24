import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    private int[][] grid;
    private Cell[][] cells;
    private int width = 16;
    private int height = 16;
    private int bombs = 40;
    private int flagTotal = 40;
    private GreenfootImage[] image = new GreenfootImage[9];
    private GreenfootImage bomb = new GreenfootImage("bomb.png");
    
    private int secondsElapsed;
    private int count = 0;
    
    private boolean gameover = false;
    private boolean won = false;
    private boolean started = false;
    
    private Text time;
    private Text flags;
    private Reset reset;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(16, 16+1, 32); 
        grid = new int[width][height];
        cells = new Cell[width][height];
        for(int i = 0; i <=8; i++){
            image[i] = new GreenfootImage(i + ".png");
        }
        
        spawnMines();
        printGrid();
        time =new Text(formatTime(secondsElapsed),100,30);
        flags = new Text("" + flagTotal, 60,30);
        reset = new Reset();
        addObject(time, 1,0);
        addObject(flags,getWidth(),0);
        addObject(reset,getWidth()/2,0);
        
        
        GreenfootImage bg = new GreenfootImage(1,1);
        bg.setColor(Color.GRAY);
        bg.fill();
        setBackground(bg);
    }
    public void act(){
        if(gameover || won || !started) return;
        count++;
        if(count%60== 0) secondsElapsed++;
        time.update(formatTime(secondsElapsed));
        checkWin();
    }
    public void start(){
        started = true;
    }
    public void checkWin(){
        int winCondition = width*height;
        int tally = 0;
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(cells[i][j].clicked() || grid[i][j] == -1){
                    tally++;
                }
                else return;
            }
        }
        if(tally == winCondition){
            won = true;
            showText("You win!", getWidth()/2+3, 0);
        }
    }
    public int getFlags(){
        return flagTotal;
    }
    public void setFlags(int flags){
        flagTotal = flags;
        this.flags.update(""+flagTotal);
    }
    private String formatTime(int s){
        String zeroes[] = new String[2];
        zeroes[0] = "";
        zeroes[1] = "";
        int minutes = s/60;
        int seconds = s-minutes*60;
        if(minutes < 10) zeroes[0] ="0";
        if(seconds < 10) zeroes[1] = "0";
        
        return "" + zeroes[0] + minutes +":" + zeroes[1] + seconds;
        
    }
    public void printGrid(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(grid[i][j] == -1){
                    cells[i][j] = new Cell(bomb, grid[i][j]);
                    addObject(cells[i][j], i, j+1);
                }
                else{
                    cells[i][j] =new Cell(image[grid[i][j]], grid[i][j]);
                    addObject(cells[i][j], i, j+1);
                }
            }
        }
    }
    public void spawnMines(){
        for(int i = 0; i < bombs; i++){
            int x = Greenfoot.getRandomNumber(width);
            int y = Greenfoot.getRandomNumber(height);
            while(grid[x][y] == -1){
                x = Greenfoot.getRandomNumber(width);
                y = Greenfoot.getRandomNumber(height);
            }
            grid[x][y] = -1;
            markSurroundings(x,y);
        }
        
        
    }
    public void endGame(){
        gameover = true;
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(grid[i][j] == -1){
                    cells[i][j].reveal();
                    
                }
            }
        }
        reset.setImage(new GreenfootImage("gameover.png"));
    }
    public boolean gameOver(){
        return gameover;
    }   
    private void markSurroundings(int x, int y){
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                
                try{
                    if(grid[x+i][y+j] != -1){
                        grid[x+i][y+j]++;
                    }
                }catch(Exception e){}
            }
        }
        
    }
    
}
