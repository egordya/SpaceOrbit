package utilitys;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

public class ImageCache {

    HashMap<String, Image> imageMap = new HashMap<>();

    public ImageCache() {
    }

    /**
     *
     * @param path
     * @return
     */
    public Image getImage(String path) {
        if (imageMap.containsKey(path)) {
            return imageMap.get(path);
        }
        else {
            throw new IllegalArgumentException("Image with path: " + path + " is not preloaded");
        }
    }


    /**
     * Loads the image of the objects
     * @param path Takes in the path to the image
     */
    public void loadImage(String path){
        if (!imageMap.containsKey(path)) {
            Image image = new Image(path);
            imageMap.put(path, image);
        }
    }

    public void clearCache(){
        imageMap.clear();
    }

}
