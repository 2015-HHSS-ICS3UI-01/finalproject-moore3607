

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author moore3607
 */


public class Game extends JComponent implements KeyListener, MouseMotionListener, MouseListener{

    // Height and Width of our game
    static final int WIDTH = 1200;
    static final int HEIGHT = 800;
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000)/desiredFPS;
    
    //player position variables
    int x = 100;
    int y = 500;
    //mouse variables
    int mouseX = 0;
    int mouseY = 0;
    
    boolean buttonPressed = false;
    
    
    
    // block
    
    ArrayList<Rectangle> blocks = new ArrayList<>();
    
    ArrayList<Rectangle> pellets = new ArrayList<>();
    //player
    Rectangle player = new Rectangle(600,600,50,50);
    //ghostie wosties
    Rectangle blinky = new Rectangle(610, 210, 50, 50);
    Rectangle pinky = new Rectangle(550, 210, 50, 50);
    Rectangle inky = new Rectangle(670,210,50,50);
    int moveX = 0;
    int moveY = 0;
    int blinkyX = 0;
    int blinkyY = 0;
    int pinkyX = 0;
    int pinkyY = 0;
    int inkyX = 0;
    int inkyY = 0;
    
    //keyboard variables
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    

    
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE 
        g.setColor(Color.BLACK);
        g.fillRect(-3, -3, 8000, 8000);
        //created the walls for Snack-Man
        g.setColor(Color.BLUE);
        for(Rectangle block: blocks){
            g.fillRect(block.x, block.y, block.width, block.height);
        }
        
        //balls
        g.setColor(Color.YELLOW);
        for(Rectangle circle: pellets){
            g.fillOval(circle.x, circle.y, circle.width, circle.height);
        }
        
       
        
        //player
        g.setColor(Color.YELLOW);
        g.fillRect(player.x, player.y, 50, 50);
        //ghostie wosties
        g.setColor(Color.RED);
        g.fillRect(blinky.x, blinky.y, 50, 50);
        g.setColor(Color.PINK);
        g.fillRect(pinky.x, pinky.y, 50, 50);
        g.setColor(Color.cyan);
        g.fillRect(inky.x, inky.y, 50, 50);
        
        
        // GAME DRAWING ENDS HERE
    }
    
    
    // The main game loop
    // In here is where all the logic for my game will go
    public void run()
    {
        
        //blocks
        //PERIMETER
        //main floor left
        blocks.add(new Rectangle(0,720,160,20));
        //main floor right
        blocks.add(new Rectangle(1040,720,160,20));
        //bottom left wall
        blocks.add(new Rectangle(0,520,20,200));
        //bottom left roof
        blocks.add(new Rectangle(0,520,200,20));
        //transition from bottom to middle
        blocks.add(new Rectangle(180,450,20,70));
        //middle left floor
        blocks.add(new Rectangle(0,450,180,20));
        //middle left wall
        blocks.add(new Rectangle(0,270,20,200));
        //middle left roof
        blocks.add(new Rectangle(0,270,195,60));
        //transition from middle to top
        blocks.add(new Rectangle(180,220,15,50));
        //top left floor
        blocks.add(new Rectangle(0,220,180,20));
        //top left wall
        blocks.add(new Rectangle(0,70,20,150));
        //main roof
        blocks.add(new Rectangle(0,70,1200,20));
        //top right wall
        blocks.add(new Rectangle(1180,70,20,170));
        //top right floor
        blocks.add(new Rectangle(940,220,240,20));
        //transition from top to middle
        blocks.add(new Rectangle(1000,220,20,50));
        //middle right roof
        blocks.add(new Rectangle(1000,270,200,20));
        //middle right wall
        blocks.add(new Rectangle(1180,270,20,200));
        //middle right floor
        blocks.add(new Rectangle(1000,440,200,30));
        //transition from middle to bottom
        blocks.add(new Rectangle(1000,470,20,50));
        //bottom right roof
        blocks.add(new Rectangle(1000,520,200,20));
        //bottom right wall
        blocks.add(new Rectangle(1180,520,20,200));
        
        //GHOST CHAMBER
        //ghost chamber floor
        blocks.add(new Rectangle(490,350,300,20));
        //ghost chamber left wall
        blocks.add(new Rectangle(490,220,20,230));
        //ghost chamber right wall
        blocks.add(new Rectangle(770,250,50,200));
        //ghost chamber left roof
        blocks.add(new Rectangle(490,250,100,20));
        //ghost chamber right roof
        blocks.add(new Rectangle(690,250,100,20));
        //left long horizontal line below ghost chamber
        blocks.add(new Rectangle(430,590,440,20));
        
        
        //TOP AND MIDDLE LEFT
        //top left T horizontal
        blocks.add(new Rectangle(200,140,375,30));
        //top left T virtical
        blocks.add(new Rectangle(320,140,30,190));
        //vertical line to the right of top left T
        blocks.add(new Rectangle(400,220,30,390));
        //horizontal line to the left of top left T
        blocks.add(new Rectangle(70,140,70,30));
        //1st horizontal line intersecting left of the vertical line (right of T)
        blocks.add(new Rectangle(200,380,200,20));
        //horizontal line in the middle of middle left indent
        blocks.add(new Rectangle(70,380,70,20));
        //line in the middle of middle to top transition
        blocks.add(new Rectangle(255,220,10,110));
        //line hangling from right side of T horizontal
        blocks.add(new Rectangle(560,140,15,50));
        //horizontal line to the right of transition from bottom to middle
        blocks.add(new Rectangle(260,450,90,20));
        
        //TOP AND MIDDLE RIGHT
        //top middle virtical line
        blocks.add(new Rectangle(630,70,20,130));
        //top right C vertical
        blocks.add(new Rectangle(870,140,20,470));
        //top right C top horizontal
        blocks.add(new Rectangle(870,140,260,30));
        //top right C bottom horizontal
        blocks.add(new Rectangle(870,370,220,20));
        //horizontal line intersecting left of top right C
        blocks.add(new Rectangle(790,180,80,20));
        //horizontal line intersecting right of top middle line
        blocks.add(new Rectangle(650,180,80,20));
        //2nd wall (transition) on right middle indent
        blocks.add(new Rectangle(940,220,20,100));
        //2nd wall (roof) on right middle indent
        blocks.add(new Rectangle(940,300,220,20));
        //2nd wall (corner) on right middle indent
        blocks.add(new Rectangle(1140,300,20,150));
        
        //BOTTOM LEFT
        //left line hanging from transition horizontal by bottom left
        blocks.add(new Rectangle(260,450,20,160));
        //rright side line from ^
        blocks.add(new Rectangle(335,530,10,80));
        //top horizontal line going into bottom left indent
        blocks.add(new Rectangle(70,590,210,20));
        //verticle line going down in bottom left indent
        blocks.add(new Rectangle(70,590,20,80));
        //left verticle line going up from the main floor
        blocks.add(new Rectangle(140,660,20,60));
        //right verticle line going up from the main floor
        blocks.add(new Rectangle(1040,660,20,60));
        //long horizontal line above main floor
        blocks.add(new Rectangle(140,660,990,20));
        
        //BOTTOM RIGHT
        
        
        
        pellets.add(new Rectangle(35, 105, 20, 20));
        pellets.add(new Rectangle(75, 105, 20, 20));
        pellets.add(new Rectangle(115, 105, 20, 20));
        pellets.add(new Rectangle(155, 105, 20, 20));
        pellets.add(new Rectangle(195, 105, 20, 20));
        pellets.add(new Rectangle(235, 105, 20, 20));
        pellets.add(new Rectangle(275, 105, 20, 20));
        pellets.add(new Rectangle(315, 105, 20, 20));
        pellets.add(new Rectangle(355, 105, 20, 20));
        pellets.add(new Rectangle(395, 105, 20, 20));
        pellets.add(new Rectangle(435, 105, 20, 20));
        pellets.add(new Rectangle(475, 105, 20, 20));
        pellets.add(new Rectangle(515, 105, 20, 20));
        pellets.add(new Rectangle(555, 105, 20, 20));
        pellets.add(new Rectangle(595, 105, 20, 20));
        
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;
        
        // the main game loop section
        // game will end if you set done = false;
        boolean done = false; 
        while(!done)
        {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();
            
            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            
            x = mouseX;
            y = mouseY;
            //moving the player
            if(left){
                right = false;
                up = false;
                down = false;
                moveY = 0;
                moveX = -2;
            }else if(right){
                left = false;
                up = false;
                down = false;
                moveY = 0;
                moveX = 2;
            }else if(up){
                left = false;
                right = false;
                down = false;
                moveX = 0;
                moveY = -2;
            }else if(down){
                left = false;
                right = false;
                up = false;
                moveX = 0;
                moveY = 2;
            }
            int randnum = (int) (Math.random() * (5 - 1 + 1)) + 1;
            
            if(blinkyX == 0 && blinkyY == 0){
                blinkyX = 0;
                blinkyY = 0;
            }
            blinky.x = blinky.x + blinkyX;
            blinky.y = blinky.y + blinkyY;
            pinky.x = pinky.x + pinkyX;
            pinky.y = pinky.y + pinkyY;
            inky.x = inky.x + inkyX;
            inky.y = inky.y + inkyY;        
            player.x = player.x + moveX;
            player.y = player.y + moveY;
            
            //go through all blocks
            for(Rectangle block: blocks){
                //is the player hitting a block
               if(player.intersects(block)){
                   //get the collision rectangle
                   Rectangle intersection = player.intersection(block);
                   //fix the x movement
                   if(intersection.width < intersection.height){
                       if(player.x < block.x){
                           //move the player the overlap
                           player.x = player.x - intersection.width;
                       }else{
                           player.x = player.x + intersection.width;
                       }
                   }else{ //fix the y
                       //hit the block with my head
                       if(player.y > block.y){
                           player.y = player.y + intersection.height;
                           moveY = 0;
                       }else{
                           player.y = player.y - intersection.height;
                           moveY = 0;
                       }
                   }
               } 
            }
            
            //go through all blocks
            for(Rectangle block: blocks){
                //is the player hitting a block
               if(blinky.intersects(block)){
                   //get the collision rectangle
                   Rectangle intersection = blinky.intersection(block);
                   //fix the x movement
                   if(intersection.width < intersection.height){
                       if(blinky.x < block.x){
                           //move the player the overlap
                           blinky.x = blinky.x - intersection.width;
                       }else{
                           blinky.x = blinky.x + intersection.width;
                       }
                   }else{ //fix the y
                       //hit the block with my head
                       if(blinky.y > block.y){
                           blinky.y = blinky.y + intersection.height;
                           blinkyY = 0;
                       }else{
                           blinky.y = blinky.y - intersection.height;
                           blinkyY = 0;
                       }
                   }
               } 
            }
            
            //go through all blocks
            for(Rectangle block: blocks){
                //is the player hitting a block
               if(pinky.intersects(block)){
                   //get the collision rectangle
                   Rectangle intersection = pinky.intersection(block);
                   //fix the x movement
                   if(intersection.width < intersection.height){
                       if(pinky.x < block.x){
                           //move the player the overlap
                           pinky.x = pinky.x - intersection.width;
                       }else{
                           pinky.x = pinky.x + intersection.width;
                       }
                   }else{ //fix the y
                       //hit the block with my head
                       if(pinky.y > block.y){
                           pinky.y = pinky.y + intersection.height;
                           pinkyY = 0;
                       }else{
                           pinky.y = pinky.y - intersection.height;
                           pinkyY = 0;
                       }
                   }
               } 
            }
            
            //go through all blocks
            for(Rectangle block: blocks){
                //is the player hitting a block
               if(inky.intersects(block)){
                   //get the collision rectangle
                   Rectangle intersection = inky.intersection(block);
                   //fix the x movement
                   if(intersection.width < intersection.height){
                       if(inky.x < block.x){
                           //move the player the overlap
                           inky.x = inky.x - intersection.width;
                       }else{
                           inky.x = inky.x + intersection.width;
                       }
                   }else{ //fix the y
                       //hit the block with my head
                       if(inky.y > block.y){
                           inky.y = inky.y + intersection.height;
                           inkyY = 0;
                       }else{
                           inky.y = inky.y - intersection.height;
                           inkyY = 0;
                       }
                   }
               } 
            }
            
            //go through all blocks
            for(Rectangle block: pellets){
                //is the player hitting a block
               if(player.intersects(block)){
                   pellets.remove(block);
                   break;
               }
            }
            

            // GAME LOGIC ENDS HERE 
            
            // update the drawing (calls paintComponent)
            repaint();
            
            
            
            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if(deltaTime > desiredTime)
            {
                //took too much time, don't wait
            }else
            {
                try
                {
                    Thread.sleep(desiredTime - deltaTime);
                }catch(Exception e){};
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");
       
        // creates an instance of my game
        Game game = new Game();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        // adds the game to the window
        frame.add(game);
         
        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
        frame.addKeyListener(game);
        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
         int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT){
            left = true;
        }else if(key == KeyEvent.VK_RIGHT){
            right = true;
        }else if(key == KeyEvent.VK_UP){
            up = true;
        }else if(key == KeyEvent.VK_DOWN){
            down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT){
            left = false;
        }else if(key == KeyEvent.VK_RIGHT){
            right = false;
        }else if(key == KeyEvent.VK_UP){
            up = false;
        }else if(key == KeyEvent.VK_DOWN){
            down = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}