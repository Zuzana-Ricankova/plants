package com.classes;

import java.time.LocalDate;


public class Plant implements Comparable<Plant>{
    String name;
    String notes;
    LocalDate planted;
    LocalDate watering;
    int frequencyOfWatering;


    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public Plant(String name, LocalDate planted, int frequencyOfWatering) {
        this(name, "", planted, LocalDate.now(), frequencyOfWatering);

    }

    public Plant(String name) {
        this(name, "", LocalDate.now(), LocalDate.now(), 7);

    }

    ///region setter and getter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException{
        if (watering.isBefore(planted)){
            throw new PlantException("Date of watering: " + watering +
                    "cannnot be older than date of planting: " + planted + "!");
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException{
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Mistake of writing frequency number of plant: "
                    + name + "the frequency number was written: "+ frequencyOfWatering + ". Must be bigger than 0!");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }


    ///endregion setter and getter

    public String getWateringInfo(){
        return name + ": last watering:" + this.watering + "; next watering: " + this.watering.plusDays(this.frequencyOfWatering);
    }

    @Override
    public String toString() {
        return "\nname: " + name +
                ", \tnotes: " + notes +
                ", \tplanted: " + planted +
                ", \twatering: " + watering +
                ", \tfrequency of watering: " + frequencyOfWatering;
    }

    @Override
    public int compareTo(Plant otherPlant) {
        return this.getName().compareTo(otherPlant.getName());
    }

}





