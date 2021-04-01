import java.util.ArrayList;

import java.awt.Graphics;

/*
 * Handles each vertex in the sketch
 */
public class VertexManager {
    private ArrayList<Vertex> verticies = new ArrayList<>(); 

    private Vertex selectedVertex;
    private WallManager wallManager;


    public VertexManager()
    {
        wallManager = new WallManager();
    }
    public boolean isVertexSelected()
    {
        return selectedVertex==null;
    }

    public void ClickEvent(int x, int y, ClickAction action)
    {
        switch(action)
        {
            case Select:
                if(selectedVertex!=null)
                {
                    selectedVertex = null;
                    break;
                }
                for (Vertex vertex : verticies) {
                    if(vertex.contains(x, y))
                    {
                        selectedVertex = vertex;
                    }
                }
                break;
            case Create:
                if(selectedVertex!=null)
                {
                    selectedVertex = null;
                    break;
                }
                else{
                    Vertex v = new Vertex(x, y);
                    verticies.add(v);
                    wallManager.AddPoint(v);
                    break;
                }
            case Delete:
                for (Vertex vertex : verticies) {
                    if(vertex.contains(x, y))
                    {
                        verticies.remove(vertex);
                        break;
                    }
                }
                break;
                


                

        }

    }

    public void MoveEvent(int x, int y)
    {
        if(selectedVertex!=null)
        {
            selectedVertex.update(x, y);
            for (Vertex vertex : verticies) {
                if(vertex!=selectedVertex)
                {
                    if(vertex.contains(x, y))
                    {
                        selectedVertex.update(vertex.xPos, vertex.yPos);
                    }
                }
            }
        }
    }
    public void draw(Graphics g)
    {
        for (Vertex vertex : verticies) {
            vertex.draw(g);
        }
        wallManager.draw(g);
    }
    
}
