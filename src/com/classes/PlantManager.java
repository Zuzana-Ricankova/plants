package com.classes;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PlantManager {
    List<Plant> plants = new ArrayList <>();



    //metoda odebrani kvetiny ze seznamu
//    public void removePlant(Plant plant) {
//        plants.remove(plant);
//    }
    public void remove(int index) {
        plants.remove(index);
    }

    //metoda ziskani kvetiny na zadanem indexu
    public Plant getPlants(int index) {
        return plants.get(index);
    }

    public void setPlants(int index, Plant plant) {
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
        plants.clear();
        try (Scanner scanner = new Scanner (new BufferedReader(new FileReader(fileName)))){
            while (scanner.hasNextLine()){
                lineCounter ++;
                String line= scanner.nextLine();
                String[] parts = line.split("\t");
                if (parts.length != 5) throw new PlantException(
                        "Nespravny pocet polozek na radku cislo: " + lineCounter + " :" + line + "!");
                String name = parts[0];
                String notes = parts[1];
                int frequencyOfWatering = Integer.parseInt(parts[2]);
                LocalDate watering = LocalDate.parse(parts[3]);
                LocalDate planted = LocalDate.parse(parts[4]);
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
        String delimiter = "\t";
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))){
            for (Plant plant : plants) {
                writer.println(plant.getName() +
                        delimiter +plant.getNotes() +
                        delimiter + plant.getFrequencyOfWatering() +
                        delimiter + plant.getWatering() +
                        delimiter + plant.getPlanted());

            }
        }catch (FileNotFoundException e){
            throw new PlantException("File " + fileName + "not found!\n" + e.getLocalizedMessage());
        }catch (IOException e) {
            throw new PlantException("Output error when writing to file: " + fileName + ":\n" + e.getLocalizedMessage());
        }
    }

    public void sortByName() {
        Collections.sort(plants);
    }

    public void sortByLastWatering(){
        plants.sort(new PlantComparator());
    }

    @Override
    public String toString() {
        return "plants: " + "\n" + plants;
    }
}

