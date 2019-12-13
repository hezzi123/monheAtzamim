package Ex1;
public class ComplexFunction implements complex_function
{
    private function left;
    private function right;
    private Operation operation;

    /**
     * Zero (empty polynom)
     */
    public ComplexFunction ()
    {
        this.left = new Polynom ("0");
        this.right = new Polynom ("0");
        this.operation = Operation.None;
    }

    /**
     * constructor
     * @param operation is a variable represents the operation between the functions
     * @param left is the left function
     * @param right is the right function
     */
    public ComplexFunction (Operation operation , function left , function right)
    {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }
    /**
     * constructor in case we have only 1 function
     * the operation will be nothing and th right function as well
     * @param left is the left function
     */
    public ComplexFunction (function left) {
        this.left = left;
        this.right = null;
        this.operation = Operation.None;
    }
    /**
     * constructor in case we will get a operation as string
     * @param s is a variable represents the operation between the functions
     * @param left is the left function
     * @param right is the right function
     */
    public ComplexFunction (String s, function left, function right)
    {
        if(s.equals("plus"))
        {
            this.operation = Operation.Plus;
        }
        else if (s.equals("mul"))
        {
            this.operation = Operation.Times;
        }
        else if (s.equals("div"))
        {
            this.operation = Operation.Divid;
        }
        else if (s.equals("max"))
        {
            this.operation = Operation.Max;
        }
        else if (s.equals("min"))
        {
            this.operation = Operation.Min;
        }
        else if (s.equals("comp"))
        {
            this.operation = Operation.Comp;
        }
        else if (s.equals("comp"))
        {
            this.operation = Operation.Comp;
        }
        else
        {
            throw new ArithmeticException("Can't use an unknown operation");
        }
        this.left = left;
        this.right = right;
    }

    /**
     * Add to this complex_function the f1 complex_function
     * @param f1 the complex_function which will be added to this complex_function.
     */
    public void plus(function f1)
    {
        this.left = new ComplexFunction (this.operation, this.left, this.right);
        this.operation = Operation.Plus;
        this.right = f1;
    }

    /**
     * Multiply this complex_function with the f1 complex_function
     * @param f1 the complex_function which will be multiply be this complex_function.
     */
    public void mul(function f1)
    {
        this.left = new ComplexFunction (this.operation, this.left, this.right);
        this.operation = Operation.Times;
        this.right = f1;
    }

    /**
     *  Divides this complex_function with the f1 complex_function
     * @param f1 the complex_function which will be divid this complex_function.
     */
    public void div(function f1)
    {
        this.left = new ComplexFunction (this.operation, this.left, this.right);
        this.operation = Operation.Divid;
        this.right = f1;
    }

    /**
     * Computes the maximum over this complex_function and the f1 complex_function
     * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
     */
    public void max(function f1)
    {
        this.left = new ComplexFunction (this.operation, this.left, this.right);
        this.operation = Operation.Max;
        this.right = f1;
    }

    /**
     *  Computes the minimum over this complex_function and the f1 complex_function
     * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
     */
    public void min(function f1)
    {
        this.left = new ComplexFunction (this.operation, this.left, this.right);
        this.operation = Operation.Min;
        this.right = f1;
    }

    /**
     * This method wrap the f1 complex_function with this function: this.f(f1(x))
     * @param f1 complex function
     */
    public void comp(function f1)
    {
        this.left = new ComplexFunction (this.operation, this.left, this.right);
        this.operation = Operation.Comp;
        this.right = f1;
    }

    /**
     * returns the left side of the complex function - this side should always exists (should NOT be null).
     * @return a function representing the left side of this complex function
     */
    public function left()
    {
        return this.left;
    }

    /**
     *  returns the right side of the complex function - this side might not exists (aka equals null).
     * @return a function representing the left side of this complex function
     */
    public function right()
    {
        return this.right;
    }

    /**
     * The complex_function operation: plus, mul, div, max, min, comp
     * @return
     */
    public Operation getOp()
    {
        return this.operation;
    }

