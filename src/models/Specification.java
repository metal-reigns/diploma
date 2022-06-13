/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Unlimited
 */
public class Specification
{

    private SimpleStringProperty Дерево_Поз;
    private SimpleStringProperty Элементы_1_Обозначение;
    private SimpleStringProperty Элементы_1_Наименование;
    private SimpleStringProperty Элементы_1_Примечание;
    private SimpleStringProperty Дерево_Кол;
    private SimpleIntegerProperty Элементы_1_IDЭлементов;
    private SimpleStringProperty Материалы_IDМатериалов;
    private SimpleStringProperty Материалы_Материал;
    private SimpleStringProperty Формулы_IDФормул;
    private SimpleStringProperty Элементы_Обозначение;
    private SimpleStringProperty Элементы_1_а;
    private SimpleStringProperty Элементы_1_б;
    private SimpleStringProperty Элементы_1_в;
    private SimpleStringProperty Элементы_1_г;
    private SimpleStringProperty Элементы_1_д;
    private SimpleStringProperty Элементы_1_е;
    private SimpleStringProperty Элементы_1_НРМ;
    private SimpleStringProperty Материалы_ЕИМ;
    private SimpleStringProperty Формулы_ФормулаРасчета;
    private SimpleStringProperty Формулы_а;
    private SimpleStringProperty Формулы_б;
    private SimpleStringProperty Формулы_в;
    private SimpleStringProperty Формулы_г;
    private SimpleStringProperty Формулы_д;
    private SimpleStringProperty Формулы_е;
    private SimpleStringProperty Цех_НазваниеЦеха;
    private SimpleIntegerProperty Дерево_IDДерева;
    private SimpleStringProperty Цех_1_НазваниеЦеха;
    private SimpleStringProperty Элементы_1_КоэффициентЭ;
    private SimpleStringProperty Материалы_КоэффициентМ;
    private SimpleStringProperty Элементы_1_КТДЭ;
    private SimpleStringProperty Элементы_1_ЕИЭ;
    private SimpleStringProperty Материалы_КТДМ;
    private SimpleBooleanProperty Элементы_1_Узел;
    private SimpleStringProperty Выписано;

    public Specification(String Дерево_Поз, String Элементы_1_Обозначение, String Элементы_1_Наименование, String Элементы_1_Примечание, String Дерево_Кол, int Элементы_1_IDЭлементов, String Материалы_IDМатериалов, String Материалы_Материал, String Формулы_IDФормул, String Элементы_Обозначение, String Элементы_1_а, String Элементы_1_б, String Элементы_1_в, String Элементы_1_г, String Элементы_1_д, String Элементы_1_е, String Элементы_1_НРМ, String Материалы_ЕИМ, String Формулы_ФормулаРасчета, String Формулы_а, String Формулы_б, String Формулы_в, String Формулы_г, String Формулы_д, String Формулы_е, String Цех_НазваниеЦеха, int Дерево_IDДерева, String Цех_1_НазваниеЦеха, String Элементы_1_КоэффициентЭ, String Материалы_КоэффициентМ, String Элементы_1_КТДЭ, String Элементы_1_ЕИЭ, String Материалы_КТДМ, boolean Элементы_1_Узел, String Выписано)
    {
        this.Дерево_Поз = new SimpleStringProperty(Дерево_Поз);
        this.Элементы_1_Обозначение = new SimpleStringProperty(Элементы_1_Обозначение);
        this.Элементы_1_Наименование = new SimpleStringProperty(Элементы_1_Наименование);
        this.Элементы_1_Примечание = new SimpleStringProperty(Элементы_1_Примечание);
        this.Дерево_Кол = new SimpleStringProperty(Дерево_Кол);
        this.Элементы_1_IDЭлементов = new SimpleIntegerProperty(Элементы_1_IDЭлементов);
        this.Материалы_IDМатериалов = new SimpleStringProperty(Материалы_IDМатериалов);
        this.Материалы_Материал = new SimpleStringProperty(Материалы_Материал);
        this.Формулы_IDФормул = new SimpleStringProperty(Формулы_IDФормул);
        this.Элементы_Обозначение = new SimpleStringProperty(Элементы_Обозначение);
        this.Элементы_1_а = new SimpleStringProperty(Элементы_1_а);
        this.Элементы_1_б = new SimpleStringProperty(Элементы_1_б);
        this.Элементы_1_в = new SimpleStringProperty(Элементы_1_в);
        this.Элементы_1_г = new SimpleStringProperty(Элементы_1_г);
        this.Элементы_1_д = new SimpleStringProperty(Элементы_1_д);
        this.Элементы_1_е = new SimpleStringProperty(Элементы_1_е);
        this.Элементы_1_НРМ = new SimpleStringProperty(Элементы_1_НРМ);
        this.Материалы_ЕИМ = new SimpleStringProperty(Материалы_ЕИМ);
        this.Формулы_ФормулаРасчета = new SimpleStringProperty(Формулы_ФормулаРасчета);
        this.Формулы_а = new SimpleStringProperty(Формулы_а);
        this.Формулы_б = new SimpleStringProperty(Формулы_б);
        this.Формулы_в = new SimpleStringProperty(Формулы_в);
        this.Формулы_г = new SimpleStringProperty(Формулы_г);
        this.Формулы_д = new SimpleStringProperty(Формулы_д);
        this.Формулы_е = new SimpleStringProperty(Формулы_е);
        this.Цех_НазваниеЦеха = new SimpleStringProperty(Цех_НазваниеЦеха);
        this.Дерево_IDДерева = new SimpleIntegerProperty(Дерево_IDДерева);
        this.Цех_1_НазваниеЦеха = new SimpleStringProperty(Цех_1_НазваниеЦеха);
        this.Элементы_1_КоэффициентЭ = new SimpleStringProperty(Элементы_1_КоэффициентЭ);
        this.Материалы_КоэффициентМ = new SimpleStringProperty(Материалы_КоэффициентМ);
        this.Элементы_1_КТДЭ = new SimpleStringProperty(Элементы_1_КТДЭ);
        this.Элементы_1_ЕИЭ = new SimpleStringProperty(Элементы_1_ЕИЭ);
        this.Материалы_КТДМ = new SimpleStringProperty(Материалы_КТДМ);
        this.Элементы_1_Узел = new SimpleBooleanProperty(Элементы_1_Узел);
        this.Выписано = new SimpleStringProperty(Выписано);
    }

