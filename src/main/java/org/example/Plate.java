package org.example;

public class Plate {
    private String descrizione;
    private int id;
    private boolean vegan;
    private String name;
    private double calories;
    private double price;

    public Plate(String descrizione, int id, boolean vegan, String name, double calories, double price) {
        this.descrizione = descrizione;
        this.id = id;
        this.vegan = vegan;
        this.name = name;
        this.calories = calories;
        this.price = price;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
