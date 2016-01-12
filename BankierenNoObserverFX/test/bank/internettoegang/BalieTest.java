/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.internettoegang;

import bank.bankieren.Bank;
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
 * @author Gebruiker
 */
public class BalieTest {

    public BalieTest()
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
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of openRekening method, of class Balie.
     */
    @Test
    public void testOpenRekening()
    {
        try
        {
            System.out.println("openRekening");
            String naam = "";
            String plaats = "";
            String wachtwoord = "";
            Balie instance = new Balie(new Bank("test"));
            String expResult = null;
            String result = instance.openRekening(naam, plaats, wachtwoord);
            assertNull(result);
            naam = "bob";
            plaats = "tilburg";
            wachtwoord = "123456";
            result = instance.openRekening(naam, plaats, wachtwoord);
            assertNotNull(result);
        } catch (RemoteException ex)
        {
            Logger.getLogger(BalieTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of logIn method, of class Balie.
     */
    @Test
    public void testLogIn() throws Exception
    {
        System.out.println("logIn");
        String wachtwoord = "123456";
        String naam = "bob";
        String plaats = "tilburg";
        Balie instance = new Balie(new Bank("test"));
        String account = instance.openRekening(naam, plaats, wachtwoord);
        IBankiersessie result = instance.logIn("", wachtwoord);
        assertNull(result);
        result = instance.logIn(account, "");
        assertNull(result);
        result = instance.logIn(account, wachtwoord);
        assertNotNull(result);
    }
}
