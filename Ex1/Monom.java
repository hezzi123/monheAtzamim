package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Ex1.Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative),
 * see: https://en.wikipedia.org/wiki/Monomial
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply.
 * @author Boaz
 *
 */
public class Monom implements function{
    public static final Monom ZERO = new Monom(0,0);
    public static final Monom MINUS1 = new Monom(-1,0);
    public static final double EPSILON = 0.0000001;
    public static final Comparator<Monom> _Comp = new Monom_Comperator();
    public static Comparator<Monom> getComp() {return _Comp;}
    public Monom(double a, int b){
        this.set_coefficient(a);
        this.set_power(b);
    }
    public Monom(Monom ot) {
        this(ot.get_coefficient(), ot.get_power());
    }

    public double get_coefficient() {
        return this._coefficient;
    }
    public int get_power() {
        return this._power;
    }
    /**
     * this method returns the derivative monom of this.
     * @return
     */
    public Monom derivative() {
        if(this.get_power()==0) {return getNewZeroMonom();}
        return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
    }
    public double f(double x) {
        double ans=0;
        double p = this.get_power();
        ans = this.get_coefficient()*Math.pow(x, p);
        return ans;
    }
    public boolean isZero() {return this.get_coefficient() == 0;}
    // ***************** add your code below **********************

    /**
     *Function that get a string
     *build a monom from the string and check all limit problems
     * @param s : is a string represents a Ex1.Monom
     */
    public Monom(String s)
    {
        boolean b = false;
        s = s.replaceAll(" " , "");
        s = s.toLowerCase();
        int count = s.length() - s.replaceAll("x","").length();
        if(isNumeric(s) == true)
        {
            this._power = 0;
            this._coefficient = Double.valueOf(s);
        }
        else if (count != 1)
        {
            throw new ArithmeticException("not valid input");
        }
        if(s.charAt(0) == '0' && b == false)
        {
            this._coefficient = 0;
            this._power = 0;
            b = true;
        }
        if (s.charAt(s.length() - 1) == 'x')
        {
            s = s + "^1";
        }
        if (s.equals("x"))
        {
            this._power = 1;
            this._coefficient = 1;
        }
        else if (s.contains("x") && b == false)
        {
            if (s.charAt(0) == 'x')
            {
                s = "1" + s;
            }
            if (s.charAt(0) == '-' && s.charAt(1) == 'x')
            {
                s = "-1" + s.substring(1);
            }
            String[] arr = s.split("x");
            if (arr.length > 2)
                throw new ArithmeticException("not valid input");
            if (isNumeric(arr[0]) == false)
            {
                throw new ArithmeticException("not valid input");
            }
            if(!arr[1].contains("^"))
            {
                throw new ArithmeticException("not valid input");
            }
            if (isNumeric(arr[1].substring(1)) == false)
            {
                throw new ArithmeticException("not valid input");
            }
            this._power = Integer.valueOf(arr[1].substring(1));
            this._coefficient = Double.valueOf(arr[0]);
        }
        else if(!s.contains("x"))
        {
            if (isNumeric(s) == false)
            {
                throw new ArithmeticException("not valid input");
            }
            else
            {
                this._coefficient = Double.valueOf(s);
            }
        }

    }

    /**
     * Function that get a monom
     * add the monom to this monom
     */
    public void add(Monom m)
    {
        if (m._power == this._power)
        {
            this._coefficient += m._coefficient;
        }
        else
        {
            throw new ArithmeticException("cant add a monom from a different power");
        }
    }


    /**
     *Function that get a monom
     * multipy the monom with this monom
     */
    public void multipy(Monom d)
    {
        this._coefficient *= d._coefficient;
        this._power += d._power;
    }

    /**
     * Return the string of the current monom
     */
    public String toString()
    {
        String ans = "";
        if(this._coefficient == 0)
        {
            ans = "0";
        }
        else if (this._power == 0)
        {
            ans = this._coefficient + "";
        }
        else
        {
            ans = this._coefficient + "x^" + this._power;
        }
        return ans;
    }

    /*
    *Function that gets a string and init to monom
     */
    public function initFromString(String s)
    {
        Monom m1 = new Monom(s);
        return m1;
    }

    /**
     * Copy this monom
     */
    public function copy()
    {
        return new Monom(this.get_coefficient() , this.get_power());
    }
    /*
    Checking if the two monoms are equals
     */
    public boolean equals(Monom m)
    {
        if(this._coefficient == m.get_coefficient() && this._power == m.get_power())
            return true;
        return false;
    }
    /**
     *Boolean function that get a string
     *check if the string is numeric or not
     */
    private boolean isNumeric(String s) {
        try
        {
            double d = Double.parseDouble(s);
        }
        catch (NumberFormatException | NullPointerException nfe)
        {
            return false;
        }
        return true;
    }
    // you may (always) add other methods.

    //****************** Private Methods and Data *****************


    private void set_coefficient(double a){
        this._coefficient = a;
    }
    private void set_power(int p) {
        if(p<0) {throw new RuntimeException("ERR the power of Ex1.Monom should not be negative, got: "+p);}
        this._power = p;
    }
    private static Monom getNewZeroMonom() {return new Monom(ZERO);}
    private double _coefficient;
    private int _power;


}
