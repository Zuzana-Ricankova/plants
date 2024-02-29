import com.classes.Plant;
import com.classes.PlantException;
import com.classes.Settings;
import com.classes.PlantManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Plant> plants = new ArrayList<>();
        PlantManager plantManager = new PlantManager();

        String fileName = Settings.getFilename();


        try{
            plantManager.loadContentFromFile(fileName);

        }catch (PlantException e){
            System.err.println("Nastala chyba pri nacitani obsahu kvetin ze souboru: " + fileName + ":\n" + e.getLocalizedMessage() + "\n");
        }

        System.out.println("Obsah souboru: " + plantManager.getPlants());
        for(Plant plant : plantManager.getPlants()){
            System.out.println(plant.getWateringInfo());}

        plantManager.addPlant(new Plant("Pampeliska", "venkovni", LocalDate.of(2020,3,3), LocalDate.of(2021,4,4), 9));
        plantManager.addPlant(new Plant("Kopretina", LocalDate.of(2022,4,3), 8));
        plantManager.remove(1);


        try{
            plantManager.saveContentToFile("kvetiny_new.txt");
        }catch (PlantException e){
            System.err.println("Nastala chyba pri nacitani obsahu kvetin do souboru: " + "kvetiny_new.txt" + ":\n" + e.getLocalizedMessage() + "\n");
        }


        try{
            plantManager.loadContentFromFile("kvetiny_new.txt");
        }catch (PlantException e){
            System.err.println("Nastala chyba pri nacitani obsahu kvetin ze souboru: " + "kvetiny_new.txt" + ":\n" + e.getLocalizedMessage() + "\n");
        }



        plantManager.sortByName();
        System.out.println("\nSorted by name: ");
        plantManager.getPlants().forEach(System.out::println);


        plantManager.sortByLastWatering();
        System.out.println("\nSorted by last watering: ");
        plantManager.getPlants().forEach(System.out::println);


        //hlasi chybu
        try{
            plantManager.loadContentFromFile("kvetiny-spatne-datum.txt");
        }catch (PlantException e){
            System.err.println("Nastala chyba pri nacitani obsahu kvetin ze souboru: " + "kvetiny-spatne-datum.tx" + ":\n" + e.getLocalizedMessage() + "\n");
        }
        System.out.println("Obsah souboru: " + plantManager.getPlants());
        //hlasi chybu
        try{
            plantManager.loadContentFromFile("kvetiny-spatne-frekvence.txt");
        }catch (PlantException e){
            System.err.println("Nastala chyba pri nacitani obsahu kvetin ze souboru: " + "kvetiny-spatne-frekvence.tx" + ":\n" + e.getLocalizedMessage() + "\n");
        }
        System.out.println("Obsah souboru: " + plantManager.getPlants());


    }
}