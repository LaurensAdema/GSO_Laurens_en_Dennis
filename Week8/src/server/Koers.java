package server;

import shared.IFonds;

/**
 *
 * @author Kevin van der Burg & Milton van de Sanden
 */
public class Koers implements IFonds {
    private String name;
    private double koers;
    
    /**
     * Public constructor
     * @param name the name of the "Koers"
     * @param koers the value of the "Koers"
     */
    public Koers(String name, double koers) {
        this.name = name;
        this.koers = koers;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getKoers() {
        return this.koers;
    }
    
    /**
     *
     * @param koers the value of the "Koers"
     */
    public void setKoers(double koers) {
        this.koers = koers;
    }
    
}