    public Specification(String Выписано)
    {
        this.Выписано = new SimpleStringProperty(Выписано);
    }

    public String getДерево_Поз()
    {
        return Дерево_Поз.get();
    }

    public void setДерево_Поз(String Дерево_Поз)
    {
        this.Дерево_Поз.set(Дерево_Поз);
    }

    public String getЭлементы_1_Обозначение()
    {
        return Элементы_1_Обозначение.get();
    }

    public void setЭлементы_1_Обозначение(String Элементы_1_Обозначение)
    {
        this.Элементы_1_Обозначение.set(Элементы_1_Обозначение);
    }

    public String getЭлементы_1_Наименование()
    {
        if (Элементы_1_Наименование.get() == null)
        {
            return "";
        }
        return Элементы_1_Наименование.get();
    }

    public void setЭлементы_1_Наименование(String Элементы_1_Наименование)
    {
        this.Элементы_1_Наименование.set(Элементы_1_Наименование);
    }

    public String getЭлементы_1_Примечание()
    {
        return Элементы_1_Примечание.get();
    }

    public void setЭлементы_1_Примечание(String Элементы_1_Примечание)
    {
        this.Элементы_1_Примечание.set(Элементы_1_Примечание);
    }

    public String getДерево_Кол()
    {
        return Дерево_Кол.get();
    }

    public void setДерево_Кол(String Дерево_Кол)
    {

        this.Дерево_Кол.set(Дерево_Кол);
    }

    public SimpleStringProperty Дерево_ПозProperty()
    {
        return Дерево_Поз;
    }

    public SimpleStringProperty Элементы_1_ОбозначениеProperty()
    {
        return Элементы_1_Обозначение;
    }

    public SimpleStringProperty Элементы_1_НаименованиеProperty()
    {

        return Элементы_1_Наименование;
    }

    public SimpleStringProperty Элементы_1_ПримечаниеProperty()
    {
        return Элементы_1_Примечание;
    }

