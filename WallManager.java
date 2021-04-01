import java.util.ArrayList;
import java.awt.Graphics;

public class WallManager {
    public Vertex workingVertex;
    private ArrayList<Wall> walls = new ArrayList<>();

    public void AddPoint(Vertex v)
    {
        if(workingVertex==null)
        {
            workingVertex = v;
        }
        else
        {
            walls.add(new Wall(workingVertex,v));
            workingVertex = null;
        }

    }
    
    public void draw(Graphics g)
    {
        for (Wall wall : walls) {
            wall.draw(g);
        }
    }
}
