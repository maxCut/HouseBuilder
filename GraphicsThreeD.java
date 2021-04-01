
import javax.media.j3d.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.ColorCube;
import javax.media.j3d.BranchGroup;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import javax.vecmath.*;
import java.util.ArrayList;
public class GraphicsThreeD  {
    SimpleUniverse universe = new SimpleUniverse();
    public GraphicsThreeD()
    {
        System.setProperty("sun.awt.noerasebackground", "true");
        BranchGroup group = new BranchGroup();
        universe.getViewingPlatform().setNominalViewingTransform();
        //drawCube(group);

        

        OrbitBehavior orbit = new OrbitBehavior(universe.getCanvas(), OrbitBehavior.REVERSE_ROTATE);
        orbit.setSchedulingBounds(new BoundingSphere());
        orbit.setRotXFactor(2);
        orbit.setRotYFactor(2);
        universe.getViewingPlatform().setViewPlatformBehavior(orbit);
 


        universe.addBranchGraph(group);

    }

    /*
     * redraws the scene so that user can see updates in 3d.
     */
    public void redraw(ArrayList<Wall>  walls)
    {
        universe.getCanvas().getGraphicsContext3D().clear();
        BranchGroup group = new BranchGroup();
        for (Wall wall : walls) {
            drawWall(group, wall);
        }

        OrbitBehavior orbit = new OrbitBehavior(universe.getCanvas(), OrbitBehavior.REVERSE_ROTATE);
        orbit.setSchedulingBounds(new BoundingSphere());
        orbit.setRotXFactor(2);
        orbit.setRotYFactor(2);
 


        universe.addBranchGraph(group);
        universe.getCanvas().repaint();
    }

