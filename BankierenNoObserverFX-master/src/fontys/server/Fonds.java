/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.server;

import java.io.Serializable;

/**
 *
 * @author Gebruiker
 */
public class Fonds implements IFonds, Serializable {

    private final String naam;
    private double koers;

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

    public Fonds(String naam)
    {
        this.naam = naam;
        this.koers = Math.random() * 100;
    }

    @Override
    public void changeKoers()
    {
        koers = koers + (Math.random() * 2) - 1;
        if (koers < 0)
        {
            koers = 0.00;
        }
        if (koers >= 100)
        {
            koers = 99.99;
        }
    }
}