    public SimpleStringProperty Дерево_КолProperty()
    {
        return Дерево_Кол;
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

    public String getМатериалы_IDМатериалов()
    {
        return Материалы_IDМатериалов.get();
    }

    public void setМатериалы_IDМатериалов(String value)
    {
        Материалы_IDМатериалов.set(value);
    }

    public SimpleStringProperty Материалы_IDМатериаловProperty()
    {
        return Материалы_IDМатериалов;
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

    public String getФормулы_IDФормул()
    {
        return Формулы_IDФормул.get();
    }

    public void setФормулы_IDФормул(String value)
    {
        Формулы_IDФормул.set(value);
    }

    public SimpleStringProperty Формулы_IDФормулProperty()
    {
        return Формулы_IDФормул;
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

    public String getЭлементы_1_а()
    {
        return Элементы_1_а.get();
    }

    public void setЭлементы_1_а(String value)
    {
        Элементы_1_а.set(value);
    }

    public SimpleStringProperty Элементы_1_аProperty()
    {
        return Элементы_1_а;
    }

    public String getЭлементы_1_б()
    {
        return Элементы_1_б.get();
    }

    public void setЭлементы_1_б(String value)
    {
        Элементы_1_б.set(value);
    }

    public SimpleStringProperty Элементы_1_бProperty()
    {
        return Элементы_1_б;
    }

    public String getЭлементы_1_в()
    {
        return Элементы_1_в.get();
    }

    public void setЭлементы_1_в(String value)
    {
        Элементы_1_в.set(value);
    }

    public SimpleStringProperty Элементы_1_вProperty()
    {
        return Элементы_1_в;
    }

    public String getЭлементы_1_г()
    {
        return Элементы_1_г.get();
    }

    public void setЭлементы_1_г(String value)
    {
        Элементы_1_г.set(value);
    }

    public SimpleStringProperty Элементы_1_гProperty()
    {
        return Элементы_1_г;
    }

    public String getЭлементы_1_д()
    {
        return Элементы_1_д.get();
    }

    public void setЭлементы_1_д(String value)
    {
        Элементы_1_д.set(value);
    }

    public SimpleStringProperty Элементы_1_дProperty()
    {
        return Элементы_1_д;
    }

    public String getЭлементы_1_е()
    {
        return Элементы_1_е.get();
    }

    public void setЭлементы_1_е(String value)
    {
        Элементы_1_е.set(value);
    }

    public SimpleStringProperty Элементы_1_еProperty()
    {
        return Элементы_1_е;
    }

    public String getЭлементы_1_НРМ()
    {
        return Элементы_1_НРМ.get();
    }

    public void setЭлементы_1_НРМ(String value)
    {
        Элементы_1_НРМ.set(value);
    }

    public SimpleStringProperty Элементы_1_НРМProperty()
    {
        return Элементы_1_НРМ;
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

    public String getЦех_НазваниеЦеха()
    {
        return Цех_НазваниеЦеха.get();
    }

    public void setЦех_НазваниеЦеха(String value)
    {
        Цех_НазваниеЦеха.set(value);
    }

    public SimpleStringProperty Цех_НазваниеЦехаProperty()
    {
        return Цех_НазваниеЦеха;
    }

    public int getДерево_IDДерева()
    {
        return Дерево_IDДерева.get();
    }

    public void setДерево_IDДерева(int value)
    {
        Дерево_IDДерева.set(value);
    }

    public SimpleIntegerProperty Дерево_IDДереваProperty()
    {
        return Дерево_IDДерева;
    }

    public String getЦех_1_НазваниеЦеха()
    {
        return Цех_1_НазваниеЦеха.get();
    }

    public void setЦех_1_НазваниеЦеха(String value)
    {
        Цех_1_НазваниеЦеха.set(value);
    }

    public SimpleStringProperty Цех_1_НазваниеЦехаProperty()
    {
        return Цех_1_НазваниеЦеха;
    }

    public String getЭлементы_1_КоэффициентЭ()
    {
        return Элементы_1_КоэффициентЭ.get();
    }

    public void setЭлементы_1_КоэффициентЭ(String value)
    {
        Элементы_1_КоэффициентЭ.set(value);
    }

    public SimpleIntegerProperty Элементы_1_КоэффициентЭProperty()
    {
        return Элементы_1_IDЭлементов;
    }

    public String getМатериалы_КоэффициентМ()
    {
        return Материалы_КоэффициентМ.get();
    }

    public void setМатериалы_КоэффициентМ(String value)
    {
        Материалы_КоэффициентМ.set(value);
    }

    public SimpleStringProperty Материалы_КоэффициентМProperty()
    {
        return Материалы_КоэффициентМ;
    }

    public String getЭлементы_1_КТДЭ()
    {
        return Элементы_1_КТДЭ.get();
    }

    public void setЭлементы_1_КТДЭ(String value)
    {
        Элементы_1_КТДЭ.set(value);
    }

    public SimpleStringProperty Элементы_1_КТДЭProperty()
    {
        return Элементы_1_КТДЭ;
    }

    public String getЭлементы_1_ЕИЭ()
    {
        return Элементы_1_ЕИЭ.get();
    }

    public void setЭлементы_1_ЕИЭ(String value)
    {
        Элементы_1_ЕИЭ.set(value);
    }

    public SimpleStringProperty Элементы_1_ЕИЭProperty()
    {
        return Элементы_1_ЕИЭ;
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

    public boolean getЭлементы_1_Узел()
    {
        return Элементы_1_Узел.get();
    }

    public void setЭлементы_1_Узел(boolean value)
    {
        Элементы_1_Узел.set(value);
    }

    public SimpleBooleanProperty Элементы_1_УзелProperty()
    {
        return Элементы_1_Узел;
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

}
