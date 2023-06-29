package org.example;

import java.util.ArrayList;

public class PlateList {
    private ArrayList<Plate> piatti;

    public PlateList() {
        this.piatti = new ArrayList<>();
    }

    public void buildPlateList() {
        this.piatti.add(new Plate("La pizza, un prodotto gastronomico salato, che consiste in un impasto a base di farina, acqua e lievito.",1,false,"Pizza",266,5));
        this.piatti.add(new Plate("il piatto....",3,false,"Risotto alla milanese", 325.94, 8));
        this.piatti.add(new Plate("Il famoso piatto..",34,false,"Costata",433,133.0));
        this.piatti.add(new Plate("Questo piatto..",33,true,"Cibo vegano",130,20));
        this.piatti.add(new Plate("Questo altro piatto",11,true,"Cibo vegano numero due",152,15));
    }


    public ArrayList<Plate> getPiatti() {
        return piatti;
    }
}