    /**
     * Function that calculate the complex function with x at with right operator
     * @param x the number the will be of x
     */
    public double f(double x)
    {
        if(this.operation == Operation.Plus)
        {
            return this.left.f(x) + this.right.f(x);
        }
        else if(this.operation == Operation.Times)
        {
            return this.left.f(x) * this.right.f(x);
        }
        else if(this.operation == Operation.Divid)
        {
            return this.left.f(x) / this.right.f(x);
        }
        else if(this.operation == Operation.Min)
        {
            return Math.min(this.left.f(x) , this.right.f(x));
        }
        else if(this.operation == Operation.Max)
        {
            return Math.max(this.left.f(x) , this.right.f(x));
        }
        else if(this.operation == Operation.Comp)
        {
            return this.left.f(right.f(x));
        }
        else if (this.operation == Operation.None)
        {
            return this.left.f(x);
        }
        else
        {
            throw new ArithmeticException("Can't use an unknown operation");
        }
    }

    /**
     * Function that gets a string and init to a complex function
     * @param s the string that we r init to function
     * @return the string as a function
     */
    public function initFromString(String s)
    {
        s = s.replaceAll(" " , "");
        if(!s.contains(","))
        {
            return new Polynom(s);
        }
        int counterBackSlash = 1;
        int i;
        for (i = s.indexOf('(') + 1; i < s.length(); i++)
        {
            if(s.charAt(i) == '(')
            {
                counterBackSlash++;
            }
            if (s.charAt(i) == ',')
            {
                counterBackSlash--;
            }
            if(counterBackSlash == 0)
            {
                break;
            }
        }
        String operation = s.substring(0 , s.indexOf('('));
        function left = initFromString(s.substring(s.indexOf('(') + 1 , i));
        function right = initFromString(s.substring(i + 1 , s.lastIndexOf(')')));
        function complexFunction = new ComplexFunction(operation , left , right);
        return complexFunction;
    }

    /**
     * Function that checks if the object instance of ComplexFunction and if true
     * checks they r equals.
     * @return true if they r equals else, false
     */
    public boolean equals(Object obj)
    {
        ComplexFunction complexFunction;
        ComplexFunction myComplexFunction;
        boolean b = true;
        //checking if the insert object is frm complexFunction
        if (!(obj instanceof ComplexFunction))
        {
            return false;
        }
        complexFunction = new ComplexFunction (((ComplexFunction) obj).operation , ((ComplexFunction) obj).left , ((ComplexFunction) obj).right);
        myComplexFunction = new ComplexFunction (this.operation , this.left , this.right);
        for (int i = -30; i < 30 && i != 0;i++)
        {
            if (myComplexFunction.f(i) != complexFunction.f(i))
            {
                b = false;
                i = 31;
            }

        }
        if (b == false)
            return false;
        return true;
    }

    /**
     * @return the copied complexFunction of this complex.
     */
    public function copy()
    {
        return new ComplexFunction (this.operation, this.left, this.right);
    }

    /*
     *return the string of our complexFunction
     */
    public String toString()
    {
        if(this.operation == Operation.Plus)
        {
            return "plus(" + this.left + "," + this.right + ")";
        }
        else if(this.operation == Operation.Times)
        {
            return "mul(" + this.left + "," + this.right + ")";
        }
        else if(this.operation == Operation.Divid)
        {
            return "div(" + this.left +","+ this.right + ")";
        }
        else if(this.operation == Operation.Min)
        {
            return "min(" + this.left + "," + this.right + ")";
        }
        else if(this.operation == Operation.Max)
        {
            return "max(" + this.left + "," + this.right + ")";
        }
        else if(this.operation == Operation.Comp)
        {
            return "comp(" + this.left + "," + this.right + ")";
        }
        else if(this.operation == Operation.None)
        {
            if(this.right == null)
            {
                return "" + this.left;
            }
        }
        return this.operation + "(" + this.left + "," + this.right + ")";
    }
}
