/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.client;

/**
 *
 * @author Gebruiker
 */
public class Fonds implements IFonds{
private final String naam;
private final double koers;
    @Override
    public String getNaam()
    {
        return naam;
    }

    @Override
    public double getKoers()
    {
        return koers;
    }

    public Fonds(String naam, double koers)
    {
        this.naam = naam;
        this.koers = koers;
    }
    
}
