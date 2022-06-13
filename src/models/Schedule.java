/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author aavhimovich
 */
public class Schedule
{

    private SimpleStringProperty Обозначение = null;
    private SimpleStringProperty Ответственный = null;
    private SimpleStringProperty Выписано = null;
    private SimpleStringProperty Примечание = null;

    public Schedule(String Обозначение, String Ответственный, String Выписано, String Примечание)
    {
        this.Обозначение = new SimpleStringProperty(Обозначение);
        this.Ответственный = new SimpleStringProperty(Ответственный);
        this.Выписано = new SimpleStringProperty(Выписано);
        this.Примечание = new SimpleStringProperty(Примечание);

    }

    public Schedule(String Выписано)
    {
        this.Выписано = new SimpleStringProperty(Выписано);
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

    public String getОтветственный()
    {
        return Ответственный.get();
    }

    public void setОтветственный(String value)
    {
        Ответственный.set(value);
    }

    public SimpleStringProperty ОтветственныйProperty()
    {
        return Ответственный;
    }

    public String getВыписано()
    {
        return Выписано.get();
    }

    public void setВыписано(String value)
    {
        Выписано.set(value);
    }

    public SimpleStringProperty ВыписаноProperty()
    {
        return Выписано;
    }

    public String getПримечание()
    {
        return Примечание.get();
    }

    public void setПримечание(String value)
    {
        Примечание.set(value);
    }

    public SimpleStringProperty ПримечаниеProperty()
    {
        return Примечание;
    }
}
