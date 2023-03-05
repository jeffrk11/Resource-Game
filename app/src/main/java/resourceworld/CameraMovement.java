package resourceworld;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.input.Input;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;


public final class CameraMovement implements IUpdateable{

    private Camera camera;
    private Point2D lastMousePosition;
    private boolean moving;

    CameraMovement(){
        lastMousePosition =null;
    }

    public Camera getCamera(){ 
        if(camera != null)
            return camera; 
        init();
        return camera;
    }

    public void init(){
        camera = new Camera();
        camera.setZoom(0.3f, 0);
        camera.setFocus(100, 350);
        moving = false;
    }

    @Override
    public void update() {
        //drag
        this.mouseMovement();
        //zoom
        Input.mouse().onWheelMoved( (e) -> {
            
            if( camera.getZoom() - ((float) e.getWheelRotation())/10000 >= 0.3f && camera.getZoom() - ((float) e.getWheelRotation())/10000 <= 1.2f)
                camera.setZoom(camera.getZoom() - ((float) e.getWheelRotation())/10000, 0);
        });
    
    }

    private void mouseMovement(){
        if(Input.mouse().isPressed() && Input.mouse().isLeftButtonPressed() && !moving){
            lastMousePosition = Input.mouse().getLocation();
            moving = true;
        }else if(Input.mouse().isPressed() && Input.mouse().isLeftButtonPressed() && lastMousePosition.distance(Input.mouse().getLocation()) != 0){
            double distanceX = Input.mouse().getLocation().getX() - lastMousePosition.getX();
            double distanceY = Input.mouse().getLocation().getY() - lastMousePosition.getY();
            camera.setFocus(camera.getFocus().getX() -  distanceX, camera.getFocus().getY() - distanceY);
            lastMousePosition = Input.mouse().getLocation();
            moving = true;
        }else{
            lastMousePosition = Input.mouse().getLocation();
            moving = false;
        }
    }
    
}
