
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
public class Game extends JComponent implements KeyListener, MouseMotionListener, MouseListener {

    // Height and Width of our game
    static final int WIDTH = 1200;
    static final int HEIGHT = 800;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    //player position variables
    int x = 100;
    int y = 500;
    //mouse variables
    int mouseX = 0;
    int mouseY = 0;
    boolean buttonPressed = false;
    //delay variables
    long deadtimer = 0;
    //extra variables
    boolean dead = false;
    int lives = 3;
    boolean start = false;
    int pelletcounter = 240;
    // block
    ArrayList<Rectangle> blocks = new ArrayList<>();
    ArrayList<Rectangle> pellets = new ArrayList<>();
    //player
    Rectangle player = new Rectangle(610, 610, 40, 40);
    //ghostie wosties
    Rectangle blinky = new Rectangle(20, 100, 50, 50);
    Rectangle pinky = new Rectangle(20, 670, 50, 50);
    Rectangle inky = new Rectangle(1130, 100, 50, 50);
    Rectangle clinky = new Rectangle(1130, 670, 50, 50);
    int moveX = 0;
    int moveY = 0;
    int blinkyX = 0;
    int blinkyY = 0;
    int pinkyX = 0;
    int pinkyY = 0;
    int inkyX = 0;
    int inkyY = 0;
    int clinkyX = 0;
    int clinkyY = 0;
    //keyboard variables
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        g.setColor(Color.BLACK);
        g.fillRect(-3, -3, 8000, 8000);
        //created the walls for Snack-Man
        g.setColor(Color.BLUE);
        for (Rectangle block : blocks) {
            g.fillRect(block.x, block.y, block.width, block.height);
        }

        //balls
        g.setColor(Color.YELLOW);
        for (Rectangle circle : pellets) {
            g.fillOval(circle.x, circle.y, circle.width, circle.height);
        }

        //start screen
        if (start == false) {
            Font gamefont = new Font("Arial", Font.BOLD, 50);
            g.setFont(gamefont);
            g.setColor(Color.WHITE);
            g.drawString("Press any arrow key to start!", 250, 400);

        }

        //if the player removes all pellets, the words "You Win!" will come
        //onto the screen
        if (pelletcounter == 0) {
            Font gamefont = new Font("Arial", Font.BOLD, 40);
            g.setFont(gamefont);
            g.setColor(Color.WHITE);
            g.drawString("You Win! Please reload the program to restart.", 105, 400);
        }

        //player
        g.setColor(Color.YELLOW);
        g.fillRect(player.x, player.y, 40, 40);
        //ghostie wosties
        g.setColor(Color.RED);
        g.fillRect(blinky.x, blinky.y, 50, 50);
        g.setColor(Color.PINK);
        g.fillRect(pinky.x, pinky.y, 50, 50);
        g.setColor(Color.cyan);
        g.fillRect(inky.x, inky.y, 50, 50);
        g.setColor(Color.GREEN);
        g.fillRect(clinky.x, clinky.y, 50, 50);

        //lives
        g.setColor(Color.YELLOW);
        if (lives == 3) {
            g.fillRect(20, 745, 50, 50);
            g.fillRect(80, 745, 50, 50);
            g.fillRect(140, 745, 50, 50);
        } else if (lives == 2) {
            g.fillRect(20, 745, 50, 50);
            g.fillRect(80, 745, 50, 50);
            g.setColor(Color.BLACK);
            g.fillRect(140, 745, 50, 50);
        } else if (lives == 1) {
            g.fillRect(20, 745, 50, 50);
            g.setColor(Color.BLACK);
            g.fillRect(80, 745, 50, 50);
            g.fillRect(140, 745, 50, 50);
        } else if (lives <= 0) {
            g.setColor(Color.BLACK);
            g.fillRect(20, 745, 50, 50);
            g.fillRect(80, 745, 50, 50);
            g.fillRect(140, 745, 50, 50);
            Font gamefont = new Font("Arial", Font.BOLD, 40);
            g.setFont(gamefont);
            g.setColor(Color.WHITE);
            g.drawString("You Lose! Please reload the program to restart.", 105, 400);
        }


