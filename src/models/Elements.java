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
public class Elements
{

    private final SimpleIntegerProperty Элементы_IDЭлементов;
    private final SimpleStringProperty Элементы_КТДЭ;
    private final SimpleStringProperty Элементы_Обозначение;
    private final SimpleStringProperty Элементы_Наименование;
    private final SimpleStringProperty Элементы_примечание;

    public Elements(int Элементы_IDЭлементов, String Элементы_КТДЭ, String Элементы_Обозначение, String Элементы_Наименование, String Элементы_примечание)
    {
        this.Элементы_IDЭлементов = new SimpleIntegerProperty(Элементы_IDЭлементов);
        this.Элементы_КТДЭ = new SimpleStringProperty(Элементы_КТДЭ);
        this.Элементы_Обозначение = new SimpleStringProperty(Элементы_Обозначение);
        this.Элементы_Наименование = new SimpleStringProperty(Элементы_Наименование);
        this.Элементы_примечание = new SimpleStringProperty(Элементы_примечание);
    }

    public int getЭлементы_IDЭлементов()
    {
        return Элементы_IDЭлементов.get();
    }

    public void setЭлементы_IDЭлементов(int value)
    {
        Элементы_IDЭлементов.set(value);
    }

    public SimpleIntegerProperty Элементы_IDЭлементовProperty()
    {
        return Элементы_IDЭлементов;
    }

    public String getЭлементы_КТДЭ()
    {
        return Элементы_КТДЭ.get();
    }

    public void setЭлементы_КТДЭ(String value)
    {
        Элементы_КТДЭ.set(value);
    }

    public SimpleStringProperty Элементы_КТДЭProperty()
    {
        return Элементы_КТДЭ;
    }

    public String getЭлементы_Обозначение()
    {
        return Элементы_Обозначение.get();
    }

    public void setЭлементы_Обозначение(String value)
    {
        Элементы_Обозначение.set(value);
    }

    public SimpleStringProperty Элементы_ОбозначениеProperty()
    {
        return Элементы_Обозначение;
    }

    public String getЭлементы_Наименование()
    {
        return Элементы_Наименование.get();
    }

    public void setЭлементы_Наименование(String value)
    {
        Элементы_Наименование.set(value);
    }

    public SimpleStringProperty Элементы_НаименованиеProperty()
    {
        return Элементы_Наименование;
    }

    public String getЭлементы_примечание()
    {
        return Элементы_примечание.get();
    }

    public void setЭлементы_примечание(String value)
    {
        Элементы_примечание.set(value);
    }

    public SimpleStringProperty Элементы_примечаниеProperty()
    {
        return Элементы_примечание;
    }
}
