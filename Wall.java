import java.awt.Graphics;
public class Wall {
    Vertex point1;
    Vertex point2;
    final int WIDTH = 15; 
    public Wall(Vertex p1, Vertex p2)
    {
        point1 = p1;
        point2 = p2;
    }

    public boolean Contains(Vertex v)
    {
        return point1==v||point2==v;
    }

    public void draw(Graphics g)
    {
        drawOffsetLine(g, point1.xPos, point1.yPos, point2.xPos, point2.yPos, WIDTH/2);
        drawOffsetLine(g, point1.xPos, point1.yPos, point2.xPos, point2.yPos, -WIDTH/2);
    }

    /*
     * This function uses trig to offset a line relative to original line based on offset
     */
    private void drawOffsetLine(Graphics g, double x1, double y1, double x2, double y2, double offset)
    {
        double alpha = Math.atan2(y2-y1, x2-x1) - Math.PI/2; //90 degree offset of angle of line
        int line1X1 = (int)(x1 + offset * Math.cos(alpha));
        int line1Y1 = (int)(y1 + offset * Math.sin(alpha));
        int line1X2 = (int)(x2 + offset * Math.cos(alpha));
        int line1Y2 = (int)(y2 + offset * Math.sin(alpha));

        g.drawLine(line1X1, line1Y1, line1X2, line1Y2);

    }
}
