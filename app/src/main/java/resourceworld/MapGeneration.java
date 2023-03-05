package resourceworld;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.CollisionBox;
import de.gurkenlabs.litiengine.environment.tilemap.IMap;
import de.gurkenlabs.litiengine.environment.tilemap.ITileLayer;
import de.gurkenlabs.litiengine.environment.tilemap.MapOrientations;
import de.gurkenlabs.litiengine.graphics.RenderType;
import de.gurkenlabs.litiengine.resources.Maps.MapGenerator;
import de.gurkenlabs.litiengine.resources.Resources;

public final class MapGeneration {
    private static final int BASIC_COLOR_TILE_1 = 16;
    private static final int BASIC_COLOR_TILE_2 = 17;
    private static final int BASIC_COLOR_TILE_3 = 18;
    private static final int BASIC_COLOR_TILE_4 = 19;

    public static IMap generateRandomizedMap(int width, int height) {
        final String MAP_NAME = "mymap";
        final int MAP_WIDTH = width;
        final int MAP_HEIGHT = height;
        final int TILE_WIDTH = 4;
        final int TILE_HEIGHT = 4;
    
        IMap map;
        ITileLayer tileLayer;
    
        // ------------- THIS IS WHERE ALL THE MAP GENERATION MAGIC HAPPENS ---------------
        // always generate maps in a try-with-resource block to ensure that the map is "finished" when the creation is done
        try (MapGenerator generator = 
        Resources.maps()
            .generate(MapOrientations.ORTHOGONAL, MAP_NAME, MAP_WIDTH, MAP_HEIGHT, TILE_WIDTH, TILE_HEIGHT, 
                Resources.tilesets().get("src/main/resources/maps/tileset.tsx"))) {

            tileLayer = generator.addTileLayer(RenderType.GROUND, (x, y) -> {
                if (x == y) {
                // draw a diagonal in another tile color
                    return BASIC_COLOR_TILE_1;
                }
        
                // fill the entire map with this tile
                return BASIC_COLOR_TILE_2;
            });
        
            // set an explicit tile at a location
            tileLayer.setTile(12, 13, BASIC_COLOR_TILE_4);
        
            // add a collision box to the map
            //generator.add(new CollisionBox(0, 64, 100, 30));
        
            // add a creature (Jorge) to the map
            //generator.add(createJorge());
        
            map = generator.getMap();
        }
    
        // // adjust the tile layer randomly to demonstrate runtime modification of maps
        // Game.world().attach(MAP_NAME, () -> {
        //     // on every tick, this will random
        //     int x = Game.random().nextInt(50);
        //     int y = Game.random().nextInt(50);
        
        //     // choose from a random color tile and set it
        //     tileLayer.setTile(x, y, Game.random().choose(BASIC_COLOR_TILE_1, BASIC_COLOR_TILE_2, BASIC_COLOR_TILE_3));
        // });
    
        return map;
    }
}
