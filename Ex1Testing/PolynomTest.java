package Ex1Testing;

import Ex1.Monom;
import Ex1.Polynom;
import org.junit.Test;

import static org.junit.Assert.*;

public class PolynomTest
{
    //copy tester
    @Test
    public void copy()
    {

        Polynom p1 = new Polynom("x^2+4x-8");
        Polynom p2 = (Polynom)p1.copy();
        Polynom expected=new Polynom("x^2+4x-8");
        assertTrue(p2.equals(expected));

    }

    //Multipy tester
    @Test
    public void MultipyMonomPolynom()
    {

        Polynom p1 = new Polynom("x^2+4x");
        Monom m = new Monom(1 , 1);
        Polynom p2 = new Polynom("x-3");
        p1.multiply(m);
        //x^3+4x^2
        p1.multiply(p2);
        //x^4+x^3-12x^2
        Polynom expected=new Polynom("x^4+x^3-12x^2");
        assertTrue(p1.equals(expected));
    }

    //Add and substract tester
    @Test
    public void addAndSubstract()
    {

        Polynom p1 = new Polynom("x^2+4x");
        Polynom p2 = new Polynom("x-3");
        p1.substract(p1);
        //x^3+4x^2
        p1.add(p2);
        //x^4+x^3-12x^2
        Polynom expected=new Polynom("x-3");
        assertTrue(p1.equals(expected));
    }

    //Constructor tester
    @Test
    public void checkingMyConstructor()
    {
        Polynom p1 = new Polynom();
        String[] monoms = {"4", "-x^2","x^2","-4","x^3","x^4"};
        for(int i=0;i<monoms.length;i++) {
            Monom m = new Monom(monoms[i]);
            p1.add(m);
        }
        Polynom expected=new Polynom("x^4+x^3");
        assertTrue(p1.equals(expected));
    }

    //area and root tester
    @Test
    public void areaAndRoot()
    {
        String[][] polynoms = {{"4x^3","-5x^4","6x","-7"}};
        double[][] res = {{0,0.2135},{0,0.83334},{2.404,0.9999}};
        for (int i = 0; i < polynoms.length; i++) {
            Polynom p1 = new Polynom();
            for (int j = 0; j < polynoms[i].length; j++) {
                Monom temp = new Monom(polynoms[i][j]);
                p1.add(temp);
            }
            Polynom expected=new Polynom("4x^3-5x^4+6x-7");
            assertTrue(p1.equals(expected));
        }
    }

    //derivative tester
    @Test
    public void derivative()
    {
        Polynom p1 = new Polynom();
        String[] monoms = {"4", "-5x^2","x^2","-9x","x^3","x^4"};
        for(int i=0;i<monoms.length;i++) {
            Monom m = new Monom(monoms[i]);
            p1.add(m);
        }
        Polynom p2 = (Polynom)p1.derivative();
        Polynom expected=new Polynom("4x^3+3x^2-8x-9");
        assertTrue(p2.equals(expected));
    }

    //isZero with substract tester
    @Test
    public void checkingIs0()
    {
        Polynom p1 = new Polynom();
        String[] monoms = {"4", "-5x^2","x^2","-9x","x^3","x^4"};
        for(int i=0;i<monoms.length;i++) {
            Monom m = new Monom(monoms[i]);
            p1.add(m);
        }
        p1.substract(p1);
        boolean b = p1.isZero();
        boolean expected = true;
        assertEquals(b , expected);
    }

    //Init from string test
    @Test
    public void InitFromString()
    {
        Polynom m1 = new Polynom("0");
        m1 = (Polynom) m1.initFromString("x^3-2x^4+1+x^4");
        Polynom expected=new Polynom("-x^4+x^3+1");
        assertTrue(m1.equals(expected));
    }

    //f(x) tester
    @Test
    public void fx()
    {

        Polynom p1 = new Polynom("x^2+4x+3");
        double x = p1.f(1);
        double expected = 8;
        assertTrue(x == expected );
    }

}