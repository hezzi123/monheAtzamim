package Ex1Testing;

import Ex1.ComplexFunction;
import Ex1.Polynom;
import Ex1.function;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComplexFunctionTest
{
    //multipy and plus tester
    @Test
    public void mulAndPlus()
    {
        function f1 = new Polynom("x^4");
        function f2 = new Polynom("x^2");
        function f3 = new Polynom("x");
        function f4 = new Polynom("1");
        ComplexFunction c = new ComplexFunction("div" , f1 , f3);
        ComplexFunction expected = new ComplexFunction("mul" , f2 , f4);
        expected.mul(f4);
        c.div(f3);
        assertTrue(c.equals(expected));
    }

    //multipy and plus tester as false expected
    @Test
    public void mulAndPlusFalse()
    {
        function f1 = new Polynom("x^4");
        function f2 = new Polynom("x^2");
        function f3 = new Polynom("x");
        function f4 = new Polynom("1");
        ComplexFunction c = new ComplexFunction("div" , f1 , f3);
        ComplexFunction expected = new ComplexFunction("mul" , f2 , f4);
        expected.mul(f4);
        c.div(f3);
        c.plus(f4);
        assertFalse(c.equals(expected));
    }

    //minimum and maximum tester
    @Test
    public void minAndMax()
    {
        function f1 = new Polynom("x^4");
        function f2 = new Polynom("x^2");
        function f3 = new Polynom("x");
        function f4 = new Polynom("1");
        ComplexFunction c = new ComplexFunction("max" , f1 , f3);
        ComplexFunction expected = new ComplexFunction("min" , f1 , f2);
        expected.mul(f4);
        c.div(f2);
        assertTrue(c.equals(expected));
    }

    @Test
    public void checkingConstructorWithCopy()
    {
        function f1 = new Polynom("x^4");
        function f2 = new Polynom("-x^4");
        ComplexFunction c1 = new ComplexFunction("plus" , f1 , f2);
        ComplexFunction c = new ComplexFunction();
        ComplexFunction expected = (ComplexFunction) c.copy();
        assertTrue(c.equals(expected));
    }

    //init from string tester
    @Test
    public void initfromstring()
    {
        function f1 = new Polynom("8x");
        function f2 = new Polynom("5x^2");
        function f3 = new Polynom("3");
        String s = "max(mul(8.0x,5.0x^2),3)";
        ComplexFunction c = new ComplexFunction();
        ComplexFunction cf = (ComplexFunction) c.initFromString(s);
        ComplexFunction expected = new ComplexFunction("mul" , f1 , f2);
        expected.max(f3);
        assertTrue(cf.equals(expected));
    }

}