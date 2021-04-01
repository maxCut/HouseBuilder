import java.awt.Graphics;
/*
 * This class represents the vertecies of objects in the sketch
 */
public class Vertex {
    final double RADIUS = 15.0;
    double xPos;
    double yPos;

    public Vertex(double x, double y)
    {
        xPos = x;
        yPos = y;
    }

    public void update(double x, double y)
    {
        xPos = x;
        yPos = y;
    }
    
    public boolean contains(int x, int y)
    {
        double distance = Math.sqrt(Math.pow(xPos-x,2) + Math.pow(yPos-y, 2));
        return distance<RADIUS;
    }

    public void draw(Graphics g)
    {
        int xDraw = (int)(xPos - RADIUS/2);
        int yDraw = (int)(yPos - RADIUS/2);
        g.drawOval(xDraw, yDraw, (int)RADIUS, (int)RADIUS);
    }
}
