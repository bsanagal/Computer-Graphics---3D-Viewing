/* class ViewVolume
 * OpenGL methods for drawing a view volume (given Camera specification)
 * using OpenGL clipping planes
 *
 * CS 428   Doug DeCarlo
 */

import javax.media.opengl.*;
import static javax.media.opengl.GL.GL_CLIP_PLANE0;
import static javax.media.opengl.GL.GL_CLIP_PLANE1;
import static javax.media.opengl.GL.GL_CLIP_PLANE2;
import static javax.media.opengl.GL.GL_CLIP_PLANE3;
import static javax.media.opengl.GL.GL_CLIP_PLANE4;
import static javax.media.opengl.GL.GL_CLIP_PLANE5;

import static javax.media.opengl.GL.GL_LINE_LOOP;
import javax.vecmath.*;

class ViewVolume
{
    
    private static double[] p0 = new double[3];
    private static double[] p1 = new double[3];
    private static double[] p2 = new double[3];
    private static double[] p3 = new double[3];
    private static double[] p4 = new double[3];
    private static double[] p5 = new double[3];
    private static double[] p6 = new double[3];
    private static double[] p7 = new double[3];
    
   
    
    // Constructor
       
    public ViewVolume()
    {
        
        
    }

    //-----------------------------------------------------------------------

    // Draw view volume (given camera specification in Camera) in
    // its canonical coordinate system
    public void draw(GL gl)
    {
        
        double near, far;
        near = (double) -Camera.near();
        far = (double) -Camera.far();
        // ...
        
        if(Camera.isPerspective()){
          double[] v0 = {Camera.left(),Camera.top(),near};
          double[] v1 = {Camera.right(),Camera.top(),near};
          double[] v2 = {Camera.right(),Camera.bottom(),near};
          double[] v3 = {Camera.left(), Camera.bottom(),near};
          double[] v4 = {(far/near)*Camera.left(),(far/near)*Camera.bottom(),far};
          double[] v5 = {(far/near)*Camera.right(),(far/near)*Camera.bottom(),far};
          double[] v6 = {(far/near)*Camera.right(),(far/near)*Camera.top(),far};
          double[] v7 = {(far/near)*Camera.left(),(far/near)*Camera.top(),far};
        
          setpoints(v0,v1,v2,v3,v4,v5,v6,v7);
          
        
        gl.glBegin(GL_LINE_LOOP);
        gl.glColor3d(1, 0, 0);
        
        gl.glVertex3dv(v0, 0);
        gl.glVertex3dv(v1, 0);
        gl.glVertex3dv(v2, 0);
        gl.glVertex3dv(v3, 0);
        gl.glEnd();
        
        gl.glBegin(GL_LINE_LOOP);
        gl.glColor3d(0, 1, 0);
        gl.glVertex3dv(v1, 0);
        gl.glVertex3dv(v6, 0);
        gl.glVertex3dv(v5, 0);
        gl.glVertex3dv(v2, 0);
        gl.glEnd();
        
        gl.glBegin(GL_LINE_LOOP);
        gl.glColor3d(0, 0, 1);
        gl.glVertex3dv(v0, 0);
        gl.glVertex3dv(v7, 0);
        gl.glVertex3dv(v4, 0);
        gl.glVertex3dv(v3, 0);
        gl.glEnd();
        
        gl.glBegin(GL_LINE_LOOP);
        gl.glColor3d(1, 1, 1);
        gl.glVertex3dv(v7, 0);
        gl.glVertex3dv(v6, 0);
        gl.glVertex3dv(v5, 0);
        gl.glVertex3dv(v4, 0);
        gl.glEnd();
    }else{
          double[] v0 = {Camera.left(),Camera.top(),near};
          double[] v1 = {Camera.right(),Camera.top(),near};
          double[] v2 = {Camera.right(),Camera.bottom(),near};
          double[] v3 = {Camera.left(), Camera.bottom(),near};
          double[] v4 = {Camera.left(),Camera.bottom(),far};
          double[] v5 = {Camera.right(),Camera.bottom(),far};
          double[] v6 = {Camera.right(),Camera.top(),far};
          double[] v7 = {Camera.left(),Camera.top(),far};
          
          setpoints(v0,v1,v2,v3,v4,v5,v6,v7);
          
          gl.glBegin(GL_LINE_LOOP);
        gl.glColor3d(1, 0, 0);
        
        gl.glVertex3dv(v0, 0);
        gl.glVertex3dv(v1, 0);
        gl.glVertex3dv(v2, 0);
        gl.glVertex3dv(v3, 0);
        gl.glEnd();
        
        gl.glBegin(GL_LINE_LOOP);
        gl.glColor3d(0, 1, 0);
        gl.glVertex3dv(v1, 0);
        gl.glVertex3dv(v6, 0);
        gl.glVertex3dv(v5, 0);
        gl.glVertex3dv(v2, 0);
        gl.glEnd();
        
        gl.glBegin(GL_LINE_LOOP);
        gl.glColor3d(0, 0, 1);
        gl.glVertex3dv(v0, 0);
        gl.glVertex3dv(v7, 0);
        gl.glVertex3dv(v4, 0);
        gl.glVertex3dv(v3, 0);
        gl.glEnd();
        
        gl.glBegin(GL_LINE_LOOP);
        gl.glColor3d(1, 1, 1);
        gl.glVertex3dv(v7, 0);
        gl.glVertex3dv(v6, 0);
        gl.glVertex3dv(v5, 0);
        gl.glVertex3dv(v4, 0);
        gl.glEnd();
        }
    }

