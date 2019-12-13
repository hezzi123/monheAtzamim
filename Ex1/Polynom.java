package Ex1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents a Ex1.Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 *
 * @author Boaz
 *
 */

public class Polynom implements Polynom_able
{
    private ArrayList<Ex1.Monom> arr = new ArrayList<Ex1.Monom>();

    /**
     * Zero (empty polynom)
     */
    public Polynom() {
        this.arr = new ArrayList<Ex1.Monom>();
    }
    /**
     * init a Ex1.Polynom from a String such as:
     *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
     * @param s: is a string represents a Ex1.Polynom
     */
    public Polynom(String s)
    {
        s = s.replaceAll(" " , "");
        if (s.contains("(") || s.contains(")"))
        {
            throw new ArithmeticException("not valid input");
        }
        s = s.replaceAll("-","+-");
        if (s.isEmpty())
        {
            s = "0";
        }
        if (s.charAt(0) == '+')
        {
            s = s.substring(1);
        }
        String [] strArr = s.split("\\+");
        Ex1.Monom m;
        for (int i = 0; i < strArr.length; i++)
        {
            m = new Ex1.Monom(strArr[i]);
            this.arr.add(m);
        }
        sortTheArray(this.arr);
    }

    /**
     *Function that gets a double
     * return this Ex1.Polynom value at x
     */
    public double f(double x)
    {
        double ans = 0;
        Ex1.Monom m;
        for (int i = 0; i < this.arr.size(); i++)
        {
            m = this.arr.get(i);
            ans = ans + ((Math.pow(x , m.get_power())) * m.get_coefficient());
        }
        return ans;
    }

    /**
     * Function that adds p1 to this Ex1.Polynom
     * @param p1 : is a Polynom_able represents a polynom
     */
    public void add(Polynom_able p1)
    {
        boolean b = false;
        Ex1.Monom m1;
        Ex1.Monom m;
        Iterator<Ex1.Monom> itr = p1.iteretor();
        while (itr.hasNext())
        {
            m1 = itr.next();
            for (int i = 0; b == false && i < this.arr.size(); i++)
            {
                if (this.arr.get(i).get_power() == m1.get_power())
                {
                    m = new Ex1.Monom(this.arr.get(i).get_coefficient() + m1.get_coefficient() , m1.get_power());
                    this.arr.remove(i);
                    this.arr.add(i , m);
                    b = true;
                }
            }
            if (b == false)
            {
                this.arr.add(m1);
            }
            b = false;
        }
    }

    /**
     * Function that adds monom to this Ex1.Polynom
     */
    public void add(Ex1.Monom m1)
    {
        boolean b = false;
        Ex1.Monom m;
        for (int i = 0; b == false && i < this.arr.size(); i++)
        {
            if (this.arr.get(i).get_power() == m1.get_power())
            {
                m = new Ex1.Monom(this.arr.get(i).get_coefficient() + m1.get_coefficient() , m1.get_power());
                this.arr.remove(i);
                this.arr.add(i , m);
                b = true;
            }
        }
        if (b == false)
        {
            this.arr.add(m1);
        }
    }

    /**
     * Function that substracts p1 from this Ex1.Polynom
     * @param p1 : is a Polynom_able represents a polynom
     */
    public void substract(Polynom_able p1)
    {
        Ex1.Monom m1;
        Ex1.Monom m;
        Ex1.Monom m2 = new Ex1.Monom(-1 , 0);
        boolean b = false;
        Polynom_able copyPoly = p1.copy();
        Iterator<Ex1.Monom> itr = copyPoly.iteretor();
        while (itr.hasNext())
        {
            m1 = itr.next();
            for (int i = 0; b == false && i < this.arr.size(); i++)
            {
                if (this.arr.get(i).get_power() == m1.get_power())
                {
                    m = new Ex1.Monom(this.arr.get(i).get_coefficient() - m1.get_coefficient() , m1.get_power());
                    this.arr.remove(i);
                    this.arr.add(i , m);
                    b = true;
                }
            }
            if (b == false)
            {
                m1.multipy(m2);
                this.arr.add(m1);
            }
            b = false;
        }
        sorted(this.arr);

    }

    /**
     * Function that multiplies p1 with this Ex1.Polynom
     * @param p1 : is a Polynom_able represents a polynom
     */
    public void multiply(Polynom_able p1)
    {
        Ex1.Monom m;
        Ex1.Monom m1;
        Ex1.Monom m2;
        Iterator<Ex1.Monom> itr = p1.iteretor();
        Iterator<Ex1.Monom> myItr = this.iteretor();
        ArrayList<Ex1.Monom> brr = new ArrayList<Ex1.Monom>();
        while (itr.hasNext())
        {
            m = itr.next();
            while (myItr.hasNext())
            {
                m1 = myItr.next();
                m2 = new Ex1.Monom(m.get_coefficient() * m1.get_coefficient() , m.get_power() + m1.get_power());
                brr.add(m2);
            }
            myItr = this.iteretor();
        }
        this.arr.clear();
        for (int i = 0; i < brr.size(); i++)
        {
            this.arr.add(brr.get(i));
        }
        sortTheArray(this.arr);
    }

    /**
     *Function that gets a polynom and check if this polynom is same as p1
     * @param p1
     */
    public boolean equals(Polynom_able p1)
    {
        boolean b = true;
        sorted(this.arr);
        p1.toString();
        Iterator <Ex1.Monom> itr1 = this.iteretor();
        Iterator <Ex1.Monom> itr2 = p1.iteretor();
        while(itr1.hasNext() && itr2.hasNext())
        {
            if(!itr1.next().toString().equals(itr2.next().toString()))
            {
                b = false;
            }
        }
        if (itr1.hasNext() || itr2.hasNext())
        {
            b = false;
        }
        return b;
    }

