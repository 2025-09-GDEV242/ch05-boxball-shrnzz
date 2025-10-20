import java.awt.Color;
import java.util.*;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Box box;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     * 
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
        box=new Box (100,100,500,400, myCanvas);
        box.draw();
        
    }

    /**
     * boxBounce - simulate 5-50 balls bouncing within a box
     * 
     * @param numOfBalls number of balls to simulate bouncing, clamped between 5-50. 
     */
    public void boxBounce(int numOfBalls)
    {
        // you must implement this
        // clamp between 5 and 30
        if (numOfBalls < 5) 
        numOfBalls = 5;
        if (numOfBalls > 50) 
        numOfBalls = 50;
        
        myCanvas.setVisible(true);
        myCanvas.erase();
        box.draw();
        
        Random rand = new Random();
        ArrayList<BoxBall> balls = new ArrayList<>();
        
        int left = box.getLeftWall();
        int right = box.getRightWall();
        int top = box.getTopWall();
        int bottom = box.getBottomWall();
        
        // create balls
        for (int i = 0; i < numOfBalls; i++) {
            int diameter = rand.nextInt(20) + 10; // 10–30 pixels
            int x = rand.nextInt(right - left - diameter) + left;
            int y = rand.nextInt(bottom - top - diameter) + top;
            
            // create random colors
            int r = rand.nextInt(200);
            int g = rand.nextInt(200);
            int b = rand.nextInt(200);
            Color color = new Color(r, g, b);
            
            BoxBall bball = new BoxBall(x, y, diameter, color, box, myCanvas);
            bball.draw();
            balls.add(bball);
       
        }
    
        // make balls move forever 
        while (true) {
        myCanvas.wait(50);
        for (BoxBall ball : balls) {
            ball.move();
        }
        box.draw(); // redraw box so edges stay visible
        }
    }
    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
