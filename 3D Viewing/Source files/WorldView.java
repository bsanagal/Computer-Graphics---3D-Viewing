/* class WorldView
 * The OpenGL drawing component of the interface
 * for the "world" viewport
 *
 * CS 428   Doug DeCarlo
 */

import java.awt.*;
import javax.media.opengl.*;
import static javax.media.opengl.GL.GL_CLIP_PLANE0;
import static javax.media.opengl.GL.GL_CLIP_PLANE1;
import static javax.media.opengl.GL.GL_CLIP_PLANE2;
import static javax.media.opengl.GL.GL_CLIP_PLANE3;
import static javax.media.opengl.GL.GL_CLIP_PLANE4;
import static javax.media.opengl.GL.GL_CLIP_PLANE5;

public class WorldView extends SimpleGLCanvas
{
    ViewVolume viewVol;
    Cube cube;
    //CameraView cameraview;

    public WorldView(Window parent, boolean debug)
    {
        super(parent);
        debugging = debug;

        viewVol = new ViewVolume();
        cube = new Cube();
        
        
    }

    // Method for initializing OpenGL (called once at the beginning)
    public void init(GL gl)
    {
        // Set background color to black
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        // Turn on visibility test
        gl.glEnable(GL.GL_DEPTH_TEST);
    }

    // ------------------------------------------------------------

    // Method for handling window resizing
    public void projection(GL gl)
    {
        // Set drawing area
        gl.glViewport(0, 0, width, height);

        
        double l,b,r,t;
        double aspectratio = (double)width/(double)height;
        b = -1;
        t = 1;
        r = aspectratio;
        l = -r;

        // Set the world projection transformation based on the
        // aspect ratio of the window
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustum(l, r, b, t, 2, 100);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        
        
    }

    // Method for drawing the contents of the window
    public void draw(GL gl)
    {
        // Clear the window
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        projection(gl);
        // Draw the cube (perhaps clipped) and the view volume
        gl.glLoadIdentity();
        
        //World view transformation
        transformation(gl);
        
        ///View inverse transformation
        gl.glPushMatrix();
        
        gl.glRotated(-Camera.pitch(), 1, 0, 0);
        gl.glRotated(-Camera.yaw(), 0, 1, 0);
        gl.glRotated(-Camera.roll(), 0, 0, 1);
        gl.glTranslated(-Camera.tx(), -Camera.ty(), -Camera.tz());
            //Disable clipping planes
            gl.glDisable(GL_CLIP_PLANE0);
            gl.glDisable(GL_CLIP_PLANE1);
            gl.glDisable(GL_CLIP_PLANE2);
            gl.glDisable(GL_CLIP_PLANE3);
            gl.glDisable(GL_CLIP_PLANE4);
            gl.glDisable(GL_CLIP_PLANE5);
            
        viewVol.draw(gl);
        //System.out.println("cliplane: "+World.isClipping());
        
           //Clipping
           if(World.isClipping()){
            viewVol.placeClipPlanes(gl);} 
           else{
                gl.glDisable(GL_CLIP_PLANE0);
                gl.glDisable(GL_CLIP_PLANE1);
                gl.glDisable(GL_CLIP_PLANE2);
                gl.glDisable(GL_CLIP_PLANE3);
                gl.glDisable(GL_CLIP_PLANE4);
                gl.glDisable(GL_CLIP_PLANE5);
           }
        
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        //model transformation
        cube.transform(gl);
        //Enable Clipping planes
       if(World.isClipping()){ 
        gl.glEnable(GL_CLIP_PLANE0);
        gl.glEnable(GL_CLIP_PLANE1);
        gl.glEnable(GL_CLIP_PLANE2);
        gl.glEnable(GL_CLIP_PLANE3);
        gl.glEnable(GL_CLIP_PLANE4);
        gl.glEnable(GL_CLIP_PLANE5);
       }
        //Draw the cube
        cube.draw(gl, true);
        gl.glPopMatrix();
        
    }

    // Apply the (world) viewing transformation (W)
    void transformation(GL gl)
    {
        // ...
        gl.glTranslated(World.tx(), World.ty(), World.tz());
        gl.glRotated(World.roll(), 0, 0, 1);
        gl.glRotated(World.yaw(), 0, 1, 0);
        gl.glRotated(World.pitch(), 1, 0, 0);
    }
}