    /**
     * Boolean function that checks if the polynom equals to 0
     */
    public boolean isZero()
    {
        if (this.arr.size() == 1 && this.arr.get(0).toString().equals("0"))
            return true;
        return false;
    }

    /**
     * assuming (f(x0)*f(x1)<=0, returns f(x2) such that:
     *  x0<=x2<=x2 & (ii) {f(x2)<eps
     * @param x0 starting point
     * @param x1 end point
     * @param eps>0 (positive) representing the epsilon range the solution should be within.
     */
    public double root(double x0, double x1, double eps)
    {
        double sumLim = x0 + x1;
        if (this.f(x0) * this.f(x1) > 0)
        {
            throw new ArithmeticException("positive polynom has no roots");
        }
        if (this.f(x0) == 0)
        {
            return x0;
        }
        if (this.f(x1) == 0)
        {
            return x1;
        }
        if(Math.abs(x0-x1) < eps)
        {
            return sumLim / 2;
        }
        double mid = sumLim/2;
        if (this.f(x0) * this.f(mid) < 0)
        {
            return root(x0 , mid , eps);
        }
        else
        {
            return root(mid, x1, eps);
        }
    }

    /**
     * Function that copy this polynom to another polynom
     */
    public Polynom_able copy()
    {
        Ex1.Monom m;
        Polynom_able p = new Polynom();
        Iterator<Ex1.Monom> itr = this.iteretor();
        while(itr.hasNext())
        {
            p.add(itr.next());
        }
        return p;
    }

    /**
     * Function that calculates the dervative of this polynom
     */
    public Polynom_able derivative()
    {
        Ex1.Monom m;
        Ex1.Monom m1;
        Polynom_able p = new Polynom();
        for (int i = 0; i < this.arr.size(); i++)
        {
            m = this.arr.get(i);
            if (m.get_power() > 0)
            {
                m1 = new Ex1.Monom(m.get_power() * m.get_coefficient(), m.get_power() - 1);
                p.add(m1);
            }
            else
            {
                m1 = new Ex1.Monom(0, 0);
                p.add(m1);
            }
        }
        return p;
    }

    /**
     * Function that calculate the integral of the polynom as close as possible
     * @param x0 starting pooint
     * @param x1 end point
     * @param eps positive step value
     * @return
     */
    public double area(double x0, double x1, double eps)
    {
        int x2;
        double areaAnswer;
        double x3;
        x2 = (int)(x1-x0);
        areaAnswer = 0;
        x3 = x0;
        for (int i = 0; i < Math.abs(x2 / eps); i++)
        {
            if(0 < this.f(x3))
            {
                areaAnswer += (this.f(x3) * eps);
            }
            x3 += eps;
        }
        return areaAnswer;
    }

    /**
     * function that return this iterator
     */
    public Iterator<Ex1.Monom> iteretor()
    {
        return this.arr.iterator();
    }

    /**
     * function that gets monom
     * multipy this polynom with a Ex1.Monom
     */
    public void multiply(Monom m1)
    {
        for (int i = 0; i < this.arr.size(); i++)
        {
            this.arr.get(i).multipy(m1);
        }
        sortTheArray(this.arr);
    }

    /**
     * function that return the string of the current polynom
     */
    public String toString()
    {
        String s = sorted(this.arr);
        if (s.isEmpty())
        {
            return "0";
        }
        if (s.charAt(0) == '+')
        {
            s = s.substring(1);
        }
        return s;
    }

    /*
    * Gets a string and init to polynom
     */
    public function initFromString(String s)
    {
        Polynom p1 = new Polynom(s);
        return p1;
    }

    /**
     * Function that helps to the function of the toString to print it
     * by order from the highest to the lowest
     */
    private String sorted(ArrayList<Ex1.Monom> m)
    {
        double max = -1;
        Ex1.Monom [] mArr = new Ex1.Monom[m.size()];
        int maxIndex = -1;
        for (int j = 0; j < mArr.length; j++)
        {
            for (int i = 0; i < m.size(); i++)
            {
                if (m.get(i).get_power() > max)
                {
                    maxIndex = i;
                    max = m.get(i).get_power();
                }
            }
            mArr[j] = m.get(maxIndex);
            m.remove(maxIndex);
            max = -1;
        }
        String s ="";
        for (int i = 0; i < mArr.length; i++)
        {
            if(!mArr[i].isZero() && mArr[i].toString().charAt(0) == '-')
                s = s + mArr[i].toString();
            else if (!mArr[i].isZero())
                s = s + "+" + mArr[i].toString();
        }
        this.arr.clear();
        Polynom_able p = new Polynom(s);
        Iterator<Ex1.Monom> itr = p.iteretor();
        while(itr.hasNext())
        {
            this.arr.add(itr.next());
        }
        return s;
    }

    /**
     * Function the add and substract the monoms with the same power
     */
    private void sortTheArray(ArrayList<Ex1.Monom> a)
    {
        for (int i = 0; i < this.arr.size(); i++)
        {
            for (int j = 0; j < i; j++)
            {
                if (this.arr.get(i).get_power() == this.arr.get(j).get_power())
                {
                    this.arr.get(i).add(this.arr.get(j));
                    this.arr.remove(j);
                    j =0;
                    i = 0;
                }
            }
        }
    }
}
