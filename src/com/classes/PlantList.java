package com.classes;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlantList {
    List<Plant> plants = new ArrayList <>();



    //metoda odebrani kvetiny ze seznamu
    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    //metoda ziskani kvetiny na zadanem indexu
    public List<Plant> getPlants(int index) {
        return plants;
    }

    public void setPlants(int index, Plant plant) {
        this.plants = plants;
    }

    public List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }


    //metoda pro pridani nove kvetiny
    public void addPlant(Plant plant){
        plants.add(plant);
    }

public void addPlants(List<Plant>plants){
        this.plants.addAll(plants);
}


public void loadContentFromFile (String fileName) throws PlantException{
        int lineCounter = 0;
        try (Scanner scanner = new Scanner (new BufferedReader(new FileReader(fileName)))){
            while (scanner.hasNextLine()){
                lineCounter ++;
                String line= scanner.nextLine();
                String[] parts = line.split(Settings.getDelimiter());
                if (parts.length != 5) throw new PlantException(
                        "Nespravny pocet polozek na radku cislo: " + lineCounter + " :" + line + "!");
                String name = parts[0];
                String notes = parts[1];
                LocalDate planted = LocalDate.parse(parts[4]);
                LocalDate watering = LocalDate.parse(parts[3]);
                int frequencyOfWatering = Integer.parseInt(parts[2]);
                Plant plant = new Plant(name, notes, planted, watering, frequencyOfWatering);
                plants.add(plant);
            }

        }
        catch (FileNotFoundException e){
            throw new PlantException("Soubor" + fileName + "nebyl nalezen!\n" + e.getLocalizedMessage());
        }catch (NumberFormatException e){
            throw new PlantException("Chyba pri cteni ciselne hodnoty na radku cislo: " + lineCounter + ":\n" + e.getLocalizedMessage());
        }catch (IllegalArgumentException e){
            throw new PlantException("Chyba pri cteni kategorie na radku cislo: " + lineCounter + ":\n" + e.getLocalizedMessage());
        }catch (DateTimeException e){
            throw new PlantException("Chyba pri cteni data na radku cislo: " + lineCounter + ":\n" + e.getLocalizedMessage());
        }
}

public void saveContentToFile(String fileName) throws PlantException{
        String delimiter = Settings.getDelimiter();
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))){
            for (Plant plant : plants) {
                writer.println(plant.getName() +
                        delimiter +plant.getNotes() +
                        delimiter + plant.getPlanted() +
                        delimiter + plant.getWatering() +
                        delimiter + plant.getFrequencyOfWatering());
            }
        }catch (FileNotFoundException e){
            throw new PlantException("File " + fileName + "not found!\n" + e.getLocalizedMessage());
        }catch (IOException e) {
            throw new PlantException("Output error when writing to file: " + fileName + ":\n" + e.getLocalizedMessage());
        }
}





}