        // GAME DRAWING ENDS HERE
    }

    public void addPellets() {
        pellets.clear();
        //first top left horizontal line (15)
        pellets.add(new Rectangle(35, 115, 20, 20));
        pellets.add(new Rectangle(75, 115, 20, 20));
        pellets.add(new Rectangle(115, 115, 20, 20));
        pellets.add(new Rectangle(155, 115, 20, 20));
        pellets.add(new Rectangle(195, 115, 20, 20));
        pellets.add(new Rectangle(235, 115, 20, 20));
        pellets.add(new Rectangle(275, 115, 20, 20));
        pellets.add(new Rectangle(315, 115, 20, 20));
        pellets.add(new Rectangle(355, 115, 20, 20));
        pellets.add(new Rectangle(395, 115, 20, 20));
        pellets.add(new Rectangle(435, 115, 20, 20));
        pellets.add(new Rectangle(475, 115, 20, 20));
        pellets.add(new Rectangle(515, 115, 20, 20));
        pellets.add(new Rectangle(555, 115, 20, 20));
        pellets.add(new Rectangle(595, 115, 20, 20));

        //top left verticle line (2)
        pellets.add(new Rectangle(35, 150, 20, 20));
        pellets.add(new Rectangle(35, 185, 20, 20));

        //second top left horizontal line (8)
        pellets.add(new Rectangle(75, 185, 20, 20));
        pellets.add(new Rectangle(115, 185, 20, 20));
        pellets.add(new Rectangle(155, 185, 20, 20));
        pellets.add(new Rectangle(195, 185, 20, 20));
        pellets.add(new Rectangle(235, 185, 20, 20));
        pellets.add(new Rectangle(275, 185, 20, 20));
        pellets.add(new Rectangle(315, 185, 20, 20));
        pellets.add(new Rectangle(355, 185, 20, 20));

        //second top left verticle line (4)
        pellets.add(new Rectangle(355, 225, 20, 20));
        pellets.add(new Rectangle(355, 265, 20, 20));
        pellets.add(new Rectangle(355, 305, 20, 20));
        pellets.add(new Rectangle(355, 345, 20, 20));

        //first middle left horizontal line (8)
        pellets.add(new Rectangle(315, 345, 20, 20));
        pellets.add(new Rectangle(275, 345, 20, 20));
        pellets.add(new Rectangle(235, 345, 20, 20));
        pellets.add(new Rectangle(195, 345, 20, 20));
        pellets.add(new Rectangle(155, 345, 20, 20));
        pellets.add(new Rectangle(115, 345, 20, 20));
        pellets.add(new Rectangle(75, 345, 20, 20));
        pellets.add(new Rectangle(35, 345, 20, 20));

        //first middle vertical line (2)
        pellets.add(new Rectangle(35, 380, 20, 20));
        pellets.add(new Rectangle(35, 415, 20, 20));

        //second middle left horizontal line (left) (5)
        pellets.add(new Rectangle(70, 415, 20, 20));
        pellets.add(new Rectangle(105, 415, 20, 20));
        pellets.add(new Rectangle(140, 415, 20, 20));
        pellets.add(new Rectangle(175, 415, 20, 20));
        pellets.add(new Rectangle(210, 415, 20, 20));

        //second left middle horizontal line(right) (2)
        pellets.add(new Rectangle(320, 420, 20, 20));
        pellets.add(new Rectangle(355, 420, 20, 20));

        //second middle verticle line (4)
        pellets.add(new Rectangle(215, 445, 20, 20));
        pellets.add(new Rectangle(215, 480, 20, 20));
        pellets.add(new Rectangle(215, 515, 20, 20));
        pellets.add(new Rectangle(215, 555, 20, 20));

        //first bottom left horizontal (5)
        pellets.add(new Rectangle(180, 555, 20, 20));
        pellets.add(new Rectangle(145, 555, 20, 20));
        pellets.add(new Rectangle(110, 555, 20, 20));
        pellets.add(new Rectangle(75, 555, 20, 20));
        pellets.add(new Rectangle(35, 555, 20, 20));

        //first bottom left vertical (4)
        pellets.add(new Rectangle(35, 585, 20, 20));
        pellets.add(new Rectangle(35, 620, 20, 20));
        pellets.add(new Rectangle(35, 655, 20, 20));
        pellets.add(new Rectangle(35, 685, 20, 20));

        //second bottom left horizontal (2)
        pellets.add(new Rectangle(70, 685, 20, 20));
        pellets.add(new Rectangle(105, 685, 20, 20));

        //second bottom left vertical (2)
        pellets.add(new Rectangle(105, 655, 20, 20));
        pellets.add(new Rectangle(105, 625, 20, 20));

        //third bottom left horizontal (5)
        pellets.add(new Rectangle(140, 625, 20, 20));
        pellets.add(new Rectangle(175, 625, 20, 20));
        pellets.add(new Rectangle(210, 625, 20, 20));
        pellets.add(new Rectangle(245, 625, 20, 20));
        pellets.add(new Rectangle(285, 625, 20, 20));

        //third bottom left vertical (5)
        pellets.add(new Rectangle(285, 585, 20, 20));
        pellets.add(new Rectangle(285, 545, 20, 20));
        pellets.add(new Rectangle(285, 505, 20, 20));
        pellets.add(new Rectangle(285, 465, 20, 20));
        pellets.add(new Rectangle(285, 420, 20, 20));

        //fourth bottom left verticle line (6)
        pellets.add(new Rectangle(355, 455, 20, 20));
        pellets.add(new Rectangle(355, 490, 20, 20));
        pellets.add(new Rectangle(355, 525, 20, 20));
        pellets.add(new Rectangle(355, 560, 20, 20));
        pellets.add(new Rectangle(355, 595, 20, 20));
        pellets.add(new Rectangle(355, 630, 20, 20));

        //bottom (last) outer center horizontal line (8)
        pellets.add(new Rectangle(395, 630, 20, 20));
        pellets.add(new Rectangle(430, 630, 20, 20));
        pellets.add(new Rectangle(465, 630, 20, 20));
        pellets.add(new Rectangle(495, 630, 20, 20));
        pellets.add(new Rectangle(525, 630, 20, 20));
        pellets.add(new Rectangle(560, 630, 20, 20));
        pellets.add(new Rectangle(595, 630, 20, 20));
        pellets.add(new Rectangle(630, 630, 20, 20));

        //middle outer center horizontal line (7)
        pellets.add(new Rectangle(625, 555, 20, 20));
        pellets.add(new Rectangle(590, 555, 20, 20));
        pellets.add(new Rectangle(555, 555, 20, 20));
        pellets.add(new Rectangle(520, 555, 20, 20));
        pellets.add(new Rectangle(485, 555, 20, 20));
        pellets.add(new Rectangle(455, 555, 20, 20));
        pellets.add(new Rectangle(425, 555, 20, 20));

        //left-most outer center vertical line (9)
        pellets.add(new Rectangle(425, 515, 20, 20));
        pellets.add(new Rectangle(425, 475, 20, 20));
        pellets.add(new Rectangle(425, 435, 20, 20));
        pellets.add(new Rectangle(425, 395, 20, 20));
        pellets.add(new Rectangle(425, 355, 20, 20));
        pellets.add(new Rectangle(425, 315, 20, 20));
        pellets.add(new Rectangle(425, 275, 20, 20));
        pellets.add(new Rectangle(425, 230, 20, 20));
        pellets.add(new Rectangle(425, 185, 20, 20));

        //top outer center horizontal line (10)
        pellets.add(new Rectangle(465, 185, 20, 20));
        pellets.add(new Rectangle(505, 185, 20, 20));
        pellets.add(new Rectangle(545, 185, 20, 20));
        pellets.add(new Rectangle(590, 185, 20, 20));
        pellets.add(new Rectangle(625, 185, 20, 20));
        pellets.add(new Rectangle(665, 185, 20, 20));
        pellets.add(new Rectangle(705, 185, 20, 20));
        pellets.add(new Rectangle(745, 185, 20, 20));
        pellets.add(new Rectangle(790, 185, 20, 20));
        pellets.add(new Rectangle(835, 185, 20, 20));

        //right-most outer center vertical line (11)
        pellets.add(new Rectangle(835, 225, 20, 20));
        pellets.add(new Rectangle(835, 260, 20, 20));
        pellets.add(new Rectangle(835, 305, 20, 20));
        pellets.add(new Rectangle(835, 350, 20, 20));
        pellets.add(new Rectangle(835, 390, 20, 20));
        pellets.add(new Rectangle(835, 430, 20, 20));
        pellets.add(new Rectangle(835, 470, 20, 20));
        pellets.add(new Rectangle(835, 510, 20, 20));
        pellets.add(new Rectangle(835, 550, 20, 20));
        pellets.add(new Rectangle(835, 590, 20, 20));
        pellets.add(new Rectangle(835, 625, 20, 20));

        //bottom inner center horizontal line (leading into/from lower right) (11)
        pellets.add(new Rectangle(1045, 625, 20, 20));
        pellets.add(new Rectangle(1010, 625, 20, 20));
        pellets.add(new Rectangle(975, 625, 20, 20));
        pellets.add(new Rectangle(940, 625, 20, 20));
        pellets.add(new Rectangle(905, 625, 20, 20));
        pellets.add(new Rectangle(870, 625, 20, 20));
        pellets.add(new Rectangle(835, 625, 20, 20));
        pellets.add(new Rectangle(800, 625, 20, 20));
        pellets.add(new Rectangle(765, 625, 20, 20));
        pellets.add(new Rectangle(730, 625, 20, 20));
        pellets.add(new Rectangle(695, 625, 20, 20));
        

        //bottom inner center vertical line (2)
        pellets.add(new Rectangle(695, 590, 20, 20));
        pellets.add(new Rectangle(695, 555, 20, 20));

        //second bottom (second last) inner center horizontal line (2)
        pellets.add(new Rectangle(730, 555, 20, 20));
        pellets.add(new Rectangle(765, 555, 20, 20));

        //right-most inner center vertical line (7)
        pellets.add(new Rectangle(765, 530, 20, 20));
        pellets.add(new Rectangle(765, 500, 20, 20));
        pellets.add(new Rectangle(765, 465, 20, 20));
        pellets.add(new Rectangle(765, 430, 20, 20));
        pellets.add(new Rectangle(765, 395, 20, 20));
        pellets.add(new Rectangle(765, 360, 20, 20));
        pellets.add(new Rectangle(765, 325, 20, 20));

        //middle inner center horizontal line (5)
        pellets.add(new Rectangle(725, 395, 20, 20));
        pellets.add(new Rectangle(685, 395, 20, 20));
        pellets.add(new Rectangle(640, 395, 20, 20));
        pellets.add(new Rectangle(600, 395, 20, 20));
        pellets.add(new Rectangle(565, 395, 20, 20));

        //right core vertical (3)
        pellets.add(new Rectangle(640, 425, 20, 20));
        pellets.add(new Rectangle(640, 455, 20, 20));
        pellets.add(new Rectangle(640, 490, 20, 20));

        //right core lower section (4)
        pellets.add(new Rectangle(670, 490, 20, 20));
        pellets.add(new Rectangle(700, 490, 20, 20));
        pellets.add(new Rectangle(700, 460, 20, 20));
        pellets.add(new Rectangle(730, 460, 20, 20));

        //left core lower section (5)
        pellets.add(new Rectangle(565, 425, 20, 20));
        pellets.add(new Rectangle(565, 455, 20, 20));
        pellets.add(new Rectangle(565, 485, 20, 20));
        pellets.add(new Rectangle(530, 485, 20, 20));
        pellets.add(new Rectangle(495, 485, 20, 20));

        //left-most inner center vertical line (6)
        pellets.add(new Rectangle(495, 445, 20, 20));
        pellets.add(new Rectangle(495, 405, 20, 20));
        pellets.add(new Rectangle(495, 365, 20, 20));
        pellets.add(new Rectangle(495, 325, 20, 20));
        pellets.add(new Rectangle(495, 290, 20, 20));
        pellets.add(new Rectangle(495, 255, 20, 20));

        //top inner center horizontal line (9)
        pellets.add(new Rectangle(535, 255, 20, 20));
        pellets.add(new Rectangle(575, 255, 20, 20));
        pellets.add(new Rectangle(615, 255, 20, 20));
        pellets.add(new Rectangle(655, 255, 20, 20));
        pellets.add(new Rectangle(695, 255, 20, 20));
        pellets.add(new Rectangle(735, 255, 20, 20));
        pellets.add(new Rectangle(775, 255, 20, 20));
        pellets.add(new Rectangle(805, 255, 20, 20));
        pellets.add(new Rectangle(495, 255, 20, 20));

        //second inner center horizontal line (6)
        pellets.add(new Rectangle(725, 325, 20, 20));
        pellets.add(new Rectangle(685, 325, 20, 20));
        pellets.add(new Rectangle(645, 325, 20, 20));
        pellets.add(new Rectangle(605, 325, 20, 20));
        pellets.add(new Rectangle(565, 325, 20, 20));
        pellets.add(new Rectangle(530, 325, 20, 20));
        
        //first right horizontal line (13)
        pellets.add(new Rectangle(665, 115, 20, 20));
        pellets.add(new Rectangle(705, 115, 20, 20));
        pellets.add(new Rectangle(745, 115, 20, 20));
        pellets.add(new Rectangle(785, 115, 20, 20));
        pellets.add(new Rectangle(825, 115, 20, 20));
        pellets.add(new Rectangle(865, 115, 20, 20));
        pellets.add(new Rectangle(905, 115, 20, 20));
        pellets.add(new Rectangle(945, 115, 20, 20));
        pellets.add(new Rectangle(985, 115, 20, 20));
        pellets.add(new Rectangle(1025, 115, 20, 20));
        pellets.add(new Rectangle(1065, 115, 20, 20));
        pellets.add(new Rectangle(1105, 115, 20, 20));
        pellets.add(new Rectangle(1145, 115, 20, 20));
        
        //first right verticle line (2)
        pellets.add(new Rectangle(1145, 150, 20, 20));
        pellets.add(new Rectangle(1145, 185, 20, 20));
        
        //second right horizontal line (6)
        pellets.add(new Rectangle(1105, 185, 20, 20));
        pellets.add(new Rectangle(1065, 185, 20, 20));
        pellets.add(new Rectangle(1025, 185, 20, 20));
        pellets.add(new Rectangle(985, 185, 20, 20));
        pellets.add(new Rectangle(945, 185, 20, 20));
        pellets.add(new Rectangle(905, 185, 20, 20));
        
        //second right vertical line (4)
        pellets.add(new Rectangle(905, 225, 20, 20));
        pellets.add(new Rectangle(905, 265, 20, 20));
        pellets.add(new Rectangle(905, 300, 20, 20));
        pellets.add(new Rectangle(905, 335, 20, 20));
        
        //third right horizontal line (6)
        pellets.add(new Rectangle(945, 335, 20, 20));
        pellets.add(new Rectangle(985, 335, 20, 20));
        pellets.add(new Rectangle(1025, 335, 20, 20));
        pellets.add(new Rectangle(1065, 335, 20, 20));
        pellets.add(new Rectangle(1105, 335, 20, 20));
        pellets.add(new Rectangle(1145, 335, 20, 20));
        
        //third right vertical line (2)
        pellets.add(new Rectangle(1145, 370, 20, 20));
        pellets.add(new Rectangle(1145, 405, 20, 20));
        
        //fourth right horizontal line (6)
        pellets.add(new Rectangle(1105, 405, 20, 20));
        pellets.add(new Rectangle(1065, 405, 20, 20));
        pellets.add(new Rectangle(1025, 405, 20, 20));
        pellets.add(new Rectangle(985, 405, 20, 20));
        pellets.add(new Rectangle(945, 405, 20, 20));
        pellets.add(new Rectangle(905, 405, 20, 20));
        
        //fourth right vertical line (4)
        pellets.add(new Rectangle(905, 445, 20, 20));
        pellets.add(new Rectangle(905, 480, 20, 20));
        pellets.add(new Rectangle(905, 515, 20, 20));
        pellets.add(new Rectangle(905, 555, 20, 20));
        
        //fifth right horizontal line (6)
        pellets.add(new Rectangle(945, 555, 20, 20));
        pellets.add(new Rectangle(985, 555, 20, 20));
        pellets.add(new Rectangle(1025, 555, 20, 20));
        pellets.add(new Rectangle(1065, 555, 20, 20));
        pellets.add(new Rectangle(1105, 555, 20, 20));
        pellets.add(new Rectangle(1145, 555, 20, 20));
        
        //fifth right vertical line (3)
        pellets.add(new Rectangle(1145, 600, 20, 20));
        pellets.add(new Rectangle(1145, 640, 20, 20));
        pellets.add(new Rectangle(1145, 685, 20, 20));
        
        //sixth right horizontal line (2)
        pellets.add(new Rectangle(1110, 685, 20, 20));
        pellets.add(new Rectangle(1075, 685, 20, 20));
        
        //sixth right vertical line (2)
        pellets.add(new Rectangle(1075, 655, 20, 20));
        pellets.add(new Rectangle(1075, 625, 20, 20));
        
        
        
        
        
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {


        //blocks
        //PERIMETER
        //main floor left
        blocks.add(new Rectangle(0, 720, 160, 20));
        //main floor right
        blocks.add(new Rectangle(1040, 720, 160, 20));
        //bottom left wall
        blocks.add(new Rectangle(0, 520, 20, 200));
        //bottom left roof
        blocks.add(new Rectangle(0, 520, 200, 20));
        //transition from bottom to middle
        blocks.add(new Rectangle(180, 450, 20, 70));
        //middle left floor
        blocks.add(new Rectangle(0, 450, 180, 20));
        //middle left wall
        blocks.add(new Rectangle(0, 320, 20, 130));
        //middle left roof
        blocks.add(new Rectangle(0, 310, 320, 20));
        //transition from middle to top
        blocks.add(new Rectangle(320, 220, 20, 110));
        //top left floor
        blocks.add(new Rectangle(0, 220, 340, 20));
        //top left wall
        blocks.add(new Rectangle(0, 80, 20, 140));
        //main roof 
        blocks.add(new Rectangle(0, 80, 1180, 20));
        //top right wall
        blocks.add(new Rectangle(1180, 80, 20, 160));
        //top right floor
        blocks.add(new Rectangle(940, 220, 240, 20));
        //transition from top to middle
        blocks.add(new Rectangle(940, 220, 20, 100));
        //middle right roof
        blocks.add(new Rectangle(940, 300, 260, 20));
        //middle right wall
        blocks.add(new Rectangle(1180, 300, 20, 160));
        //middle right floor
        blocks.add(new Rectangle(940, 440, 260, 20));
        //transition from middle to bottom
        blocks.add(new Rectangle(940, 460, 20, 60));
        //bottom right roof
        blocks.add(new Rectangle(940, 520, 260, 20));
        //bottom right wall
        blocks.add(new Rectangle(1180, 520, 20, 200));

        //CENTER
        //left long horizontal line below center
        blocks.add(new Rectangle(410, 590, 200, 20));
        //corner (vertical) at bottom leading into center area
        blocks.add(new Rectangle(660, 520, 20, 140));
        //line (horizontal) leading left from the corner
        blocks.add(new Rectangle(460, 520, 290, 20));
        //middle line poking up from ^
        blocks.add(new Rectangle(600, 430, 20, 100));
        //(top) left line leading back to top from ^^
        blocks.add(new Rectangle(460, 220, 20, 140));
        //(bottom) left line leading back to top from ^^
        blocks.add(new Rectangle(460, 420, 20, 100));
        //right line poking up from ^^^
        blocks.add(new Rectangle(735, 510, 15, 30));
        //line leading right from ^^
        blocks.add(new Rectangle(460, 220, 360, 20));
        //line leading right from middle poke
        blocks.add(new Rectangle(670, 430, 80, 20));
        //line leading down from ^
        blocks.add(new Rectangle(670, 430, 15, 40));


        //top part of inner center 
        //(bottom) line leading down from ^^
        blocks.add(new Rectangle(800, 290, 20, 320));
        //line leading left from top part of (bottom)
        blocks.add(new Rectangle(530, 290, 280, 20));
        //line (with hole above it) leading down from ^
        blocks.add(new Rectangle(530, 360, 20, 110));
        //line leading right from ^
        blocks.add(new Rectangle(530, 360, 220, 20));
        //line leading left from bottom part of (bottom)
        blocks.add(new Rectangle(730, 590, 90, 20));


        //bottom part of inner center


        //TOP AND MIDDLE LEFT
        //(left) top left T horizontal
        blocks.add(new Rectangle(70, 150, 100, 20));
        //(right) top left T horizontal
        blocks.add(new Rectangle(230, 150, 340, 20));
        //(top) top left T vertical
        blocks.add(new Rectangle(390, 160, 20, 90));
        //(middle) top T vertical
        blocks.add(new Rectangle(390, 310, 20, 130));
        //(bottom) top left T vertical
        blocks.add(new Rectangle(390, 500, 20, 110));

        //TOP AND MIDDLE RIGHT
        //top middle virtical line
        blocks.add(new Rectangle(630, 80, 20, 90));
        //(top) top right C vertical
        blocks.add(new Rectangle(870, 150, 20, 90));
        //(middle) top right C vertical
        blocks.add(new Rectangle(870, 300, 20, 170));
        //(bottom) top right C vertical
        blocks.add(new Rectangle(870, 530, 20, 80));
        //top right C top horizontal
        blocks.add(new Rectangle(870, 150, 260, 20));
        //(left) top right C bottom horizontal
        blocks.add(new Rectangle(870, 370, 130, 20));
        //(right) top right C bottom horizontal
        blocks.add(new Rectangle(1060, 370, 70, 20));
        //horizontal line intersecting left of top right C
        blocks.add(new Rectangle(710, 150, 180, 20));


        //BOTTOM LEFT
        //horizontal line on bottom left T
        blocks.add(new Rectangle(70, 380, 330, 20));
        //(top) vertical line on bottom left T
        blocks.add(new Rectangle(250, 400, 20, 40));
        //(bottom) vertical line on bottom left T 
        blocks.add(new Rectangle(250, 500, 20, 100));
        //verticle line coming from main floor (left)
        blocks.add(new Rectangle(320, 450, 20, 210));
        //(left) horizontal line going into bottom left indent
        blocks.add(new Rectangle(70, 590, 50, 20));
        //(right) horizontal line going into bottom left indent
        blocks.add(new Rectangle(180, 590, 90, 20));
        //verticle line going down in bottom left indent
        blocks.add(new Rectangle(70, 590, 20, 80));
        //left verticle line going up from the main floor
        blocks.add(new Rectangle(140, 660, 20, 60));
        //right verticle line going up from the main floor
        blocks.add(new Rectangle(1040, 660, 20, 60));
        //long horizontal line above main floor
        blocks.add(new Rectangle(140, 660, 920, 20));

        //BOTTOM RIGHT
        //(left) line leading right from verticle line of top right C
        blocks.add(new Rectangle(870, 590, 130, 20));
        //(right) line leading right from verticle line of top right C
        blocks.add(new Rectangle(1060, 590, 70, 20));
        //line leading down from ^
        blocks.add(new Rectangle(1110, 590, 20, 80));


        //PELLETS
        addPellets();

        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {

            int randblinky = (int) (Math.random() * 4);
            int randpinky = (int) (Math.random() * 4);
            int randinky = (int) (Math.random() * 4);
            int randclinky = (int) (Math.random() * 4);
            

            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 

            if ((up
                    || down
                    || left
                    || right) && !start) {
                start = true;
            }

            if (start) {
                //moving the player
                if (left) {
                    right = false;
                    up = false;
                    down = false;
                    moveY = 0;
                    moveX = -2;
                } else if (right) {
                    left = false;
                    up = false;
                    down = false;
                    moveY = 0;
                    moveX = 2;
                } else if (up) {
                    left = false;
                    right = false;
                    down = false;
                    moveX = 0;
                    moveY = -2;
                } else if (down) {
                    left = false;
                    right = false;
                    up = false;
                    moveX = 0;
                    moveY = 2;
                }




                if (System.currentTimeMillis() > deadtimer) {
                    player.x = player.x + moveX;
                    player.y = player.y + moveY;
                    blinky.x = blinky.x + blinkyX;
                    blinky.y = blinky.y + blinkyY;
                    pinky.x = pinky.x + pinkyX;
                    pinky.y = pinky.y + pinkyY;
                    inky.x = inky.x + inkyX;
                    inky.y = inky.y + inkyY;
                    clinky.x = clinky.x + clinkyX;
                    clinky.y = clinky.y + clinkyY;
                    if (dead) {
                        player.x = 610;
                        player.y = 610;
                        moveX = 0;
                        moveY = 0;
                        blinky.x = 20;
                        blinky.y = 100;
                        blinkyX = 0;
                        blinkyY = 0;
                        pinky.x = 20;
                        pinky.y = 670;
                        pinkyX = 0;
                        pinkyY = 0;
                        inky.x = 1130;
                        inky.y = 100;
                        inkyX = 0;
                        inkyY = 0;
                        clinky.x = 1130;
                        clinky.y = 670;
                        clinkyX = 0;
                        clinkyY = 0;
                        dead = false;
                    }
                }


                //go through all blocks
                for (Rectangle block : blocks) {
                    //is the player hitting a block
                    if (player.intersects(block)) {
                        //get the collision rectangle
                        Rectangle intersection = player.intersection(block);
                        //fix the x movement
                        if (intersection.width < intersection.height) {
                            if (player.x < block.x) {
                                //move the player the overlap
                                player.x = player.x - intersection.width;
                            } else {
                                player.x = player.x + intersection.width;
                            }
                        } else { //fix the y
                            //hit the block with my head
                            if (player.y > block.y) {
                                player.y = player.y + intersection.height;
                                moveY = 0;
                            } else {
                                player.y = player.y - intersection.height;
                                moveY = 0;
                            }
                        }
                    }
                }

                //go through all blocks
                for (Rectangle block : blocks) {
                    //is the player hitting a block
                    if (blinky.intersects(block)) {
                        //get the collision rectangle
                        Rectangle intersection = blinky.intersection(block);
                        //fix the x movement
                        if (intersection.width < intersection.height) {
                            if (blinky.x < block.x) {
                                //move the player the overlap
                                blinky.x = blinky.x - intersection.width;
                                blinkyX = 0;
                            } else {
                                blinky.x = blinky.x + intersection.width;
                                blinkyX = 0;
                            }
                        } else { //fix the y
                            //hit the block with my head
                            if (blinky.y > block.y) {
                                blinky.y = blinky.y + intersection.height;
                                blinkyY = 0;
                            } else {
                                blinky.y = blinky.y - intersection.height;
                                blinkyY = 0;
                            }
                        }
                    }
                }

                //go through all blocks
                for (Rectangle block : blocks) {
                    //is the player hitting a block
                    if (pinky.intersects(block)) {
                        //get the collision rectangle
                        Rectangle intersection = pinky.intersection(block);
                        //fix the x movement
                        if (intersection.width < intersection.height) {
                            if (pinky.x < block.x) {
                                //move the player the overlap
                                pinky.x = pinky.x - intersection.width;
                                pinkyX = 0;
                            } else {
                                pinky.x = pinky.x + intersection.width;
                                pinkyX = 0;
                            }
                        } else { //fix the y
                            //hit the block with my head
                            if (pinky.y > block.y) {
                                pinky.y = pinky.y + intersection.height;
                                pinkyY = 0;
                            } else {
                                pinky.y = pinky.y - intersection.height;
                                pinkyY = 0;
                            }
                        }
                    }
                }

                //go through all blocks
                for (Rectangle block : blocks) {
                    //is the player hitting a block
                    if (inky.intersects(block)) {
                        //get the collision rectangle
                        Rectangle intersection = inky.intersection(block);
                        //fix the x movement
                        if (intersection.width < intersection.height) {
                            if (inky.x < block.x) {
                                //move the player the overlap
                                inky.x = inky.x - intersection.width;
                                inkyX = 0;
                            } else {
                                inky.x = inky.x + intersection.width;
                                inkyX = 0;
                            }
                        } else { //fix the y
                            //hit the block with my head
                            if (inky.y > block.y) {
                                inky.y = inky.y + intersection.height;
                                inkyY = 0;
                            } else {
                                inky.y = inky.y - intersection.height;
                                inkyY = 0;
                            }
                        }
                    }
                }

                //go through all blocks
                for (Rectangle block : blocks) {
                    //is the player hitting a block
                    if (clinky.intersects(block)) {
                        //get the collision rectangle
                        Rectangle intersection = clinky.intersection(block);
                        //fix the x movement
                        if (intersection.width < intersection.height) {
                            if (clinky.x < block.x) {
                                //move the player the overlap
                                clinky.x = clinky.x - intersection.width;
                                clinkyX = 0;
                            } else {
                                clinky.x = clinky.x + intersection.width;
                                clinkyX = 0;
                            }
                        } else { //fix the y
                            //hit the block with my head
                            if (clinky.y > block.y) {
                                clinky.y = clinky.y + intersection.height;
                                clinkyY = 0;
                            } else {
                                clinky.y = clinky.y - intersection.height;
                                clinkyY = 0;
                            }
                        }
                    }
                }

                //making pellets dissappear
                for (Rectangle block : pellets) {
                    //is the player hitting a pellet
                    if (player.intersects(block)) {
                        pellets.remove(block);
                        pelletcounter = pelletcounter - 1;
                        break;
                    }
                }

                //moving the ghostie wosties
                if (blinkyX == 0 && blinkyY == 0) {
                    if (randblinky == 0) {
                        blinkyX = -3;
                        blinkyY = 0;
                    } else if (randblinky == 1) {
                        blinkyX = 3;
                        blinkyY = 0;
                    } else if (randblinky == 2) {
                        blinkyX = 0;
                        blinkyY = -3;
                    } else if (randblinky == 3) {
                        blinkyX = 0;
                        blinkyY = 3;
                    }
                }

                if (pinkyX == 0 && pinkyY == 0) {
                    if (randpinky == 0) {
                        pinkyX = -3;
                        pinkyY = 0;
                    } else if (randpinky == 1) {
                        pinkyX = 3;
                        pinkyY = 0;
                    } else if (randpinky == 2) {
                        pinkyX = 0;
                        pinkyY = -3;
                    } else if (randpinky == 3) {
                        pinkyX = 0;
                        pinkyY = 3;
                    }
                }

                if (inkyX == 0 && inkyY == 0) {
                    if (randinky == 0) {
                        inkyX = -3;
                        inkyY = 0;
                    } else if (randinky == 1) {
                        inkyX = 3;
                        inkyY = 0;
                    } else if (randinky == 2) {
                        inkyX = 0;
                        inkyY = -3;
                    } else if (randinky == 3) {
                        inkyX = 0;
                        inkyY = 3;
                    }
                }

                if (clinkyX == 0 && clinkyY == 0) {
                    if (randclinky == 0) {
                        clinkyX = -3;
                        clinkyY = 0;
                    } else if (randclinky == 1) {
                        clinkyX = 3;
                        clinkyY = 0;
                    } else if (randclinky == 2) {
                        clinkyX = 0;
                        clinkyY = -3;
                    } else if (randclinky == 3) {
                        clinkyX = 0;
                        clinkyY = 3;
                    }
                }

                //creating death touch for ghostie wosties
                if (!dead) {
                    if (player.intersects(blinky)) {
                        deadtimer = System.currentTimeMillis() + 2000;
                        dead = true;
                        lives = lives - 1;
                    } else if (player.intersects(pinky)) {
                        deadtimer = System.currentTimeMillis() + 2000;
                        dead = true;
                        lives = lives - 1;
                    } else if (player.intersects(inky)) {
                        deadtimer = System.currentTimeMillis() + 2000;
                        dead = true;
                        lives = lives - 1;
                    } else if (player.intersects(clinky)) {
                        deadtimer = System.currentTimeMillis() + 2000;
                        dead = true;
                        lives = lives - 1;
                    }
                }
                
                if(pelletcounter == 0){
                    done = true;
                }


            }

            if (lives == 0) {
                   done = true;
            }









            // GAME LOGIC ENDS HERE 

            // update the drawing (calls paintComponent)
            repaint();



            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if (deltaTime > desiredTime) {
                //took too much time, don't wait
            } else {
                try {
                    Thread.sleep(desiredTime - deltaTime);
                } catch (Exception e) {
                };
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
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
        if (key == KeyEvent.VK_LEFT) {
            left = true;
        } else if (key == KeyEvent.VK_RIGHT) {
            right = true;
        } else if (key == KeyEvent.VK_UP) {
            up = true;
        } else if (key == KeyEvent.VK_DOWN) {
            down = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            left = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            right = false;
        } else if (key == KeyEvent.VK_UP) {
            up = false;
        } else if (key == KeyEvent.VK_DOWN) {
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