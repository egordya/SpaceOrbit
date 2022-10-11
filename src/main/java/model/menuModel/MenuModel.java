package model.menuModel;

import javafx.scene.control.Button;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MenuModel {

    final String pathToLevels = getClass().getResource("/json/levels").getPath();

    public MenuModel(){
    }

    public String[] getAllLevels(){
        File folder = new File(pathToLevels);
        File[] listOfFiles = folder.listFiles();

        ArrayList<String> paths = new ArrayList<>();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                paths.add(file.toString());
            }
        }

        return paths.toArray(new String[0]);
    }

    public List<Button> getlvlbuttons() {
        File[] lvlFolder = new File("src/main/resources/json/levels").listFiles();
        List<Button> buttons = new ArrayList<>();
        for (File file : lvlFolder) {
            if (file.isFile()) {
                buttons.add(new Button((file.getName().replaceFirst("[.][^.]+$", ""))));
            }}
        return buttons;
    }


}