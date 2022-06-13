/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Unlimited
 */
public class Formulas
{

    private final SimpleIntegerProperty Формулы_IDФормул;
    private final SimpleStringProperty Формулы_Формула;
    private final SimpleStringProperty Формулы_ФормулаРасчета;
    private final SimpleStringProperty Формулы_а;
    private final SimpleStringProperty Формулы_б;
    private final SimpleStringProperty Формулы_в;
    private final SimpleStringProperty Формулы_г;
    private final SimpleStringProperty Формулы_д;
    private final SimpleStringProperty Формулы_е;
    public Formulas(int Формулы_IDФормул, String Формулы_Формула, String Формулы_ФормулаРасчета, String Формулы_а, String Формулы_б, String Формулы_в, String Формулы_г, String Формулы_д, String Формулы_е)
    {
        this.Формулы_IDФормул = new SimpleIntegerProperty(Формулы_IDФормул);
        this.Формулы_Формула = new SimpleStringProperty(Формулы_Формула);
        this.Формулы_ФормулаРасчета = new SimpleStringProperty(Формулы_ФормулаРасчета);
        this.Формулы_а = new SimpleStringProperty(Формулы_а);
        this.Формулы_б = new SimpleStringProperty(Формулы_б);
        this.Формулы_в = new SimpleStringProperty(Формулы_в);
        this.Формулы_г = new SimpleStringProperty(Формулы_г);
        this.Формулы_д = new SimpleStringProperty(Формулы_д);
        this.Формулы_е = new SimpleStringProperty(Формулы_е);
    }



    public int getФормулы_IDФормул()
    {
        return Формулы_IDФормул.get();
    }

    public void setФормулы_IDФормул(int value)
    {
        Формулы_IDФормул.set(value);
    }

    public SimpleIntegerProperty Формулы_IDФормулProperty()
    {
        return Формулы_IDФормул;
    }

    public String getФормулы_Формула()
    {
        return Формулы_Формула.get();
    }

    public void setФормулы_Формула(String value)
    {
        Формулы_Формула.set(value);
    }

    public SimpleStringProperty Формулы_ФормулаProperty()
    {
        return Формулы_Формула;
    }

    public String getФормулы_ФормулаРасчета()
    {
        return Формулы_ФормулаРасчета.get();
    }

    public void setФормулы_ФормулаРасчета(String value)
    {
        Формулы_ФормулаРасчета.set(value);
    }

    public SimpleStringProperty Формулы_ФормулаРасчетаProperty()
    {
        return Формулы_ФормулаРасчета;
    }

    public String getФормулы_а()
    {
        return Формулы_а.get();
    }

    public void setФормулы_а(String value)
    {
        Формулы_а.set(value);
    }

    public SimpleStringProperty Формулы_аProperty()
    {
        return Формулы_а;
    }

    public String getФормулы_б()
    {
        return Формулы_б.get();
    }

    public void setФормулы_б(String value)
    {
        Формулы_б.set(value);
    }

    public SimpleStringProperty Формулы_бProperty()
    {
        return Формулы_б;
    }

    public String getФормулы_в()
    {
        return Формулы_в.get();
    }

    public void setФормулы_в(String value)
    {
        Формулы_в.set(value);
    }

    public SimpleStringProperty Формулы_вProperty()
    {
        return Формулы_в;
    }

    public String getФормулы_г()
    {
        return Формулы_г.get();
    }

    public void setФормулы_г(String value)
    {
        Формулы_г.set(value);
    }

    public SimpleStringProperty Формулы_гProperty()
    {
        return Формулы_г;
    }

    public String getФормулы_д()
    {
        return Формулы_д.get();
    }

    public void setФормулы_д(String value)
    {
        Формулы_д.set(value);
    }

    public SimpleStringProperty Формулы_дProperty()
    {
        return Формулы_д;
    }

    public String getФормулы_е()
    {
        return Формулы_е.get();
    }

    public void setФормулы_е(String value)
    {
        Формулы_е.set(value);
    }

    public SimpleStringProperty Формулы_еProperty()
    {
        return Формулы_е;
    }

}
