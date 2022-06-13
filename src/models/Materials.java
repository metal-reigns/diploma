/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Unlimited
 */
public class Materials
{

    private final SimpleIntegerProperty Материалы_IDМатериалов;
    private final SimpleStringProperty Материалы_КТДМ;
    private final SimpleStringProperty Материалы_Материал;
    private final SimpleStringProperty Материалы_ЕИМ;
    private final SimpleStringProperty Формулы_Формула;
    private final SimpleStringProperty Материалы_Коэффициент;

    public Materials(int Материалы_IDМатериалов, String Материалы_КТДМ, String Материалы_Материал, String Материалы_ЕИМ, String Формулы_Формула, String Материалы_Коэффициент)
    {
        this.Материалы_IDМатериалов = new SimpleIntegerProperty(Материалы_IDМатериалов);
        this.Материалы_КТДМ = new SimpleStringProperty(Материалы_КТДМ);
        this.Материалы_Материал = new SimpleStringProperty(Материалы_Материал);
        this.Материалы_ЕИМ = new SimpleStringProperty(Материалы_ЕИМ);
        this.Формулы_Формула = new SimpleStringProperty(Формулы_Формула);
        this.Материалы_Коэффициент = new SimpleStringProperty(Материалы_Коэффициент);
    }

    public int getМатериалы_IDМатериалов()
    {
        return Материалы_IDМатериалов.get();
    }

    public void setМатериалы_IDМатериалов(int value)
    {
        Материалы_IDМатериалов.set(value);
    }

    public SimpleIntegerProperty Материалы_IDМатериаловProperty()
    {
        return Материалы_IDМатериалов;
    }

    public String getМатериалы_КТДМ()
    {
        return Материалы_КТДМ.get();
    }

    public void setМатериалы_КТДМ(String value)
    {
        Материалы_КТДМ.set(value);
    }

    public SimpleStringProperty Материалы_КТДМProperty()
    {
        return Материалы_КТДМ;
    }

    public String getМатериалы_Материал()
    {
        return Материалы_Материал.get();
    }

    public void setМатериалы_Материал(String value)
    {
        Материалы_Материал.set(value);
    }

    public SimpleStringProperty Материалы_МатериалProperty()
    {
        return Материалы_Материал;
    }

    public String getМатериалы_ЕИМ()
    {
        return Материалы_ЕИМ.get();
    }

    public void setМатериалы_ЕИМ(String value)
    {
        Материалы_ЕИМ.set(value);
    }

    public SimpleStringProperty Материалы_ЕИМProperty()
    {
        return Материалы_ЕИМ;
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

    public String getМатериалы_Коэффициент()
    {
        return Материалы_Коэффициент.get();
    }

    public void setМатериалы_Коэффициент(String value)
    {
        Материалы_Коэффициент.set(value);
    }

    public SimpleStringProperty Материалы_КоэффициентProperty()
    {
        return Материалы_Коэффициент;
    }
}
