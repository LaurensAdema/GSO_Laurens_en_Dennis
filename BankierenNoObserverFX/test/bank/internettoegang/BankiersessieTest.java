/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.internettoegang;

import bank.bankieren.Bank;
import bank.bankieren.IRekening;
import bank.bankieren.Money;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author laure
 */
public class BankiersessieTest {
    
    private Bank bank;
    private int rekening1;
    private int rekening2;
    private Bankiersessie bankiersessie1;
    private Bankiersessie bankiersessie2;
    
    public BankiersessieTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
        try
        {
            bank = new Bank("test");
            rekening1 = bank.openRekening("test1", "teststad");
            rekening2 = bank.openRekening("test2", "teststad");
            bankiersessie1 = new Bankiersessie(rekening1, bank);
            bankiersessie2 = new Bankiersessie(rekening2, bank);
        } catch (RemoteException ex)
        {
            Logger.getLogger(BankiersessieTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of isGeldig method, of class Bankiersessie.
     */
    @Test
    public void testIsGeldig()
    {
        System.out.println("isGeldig");
        assertTrue(bankiersessie1.isGeldig());
    }

    /**
     * Test of maakOver method, of class Bankiersessie.
     */
    @Test
    public void testMaakOver() throws Exception
    {
        System.out.println("maakOver");
        try
        {
            //zelfde rekening
            bankiersessie1.maakOver(rekening1, new Money(100, "euro"));
            fail("Het geld kan naar dezelfde rekening worden overgemaakt als de afzender.");
        } catch (Exception e)
        {
        }
        try
        {
            //negatief bedrag
            bankiersessie1.maakOver(rekening2, new Money(-100, "euro"));
            fail("Er kan een negatief bedrag worden overgemaakt.");
        } catch (Exception e)
        {
        }
        try
        {
            bankiersessie1.maakOver(rekening2, new Money(100, "euro"));
        } catch (Exception e)
        {
            fail("Het geld is niet overgemaakt.");
        }
    }

    /**
     * Test of logUit method, of class Bankiersessie.
     */
    @Test
    public void testLogUit() throws Exception
    {
        try
        {
            bankiersessie1.logUit();
        } catch (Exception e)
        {
            fail("Er kon niet worden uitgelogd.");
        }
    }
    
}
