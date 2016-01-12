/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.bankieren;

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
public class BankTest {

    public BankTest()
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
     * Test of openRekening method, of class Bank.
     */
    @Test
    public void testOpenRekening()
    {
        System.out.println("openRekening");
        String name = "bob";
        String city = "tilburg";
        Bank instance = null;
        int result = instance.openRekening(name, city);
        assertTrue(result >= 0);
    }

    /**
     * Test of getRekening method, of class Bank.
     */
    @Test
    public void testGetRekening()
    {
        System.out.println("getRekening");
        Bank instance = new Bank("test");
        int nr = instance.openRekening("bob IV", "tilburg");
        try
        {
            IRekening result = instance.getRekening(nr + 1);
            assertFalse(true);
        } catch (Exception ex)
        {
        }
        try
        {
            IRekening result = instance.getRekening(nr + 1);
        } catch (Exception ex)
        {
            assertFalse(true);
        }
    }

    /**
     * Test of maakOver method, of class Bank.
     */
    @Test
    public void testMaakOver() throws Exception
    {
        System.out.println("maakOver");
        Money money = new Money(1000, "€");
        Bank instance = new Bank("test");
        int source = instance.openRekening("bob", "tilburg");
        int destination = instance.openRekening("dave", "tilburg");
        boolean expResult = false;
        boolean result = instance.maakOver(source, destination, money);
        assertEquals(expResult, result);
        ((Rekening) (instance.getRekening(source))).muteer(money);
        expResult = true;
        result = instance.maakOver(source, destination, money);
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Bank.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        Bank instance = new Bank("test");
        String expResult = "test";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

}
