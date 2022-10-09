package utilitys;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

public class ImageCache {

    HashMap<String, Image> imageMap = new HashMap<>();

    public ImageCache() {
    }

    public Image getImage(String path) {
        if (imageMap.containsKey(path)) {
            return imageMap.get(path);
        }
        else {
            throw new IllegalArgumentException("Image with path: " + path + " is not preloaded");
        }
    }

    public void loadImage(String path){
        if (!imageMap.containsKey(path)) {
            Image image = new Image(path);
            imageMap.put(path, image);
        }
    }

}