    /*
     * Projects 2d wall object into 3d space.
     */
    public void drawWall(BranchGroup group, Wall wall)
    {        

        //establish scaling to normalize points.
        float xScaler = .01f;
        float yScaler = .01f;
        float zScaler = .01f;

        //Wall is 8 points.
        Point3f bottomLeftFront = new Point3f();
        bottomLeftFront.x = wall.getFrontLeftPointX()*xScaler;
        bottomLeftFront.y = wall.getFrontLeftPointY()*yScaler;
        bottomLeftFront.z = 0;
        
        Point3f topLeftFront = new Point3f();
        topLeftFront.x = wall.getFrontLeftPointX()*xScaler;
        topLeftFront.y = wall.getFrontLeftPointY()*yScaler;
        topLeftFront.z = Wall.WALLHIEGHT *zScaler;

        Point3f bottomRightFront = new Point3f();
        bottomRightFront.x = wall.getFrontRightPointX()*xScaler;
        bottomRightFront.y = wall.getFrontRightPointY()*yScaler;
        bottomRightFront.z = 0;
        
        Point3f topRightFront = new Point3f();
        topRightFront.x = wall.getFrontRightPointX()*xScaler;
        topRightFront.y = wall.getFrontRightPointY()*yScaler;
        topRightFront.z = Wall.WALLHIEGHT*zScaler;
        
        Point3f bottomLeftBack = new Point3f();
        bottomLeftBack.x = wall.getBackLeftPointX()*xScaler;
        bottomLeftBack.y = wall.getBackLeftPointY()*yScaler;
        bottomLeftBack.z = 0;
        
        Point3f topLeftBack = new Point3f();
        topLeftBack.x = wall.getBackLeftPointX()*xScaler;
        topLeftBack.y = wall.getBackLeftPointY()*yScaler;
        topLeftBack.z = Wall.WALLHIEGHT*zScaler;

        Point3f bottomRightBack = new Point3f();
        bottomRightBack.x = wall.getBackRightPointX()*xScaler;
        bottomRightBack.y = wall.getBackRightPointY()*yScaler;
        bottomRightBack.z = 0;
        
        Point3f topRightBack = new Point3f();
        topRightBack.x = wall.getBackRightPointX()*xScaler;
        topRightBack.y = wall.getBackRightPointY()*yScaler;
        topRightBack.z = Wall.WALLHIEGHT*zScaler;
        
        Appearance app = new Appearance();
        QuadArray lsa = new QuadArray(48,QuadArray.COORDINATES|QuadArray.NORMALS);
        Vector3f [] normals=new Vector3f[24];
        for(int i=0;i<24;i++)normals[i]=new Vector3f();
        Point3f [] pts=new Point3f[24];
        for(int i=0;i<24;i++)pts[i]=new Point3f();
        Color3f [] clrs=new Color3f[24];
        for(int i=0;i<24;i++)clrs[i]=new Color3f(0.5f,0.5f,0.5f);
        //cube=6 quads 
        //first quad front face
        pts[0] = bottomLeftFront;
        pts[1] = bottomRightFront;
        pts[2] = topRightFront;
        pts[3] = topLeftFront;
        normals[0].x=-0;normals[1].x=-0;normals[2].x=-0;normals[3].x=-7;
        //second quad
        //Top face
        pts[4] = topRightBack;
        pts[5] = topLeftBack;
        pts[6] = topRightFront;
        pts[7] = topLeftFront;
        normals[4].x=0;normals[5].x=0;normals[6].x=0;normals[7].x=1;

        //third quad
        //left face
        pts[8] = topLeftBack;
        pts[9] = bottomLeftBack;
        pts[10] = topLeftFront;
        pts[11] = bottomLeftFront;
        normals[8].z=-1;normals[9].z=-1;normals[10].z=-1;normals[11].z=-1;
        //fourth quad
        //back face
        pts[12] = topLeftBack;
        pts[13] = bottomLeftBack;
        pts[14] = topRightBack;
        pts[15] = bottomRightBack;
        normals[12].z=1;normals[13].z=1;normals[14].z=1;normals[15].z=1;
        //fifth quad
        //right face
        pts[16] = topRightFront;
        pts[17] = bottomRightFront;
        pts[18] = topRightBack;
        pts[19] = bottomRightBack;
        normals[16].y=-1;normals[17].y=-1;normals[18].y=-1;normals[19].y=-1;
        //sixth quad
        //bottom face
        pts[20] = bottomRightFront;
        pts[21] = bottomLeftFront;
        pts[22] = bottomRightBack;
        pts[23] = bottomLeftBack;
        normals[20].y=1;normals[21].y=1;normals[22].y=1;normals[23].y=1;
        lsa.setNormals(0, normals);
        lsa.setCoordinates(0, pts);
        Shape3D sh=new Shape3D();
        PolygonAttributes pa=new PolygonAttributes();
        pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
        pa.setCullFace(PolygonAttributes.CULL_NONE);
        Material mat=new Material();
        mat.setEmissiveColor(new Color3f(0.5f,0.5f,0.5f));
        mat.setAmbientColor(new Color3f(0.1f,0.1f,0.1f));
        mat.setDiffuseColor(new Color3f(0.2f,0.3f,0.4f));
        mat.setSpecularColor(new Color3f(0.6f,0.3f,0.2f));
        mat.setLightingEnable(true);
        RenderingAttributes ra=new RenderingAttributes();
        ra.setIgnoreVertexColors(true);
        ColoringAttributes ca=new ColoringAttributes();
        ca.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
        ca.setColor(new Color3f(0.2f,0.5f,0.9f));
        app.setColoringAttributes(ca);
        app.setRenderingAttributes(ra);   
        app.setMaterial(mat);
        app.setPolygonAttributes(pa);
        sh.setGeometry(lsa);
        sh.setAppearance(app);
        sh.setPickable(true); 
        TransformGroup objRotate = new TransformGroup();
        objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objRotate.addChild(sh);

        DirectionalLight light1=new DirectionalLight();
        light1.setInfluencingBounds(new BoundingSphere(new Point3d(-5.0,0,0),10.0));
        light1.setColor(new Color3f(1f,1f,1f));
        light1.setDirection(new Vector3f(0,1,0));
        objRotate.addChild(light1);
        DirectionalLight light2=new DirectionalLight();
        light2.setInfluencingBounds(new BoundingSphere(new Point3d(5.0,0,0),10.0));
        light2.setColor(new Color3f(0.5f,1f,0.5f));
        light2.setDirection(new Vector3f(0,-1,0));
        objRotate.addChild(light2);
        group.addChild(objRotate);

    }