    // Specify positions of all clipping planes
    //  - this is called from WorldView.draw()
    //  - it calls placeClipPlane() below 6 times -- once for each
    //    side of the view volume
    public void placeClipPlanes(GL gl)
    {
       
        placeClipPlane(gl, GL_CLIP_PLANE0, new Point3d(p0), new Point3d(p3), new Point3d(p2));
        placeClipPlane(gl, GL_CLIP_PLANE1, new Point3d(p7), new Point3d(p0), new Point3d(p1));
        placeClipPlane(gl, GL_CLIP_PLANE2, new Point3d(p2), new Point3d(p3), new Point3d(p4));
        placeClipPlane(gl, GL_CLIP_PLANE3, new Point3d(p4), new Point3d(p3), new Point3d(p0));
        placeClipPlane(gl, GL_CLIP_PLANE4, new Point3d(p6), new Point3d(p1), new Point3d(p2));
        placeClipPlane(gl, GL_CLIP_PLANE5, new Point3d(p5), new Point3d(p4), new Point3d(p7));
        
        
    }

    // Specify position of one particular clipping plane given 3
    // points on the plane ordered counter-clockwise
    void placeClipPlane(GL gl, int plane, Point3d p1, Point3d p2, Point3d p3)
    {
        // Plane equation Ax + By + Cz + D stored as [A,B,C,D]
        double[] eqn = new double[4];
        Vector3d n = new Vector3d();
        Vector3d a = new Vector3d();
        Vector3d b = new Vector3d();
        Vector3d p = new Vector3d();
       
        
        // Compute the plane equation from the 3 points -- fill in eqn[]
         
        //cal vectors
        //a.x = p1.x-p2.x; a.y = p1.y-p2.y; a.z = p1.z-p2.z;
        //b.x = p3.x-p2.x; a.y = p3.y-p2.y; a.z = p3.z-p2.z;
        a.sub(p1, p2);
        b.sub(p3,p2);
        //cal normal of plane
        n.cross(a,b);
        
        //A
        eqn[0] = n.x;
        //B
        eqn[1] = n.y;
        //C
        eqn[2] = n.z;
        //D
        p.x = p2.x; p.y = p2.y; p.z = p2.z;
        eqn[3] = -(p.dot(n));

        // *** Use the javax.vecmath library for this computation!
        System.out.println("eqn: "+eqn[0]+", "+eqn[1]+", "+eqn[2]+", "+eqn[3]);
       
        // Specify the clipping plane
        gl.glClipPlane(plane, eqn,0);
        //gl.glEnable(plane);
        
    }
    public static void setpoints(double[] v0, double[] v1, double[] v2,
                                 double[] v3, double[] v4, double[] v5, 
                                 double[] v6, double[] v7)
    {
        p0 = v0; p1 = v1; p2 = v2; p3 = v3; p4 = v4; p5 = v5; p6 = v6; p7 = v7;
    }
    
    public static double[] po() { return p0;}
    public static double[] p1() { return p1;}
    public static double[] p2() { return p2;}
    public static double[] p3() { return p3;}
    public static double[] p4() { return p4;}
    public static double[] p5() { return p5;}
    public static double[] p6() { return p6;}
    public static double[] p7() { return p7;}
    
}
