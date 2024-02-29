import com.classes.Plant;
import com.classes.PlantException;
import com.classes.Settings;
import com.classes.PlantList;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Plant> plants = new ArrayList<>();

        String fileName = Settings.getFilename();

        PlantList kvetiny = new PlantList();
        try{
            kvetiny.loadContentFromFile(fileName);
        }catch (PlantException e){
            System.err.println("Nastala chyba pri nacitani obsahu kvetin ze souboru: " + fileName + ":\n" + e.getLocalizedMessage() + "\n");
        }

        //vypis obsahu kosiku
        System.out.println("Obsah souboru: " + kvetiny.getPlants());

        

    }
}