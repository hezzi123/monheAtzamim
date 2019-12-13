package Ex1Testing;

import Ex1.Monom;
import org.junit.Test;

import static org.junit.Assert.*;


//Junit monom tester
public class MonomTest
{
    //Add and multipy test
    @Test
    public void addMultipy()
    {

        Monom m1 = new Monom(1, 2);
        Monom m2 = new Monom(54 , 2);
        Monom m3 = new Monom(2 , 1);
        m1.add(m2);
        m1.multipy(m3);
        Monom expected=new Monom(110 , 3);
        assertTrue(m1.equals(expected));
    }

    //Checking if the monom is the correct monom from a string
    @Test
    public void checkingFromString()
    {
        Monom m1 = new Monom("4x");
        Monom expected=new Monom(4,1);
        assertTrue(m1.equals(expected));
    }

    //derivative test
    @Test
    public void checkingWIthDet()
    {
        Monom m1 = new Monom("8x^3");
        Monom m2 = m1.derivative();
        Monom expected=new Monom(24,2);
        assertTrue(m2.equals(expected));
    }

    //Checking if the limit monom is the correct monom from a string
    @Test
    public void checkingFromLimitString()
    {
        Monom m1 = new Monom("4x^0");
        Monom expected=new Monom(4,0);
        assertTrue(m1.equals(expected));
        Monom m2 = new Monom("0x^3");
        Monom expected2=new Monom(0,0);
        assertTrue(m2.equals(expected2));
    }

    //Add the same monom test
    @Test
    public void addSameMonom()
    {

        Monom m1 = new Monom(1, 3);
        m1.add(m1);
        m1.multipy(m1);
        Monom expected=new Monom(4 , 6);
        assertTrue(m1.equals(expected));
    }

    //Init from string test
    @Test
    public void InitFromString()
    {
        Monom m1 = new Monom("0");
        m1 = (Monom) m1.initFromString("x^3");
        Monom expected=new Monom("x^3");
        assertTrue(m1.equals(expected));
    }

    //copy test
    @Test
    public void copy()
    {
        Monom m1 = new Monom("3x^2");
        Monom m2 = (Monom) m1.copy();
        Monom expected=new Monom("3x^2");
        assertTrue(m2.equals(expected));
    }

}