    public void drawCube(BranchGroup g)
    {
        Appearance app = new Appearance();

        QuadArray lsa = new QuadArray(48,QuadArray.COORDINATES|QuadArray.NORMALS);
        Vector3f [] normals=new Vector3f[24];
        for(int i=0;i<24;i++)normals[i]=new Vector3f();
        Point3f [] pts=new Point3f[24];
        for(int i=0;i<24;i++)pts[i]=new Point3f();
        Color3f [] clrs=new Color3f[24];
        for(int i=0;i<24;i++)clrs[i]=new Color3f(0.5f,0.5f,0.5f);
        //cube=6 quads 
        //first quad
        pts[0].x=-0.7f;pts[0].y=-0.7f;pts[0].z=-0.7f;
        pts[1].x=-0.5f;pts[1].y=-0.5f;pts[1].z=0.5f;
        pts[2].x=-0.5f;pts[2].y=0.5f;pts[2].z=0.5f;
        pts[3].x=-0.5f;pts[3].y=0.5f;pts[3].z=-0.5f;
        normals[0].x=-1;normals[1].x=-1;normals[2].x=-1;normals[3].x=-1;
        //second quad
        pts[4].x=0.5f;pts[4].y=-0.5f;pts[4].z=-0.5f;
        pts[5].x=0.5f;pts[5].y=-0.5f;pts[5].z=0.5f;
        pts[6].x=0.5f;pts[6].y=0.5f;pts[6].z=0.5f;
        pts[7].x=0.5f;pts[7].y=0.5f;pts[7].z=-0.5f;
        normals[4].x=1;normals[5].x=1;normals[6].x=1;normals[7].x=1;

        //third quad
        pts[8].x=-0.5f;pts[8].y=-0.5f;pts[8].z=-0.5f;
        pts[9].x=0.5f;pts[9].y=-0.5f;pts[9].z=-0.5f;
        pts[10].x=0.5f;pts[10].y=0.5f;pts[10].z=-0.5f;
        pts[11].x=-0.5f;pts[11].y=0.5f;pts[11].z=-0.5f;
        normals[8].z=-1;normals[9].z=-1;normals[10].z=-1;normals[11].z=-1;
        //fourth quad
        pts[12].x=-0.5f;pts[12].y=-0.5f;pts[12].z=0.5f;
        pts[13].x=0.5f;pts[13].y=-0.5f;pts[13].z=0.5f;
        pts[14].x=0.5f;pts[14].y=0.5f;pts[14].z=0.5f;
        pts[15].x=-0.5f;pts[15].y=0.5f;pts[15].z=0.5f;
        normals[12].z=1;normals[13].z=1;normals[14].z=1;normals[15].z=1;
        //fifth quad
        pts[16].x=-0.5f;pts[16].y=-0.5f;pts[16].z=-0.5f;
        pts[17].x=-0.5f;pts[17].y=-0.5f;pts[17].z=0.5f;
        pts[18].x=0.5f;pts[18].y=-0.5f;pts[18].z=0.5f;
        pts[19].x=0.5f;pts[19].y=-0.5f;pts[19].z=-0.5f;
        normals[16].y=-1;normals[17].y=-1;normals[18].y=-1;normals[19].y=-1;
        //sixth quad
        pts[20].x=-0.5f;pts[20].y=0.5f;pts[20].z=-0.5f;
        pts[21].x=-0.5f;pts[21].y=0.5f;pts[21].z=0.5f;
        pts[22].x=0.5f;pts[22].y=0.5f;pts[22].z=0.5f;
        pts[23].x=0.5f;pts[23].y=0.5f;pts[23].z=-0.5f;
        normals[20].y=1;normals[21].y=1;normals[22].y=1;normals[23].y=1;
        lsa.setNormals(0, normals);
        lsa.setCoordinates(0, pts);
        Shape3D sh=new Shape3D();
        PolygonAttributes pa=new PolygonAttributes();
        pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
        pa.setCullFace(PolygonAttributes.CULL_NONE);
        Material mat=new Material();
        mat.setEmissiveColor(new Color3f(0.5f,0.5f,0.5f));
        mat.setAmbientColor(new Color3f(0.1f,0.1f,0.1f));
        mat.setDiffuseColor(new Color3f(0.2f,0.3f,0.4f));
        mat.setSpecularColor(new Color3f(0.6f,0.3f,0.2f));
        mat.setLightingEnable(true);
        RenderingAttributes ra=new RenderingAttributes();
        ra.setIgnoreVertexColors(true);
        ColoringAttributes ca=new ColoringAttributes();
        ca.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
        ca.setColor(new Color3f(0.2f,0.5f,0.9f));
        app.setColoringAttributes(ca);
        app.setRenderingAttributes(ra);   
        app.setMaterial(mat);
        app.setPolygonAttributes(pa);
        sh.setGeometry(lsa);
        sh.setAppearance(app);
        sh.setPickable(true); 
        TransformGroup objRotate = new TransformGroup();
        objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objRotate.addChild(sh);

        DirectionalLight light1=new DirectionalLight();
        light1.setInfluencingBounds(new BoundingSphere(new Point3d(-5.0,0,0),10.0));
        light1.setColor(new Color3f(1f,1f,1f));
        light1.setDirection(new Vector3f(0,1,0));
        objRotate.addChild(light1);
        DirectionalLight light2=new DirectionalLight();
        light2.setInfluencingBounds(new BoundingSphere(new Point3d(5.0,0,0),10.0));
        light2.setColor(new Color3f(0.5f,1f,0.5f));
        light2.setDirection(new Vector3f(0,-1,0));
        objRotate.addChild(light2);
        g.addChild(objRotate);

    }
    public static void main(String[] args)
    {
        System.setProperty("sun.awt.noerasebackground", "true");
        new GraphicsThreeD();
    }
    
}
