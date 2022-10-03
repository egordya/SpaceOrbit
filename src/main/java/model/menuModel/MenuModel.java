package model.menuModel;

import java.io.File;
import java.util.ArrayList;

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


}