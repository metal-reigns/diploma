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
public class Search
{

    private final SimpleIntegerProperty Элементы_1_IDЭлементов;
    private final SimpleStringProperty Обозначение;
    private final SimpleStringProperty Дерево_Кол;

    public Search(int Элементы_1_IDЭлементов, String Обозначение, String Дерево_Кол)
    {
        this.Элементы_1_IDЭлементов = new SimpleIntegerProperty(Элементы_1_IDЭлементов);
        this.Обозначение = new SimpleStringProperty(Обозначение);
        this.Дерево_Кол = new SimpleStringProperty(Дерево_Кол);
    }

    public int getЭлементы_1_IDЭлементов()
    {
        return Элементы_1_IDЭлементов.get();
    }

    public void setЭлементы_1_IDЭлементов(int value)
    {
        Элементы_1_IDЭлементов.set(value);
    }

    public SimpleIntegerProperty Элементы_1_IDЭлементовProperty()
    {
        return Элементы_1_IDЭлементов;
    }

    public String getОбозначение()
    {
        return Обозначение.get();
    }

    public void setОбозначение(String value)
    {
        Обозначение.set(value);
    }

    public SimpleStringProperty ОбозначениеProperty()
    {
        return Обозначение;
    }

    public String getДерево_Кол()
    {
        return Дерево_Кол.get();
    }

    public void setДерево_Кол(String value)
    {
        Дерево_Кол.set(value);
    }

    public SimpleStringProperty Дерево_КолProperty()
    {
        return Дерево_Кол;
    }

}
