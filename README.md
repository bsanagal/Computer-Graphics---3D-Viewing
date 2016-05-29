# Computer-Graphics---3D-Viewing
This project helps to understand the geometry of 3D viewing, and the transformations needed to implement it.  Viewing and transformations are done in OpenGL. I got clear idea about matrix stack, as well as how to use the OpenGL clipping planes.

[ Bhargav Ram Reddy ]
CS 428 - Project 1
	Source files have been well commented.
Sample Output:
 

Methods completed or changed:
-----------------------------

Cube.java:
- transform()
	To apply model transformation and push M onto stack

CameraView.java:
- projection()
	This method handles window resizing, sets the camera projection transformation based on the aspect ratio. The projection type is either perspective or orthographic, it depends on Boolean Camera.isPerspective()


- draw()
	This method draws the cube (by calling Cube.draw()), includes transformations(VM) by calling transformation methods
- transformation()
	To apply camera view transformation

WorldView.java:
- projection()
	This method handles window resizing, sets the world projection transformation based on the aspect ratio. The projection type is always perspective 
- draw()
	This method draws the view volume of camera and cube. Calls world transformation method, pushes inverse view transformation onto stack and pops, pushes model transformation and pops
	glLoadIdentity();
	W transform;
	
Push matrix() 
	V inverse transformation
	Pop matrix()
Push matrix() 
	M transformation
	Pop matrix()

	
- transformation()
	Apply world view transformation W

ViewVolume.java:
- draw()
	Draw Camera view volume by calculating vertices.
- placeClipPlanes()
	This is called in WorldView.draw() method. In this method, placeClipPlane() method is called for 6 times(6 planes) passing 3 points of a plane.
- placeClipPlane()
	Defines planes by calculating equation. Once the equation is calculated the corresponding planes are clipped by calling glClipPlane(plane, eqn,0) function.

