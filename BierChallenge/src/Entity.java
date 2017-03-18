package beerchallenge;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


abstract class Entity {
        private final String name;
        private int pong, klim, pHits, kHits;
        private double strength, exTime;
        final IntegerProperty beerDone = new SimpleIntegerProperty();

        Entity(String name) {
            this.name = name;
            this.beerDone.set(0);
            this.pong = 0;
            this.klim = 0;
            this.pHits = 0;
            this.kHits = 0;
        }

        Entity(String name, int drunk, int pong, int klim, int pHits, int kHits) {
            this.name = name;
            this.beerDone.set(drunk);
            this.pong = pong;
            this.klim = klim;
            this.pHits = pHits;
            this.kHits = kHits;
        }
        
        final void setBeer(int drunk) {
            this.beerDone.set(drunk);
        }
        
        final void addBeer(int x) {
            this.beerDone.set(beerDone.getValue() + x);
        }
        
        final void addPThrow(boolean hit) {
            if(hit)
                this.pHits++;
            pong++;
        }
        
        final void addKThrow(boolean hit) {
            if(hit)
                this.kHits++;
            klim++;
        }
        
        final void setStrength(double str) {
            this.strength = str;
        }
        
        final void setEx(double ex) {
            this.exTime = ex;
        }
        
        final String getName() {
            return name;
        }

        final IntegerProperty getDrunk() {
            return beerDone;
        }

        final int getPong() {
            return pong;
        }

        final int getKlim() {
            return klim;
        }

        final int getPHits() {
            return pHits;
        }

        final int getKHits() {
            return kHits;
        }
        
        final double getStr() {
            return strength;
        }
        
        final double getEx() {
            return exTime;
        }

        final double getPAcc() {
            return (pHits/pong) * 100;
        }

        final double getKAcc() {
            return (kHits/klim) * 100;
        }

}
