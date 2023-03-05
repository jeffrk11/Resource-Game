package resourceworld;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.environment.tilemap.IMap;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;

public class App {
    

    public static void main(String[] args) {
        Game.info().setName("ResourceWorld");

        Game.init(args);

        Game.graphics().setBaseRenderScale(4f);

        //camera
        CameraMovement camera = new CameraMovement();
        camera.getCamera().setZoom(0.3f, 0);
        Game.world().setCamera(camera.getCamera());
        
        Game.screens().add(new IngameScreen());
        
        // load a demo map for map generation
        //IMap map = MapGeneration.generateRandomizedMap(50,50);
        IMap map = Resources.maps().get("src/main/resources/maps/map-ocean.tmx");
        Game.world().loadEnvironment(map);
        
        
        Game.start();
        Game.loop().attach(camera);
    }
}
