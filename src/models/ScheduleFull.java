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
public class ScheduleFull
{

    private SimpleStringProperty Обозначение = null;
    private SimpleStringProperty Ответственный = null;
    private SimpleStringProperty Выписано = null;
    private SimpleStringProperty Примечание = null;
    private SimpleStringProperty Заготовка = null;
    private SimpleStringProperty Сборка = null;
    private SimpleStringProperty Панели = null;
    private SimpleStringProperty Электрики = null;

    public ScheduleFull(String Обозначение, String Ответственный, String Выписано, String Примечание, String Заготовка, String Сборка, String Панели, String Электрики)
    {
        this.Обозначение = new SimpleStringProperty(Обозначение);
        this.Ответственный = new SimpleStringProperty(Ответственный);
        this.Выписано = new SimpleStringProperty(Выписано);
        this.Примечание = new SimpleStringProperty(Примечание);
        this.Заготовка = new SimpleStringProperty(Заготовка);
        this.Сборка = new SimpleStringProperty(Сборка);
        this.Панели = new SimpleStringProperty(Панели);
        this.Электрики = new SimpleStringProperty(Электрики);
    }

    public ScheduleFull(String Выписано)
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
    public String getЗаготовка()
    {
        return Заготовка.get();
    }

    public void setЗаготовка(String value)
    {
        Заготовка.set(value);
    }

    public SimpleStringProperty ЗаготовкаProperty()
    {
        return Заготовка;
    }

    public String getСборка()
    {
        return Сборка.get();
    }

    public void setСборка(String value)
    {
        Сборка.set(value);
    }

    public SimpleStringProperty СборкаProperty()
    {
        return Сборка;
    }

    public String getПанели()
    {
        return Панели.get();
    }

    public void setПанели(String value)
    {
        this.Панели = Панели;
    }

    public SimpleStringProperty ПанелиProperty()
    {
        return Панели;
    }

    public String getЭлектрики()
    {
        return Электрики.get();
    }

    public void setЭлектрики(String value)
    {
        Электрики.set(value);
    }

    public SimpleStringProperty ЭлектрикиProperty()
    {
        return Электрики;
    }
}
