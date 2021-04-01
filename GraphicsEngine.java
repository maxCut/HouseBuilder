import javax.swing.JComponent;

import javax.swing.JFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.MouseAdapter;
import java.awt.Graphics;

/*
 * This class handles u/i. Graphics will be passed to relevant graphics objects
 */
public class GraphicsEngine extends JComponent {
    VertexManager vertexManager = new VertexManager();
    

    public GraphicsEngine()
    {
        addMouseListener(new MouseAdapter()
        {
            /*
             * This event is fired when mouse button is clicked. Calls all subscribed mouse events 
             */
            public void mouseClicked(MouseEvent e)
            {
                int button = e.getButton();
                ClickAction action = ClickAction.None;
                if(button == MouseEvent.BUTTON1)
                {
                    action = ClickAction.Select;
                }
                else if(button==MouseEvent.BUTTON2){                    
                    action = ClickAction.Delete;
                }
                else if(button==MouseEvent.BUTTON3)
                {
                    action = ClickAction.Create;
                }
                vertexManager.ClickEvent(e.getX(), e.getY(), action);
            }

        });
        /*
         * This event is fired when mouse is moved. Calls all subscribed mouse events 
         */
        addMouseMotionListener(new MouseMotionAdapter()
            {
                public void mouseMoved(MouseEvent e)
                {
                    vertexManager.MoveEvent(e.getX(), e.getY());
                }
            });
    }

    /*
     * The below code handles timer events. This includes frame rate and update functions.
     */
    private final int FRAMERRATE = 5;
    private Timer graphicsClock = new Timer();

    /*
     * Initializes the timer events.
     */
    public void Start()
    {
        /*
         * Builds the graphics objects for the engine
         */
        JFrame frame = new JFrame();
        frame.add(this);
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TimerTask updateGraphics = new TimerTask(){
            public void run()
            {
                frame.repaint();
            }            
        };
        graphicsClock.scheduleAtFixedRate(updateGraphics, 0 , FRAMERRATE);
    }

    /*
     * This is where graphics are drawn, to call all subscribed graphics elements
     */
    public void paintComponent(Graphics g)
    {
        vertexManager.draw(g);
    }
}
