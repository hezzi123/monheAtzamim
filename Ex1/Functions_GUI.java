package Ex1;

import com.google.gson.Gson;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;




public class Functions_GUI implements functions {

    private ArrayList <function> arr;

    //constructor
    public Functions_GUI ()
    {
        this.arr = new ArrayList <function>();
    }

    /**
     * Init a new collection of functions from a file
     * @param file - the file name
     * @throws IOException if the file does not exists of unreadable (wrong format)
     */
    public void initFromFile (String file) throws IOException
    {
        String s="";
        FileReader fileReader = new FileReader (file);
        BufferedReader bufferedReader = new BufferedReader (fileReader);
        ComplexFunction complexFunction = new ComplexFunction ();
        while (bufferedReader.readLine() != null)
        {
            if (bufferedReader.readLine() != null)
            {
                function function = complexFunction.initFromString(s);
                this.arr.add(function);
            }
        }
        bufferedReader.close();
    }
    /**
     * this fucntion saves to file.
     * @param file - the file name
     * @throws IOException if the file is not writable
     */
    public void saveToFile (String file) throws IOException
    {
        FileWriter fileWriter;
        fileWriter = new FileWriter (file);
        for (int i = 0; i < this.arr.size(); i++)
        {
            fileWriter.write(this.arr.get(i).toString());
        }
        fileWriter.close();
    }

    /**
     * Draws all the functions in the collection in a GUI window using the
     * given parameters for the GUI windo and the range & resolution
     * @param width - the width of the window - in pixels
     * @param height - the height of the window - in pixels
     * @param rx - the range of the horizontal axis
     * @param ry - the range of the vertical axis
     * @param resolution - the number of samples with in rx: the X_step = rx/resulution
     */
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution)
    {
        Color[] color = {Color.BLUE , Color.CYAN , Color.MAGENTA , Color.RED , Color.GREEN , Color.PINK};
        StdDraw.setCanvasSize(width , height);
        StdDraw.setYscale(ry.get_min() , ry.get_max());
        StdDraw.setXscale(rx.get_min() , rx.get_max());
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setFont(new Font("Arial", Font.BOLD, 16));
        for(double i = ry.get_min(); i < ry.get_max();i++)
        {
            StdDraw.text(0.2,i+0.2,i+"");
            StdDraw.line(rx.get_min() , i , rx.get_max() , i);
        }
        for(double i = rx.get_min(); i < rx.get_max(); i++)
        {
            StdDraw.text(i+0.2,0.2,i+"");
            StdDraw.line(i, ry.get_min(), i, ry.get_max());
        }
        StdDraw.setPenRadius(0.007);
        StdDraw.line(rx.get_min(),0, rx.get_max(), 0);
        StdDraw.line(0, ry.get_min(), 0, ry.get_max());
        double minDraw = Math.abs(rx.get_min());
        double maxDraw = Math.abs(rx.get_max());
        double draw = (minDraw + maxDraw) / resolution;
        double a;
        for(int i = 0; i<this.arr.size(); i++)
        {
            StdDraw.setPenColor(color[i % color.length]);//if it will be only i it will bring us for exception
            for(double j = rx.get_min(); j < rx.get_max(); j += draw)
            {
                a = j + draw;
                StdDraw.line(j , this.arr.get(i).f(j),a, this.arr.get(i).f(a));
            }
        }
    }

    /**
     * Draws all the functions in the collection in a GUI window using the given JSON file
     * @param json_file - the file with all the parameters for the GUI window.
     * Note: is the file id not readable or in wrong format should use default values.
     */
    public void drawFunctions(String json_file) {
        Gson g = new Gson();
        try
        {
            GUI_params j = g.fromJson(new FileReader(json_file), GUI_params.class);
            String result = g.toJson(j);
            System.out.println(result);
            int width = j.getWidth();
            int height = j.getHeight();
            Range firstRange = new Range(j.Range_X[0], j.Range_X[1]);
            Range secondRange = new Range(j.Range_Y[0], j.Range_Y[1]);
            int resolution = j.getResolution();
            drawFunctions(width , height , firstRange , secondRange , resolution);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public boolean addAll(Collection<? extends function> c)
    {
        return this.arr.addAll(c);
    }

    public void clear()
    {
        this.arr.clear();
    }

    public boolean contains(Object o)
    {
        return this.arr.contains(o);
    }

    public boolean containsAll(Collection<?> c)
    {
        return this.arr.containsAll(c);
    }

    public boolean isEmpty()
    {
        return this.arr.isEmpty();
    }

    public Iterator<function> iterator()
    {
        return this.arr.iterator();
    }

    public boolean remove(Object o)
    {
        return this.arr.remove(o);
    }

    public boolean removeAll(Collection<?> c)
    {
        return this.arr.removeAll(c);
    }

    public boolean retainAll(Collection<?> c)
    {
        return this.arr.retainAll(c);
    }

    public int size()
    {
        return this.arr.size();
    }

    public Object[] toArray()
    {
        return this.arr.toArray();
    }

    public <T> T[] toArray(T[] a)
    {
        return this.arr.toArray(a);
    }

    public boolean add(function e)
    {
        return this.arr.add(e);
    }



}
