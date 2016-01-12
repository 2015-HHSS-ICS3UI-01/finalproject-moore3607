

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
    
    //player
    Rectangle player = new Rectangle(600,600,50,50);
    int moveX = 0;
    int moveY = 0;
    
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
        //created the walls for Snack-Man
        g.setColor(Color.BLUE);
        for(Rectangle block: blocks){
            g.fillRect(block.x, block.y, block.width, block.height);
        }
        
        g.setColor(Color.RED);
        g.fillRect(player.x, player.y, 50, 50);
        // GAME DRAWING ENDS HERE
    }
    
    
    // The main game loop
    // In here is where all the logic for my game will go
    public void run()
    {
        
        //blocks
        //main floor
        blocks.add(new Rectangle(0,720,1200,20));
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
        blocks.add(new Rectangle(0,270,200,20));
        //transition from middle to top
        blocks.add(new Rectangle(180,220,20,50));
        //top left floor
        blocks.add(new Rectangle(0,220,180,20));
        //top left wall
        blocks.add(new Rectangle(0,70,20,150));
        //main roof
        blocks.add(new Rectangle(0,70,1200,20));
        //top right wall
        blocks.add(new Rectangle(1180,70,20,170));
        //top right floor
        blocks.add(new Rectangle(1000,220,180,20));
        //transition from top to middle
        blocks.add(new Rectangle(1000,220,20,50));
        //middle right roof
        blocks.add(new Rectangle(1000,270,200,20));
        //middle right wall
        blocks.add(new Rectangle(1180,270,20,200));
        //middle right floor
        blocks.add(new Rectangle(1000,450,200,20));
        //transition from middle to bottom
        blocks.add(new Rectangle(1000,470,20,50));
        //bottom right roof
        blocks.add(new Rectangle(1000,520,200,20));
        //bottom right wall
        blocks.add(new Rectangle(1180,520,20,200));
        
        
        //ghost chamber floor
        blocks.add(new Rectangle(490,350,300,20));
        //ghost chamber left wall
        blocks.add(new Rectangle(490,250,20,200));
        //ghost chamber right wall
        blocks.add(new Rectangle(770,250,50,200));
        //ghost chamber left roof
        blocks.add(new Rectangle(490,250,100,20));
        //ghost chamber right roof
        blocks.add(new Rectangle(690,250,100,20));
        
        
        //top left T horizontal
        blocks.add(new Rectangle(200,140,380,30));
        //top left T virtical
        blocks.add(new Rectangle(320,140,30,200));
        //vertical line to the right of top left T
        blocks.add(new Rectangle(400,220,40,200));
        //horizontal line to the left of top left T
        blocks.add(new Rectangle(70,140,80,30));
        
        
        //top middle virtical line
        blocks.add(new Rectangle(630,70,20,130));
        //top right C vertical
        blocks.add(new Rectangle(870,140,20,250));
        //top right C top horizontal
        blocks.add(new Rectangle(870,140,260,30));
        //top right C bottom horizontal
        //horizontal line intersecting left of top right C
        blocks.add(new Rectangle(790,180,80,20));
        //horizontal line intersecting right of top middle line
        blocks.add(new Rectangle(650,180,80,20));
        
        
        
        
        
        
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
            
            player.x = player.x + moveX;
            player.y = player.y + moveY;
            
            //go through all blocks
            for(Rectangle block: blocks){
                //is the player itting a block
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