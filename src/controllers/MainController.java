/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.CommonMaterialsValues;
import models.CommonMaterials;
import models.Elements;
import models.Formulas;
import models.Specification;
import models.Search;
import models.SpecificationCommonMaterials;
import models.Materials;
import models.Schedule;
import models.MaterialsValues;
import models.Orders;
import static controllers.Variables.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import models.ScheduleFull;
import org.apache.log4j.helpers.DateTimeDateFormat;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

/**
 *
 * @author Unlimited
 */
public class MainController implements Initializable
{

    private final ObservableList<Specification> dataSpecification = FXCollections.observableArrayList();
    private final ObservableList<Specification> dataElement = FXCollections.observableArrayList();
    private final ObservableList<Specification> dataSpecificationRow = FXCollections.observableArrayList();
    private final ObservableList<Elements> dataElements = FXCollections.observableArrayList();
    private final ObservableList<Materials> dataMaterials = FXCollections.observableArrayList();
    private final ObservableList<CommonMaterials> dataCommonMaterials = FXCollections.observableArrayList();
    private final ObservableList<Formulas> dataFormulas = FXCollections.observableArrayList();
    private final ObservableList<Search> dataSearch = FXCollections.observableArrayList();
    private final ObservableList<Orders> dataOrders = FXCollections.observableArrayList();
    private final ObservableList<Schedule> dataSchedule = FXCollections.observableArrayList();
    private ObservableList<Specification> specification1 = FXCollections.observableArrayList();
    private ObservableList<Integer> schedule = FXCollections.observableArrayList();

    private final ObservableList<MaterialsValues> dataMaterialsValues = FXCollections.observableArrayList();
    private final ObservableList<SpecificationCommonMaterials> dataSpecificationCommonMaterials = FXCollections.observableArrayList();
    private final ObservableList<CommonMaterialsValues> dataCommonMaterialsValues = FXCollections.observableArrayList();

    private final ObservableList guildList = FXCollections.observableArrayList();
    private final ObservableList guildMaterialsList = FXCollections.observableArrayList();
    private final ObservableList<String> materialsList = FXCollections.observableArrayList();

    private final ObservableList guildCommonMaterialsList = FXCollections.observableArrayList();
    private final ObservableList commonMaterialsList = FXCollections.observableArrayList();
    private final ObservableList elementsList = FXCollections.observableArrayList();
    private final ObservableList<String> elementsListReplace = FXCollections.observableArrayList();
    private final ObservableList<String> elementsListReplaced = FXCollections.observableArrayList();
    private final ObservableList<String> orderList = FXCollections.observableArrayList();

    private final ObservableList parentsList = FXCollections.observableArrayList();
    private final ObservableList formulasList = FXCollections.observableArrayList();
    private final ObservableList<Integer> idReplaceList = FXCollections.observableArrayList();
    private final ObservableList<Integer> idReplacedList = FXCollections.observableArrayList();

    private final ObservableList<String> lastSelectedList = FXCollections.observableArrayList();
    private final KeyCodeCombination keyCodeCopy = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_ANY);

    private final Set<String> order = new HashSet<>();
    private String stringComboboxSchedule;

    Dialog dialog = new Dialog();

    String titleName;

    Desktop desktop = null;
    String userSecondName;
    int id;
    int count1;
    TreeItem<String> treeItem;
    static ArrayList<Integer> rowIndex;

    Specification specification;
    Materials materials;
    MaterialsValues materialsValues;

    SpecificationCommonMaterials commonMaterial;

    ComboBox<String> comboBoxReplace = new ComboBox(elementsListReplace);
    ComboBox<String> comboBoxReplaced = new ComboBox(elementsListReplaced);

    String ktd;
    String coefficient;
    String element;

    double[] values = new double[6];
    Alert alert;

    @FXML
    private Button buttonFinde;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonLeft;
    @FXML
    private Button buttonRight;
    @FXML
    private Button buttonAddElementRow;

    @FXML
    private Button buttonDeleteElementRow;

    @FXML
    private JFXComboBox comboboxElement;

    @FXML
    private JFXComboBox<?> comboboxGuild;
    @FXML
    private JFXComboBox comboboxElementParents;
    @FXML
    private JFXComboBox comboboxMaterial;
    @FXML
    private JFXComboBox comboboxGuildMaterial;
    @FXML
    private JFXComboBox comboboxCommonMaterial;
    @FXML
    private JFXComboBox comboboxGuildCommonMaterial;

    @FXML
    public Label labelParent;
    @FXML
    private Label labelMaterialFormula;
    @FXML
    private Label labelMaterialResult;
    @FXML
    private Label labelMaterialUnit;

    @FXML
    private Label labelCommonFormula;
    @FXML
    private Label labelCommonResult;
    @FXML
    private Label labelCommonUnit;
    @FXML
    private TableView<Specification> tableViewSpecification;

    @FXML
    private TableColumn<Specification, String> tableColumnPosition;
    @FXML
    private TableColumn<Specification, String> tableColumnElement;
    @FXML
    private TableColumn<Specification, String> tableColumnName;
    @FXML
    private TableColumn<Specification, String> tableColumnDescription;
    @FXML
    private TableColumn<Specification, String> tableColumnCount;

    @FXML
    private TableView<MaterialsValues> tableViewMaterialValues;
    @FXML
    private TableColumn<MaterialsValues, String> tableColumnMaterialParameter;
    @FXML
    private TableColumn<MaterialsValues, String> tableColumnMaterialValue;

    @FXML
    private TableView<CommonMaterialsValues> tableViewCommonValues;
    @FXML
    private TableColumn<CommonMaterialsValues, String> tableColumnCommonParameter;
    @FXML
    private TableColumn<CommonMaterialsValues, String> tableColumnCommonValue;

    @FXML
    public TableView<SpecificationCommonMaterials> tableViewCommonMaterial;

    @FXML
    private TableColumn<SpecificationCommonMaterials, String> tableColumnCommon;
    @FXML
    private TableColumn<SpecificationCommonMaterials, String> tableColumnCommonResult;
    @FXML
    private TableColumn<SpecificationCommonMaterials, String> tableColumnUnit;
    @FXML
    private TableColumn<SpecificationCommonMaterials, String> tableColumnCommonGuild;

    @FXML
    private TextField textFieldPosition;
    @FXML
    private TextField textFieldElement;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldDescription;
    @FXML
    private TextField textFieldCount;
    @FXML
    private TextField textFieldUnit;
    @FXML
    private TextField textFieldKTD;
    @FXML
    private TextField textFieldKTDC;
    @FXML
    private TextField textFieldСoefficientC;
    @FXML
    private TextField textFieldСoefficientCE;
    @FXML
    private TextField textFieldKTDM;
    @FXML
    private TextField textFieldСoefficientM;
    @FXML
    private TextField textFieldСoefficientE;
    @FXML
    private Button buttonFindeTree;
    @FXML
    private JFXComboBox comboboxTree;
    @FXML
    private TextField textFieldTableElement;
    @FXML
    private TextField textFieldTableName;
    @FXML
    private Button buttonAddElement;
    @FXML
    private Button buttonFilterElements;
    @FXML
    private Button buttonChangeElement;
    @FXML
    private Button buttonDeleteElement;
    @FXML
    private Button buttonAddCommon;
    @FXML
    private Button buttonChangeCommon;
    @FXML
    private Button buttonDeleteCommon;
    @FXML
    private Button buttonFormulaUpdate;
    @FXML
    private Button buttonFormulaChange;
    @FXML
    private Button buttonFormulaDelete;
    @FXML
    private Button buttonAddMaterial;
    @FXML
    private Button buttonChangeMaterial;
    @FXML
    private Button buttonDeleteMaterial;
    @FXML
    private Button buttonAddCommonRow;
    @FXML
    private Button buttonDeleteCommonRow;
    @FXML
    private MenuItem menuItemSaveMaterials;
    @FXML
    private MenuItem menuItemSaveCut;
    @FXML
    private MenuItem menuItemSaveTree;
    @FXML
    private MenuItem menuItemCopy;
    @FXML
    private TreeView<String> treeView;
    //private ProgressBar progresBar;
    @FXML
    private TableView<Search> tableViewSearch;
    @FXML
    private TableView<Elements> tableViewElements;
    @FXML
    private TableColumn<Elements, String> tableColumnElementKTDE;
    @FXML
    private TableColumn<Elements, String> tableColumnElementElement;
    @FXML
    private TableColumn<Elements, String> tableColumnElementName;
    @FXML
    private TableColumn<Elements, String> tableColumnElementDescription;
    @FXML
    public TableView<CommonMaterials> tableViewCommonMaterials;
    @FXML
    private TableColumn<CommonMaterials, String> tableColumnCommonKTDV;
    @FXML
    private TableColumn<CommonMaterials, String> tableColumnCommonCommon;
    @FXML
    private TableColumn<CommonMaterials, String> tableColumnCommonUnit;
    @FXML
    private TableColumn<CommonMaterials, String> tableColumnCommonFormula;
    @FXML
    private TableView<Formulas> tableViewFormulas;
    @FXML
    private TableColumn<Formulas, String> tableColumnFormulaName;
    @FXML
    private TableColumn<Formulas, String> tableColumnFormulaFormula;
    @FXML
    private TableColumn<Formulas, String> tableColumnFormulaA;
    @FXML
    private TableColumn<Formulas, String> tableColumnFormulaB;
    @FXML
    private TableColumn<Formulas, String> tableColumnFormulaC;
    @FXML
    private TableColumn<Formulas, String> tableColumnFormulaD;
    @FXML
    private TableColumn<Formulas, String> tableColumnFormulaE;
    @FXML
    private TableColumn<Formulas, String> tableColumnFormulaF;
    @FXML
    private TableView<Materials> tableViewMaterials;
    @FXML
    private TableColumn<Materials, String> tableColumnMaterialKTDM;
    @FXML
    private TableColumn<Materials, String> tableColumnMaterialMaterial;
    @FXML
    private TableColumn<Materials, String> tableColumnMaterialUnit;
    @FXML
    private TableColumn<Materials, String> tableColumnMaterialFormula;
    @FXML
    private TableColumn<Materials, String> tableColumnMaterialCoefficient;
    @FXML
    private Button buttonFilterCommon;
    @FXML
    private Button buttonFilterMaterial;
    @FXML
    private TextField textFieldMaterial;
    @FXML
    private TextField textFieldCommonMaterial;
    @FXML
    private TextField textFieldElementsFiltered;
    @FXML
    private TextField textFieldTableDescription;
    @FXML
    private TextField textFieldTableKTD;
    @FXML
    private JFXComboBox comboboxTableCommon;
    @FXML
    private JFXComboBox comboboxTableCommonFormula;
    @FXML
    private TextField textFieldFormula;
    @FXML
    private TextField textFieldCalculation;
    @FXML
    private TextField textFieldA;
    @FXML
    private TextField textFieldD;
    @FXML
    private TextField textFieldB;
    @FXML
    private TextField textFieldE;
    @FXML
    private TextField textFieldC;
    @FXML
    private TextField textFieldF;
    @FXML
    private JFXComboBox comboboxTableMateral;
    @FXML
    private JFXComboBox comboboxTableMaterialFormulа;
    @FXML
    private TextField textFieldTableMaterialUnit;
    @FXML
    private TextField textFieldTableMaterialKTDM;
    @FXML
    private TextField textFieldTableCommonKTDV;
    @FXML
    private TextField textFieldTableCommonUnit;
    @FXML
    private Button buttonAddFormula;
    @FXML
    private JFXComboBox comboboxSearchElement;
    @FXML
    private Button buttonSearchElement;
    @FXML
    private TableColumn<Search, String> tableColumnTableSearchCount;
    @FXML
    private TableColumn<Search, String> tableColumnTableSearch;

    @FXML
    private MenuItem menuItemReplaceElement;
    @FXML
    private MenuItem menuItemReplaceMaterial;
    @FXML
    private MenuItem menuItemReplaceCommon;
    @FXML
    private MenuItem menuItemReplaceFormula;
    @FXML
    private CheckBox checkBoxAccelerate;
    @FXML
    private MenuItem menuItemExit;
    @FXML
    private CheckBox checkBoxNode;
    @FXML
    private CheckBox checkBoxNodes;
    @FXML
    private JFXComboBox comboboxSchedule;
    @FXML
    private TreeTableView<Schedule> treeTableView;
    @FXML
    private TreeTableColumn<Schedule, String> treeTableColumnElement;
    @FXML
    private TreeTableColumn<Schedule, String> treeTableColumnWritten;
    @FXML
    private TreeTableColumn<Schedule, String> treeTableColumnResponsible;
    @FXML
    private TreeTableColumn<Schedule, String> treeTableColumnNote;
    @FXML
    private TextField textFieldOrder;
    @FXML
    private Button buttonAddOrder;
    @FXML
    private TableView<Orders> tableViewOrder;
    @FXML
    private TableColumn<Orders, String> tableColumnTableOrder;
    @FXML
    private JFXComboBox<?> comboboxTableElement;
    @FXML
    private Button buttonUpdateOrders;
    @FXML
    private Button buttonDeleteOrder;
    @FXML
    private MenuItem menuItemSaveGraph;
    @FXML
    private JFXButton buttonPanel;
    @FXML
    private MenuItem menuItemSaveCheck;
    @FXML
    private JFXButton buttonBar;
    @FXML
    private TabPane tabPaneLeft;
    @FXML
    private JFXButton buttonHomeSpecification;
    @FXML
    private JFXButton buttonHomeTree;
    @FXML
    private ContextMenu contextMenu;
    //private JFXTextField jfxTextFieldMaterial;

    @FXML
    private AnchorPane anchorPanneMaterial;
    @FXML
    private AnchorPane anchorPaneElement;
    @FXML
    private Tab tabSchedule;
    @FXML
    private TableColumn<Specification, String> tableColumnWritten;
    @FXML
    private JFXCheckBox checkBoxWriten;
    @FXML
    private JFXComboBox<String> comboboxOrder;
    @FXML
    private AnchorPane anchorePaneMain;
    @FXML
    private MenuItem menuItemSaveMaterialsFull;
    @FXML
    private MenuItem menuItemCooperation;
    @FXML
    private TreeTableColumn<?, ?> treeTableColumnHarvesting;
    @FXML
    private TreeTableColumn<?, ?> treeTableColumnAssembly;
    @FXML
    private TreeTableColumn<?, ?> treeTableColumnPanels;
    @FXML
    private TreeTableColumn<?, ?> treeTableColumnelEctricians;
    @FXML
    private JFXTextField textFieldTimer;
    @FXML
    private JFXTextField textFieldTableMaterialCofficient;
    @FXML
    private MenuItem menuItemCooperationMidi;
    @FXML
    private MenuItem menuItemCommon;
    @FXML
    private MenuItem menuItemColoring;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        if (Desktop.isDesktopSupported())
        {
            desktop = Desktop.getDesktop();
        }

        textFieldElement.setContextMenu(new ContextMenu());
        treeView.getStyleClass().add("treeView");
        tableViewSpecification.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableViewCommonMaterial.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        comboboxElementParents.setItems(parentsList);
        comboboxElement.setItems(elementsList);

        comboboxElement.getEditor().setOnKeyTyped((event) ->
        {
            boolean max = comboboxElement.getEditor().getText().length() > 59;
            if (max)
            {
                event.consume();
            }
        });
        comboboxTree.getEditor().setOnKeyTyped((event) ->
        {
            boolean max = comboboxElement.getEditor().getText().length() > 59;
            if (max)
            {
                event.consume();
            }
        });
        textFieldElementsFiltered.setOnKeyTyped((event) ->
        {
            boolean max = comboboxElement.getEditor().getText().length() > 59;
            if (max)
            {
                event.consume();
            }
        });
        textFieldTableElement.setOnKeyTyped((event) ->
        {
            boolean max = comboboxElement.getEditor().getText().length() > 59;
            if (max)
            {
                event.consume();
            }
        });
        textFieldTableElement.setOnKeyTyped((event) ->
        {
            boolean max = comboboxElement.getEditor().getText().length() > 59;
            if (max)
            {
                event.consume();
            }
        });

        comboboxSearchElement.setItems(elementsList);
        comboboxTree.setItems(elementsList);
        comboboxGuild.setItems(guildList);
        comboboxSchedule.setItems(orderList);
        comboboxOrder.setItems(orderList);
        comboboxMaterial.setItems(materialsList);
        comboboxGuildMaterial.setItems(guildMaterialsList);
        comboboxCommonMaterial.setItems(commonMaterialsList);
        comboboxGuildCommonMaterial.setItems(guildCommonMaterialsList);

        tableViewSpecification.setItems(dataSpecification);

        tableViewElements.setItems(dataElements);

        tableColumnElementKTDE.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnElementKTDE.setCellValueFactory(new PropertyValueFactory<Elements, String>("Элементы_КТДЭ"));
        tableColumnElementElement.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnElementElement.setCellValueFactory(new PropertyValueFactory<Elements, String>("Элементы_Обозначение"));
        tableColumnElementName.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnElementName.setCellValueFactory(new PropertyValueFactory<Elements, String>("Элементы_Наименование"));
        tableColumnElementDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnElementDescription.setCellValueFactory(new PropertyValueFactory<Elements, String>("Элементы_примечание"));

        tableViewMaterials.setItems(dataMaterials);

        tableColumnMaterialKTDM.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnMaterialKTDM.setCellValueFactory(new PropertyValueFactory<Materials, String>("Материалы_КТДМ"));
        tableColumnMaterialMaterial.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnMaterialMaterial.setCellValueFactory(new PropertyValueFactory<Materials, String>("Материалы_Материал"));
        tableColumnMaterialUnit.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnMaterialUnit.setCellValueFactory(new PropertyValueFactory<Materials, String>("Материалы_ЕИМ"));
        tableColumnMaterialFormula.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnMaterialFormula.setCellValueFactory(new PropertyValueFactory<Materials, String>("Формулы_Формула"));
        tableColumnMaterialCoefficient.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnMaterialCoefficient.setCellValueFactory(new PropertyValueFactory<Materials, String>("Материалы_Коэффициент"));

        tableViewCommonMaterials.setItems(dataCommonMaterials);

        tableColumnCommonKTDV.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnCommonKTDV.setCellValueFactory(new PropertyValueFactory<CommonMaterials, String>("Вспомогательные_КТДВ"));
        tableColumnCommonCommon.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnCommonCommon.setCellValueFactory(new PropertyValueFactory<CommonMaterials, String>("Вспомогательные_Вспомогательный"));
        tableColumnCommonUnit.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnCommonUnit.setCellValueFactory(new PropertyValueFactory<CommonMaterials, String>("Вспомогательные_ЕИВ"));
        tableColumnCommonFormula.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnCommonFormula.setCellValueFactory(new PropertyValueFactory<CommonMaterials, String>("Формулы_Формула"));

        tableViewFormulas.setItems(dataFormulas);

        tableColumnFormulaName.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnFormulaName.setCellValueFactory(new PropertyValueFactory<Formulas, String>("Формулы_Формула"));
        tableColumnFormulaFormula.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnFormulaFormula.setCellValueFactory(new PropertyValueFactory<Formulas, String>("Формулы_ФормулаРасчета"));
        tableColumnFormulaA.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnFormulaA.setCellValueFactory(new PropertyValueFactory<Formulas, String>("Формулы_а"));
        tableColumnFormulaB.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnFormulaB.setCellValueFactory(new PropertyValueFactory<Formulas, String>("Формулы_б"));
        tableColumnFormulaC.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnFormulaC.setCellValueFactory(new PropertyValueFactory<Formulas, String>("Формулы_в"));
        tableColumnFormulaD.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnFormulaD.setCellValueFactory(new PropertyValueFactory<Formulas, String>("Формулы_г"));
        tableColumnFormulaE.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnFormulaE.setCellValueFactory(new PropertyValueFactory<Formulas, String>("Формулы_д"));
        tableColumnFormulaF.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnFormulaF.setCellValueFactory(new PropertyValueFactory<Formulas, String>("Формулы_е"));

        tableViewSearch.setItems(dataSearch);

        tableColumnTableSearch.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnTableSearch.setCellValueFactory(new PropertyValueFactory<Search, String>("Обозначение"));
        tableColumnTableSearchCount.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnTableSearchCount.setCellValueFactory(new PropertyValueFactory<Search, String>("Дерево_Кол"));

        tableViewMaterialValues.setItems(dataMaterialsValues);

        tableColumnCommonParameter.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnCommonParameter.setCellValueFactory(new PropertyValueFactory<CommonMaterialsValues, String>("parameter"));
        tableColumnCommonValue.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnCommonValue.setCellValueFactory(new PropertyValueFactory<CommonMaterialsValues, String>("value"));

        tableColumnMaterialParameter.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnMaterialParameter.setCellValueFactory(new PropertyValueFactory<MaterialsValues, String>("parameter"));
        tableColumnMaterialValue.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnMaterialValue.setCellValueFactory(new PropertyValueFactory<MaterialsValues, String>("value"));

        tableColumnPosition.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnPosition.setCellValueFactory(new PropertyValueFactory<Specification, String>("Дерево_Поз"));
        tableColumnPosition.setEditable(false);
        tableColumnElement.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnElement.setCellValueFactory(new PropertyValueFactory<Specification, String>("Элементы_1_Обозначение"));
        tableColumnElement.setEditable(false);
        tableColumnName.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnName.setCellValueFactory(new PropertyValueFactory<Specification, String>("Элементы_1_Наименование"));
        tableColumnName.setEditable(false);
        tableColumnDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnDescription.setCellValueFactory(new PropertyValueFactory<Specification, String>("Элементы_1_Примечание"));
        tableColumnDescription.setEditable(false);
        tableColumnCount.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnCount.setCellValueFactory(new PropertyValueFactory<Specification, String>("Дерево_Кол"));
        tableColumnCount.setEditable(false);

        tableColumnWritten.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnWritten.setCellValueFactory(new PropertyValueFactory<Specification, String>("Выписано"));
        tableColumnWritten.setEditable(true);

        tableViewSpecification.setEditable(true);

        tableColumnCommon.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnCommon.setCellValueFactory(new PropertyValueFactory<SpecificationCommonMaterials, String>("В_Вспомогательные_Вспомогательный"));
        tableColumnCommonResult.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnCommonResult.setCellValueFactory(new PropertyValueFactory<SpecificationCommonMaterials, String>("В_ЭлементыВспомогательные_НРМ"));
        tableColumnUnit.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnUnit.setCellValueFactory(new PropertyValueFactory<SpecificationCommonMaterials, String>("В_Вспомогательные_ЕИВ"));
        tableColumnCommonGuild.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnCommonGuild.setCellValueFactory(new PropertyValueFactory<SpecificationCommonMaterials, String>("В_Цех_НазваниеЦеха"));

        //progresBar.setProgress(0);
        comboBoxReplace.setEditable(true);
        comboBoxReplaced.setEditable(true);
        comboBoxReplace.setMinWidth(200);
        comboBoxReplace.setMaxWidth(200);
        comboBoxReplaced.setMinWidth(200);
        comboBoxReplaced.setMaxWidth(200);

        treeTableView.setEditable(true);
        treeTableColumnWritten.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        treeTableColumnResponsible.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        treeTableColumnNote.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        /*
        treeTableColumnHarvesting.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        treeTableColumnAssembly.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        treeTableColumnPanels.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        treeTableColumnelEctricians.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());*/

        treeTableColumnElement.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Schedule, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getОбозначение())
        );

        treeTableColumnResponsible.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Schedule, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getОтветственный())
        );
        treeTableColumnWritten.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Schedule, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getВыписано())
        );
        treeTableColumnNote.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Schedule, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getПримечание())
        );/*
        treeTableColumnHarvesting.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Schedule, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getЗаготовка())
        );
        treeTableColumnAssembly.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Schedule, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getСборка())
        );
        treeTableColumnPanels.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Schedule, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getПанели())
        );
        treeTableColumnelEctricians.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Schedule, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getЭлектрики())
        );*/

        treeTableColumnResponsible.setOnEditCommit((event) ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else
            {
                String str1 = treeTableView.getRoot().getValue().getОбозначение();
                String str2 = treeTableView.getSelectionModel().getSelectedItem().getValue().getОбозначение();
                updateSchedule("UPDATE Элементы INNER JOIN (Заказы INNER JOIN График ON Заказы.idЗаказа = График.Родитель) ON Элементы.IDЭлементов = График.Потомок SET График.Ответственный = \"" + event.getNewValue() + "\"\n"
                        + "WHERE (((Заказы.Заказ)=?) AND (concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=?);", str1, str2.substring(0, str2.lastIndexOf(" ")));
                treeTableView.getSelectionModel().getSelectedItem().getValue().setОтветственный(event.getNewValue());
            }

        });
        treeTableColumnWritten.setOnEditCommit((event) ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else
            {
                String str1 = treeTableView.getRoot().getValue().getОбозначение();
                String str2 = treeTableView.getSelectionModel().getSelectedItem().getValue().getОбозначение();
                updateSchedule("UPDATE Элементы INNER JOIN (Заказы INNER JOIN График ON Заказы.idЗаказа = График.Родитель) ON Элементы.IDЭлементов = График.Потомок SET График.Выписано = \"" + event.getNewValue() + "\"\n"
                        + "WHERE (((Заказы.Заказ)=?) AND (concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=?);", str1, str2.substring(0, str2.lastIndexOf(" ")));

                treeTableView.getSelectionModel().getSelectedItem().getValue().setВыписано(event.getNewValue());
            }
        });
        treeTableColumnNote.setOnEditCommit((event) ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else
            {
                String str1 = treeTableView.getRoot().getValue().getОбозначение();
                String str2 = treeTableView.getSelectionModel().getSelectedItem().getValue().getОбозначение();
                updateSchedule("UPDATE Элементы INNER JOIN (Заказы INNER JOIN График ON Заказы.idЗаказа = График.Родитель) ON Элементы.IDЭлементов = График.Потомок SET График.Примечание = \"" + event.getNewValue() + "\"\n"
                        + "WHERE (((Заказы.Заказ)=?) AND (concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=?);", str1, str2.substring(0, str2.lastIndexOf(" ")));

                treeTableView.getSelectionModel().getSelectedItem().getValue().setПримечание(event.getNewValue());
            }
        });/*
        treeTableColumnHarvesting.setOnEditCommit((event) ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else
            {
                String str1 = treeTableView.getRoot().getValue().getОбозначение();
                String str2 = treeTableView.getSelectionModel().getSelectedItem().getValue().getОбозначение();
                updateSchedule("UPDATE Элементы INNER JOIN (Заказы INNER JOIN График ON Заказы.idЗаказа = График.Родитель) ON Элементы.IDЭлементов = График.Потомок SET График.Заготовка = \"" + event.getNewValue() + "\"\n"
                        + "WHERE (((Заказы.Заказ)=?) AND (concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=?);", str1, str2.substring(0, str2.lastIndexOf(" ")));
                treeTableView.getSelectionModel().getSelectedItem().getValue().setЗаготовка(event.getNewValue());
            }

        });
        treeTableColumnAssembly.setOnEditCommit((event) ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else
            {
                String str1 = treeTableView.getRoot().getValue().getОбозначение();
                String str2 = treeTableView.getSelectionModel().getSelectedItem().getValue().getОбозначение();
                updateSchedule("UPDATE Элементы INNER JOIN (Заказы INNER JOIN График ON Заказы.idЗаказа = График.Родитель) ON Элементы.IDЭлементов = График.Потомок SET График.Сборка = \"" + event.getNewValue() + "\"\n"
                        + "WHERE (((Заказы.Заказ)=?) AND (concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=?);", str1, str2.substring(0, str2.lastIndexOf(" ")));
                treeTableView.getSelectionModel().getSelectedItem().getValue().setСборка(event.getNewValue());
            }

        });
        treeTableColumnPanels.setOnEditCommit((event) ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else
            {
                String str1 = treeTableView.getRoot().getValue().getОбозначение();
                String str2 = treeTableView.getSelectionModel().getSelectedItem().getValue().getОбозначение();
                updateSchedule("UPDATE Элементы INNER JOIN (Заказы INNER JOIN График ON Заказы.idЗаказа = График.Родитель) ON Элементы.IDЭлементов = График.Потомок SET График.Панели = \"" + event.getNewValue() + "\"\n"
                        + "WHERE (((Заказы.Заказ)=?) AND (concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=?);", str1, str2.substring(0, str2.lastIndexOf(" ")));
                treeTableView.getSelectionModel().getSelectedItem().getValue().setПанели(event.getNewValue());
            }

        });
        treeTableColumnelEctricians.setOnEditCommit((event) ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else
            {
                String str1 = treeTableView.getRoot().getValue().getОбозначение();
                String str2 = treeTableView.getSelectionModel().getSelectedItem().getValue().getОбозначение();
                updateSchedule("UPDATE Элементы INNER JOIN (Заказы INNER JOIN График ON Заказы.idЗаказа = График.Родитель) ON Элементы.IDЭлементов = График.Потомок SET График.Электрики = \"" + event.getNewValue() + "\"\n"
                        + "WHERE (((Заказы.Заказ)=?) AND (concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=?);", str1, str2.substring(0, str2.lastIndexOf(" ")));
                treeTableView.getSelectionModel().getSelectedItem().getValue().setЭлектрики(event.getNewValue());
            }

        });*/

        buttonPanel.setOnAction((event)
                ->
        {
            type = "Panel";
            idElement = tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_IDЭлементов();
            Parent root = null;
            try
            {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("views/ParametersView.fxml"));
            }
            catch (IOException ex)
            {

            }
            Scene scene = new Scene(root);
            Stage stageBar = new Stage();
            stageBar.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("resources/icon.png")));
            stageBar.setTitle("Введите параметры");
            stageBar.setScene(scene);

            stageBar.showAndWait();
            getCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                    + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                    + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных) Is Not Null) AND ((Элементы.Обозначение)=\"" + labelParent.getText().replaceAll("\"", "\"\"") + "\"));", true);
            tableViewCommonMaterial.setItems(commonMaterialsFilter(dataSpecificationCommonMaterials));
        });

        /*
        buttonPanel.setOnAction((event) ->
        {

            if (!tableViewSpecification.getSelectionModel().isEmpty())
            {
                dialog = new Dialog();
                dialog.setTitle("Введите параметры");
                dialog.setResizable(true);
                // Get the Stage.
                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();

                // Add a custom icon.
                stage.getIcons().add(new Image(this.getClass().getResource("icon.png").toString()));

                Label label1 = new Label("Длина, мм: ");
                Label label2 = new Label("Ширина, мм: ");
                Label label3 = new Label("Количество слоев: ");

                TextField text1 = new TextField();
                TextField text2 = new TextField();
                TextField text3 = new TextField();

                GridPane grid = new GridPane();
                grid.add(label1, 1, 1);
                grid.add(text1, 2, 1);
                grid.add(label2, 1, 2);
                grid.add(text2, 2, 2);
                grid.add(label3, 1, 3);
                grid.add(text3, 2, 3);
                dialog.getDialogPane().setContent(grid);

                ButtonType buttonTypeOk = new ButtonType("Добавить", ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

                Optional result = dialog.showAndWait();
                try
                {
                    double a = Double.parseDouble(text1.getText());
                    double b = Double.parseDouble(text2.getText());
                    double c = Double.parseDouble(text3.getText());
                    double result1 = (a * b * 0.5 * c) / 1000000;

                    result.ifPresent((object) ->
                    {
                        insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа,вспб,вспв, НРМ)\n"
                                + "VALUES(?,414,4," + a + "," + b + "," + c + "," + result1 + ");");
                        insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                + "VALUES(?,333,4," + result1 + "," + result1 / 5 + ");");
                        insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                + "VALUES(?,78,4, " + a + "," + (a / 1000) * 2 + ");");
                        if ((a * b) / 1000000 > 6.25)
                        {
                            insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                    + "VALUES(?,63,4," + 1 + "," + 1 + ");");
                            insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                    + "VALUES(?,415,4," + 0.5 + "," + 0.5 + ");");
                            insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                    + "VALUES(?,358,4," + 2 + "," + (0.3 * 2) + ");");
                        }
                        else
                        {
                            insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                    + "VALUES(?,63,4," + 0.5 + "," + 0.5 + ");");
                            insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                    + "VALUES(?,415,4," + 0.25 + "," + 0.25 + ");");
                            insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                    + "VALUES(?,358,4," + 1 + "," + (0.3 * 1) + ");");
                        }

                        getCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                                + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                                + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных) Is Not Null) AND ((Элементы.Обозначение)=\"" + labelParent.getText().replaceAll("\"", "\"\"") + "\"));", true);
                        tableViewCommonMaterial.setItems(commonMaterialsFilter(dataSpecificationCommonMaterials));
                    });
                }
                catch (java.lang.NumberFormatException exception)
                {

                }

            }
        });*/
        buttonBar.setOnAction((event)
                ->
        {

            type = "Bar";
            idElement = tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_IDЭлементов();
            Parent root = null;
            try
            {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("views/ParametersView.fxml"));
            }
            catch (IOException ex)
            {

            }
            Scene scene = new Scene(root);
            Stage stageBar = new Stage();
            stageBar.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("resources/icon.png")));
            stageBar.setTitle("Введите параметры");
            stageBar.setScene(scene);

            stageBar.showAndWait();

            getCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                    + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                    + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных) Is Not Null) AND ((Элементы.Обозначение)=\"" + labelParent.getText().replaceAll("\"", "\"\"") + "\"));", true);
            tableViewCommonMaterial.setItems(commonMaterialsFilter(dataSpecificationCommonMaterials));

        });
        /*
        buttonBar.setOnAction((event) ->
        {

            if (!tableViewSpecification.getSelectionModel().isEmpty())
            {
                dialog = new Dialog();
                dialog.setTitle("Введите параметры");
                dialog.setResizable(true);
                // Get the Stage.
                try
                {
                    Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("icon.png").toString()));
                }
                catch (NullPointerException ex)
                {

                }

                // Add a custom icon.
                Label label1 = new Label("Длина, мм: ");
                Label label2 = new Label("Ширина, мм: ");
                Label label3 = new Label("Количество слоев: ");

                TextField text1 = new TextField();
                TextField text2 = new TextField();
                TextField text3 = new TextField();

                GridPane grid = new GridPane();
                grid.add(label1, 1, 1);
                grid.add(text1, 2, 1);
                grid.add(label2, 1, 2);
                grid.add(text2, 2, 2);
                grid.add(label3, 1, 3);
                grid.add(text3, 2, 3);
                dialog.getDialogPane().setContent(grid);

                ButtonType buttonTypeOk = new ButtonType("Добавить", ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

                Optional result = dialog.showAndWait();
                try
                {
                    double a = Double.parseDouble(text1.getText());
                    double b = Double.parseDouble(text2.getText());
                    double c = Double.parseDouble(text3.getText());
                    double result1 = (a * b * 0.5 * c) / 1000000;

                    result.ifPresent((object) ->
                    {
                        insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа,вспб,вспв, НРМ)\n"
                                + "VALUES(?,414,4," + a + "," + b + "," + c + "," + result1 + ");");
                        insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента,IDВспомогательного,IDЦехаВ, вспа, НРМ)\n"
                                + "VALUES(?,333,4," + result1 + "," + result1 / 5 + ");");
                        getCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                                + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                                + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных) Is Not Null) AND ((Элементы.Обозначение)=\"" + labelParent.getText().replaceAll("\"", "\"\"") + "\"));", true);
                        tableViewCommonMaterial.setItems(commonMaterialsFilter(dataSpecificationCommonMaterials));
                    });
                }
                catch (java.lang.NumberFormatException exception)
                {

                }

            }
        });*/
// <Order
        comboboxTableElement.setItems(elementsList);

        tableViewOrder.setItems(dataOrders);
        tableViewOrder.setEditable(true);
        tableColumnTableOrder.setEditable(true);

        tableColumnTableOrder.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnTableOrder.setCellValueFactory(new PropertyValueFactory<Orders, String>("Заказы_Заказ"));

        tableColumnTableOrder.setOnEditCommit((event)
                ->
        {

            updateString("UPDATE Заказы SET Заказы.Заказ = ?\n"
                    + "WHERE (((Заказы.IDЗаказа)=" + tableViewOrder.getSelectionModel().getSelectedItem().getЗаказы_idЗаказа() + "));", event.getNewValue());
            updateString("UPDATE Элементы SET Элементы.Обозначение = ?\n"
                    + "WHERE (((Элементы.Обозначение)=" + tableViewOrder.getSelectionModel().getSelectedItem().getЗаказы_idЗаказа() + "));", event.getNewValue());

        });

        buttonUpdateOrders.setOnAction((event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else
            {
                getOrders("SELECT idЗаказа, Заказ FROM заказы;");
            }
        });

        buttonAddOrder.setOnAction((event)
                ->
        {

            if (!comboboxTableElement.getEditor().getText().isEmpty() && !textFieldOrder.getText().isEmpty())
            {
                int id = insertGetId("INSERT INTO Элементы ( Обозначение )\n"
                        + "VALUES(?);");

                insertOrder("INSERT INTO заказы ( Заказ, Узел )\n"
                        + "VALUES(?,?)", id);

                addChildToParent("INSERT INTO Дерево ( Родитель, Обозначение, Поз, Кол )\n"
                        + "VALUES(?,?,1,1)", id, getId("SELECT Элементы.IDЭлементов\n"
                                + "FROM Элементы\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + comboboxTableElement.getEditor().getText() + "\"));", "IDЭлементов"));
                getOrders("SELECT idЗаказа, Заказ FROM заказы;");
            }

        });
        comboboxTableElement.setOnShowing((event)
                ->
        {

            if (!comboboxTableElement.getEditor().getText().isEmpty())
            {

                getStringObservableList("SELECT Элементы.Обозначение\n"
                        + "FROM Элементы\n"
                        + "WHERE (((Элементы.Обозначение) Like \"%" + comboboxTableElement.getEditor().getText().replaceAll("\"", "\"\"") + "%\") AND Элементы.Узел = true)\n"
                        + "ORDER BY Элементы.Обозначение;", elementsList, "Обозначение");

            }
        });
        buttonDeleteOrder.setOnAction((event)
                ->
        {

            if (!tableViewOrder.getSelectionModel().isEmpty())
            {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Подтверждение");
                alert.setHeaderText(null);
                alert.setContentText("Вы действительно хотите удалить заказ?");

                ButtonType buttonTypeYes = new ButtonType("Да");
                ButtonType buttonTypeNo = new ButtonType("Нет");

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes)
                {
                    int id = tableViewOrder.getSelectionModel().getSelectedItem().getЗаказы_idЗаказа();
                    deleteById("DELETE FROM Заказы WHERE idЗаказа = ?", id);
                    deleteById("DELETE from график where Родитель=?;", id);
                    getOrders("SELECT idЗаказа, Заказ FROM заказы;");
                }
            }
        });

// Order>
// <Schedule
/*
        comboboxSchedule.setOnAction((event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else if (comboboxSchedule.getSelectionModel().getSelectedItem() != null)
            {
                stringComboboxSchedule = comboboxSchedule.getSelectionModel().getSelectedItem().toString();
                if (!comboboxSchedule.getSelectionModel().isEmpty())
                {
                    getIDУзла("SELECT Элементы_1.IDЭлементов, заказы.idЗаказа, график.Выписано\n"
                            + "FROM заказы INNER JOIN (((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов) LEFT JOIN График ON Элементы_1.IDЭлементов = График.Потомок) ON заказы.Узел = Элементы.IDЭлементов\n"
                            + "WHERE (((заказы.Заказ)=\"" + stringComboboxSchedule + "\"))\n"
                            + "ORDER BY Дерево.Поз;");

                    setSchedule("INSERT INTO График ( Родитель, Потомок, Ответственный, Выписано, Примечание, Заготовка, Сборка, Панели, Электрики )\n"
                            + "VALUES(?,?,\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\");", schedule.get(0), schedule.get(1));

                    ObservableList<ScheduleFull> dataSchedule = FXCollections.observableArrayList();
                    TreeItem<ScheduleFull> root = new TreeItem<>(new ScheduleFull(stringComboboxSchedule, "", "", "", "", "", "", ""));

                    dataSchedule = getSchedule("SELECT график.Родитель, заказы.idЗаказа, Элементы_1.IDЭлементов, concat_ws(\" \",Элементы_1.Обозначение , Элементы_1.Наименование,\" \") AS Обозначение, Дерево.Кол, Элементы_1.ЕИЭ, График.Ответственный, График.Выписано, График.Примечание, График.Заготовка, График.Сборка, График.Панели, График.Электрики\n"
                            + "FROM заказы INNER JOIN (((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов) LEFT JOIN График ON Элементы_1.IDЭлементов = График.Потомок) ON заказы.Узел = Элементы.IDЭлементов\n"
                            + "WHERE (((заказы.Заказ)=\"" + stringComboboxSchedule + "\") AND (график.Родитель = заказы.idЗаказа))\n"
                            + "ORDER BY Дерево.Поз;");
                    dataSchedule.stream().forEach((schedule)
                            ->
                    {
                        root.getChildren().add(new TreeItem<>(schedule));
                    });

                    root.setExpanded(true);
                    fillTreeSchedule(root);

                    treeTableView.setRoot(root);
                    treeTableView.getColumns().setAll(treeTableColumnElement, treeTableColumnResponsible, treeTableColumnWritten, treeTableColumnNote, treeTableColumnHarvesting, treeTableColumnAssembly, treeTableColumnPanels, treeTableColumnelEctricians);
                }
            }
        });*/
        comboboxSchedule.setOnAction((event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else if (comboboxSchedule.getSelectionModel().getSelectedItem() != null)
            {
                stringComboboxSchedule = comboboxSchedule.getSelectionModel().getSelectedItem().toString();
                if (!comboboxSchedule.getSelectionModel().isEmpty())
                {
                    getIDУзла("SELECT Элементы_1.IDЭлементов, заказы.idЗаказа, график.Выписано\n"
                            + "FROM заказы INNER JOIN (((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов) LEFT JOIN График ON Элементы_1.IDЭлементов = График.Потомок) ON заказы.Узел = Элементы.IDЭлементов\n"
                            + "WHERE (((заказы.Заказ)=\"" + stringComboboxSchedule + "\"))\n"
                            + "ORDER BY Дерево.Поз;");

                    setSchedule("INSERT INTO График ( Родитель, Потомок, Ответственный, Выписано, Примечание)\n"
                            + "VALUES(?,?,\"-\",\"-\",\"-\");", schedule.get(0), schedule.get(1));

                    ObservableList<Schedule> dataSchedule = FXCollections.observableArrayList();
                    TreeItem<Schedule> root = new TreeItem<>(new Schedule(stringComboboxSchedule, "", "", ""));

                    dataSchedule = getSchedule("SELECT график.Родитель, заказы.idЗаказа, Элементы_1.IDЭлементов, concat_ws(\" \",Элементы_1.Обозначение , Элементы_1.Наименование,\" \") AS Обозначение, Дерево.Кол, Элементы_1.ЕИЭ, График.Ответственный, График.Выписано, График.Примечание\n"
                            + "FROM заказы INNER JOIN (((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов) LEFT JOIN График ON Элементы_1.IDЭлементов = График.Потомок) ON заказы.Узел = Элементы.IDЭлементов\n"
                            + "WHERE (((заказы.Заказ)=\"" + stringComboboxSchedule + "\") AND (график.Родитель = заказы.idЗаказа))\n"
                            + "ORDER BY Дерево.Поз;");
                    dataSchedule.stream().forEach((schedule)
                            ->
                    {
                        root.getChildren().add(new TreeItem<>(schedule));
                    });

                    root.setExpanded(true);
                    fillTreeSchedule(root);

                    treeTableView.setRoot(root);
                    treeTableView.getColumns().setAll(treeTableColumnElement, treeTableColumnResponsible, treeTableColumnWritten, treeTableColumnNote);
                }
            }
        });
        comboboxSchedule.setOnShowing(
                (event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else
            {
                getStringObservableList("SELECT Заказ FROM заказы;", orderList, "Заказ");
            }

        }
        );
        comboboxOrder.setOnShowing(
                (event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else
            {
                getStringObservableList("SELECT Заказ FROM заказы;", orderList, "Заказ");
            }

        }
        );
        comboboxOrder.setOnHiding(
                (event)
                ->
        {
            String str = labelParent.getText().replaceAll("\"", "\"\"");
            try
            {
                getElemnts("SELECT Элементы_1.IDЭлементов, Дерево.Поз, Элементы_1.Обозначение, Элементы_1.Наименование, Элементы_1.примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы.Обозначение, Элементы_1.а, Элементы_1.б, Элементы_1.в, Элементы_1.г, Элементы_1.д, Элементы_1.е, Элементы_1.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы_1.КоэффициентЭ, Материалы.КоэффициентМ, Элементы_1.КТДЭ, Элементы_1.ЕИЭ, Материалы.КТДМ, Элементы_1.Узел\n"
                        + "FROM (Цех RIGHT JOIN ((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) LEFT JOIN ((Элементы AS Элементы_1 LEFT JOIN Материалы ON Элементы_1.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) ON Дерево.Обозначение = Элементы_1.IDЭлементов) ON Цех.IDЦехов = Дерево.IDЦехаЭ) LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов\n"
                        + "WHERE (((Элементы.Обозначение)=\"" + str + "\"))\n"
                        + "ORDER BY Дерево.Поз;");
            }
            catch (NullPointerException ex)
            {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            getWriten("SELECT график.Выписано\n"
                    + "FROM (((элементы LEFT JOIN дерево ON элементы.IDЭлементов = дерево.Родитель) LEFT JOIN элементы AS элементы_1 ON дерево.Обозначение = элементы_1.IDЭлементов) LEFT JOIN график ON элементы_1.IDЭлементов = график.Потомок) LEFT JOIN заказы ON график.Родитель = заказы.idЗаказа\n"
                    + "WHERE (((элементы.Обозначение)=\"" + str + "\") AND ((заказы.Заказ)=\"" + comboboxOrder.getValue() + "\")) OR (((элементы.Обозначение)=\"" + str + "\") AND ((заказы.Заказ) Is Null))\n"
                    + "ORDER BY дерево.Поз;");

            getCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                    + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                    + "WHERE (((Элементы.Обозначение)=\"" + str + "\"));", true);
        }
        );
        menuItemSaveGraph.setOnAction(
                (event)
                ->
        {

            try
            {
                getGraph();
            }
            catch (IOException ex)
            {

            }
            catch (InvalidFormatException ex)
            {

            }

            /*
                File file = new File(saveXls + File.separator + comboboxSchedule.getSelectionModel().getSelectedItem() + ".txt");
                
                try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "windows-1251")))
                {
                for (int i = 0; i < treeTableView.getExpandedItemCount(); i++)
                {
                bw.write(treeTableView.getColumns().get(0).getCellData(i) + " " + treeTableView.getColumns().get(1).getCellData(i) + "\r\n");
                }
                
                }
                catch (IOException ex)
                {
                System.out.println("IOException1");
                }
                Desktop desktop = Desktop.getDesktop();
                if (file.exists())
                {
                try
                {
                desktop.open(file);
                }
                catch (IOException ex)
                {
                System.out.println("IOException");
                }
                }
             */
        });
// Schedule>

        checkBoxNode.setOnAction(
                (event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0 && tableViewSpecification.getSelectionModel().getSelectedIndex() != -1)
            {
                specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                updateNode("UPDATE Элементы SET Элементы.Узел = ?\n"
                        + "WHERE (((Элементы.IDЭлементов)=" + specification.getЭлементы_1_IDЭлементов() + "));", checkBoxNode.selectedProperty().get());
                specification.setЭлементы_1_Узел(checkBoxNode.selectedProperty().get());
            }
        }
        );

        menuItemCopy.setOnAction(
                (event)
                ->
        {
            copySelectionToClipboard(tableViewSpecification);
        }
        );
        tableViewSpecification.setOnKeyPressed(
                (event)
                ->
        {
            if (keyCodeCopy.match(event))
            {
                copySelectionToClipboard(tableViewSpecification);
            }
        }
        );
        textFieldСoefficientM.setOnAction(
                (event)
                ->
        {

            if (tableViewSpecification.getSelectionModel().getSelectedIndex() != -1)
            {
                if (!DBConnection.connect())
                {
                    bdNotConnected();
                }
                else
                {
                    specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                    coefficient = textFieldСoefficientM.getText().replace(",", ".");
                    labelMaterialFormula.setText(specification.getФормулы_ФормулаРасчета() + "*" + coefficient + "*" + specification.getЭлементы_1_КоэффициентЭ());
                    try
                    {
                        updateDouble("UPDATE Материалы SET Материалы.КоэффициентМ = ?\n"
                                + "WHERE (((Материалы.IDМатериалов)=" + specification.getМатериалы_IDМатериалов() + "));", Double.parseDouble(coefficient));
                        specification.setМатериалы_КоэффициентМ(coefficient);
                    }
                    catch (NumberFormatException ex)
                    {
                        updateDouble("UPDATE Материалы SET Материалы.КоэффициентМ = ?\n"
                                + "WHERE (((Материалы.IDМатериалов)=" + specification.getМатериалы_IDМатериалов() + "));", 0);
                        specification.setМатериалы_КоэффициентМ("0");
                    }
                }

            }
        }
        );
        textFieldСoefficientE.setOnAction(
                (event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else if (tableViewSpecification.getSelectionModel().getSelectedIndex() != -1)
            {
                specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                coefficient = textFieldСoefficientE.getText().replace(",", ".");
                labelMaterialFormula.setText(specification.getФормулы_ФормулаРасчета() + "*" + specification.getМатериалы_КоэффициентМ() + "*" + coefficient);
                try
                {
                    updateDouble("UPDATE Элементы SET Элементы.КоэффициентЭ = ?\n"
                            + "WHERE (((Элементы.IDЭлементов)=" + specification.getЭлементы_1_IDЭлементов() + "));", Double.parseDouble(coefficient));
                    specification.setЭлементы_1_КоэффициентЭ(coefficient);
                }
                catch (NumberFormatException ex)
                {
                    updateDouble("UPDATE Элементы SET Элементы.КоэффициентЭ = ?\n"
                            + "WHERE (((Элементы.IDЭлементов)=" + specification.getЭлементы_1_IDЭлементов() + "));", 0);
                    specification.setЭлементы_1_КоэффициентЭ("0");
                }

            }
        }
        );

        textFieldСoefficientC.setOnAction(
                (event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else if (tableViewCommonMaterial.getSelectionModel().getSelectedIndex() != -1)
            {
                commonMaterial = tableViewCommonMaterial.getSelectionModel().getSelectedItem();
                coefficient = textFieldСoefficientC.getText().replace(",", ".");
                labelCommonFormula.setText(commonMaterial.getВ_Формулы_ФормулаРасчета() + "*" + coefficient + "*" + commonMaterial.getВ_ЭлементыВспомогательные_КоэфВЭ());
                try
                {
                    updateDouble("UPDATE Вспомогательные SET Вспомогательные.КоэффициентВМ = ?\n"
                            + "WHERE (((Вспомогательные.IDВспомогательных)=" + commonMaterial.getВ_Вспомогательные_IDВспомогательных() + "));", Double.parseDouble(coefficient));
                    commonMaterial.setВ_Вспомогательные_КоэффициентВМ(coefficient);
                }
                catch (NumberFormatException ex)
                {
                    updateDouble("UPDATE Вспомогательные SET Вспомогательные.КоэффициентВМ = ?\n"
                            + "WHERE (((Вспомогательные.IDВспомогательных)=" + commonMaterial.getВ_Вспомогательные_IDВспомогательных() + "));", 0);
                    commonMaterial.setВ_Вспомогательные_КоэффициентВМ("0");
                }

            }
        }
        );
        textFieldСoefficientCE.setOnAction(
                (event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else if (tableViewCommonMaterial.getSelectionModel().getSelectedIndex() != -1)
            {
                commonMaterial = tableViewCommonMaterial.getSelectionModel().getSelectedItem();
                coefficient = textFieldСoefficientCE.getText().replace(",", ".");
                labelCommonFormula.setText(commonMaterial.getВ_Формулы_ФормулаРасчета() + "*" + commonMaterial.getВ_Вспомогательные_КоэффициентВМ() + "*" + coefficient);
                try
                {
                    updateDouble("UPDATE ЭлементыВспомогательные SET ЭлементыВспомогательные.КоэфВЭ = ?\n"
                            + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных)=" + commonMaterial.getВ_ЭлементыВспомогательные_IDЭлементовВспомогательных() + "));", Double.parseDouble(coefficient));
                    commonMaterial.setВ_ЭлементыВспомогательные_КоэфВЭ(coefficient);

                }
                catch (NumberFormatException ex)
                {
                    updateDouble("UPDATE ЭлементыВспомогательные SET ЭлементыВспомогательные.КоэфВЭ = ?\n"
                            + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных)=" + commonMaterial.getВ_ЭлементыВспомогательные_IDЭлементовВспомогательных() + "));", 0);
                    commonMaterial.setВ_ЭлементыВспомогательные_КоэфВЭ("0");
                }

            }
        }
        );

        textFieldKTDM.setOnAction(
                (event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else if (tableViewSpecification.getSelectionModel().getSelectedIndex() != -1)
            {
                specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                ktd = textFieldKTDM.getText();
                try
                {
                    updateInteger("UPDATE Материалы SET Материалы.КТДМ = ?\n"
                            + "WHERE (((Материалы.IDМатериалов)=" + specification.getМатериалы_IDМатериалов() + "));", Integer.parseInt(ktd));
                    specification.setМатериалы_КТДМ(ktd);
                }
                catch (NumberFormatException ex)
                {
                    updateInteger("UPDATE Материалы SET Материалы.КТДМ = ?\n"
                            + "WHERE (((Материалы.IDМатериалов)=" + specification.getМатериалы_IDМатериалов() + "));", 0);
                    specification.setМатериалы_КТДМ("0");
                }

            }
        }
        );

        textFieldKTDC.setOnAction(
                (event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else if (tableViewCommonMaterial.getSelectionModel().getSelectedIndex() != -1)
            {
                commonMaterial = (SpecificationCommonMaterials) tableViewCommonMaterial.getSelectionModel().getSelectedItem();
                ktd = textFieldKTDC.getText();
                try
                {
                    updateInteger("UPDATE Вспомогательные SET Вспомогательные.КТДВ = ?\n"
                            + "WHERE (((Вспомогательные.IDВспомогательных)=" + commonMaterial.getВ_Вспомогательные_IDВспомогательных() + "));", Integer.parseInt(ktd));
                }
                catch (NumberFormatException ex)
                {
                    updateInteger("UPDATE Вспомогательные SET Вспомогательные.КТДВ = ?\n"
                            + "WHERE (((Вспомогательные.IDВспомогательных)=" + commonMaterial.getВ_Вспомогательные_IDВспомогательных() + "));", 0);
                }
                commonMaterial.setВ_Вспомогательные_КТДВ(ktd);
            }

        }
        );

        tableViewFormulas.setOnMousePressed(
                (event)
                ->
        {
            if (tableViewFormulas.getSelectionModel().getSelectedIndex() != -1)
            {
                textFieldFormula.setText(((Formulas) dataFormulas.get(tableViewFormulas.getSelectionModel().getSelectedIndex())).getФормулы_Формула());
                textFieldCalculation.setText(((Formulas) dataFormulas.get(tableViewFormulas.getSelectionModel().getSelectedIndex())).getФормулы_ФормулаРасчета());
                textFieldA.setText(((Formulas) dataFormulas.get(tableViewFormulas.getSelectionModel().getSelectedIndex())).getФормулы_а());
                textFieldB.setText(((Formulas) dataFormulas.get(tableViewFormulas.getSelectionModel().getSelectedIndex())).getФормулы_б());
                textFieldC.setText(((Formulas) dataFormulas.get(tableViewFormulas.getSelectionModel().getSelectedIndex())).getФормулы_в());
                textFieldD.setText(((Formulas) dataFormulas.get(tableViewFormulas.getSelectionModel().getSelectedIndex())).getФормулы_г());
                textFieldE.setText(((Formulas) dataFormulas.get(tableViewFormulas.getSelectionModel().getSelectedIndex())).getФормулы_д());
                textFieldF.setText(((Formulas) dataFormulas.get(tableViewFormulas.getSelectionModel().getSelectedIndex())).getФормулы_е());
            }
        }
        );

        comboboxTableCommonFormula.setOnShowing(
                (event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else
            {
                getFormulas("SELECT Формулы.IDФормул, Формулы.Формула FROM Формулы ORDER BY Формулы.Формула", comboboxTableCommonFormula);
            }
        }
        );

        comboboxTableMaterialFormulа.setOnShowing(
                (event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else
            {
                getFormulas("SELECT Формулы.IDФормул, Формулы.Формула FROM Формулы ORDER BY Формулы.Формула", comboboxTableMaterialFormulа);
            }
        }
        );
        comboboxTableMaterialFormulа.setOnMousePressed(
                (event)
                ->
        {
            System.out.println(comboboxTableMaterialFormulа.getSelectionModel().getSelectedIndex());
        }
        );

        buttonDeleteCommonRow.setOnMousePressed(
                (event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else if (tableViewCommonMaterial.getSelectionModel().getSelectedIndex() != -1)
            {
                deleteById("DELETE FROM ЭлементыВспомогательные WHERE IDЭлементовВспомогательных = ?", tableViewCommonMaterial.getSelectionModel().getSelectedItem().getВ_ЭлементыВспомогательные_IDЭлементовВспомогательных());
                dataSpecificationCommonMaterials.remove(tableViewCommonMaterial.getSelectionModel().getSelectedItem());
                tableViewCommonMaterial.setItems(commonMaterialsFilter(dataSpecificationCommonMaterials));
            }

        }
        );

        buttonAddCommonRow.setOnMousePressed(
                (event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else if (!tableViewSpecification.getSelectionModel().isEmpty())
            {
                insertCommonElementToDb("INSERT INTO ЭлементыВспомогательные ( IDЭлемента )\n"
                        + "VALUES(?);");
                getCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                        + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                        + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных) Is Not Null) AND ((Элементы.Обозначение)=\"" + labelParent.getText().replaceAll("\"", "\"\"") + "\"));", true);
                tableViewCommonMaterial.setItems(commonMaterialsFilter(dataSpecificationCommonMaterials));
            }
        }
        );

        comboboxElement.setOnKeyPressed(
                (event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else if (event.getCode().equals(KeyCode.ENTER))
            {
                setElementRow();
            }
        }
        );
        comboboxTree.setOnKeyPressed(
                (event)
                ->
        {
            if (event.getCode().equals(KeyCode.ENTER))
            {
                if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0)
                {
                    setElementRow();
                }
                else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
                {
                    setElementRow2();
                }
            }
        }
        );

        menuItemExit.setOnAction(
                (event)
                ->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение");
            alert.setHeaderText(null);
            alert.setContentText("Вы действительно хотите выйти?");
            alert.initStyle(StageStyle.UTILITY);
            alert.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("resources/icon.png").toString()));

            ButtonType buttonTypeYes = new ButtonType("Да");
            ButtonType buttonTypeNo = new ButtonType("Нет");
            ButtonType buttonTypeClose = new ButtonType("Закрыть", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeClose);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes)
            {
                System.exit(0);
            }
        }
        );
        comboBoxReplace.setOnShowing(
                (event)
                ->
        {

            if ("Обозначение".equals(titleName))
            {
                findeElementsReplace("SELECT DISTINCT Элементы.IDЭлементов ,Элементы.Обозначение\n"
                        + "FROM Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Обозначение\n"
                        + "WHERE (((Элементы.Обозначение) Like \"%" + comboBoxReplace.getEditor().getText().replaceAll("\"", "\"\"") + "%\"))\n"
                        + "ORDER BY Элементы.Обозначение;", elementsListReplace);
            }
            else if ("Материал".equals(titleName))
            {

            }
        }
        );
        comboBoxReplaced.setOnShowing(
                (event)
                ->
        {

            if ("Обозначение".equals(titleName))
            {
                findeElementsReplaced("SELECT Элементы.IDЭлементов, Элементы.Обозначение\n"
                        + "FROM Элементы\n"
                        + "WHERE (((Элементы.Обозначение) Like \"%" + comboBoxReplaced.getEditor().getText().replaceAll("\"", "\"\"") + "%\"))\n"
                        + "ORDER BY Элементы.Обозначение;", elementsListReplaced);
            }
            else if ("Материал".equals(titleName))
            {

                findeElementsReplaced("SELECT Материалы.IDМатериалов, Материалы.Материал\n"
                        + "FROM Материалы\n"
                        + "WHERE (((Материалы.Материал) Like \"%" + comboBoxReplaced.getEditor().getText().replaceAll("\"", "\"\"") + "%\"))\n"
                        + "ORDER BY Материалы.Материал;", elementsListReplaced);
            }
        }
        );

        menuItemReplaceElement.setOnAction(
                (event)
                ->
        {
            titleName = "Обозначение";
            replaceDialog("Замена обозначения", "Старое обозначение:", "Новое обозначени:");
        }
        );
        menuItemReplaceMaterial.setOnAction(
                (event)
                ->
        {
            titleName = "Материал";
            replaceDialog("Замена материала", "Старый материал:", "Новый материал:");
        }
        );
        menuItemReplaceCommon.setOnAction(
                (event)
                ->
        {
            titleName = "Вспомогательный";
            replaceDialog("Замена вспомогательного", "Старый вспомогательный:", "Новый вспомогательный:");
        }
        );
        menuItemReplaceFormula.setOnAction(
                (event)
                ->
        {
            titleName = "Формула";
            replaceDialog("Замена формулы", "Старая формула:", "Новая формула:");
        }
        );

        buttonSearchElement.setOnAction(
                (event)
                ->
        {
            showParents("SELECT Элементы_1.IDЭлементов, concat_ws(\" \",Элементы_1.Обозначение, Элементы_1.Наименование) AS Обозначение, Дерево.Кол\n"
                    + "FROM (Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Обозначение) INNER JOIN Элементы AS Элементы_1 ON Дерево.Родитель = Элементы_1.IDЭлементов\n"
                    + "WHERE (((Элементы.Обозначение)=\"" + comboboxSearchElement.getEditor().getText().replaceAll("\"", "\"\"") + "\"));");
        }
        );
        comboboxSearchElement.setOnShowing(
                (event)
                ->
        {
            getStringObservableList("SELECT Элементы.Обозначение\n"
                    + "FROM Элементы\n"
                    + "WHERE (((Элементы.Обозначение) Like \"%" + comboboxSearchElement.getEditor().getText().replaceAll("\"", "\"\"") + "%\"))\n"
                    + "ORDER BY Элементы.Обозначение;", elementsList, "Обозначение");
        }
        );

        buttonChangeElement.setOnAction(
                (event)
                ->
        {
            if (!tableViewElements.getSelectionModel().isEmpty())
            {
                int id = tableViewElements.getSelectionModel().getSelectedItem().getЭлементы_IDЭлементов();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Подтверждение");
                alert.setHeaderText(null);
                alert.setContentText("Вы действительно хотите изменить?");

                ButtonType buttonTypeYes = new ButtonType("Да");
                ButtonType buttonTypeNo = new ButtonType("Нет");

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes)
                {
                    updateElement("UPDATE Элементы SET Элементы.КТДЭ =?, Элементы.Обозначение = ?, Элементы.Наименование = ?, Элементы.Примечание = ?\n"
                            + "WHERE (((Элементы.IDЭлементов)=" + tableViewElements.getSelectionModel().getSelectedItem().getЭлементы_IDЭлементов() + "));");
                    showElements("SELECT Элементы.IDЭлементов, Элементы.КТДЭ, Элементы.Обозначение, Элементы.Наименование, Элементы.примечание\n"
                            + "FROM Элементы\n"
                            + "WHERE (((Элементы.Обозначение) Like \"%" + textFieldElementsFiltered.getText().replaceAll("\"", "\"\"") + "%\"))\n"
                            + "ORDER BY Элементы.Обозначение;");
                }
            }
        }
        );
        buttonChangeMaterial.setOnAction(
                (event)
                ->
        {
            if (!tableViewMaterials.getSelectionModel().isEmpty())
            {
                int id = tableViewMaterials.getSelectionModel().getSelectedItem().getМатериалы_IDМатериалов();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Подтверждение");
                alert.setHeaderText(null);
                alert.setContentText("Вы действительно хотите изменить?");

                ButtonType buttonTypeYes = new ButtonType("Да");
                ButtonType buttonTypeNo = new ButtonType("Нет");

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes)
                {
                    updateMaterialFormula("UPDATE Материалы SET Материалы.КТДМ = ?, Материалы.Материал = ?, Материалы.ЕИМ = ?, Материалы.IDФормулы = ?, Материалы.КоэффициентМ = ?\n"
                            + "WHERE (((Материалы.IDМатериалов)=" + tableViewMaterials.getSelectionModel().getSelectedItem().getМатериалы_IDМатериалов() + "));");
                    showMaterials("SELECT Материалы.IDМатериалов, Материалы.КТДМ, Материалы.Материал, Материалы.ЕИМ, Формулы.Формула, Материалы.КоэффициентМ\n"
                            + "FROM Материалы LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул\n"
                            + "WHERE (((Материалы.Материал) Like \"%" + textFieldMaterial.getText().replaceAll("\"", "\"\"") + "%\"))\n"
                            + "ORDER BY Материалы.Материал;");
                }
            }
        }
        );
        buttonChangeCommon.setOnAction(
                (event)
                ->
        {
            if (!tableViewCommonMaterials.getSelectionModel().isEmpty())
            {
                int id = tableViewCommonMaterials.getSelectionModel().getSelectedItem().getВспомогательные_IDВспомогательных();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Подтверждение");
                alert.setHeaderText(null);
                alert.setContentText("Вы действительно хотите изменить?");

                ButtonType buttonTypeYes = new ButtonType("Да");
                ButtonType buttonTypeNo = new ButtonType("Нет");

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes)
                {
                    updateCommonMaterialFormula("UPDATE Вспомогательные SET Вспомогательные.КТДВ = ?, Вспомогательные.Вспомогательный = ?, Вспомогательные.ЕИВ = ?, Вспомогательные.Формула = ?\n"
                            + "WHERE (((Вспомогательные.IDВспомогательных)=" + tableViewCommonMaterials.getSelectionModel().getSelectedItem().getВспомогательные_IDВспомогательных() + "));");
                    showCommonMaterials("SELECT Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ, Вспомогательные.Вспомогательный, Вспомогательные.ЕИВ, Формулы.Формула\n"
                            + "FROM Вспомогательные LEFT JOIN Формулы ON Вспомогательные.Формула = Формулы.IDФормул\n"
                            + "WHERE (((Вспомогательные.Вспомогательный) Like \"%" + textFieldCommonMaterial.getText().replaceAll("\"", "\"\"") + "%\"))\n"
                            + "ORDER BY Вспомогательные.Вспомогательный;");
                }
            }
        }
        );
        buttonFormulaChange.setOnAction(
                (event)
                ->
        {
            if (!tableViewFormulas.getSelectionModel().isEmpty())
            {
                int id = tableViewFormulas.getSelectionModel().getSelectedItem().getФормулы_IDФормул();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Подтверждение");
                alert.setHeaderText(null);
                alert.setContentText("Вы действительно хотите изменить?");

                ButtonType buttonTypeYes = new ButtonType("Да");
                ButtonType buttonTypeNo = new ButtonType("Нет");

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes)
                {
                    updateFormula("UPDATE Формулы SET Формулы.Формула = ?, Формулы.ФормулаРасчета = ?, Формулы.а = ?, Формулы.б = ?, Формулы.в = ?, Формулы.г = ?, Формулы.д = ?, Формулы.е = ?\n"
                            + "WHERE (((Формулы.IDФормул)=" + tableViewFormulas.getSelectionModel().getSelectedItem().getФормулы_IDФормул() + "))");
                    showFormulas("SELECT Формулы.IDФормул, Формулы.Формула, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е\n"
                            + "FROM Формулы\n"
                            + "ORDER BY Формулы.Формула;");
                }
            }
        }
        );

        buttonAddElement.setOnAction(
                (event)
                ->
        {/*
            if (!tableViewElements.getSelectionModel().isEmpty())
            {*/
            insertElementToDb("INSERT INTO Элементы ( Обозначение, Наименование, Примечание )\n"
                    + "VALUES(?, ?, ?);");
            showElements("SELECT Элементы.IDЭлементов, Элементы.КТДЭ, Элементы.Обозначение, Элементы.Наименование, Элементы.примечание\n"
                    + "FROM Элементы\n"
                    + "WHERE (((Элементы.Обозначение) Like \"%" + textFieldTableElement.getText().replaceAll("\"", "\"\"") + "%\"))\n"
                    + "ORDER BY Элементы.Обозначение;");
            /*}*/
        }
        );
        buttonAddMaterial.setOnAction(
                (event)
                ->
        {/*
            if (!tableViewMaterials.getSelectionModel().isEmpty())
            {*/
            insertMaterialToDb("INSERT INTO Материалы ( КТДМ, Материал, ЕИМ, IDФормулы, КоэффициентМ )\n"
                    + "VALUES(?, ?, ?, ?, ?);");
            showMaterials("SELECT Материалы.IDМатериалов, Материалы.КТДМ, Материалы.Материал, Материалы.ЕИМ, Формулы.Формула, Материалы.КоэффициентМ\n"
                    + "FROM Материалы LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул\n"
                    + "WHERE (((Материалы.Материал) Like \"%" + comboboxTableMateral.getEditor().getText().replaceAll("\"", "\"\"") + "%\"))\n"
                    + "ORDER BY Материалы.Материал;");
            /*  }*/
        }
        );
        buttonAddCommon.setOnAction(
                (event)
                ->
        {/*
            if (!tableViewCommonMaterials.getSelectionModel().isEmpty())
            {*/
            insertCommonMaterialToDb("INSERT INTO Вспомогательные ( Вспомогательный, ЕИВ, Формула, КТДВ)\n"
                    + "VALUES(?, ?, ?, ?);");
            showCommonMaterials("SELECT Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ, Вспомогательные.Вспомогательный, Вспомогательные.ЕИВ, Формулы.Формула\n"
                    + "FROM Вспомогательные LEFT JOIN Формулы ON Вспомогательные.Формула = Формулы.IDФормул\n"
                    + "WHERE (((Вспомогательные.Вспомогательный) Like \"%" + comboboxTableCommon.getEditor().getText().replaceAll("\"", "\"\"") + "%\"))\n"
                    + "ORDER BY Вспомогательные.Вспомогательный;");
            /*}*/
        }
        );
        buttonAddFormula.setOnAction(
                (event)
                ->
        {
            if (!tableViewFormulas.getSelectionModel().isEmpty())
            {
                insertFormulaToDb("INSERT INTO Формулы ( Формула, ФормулаРасчета, а, б, в, г, д, е )\n"
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
                showFormulas("SELECT Формулы.IDФормул, Формулы.Формула, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е\n"
                        + "FROM Формулы\n"
                        + "ORDER BY Формулы.Формула;");
            }
        }
        );

        buttonDeleteElement.setOnAction(
                (event)
                ->
        {
            if (!tableViewElements.getSelectionModel().isEmpty())
            {
                int id = tableViewElements.getSelectionModel().getSelectedItem().getЭлементы_IDЭлементов();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Подтверждение");
                alert.setHeaderText(null);
                alert.setContentText("Вы действительно хотите удалить?");

                ButtonType buttonTypeYes = new ButtonType("Да");
                ButtonType buttonTypeNo = new ButtonType("Нет");

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes & !checkElementInTree("SELECT Дерево.Обозначение\n"
                        + "FROM Дерево\n"
                        + "WHERE (((Дерево.Обозначение)=" + id + "));") & !checkElementInTree("SELECT Дерево.Родитель\n"
                                + "FROM Дерево\n"
                                + "WHERE (((Дерево.Родитель)=" + id + "));"))
                {

                    deleteFromTable("DELETE FROM Элементы WHERE IDЭлементов = ?", id);
                    dataElements.remove(tableViewElements.getSelectionModel().getSelectedIndex());
                }
                else if (result.get() == buttonTypeYes)
                {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Предупреждение");
                    alert.setHeaderText(null);
                    alert.setContentText("Используется");
                    alert.showAndWait();
                }
            }
        }
        );
        buttonDeleteMaterial.setOnAction(
                (event)
                ->
        {
            if (!tableViewMaterials.getSelectionModel().isEmpty())
            {
                int id = tableViewMaterials.getSelectionModel().getSelectedItem().getМатериалы_IDМатериалов();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Подтверждение");
                alert.setHeaderText(null);
                alert.setContentText("Вы действительно хотите удалить?");

                ButtonType buttonTypeYes = new ButtonType("Да");
                ButtonType buttonTypeNo = new ButtonType("Нет");

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes & !checkElementInTree("SELECT Элементы.IDМатериала\n"
                        + "FROM Элементы\n"
                        + "WHERE (((Элементы.IDМатериала)=" + id + "));"))
                {
                    deleteFromTable("DELETE FROM Материалы WHERE IDМатериалов = ?", id);
                    dataMaterials.remove(tableViewMaterials.getSelectionModel().getSelectedIndex());

                }
                else if (result.get() == buttonTypeYes)
                {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Предупреждение");
                    alert.setHeaderText(null);
                    alert.setContentText("Используется");
                    alert.showAndWait();
                }
            }
        }
        );

        buttonDeleteCommon.setOnAction(
                (event)
                ->
        {
            if (!tableViewCommonMaterials.getSelectionModel().isEmpty())
            {
                int id = tableViewCommonMaterials.getSelectionModel().getSelectedItem().getВспомогательные_IDВспомогательных();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Подтверждение");
                alert.setHeaderText(null);
                alert.setContentText("Вы действительно хотите удалить?");

                ButtonType buttonTypeYes = new ButtonType("Да");
                ButtonType buttonTypeNo = new ButtonType("Нет");

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes & !checkElementInTree("SELECT ЭлементыВспомогательные.IDВспомогательного\n"
                        + "FROM ЭлементыВспомогательные\n"
                        + "WHERE (((ЭлементыВспомогательные.IDВспомогательного)=" + id + "));"))
                {
                    deleteFromTable("DELETE FROM Вспомогательные WHERE IDВспомогательных = ?", id);
                    dataCommonMaterials.remove(tableViewCommonMaterials.getSelectionModel().getSelectedIndex());

                }
                else if (result.get() == buttonTypeYes)
                {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Предупреждение");
                    alert.setHeaderText(null);
                    alert.setContentText("Используется");
                    alert.showAndWait();
                }
            }

        }
        );
        buttonFormulaDelete.setOnAction(
                (event)
                ->
        {
            if (!tableViewFormulas.getSelectionModel().isEmpty())
            {
                int id = tableViewFormulas.getSelectionModel().getSelectedItem().getФормулы_IDФормул();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Подтверждение");
                alert.setHeaderText(null);
                alert.setContentText("Вы действительно хотите удалить?");

                ButtonType buttonTypeYes = new ButtonType("Да");
                ButtonType buttonTypeNo = new ButtonType("Нет");

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes & !checkElementInTree("SELECT Материалы.IDФормулы\n"
                        + "FROM Материалы\n"
                        + "WHERE (((Материалы.IDФормулы)=" + id + "));") & !checkElementInTree("SELECT Вспомогательные.Формула\n"
                                + "FROM Вспомогательные\n"
                                + "WHERE (((Вспомогательные.Формула)=" + id + "));"))
                {
                    deleteFromTable("DELETE FROM Вспомогательные WHERE IDВспомогательных = ?", id);
                    dataFormulas.remove(tableViewFormulas.getSelectionModel().getSelectedIndex());

                }
                else if (result.get() == buttonTypeYes)
                {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Предупреждение");
                    alert.setHeaderText(null);
                    alert.setContentText("Используется");
                    alert.showAndWait();
                }
            }

        }
        );
        buttonFormulaUpdate.setOnAction(
                (event)
                ->
        {
            showFormulas("SELECT Формулы.IDФормул, Формулы.Формула, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е\n"
                    + "FROM Формулы\n"
                    + "ORDER BY Формулы.Формула;");
        }
        );

        tableViewElements.setOnMousePressed(
                (event)
                ->
        {
            if (tableViewElements.getSelectionModel().getSelectedIndex() != -1)
            {
                textFieldTableKTD.setText(((Elements) dataElements.get(tableViewElements.getSelectionModel().getSelectedIndex())).getЭлементы_КТДЭ());
                textFieldTableElement.setText(((Elements) dataElements.get(tableViewElements.getSelectionModel().getSelectedIndex())).getЭлементы_Обозначение());
                textFieldTableName.setText(((Elements) dataElements.get(tableViewElements.getSelectionModel().getSelectedIndex())).getЭлементы_Наименование());
                textFieldTableDescription.setText(((Elements) dataElements.get(tableViewElements.getSelectionModel().getSelectedIndex())).getЭлементы_примечание());
            }
        }
        );
        tableViewMaterials.setOnMousePressed(
                (event)
                ->
        {
            if (tableViewMaterials.getSelectionModel().getSelectedIndex() != -1)
            {
                textFieldTableMaterialKTDM.setText(((Materials) dataMaterials.get(tableViewMaterials.getSelectionModel().getSelectedIndex())).getМатериалы_КТДМ());
                comboboxTableMateral.getEditor().setText(((Materials) dataMaterials.get(tableViewMaterials.getSelectionModel().getSelectedIndex())).getМатериалы_Материал());
                textFieldTableMaterialUnit.setText(((Materials) dataMaterials.get(tableViewMaterials.getSelectionModel().getSelectedIndex())).getМатериалы_ЕИМ());
                comboboxTableMaterialFormulа.getEditor().setText(((Materials) dataMaterials.get(tableViewMaterials.getSelectionModel().getSelectedIndex())).getФормулы_Формула());
                textFieldTableMaterialCofficient.setText(((Materials) dataMaterials.get(tableViewMaterials.getSelectionModel().getSelectedIndex())).getМатериалы_Коэффициент());
            }

        }
        );
        tableViewCommonMaterials.setOnMousePressed(
                (event)
                ->
        {
            if (tableViewCommonMaterials.getSelectionModel().getSelectedIndex() != -1)
            {
                textFieldTableCommonKTDV.setText(((CommonMaterials) dataCommonMaterials.get(tableViewCommonMaterials.getSelectionModel().getSelectedIndex())).getВспомогательные_КТДВ());
                comboboxTableCommon.getEditor().setText(((CommonMaterials) dataCommonMaterials.get(tableViewCommonMaterials.getSelectionModel().getSelectedIndex())).getВспомогательные_Вспомогательный());
                textFieldTableCommonUnit.setText(((CommonMaterials) dataCommonMaterials.get(tableViewCommonMaterials.getSelectionModel().getSelectedIndex())).getВспомогательные_ЕИВ());
                comboboxTableCommonFormula.getEditor().setText(((CommonMaterials) dataCommonMaterials.get(tableViewCommonMaterials.getSelectionModel().getSelectedIndex())).getФормулы_Формула());
                comboboxTableCommonFormula.getEditor().setText(((CommonMaterials) dataCommonMaterials.get(tableViewCommonMaterials.getSelectionModel().getSelectedIndex())).getФормулы_Формула());
            }

        }
        );

        buttonFilterElements.setOnAction(
                (event)
                ->
        {
            showElements("SELECT Элементы.IDЭлементов, Элементы.КТДЭ, Элементы.Обозначение, Элементы.Наименование, Элементы.примечание\n"
                    + "FROM Элементы\n"
                    + "WHERE (((Элементы.Обозначение) Like \"%" + textFieldElementsFiltered.getText().replaceAll("\"", "\"\"") + "%\"))\n"
                    + "ORDER BY Элементы.Обозначение;");
        }
        );
        buttonFilterMaterial.setOnAction(
                (event)
                ->
        {
            showMaterials("SELECT Материалы.IDМатериалов, Материалы.КТДМ, Материалы.Материал, Материалы.ЕИМ, Формулы.Формула, Материалы.КоэффициентМ\n"
                    + "FROM Материалы LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул\n"
                    + "WHERE (((Материалы.Материал) Like \"%" + textFieldMaterial.getText().replaceAll("\"", "\"\"") + "%\"))\n"
                    + "ORDER BY Материалы.Материал;");

        }
        );

        buttonFilterCommon.setOnAction(
                (event)
                ->
        {
            showCommonMaterials("SELECT Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ, Вспомогательные.Вспомогательный, Вспомогательные.ЕИВ, Формулы.Формула\n"
                    + "FROM Вспомогательные LEFT JOIN Формулы ON Вспомогательные.Формула = Формулы.IDФормул\n"
                    + "WHERE (((Вспомогательные.Вспомогательный) Like \"%" + textFieldCommonMaterial.getText().replaceAll("\"", "\"\"") + "%\"))\n"
                    + "ORDER BY Вспомогательные.Вспомогательный;");
        }
        );

        menuItemSaveTree.setOnAction(
                (event)
                ->
        {
            TreeItem<String> model = treeView.getRoot();

            File file = new File(saveXls + File.separator + comboboxTree.getEditor().getText() + ".txt");

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "windows-1251")))
            {
                bw.write(getTreeText(model, "-"));
            }
            catch (IOException ex)
            {

            }
            if (file.exists())
            {
                try
                {
                    desktop.open(file);
                }
                catch (IOException ex)
                {

                }
            }
        }
        );

        buttonFindeTree.setOnAction(
                (event)
                ->
        {
            if (checkBoxNodes.isSelected())
            {
                getTree("SELECT concat_ws(\" \",Элементы_1.Обозначение , Элементы_1.Наименование) AS Обозначение, Дерево.Кол, Элементы_1.ЕИЭ\n"
                        + "FROM (Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов\n"
                        + "WHERE (((Элементы.Обозначение)=\"" + comboboxTree.getSelectionModel().getSelectedItem() + "\")& (Элементы_1.Узел)!=0)\n"
                        + "ORDER BY Дерево.Поз;", comboboxTree.getEditor().getText());
            }
            else
            {
                getTree("SELECT concat_ws(\" \",Элементы_1.Обозначение , Элементы_1.Наименование) AS Обозначение, Дерево.Кол, Элементы_1.ЕИЭ\n"
                        + "FROM (Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов\n"
                        + "WHERE (((Элементы.Обозначение)=\"" + comboboxTree.getSelectionModel().getSelectedItem() + "\"))\n"
                        + "ORDER BY Дерево.Поз;", comboboxTree.getEditor().getText());
            }
        }
        );

        treeView.setOnMousePressed(
                (event)
                ->
        {
            selectTreeViewItem();
        }
        );
        //Thread thread1;
        menuItemSaveMaterials.setOnAction(
                (event)
                ->
        {
            if (!tableViewSpecification.getSelectionModel().isEmpty() || !treeView.getSelectionModel().isEmpty())
            {
                if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0)
                {
                    element = tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Обозначение();
                    specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                }
                else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
                {
                    element = dataElement.get(0).getЭлементы_1_Обозначение();
                    specification = dataElement.get(0);
                }
                new Thread()
                {
                    @Override
                    public void run()
                    {
                        startTime = System.currentTimeMillis();
                        updateУзел("UPDATE Дерево SET Дерево.Обозначение = ?, Дерево.Кол = ?\n"
                                + "WHERE (((Дерево.Родитель)=75490));", specification);
                        executeUpdate("drop table if exists tableall_b, tableall_a, tableall;");
                        executeUpdate(strq1 + strq2);
                        executeUpdate("DELETE FROM tableall LIMIT 1;");
                        executeUpdate("create table tableall_a\n"
                                + "(SELECT Элементы.КТДЭ, Элементы.Обозначение, concat_ws(\" \", Элементы.Наименование, Элементы.Примечание) AS Наименование, Элементы.ЕИЭ, tableall.Кол, Цех.НазваниеЦеха as Цех, Материалы.КТДМ, Материалы.Материал , Материалы.ЕИМ, tableall.Кол*Элементы.НРМ AS НРММ, Цех_1.НазваниеЦеха as Цеха, Вспомогательные.КТДВ, Вспомогательные.Вспомогательный, Вспомогательные.ЕИВ, tableall.Кол*ЭлементыВспомогательные.НРМ AS НРМВ, Цех_2.НазваниеЦеха as Цехб, tableall.IDД\n"
                                + "FROM (((((tableall LEFT JOIN Элементы ON tableall.Обо_а = Элементы.IDЭлементов) LEFT JOIN Материалы ON Элементы.IDМатериала = Материалы.IDМатериалов) LEFT JOIN (Вспомогательные RIGHT JOIN ЭлементыВспомогательные ON Вспомогательные.IDВспомогательных = ЭлементыВспомогательные.IDВспомогательного) ON tableall.Обо_а = ЭлементыВспомогательные.IDЭлемента) LEFT JOIN Цех ON tableall.IDЦЭ_а = Цех.IDЦехов) LEFT JOIN Цех AS Цех_2 ON ЭлементыВспомогательные.IDЦехаВ = Цех_2.IDЦехов) LEFT JOIN (Дерево LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов) ON tableall.IDДа = Дерево.IDДерева\n"
                                + ");");
                        executeUpdate("create  table tableall_b\n"
                                + "(SELECT distinct tableall_a.КТДЭ, tableall_a.Обозначение, Наименование, tableall_a.ЕИЭ, tableall_a.Кол, tableall_a.Цех, tableall_a.IDД\n"
                                + "FROM tableall_a)\n"
                                + "UNION ALL\n"
                                + "(SELECT tableall_a.КТДМ, tableall_a.Материал, NULL, tableall_a.ЕИМ, tableall_a.НРММ, tableall_a.Цеха, tableall_a.Обозначение\n"
                                + "FROM tableall_a where Материал is not null)\n"
                                + "UNION ALL \n"
                                + "(SELECT tableall_a.КТДВ, tableall_a.Вспомогательный, NULL, tableall_a.ЕИВ, tableall_a.НРМВ, tableall_a.Цехб, tableall_a.Обозначение\n"
                                + "FROM tableall_a where Вспомогательный is not null);");
                        try
                        {

                            getNRM("select КТДЭ as КТД,Обозначение, Наименование, sum(Кол) as Кол ,ЕИЭ as ЕИ,Цех\n"
                                    + "from tableall_b\n"
                                    + "group by Обозначение, Цех\n"
                                    + "HAVING (((tableall_b.Цех) Is Not Null));");

                        }
                        catch (IOException ex)
                        {

                        }
                        catch (InvalidFormatException ex)
                        {

                        }
                    }
                }.start();

            }
        }
        );

        menuItemSaveMaterialsFull.setOnAction((event) ->
        {
            if (!tableViewSpecification.getSelectionModel().isEmpty() || !treeView.getSelectionModel().isEmpty())
            {
                if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0)
                {
                    element = tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Обозначение();
                    specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                }
                else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
                {
                    element = dataElement.get(0).getЭлементы_1_Обозначение();
                    specification = dataElement.get(0);
                }
                new Thread()
                {
                    @Override
                    public void run()
                    {

                        updateУзел("UPDATE Дерево SET Дерево.Обозначение = ?, Дерево.Кол = ?\n"
                                + "WHERE (((Дерево.Родитель)=75490));", specification);
                        executeUpdate("drop table if exists tableall_b, tableall_a, tableall;");
                        executeUpdate(strq1 + strq2);
                        executeUpdate("DELETE FROM tableall LIMIT 1;");
                        executeUpdate("create table tableall_a\n"
                                + "(SELECT Элементы.КТДЭ, Элементы.Обозначение, concat_ws(\" \", Элементы.Наименование, Элементы.Примечание) AS Наименование, Элементы.ЕИЭ, tableall.Кол, Цех.НазваниеЦеха as Цех, Материалы.КТДМ, Материалы.Материал , Материалы.ЕИМ, tableall.Кол*Элементы.НРМ AS НРММ, Цех_1.НазваниеЦеха as Цеха, Вспомогательные.КТДВ, Вспомогательные.Вспомогательный, Вспомогательные.ЕИВ, tableall.Кол*ЭлементыВспомогательные.НРМ AS НРМВ, Цех_2.НазваниеЦеха as Цехб, tableall.IDД\n"
                                + "FROM (((((tableall LEFT JOIN Элементы ON tableall.Обо_а = Элементы.IDЭлементов) LEFT JOIN Материалы ON Элементы.IDМатериала = Материалы.IDМатериалов) LEFT JOIN (Вспомогательные RIGHT JOIN ЭлементыВспомогательные ON Вспомогательные.IDВспомогательных = ЭлементыВспомогательные.IDВспомогательного) ON tableall.Обо_а = ЭлементыВспомогательные.IDЭлемента) LEFT JOIN Цех ON tableall.IDЦЭ_а = Цех.IDЦехов) LEFT JOIN Цех AS Цех_2 ON ЭлементыВспомогательные.IDЦехаВ = Цех_2.IDЦехов) LEFT JOIN (Дерево LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов) ON tableall.IDДа = Дерево.IDДерева\n"
                                + ");");
                        executeUpdate("create  table tableall_b\n"
                                + "(SELECT distinct tableall_a.КТДЭ, tableall_a.Обозначение, Наименование, tableall_a.ЕИЭ, tableall_a.Кол, tableall_a.Цех, tableall_a.IDД\n"
                                + "FROM tableall_a)\n"
                                + "UNION ALL\n"
                                + "(SELECT tableall_a.КТДМ, tableall_a.Материал, NULL, tableall_a.ЕИМ, tableall_a.НРММ, tableall_a.Цеха, tableall_a.Обозначение\n"
                                + "FROM tableall_a where Материал is not null)\n"
                                + "UNION ALL \n"
                                + "(SELECT tableall_a.КТДВ, tableall_a.Вспомогательный, NULL, tableall_a.ЕИВ, tableall_a.НРМВ, tableall_a.Цехб, tableall_a.Обозначение\n"
                                + "FROM tableall_a where Вспомогательный is not null);");
                        try
                        {

                            getNRMFull("select КТДЭ as КТД,Обозначение, Наименование, sum(Кол) as Кол ,ЕИЭ as ЕИ,Цех\n"
                                    + "from tableall_b\n"
                                    + "group by Обозначение, Цех;");

                        }
                        catch (IOException ex)
                        {

                        }
                        catch (InvalidFormatException ex)
                        {

                        }
                    }
                }.start();

            }
        });
        menuItemCommon.setOnAction((event) ->
        {
            if (!tableViewSpecification.getSelectionModel().isEmpty() || !treeView.getSelectionModel().isEmpty())
            {
                if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0)
                {
                    element = tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Обозначение();
                    specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                }
                else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
                {
                    element = dataElement.get(0).getЭлементы_1_Обозначение();
                    specification = dataElement.get(0);
                }
                new Thread()
                {
                    @Override
                    public void run()
                    {

                        updateУзел("UPDATE Дерево SET Дерево.Обозначение = ?, Дерево.Кол = ?\n"
                                + "WHERE (((Дерево.Родитель)=75490));", specification);
                        executeUpdate("drop table if exists tableall_b, tableall_a, tableall;");
                        executeUpdate(strq1 + strq2);
                        executeUpdate("DELETE FROM tableall LIMIT 1;");
                        executeUpdate("create table tableall_a\n"
                                + "(SELECT Элементы.КТДЭ, Элементы.Обозначение, concat_ws(\" \", Элементы.Наименование, Элементы.Примечание) AS Наименование, Элементы.ЕИЭ, tableall.Кол, Цех.НазваниеЦеха as Цех, Материалы.КТДМ, Материалы.Материал , Материалы.ЕИМ, tableall.Кол*Элементы.НРМ AS НРММ, Цех_1.НазваниеЦеха as Цеха, Вспомогательные.КТДВ, Вспомогательные.Вспомогательный, Вспомогательные.ЕИВ, tableall.Кол*ЭлементыВспомогательные.НРМ AS НРМВ, Цех_2.НазваниеЦеха as Цехб, tableall.IDД\n"
                                + "FROM (((((tableall LEFT JOIN Элементы ON tableall.Обо_а = Элементы.IDЭлементов) LEFT JOIN Материалы ON Элементы.IDМатериала = Материалы.IDМатериалов) LEFT JOIN (Вспомогательные RIGHT JOIN ЭлементыВспомогательные ON Вспомогательные.IDВспомогательных = ЭлементыВспомогательные.IDВспомогательного) ON tableall.Обо_а = ЭлементыВспомогательные.IDЭлемента) LEFT JOIN Цех ON tableall.IDЦЭ_а = Цех.IDЦехов) LEFT JOIN Цех AS Цех_2 ON ЭлементыВспомогательные.IDЦехаВ = Цех_2.IDЦехов) LEFT JOIN (Дерево LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов) ON tableall.IDДа = Дерево.IDДерева\n"
                                + ");");
                        executeUpdate("create  table tableall_b\n"
                                + "(SELECT distinct tableall_a.КТДЭ, tableall_a.Обозначение, Наименование, tableall_a.ЕИЭ, tableall_a.Кол, tableall_a.Цех, tableall_a.IDД\n"
                                + "FROM tableall_a)\n"
                                + "UNION ALL\n"
                                + "(SELECT tableall_a.КТДМ, tableall_a.Материал, NULL, tableall_a.ЕИМ, tableall_a.НРММ, tableall_a.Цеха, tableall_a.Обозначение\n"
                                + "FROM tableall_a where Материал is not null)\n"
                                + "UNION ALL \n"
                                + "(SELECT tableall_a.КТДВ, tableall_a.Вспомогательный, NULL, tableall_a.ЕИВ, tableall_a.НРМВ, tableall_a.Цехб, tableall_a.Обозначение\n"
                                + "FROM tableall_a where Вспомогательный is not null);");
                        try
                        {
                            getCommonMaterials1("select КТДЭ, Обозначение, Наименование, ЕИЭ, Кол, Цех, КТДМ, Материал, ЕИМ, НРММ, Цеха, КТДВ, Вспомогательный, ЕИВ, НРМВ,Цехб \n"
                                    + "from tableall_a\n");
                        }
                        catch (IOException ex)
                        {

                        }
                        catch (InvalidFormatException ex)
                        {

                        }
                    }
                }.start();

            }
        });
        menuItemSaveCut.setOnAction(
                (event)
                ->
        {
            if (!tableViewSpecification.getSelectionModel().isEmpty() || !treeView.getSelectionModel().isEmpty())
            {
                if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0)
                {
                    element = tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Обозначение();
                    specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                }
                else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
                {
                    element = dataElement.get(0).getЭлементы_1_Обозначение();
                    specification = dataElement.get(0);
                }
                new Thread()
                {
                    @Override
                    public void run()
                    {
                        updateУзел("UPDATE Дерево SET Дерево.Обозначение = ?, Дерево.Кол = ?\n"
                                + "WHERE (((Дерево.Родитель)=75490));", specification);

                        executeUpdate("drop table if exists tableall_a,tableall;");
                        executeUpdate(strq1 + strq2);
                        executeUpdate("DELETE FROM tableall LIMIT 1;");
                        executeUpdate("create table tableall_a\n"
                                + "SELECT Элементы.а as Длина, Элементы.б as Ширина, tableall.Кол, Материалы.Материал, null, concat_ws(\" \",Элементы.Обозначение, Элементы.Наименование, Элементы.Примечание) AS Обозначение, Элементы.НРМ, Цех.НазваниеЦеха\n"
                                + "FROM ((Элементы RIGHT JOIN tableall ON Элементы.IDЭлементов = tableall.Обо_а) LEFT JOIN Материалы ON Элементы.IDМатериала = Материалы.IDМатериалов) LEFT JOIN (Цех RIGHT JOIN Дерево ON Цех.IDЦехов = Дерево.IDЦехаМ) ON tableall.IDДа = Дерево.IDДерева\n"
                                + "WHERE ((concat_ws(\" \",Элементы.Обозначение, Элементы.Наименование, Элементы.Примечание)<>\"\"))\n"
                                + "ORDER BY Обозначение;"
                        );
                        try
                        {
                            getCut("SELECT * FROM snm.tableall_a;");
                        }
                        catch (IOException ex)
                        {

                        }
                        catch (InvalidFormatException ex)
                        {

                        }
                    }
                }.start();

            }
        }
        );
        menuItemColoring.setOnAction(
                (event)
                ->
        {
            if (!tableViewSpecification.getSelectionModel().isEmpty() || !treeView.getSelectionModel().isEmpty())
            {
                if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0)
                {
                    element = tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Обозначение();
                    specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                }
                else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
                {
                    element = dataElement.get(0).getЭлементы_1_Обозначение();
                    specification = dataElement.get(0);
                }
                new Thread()
                {
                    @Override
                    public void run()
                    {
                        updateУзел("UPDATE Дерево SET Дерево.Обозначение = ?, Дерево.Кол = ?\n"
                                + "WHERE (((Дерево.Родитель)=75490));", specification);

                        executeUpdate("drop table if exists tableall_a,tableall;");
                        executeUpdate(strq1 + strq2);
                        executeUpdate("DELETE FROM tableall LIMIT 1;");
                        executeUpdate("create table tableall_a\n"
                                + "SELECT Элементы.а as Длина, Элементы.б as Ширина, tableall.Кол, null as Сторон, null as Вес, Материалы.Материал, null, concat_ws(\" \",Элементы.Обозначение, Элементы.Наименование, Элементы.Примечание) AS Обозначение, Элементы.НРМ, Цех.НазваниеЦеха\n"
                                + "FROM ((Элементы RIGHT JOIN tableall ON Элементы.IDЭлементов = tableall.Обо_а) LEFT JOIN Материалы ON Элементы.IDМатериала = Материалы.IDМатериалов) LEFT JOIN (Цех RIGHT JOIN Дерево ON Цех.IDЦехов = Дерево.IDЦехаМ) ON tableall.IDДа = Дерево.IDДерева\n"
                                + "WHERE ((concat_ws(\" \",Элементы.Обозначение, Элементы.Наименование, Элементы.Примечание)<>\"\"))\n"
                                + "ORDER BY Обозначение;"
                        );
                        try
                        {
                            getColoring("SELECT * FROM snm.tableall_a;");
                        }
                        catch (IOException ex)
                        {

                        }
                        catch (InvalidFormatException ex)
                        {

                        }
                    }
                }.start();

            }
        }
        );
        menuItemSaveCheck.setOnAction(
                (event)
                ->
        {
            if (!tableViewSpecification.getSelectionModel().isEmpty() || !treeView.getSelectionModel().isEmpty())
            {
                if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0)
                {
                    element = tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Обозначение();
                    specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                }
                else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
                {
                    element = dataElement.get(0).getЭлементы_1_Обозначение();
                    specification = dataElement.get(0);
                }
                new Thread()
                {
                    @Override
                    public void run()
                    {
                        updateУзел("UPDATE Дерево SET Дерево.Обозначение = ?, Дерево.Кол = ?\n"
                                + "WHERE (((Дерево.Родитель)=75490));", specification);

                        executeUpdate("drop table if exists tableall_a,tableall;");
                        executeUpdate(strq1 + strq2);
                        executeUpdate("DELETE FROM tableall LIMIT 1;");
                        executeUpdate("create table tableall_a\n"
                                + "SELECT Элементы.а, Элементы.б, Элементы.в, Элементы.г, Элементы.д, Элементы.е, concat_ws(\" \",Элементы.Обозначение, Элементы.Наименование, Элементы.Примечание) AS Обозначение, Материалы.Материал, tableall.Кол, Элементы.ЕИЭ, Элементы.НРМ, Материалы.ЕИМ, Цех.НазваниеЦеха AS ЦехМ, цех_1.НазваниеЦеха AS ЦехЭ\n"
                                + "FROM (((Элементы RIGHT JOIN tableall ON Элементы.IDЭлементов = tableall.Обо_а) LEFT JOIN Материалы ON Элементы.IDМатериала = Материалы.IDМатериалов) LEFT JOIN (Цех RIGHT JOIN Дерево ON Цех.IDЦехов = Дерево.IDЦехаМ) ON tableall.IDДа = Дерево.IDДерева) LEFT JOIN цех AS цех_1 ON Дерево.IDЦехаЭ = цех_1.IDЦехов\n"
                                + "WHERE ((concat_ws(\" \",Элементы.Обозначение, Элементы.Наименование, Элементы.Примечание)<>\"\"))\n"
                                + "ORDER BY Обозначение;"
                        );
                        try
                        {
                            getCheck1("SELECT * FROM snm.tableall_a;");
                        }
                        catch (IOException ex)
                        {

                        }
                        catch (InvalidFormatException ex)
                        {

                        }
                    }
                }.start();

            }
        }
        );
        menuItemCooperation.setOnAction((event) ->
        {
            if (!tableViewSpecification.getSelectionModel().isEmpty() || !treeView.getSelectionModel().isEmpty())
            {
                if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0)
                {
                    element = tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Обозначение();
                    specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                }
                else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
                {
                    element = dataElement.get(0).getЭлементы_1_Обозначение();
                    specification = dataElement.get(0);
                }
                new Thread()
                {
                    @Override
                    public void run()
                    {
                        updateУзел("UPDATE Дерево SET Дерево.Обозначение = ?, Дерево.Кол = ?\n"
                                + "WHERE (((Дерево.Родитель)=75490));", specification);
                        executeUpdate("drop table if exists tableall_b, tableall_a, tableall;");
                        executeUpdate(strq1 + strq2);
                        executeUpdate("DELETE FROM tableall LIMIT 1;");
                        executeUpdate("create table tableall_a\n"
                                + "(SELECT Элементы.КТДЭ, Элементы.Обозначение, concat_ws(\" \", Элементы.Наименование, Элементы.Примечание) AS Наименование, Элементы.ЕИЭ, tableall.Кол, Цех.НазваниеЦеха as Цех, Материалы.КТДМ, Материалы.Материал , Материалы.ЕИМ, tableall.Кол*Элементы.НРМ AS НРММ, Цех_1.НазваниеЦеха as Цеха, Вспомогательные.КТДВ, Вспомогательные.Вспомогательный, Вспомогательные.ЕИВ, tableall.Кол*ЭлементыВспомогательные.НРМ AS НРМВ, Цех_2.НазваниеЦеха as Цехб, tableall.IDД\n"
                                + "FROM (((((tableall LEFT JOIN Элементы ON tableall.Обо_а = Элементы.IDЭлементов) LEFT JOIN Материалы ON Элементы.IDМатериала = Материалы.IDМатериалов) LEFT JOIN (Вспомогательные RIGHT JOIN ЭлементыВспомогательные ON Вспомогательные.IDВспомогательных = ЭлементыВспомогательные.IDВспомогательного) ON tableall.Обо_а = ЭлементыВспомогательные.IDЭлемента) LEFT JOIN Цех ON tableall.IDЦЭ_а = Цех.IDЦехов) LEFT JOIN Цех AS Цех_2 ON ЭлементыВспомогательные.IDЦехаВ = Цех_2.IDЦехов) LEFT JOIN (Дерево LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов) ON tableall.IDДа = Дерево.IDДерева\n"
                                + ");");
                        executeUpdate("create  table tableall_b\n"
                                + "(SELECT distinct tableall_a.КТДЭ, tableall_a.Обозначение, Наименование, tableall_a.ЕИЭ, tableall_a.Кол, tableall_a.Цех, tableall_a.IDД\n"
                                + "FROM tableall_a)");
                        try
                        {
                            getCooperation("SELECT Null AS №, tableall_b.Обозначение, tableall_b.Наименование, Sum(tableall_b.Кол) AS Кол, tableall_b.ЕИЭ AS ЕИ, tableall_b.Цех\n"
                                    + "FROM tableall_b\n"
                                    + "GROUP BY tableall_b.Обозначение, tableall_b.Наименование, tableall_b.ЕИЭ, tableall_b.Цех\n"
                                    + "HAVING (((tableall_b.Цех)=\"Токарка\" Or (tableall_b.Цех)=\"Фрезеровка\" Or (tableall_b.Цех)=\"Внешняя коопер\"));");

                        }
                        catch (IOException ex)
                        {

                        }
                        catch (InvalidFormatException ex)
                        {

                        }
                    }
                }.start();

            }
            else
            {

            }
        });
        menuItemCooperationMidi.setOnAction((event) ->
        {
            if (!tableViewSpecification.getSelectionModel().isEmpty() && !comboboxOrder.getSelectionModel().isEmpty() || !treeView.getSelectionModel().isEmpty())
            {
                if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0)
                {
                    element = tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Обозначение();
                    specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                }
                else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
                {
                    element = dataElement.get(0).getЭлементы_1_Обозначение();
                    specification = dataElement.get(0);
                }
                new Thread()
                {
                    @Override
                    public void run()
                    {
                        updateУзел("UPDATE Дерево SET Дерево.Обозначение = ?, Дерево.Кол = ?\n"
                                + "WHERE (((Дерево.Родитель)=75490));", specification);
                        executeUpdate("drop table if exists tableall_b, tableall_a, tableall;");
                        executeUpdate(strq1 + strq2);
                        executeUpdate("DELETE FROM tableall LIMIT 1;");
                        executeUpdate("create table tableall_a\n"
                                + "(SELECT Элементы.КТДЭ, Элементы.Обозначение, concat_ws(\" \", Элементы.Наименование, Элементы.Примечание) AS Наименование, Элементы.ЕИЭ, tableall.Кол, Цех.НазваниеЦеха as Цех, Материалы.КТДМ, Материалы.Материал , Материалы.ЕИМ, tableall.Кол*Элементы.НРМ AS НРММ, Цех_1.НазваниеЦеха as Цеха, Вспомогательные.КТДВ, Вспомогательные.Вспомогательный, Вспомогательные.ЕИВ, tableall.Кол*ЭлементыВспомогательные.НРМ AS НРМВ, Цех_2.НазваниеЦеха as Цехб, tableall.IDД\n"
                                + "FROM (((((tableall LEFT JOIN Элементы ON tableall.Обо_а = Элементы.IDЭлементов) LEFT JOIN Материалы ON Элементы.IDМатериала = Материалы.IDМатериалов) LEFT JOIN (Вспомогательные RIGHT JOIN ЭлементыВспомогательные ON Вспомогательные.IDВспомогательных = ЭлементыВспомогательные.IDВспомогательного) ON tableall.Обо_а = ЭлементыВспомогательные.IDЭлемента) LEFT JOIN Цех ON tableall.IDЦЭ_а = Цех.IDЦехов) LEFT JOIN Цех AS Цех_2 ON ЭлементыВспомогательные.IDЦехаВ = Цех_2.IDЦехов) LEFT JOIN (Дерево LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов) ON tableall.IDДа = Дерево.IDДерева\n"
                                + ");");
                        executeUpdate("create  table tableall_b\n"
                                + "(SELECT distinct tableall_a.КТДЭ, tableall_a.Обозначение, Наименование, tableall_a.ЕИЭ, tableall_a.Кол, tableall_a.Цех, tableall_a.IDД\n"
                                + "FROM tableall_a)");
                        try
                        {
                            getCooperationMidi("SELECT Null AS №, tableall_b.Обозначение, tableall_b.Наименование, Sum(tableall_b.Кол) AS Кол, tableall_b.ЕИЭ AS ЕИ, tableall_b.Цех\n"
                                    + "FROM tableall_b\n"
                                    + "GROUP BY tableall_b.Обозначение, tableall_b.Наименование, tableall_b.ЕИЭ, tableall_b.Цех\n"
                                    + "HAVING (((tableall_b.Цех)=\"Токарка\" Or (tableall_b.Цех)=\"Фрезеровка\" Or (tableall_b.Цех)=\"Внешняя коопер\"));");
                        }
                        catch (IOException ex)
                        {
                        }
                        catch (InvalidFormatException ex)
                        {
                        }
                    }
                }.start();
            }
            else
            {
            }
        });
        comboboxGuildMaterial.setOnShowing(
                (event)
                ->
        {
            getGuildMaterials("SELECT Цех.НазваниеЦеха\n"
                    + "FROM Цех\n"
                    + "ORDER BY Цех.НазваниеЦеха;");
        }
        );
        comboboxGuildMaterial.setOnHiding(
                (event)
                ->
        {

            int index = tableViewSpecification.getSelectionModel().getSelectedIndex();

            if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
            {
                specification1.add(dataElement.get(0));
            }
            else
            {
                specification1 = tableViewSpecification.getSelectionModel().getSelectedItems();
            }

            for (Specification specifications2 : specification1)
            {
                if (comboboxGuildMaterial.getSelectionModel().getSelectedItem() != null)
                {
                    specifications2.setЦех_1_НазваниеЦеха(comboboxGuildMaterial.getSelectionModel().getSelectedItem().toString());
                    updateGuildMaterials("UPDATE Дерево SET Дерево.IDЦехаМ = ?\n"
                            + "WHERE (((Дерево.IDДерева)=" + specifications2.getДерево_IDДерева() + "));", getId("SELECT Цех.IDЦехов\n"
                            + "FROM Цех\n"
                            + "WHERE (((Цех.НазваниеЦеха)=\"" + comboboxGuildMaterial.getEditor().getText() + "\"));", "IDЦехов"));
                }
                else
                {
                    specifications2.setЦех_1_НазваниеЦеха("");
                    updateGuildMaterials("UPDATE Дерево SET Дерево.IDЦехаМ = ?\n"
                            + "WHERE (((Дерево.IDДерева)=" + specifications2.getДерево_IDДерева() + "));", 0);
                }
            }
            tableViewSpecification.getSelectionModel().select(index);

        }
        );

        comboboxMaterial.setOnShowing(
                (event)
                ->
        {
            if (comboboxMaterial.getEditor().getText() != null)
            {
                getMaterials("SELECT Материалы.Материал\n"
                        + "FROM Материалы\n"
                        + "WHERE (((Материалы.Материал) Like \"%" + comboboxMaterial.getEditor().getText().replaceAll("\"", "\"\"") + "%\"))\n"
                        + "ORDER BY Материалы.Материал;");
            }
            else
            {
                getMaterials("SELECT Материалы.Материал\n"
                        + "FROM Материалы\n"
                        + "ORDER BY Материалы.Материал;");
            }
        });
        /*
        jfxTextFieldMaterial.setOnKeyReleased((event) ->
        {
            TextFields.bindAutoCompletion(jfxTextFieldMaterial, materialsList).setMinWidth(jfxTextFieldMaterial.getWidth());
            getMaterials("SELECT Материалы.Материал\n"
                    + "FROM Материалы\n"
                    + "WHERE (((Материалы.Материал) Like \"%" + jfxTextFieldMaterial.getText().replaceAll("\"", "\"\"") + "%\"))\n"
                    + "ORDER BY Материалы.Материал;");
        });
        anchorPanneMaterial.setOnMouseClicked((event) ->
        {
            if (jfxTextFieldMaterial.getText() != null)
            {
                int index = tableViewSpecification.getSelectionModel().getSelectedIndex();
                ObservableList<Specification> observableList = tableViewSpecification.getSelectionModel().getSelectedItems();
                dataMaterialsValues.clear();

                for (Specification specification1 : observableList)
                {
                    int id = specification1.getЭлементы_1_IDЭлементов();
                    if (jfxTextFieldMaterial.getText() != null)
                    {
                        specification1.setМатериалы_Материал(jfxTextFieldMaterial.getText());
                        updateMaterial("UPDATE Элементы SET Элементы.IDМатериала = ?\n"
                                + "WHERE (((Элементы.IDЭлементов)=\"" + id + "\"));", getId("SELECT Материалы.IDМатериалов\n"
                                        + "FROM Материалы\n"
                                        + "WHERE (((Материалы.Материал)=\"" + jfxTextFieldMaterial.getText().replaceAll("\"", "\"\"") + "\"));", "IDМатериалов"));
                    }
                    else
                    {
                        specification1.setМатериалы_Материал("");
                        updateMaterial("UPDATE Элементы SET Элементы.IDМатериала = ?\n"
                                + "WHERE (((Элементы.IDЭлементов)=\"" + id + "\"));", 0);
                    }
                }
                getElemnts("SELECT Элементы_1.IDЭлементов, Дерево.Поз, Элементы_1.Обозначение, Элементы_1.Наименование, Элементы_1.примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы.Обозначение, Элементы_1.а, Элементы_1.б, Элементы_1.в, Элементы_1.г, Элементы_1.д, Элементы_1.е, Элементы_1.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы_1.КоэффициентЭ, Материалы.КоэффициентМ, Элементы_1.КТДЭ, Элементы_1.ЕИЭ, Материалы.КТДМ, Элементы_1.Узел\n"
                        + "FROM (Цех RIGHT JOIN ((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) LEFT JOIN ((Элементы AS Элементы_1 LEFT JOIN Материалы ON Элементы_1.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) ON Дерево.Обозначение = Элементы_1.IDЭлементов) ON Цех.IDЦехов = Дерево.IDЦехаЭ) LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов\n"
                        + "WHERE (((Элементы.Обозначение)=\"" + labelParent.getText().replaceAll("\"", "\"\"") + "\"))\n"
                        + "ORDER BY Дерево.Поз;");
                tableViewSpecification.getSelectionModel().select(index);

                dataMaterialsValues.add(new MaterialsValues(
                        tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_а(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_а()
                ));
                dataMaterialsValues.add(new MaterialsValues(
                        tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_б(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_б()
                ));
                dataMaterialsValues.add(new MaterialsValues(
                        tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_в(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_в()
                ));
                dataMaterialsValues.add(new MaterialsValues(
                        tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_г(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_г()
                ));
                dataMaterialsValues.add(new MaterialsValues(
                        tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_д(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_д()
                ));
                dataMaterialsValues.add(new MaterialsValues(
                        tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_е(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_е()
                ));
                getParameters(tableViewSpecification.getSelectionModel().getSelectedItem());
            }
        });*/
        comboboxMaterial.setOnHiding(
                (event)
                ->
        {
            int index = tableViewSpecification.getSelectionModel().getSelectedIndex();
            ObservableList<Specification> observableList = tableViewSpecification.getSelectionModel().getSelectedItems();
            dataMaterialsValues.clear();

            for (Specification specification1 : observableList)
            {
                int id = specification1.getЭлементы_1_IDЭлементов();
                if (comboboxMaterial.getSelectionModel().getSelectedItem() != null)
                {
                    specification1.setМатериалы_Материал(comboboxMaterial.getSelectionModel().getSelectedItem().toString());
                    updateMaterial("UPDATE Элементы SET Элементы.IDМатериала = ?\n"
                            + "WHERE (((Элементы.IDЭлементов)=\"" + id + "\"));", getId("SELECT Материалы.IDМатериалов\n"
                                    + "FROM Материалы\n"
                                    + "WHERE (((Материалы.Материал)=\"" + comboboxMaterial.getEditor().getText().replaceAll("\"", "\"\"") + "\"));", "IDМатериалов"));
                }
                else
                {
                    specification1.setМатериалы_Материал("");
                    updateMaterial("UPDATE Элементы SET Элементы.IDМатериала = ?\n"
                            + "WHERE (((Элементы.IDЭлементов)=\"" + id + "\"));", 0);
                }
            }
            try
            {
                getElemnts("SELECT Элементы_1.IDЭлементов, Дерево.Поз, Элементы_1.Обозначение, Элементы_1.Наименование, Элементы_1.примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы.Обозначение, Элементы_1.а, Элементы_1.б, Элементы_1.в, Элементы_1.г, Элементы_1.д, Элементы_1.е, Элементы_1.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы_1.КоэффициентЭ, Материалы.КоэффициентМ, Элементы_1.КТДЭ, Элементы_1.ЕИЭ, Материалы.КТДМ, Элементы_1.Узел\n"
                        + "FROM (Цех RIGHT JOIN ((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) LEFT JOIN ((Элементы AS Элементы_1 LEFT JOIN Материалы ON Элементы_1.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) ON Дерево.Обозначение = Элементы_1.IDЭлементов) ON Цех.IDЦехов = Дерево.IDЦехаЭ) LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов\n"
                        + "WHERE (((Элементы.Обозначение)=\"" + labelParent.getText().replaceAll("\"", "\"\"") + "\"))\n"
                        + "ORDER BY Дерево.Поз;");
            }
            catch (NullPointerException ex)
            {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

            tableViewSpecification.getSelectionModel().select(index);

            dataMaterialsValues.add(new MaterialsValues(
                    tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_а(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_а()
            ));
            dataMaterialsValues.add(new MaterialsValues(
                    tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_б(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_б()
            ));
            dataMaterialsValues.add(new MaterialsValues(
                    tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_в(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_в()
            ));
            dataMaterialsValues.add(new MaterialsValues(
                    tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_г(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_г()
            ));
            dataMaterialsValues.add(new MaterialsValues(
                    tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_д(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_д()
            ));
            dataMaterialsValues.add(new MaterialsValues(
                    tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_е(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_е()
            ));
            getParameters(tableViewSpecification.getSelectionModel().getSelectedItem());
        }
        );
        comboboxCommonMaterial.setOnShowing(
                (event)
                ->
        {
            if (comboboxCommonMaterial.getEditor().getText() != null)
            {

                String str = comboboxCommonMaterial.getEditor().getText().replaceAll("\"", "\"\"");
                getCommonMaterials("SELECT Вспомогательные.Вспомогательный\n"
                        + "FROM Вспомогательные\n"
                        + "WHERE (((Вспомогательные.Вспомогательный) Like \"%" + str + "%\"))\n"
                        + "ORDER BY Вспомогательные.Вспомогательный;");
            }
            else
            {
                getCommonMaterials("SELECT Вспомогательные.Вспомогательный\n"
                        + "FROM Вспомогательные\n"
                        + "ORDER BY Вспомогательные.Вспомогательный;");
            }

        }
        );
        comboboxCommonMaterial.setOnHiding(
                (event)
                ->
        {
            if (comboboxCommonMaterial.getSelectionModel().getSelectedItem() != null && tableViewCommonMaterial.getSelectionModel().getSelectedIndex() != -1)
            {
                int index = tableViewCommonMaterial.getSelectionModel().getSelectedIndex();
                int id = tableViewCommonMaterial.getSelectionModel().getSelectedItem().getВ_ЭлементыВспомогательные_IDЭлементовВспомогательных();
                updateCommonMaterial("UPDATE ЭлементыВспомогательные SET ЭлементыВспомогательные.IDВспомогательного = ? \n"
                        + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных)=" + id + "));");

                commonMaterial = getRowCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                        + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                        + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных)=" + id + "));");
                dataSpecificationCommonMaterials.set(rowIndex.get(index), commonMaterial);
                tableViewCommonMaterial.setItems(commonMaterialsFilter(dataSpecificationCommonMaterials));
                tableViewCommonMaterial.getSelectionModel().select(index);

                labelCommonFormula.setText(commonMaterial.getВ_Формулы_ФормулаРасчета());
                labelCommonResult.setText(commonMaterial.getВ_ЭлементыВспомогательные_НРМ());
                labelCommonUnit.setText(commonMaterial.getВ_Вспомогательные_ЕИВ());

                textFieldKTDC.setText(commonMaterial.getВ_Вспомогательные_КТДВ());
                textFieldСoefficientC.setText(commonMaterial.getВ_Вспомогательные_КоэффициентВМ());
                textFieldСoefficientCE.setText(commonMaterial.getВ_ЭлементыВспомогательные_КоэфВЭ());

                dataCommonMaterialsValues.set(0, new CommonMaterialsValues(commonMaterial.getВ_Формулы_а(), commonMaterial.getВ_ЭлементыВспомогательные_вспа()));
                dataCommonMaterialsValues.set(1, new CommonMaterialsValues(commonMaterial.getВ_Формулы_б(), commonMaterial.getВ_ЭлементыВспомогательные_вспб()));
                dataCommonMaterialsValues.set(2, new CommonMaterialsValues(commonMaterial.getВ_Формулы_в(), commonMaterial.getВ_ЭлементыВспомогательные_вспв()));
                dataCommonMaterialsValues.set(3, new CommonMaterialsValues(commonMaterial.getВ_Формулы_г(), commonMaterial.getВ_ЭлементыВспомогательные_вспг()));
                dataCommonMaterialsValues.set(4, new CommonMaterialsValues(commonMaterial.getВ_Формулы_д(), commonMaterial.getВ_ЭлементыВспомогательные_вспд()));
                dataCommonMaterialsValues.set(5, new CommonMaterialsValues(commonMaterial.getВ_Формулы_е(), commonMaterial.getВ_ЭлементыВспомогательные_вспе()));
            }

        }
        );
        comboboxGuildCommonMaterial.setOnShowing(
                (event)
                ->
        {
            getGuildCommonMaterials("SELECT Цех.НазваниеЦеха\n"
                    + "FROM Цех\n"
                    + "ORDER BY Цех.НазваниеЦеха;");
        }
        );

        comboboxGuildCommonMaterial.setOnHiding(
                (event)
                ->
        {
            if (comboboxGuildCommonMaterial.getSelectionModel().getSelectedItem() != null && tableViewCommonMaterial.getSelectionModel().getSelectedIndex() != -1)
            {
                int index = tableViewCommonMaterial.getSelectionModel().getSelectedIndex();
                int id = tableViewCommonMaterial.getSelectionModel().getSelectedItem().getВ_ЭлементыВспомогательные_IDЭлементовВспомогательных();
                commonMaterial = dataSpecificationCommonMaterials.get(rowIndex.get(index));
                updateGuildCommonMaterial("UPDATE ЭлементыВспомогательные SET ЭлементыВспомогательные.IDЦехаВ = ? \n"
                        + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных)=" + id + "));");
                commonMaterial = getRowCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                        + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                        + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных)=" + id + "));");
                dataSpecificationCommonMaterials.set(rowIndex.get(index), commonMaterial);
                tableViewCommonMaterial.setItems(commonMaterialsFilter(dataSpecificationCommonMaterials));
                tableViewCommonMaterial.getSelectionModel().select(index);
            }
        }
        );
        buttonAddElementRow.setOnAction(
                (event)
                ->
        {
            if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0)
            {
                setElementRow();
            }
            else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
            {

                setElementRow2();
            }
        }
        );
        textFieldElement.setOnAction(
                (event)
                ->
        {
            if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0)
            {
                specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                specification.setЭлементы_1_Обозначение(textFieldElement.getText());
                updateString("UPDATE Элементы SET Элементы.Обозначение = ?\n"
                        + "WHERE (((Элементы.IDЭлементов)=" + specification.getЭлементы_1_IDЭлементов() + "));", textFieldElement.getText());
                tableViewSpecification.getSelectionModel().selectNext();
                tableViewSpecification.getSelectionModel().clearSelection(tableViewSpecification.getSelectionModel().getSelectedIndex() - 1);
                textFieldElement.selectAll();
            }
            else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
            {
                treeView.getTreeItem(treeView.getSelectionModel().getSelectedIndex()).setValue(textFieldElement.getText() + " " + textFieldName.getText() + " " + textFieldCount.getText() + textFieldUnit.getText());
                updateString("UPDATE Элементы SET Элементы.Обозначение = ?\n"
                        + "WHERE (((Элементы.IDЭлементов)=" + dataElement.get(0).getЭлементы_1_IDЭлементов() + "));", textFieldElement.getText());
            }

        }
        );

        textFieldPosition.setOnAction(
                (event)
                ->
        {
            if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0 && tableViewSpecification.getSelectionModel().getSelectedIndex() != -1)
            {
                specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                specification.setДерево_Поз(textFieldPosition.getText());
                updateInteger("UPDATE Дерево SET Дерево.Поз = ?\n"
                        + "WHERE (((Дерево.IDДерева)=" + specification.getДерево_IDДерева() + "));", Integer.parseInt(textFieldPosition.getText()));
                tableViewSpecification.getSelectionModel().selectNext();
                tableViewSpecification.getSelectionModel().clearSelection(tableViewSpecification.getSelectionModel().getSelectedIndex() - 1);
                textFieldPosition.selectAll();
            }
        }
        );
        textFieldName.setOnAction(
                (event)
                ->
        {
            if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0 && tableViewSpecification.getSelectionModel().getSelectedIndex() != -1)
            {
                specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                specification.setЭлементы_1_Наименование(textFieldName.getText());
                updateString("UPDATE Элементы SET Элементы.Наименование = ?\n"
                        + "WHERE (((Элементы.IDЭлементов)=" + specification.getЭлементы_1_IDЭлементов() + "));", textFieldName.getText());
                tableViewSpecification.getSelectionModel().selectNext();
                tableViewSpecification.getSelectionModel().clearSelection(tableViewSpecification.getSelectionModel().getSelectedIndex() - 1);
                textFieldName.selectAll();
            }
        }
        );
        textFieldDescription.setOnAction(
                (event)
                ->
        {
            if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0 && tableViewSpecification.getSelectionModel().getSelectedIndex() != -1)
            {
                specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                specification.setЭлементы_1_Примечание(textFieldDescription.getText());
                updateString("UPDATE Элементы SET Элементы.примечание = ?\n"
                        + "WHERE (((Элементы.IDЭлементов)=" + specification.getЭлементы_1_IDЭлементов() + "));", textFieldDescription.getText());
                tableViewSpecification.getSelectionModel().selectNext();
                tableViewSpecification.getSelectionModel().clearSelection(tableViewSpecification.getSelectionModel().getSelectedIndex() - 1);
                textFieldDescription.selectAll();
            }
        }
        );
        textFieldCount.setOnAction(
                (event)
                ->
        {
            if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0 && tableViewSpecification.getSelectionModel().getSelectedIndex() != -1)
            {
                ObservableList<Specification> observableList = tableViewSpecification.getSelectionModel().getSelectedItems();
                for (Specification observableList1 : observableList)
                {

                    String num = textFieldCount.getText();
                    observableList1.setДерево_Кол(num);
                    try
                    {
                        updateDouble("UPDATE Дерево SET Дерево.Кол = ?\n"
                                + "WHERE (((Дерево.IDДерева)=" + observableList1.getДерево_IDДерева() + "));", Double.parseDouble(num.replace(",", ".")));
                    }
                    catch (NumberFormatException ex)
                    {
                        updateDouble("UPDATE Дерево SET Дерево.Кол = ?\n"
                                + "WHERE (((Дерево.IDДерева)=" + observableList1.getДерево_IDДерева() + "));", 0);
                    }

                    if (observableList.size() == 1)
                    {
                        tableViewSpecification.getSelectionModel().selectNext();
                    }
                    tableViewSpecification.getSelectionModel().clearSelection(tableViewSpecification.getSelectionModel().getSelectedIndex() - 1);
                    textFieldCount.selectAll();
                }
            }
            else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
            {
                Double num = Double.parseDouble(textFieldCount.getText().replace(",", "."));
                treeView.getTreeItem(treeView.getSelectionModel().getSelectedIndex()).setValue(textFieldElement.getText() + " " + textFieldName.getText() + " " + textFieldCount.getText().replace(",", ".") + textFieldUnit.getText());
                try
                {

                    updateDouble("UPDATE Дерево SET Дерево.Кол = ?\n"
                            + "WHERE (((Дерево.IDДерева)=" + dataElement.get(0).getДерево_IDДерева() + "));", num);
                }
                catch (NumberFormatException ex)
                {

                    updateDouble("UPDATE Дерево SET Дерево.Кол = ?\n"
                            + "WHERE (((Дерево.IDДерева)=" + dataElement.get(0).getДерево_IDДерева() + "));", 0);
                }
            }

        }
        );
        textFieldKTD.setOnAction(
                (event)
                ->
        {

            if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0 && tableViewSpecification.getSelectionModel().getSelectedIndex() != -1)
            {
                specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                specification.setЭлементы_1_КТДЭ(textFieldKTD.getText());

                try
                {
                    updateInteger("UPDATE Элементы SET Элементы.КТДЭ = ?\n"
                            + "WHERE (((Элементы.IDЭлементов)=" + specification.getЭлементы_1_IDЭлементов() + "));", Integer.parseInt(textFieldKTD.getText()));
                }
                catch (NumberFormatException ex)
                {
                    updateInteger("UPDATE Элементы SET Элементы.КТДЭ = ?\n"
                            + "WHERE (((Элементы.IDЭлементов)=" + specification.getЭлементы_1_IDЭлементов() + "));", 0);
                }
            }
            else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
            {
                dataElement.get(0).setЭлементы_1_КТДЭ(textFieldKTD.getText());
                try
                {
                    updateInteger("UPDATE Элементы SET Элементы.КТДЭ = ?\n"
                            + "WHERE (((Элементы.IDЭлементов)=" + dataElement.get(0).getЭлементы_1_IDЭлементов() + "));", Integer.parseInt(textFieldKTD.getText()));
                }
                catch (NumberFormatException ex)
                {
                    updateInteger("UPDATE Элементы SET Элементы.КТДЭ = ?\n"
                            + "WHERE (((Элементы.IDЭлементов)=" + dataElement.get(0).getЭлементы_1_IDЭлементов() + "));", 0);
                }
            }
        }
        );
        textFieldUnit.setOnAction(
                (event)
                ->
        {
            if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0 && tableViewSpecification.getSelectionModel().getSelectedIndex() != -1)
            {
                specification = tableViewSpecification.getSelectionModel().getSelectedItem();
                specification.setЭлементы_1_ЕИЭ(textFieldUnit.getText());
                updateString("UPDATE Элементы SET Элементы.ЕИЭ = ?\n"
                        + "WHERE (((Элементы.IDЭлементов)=" + specification.getЭлементы_1_IDЭлементов() + "));", textFieldUnit.getText());
            }
            else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
            {
                dataElement.get(0).setЭлементы_1_ЕИЭ(textFieldUnit.getText());
                updateString("UPDATE Элементы SET Элементы.ЕИЭ = ?\n"
                        + "WHERE (((Элементы.IDЭлементов)=" + dataElement.get(0).getЭлементы_1_IDЭлементов() + "));", textFieldUnit.getText());
            }

        }
        );
        buttonDeleteElementRow.setOnAction(
                (event)
                ->
        {
            if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0 && !tableViewSpecification.getSelectionModel().isEmpty())
            {
                deleteElementFromBd("DELETE FROM Дерево WHERE IDДерева = ?", tableViewSpecification.getSelectionModel().getSelectedItem());
                dataSpecification.remove(tableViewSpecification.getSelectionModel().getSelectedIndex());
            }
            else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
            {
                deleteElementFromBd("DELETE FROM Дерево WHERE IDДерева = ?", dataElement.get(0));
                TreeItem<String> parent = treeView.getSelectionModel().getSelectedItem().getParent();
                if (parent != null)
                {
                    parent.getChildren().remove(treeView.getSelectionModel().getSelectedItem());
                }
                selectTreeViewItem();
                //treeView.getSelectionModel().select(parent);
            }

        });
        buttonRight.setOnAction(
                (event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else if (tableColumnElement.getCellData(tableViewSpecification.getSelectionModel().getSelectedIndex()) != null)
            {
                String str = tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Обозначение().replaceAll("\"", "\"\"");
                if (str != null)
                {
                    String str1 = labelParent.getText();
                    lastSelectedList.add(str1);
                    labelParent.setText(str);
                    try
                    {
                        //System.out.println(str + "," + comboboxOrder.getValue());
                        getElemnts("SELECT Элементы_1.IDЭлементов, Дерево.Поз, Элементы_1.Обозначение, Элементы_1.Наименование, Элементы_1.примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы.Обозначение, Элементы_1.а, Элементы_1.б, Элементы_1.в, Элементы_1.г, Элементы_1.д, Элементы_1.е, Элементы_1.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы_1.КоэффициентЭ, Материалы.КоэффициентМ, Элементы_1.КТДЭ, Элементы_1.ЕИЭ, Материалы.КТДМ, Элементы_1.Узел\n"
                                + "FROM (Цех RIGHT JOIN ((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) LEFT JOIN ((Элементы AS Элементы_1 LEFT JOIN Материалы ON Элементы_1.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) ON Дерево.Обозначение = Элементы_1.IDЭлементов) ON Цех.IDЦехов = Дерево.IDЦехаЭ) LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + str + "\"))\n"
                                + "ORDER BY Дерево.Поз;");
                    }
                    catch (NullPointerException ex)
                    {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    getWriten("SELECT график.Выписано\n"
                            + "FROM ((элементы LEFT JOIN дерево ON элементы.IDЭлементов = дерево.Родитель) LEFT JOIN график ON дерево.Обозначение = график.Потомок) LEFT JOIN заказы ON график.Родитель = заказы.idЗаказа\n"
                            + "WHERE (((элементы.Обозначение)=\"" + str + "\") AND ((заказы.Заказ)=\"" + comboboxOrder.getValue() + "\")) OR (((элементы.Обозначение)=\"" + str + "\") AND ((заказы.Заказ) Is Null))\n"
                            + "ORDER BY дерево.Поз;");
                    getCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                            + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                            + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных) Is Not Null) AND ((Элементы.Обозначение)=\"" + str + "\"));", true);
                    getParents("SELECT Элементы_1.Обозначение\n"
                            + "FROM (Дерево INNER JOIN Элементы ON Дерево.Обозначение = Элементы.IDЭлементов) INNER JOIN Элементы AS Элементы_1 ON Дерево.Родитель = Элементы_1.IDЭлементов\n"
                            + "WHERE (((Элементы.Обозначение)=\"" + str + "\"));");
                    comboboxElementParents.getEditor().setText(str1);
                }
            }

        }
        );
        buttonLeft.setOnAction(
                (event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else
            {
                String str = comboboxElementParents.getEditor().getText().replaceAll("\"", "\"\"");
                if (comboboxElementParents.getSelectionModel().getSelectedIndex() != -1 & !parentsList.isEmpty() & !lastSelectedList.isEmpty() & lastSelectedList.size() != 1)
                {
                    lastSelectedList.remove(lastSelectedList.size() - 1);
                    labelParent.setText(str);
                    try
                    {
                        getElemnts("SELECT Элементы_1.IDЭлементов, Дерево.Поз, Элементы_1.Обозначение, Элементы_1.Наименование, Элементы_1.примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы.Обозначение, Элементы_1.а, Элементы_1.б, Элементы_1.в, Элементы_1.г, Элементы_1.д, Элементы_1.е, Элементы_1.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы_1.КоэффициентЭ, Материалы.КоэффициентМ, Элементы_1.КТДЭ, Элементы_1.ЕИЭ, Материалы.КТДМ, Элементы_1.Узел\n"
                                + "FROM (Цех RIGHT JOIN ((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) LEFT JOIN ((Элементы AS Элементы_1 LEFT JOIN Материалы ON Элементы_1.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) ON Дерево.Обозначение = Элементы_1.IDЭлементов) ON Цех.IDЦехов = Дерево.IDЦехаЭ) LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + str + "\"))\n"
                                + "ORDER BY Дерево.Поз;");
                    }
                    catch (NullPointerException ex)
                    {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (!str.equals("В работе"))

                    {
                        getWriten("SELECT график.Выписано\n"
                                + "FROM ((элементы LEFT JOIN дерево ON элементы.IDЭлементов = дерево.Родитель) LEFT JOIN график ON дерево.Обозначение = график.Потомок) LEFT JOIN заказы ON график.Родитель = заказы.idЗаказа\n"
                                + "WHERE (((элементы.Обозначение)=\"" + str + "\") AND ((заказы.Заказ)=\"" + comboboxOrder.getValue() + "\")) OR (((элементы.Обозначение)=\"" + str + "\") AND ((заказы.Заказ) Is Null))\n"
                                + "ORDER BY дерево.Поз;");
                    }
                    else
                    {
                        getWriten("SELECT график.Выписано\n"
                                + "FROM ((элементы LEFT JOIN дерево ON элементы.IDЭлементов = дерево.Родитель) LEFT JOIN график ON дерево.Обозначение = график.Потомок) LEFT JOIN заказы ON график.Родитель = заказы.idЗаказа\n"
                                + "WHERE (((элементы.Обозначение)=\"В работе\") AND ((заказы.Заказ)=\"" + comboboxOrder.getValue() + "\")) OR (((элементы.Обозначение)=\"В работе\"))\n"
                                + "ORDER BY дерево.Поз;");
                    }

                    getCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                            + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                            + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных) Is Not Null) AND ((Элементы.Обозначение)=\"" + str + "\"));", true);

                    getParents("SELECT Элементы_1.Обозначение\n"
                            + "FROM (Дерево INNER JOIN Элементы ON Дерево.Обозначение = Элементы.IDЭлементов) INNER JOIN Элементы AS Элементы_1 ON Дерево.Родитель = Элементы_1.IDЭлементов\n"
                            + "WHERE (((Элементы.Обозначение)=\"" + str + "\"));");

                    comboboxElementParents.getEditor().setText(lastSelectedList.get(lastSelectedList.size() - 1));
                }
                else if (comboboxElementParents.getSelectionModel().getSelectedIndex() == -1 & lastSelectedList.size() == 1)
                {
                    lastSelectedList.remove(lastSelectedList.size() - 1);
                    labelParent.setText(str);

                    try
                    {
                        getElemnts("SELECT Элементы_1.IDЭлементов, Дерево.Поз, Элементы_1.Обозначение, Элементы_1.Наименование, Элементы_1.примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы.Обозначение, Элементы_1.а, Элементы_1.б, Элементы_1.в, Элементы_1.г, Элементы_1.д, Элементы_1.е, Элементы_1.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы_1.КоэффициентЭ, Материалы.КоэффициентМ, Элементы_1.КТДЭ, Элементы_1.ЕИЭ, Материалы.КТДМ, Элементы_1.Узел\n"
                                + "FROM (Цех RIGHT JOIN ((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) LEFT JOIN ((Элементы AS Элементы_1 LEFT JOIN Материалы ON Элементы_1.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) ON Дерево.Обозначение = Элементы_1.IDЭлементов) ON Цех.IDЦехов = Дерево.IDЦехаЭ) LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + str + "\"))\n"
                                + "ORDER BY Дерево.Поз;");
                    }
                    catch (NullPointerException ex)
                    {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (!str.equals("В работе"))

                    {
                        getWriten("SELECT график.Выписано\n"
                                + "FROM ((элементы LEFT JOIN дерево ON элементы.IDЭлементов = дерево.Родитель) LEFT JOIN график ON дерево.Обозначение = график.Потомок) LEFT JOIN заказы ON график.Родитель = заказы.idЗаказа\n"
                                + "WHERE (((элементы.Обозначение)=\"" + str + "\") AND ((заказы.Заказ)=\"" + comboboxOrder.getValue() + "\")) OR (((элементы.Обозначение)=\"" + str + "\") AND ((заказы.Заказ) Is Null))\n"
                                + "ORDER BY дерево.Поз;");
                    }
                    else
                    {
                        getWriten("SELECT график.Выписано\n"
                                + "FROM ((элементы LEFT JOIN дерево ON элементы.IDЭлементов = дерево.Родитель) LEFT JOIN график ON дерево.Обозначение = график.Потомок) LEFT JOIN заказы ON график.Родитель = заказы.idЗаказа\n"
                                + "WHERE (((элементы.Обозначение)=\"В работе\") AND ((заказы.Заказ)=\"" + comboboxOrder.getValue() + "\")) OR (((элементы.Обозначение)=\"В работе\"))\n"
                                + "ORDER BY дерево.Поз;");
                    }

                    getCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                            + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                            + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных) Is Not Null) AND ((Элементы.Обозначение)=\"" + str + "\"));", true);

                    getParents("SELECT Элементы_1.Обозначение\n"
                            + "FROM (Дерево INNER JOIN Элементы ON Дерево.Обозначение = Элементы.IDЭлементов) INNER JOIN Элементы AS Элементы_1 ON Дерево.Родитель = Элементы_1.IDЭлементов\n"
                            + "WHERE (((Элементы.Обозначение)=\"" + str + "\"));");

                    comboboxElementParents.getEditor().setText("");
                }
                else if (comboboxElementParents.getSelectionModel().getSelectedIndex() != -1)
                {
                    lastSelectedList.clear();
                    labelParent.setText(str);

                    try
                    {
                        getElemnts("SELECT Элементы_1.IDЭлементов, Дерево.Поз, Элементы_1.Обозначение, Элементы_1.Наименование, Элементы_1.примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы.Обозначение, Элементы_1.а, Элементы_1.б, Элементы_1.в, Элементы_1.г, Элементы_1.д, Элементы_1.е, Элементы_1.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы_1.КоэффициентЭ, Материалы.КоэффициентМ, Элементы_1.КТДЭ, Элементы_1.ЕИЭ, Материалы.КТДМ, Элементы_1.Узел\n"
                                + "FROM (Цех RIGHT JOIN ((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) LEFT JOIN ((Элементы AS Элементы_1 LEFT JOIN Материалы ON Элементы_1.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) ON Дерево.Обозначение = Элементы_1.IDЭлементов) ON Цех.IDЦехов = Дерево.IDЦехаЭ) LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + str + "\"))\n"
                                + "ORDER BY Дерево.Поз;");
                    }
                    catch (NullPointerException ex)
                    {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (!str.equals("В работе"))

                    {
                        getWriten("SELECT график.Выписано\n"
                                + "FROM ((элементы LEFT JOIN дерево ON элементы.IDЭлементов = дерево.Родитель) LEFT JOIN график ON дерево.Обозначение = график.Потомок) LEFT JOIN заказы ON график.Родитель = заказы.idЗаказа\n"
                                + "WHERE (((элементы.Обозначение)=\"" + str + "\") AND ((заказы.Заказ)=\"" + comboboxOrder.getValue() + "\")) OR (((элементы.Обозначение)=\"" + str + "\") AND ((заказы.Заказ) Is Null))\n"
                                + "ORDER BY дерево.Поз;");
                    }
                    else
                    {
                        getWriten("SELECT график.Выписано\n"
                                + "FROM ((элементы LEFT JOIN дерево ON элементы.IDЭлементов = дерево.Родитель) LEFT JOIN график ON дерево.Обозначение = график.Потомок) LEFT JOIN заказы ON график.Родитель = заказы.idЗаказа\n"
                                + "WHERE (((элементы.Обозначение)=\"В работе\") AND ((заказы.Заказ)=\"" + comboboxOrder.getValue() + "\")) OR (((элементы.Обозначение)=\"В работе\"))\n"
                                + "ORDER BY дерево.Поз;");
                    }

                    getCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                            + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                            + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных) Is Not Null) AND ((Элементы.Обозначение)=\"" + str + "\"));", true);

                    getParents("SELECT Элементы_1.Обозначение\n"
                            + "FROM (Дерево INNER JOIN Элементы ON Дерево.Обозначение = Элементы.IDЭлементов) INNER JOIN Элементы AS Элементы_1 ON Дерево.Родитель = Элементы_1.IDЭлементов\n"
                            + "WHERE (((Элементы.Обозначение)=\"" + str + "\"));");

                    comboboxElementParents.getEditor().clear();
                }

            }
        }
        );

        comboboxGuild.setOnShowing(
                (event)
                ->
        {
            if (comboboxGuild.getEditor().getText() != null)
            {
                getGuildElements("SELECT Цех.IDЦехов, Цех.НазваниеЦеха\n"
                        + "FROM Цех\n"
                        + "ORDER BY Цех.НазваниеЦеха;");
            }
            else
            {
                getGuildElements("SELECT Цех.IDЦехов, Цех.НазваниеЦеха\n"
                        + "FROM Цех\n"
                        + "ORDER BY Цех.НазваниеЦеха;");
            }

        }
        );
        comboboxGuild.setOnHiding(
                (event)
                ->
        {
            ObservableList<Specification> observableList = null;

            if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 0)
            {
                observableList = tableViewSpecification.getSelectionModel().getSelectedItems();
                for (Specification observableList1 : observableList)
                {
                    observableList1.setЦех_НазваниеЦеха(comboboxGuild.getEditor().getText());
                    updateGuildElements("UPDATE Дерево SET Дерево.IDЦехаЭ = ?\n"
                            + "WHERE (((Дерево.IDДерева)=" + observableList1.getДерево_IDДерева() + "));");
                }
            }
            else if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
            {
                updateGuildElements("UPDATE Дерево SET Дерево.IDЦехаЭ = ?\n"
                        + "WHERE (((Дерево.IDДерева)=" + dataElement.get(0).getДерево_IDДерева() + "));");
            }

        });
        tableViewCommonMaterial.setOnMousePressed(
                (event)
                ->
        {

            if (tableViewCommonMaterial.getSelectionModel().getSelectedIndex() != -1)
            {
                dataCommonMaterialsValues.clear();
                int index = tableViewCommonMaterial.getSelectionModel().getSelectedIndex();
                commonMaterial = tableViewCommonMaterial.getSelectionModel().getSelectedItem();
                dataCommonMaterialsValues.add(new CommonMaterialsValues(
                        commonMaterial.getВ_Формулы_а(),
                        commonMaterial.getВ_ЭлементыВспомогательные_вспа()
                ));
                dataCommonMaterialsValues.add(new CommonMaterialsValues(
                        commonMaterial.getВ_Формулы_б(),
                        commonMaterial.getВ_ЭлементыВспомогательные_вспб()
                ));
                dataCommonMaterialsValues.add(new CommonMaterialsValues(
                        commonMaterial.getВ_Формулы_в(),
                        commonMaterial.getВ_ЭлементыВспомогательные_вспв()
                ));
                dataCommonMaterialsValues.add(new CommonMaterialsValues(
                        commonMaterial.getВ_Формулы_г(),
                        commonMaterial.getВ_ЭлементыВспомогательные_вспг()
                ));
                dataCommonMaterialsValues.add(new CommonMaterialsValues(
                        commonMaterial.getВ_Формулы_д(),
                        commonMaterial.getВ_ЭлементыВспомогательные_вспд()
                ));
                dataCommonMaterialsValues.add(new CommonMaterialsValues(
                        commonMaterial.getВ_Формулы_е(),
                        commonMaterial.getВ_ЭлементыВспомогательные_вспе()
                ));
                tableViewCommonValues.setEditable(true);
                tableViewCommonValues.setItems(dataCommonMaterialsValues);
                getCommonParameters(tableViewCommonMaterial.getSelectionModel().getSelectedItem());
            }

        }
        );
        tableColumnCommonValue.setOnEditCommit(
                (event)
                ->
        {
            String number = event.getNewValue();
            //dataCommonMaterialsValues.get(tableViewCommonMaterial.getSelectionModel().getSelectedIndex()).setValue(number);
            tableViewCommonValues.getSelectionModel().getSelectedItem().setValue(number);
            switch (event.getTableView().getSelectionModel().getFocusedIndex())
            {
                case 0:
                {
                    tableViewCommonMaterial.getSelectionModel().getSelectedItem().setВ_ЭлементыВспомогательные_вспа(number);
                    break;
                }
                case 1:
                {
                    tableViewCommonMaterial.getSelectionModel().getSelectedItem().setВ_ЭлементыВспомогательные_вспб(number);
                    break;
                }
                case 2:
                {
                    tableViewCommonMaterial.getSelectionModel().getSelectedItem().setВ_ЭлементыВспомогательные_вспв(number);
                    break;
                }
                case 3:
                {
                    tableViewCommonMaterial.getSelectionModel().getSelectedItem().setВ_ЭлементыВспомогательные_вспг(number);
                    break;
                }
                case 4:
                {
                    tableViewCommonMaterial.getSelectionModel().getSelectedItem().setВ_ЭлементыВспомогательные_вспд(number);
                    break;
                }
                case 5:
                {
                    tableViewCommonMaterial.getSelectionModel().getSelectedItem().setВ_ЭлементыВспомогательные_вспе(number);
                    break;
                }
            }

            if (tableColumnCommonParameter.getCellData(tableViewCommonValues.getSelectionModel().getSelectedIndex() + 1) != null && !tableColumnCommonParameter.getCellData(tableViewCommonValues.getSelectionModel().getSelectedIndex() + 1).isEmpty())
            {

                tableViewCommonValues.getSelectionModel().selectNext();
                Platform.runLater(() -> tableViewCommonValues.edit(tableViewCommonValues.getSelectionModel().getSelectedIndex(), tableColumnCommonValue));
            }
            else
            {

                if (tableViewCommonMaterial.getSelectionModel().getSelectedIndex() != -1)
                {

                    for (int i = 0; i < values.length; i++)
                    {
                        values[i] = Double.parseDouble(tableColumnCommonValue.getCellData(i).replace(',', '.'));
                    }
                    int id = tableViewCommonMaterial.getSelectionModel().getSelectedItem().getВ_ЭлементыВспомогательные_IDЭлементовВспомогательных();

                    updateCommonабвгде("UPDATE ЭлементыВспомогательные SET ЭлементыВспомогательные.вспа = ?, ЭлементыВспомогательные.вспб = ?, ЭлементыВспомогательные.вспв = ?, ЭлементыВспомогательные.вспг = ?, ЭлементыВспомогательные.вспд = ?, ЭлементыВспомогательные.вспе = ?\n"
                            + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных)=" + id + "));");
                    try
                    {
                        String result = nf.format(updateCommonResult("UPDATE ЭлементыВспомогательные SET ЭлементыВспомогательные.НРМ = ?\n"
                                + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных)=" + id + "));"));
                        labelCommonResult.setText(result);
                        tableViewCommonMaterial.getSelectionModel().getSelectedItem().setВ_ЭлементыВспомогательные_НРМ(result);
                    }
                    catch (ScriptException ex)
                    {

                    }
                }
            }

        }
        );
        tableColumnMaterialValue.setOnEditCommit(
                (event)
                ->
        {
            int id;
            if (tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
            {
                id = dataElement.get(0).getЭлементы_1_IDЭлементов();
            }
            else
            {
                id = tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_IDЭлементов();
            }
            String number = event.getNewValue();
            tableViewMaterialValues.getSelectionModel().getSelectedItem().setValue(number);
            switch (event.getTableView().getSelectionModel().getFocusedIndex())
            {
                case 0:
                {

                    tableViewSpecification.getSelectionModel().getSelectedItem().setЭлементы_1_а(number);
                    break;
                }
                case 1:
                {
                    tableViewSpecification.getSelectionModel().getSelectedItem().setЭлементы_1_б(number);
                    break;
                }
                case 2:
                {
                    tableViewSpecification.getSelectionModel().getSelectedItem().setЭлементы_1_в(number);
                    break;
                }
                case 3:
                {
                    tableViewSpecification.getSelectionModel().getSelectedItem().setЭлементы_1_г(number);
                    break;
                }
                case 4:
                {
                    tableViewSpecification.getSelectionModel().getSelectedItem().setЭлементы_1_д(number);
                    break;
                }
                case 5:
                {
                    tableViewSpecification.getSelectionModel().getSelectedItem().setЭлементы_1_е(number);
                    break;
                }
            }

            if (tableColumnMaterialParameter.getCellData(tableViewMaterialValues.getSelectionModel().getSelectedIndex() + 1) != null
                    && !tableColumnMaterialParameter.getCellData(tableViewMaterialValues.getSelectionModel().getSelectedIndex() + 1).isEmpty())
            {
                tableViewMaterialValues.getSelectionModel().selectNext();
                Platform.runLater(() -> tableViewMaterialValues.edit(tableViewMaterialValues.getSelectionModel().getSelectedIndex(), tableColumnMaterialValue));
            }
            else
            {
                if (tableViewSpecification.getSelectionModel().getSelectedIndex() != -1 || tabPaneLeft.getSelectionModel().getSelectedIndex() == 1)
                {
                    for (int i = 0; i < values.length; i++)
                    {
                        values[i] = Double.parseDouble(tableColumnMaterialValue.getCellData(i).replace(',', '.'));
                    }
                    updateабвгде("UPDATE Элементы SET Элементы.а = ?, Элементы.б = ?, Элементы.в = ?, Элементы.г = ?, Элементы.д = ?, Элементы.е = ?\n"
                            + "WHERE (((Элементы.IDЭлементов)=\"" + id + "\"));");

                    try
                    {
                        String result = nf.format(updateResult("UPDATE Элементы SET Элементы.НРМ = ?\n"
                                + "WHERE (((Элементы.IDЭлементов)=\"" + id + "\"));"));
                        labelMaterialResult.setText(result);
                        tableViewSpecification.getSelectionModel().getSelectedItem().setЭлементы_1_НРМ(result);
                    }
                    catch (ScriptException ex)
                    {

                    }
                }
            }
        }
        );
        tableViewSpecification.setOnMousePressed(
                (MouseEvent event)
                ->
        {
            if (tableViewSpecification.getSelectionModel().getSelectedIndex() != -1)
            {
                dataCommonMaterialsValues.clear();
                dataMaterialsValues.clear();

                dataMaterialsValues.add(new MaterialsValues(
                        tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_а(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_а()
                ));
                dataMaterialsValues.add(new MaterialsValues(
                        tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_б(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_б()
                ));
                dataMaterialsValues.add(new MaterialsValues(
                        tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_в(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_в()
                ));
                dataMaterialsValues.add(new MaterialsValues(
                        tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_г(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_г()
                ));
                dataMaterialsValues.add(new MaterialsValues(
                        tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_д(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_д()
                ));
                dataMaterialsValues.add(new MaterialsValues(
                        tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_е(), tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_е()
                ));
                tableViewMaterialValues.setEditable(true);
                tableColumnMaterialParameter.setEditable(false);
                tableViewCommonMaterial.setEditable(true);
                tableViewCommonMaterial.setEditable(false);
                if (dataSpecificationCommonMaterials != null)
                {
                    tableViewCommonMaterial.setItems(commonMaterialsFilter(dataSpecificationCommonMaterials));
                }
                getParameters(tableViewSpecification.getSelectionModel().getSelectedItem());
                clearCommonParameters();
            }
        }
        );
        buttonFinde.setOnAction(
                (event)
                ->
        {
            String str = comboboxElement.getEditor().getText().replaceAll("\"", "\"\"");
            labelParent.setText(str);

            try
            {
                getElemnts("SELECT Элементы_1.IDЭлементов, Дерево.Поз, Элементы_1.Обозначение, Элементы_1.Наименование, Элементы_1.примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы.Обозначение, Элементы_1.а, Элементы_1.б, Элементы_1.в, Элементы_1.г, Элементы_1.д, Элементы_1.е, Элементы_1.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы_1.КоэффициентЭ, Материалы.КоэффициентМ, Элементы_1.КТДЭ, Элементы_1.ЕИЭ, Материалы.КТДМ, Элементы_1.Узел\n"
                        + "FROM (Цех RIGHT JOIN ((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) LEFT JOIN ((Элементы AS Элементы_1 LEFT JOIN Материалы ON Элементы_1.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) ON Дерево.Обозначение = Элементы_1.IDЭлементов) ON Цех.IDЦехов = Дерево.IDЦехаЭ) LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов\n"
                        + "WHERE (((Элементы.Обозначение)=\"" + str + "\"))\n"
                        + "ORDER BY Дерево.Поз;");
            }
            catch (NullPointerException ex)
            {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            getWriten("SELECT график.Выписано\n"
                    + "FROM ((элементы LEFT JOIN дерево ON элементы.IDЭлементов = дерево.Родитель) LEFT JOIN график ON дерево.Обозначение = график.Потомок) LEFT JOIN заказы ON график.Родитель = заказы.idЗаказа\n"
                    + "WHERE (((элементы.Обозначение)=\"" + str + "\") AND ((заказы.Заказ)=\"" + comboboxOrder.getValue() + "\")) OR (((элементы.Обозначение)=\"" + str + "\") AND ((заказы.Заказ) Is Null))\n"
                    + "ORDER BY дерево.Поз;");

            tableViewSpecification.getStylesheets().add("style/style.css");
            getCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                    + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                    + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных) Is Not Null) AND ((Элементы.Обозначение)=\"" + str + "\"));", true);
            getParents("SELECT Элементы_1.Обозначение\n"
                    + "FROM (Дерево INNER JOIN Элементы ON Дерево.Обозначение = Элементы.IDЭлементов) INNER JOIN Элементы AS Элементы_1 ON Дерево.Родитель = Элементы_1.IDЭлементов\n"
                    + "WHERE (((Элементы.Обозначение)=\"" + str + "\"));");
            comboboxElementParents.getEditor().clear();

        }
        );
        buttonUpdate.setOnAction(
                (event)
                ->
        {

            String str = labelParent.getText().replaceAll("\"", "\"\"");
            try
            {
                getElemnts("SELECT Элементы_1.IDЭлементов, Дерево.Поз, Элементы_1.Обозначение, Элементы_1.Наименование, Элементы_1.примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы.Обозначение, Элементы_1.а, Элементы_1.б, Элементы_1.в, Элементы_1.г, Элементы_1.д, Элементы_1.е, Элементы_1.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы_1.КоэффициентЭ, Материалы.КоэффициентМ, Элементы_1.КТДЭ, Элементы_1.ЕИЭ, Материалы.КТДМ, Элементы_1.Узел\n"
                        + "FROM (Цех RIGHT JOIN ((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) LEFT JOIN ((Элементы AS Элементы_1 LEFT JOIN Материалы ON Элементы_1.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) ON Дерево.Обозначение = Элементы_1.IDЭлементов) ON Цех.IDЦехов = Дерево.IDЦехаЭ) LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов\n"
                        + "WHERE (((Элементы.Обозначение)=\"" + str + "\"))\n"
                        + "ORDER BY Дерево.Поз;");
            }
            catch (NullPointerException ex)
            {

            }
            getWriten("SELECT график.Выписано\n"
                    + "FROM ((элементы LEFT JOIN дерево ON элементы.IDЭлементов = дерево.Родитель) LEFT JOIN график ON дерево.Обозначение = график.Потомок) LEFT JOIN заказы ON график.Родитель = заказы.idЗаказа\n"
                    + "WHERE (((элементы.Обозначение)=\"" + str + "\") AND ((заказы.Заказ)=\"" + comboboxOrder.getValue() + "\")) OR (((элементы.Обозначение)=\"" + str + "\") AND ((заказы.Заказ) Is Null))\n"
                    + "ORDER BY дерево.Поз;");

            getCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                    + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                    + "WHERE (((Элементы.Обозначение)=\"" + str + "\"));", true);

        }
        );
        buttonHomeSpecification.setOnAction(
                (event)
                ->
        {

            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else
            {
                labelParent.setText("В работе");

                try
                {
                    getElemnts("SELECT Элементы_1.IDЭлементов, Дерево.Поз, Элементы_1.Обозначение, Элементы_1.Наименование, Элементы_1.примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы.Обозначение, Элементы_1.а, Элементы_1.б, Элементы_1.в, Элементы_1.г, Элементы_1.д, Элементы_1.е, Элементы_1.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы_1.КоэффициентЭ, Материалы.КоэффициентМ, Элементы_1.КТДЭ, Элементы_1.ЕИЭ, Материалы.КТДМ, Элементы_1.Узел\n"
                            + "FROM (Цех RIGHT JOIN ((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) LEFT JOIN ((Элементы AS Элементы_1 LEFT JOIN Материалы ON Элементы_1.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) ON Дерево.Обозначение = Элементы_1.IDЭлементов) ON Цех.IDЦехов = Дерево.IDЦехаЭ) LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов\n"
                            + "WHERE (((Элементы.Обозначение)=\"В работе\"))\n"
                            + "ORDER BY Дерево.Поз;");

                    getWriten("SELECT график.Выписано\n"
                            + "FROM ((элементы LEFT JOIN дерево ON элементы.IDЭлементов = дерево.Родитель) LEFT JOIN график ON дерево.Обозначение = график.Потомок) LEFT JOIN заказы ON график.Родитель = заказы.idЗаказа\n"
                            + "WHERE (((элементы.Обозначение)=\"В работе\") AND ((заказы.Заказ)=\"" + comboboxOrder.getValue() + "\")) OR (((элементы.Обозначение)=\"В работе\"))\n"
                            + "ORDER BY дерево.Поз;");

                    getCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                            + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                            + "WHERE (((ЭлементыВспомогательные.IDЭлементовВспомогательных) Is Not Null) AND ((Элементы.Обозначение)=\"В работе\"));", true);
                    getParents("SELECT Элементы_1.Обозначение\n"
                            + "FROM (Дерево INNER JOIN Элементы ON Дерево.Обозначение = Элементы.IDЭлементов) INNER JOIN Элементы AS Элементы_1 ON Дерево.Родитель = Элементы_1.IDЭлементов\n"
                            + "WHERE (((Элементы.Обозначение)=\"В работе\"));");
                }
                catch (NullPointerException ex)
                {
                    if (!DBConnection.connect())
                    {
                        bdNotConnected();
                    }
                }

                comboboxElementParents.getEditor().clear();
            }
        }
        );
        buttonHomeTree.setOnMousePressed((event)
                ->
        {
            getTree("SELECT concat_ws(\" \",Элементы_1.Обозначение , Элементы_1.Наименование) AS Обозначение, Дерево.Кол, Элементы_1.ЕИЭ\n"
                    + "FROM (Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов\n"
                    + "WHERE ((Элементы.Обозначение)=\"В работе\")\n"
                    + "ORDER BY Дерево.Поз;", "В работе");
        });

        comboboxElement.setOnShowing(
                (event)
                ->
        {
            if (!comboboxElement.getEditor().getText().isEmpty())
            {
                getStringObservableList("SELECT Элементы.Обозначение\n"
                        + "FROM Элементы\n"
                        + "WHERE (((Элементы.Обозначение) Like \"%" + comboboxElement.getEditor().getText().replaceAll("\"", "\"\"") + "%\"))\n"
                        + "ORDER BY Элементы.Обозначение;", elementsList, "Обозначение");
            }
        }
        );

        comboboxTree.setOnShowing(
                (event)
                ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else if (!comboboxElement.getEditor().getText().isEmpty())
            {
                getStringObservableList("SELECT Элементы.Обозначение\n"
                        + "FROM Элементы\n"
                        + "WHERE (((Элементы.Обозначение) Like \"%" + comboboxTree.getEditor().getText().replaceAll("\"", "\"\"") + "%\"))\n"
                        + "ORDER BY Элементы.Обозначение;", elementsList, "Обозначение");
            }

        }
        );

        //anchorePaneMain
        checkBoxWriten.setOnAction((event) ->
        {
            if (checkBoxWriten.isSelected())
            {
                tableColumnWritten.setVisible(true);
                String str = labelParent.getText().replaceAll("\"", "\"\"");
                try
                {
                    getElemnts("SELECT Элементы_1.IDЭлементов, Дерево.Поз, Элементы_1.Обозначение, Элементы_1.Наименование, Элементы_1.примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы.Обозначение, Элементы_1.а, Элементы_1.б, Элементы_1.в, Элементы_1.г, Элементы_1.д, Элементы_1.е, Элементы_1.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы_1.КоэффициентЭ, Материалы.КоэффициентМ, Элементы_1.КТДЭ, Элементы_1.ЕИЭ, Материалы.КТДМ, Элементы_1.Узел\n"
                            + "FROM (Цех RIGHT JOIN ((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) LEFT JOIN ((Элементы AS Элементы_1 LEFT JOIN Материалы ON Элементы_1.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) ON Дерево.Обозначение = Элементы_1.IDЭлементов) ON Цех.IDЦехов = Дерево.IDЦехаЭ) LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов\n"
                            + "WHERE (((Элементы.Обозначение)=\"" + str + "\"))\n"
                            + "ORDER BY Дерево.Поз;");
                }
                catch (NullPointerException ex)
                {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                getWriten("SELECT график.Выписано\n"
                        + "FROM (((элементы LEFT JOIN дерево ON элементы.IDЭлементов = дерево.Родитель) LEFT JOIN элементы AS элементы_1 ON дерево.Обозначение = элементы_1.IDЭлементов) LEFT JOIN график ON элементы_1.IDЭлементов = график.Потомок) LEFT JOIN заказы ON график.Родитель = заказы.idЗаказа\n"
                        + "WHERE (((элементы.Обозначение)=\"" + str + "\") AND ((заказы.Заказ)=\"" + comboboxOrder.getValue() + "\")) OR (((элементы.Обозначение)=\"" + str + "\") AND ((заказы.Заказ) Is Null))\n"
                        + "ORDER BY дерево.Поз;");

                getCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                        + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                        + "WHERE (((Элементы.Обозначение)=\"" + str + "\"));", true);
            }
            else
            {
                tableColumnWritten.setVisible(false);
            }

        });
        tableColumnWritten.setOnEditCommit((event) ->
        {
            if (!DBConnection.connect())
            {
                bdNotConnected();
            }
            else
            {
                String str1 = comboboxOrder.getValue();
                int int1 = tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_IDЭлементов();
                updateSchedule("UPDATE Заказы INNER JOIN График ON Заказы.idЗаказа = График.Родитель SET График.Выписано = \"" + event.getNewValue() + "\"\n"
                        + "WHERE (((Заказы.Заказ)=?) AND ((График.Потомок)=?));", str1, int1);

                //treeTableView.getSelectionModel().getSelectedItem().getValue().setВыписано(event.getNewValue());
            }
        });
    }

    ObservableList<SpecificationCommonMaterials> commonMaterialsFilter(ObservableList<SpecificationCommonMaterials> observableList)
    {
        ObservableList<SpecificationCommonMaterials> observableListFiltered = FXCollections.observableArrayList();
        int index = 0;
        rowIndex = new ArrayList();
        for (SpecificationCommonMaterials commonMaterials : observableList)
        {
            int str1 = commonMaterials.getВ_Дерево_Обозначение();
            int str2 = dataSpecification.get(tableViewSpecification.getSelectionModel().getSelectedIndex()).getЭлементы_1_IDЭлементов();

            if (str1 == str2)
            {
                rowIndex.add(index);
                observableListFiltered.add(commonMaterials);
            }
            index++;
        }
        return observableListFiltered;
    }

    private void getElemnts(String sql) throws NullPointerException
    {
        dataSpecification.clear();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                dataSpecification.add(new Specification(
                        Integer.toString(resultSet.getInt(Дерево_Поз + 1)),
                        resultSet.getString(Элементы_1_Обозначение + 1),
                        resultSet.getString(Элементы_1_Наименование + 1),
                        resultSet.getString(Элементы_1_Примечание + 1),
                        nf.format(resultSet.getDouble(Дерево_Кол + 1)),
                        resultSet.getInt(Элементы_1_IDЭлементов + 1),
                        Integer.toString(resultSet.getInt(Материалы_IDМатериалов + 1)),
                        resultSet.getString(Материалы_Материал + 1),
                        Integer.toString(resultSet.getInt(Формулы_IDФормул + 1)),
                        resultSet.getString(Элементы_Обозначение + 1),
                        nf.format(resultSet.getDouble(Элементы_1_а + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_б + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_в + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_г + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_д + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_е + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_НРМ + 1)),
                        resultSet.getString(Материалы_ЕИМ + 1),
                        resultSet.getString(Формулы_ФормулаРасчета + 1),
                        resultSet.getString(Формулы_а + 1),
                        resultSet.getString(Формулы_б + 1),
                        resultSet.getString(Формулы_в + 1),
                        resultSet.getString(Формулы_г + 1),
                        resultSet.getString(Формулы_д + 1),
                        resultSet.getString(Формулы_е + 1),
                        resultSet.getString(Цех_НазваниеЦеха + 1),
                        resultSet.getInt(Дерево_IDДерева + 1),
                        resultSet.getString(Цех_1_НазваниеЦеха + 1),
                        nf.format(resultSet.getDouble(Элементы_1_КоэффициентЭ + 1)),
                        nf.format(resultSet.getDouble(Материалы_КоэффициентМ + 1)),
                        Integer.toString(resultSet.getInt(Элементы_1_КТДЭ + 1)),
                        resultSet.getString(Элементы_1_ЕИЭ + 1),
                        Integer.toString(resultSet.getInt(Материалы_КТДМ + 1)),
                        resultSet.getBoolean(Элементы_1_Узел + 1),
                        ""
                ));

            }
            resultSet.close();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        finally
        {
            if (connection == null)
            {
                bdNotConnected();
            }
            else
            {
                try
                {
                    statement.close();
                }
                catch (SQLException ex)
                {

                }
            }

        }
    }

    private ObservableList<Specification> getElemnt(String sql)
    {
        try
        {
            dataElement.clear();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                dataElement.add(new Specification(
                        Integer.toString(resultSet.getInt(Дерево_Поз + 1)),
                        resultSet.getString(Элементы_1_Обозначение + 1),
                        resultSet.getString(Элементы_1_Наименование + 1),
                        resultSet.getString(Элементы_1_Примечание + 1),
                        nf.format(resultSet.getDouble(Дерево_Кол + 1)),
                        resultSet.getInt(Элементы_1_IDЭлементов + 1),
                        Integer.toString(resultSet.getInt(Материалы_IDМатериалов + 1)),
                        resultSet.getString(Материалы_Материал + 1),
                        Integer.toString(resultSet.getInt(Формулы_IDФормул + 1)),
                        resultSet.getString(Элементы_Обозначение + 1),
                        nf.format(resultSet.getDouble(Элементы_1_а + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_б + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_в + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_г + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_д + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_е + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_НРМ + 1)),
                        resultSet.getString(Материалы_ЕИМ + 1),
                        resultSet.getString(Формулы_ФормулаРасчета + 1),
                        resultSet.getString(Формулы_а + 1),
                        resultSet.getString(Формулы_б + 1),
                        resultSet.getString(Формулы_в + 1),
                        resultSet.getString(Формулы_г + 1),
                        resultSet.getString(Формулы_д + 1),
                        resultSet.getString(Формулы_е + 1),
                        resultSet.getString(Цех_НазваниеЦеха + 1),
                        resultSet.getInt(Дерево_IDДерева + 1),
                        resultSet.getString(Цех_1_НазваниеЦеха + 1),
                        nf.format(resultSet.getDouble(Элементы_1_КоэффициентЭ + 1)),
                        nf.format(resultSet.getDouble(Материалы_КоэффициентМ + 1)),
                        Integer.toString(resultSet.getInt(Элементы_1_КТДЭ + 1)),
                        resultSet.getString(Элементы_1_ЕИЭ + 1),
                        Integer.toString(resultSet.getInt(Материалы_КТДМ + 1)),
                        resultSet.getBoolean(Элементы_1_Узел + 1),
                        ""
                ));

            }
            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }

        }
        return dataElement;
    }

    private void getCommonMaterial(String sql, boolean clear) throws NullPointerException
    {
        if (clear)
        {

            dataSpecificationCommonMaterials.clear();
        }
        try
        {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                dataSpecificationCommonMaterials.add(new SpecificationCommonMaterials(
                        resultSet.getInt(В_Дерево_Обозначение + 1),
                        resultSet.getInt(В_ЭлементыВспомогательные_IDЭлементовВспомогательных + 1),
                        resultSet.getString(В_Вспомогательные_Вспомогательный + 1),
                        nf.format(resultSet.getDouble(В_ЭлементыВспомогательные_НРМ + 1)),
                        resultSet.getString(В_Вспомогательные_ЕИВ + 1),
                        resultSet.getString(В_Цех_НазваниеЦеха + 1),
                        resultSet.getString(В_Формулы_ФормулаРасчета + 1),
                        resultSet.getString(В_Формулы_а + 1),
                        nf.format(resultSet.getDouble(В_ЭлементыВспомогательные_вспа + 1)),
                        resultSet.getString(В_Формулы_б + 1),
                        nf.format(resultSet.getDouble(В_ЭлементыВспомогательные_вспб + 1)),
                        resultSet.getString(В_Формулы_в + 1),
                        nf.format(resultSet.getDouble(В_ЭлементыВспомогательные_вспв + 1)),
                        resultSet.getString(В_Формулы_г + 1),
                        nf.format(resultSet.getDouble(В_ЭлементыВспомогательные_вспг + 1)),
                        resultSet.getString(В_Формулы_д + 1),
                        nf.format(resultSet.getDouble(В_ЭлементыВспомогательные_вспд + 1)),
                        resultSet.getString(В_Формулы_е + 1),
                        nf.format(resultSet.getDouble(В_ЭлементыВспомогательные_вспе + 1)),
                        nf.format(resultSet.getDouble(В_Вспомогательные_КоэффициентВМ + 1)),
                        nf.format(resultSet.getDouble(В_ЭлементыВспомогательные_КоэфВЭ + 1)),
                        resultSet.getInt(В_Вспомогательные_IDВспомогательных + 1),
                        resultSet.getString(В_Вспомогательные_КТДВ + 1)
                ));
            }

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {
            //
        }/*
        catch (NullPointerException ex)
        {
            //
        }*/
        finally
        {

            if (connection == null)
            {
                bdNotConnected();
            }
            else
            {
                try
                {
                    statement.close();
                }
                catch (SQLException ex)
                {

                }
            }

        }
    }

    private void getParameters(Specification specification)
    {
        textFieldPosition.setText(specification.getДерево_Поз());
        textFieldElement.setText(specification.getЭлементы_1_Обозначение());
        textFieldName.setText(specification.getЭлементы_1_Наименование());
        textFieldCount.setText(specification.getДерево_Кол());
        textFieldDescription.setText(specification.getЭлементы_1_Примечание());
        textFieldUnit.setText(specification.getЭлементы_1_ЕИЭ());
        textFieldKTD.setText(specification.getЭлементы_1_КТДЭ());

        comboboxMaterial.getEditor().setText(specification.getМатериалы_Материал());
        comboboxGuild.getEditor().setText(specification.getЦех_НазваниеЦеха());
        textFieldKTDM.setText(specification.getМатериалы_КТДМ());
        if (specification.getФормулы_ФормулаРасчета() != null)
        {
            labelMaterialFormula.setText(specification.getФормулы_ФормулаРасчета() + "*" + specification.getМатериалы_КоэффициентМ() + "*" + specification.getЭлементы_1_КоэффициентЭ());
        }
        else
        {
            labelMaterialFormula.setText(specification.getФормулы_ФормулаРасчета());
        }

        labelMaterialResult.setText(specification.getЭлементы_1_НРМ());
        labelMaterialUnit.setText(specification.getМатериалы_ЕИМ());
        comboboxGuildMaterial.getEditor().setText(specification.getЦех_1_НазваниеЦеха());
        textFieldСoefficientM.setText(specification.getМатериалы_КоэффициентМ());
        textFieldСoefficientE.setText(specification.getЭлементы_1_КоэффициентЭ());
        checkBoxNode.setSelected(specification.getЭлементы_1_Узел());

        //jfxTextFieldMaterial.setText(specification.getМатериалы_Материал());
    }

    private void getCommonParameters(SpecificationCommonMaterials specificationCommonMaterials)
    {

        comboboxCommonMaterial.getEditor().setText(tableColumnCommon.getCellData(tableViewCommonMaterial.getSelectionModel().getSelectedIndex()));
        comboboxGuildCommonMaterial.getEditor().setText(tableColumnCommonGuild.getCellData(tableViewCommonMaterial.getSelectionModel().getSelectedIndex()));
        if (specificationCommonMaterials.getВ_Формулы_ФормулаРасчета() != null)
        {
            labelCommonFormula.setText(specificationCommonMaterials.getВ_Формулы_ФормулаРасчета() + "*" + specificationCommonMaterials.getВ_Вспомогательные_КоэффициентВМ() + "*" + specificationCommonMaterials.getВ_ЭлементыВспомогательные_КоэфВЭ());
        }
        else
        {
            labelCommonFormula.setText(specificationCommonMaterials.getВ_Формулы_ФормулаРасчета());
        }
        labelCommonResult.setText(specificationCommonMaterials.getВ_ЭлементыВспомогательные_НРМ());
        labelCommonUnit.setText(specificationCommonMaterials.getВ_Вспомогательные_ЕИВ());
        textFieldKTDC.setText(specificationCommonMaterials.getВ_Вспомогательные_КТДВ());
        textFieldСoefficientC.setText(specificationCommonMaterials.getВ_Вспомогательные_КоэффициентВМ());
        textFieldСoefficientCE.setText(specificationCommonMaterials.getВ_ЭлементыВспомогательные_КоэфВЭ());

    }

    private void clearCommonParameters()
    {

        comboboxCommonMaterial.getEditor().clear();
        comboboxGuildCommonMaterial.getEditor().clear();

        labelCommonFormula.setText("");
        labelCommonResult.setText("");
        labelCommonUnit.setText("");
        textFieldKTDC.setText("");
        textFieldСoefficientC.setText("");
        textFieldСoefficientCE.setText("");

    }

    private void getGuildElements(String sql)
    {
        try
        {
            guildList.clear();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
//              resultSet.getInt("IDЦехов")               
                guildList.add(resultSet.getString("НазваниеЦеха"));
            }
            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }

    }

    private void getParents(String sql) throws NullPointerException
    {
        parentsList.clear();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                parentsList.add(resultSet.getString("Обозначение"));
            }

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (connection == null)
            {
                bdNotConnected();
            }
            else
            {
                try
                {
                    statement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }

    }

    private void getStringObservableList(String sql, ObservableList observableList, String str)
    {
        try
        {
            observableList.clear();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                observableList.add(resultSet.getString(str));
            }

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }

    }

    private void findeElementsReplace(String sql, ObservableList<String> observableList1)
    {
        try
        {
            observableList1.clear();
            idReplaceList.clear();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                idReplaceList.add(resultSet.getInt("IDЭлементов"));
                observableList1.add(resultSet.getString("Обозначение"));

            }

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }

    }

    private void findeElementsReplaced(String sql, ObservableList<String> observableList1)
    {
        try
        {
            observableList1.clear();
            idReplacedList.clear();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                idReplacedList.add(resultSet.getInt("IDЭлементов"));
                observableList1.add(resultSet.getString("Обозначение"));
            }
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {
        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }

    }

    private void updateабвгде(String sql)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < values.length; i++)
            {
                preparedStatement.setDouble(i + 1, values[i]);
            }

            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }

    }

    private double updateResult(String sql) throws ScriptException
    {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        engine.put("а", values[0]);
        engine.put("б", values[1]);
        engine.put("в", values[2]);
        engine.put("г", values[3]);
        engine.put("д", values[4]);
        engine.put("е", values[5]);
        //String formula = labelMaterialFormula.getText();
        String formula = tableViewSpecification.getSelectionModel().getSelectedItem().getФормулы_ФормулаРасчета();
        double result;
        try
        {
            result = Double.parseDouble(engine.eval(formula).toString()) * Double.parseDouble(textFieldСoefficientM.getText().replace(',', '.')) * Double.parseDouble(textFieldСoefficientE.getText().replace(',', '.'));
        }
        catch (NullPointerException ex)
        {
            result = 0;
        }
        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, result);
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
        return result;
    }

    private void updateCommonабвгде(String sql)
    {
        try
        {

            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < values.length; i++)
            {
                preparedStatement.setDouble(i + 1, values[i]);
            }
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private double updateCommonResult(String sql) throws ScriptException
    {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        engine.put("а", values[0]);
        engine.put("б", values[1]);
        engine.put("в", values[2]);
        engine.put("г", values[3]);
        engine.put("д", values[4]);
        engine.put("е", values[5]);
        String formula = tableViewCommonMaterial.getSelectionModel().getSelectedItem().getВ_Формулы_ФормулаРасчета();
        //String formula = labelCommonFormula.getText();
        double result;
        try
        {
            result = Double.parseDouble(engine.eval(formula).toString()) * Double.parseDouble(textFieldСoefficientC.getText().replace(',', '.')) * Double.parseDouble(textFieldСoefficientCE.getText().replace(',', '.'));
        }
        catch (NullPointerException ex)
        {
            result = 0;
        }
        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, result);
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
        return result;
    }

    private void updateString(String sql, String str)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, str);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next())
            {
                id = resultSet.getInt(1);
            }
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {
            id = getId("SELECT Элементы.IDЭлементов\n"
                    + "FROM Элементы\n"
                    + "WHERE (((Элементы.Обозначение)=\"" + str + "\"));", "IDЭлементов");
        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void updateInteger(String sql, int number)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            preparedStatement.executeUpdate();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void updateDouble(String sql, double number)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, number);
            preparedStatement.executeUpdate();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private Specification addRowElement(String sql)
    {
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                for (int i = 1; i <= 34; i++)
                {
                    specification = new Specification(
                            Integer.toString(resultSet.getInt(Дерево_Поз + 1)),
                            resultSet.getString(Элементы_1_Обозначение + 1),
                            resultSet.getString(Элементы_1_Наименование + 1),
                            resultSet.getString(Элементы_1_Примечание + 1),
                            nf.format(resultSet.getDouble(Дерево_Кол + 1)),
                            resultSet.getInt(Элементы_1_IDЭлементов + 1),
                            Integer.toString(resultSet.getInt(Материалы_IDМатериалов + 1)),
                            resultSet.getString(Материалы_Материал + 1),
                            Integer.toString(resultSet.getInt(Формулы_IDФормул + 1)),
                            resultSet.getString(Элементы_Обозначение + 1),
                            nf.format(resultSet.getDouble(Элементы_1_а + 1)),
                            nf.format(resultSet.getDouble(Элементы_1_б + 1)),
                            nf.format(resultSet.getDouble(Элементы_1_в + 1)),
                            nf.format(resultSet.getDouble(Элементы_1_г + 1)),
                            nf.format(resultSet.getDouble(Элементы_1_д + 1)),
                            nf.format(resultSet.getDouble(Элементы_1_е + 1)),
                            nf.format(resultSet.getDouble(Элементы_1_НРМ + 1)),
                            resultSet.getString(Материалы_ЕИМ + 1),
                            resultSet.getString(Формулы_ФормулаРасчета + 1),
                            resultSet.getString(Формулы_а + 1),
                            resultSet.getString(Формулы_б + 1),
                            resultSet.getString(Формулы_в + 1),
                            resultSet.getString(Формулы_г + 1),
                            resultSet.getString(Формулы_д + 1),
                            resultSet.getString(Формулы_е + 1),
                            resultSet.getString(Цех_НазваниеЦеха + 1),
                            resultSet.getInt(Дерево_IDДерева + 1),
                            resultSet.getString(Цех_1_НазваниеЦеха + 1),
                            nf.format(resultSet.getDouble(Элементы_1_КоэффициентЭ + 1)),
                            nf.format(resultSet.getDouble(Материалы_КоэффициентМ + 1)),
                            Integer.toString(resultSet.getInt(Элементы_1_КТДЭ + 1)),
                            resultSet.getString(Элементы_1_ЕИЭ + 1),
                            Integer.toString(resultSet.getInt(Материалы_КТДМ + 1)),
                            resultSet.getBoolean(Элементы_1_Узел + 1),
                            ""
                    );
                }

            }
            resultSet.close();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
        return specification;
    }

    private void addChildToParent(String sql, int num1, int num2)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, num1);
            preparedStatement.setInt(2, num2);
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private int getId(String sql, String column)
    {
        int Id = 0;
        Statement statement = null;
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                Id = resultSet.getInt(column);
            }
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {

            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
        return Id;
    }

    private void deleteElementFromBd(String sql, Specification specification)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, specification.getДерево_IDДерева());
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void getGuildMaterials(String sql)
    {
        guildMaterialsList.clear();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                guildMaterialsList.add(resultSet.getString("НазваниеЦеха"));
            }
            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
    }

    private void updateGuildMaterials(String sql, Integer number)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);

            preparedStatement.executeUpdate();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void getMaterials(String sql)
    {
        materialsList.clear();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                materialsList.add(resultSet.getString("Материал"));
            }

            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
    }

    private void updateMaterial(String sql, Integer number)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            preparedStatement.executeUpdate();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void updateGuildElements(String sql)
    {
        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, getId("SELECT Цех.IDЦехов\n"
                    + "FROM Цех\n"
                    + "WHERE (((Цех.НазваниеЦеха)=\"" + comboboxGuild.getEditor().getText() + "\"));", "IDЦехов"));

            preparedStatement.executeUpdate();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void getCommonMaterials(String sql)
    {
        commonMaterialsList.clear();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
//                resultSet.getInt("IDВспомогательных")
                commonMaterialsList.add(resultSet.getString("Вспомогательный"));
            }
            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
    }

    private void updateCommonMaterial(String sql)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, getId("SELECT Вспомогательные.IDВспомогательных\n"
                    + "FROM Вспомогательные\n"
                    + "WHERE (((Вспомогательные.Вспомогательный)=\"" + comboboxCommonMaterial.getSelectionModel().getSelectedItem().toString().replaceAll("\"", "\"\"") + "\"));", "IDВспомогательных"));
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private SpecificationCommonMaterials getRowCommonMaterial(String sql)
    {
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {

                commonMaterial = new SpecificationCommonMaterials(
                        resultSet.getInt(В_Дерево_Обозначение + 1),
                        resultSet.getInt(В_ЭлементыВспомогательные_IDЭлементовВспомогательных + 1),
                        resultSet.getString(В_Вспомогательные_Вспомогательный + 1),
                        nf.format(resultSet.getDouble(В_ЭлементыВспомогательные_НРМ + 1)),
                        resultSet.getString(В_Вспомогательные_ЕИВ + 1),
                        resultSet.getString(В_Цех_НазваниеЦеха + 1),
                        resultSet.getString(В_Формулы_ФормулаРасчета + 1),
                        resultSet.getString(В_Формулы_а + 1),
                        nf.format(resultSet.getDouble(В_ЭлементыВспомогательные_вспа + 1)),
                        resultSet.getString(В_Формулы_б + 1),
                        nf.format(resultSet.getDouble(В_ЭлементыВспомогательные_вспб + 1)),
                        resultSet.getString(В_Формулы_в + 1),
                        nf.format(resultSet.getDouble(В_ЭлементыВспомогательные_вспв + 1)),
                        resultSet.getString(В_Формулы_г + 1),
                        nf.format(resultSet.getDouble(В_ЭлементыВспомогательные_вспг + 1)),
                        resultSet.getString(В_Формулы_д + 1),
                        nf.format(resultSet.getDouble(В_ЭлементыВспомогательные_вспд + 1)),
                        resultSet.getString(В_Формулы_е + 1),
                        nf.format(resultSet.getDouble(В_ЭлементыВспомогательные_вспе + 1)),
                        nf.format(resultSet.getDouble(В_Вспомогательные_КоэффициентВМ + 1)),
                        nf.format(resultSet.getDouble(В_ЭлементыВспомогательные_КоэфВЭ + 1)),
                        resultSet.getInt(В_Вспомогательные_IDВспомогательных + 1),
                        resultSet.getString(В_Вспомогательные_КТДВ + 1)
                );
            }
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {
            }
        }
        return commonMaterial;

    }

    private void getGuildCommonMaterials(String sql)
    {
        guildCommonMaterialsList.clear();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
//                resultSet.getInt("IDЦехов")
                guildCommonMaterialsList.add(resultSet.getString("НазваниеЦеха"));
            }
            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
    }

    private void updateGuildCommonMaterial(String sql)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, getId("SELECT Цех.IDЦехов\n"
                    + "FROM Цех\n"
                    + "WHERE (((Цех.НазваниеЦеха)=\"" + comboboxGuildCommonMaterial.getEditor().getText() + "\"));", "IDЦехов"));

            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void updateУзел(String sql, Specification specification)
    {

        try
        {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, specification.getЭлементы_1_IDЭлементов());
            preparedStatement.setDouble(2, Double.parseDouble(specification.getДерево_Кол()));
            preparedStatement.executeUpdate();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {
        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {
                }
            }
        }
    }

    private void getTree(String sql, String str)
    {
        count1++;
        TreeItem<String> treeItem = new TreeItem<String>(str + " 1шт.");
        try
        {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                treeItem.getChildren().add(new TreeItem<>(resultSet.getString("Обозначение") + " " + nf.format(resultSet.getDouble("Кол")) + resultSet.getString("ЕИЭ")));
            }
            resultSet.close();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
        treeView.setRoot(treeItem);
        treeItem.setExpanded(true);
        if (!checkBoxAccelerate.isSelected())
        {
            fillTree(treeView.getRoot());
        }
        if (count1 < 2)
        {
            for (int i = 0; i < treeItem.getChildren().size(); i++)
            {
                newNode("SELECT concat_ws(\" \",Элементы_1.Обозначение , Элементы_1.Наименование, \" \") AS Обозначение, Дерево.Кол, Элементы_1.ЕИЭ\n"
                        + "FROM (Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов\n"
                        + "WHERE (((concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=\"" + treeItem.getChildren().get(i).getValue().substring(0, treeItem.getChildren().get(i).getValue().lastIndexOf(" ")) + " \"))\n"
                        + "ORDER BY Дерево.Поз;", treeItem.getChildren().get(i), false);
            }
        }

    }

    private void fillTree(TreeItem<String> treeItem)
    {
        for (int i = 0; i < treeItem.getChildren().size(); i++)
        {

            if (checkBoxNodes.isSelected())
            {
                newNode("SELECT concat_ws(\" \",Элементы_1.Обозначение , Элементы_1.Наименование, \" \") AS Обозначение, Дерево.Кол, Элементы_1.ЕИЭ\n"
                        + "FROM (Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов\n"
                        + "WHERE (((concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=\"" + treeItem.getChildren().get(i).getValue().substring(0, treeItem.getChildren().get(i).getValue().lastIndexOf(" ")) + " \")& (Элементы_1.Узел)!=0)\n"
                        + "ORDER BY Дерево.Поз;", treeItem.getChildren().get(i), false);
            }
            else
            {
                newNode("SELECT concat_ws(\" \",Элементы_1.Обозначение , Элементы_1.Наименование, \" \") AS Обозначение, Дерево.Кол, Элементы_1.ЕИЭ\n"
                        + "FROM (Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов\n"
                        + "WHERE (((concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=\"" + treeItem.getChildren().get(i).getValue().substring(0, treeItem.getChildren().get(i).getValue().lastIndexOf(" ")) + " \"))\n"
                        + "ORDER BY Дерево.Поз;", treeItem.getChildren().get(i), false);
            }
            fillTree(treeItem.getChildren().get(i));
        }
    }

    private void newNode(String sql, TreeItem<String> treeItem, Boolean expand)
    {
        count1++;

        try
        {
            if (!treeItem.getChildren().isEmpty())
            {
                return;
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                treeItem.getChildren().add(new TreeItem<String>(resultSet.getString("Обозначение") + nf.format(resultSet.getDouble("Кол")) + resultSet.getString("ЕИЭ")));

            }
            resultSet.close();
            treeItem.setExpanded(expand);
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        catch (NullPointerException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }

        }

        if (count1 < 2)
        {
            for (int i = 0; i < treeItem.getChildren().size(); i++)
            {
                newNode("SELECT concat_ws(\" \",Элементы_1.Обозначение , Элементы_1.Наименование, \" \") AS Обозначение, Дерево.Кол, Элементы_1.ЕИЭ\n"
                        + "FROM (Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов\n"
                        + "WHERE (((concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=\"" + treeItem.getChildren().get(i).getValue().substring(0, treeItem.getChildren().get(i).getValue().lastIndexOf(" ")).replaceAll("\"", "\"\"") + " \"))\n"
                        + "ORDER BY Дерево.Поз;", treeItem.getChildren().get(i), false);

            }
        }
    }
    double count;

    private String getTreeText(TreeItem<String> model, String indent)
    {
        String myRow = indent + model.getValue() + "\r\n";
        for (int i = 0; i < model.getChildren().size(); i++)
        {
            myRow += getTreeText(model.getChildren().get(i), indent + "-");
        }

        return myRow;
    }

    private void showElements(String sql)
    {
        dataElements.clear();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                dataElements.add(new Elements(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));

            }
            resultSet.close();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }

        }
    }

    private void showMaterials(String sql)
    {
        dataMaterials.clear();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                dataMaterials.add(new Materials(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        Double.toString(resultSet.getDouble(6))
                ));

            }
            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
    }

    private void showCommonMaterials(String sql)
    {
        dataCommonMaterials.clear();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                dataCommonMaterials.add(new CommonMaterials(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
    }

    private void showFormulas(String sql)
    {
        dataFormulas.clear();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                dataFormulas.add(new Formulas(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                ));
            }
            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
    }

    private void deleteFromTable(String sql, Integer id)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private boolean checkElementInTree(String sql)
    {
        boolean boolean1 = false;
        try
        {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                boolean1 = true;
            }
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {

            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
        return boolean1;

    }

    private void insertElementToDb(String sql)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, textFieldTableElement.getText());
            preparedStatement.setString(2, textFieldTableName.getText());
            preparedStatement.setString(3, textFieldTableDescription.getText());
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void insertMaterialToDb(String sql)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            try
            {
                preparedStatement.setInt(1, Integer.parseInt(textFieldTableMaterialKTDM.getText()));
            }
            catch (NumberFormatException ex)
            {
                preparedStatement.setInt(1, 0);
            }
            preparedStatement.setString(2, comboboxTableMateral.getEditor().getText());
            preparedStatement.setString(3, textFieldTableMaterialUnit.getText());
            preparedStatement.setInt(4, getId("SELECT Формулы.IDФормул\n"
                    + "FROM Формулы\n"
                    + "WHERE (((Формулы.Формула)=\"" + comboboxTableMaterialFormulа.getEditor().getText() + "\"));", "IDФормул"));
            preparedStatement.setDouble(5, Double.parseDouble(textFieldTableMaterialCofficient.getText().replace(",", ".")));
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void insertCommonMaterialToDb(String sql)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, comboboxTableCommon.getEditor().getText());
            preparedStatement.setString(2, textFieldTableCommonUnit.getText());
            preparedStatement.setInt(3, getId("SELECT Формулы.IDФормул\n"
                    + "FROM Формулы\n"
                    + "WHERE (((Формулы.Формула)=\"" + comboboxTableCommonFormula.getSelectionModel().getSelectedItem() + "\"));", "IDФормул"));
            try
            {
                preparedStatement.setInt(4, Integer.parseInt(textFieldTableCommonKTDV.getText()));
            }
            catch (NumberFormatException ex)
            {
                preparedStatement.setInt(4, 0);
            }
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void insertFormulaToDb(String sql)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, textFieldFormula.getText());
            preparedStatement.setString(2, textFieldCalculation.getText());
            preparedStatement.setString(3, textFieldA.getText());
            preparedStatement.setString(4, textFieldB.getText());
            preparedStatement.setString(5, textFieldC.getText());
            preparedStatement.setString(6, textFieldD.getText());
            preparedStatement.setString(7, textFieldE.getText());
            preparedStatement.setString(8, textFieldF.getText());
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void updateElement(String sql)
    {
        try
        {

            preparedStatement = connection.prepareStatement(sql);
            try
            {
                preparedStatement.setInt(1, Integer.parseInt(textFieldTableKTD.getText()));
            }
            catch (NumberFormatException ex)
            {
                preparedStatement.setInt(1, 0);
            }
            preparedStatement.setString(2, textFieldTableElement.getText());
            preparedStatement.setString(3, textFieldTableName.getText());
            preparedStatement.setString(4, textFieldTableDescription.getText());
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void updateFormula(String sql)
    {
        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, textFieldFormula.getText());
            preparedStatement.setString(2, textFieldCalculation.getText());
            preparedStatement.setString(3, textFieldA.getText());
            preparedStatement.setString(4, textFieldB.getText());
            preparedStatement.setString(5, textFieldC.getText());
            preparedStatement.setString(6, textFieldD.getText());
            preparedStatement.setString(7, textFieldE.getText());
            preparedStatement.setString(8, textFieldF.getText());
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void updateMaterialFormula(String sql)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(textFieldTableMaterialKTDM.getText()));
            preparedStatement.setString(2, comboboxTableMateral.getEditor().getText());
            preparedStatement.setString(3, textFieldTableMaterialUnit.getText());
            preparedStatement.setInt(4, getId("SELECT Формулы.IDФормул\n"
                    + "FROM Формулы\n"
                    + "WHERE (((Формулы.Формула)=\"" + comboboxTableMaterialFormulа.getEditor().getText() + "\"));", "IDФормул"));
            preparedStatement.setDouble(5, Double.parseDouble(textFieldTableMaterialCofficient.getText().replace(",", ".")));
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void updateCommonMaterialFormula(String sql)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(textFieldTableCommonKTDV.getText()));
            preparedStatement.setString(2, comboboxTableCommon.getEditor().getText());
            preparedStatement.setString(3, textFieldTableCommonUnit.getText());
            preparedStatement.setInt(4, getId("SELECT Формулы.IDФормул\n"
                    + "FROM Формулы\n"
                    + "WHERE (((Формулы.Формула)=\"" + comboboxTableCommonFormula.getEditor().getText() + "\"))", "IDФормул"));
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void showParents(String sql)
    {
        dataSearch.clear();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                dataSearch.add(new Search(
                        resultSet.getInt("IDЭлементов"),
                        resultSet.getString("Обозначение"),
                        nf.format(resultSet.getDouble("Кол"))
                ));
            }
            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
    }

    private void replaceDialog(String str2, String str3, String str4)
    {
        //
// Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle(titleName);
        dialog.setHeaderText(str2);
        dialog.setWidth(500);

// Set the icon (must be included in the project).
        dialog.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("resources/Replace-icon.png").toString()));
        // Get the Stage.
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();

// Add a custom icon.
        dialog.initStyle(StageStyle.UTILITY);

// Set the button types.
        ButtonType buttonTypeReplace = new ButtonType("Заменить", ButtonData.OK_DONE);
        ButtonType buttonTypeClose = new ButtonType("Закрыть", ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeReplace, buttonTypeClose);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        grid.add(new Label(str3), 0, 0);
        grid.add(comboBoxReplace, 1, 0);
        grid.add(new Label(str4), 0, 1);
        grid.add(comboBoxReplaced, 1, 1);
        grid.setAlignment(Pos.CENTER);

// Enable/Disable login button depending on whether a username was entered.
        Node replaceButton = dialog.getDialogPane().lookupButton(buttonTypeReplace);
        replaceButton.setDisable(true);
// Do some validation (using the Java 8 lambda syntax).
        comboBoxReplace.getEditor().textProperty().addListener((observable, oldValue, newValue)
                ->
        {
            replaceButton.setDisable(newValue.trim().isEmpty());
        });

// Do some validation (using the Java 8 lambda syntax).
        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton
                ->
        {
            if (dialogButton == buttonTypeReplace)
            {
                return new Pair<>(comboBoxReplace.getEditor().getText(), comboBoxReplaced.getEditor().getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(pair
                ->
        {
            replaceElement("UPDATE Дерево SET Дерево.Обозначение = ? WHERE (((Дерево.Обозначение)=?))", idReplacedList.get(comboBoxReplaced.getSelectionModel().getSelectedIndex()), idReplaceList.get(comboBoxReplace.getSelectionModel().getSelectedIndex()));
            replaceElement("UPDATE Элементы SET Элементы.IDМатериала = ? WHERE (((Элементы.IDМатериала)=?))", idReplacedList.get(comboBoxReplaced.getSelectionModel().getSelectedIndex()), idReplaceList.get(comboBoxReplace.getSelectionModel().getSelectedIndex()));

        });
    }

    private void insertCommonElementToDb(String sql)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_IDЭлементов());
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void deleteById(String sql, int id)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        catch (NullPointerException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void getFormulas(String sql, ComboBox comboBox)
    {
        formulasList.clear();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
//                formulasListId.add(resultSet.getInt("IDФормул"));
                formulasList.add(resultSet.getString("Формула"));
            }
            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
        comboBox.setItems(formulasList);
    }

    private void replaceElement(String sql, Integer integer1, Integer integer2)
    {
        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, integer1);
            preparedStatement.setInt(2, integer2);
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    public void copySelectionToClipboard(final TableView<?> table)
    {
        final Set<Integer> rows = new TreeSet<>();
        for (final TablePosition tablePosition : table.getSelectionModel().getSelectedCells())
        {
            rows.add(tablePosition.getRow());
        }
        final StringBuilder strb = new StringBuilder();
        boolean firstRow = true;
        for (final Integer row : rows)
        {
            if (!firstRow)
            {
                strb.append('\n');
            }
            firstRow = false;
            boolean firstCol = true;
            for (final TableColumn<?, ?> column : table.getColumns())
            {
                if (!firstCol)
                {
                    strb.append('\t');
                }
                firstCol = false;
                final Object cellData = column.getCellData(row);
                strb.append(cellData == null ? "" : cellData.toString());
            }
        }
        final ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(strb.toString());
        Clipboard.getSystemClipboard().setContent(clipboardContent);
    }

    private void executeUpdate(String sql)
    {
        try
        {

            statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
    }
    int secondsPassed = 0;

    public void start()
    {
        Timer timer = new Timer();
        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                secondsPassed++;
                System.out.println("Seconds passed: " + secondsPassed);
            }
        };
    }

    private void getNRM(String sql) throws IOException, InvalidFormatException
    {

        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSetMetaData = resultSet.getMetaData();
            File file = new File(saveXls + File.separator + element + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("(dd.MM.yyyy-HH.mm.dd)")) + ".xlsx");
            //Workbook wb = new HSSFWorkbook();
            Workbook wb = new XSSFWorkbook();

            Sheet sheet = wb.createSheet("НРМ");
            sheet.setColumnWidth(1, 6400);
            sheet.setColumnWidth(2, 6400);
            sheet.setColumnWidth(5, 4352);

            Row row;
            Cell cell;
            row = sheet.createRow(0);
            CellStyle cellStyle = wb.createCellStyle();

            cell = row.createCell(1);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Обозначение());
            cell = row.createCell(2);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Наименование());
            cell = row.createCell(3);
            cell.setCellValue(Double.parseDouble(tableViewSpecification.getSelectionModel().getSelectedItem().getДерево_Кол()));
            cell = row.createCell(4);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_ЕИЭ());
            row = sheet.createRow(2);
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++)
            {
                cell = row.createCell(i);
                cell.setCellValue(resultSetMetaData.getColumnLabel(i + 1));
            }
            int i = 3;
            for (int j = 0; j < 6; j++)
            {
                CellRangeAddress region = new CellRangeAddress(2, i - 1, j, j);
                RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
            }
            while (resultSet.next())
            {
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(resultSet.getInt(1));
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getString(2));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getString(3));
                cell = row.createCell(3);
                cell.setCellValue(resultSet.getDouble(4));
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getString(5));
                cell = row.createCell(5);
                cell.setCellValue(resultSet.getString(6));

                for (int j = 0; j < 6; j++)
                {
                    CellRangeAddress region = new CellRangeAddress(2, i, j, j);
                    RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
                    RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
                    RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
                    RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
                }
                textFieldTimer.setText(String.valueOf((System.currentTimeMillis() - startTime) / 1000) + " с");
                i++;
            }
            resultSet.close();
            CreationHelper createHelper = wb.getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yy hh:mm"));

            row = sheet.createRow(i += 2);
            cell = row.createCell(1);
            cell.setCellValue("Дата составления");
            row = sheet.createRow(i += 1);
            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new java.util.Date());

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            //write this workbook to an Outputstream.
            wb.write(fileOutputStream);
            fileOutputStream.close();
            try
            {
                desktop.open(file);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
            //Runtime.getRuntime().exec("cmd.exe /C start " + file.getAbsolutePath());
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private void getCommonMaterials1(String sql) throws IOException, InvalidFormatException
    {
        try
        {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSetMetaData = resultSet.getMetaData();
            File file = new File(saveXls + File.separator + element + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("(dd.MM.yyyy-HH.mm.dd)")) + "В.xlsx");
            //Workbook wb = new HSSFWorkbook();
            Workbook wb = new XSSFWorkbook();

            Sheet sheet = wb.createSheet("Вспомогательные");
            sheet.setColumnWidth(1, 6400);
            sheet.setColumnWidth(2, 6400);
            sheet.setColumnWidth(5, 4352);
            sheet.setColumnWidth(7, 6400);
            sheet.setColumnWidth(10, 6400);
            sheet.setColumnWidth(12, 6400);
            sheet.setColumnWidth(15, 6400);

            Row row;
            Cell cell;
            row = sheet.createRow(0);
            CellStyle cellStyle = wb.createCellStyle();

            cell = row.createCell(1);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Обозначение());
            cell = row.createCell(2);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Наименование());
            cell = row.createCell(3);
            cell.setCellValue(Double.parseDouble(tableViewSpecification.getSelectionModel().getSelectedItem().getДерево_Кол()));
            cell = row.createCell(4);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_ЕИЭ());
            row = sheet.createRow(2);
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++)
            {
                cell = row.createCell(i);
                cell.setCellValue(resultSetMetaData.getColumnLabel(i + 1));
            }
            int i = 3;
            while (resultSet.next())
            {
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(resultSet.getInt(1));
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getString(2));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getString(3));
                cell = row.createCell(3);
                cell.setCellValue(resultSet.getString(4));
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getDouble(5));
                cell = row.createCell(5);
                cell.setCellValue(resultSet.getString(6));
                cell = row.createCell(6);
                cell.setCellValue(resultSet.getString(7));
                cell = row.createCell(7);
                cell.setCellValue(resultSet.getString(8));
                cell = row.createCell(8);
                cell.setCellValue(resultSet.getString(9));
                cell = row.createCell(9);
                cell.setCellValue(resultSet.getDouble(10));
                cell = row.createCell(10);
                cell.setCellValue(resultSet.getString(11));
                cell = row.createCell(11);
                cell.setCellValue(resultSet.getDouble(12));
                cell = row.createCell(12);
                cell.setCellValue(resultSet.getString(13));
                cell = row.createCell(13);
                cell.setCellValue(resultSet.getString(14));
                cell = row.createCell(14);
                cell.setCellValue(resultSet.getDouble(15));
                cell = row.createCell(15);
                cell.setCellValue(resultSet.getString(16));
                //textFieldTimer.setText(String.valueOf((System.currentTimeMillis() - startTime) / 1000) + " с");
                i++;
            }
            resultSet.close();
            CreationHelper createHelper = wb.getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yy hh:mm"));

            row = sheet.createRow(i += 2);
            cell = row.createCell(1);
            cell.setCellValue("Дата составления");
            row = sheet.createRow(i += 1);
            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new java.util.Date());

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            //write this workbook to an Outputstream.
            wb.write(fileOutputStream);
            fileOutputStream.close();
            try
            {
                desktop.open(file);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
            //Runtime.getRuntime().exec("cmd.exe /C start " + file.getAbsolutePath());
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private void getNRMFull(String sql) throws IOException, InvalidFormatException
    {

        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSetMetaData = resultSet.getMetaData();

            File file = new File(saveXls + File.separator + element + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("(dd.MM.yyyy-HH.mm.dd)")) + ".xlsx");
            //Workbook wb = new HSSFWorkbook();
            Workbook wb = new XSSFWorkbook();

            Sheet sheet = wb.createSheet("НРМ");
            sheet.setColumnWidth(1, 6400);
            sheet.setColumnWidth(2, 6400);
            sheet.setColumnWidth(5, 4352);

            Row row;
            Cell cell;
            row = sheet.createRow(0);
            CellStyle cellStyle = wb.createCellStyle();

            cell = row.createCell(1);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Обозначение());
            cell = row.createCell(2);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Наименование());
            cell = row.createCell(3);
            cell.setCellValue(Double.parseDouble(tableViewSpecification.getSelectionModel().getSelectedItem().getДерево_Кол()));
            cell = row.createCell(4);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_ЕИЭ());
            row = sheet.createRow(2);
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++)
            {
                cell = row.createCell(i);
                cell.setCellValue(resultSetMetaData.getColumnLabel(i + 1));
            }
            int i = 3;/*
            for (int j = 0; j < 6; j++)
            {
                CellRangeAddress region = new CellRangeAddress(2, i - 1, j, j);
                RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
            }*/
            while (resultSet.next())
            {
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(resultSet.getInt(1));
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getString(2));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getString(3));
                cell = row.createCell(3);
                cell.setCellValue(resultSet.getDouble(4));
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getString(5));
                cell = row.createCell(5);
                cell.setCellValue(resultSet.getString(6));
                /*
                for (int j = 0; j < 6; j++)
                {
                    CellRangeAddress region = new CellRangeAddress(2, i, j, j);
                    RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
                    RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
                    RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
                    RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
                }
                textFieldTimer.setText(String.valueOf((System.currentTimeMillis() - startTime) / 1000) + " с");*/
                i++;
            }
            resultSet.close();
            CreationHelper createHelper = wb.getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yy hh:mm"));

            row = sheet.createRow(i += 2);
            cell = row.createCell(1);
            cell.setCellValue("Дата составления");
            row = sheet.createRow(i += 1);
            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new java.util.Date());

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            //write this workbook to an Outputstream.
            wb.write(fileOutputStream);
            fileOutputStream.close();
            try
            {
                desktop.open(file);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
            //Runtime.getRuntime().exec("cmd.exe /C start " + file.getAbsolutePath());
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private void getCooperation(String sql) throws IOException, InvalidFormatException
    {
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSetMetaData = resultSet.getMetaData();

            File file = new File(saveXls + File.separator + element + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("(dd.MM.yyyy-HH.mm.dd)")) + "К.xlsx");
            //Workbook wb = new HSSFWorkbook();
            Workbook wb = new XSSFWorkbook();

            Sheet sheet = wb.createSheet("НРМ");
            sheet.setColumnWidth(1, 6400);
            sheet.setColumnWidth(2, 6400);
            sheet.setColumnWidth(5, 4352);

            Row row;
            Cell cell;
            row = sheet.createRow(0);
            CellStyle cellStyle = wb.createCellStyle();

            cell = row.createCell(1);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Обозначение());
            cell = row.createCell(2);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Наименование());
            cell = row.createCell(3);
            cell.setCellValue(Double.parseDouble(tableViewSpecification.getSelectionModel().getSelectedItem().getДерево_Кол()));
            cell = row.createCell(4);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_ЕИЭ());
            row = sheet.createRow(2);
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++)
            {
                cell = row.createCell(i);
                cell.setCellValue(resultSetMetaData.getColumnLabel(i + 1));
            }
            int i = 3;

            while (resultSet.next())
            {
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(i - 2);
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getString(2));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getString(3));
                cell = row.createCell(3);
                cell.setCellValue(resultSet.getDouble(4));
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getString(5));
                cell = row.createCell(5);
                cell.setCellValue(resultSet.getString(6));
                i++;
            }
            resultSet.close();
            CreationHelper createHelper = wb.getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yy hh:mm"));

            row = sheet.createRow(i += 2);
            cell = row.createCell(1);
            cell.setCellValue("Дата составления");
            row = sheet.createRow(i += 1);
            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new java.util.Date());

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            //write this workbook to an Outputstream.
            wb.write(fileOutputStream);
            fileOutputStream.close();
            try
            {
                desktop.open(file);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
            //Runtime.getRuntime().exec("cmd.exe /C start " + file.getAbsolutePath());
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private void getCooperationMidi(String sql) throws IOException, InvalidFormatException
    {
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSetMetaData = resultSet.getMetaData();

            File file = new File(saveXls + File.separator + element + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("(dd.MM.yyyy-HH.mm.dd)")) + "К.xlsx");
            //Workbook wb = new HSSFWorkbook();
            Workbook wb = new XSSFWorkbook();

            Sheet sheet = wb.createSheet("НРМ");
            sheet.setColumnWidth(0, 1000);
            sheet.setColumnWidth(1, 6400);
            sheet.setColumnWidth(2, 6400);
            sheet.setColumnWidth(5, 3000);

            Row row;
            Cell cell;
            //row = sheet.createRow(0);
            CellStyle cellStyle = wb.createCellStyle();
            /*
            cell = row.createCell(1);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Обозначение());
            cell = row.createCell(2);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Наименование());
            cell = row.createCell(3);
            cell.setCellValue(Double.parseDouble(tableViewSpecification.getSelectionModel().getSelectedItem().getДерево_Кол()));
            cell = row.createCell(4);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_ЕИЭ());
             */
            row = sheet.createRow(0);
            cell = row.createCell(5);
            cell.setCellValue("У Т В Е Р Ж Д А Ю");
            row = sheet.createRow(1);
            cell = row.createCell(5);
            cell.setCellValue("Заместитель генерального");
            row = sheet.createRow(2);
            cell = row.createCell(5);
            cell.setCellValue("директора-технический директор");
            row = sheet.createRow(3);
            cell = row.createCell(5);
            cell.setCellValue("______________ С. А. Логвинов");

            row = sheet.createRow(5);
            cell = row.createCell(1);
            cell.setCellValue(comboboxOrder.getEditor().getText());

            row = sheet.createRow(6);
            cell = row.createCell(1);
            cell.setCellValue("ИЗДЕЛИЯ, ТРЕБУЮЩИЕ РАБОТ ПО КООПЕРАЦИИ (мехобработка)");

            row = sheet.createRow(9);

            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++)
            {
                if (i != 3)
                {
                    cell = row.createCell(i);
                    cell.setCellValue(resultSetMetaData.getColumnLabel(i + 1));
                }
                else
                {
                    cell = row.createCell(i);
                    cell.setCellValue("кол. на 1 изделие");
                }
            }
            row = sheet.createRow(10);
            cell = row.createCell(1);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Обозначение());
            cell = row.createCell(2);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_Наименование());
            cell = row.createCell(3);
            cell.setCellValue(Double.parseDouble(tableViewSpecification.getSelectionModel().getSelectedItem().getДерево_Кол()));
            cell = row.createCell(4);
            cell.setCellValue(tableViewSpecification.getSelectionModel().getSelectedItem().getЭлементы_1_ЕИЭ());
            int i = 11;
            int n = 1;
            while (resultSet.next())
            {
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(n);
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getString(2));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getString(3));
                cell = row.createCell(3);
                cell.setCellValue(resultSet.getDouble(4));
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getString(5));
                cell = row.createCell(5);
                cell.setCellValue(resultSet.getString(6));
                i++;
                n++;
            }
            resultSet.close();
            CreationHelper createHelper = wb.getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yy hh:mm"));

            row = sheet.createRow(i += 2);
            cell = row.createCell(1);
            cell.setCellValue(" зам. технического директора ______________________ С.В. Панченко");

            row = sheet.createRow(i += 2);
            cell = row.createCell(1);
            cell.setCellValue("ознакомлен:");
            row = sheet.createRow(i += 1);
            cell = row.createCell(1);
            cell.setCellValue("                            ______________________ нач. заг. цеха /А. П. Молчанский/");
            /*row = sheet.createRow(i += 1);
            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new java.util.Date());*/
            for (int y = 9; y < 10 + n; y++)
            {
                for (int j = 0; j < 6; j++)
                {
                    CellRangeAddress region = new CellRangeAddress(9, y, j, j);
                    RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
                    RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
                    RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
                    RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            //write this workbook to an Outputstream.
            wb.write(fileOutputStream);
            fileOutputStream.close();
            try
            {
                desktop.open(file);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
            //Runtime.getRuntime().exec("cmd.exe /C start " + file.getAbsolutePath());
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private void getNRM2(String sql) throws IOException, InvalidFormatException
    {
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSetMetaData = resultSet.getMetaData();

            File file = new File(saveXls + File.separator + element + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("(dd.MM.yyyy-HH.mm.dd)")) + ".xlsx");
            //Workbook wb = new HSSFWorkbook();
            Workbook wb = new XSSFWorkbook();

            Sheet sheet = wb.createSheet("НРМ");
            sheet.setColumnWidth(1, 6400);
            sheet.setColumnWidth(2, 6400);
            sheet.setColumnWidth(5, 4352);

            Row row;
            Cell cell;
            row = sheet.createRow(0);
            CreationHelper createHelper = wb.getCreationHelper();
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yy hh:mm"));
            for (int i = 0; i < 3; i++)
            {
                cell = row.createCell(i);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(new java.util.Date());
            }

            row = sheet.createRow(2);
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++)
            {
                cell = row.createCell(i);
                cell.setCellValue(resultSetMetaData.getColumnLabel(i + 1));
            }
            int i = 3;
            while (resultSet.next())
            {
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(resultSet.getInt(1));
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getString(2));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getString(3));
                cell = row.createCell(3);
                cell.setCellValue(resultSet.getDouble(4));
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getString(5));
                cell = row.createCell(5);
                cell.setCellValue(resultSet.getString(6));
                i++;
            }
            resultSet.close();
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            //write this workbook to an Outputstream.
            wb.write(fileOutputStream);
            fileOutputStream.close();
            try
            {
                desktop.open(file);
            }
            catch (IOException ex)
            {

            }
            //Runtime.getRuntime().exec("cmd.exe /C start " + file.getAbsolutePath());
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
    }

    private void getGraph() throws IOException, InvalidFormatException
    {
        File file = new File(saveXls + File.separator + treeTableView.getRoot().getValue().getОбозначение() + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("(dd.MM.yyyy-HH.mm.dd)")) + ".xlsx");
        //Workbook wb = new HSSFWorkbook();
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("График");
        sheet.setColumnWidth(0, 12800);
        sheet.setColumnWidth(1, 3800);//2560
        sheet.setColumnWidth(2, 2500);
        sheet.setColumnWidth(3, 5000);

        Row row;
        Cell cell;

        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue("Обозначение");
        cell = row.createCell(1);
        cell.setCellValue("Ответственный");
        cell = row.createCell(2);
        cell.setCellValue("Выписано");
        cell = row.createCell(3);
        cell.setCellValue("Примечание");
        CellStyle cellStyle = workbook.createCellStyle();
        fillTreeScheduleExcel(treeTableView.getRoot());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
        Date date1;
        for (int i = 0; i < dataSchedule.size(); i++)
        {
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(dataSchedule.get(i).getОбозначение());
            cell = row.createCell(1);
            cell.setCellValue(dataSchedule.get(i).getОтветственный());
            cell = row.createCell(2);
            cell.setCellValue(dataSchedule.get(i).getВыписано().replace(".19", ".2019"));
            cell = row.createCell(3);
            cell.setCellValue(dataSchedule.get(i).getПримечание());
            cell.setCellStyle(cellStyle);
        }
        dataSchedule.clear();
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        //write this workbook to an Outputstream.
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        try
        {
            desktop.open(file);
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    private void getCheck1(String sql) throws IOException, InvalidFormatException
    {
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSetMetaData = resultSet.getMetaData();
            File file = new File(saveXls + File.separator + element + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("(dd.MM.yyyy-HH.mm.dd)")) + "П.xlsx");
            //Workbook wb = new HSSFWorkbook();
            Workbook wb = new XSSFWorkbook();

            Sheet sheet = wb.createSheet("Проверка");
            sheet.setColumnWidth(0, 1280);
            sheet.setColumnWidth(1, 1280);
            sheet.setColumnWidth(2, 1280);
            sheet.setColumnWidth(3, 1280);
            sheet.setColumnWidth(4, 1280);
            sheet.setColumnWidth(5, 1280);

            sheet.setColumnWidth(6, 12800);
            sheet.setColumnWidth(7, 12800);

            sheet.setColumnWidth(12, 3840);
            sheet.setColumnWidth(13, 3840);
            Row row;
            Cell cell;
            row = sheet.createRow(0);
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++)
            {
                cell = row.createCell(i);
                cell.setCellValue(resultSetMetaData.getColumnLabel(i + 1));
            }
            int i = 1;
            while (resultSet.next())
            {
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(resultSet.getDouble(1));
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getDouble(2));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getDouble(3));
                cell = row.createCell(3);
                cell.setCellValue(resultSet.getDouble(4));
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getDouble(5));
                cell = row.createCell(5);
                cell.setCellValue(resultSet.getDouble(6));
                cell = row.createCell(6);
                cell.setCellValue(resultSet.getString(7));
                cell = row.createCell(7);
                cell.setCellValue(resultSet.getString(8));
                cell = row.createCell(8);
                cell.setCellValue(resultSet.getDouble(9));
                cell = row.createCell(9);
                cell.setCellValue(resultSet.getString(10));
                cell = row.createCell(10);
                cell.setCellValue(resultSet.getDouble(11));
                cell = row.createCell(11);
                cell.setCellValue(resultSet.getString(12));
                cell = row.createCell(12);
                cell.setCellValue(resultSet.getString(13));
                cell = row.createCell(13);
                cell.setCellValue(resultSet.getString(14));
                i++;
            }
            resultSet.close();
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            //write this workbook to an Outputstream.
            wb.write(fileOutputStream);
            fileOutputStream.close();
            try
            {
                desktop.open(file);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private void getCut(String sql) throws IOException, InvalidFormatException
    {
        try
        {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSetMetaData = resultSet.getMetaData();
            File file = new File(saveXls + File.separator + element + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("(dd.MM.yyyy-HH.mm.dd)")) + "Р.xlsx");
            //Workbook wb = new HSSFWorkbook();
            Workbook wb = new XSSFWorkbook();

            Sheet sheet = wb.createSheet("Раскрой");
            sheet.setColumnWidth(0, 2560);
            sheet.setColumnWidth(1, 2560);
            sheet.setColumnWidth(2, 1280);
            sheet.setColumnWidth(3, 12800);
            sheet.setColumnWidth(4, 2560);
            sheet.setColumnWidth(5, 12800);
            sheet.setColumnWidth(6, 1280);
            sheet.setColumnWidth(7, 3840);
            Row row;
            Cell cell;
            row = sheet.createRow(0);
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++)
            {
                cell = row.createCell(i);
                if (!"NULL".equals(resultSetMetaData.getColumnLabel(i + 1)))
                {
                    cell.setCellValue(resultSetMetaData.getColumnLabel(i + 1));
                }
            }
            int i = 1;
            while (resultSet.next())
            {
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(resultSet.getDouble(1));
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getDouble(2));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getDouble(3));
                cell = row.createCell(3);
                cell.setCellValue(resultSet.getString(4));
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getString(5));
                cell = row.createCell(5);
                cell.setCellValue(resultSet.getString(6));
                cell = row.createCell(6);
                cell.setCellValue(resultSet.getDouble(7));
                cell = row.createCell(7);
                cell.setCellValue(resultSet.getString(8));

                i++;
            }
            resultSet.close();
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            //write this workbook to an Outputstream.
            wb.write(fileOutputStream);
            fileOutputStream.close();
            try
            {
                desktop.open(file);
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
    }

    private void getColoring(String sql) throws IOException, InvalidFormatException
    {
        try
        {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSetMetaData = resultSet.getMetaData();
            File file = new File(saveXls + File.separator + element + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("(dd.MM.yyyy-HH.mm.dd)")) + "О.xlsx");
            //Workbook wb = new HSSFWorkbook();
            Workbook wb = new XSSFWorkbook();

            Sheet sheet = wb.createSheet("Окраска");
            sheet.setColumnWidth(0, 2560);
            sheet.setColumnWidth(1, 2560);
            sheet.setColumnWidth(2, 1280);
            sheet.setColumnWidth(3, 2560);
            sheet.setColumnWidth(4, 2560);
            sheet.setColumnWidth(5, 12800);
            sheet.setColumnWidth(6, 2560);
            sheet.setColumnWidth(7, 12800);
            sheet.setColumnWidth(8, 1280);
            sheet.setColumnWidth(9, 3840);
            Row row;
            Cell cell;
            row = sheet.createRow(0);
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++)
            {
                cell = row.createCell(i);
                if (!"NULL".equals(resultSetMetaData.getColumnLabel(i + 1)))
                {
                    cell.setCellValue(resultSetMetaData.getColumnLabel(i + 1));
                }
            }
            int i = 1;
            int i2 = i + 1;
            while (resultSet.next())
            {
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(resultSet.getDouble(1));
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getDouble(2));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getDouble(3));
                if (resultSet.getDouble(2) != 0)
                {
                    cell = row.createCell(3);
                    cell.setCellValue(2);
                }
                else
                {
                    cell = row.createCell(3);
                    cell.setCellValue(1.5);
                }

                cell = row.createCell(4);
                cell.setCellFormula("A" + i2 + "*B" + i2 + "/1000000*0.25*C" + i2 + "*D" + i2);

                cell = row.createCell(5);
                cell.setCellValue(resultSet.getString(6));
                cell = row.createCell(6);
                cell.setCellValue(resultSet.getString(7));
                cell = row.createCell(7);
                cell.setCellValue(resultSet.getString(8));
                cell = row.createCell(8);
                cell.setCellValue(resultSet.getDouble(9));
                cell = row.createCell(9);
                cell.setCellValue(resultSet.getString(10));

                i++;
                i2++;
            }
            row = sheet.createRow(i);
            cell = row.createCell(4);
            cell.setCellFormula("SUM(E2:E" + i + ")");
            resultSet.close();
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            //write this workbook to an Outputstream.
            wb.write(fileOutputStream);
            fileOutputStream.close();
            try
            {
                desktop.open(file);
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
    }
    String strq1 = "create table tableall\n"
            + "(select DISTINCT IDДа, Обо_а, Кол, IDЦЭ_а, null as IDД from (SELECT DISTINCT Дерево_а.IDДерева as IDДа, Дерево_а.Обозначение as Обо_а, Дерево_а.Кол as Кол, Дерево_а.IDЦехаЭ as IDЦЭ_а, Дерево_б.IDДерева as IDДб, Дерево_б.Обозначение as Обо_б, Дерево_а.Кол*Дерево_б.Кол as Колб, Дерево_б.IDЦехаЭ as IDЦЭ_б, Дерево_в.IDДерева as IDДв, Дерево_в.Обозначение as Обо_в, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол as Колв, Дерево_в.IDЦехаЭ as IDЦЭ_в, Дерево_г.IDДерева as IDДг, Дерево_г.Обозначение as Обо_г, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол as Колг, Дерево_г.IDЦехаЭ as IDЦЭ_г, Дерево_д.IDДерева as IDДд, Дерево_д.Обозначение as Обо_д, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол as Колд, Дерево_д.IDЦехаЭ as IDЦЭ_д, Дерево_е.IDДерева as IDДе, Дерево_е.Обозначение as Обо_е, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол as Коле, Дерево_е.IDЦехаЭ as IDЦЭ_е, Дерево_ё.IDДерева as IDДё, Дерево_ё.Обозначение as Обо_ё, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол as Колё, Дерево_ё.IDЦехаЭ as IDЦЭ_ё, Дерево_ж.IDДерева as IDДж, Дерево_ж.Обозначение as Обо_ж, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол as Колж, Дерево_ж.IDЦехаЭ as IDЦЭ_ж, Дерево_з.IDДерева as IDДз, Дерево_з.Обозначение as Обо_з, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол as Колз, Дерево_з.IDЦехаЭ as IDЦЭ_з, Дерево_и.IDДерева as IDДи, Дерево_и.Обозначение as Обо_и, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол as Коли, Дерево_и.IDЦехаЭ as IDЦЭ_и, Дерево_й.IDДерева as IDДй, Дерево_й.Обозначение as Обо_й, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол as Колй, Дерево_й.IDЦехаЭ as IDЦЭ_й, Дерево_к.IDДерева as IDДк, Дерево_к.Обозначение as Обо_к, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол as Колк, Дерево_к.IDЦехаЭ as IDЦЭ_к, Дерево_л.IDДерева, Дерево_л.Обозначение as Обо_л, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол as Колл, Дерево_л.IDЦехаЭ as IDЦЭ_л, Дерево_м.IDДерева as IDДм, Дерево_м.Обозначение as Обо_м, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол as Колм, Дерево_м.IDЦехаЭ as IDЦЭ_м, Дерево_н.IDДерева as IDДн, Дерево_н.Обозначение as Обо_н, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол*Дерево_н.Кол as Колн, Дерево_н.IDЦехаЭ as IDЦЭ_н\n"
            + "FROM ((((((((((((((Элементы LEFT JOIN Дерево AS Дерево_а ON Элементы.IDЭлементов = Дерево_а.Родитель) LEFT JOIN Дерево AS Дерево_б ON Дерево_а.Обозначение = Дерево_б.Родитель) LEFT JOIN Дерево AS Дерево_в ON Дерево_б.Обозначение = Дерево_в.Родитель) LEFT JOIN Дерево AS Дерево_г ON Дерево_в.Обозначение = Дерево_г.Родитель) LEFT JOIN Дерево AS Дерево_д ON Дерево_г.Обозначение = Дерево_д.Родитель) LEFT JOIN Дерево AS Дерево_е ON Дерево_д.Обозначение = Дерево_е.Родитель) LEFT JOIN Дерево AS Дерево_ё ON Дерево_е.Обозначение = Дерево_ё.Родитель) LEFT JOIN Дерево AS Дерево_ж ON Дерево_ё.Обозначение = Дерево_ж.Родитель) LEFT JOIN Дерево AS Дерево_з ON Дерево_ж.Обозначение = Дерево_з.Родитель) LEFT JOIN Дерево AS Дерево_и ON Дерево_з.Обозначение = Дерево_и.Родитель) LEFT JOIN Дерево AS Дерево_й ON Дерево_и.Обозначение = Дерево_й.Родитель) LEFT JOIN Дерево AS Дерево_к ON Дерево_й.Обозначение = Дерево_к.Родитель) LEFT JOIN Дерево AS Дерево_л ON Дерево_к.Обозначение = Дерево_л.Родитель) LEFT JOIN Дерево AS Дерево_м ON Дерево_л.Обозначение = Дерево_м.Родитель) LEFT JOIN Дерево AS Дерево_н ON Дерево_м.Обозначение = Дерево_н.Родитель\n"
            + "WHERE (((Элементы.Обозначение)=\"Узел\"))) as table1)\n"
            + "union all\n"
            + "(select DISTINCT IDДб, Обо_б, Колб, IDЦЭ_б, IDДа as IDД from (SELECT DISTINCT Дерево_а.IDДерева as IDДа, Дерево_а.Обозначение as Обо_а, Дерево_а.Кол as Кол, Дерево_а.IDЦехаЭ as IDЦЭ_а, Дерево_б.IDДерева as IDДб, Дерево_б.Обозначение as Обо_б, Дерево_а.Кол*Дерево_б.Кол as Колб, Дерево_б.IDЦехаЭ as IDЦЭ_б, Дерево_в.IDДерева as IDДв, Дерево_в.Обозначение as Обо_в, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол as Колв, Дерево_в.IDЦехаЭ as IDЦЭ_в, Дерево_г.IDДерева as IDДг, Дерево_г.Обозначение as Обо_г, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол as Колг, Дерево_г.IDЦехаЭ as IDЦЭ_г, Дерево_д.IDДерева as IDДд, Дерево_д.Обозначение as Обо_д, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол as Колд, Дерево_д.IDЦехаЭ as IDЦЭ_д, Дерево_е.IDДерева as IDДе, Дерево_е.Обозначение as Обо_е, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол as Коле, Дерево_е.IDЦехаЭ as IDЦЭ_е, Дерево_ё.IDДерева as IDДё, Дерево_ё.Обозначение as Обо_ё, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол as Колё, Дерево_ё.IDЦехаЭ as IDЦЭ_ё, Дерево_ж.IDДерева as IDДж, Дерево_ж.Обозначение as Обо_ж, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол as Колж, Дерево_ж.IDЦехаЭ as IDЦЭ_ж, Дерево_з.IDДерева as IDДз, Дерево_з.Обозначение as Обо_з, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол as Колз, Дерево_з.IDЦехаЭ as IDЦЭ_з, Дерево_и.IDДерева as IDДи, Дерево_и.Обозначение as Обо_и, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол as Коли, Дерево_и.IDЦехаЭ as IDЦЭ_и, Дерево_й.IDДерева as IDДй, Дерево_й.Обозначение as Обо_й, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол as Колй, Дерево_й.IDЦехаЭ as IDЦЭ_й, Дерево_к.IDДерева as IDДк, Дерево_к.Обозначение as Обо_к, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол as Колк, Дерево_к.IDЦехаЭ as IDЦЭ_к, Дерево_л.IDДерева, Дерево_л.Обозначение as Обо_л, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол as Колл, Дерево_л.IDЦехаЭ as IDЦЭ_л, Дерево_м.IDДерева as IDДм, Дерево_м.Обозначение as Обо_м, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол as Колм, Дерево_м.IDЦехаЭ as IDЦЭ_м, Дерево_н.IDДерева as IDДн, Дерево_н.Обозначение as Обо_н, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол*Дерево_н.Кол as Колн, Дерево_н.IDЦехаЭ as IDЦЭ_н\n"
            + "FROM ((((((((((((((Элементы LEFT JOIN Дерево AS Дерево_а ON Элементы.IDЭлементов = Дерево_а.Родитель) LEFT JOIN Дерево AS Дерево_б ON Дерево_а.Обозначение = Дерево_б.Родитель) LEFT JOIN Дерево AS Дерево_в ON Дерево_б.Обозначение = Дерево_в.Родитель) LEFT JOIN Дерево AS Дерево_г ON Дерево_в.Обозначение = Дерево_г.Родитель) LEFT JOIN Дерево AS Дерево_д ON Дерево_г.Обозначение = Дерево_д.Родитель) LEFT JOIN Дерево AS Дерево_е ON Дерево_д.Обозначение = Дерево_е.Родитель) LEFT JOIN Дерево AS Дерево_ё ON Дерево_е.Обозначение = Дерево_ё.Родитель) LEFT JOIN Дерево AS Дерево_ж ON Дерево_ё.Обозначение = Дерево_ж.Родитель) LEFT JOIN Дерево AS Дерево_з ON Дерево_ж.Обозначение = Дерево_з.Родитель) LEFT JOIN Дерево AS Дерево_и ON Дерево_з.Обозначение = Дерево_и.Родитель) LEFT JOIN Дерево AS Дерево_й ON Дерево_и.Обозначение = Дерево_й.Родитель) LEFT JOIN Дерево AS Дерево_к ON Дерево_й.Обозначение = Дерево_к.Родитель) LEFT JOIN Дерево AS Дерево_л ON Дерево_к.Обозначение = Дерево_л.Родитель) LEFT JOIN Дерево AS Дерево_м ON Дерево_л.Обозначение = Дерево_м.Родитель) LEFT JOIN Дерево AS Дерево_н ON Дерево_м.Обозначение = Дерево_н.Родитель\n"
            + "WHERE (((Элементы.Обозначение)=\"Узел\"))) as table2 where Обо_б is not null)\n"
            + "union all\n"
            + "(select DISTINCT IDДв, Обо_в, Колв, IDЦЭ_в, IDДа+\"\"+IDДб as IDД from (SELECT DISTINCT Дерево_а.IDДерева as IDДа, Дерево_а.Обозначение as Обо_а, Дерево_а.Кол as Кол, Дерево_а.IDЦехаЭ as IDЦЭ_а, Дерево_б.IDДерева as IDДб, Дерево_б.Обозначение as Обо_б, Дерево_а.Кол*Дерево_б.Кол as Колб, Дерево_б.IDЦехаЭ as IDЦЭ_б, Дерево_в.IDДерева as IDДв, Дерево_в.Обозначение as Обо_в, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол as Колв, Дерево_в.IDЦехаЭ as IDЦЭ_в, Дерево_г.IDДерева as IDДг, Дерево_г.Обозначение as Обо_г, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол as Колг, Дерево_г.IDЦехаЭ as IDЦЭ_г, Дерево_д.IDДерева as IDДд, Дерево_д.Обозначение as Обо_д, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол as Колд, Дерево_д.IDЦехаЭ as IDЦЭ_д, Дерево_е.IDДерева as IDДе, Дерево_е.Обозначение as Обо_е, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол as Коле, Дерево_е.IDЦехаЭ as IDЦЭ_е, Дерево_ё.IDДерева as IDДё, Дерево_ё.Обозначение as Обо_ё, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол as Колё, Дерево_ё.IDЦехаЭ as IDЦЭ_ё, Дерево_ж.IDДерева as IDДж, Дерево_ж.Обозначение as Обо_ж, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол as Колж, Дерево_ж.IDЦехаЭ as IDЦЭ_ж, Дерево_з.IDДерева as IDДз, Дерево_з.Обозначение as Обо_з, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол as Колз, Дерево_з.IDЦехаЭ as IDЦЭ_з, Дерево_и.IDДерева as IDДи, Дерево_и.Обозначение as Обо_и, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол as Коли, Дерево_и.IDЦехаЭ as IDЦЭ_и, Дерево_й.IDДерева as IDДй, Дерево_й.Обозначение as Обо_й, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол as Колй, Дерево_й.IDЦехаЭ as IDЦЭ_й, Дерево_к.IDДерева as IDДк, Дерево_к.Обозначение as Обо_к, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол as Колк, Дерево_к.IDЦехаЭ as IDЦЭ_к, Дерево_л.IDДерева, Дерево_л.Обозначение as Обо_л, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол as Колл, Дерево_л.IDЦехаЭ as IDЦЭ_л, Дерево_м.IDДерева as IDДм, Дерево_м.Обозначение as Обо_м, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол as Колм, Дерево_м.IDЦехаЭ as IDЦЭ_м, Дерево_н.IDДерева as IDДн, Дерево_н.Обозначение as Обо_н, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол*Дерево_н.Кол as Колн, Дерево_н.IDЦехаЭ as IDЦЭ_н\n"
            + "FROM ((((((((((((((Элементы LEFT JOIN Дерево AS Дерево_а ON Элементы.IDЭлементов = Дерево_а.Родитель) LEFT JOIN Дерево AS Дерево_б ON Дерево_а.Обозначение = Дерево_б.Родитель) LEFT JOIN Дерево AS Дерево_в ON Дерево_б.Обозначение = Дерево_в.Родитель) LEFT JOIN Дерево AS Дерево_г ON Дерево_в.Обозначение = Дерево_г.Родитель) LEFT JOIN Дерево AS Дерево_д ON Дерево_г.Обозначение = Дерево_д.Родитель) LEFT JOIN Дерево AS Дерево_е ON Дерево_д.Обозначение = Дерево_е.Родитель) LEFT JOIN Дерево AS Дерево_ё ON Дерево_е.Обозначение = Дерево_ё.Родитель) LEFT JOIN Дерево AS Дерево_ж ON Дерево_ё.Обозначение = Дерево_ж.Родитель) LEFT JOIN Дерево AS Дерево_з ON Дерево_ж.Обозначение = Дерево_з.Родитель) LEFT JOIN Дерево AS Дерево_и ON Дерево_з.Обозначение = Дерево_и.Родитель) LEFT JOIN Дерево AS Дерево_й ON Дерево_и.Обозначение = Дерево_й.Родитель) LEFT JOIN Дерево AS Дерево_к ON Дерево_й.Обозначение = Дерево_к.Родитель) LEFT JOIN Дерево AS Дерево_л ON Дерево_к.Обозначение = Дерево_л.Родитель) LEFT JOIN Дерево AS Дерево_м ON Дерево_л.Обозначение = Дерево_м.Родитель) LEFT JOIN Дерево AS Дерево_н ON Дерево_м.Обозначение = Дерево_н.Родитель\n"
            + "WHERE (((Элементы.Обозначение)=\"Узел\"))) as table3 where Обо_в is not null)\n"
            + "union all\n"
            + "(select DISTINCT IDДг, Обо_г, Колг, IDЦЭ_г, IDДа+\"\"+IDДб+\"\"+IDДв as IDД from (SELECT DISTINCT Дерево_а.IDДерева as IDДа, Дерево_а.Обозначение as Обо_а, Дерево_а.Кол as Кол, Дерево_а.IDЦехаЭ as IDЦЭ_а, Дерево_б.IDДерева as IDДб, Дерево_б.Обозначение as Обо_б, Дерево_а.Кол*Дерево_б.Кол as Колб, Дерево_б.IDЦехаЭ as IDЦЭ_б, Дерево_в.IDДерева as IDДв, Дерево_в.Обозначение as Обо_в, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол as Колв, Дерево_в.IDЦехаЭ as IDЦЭ_в, Дерево_г.IDДерева as IDДг, Дерево_г.Обозначение as Обо_г, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол as Колг, Дерево_г.IDЦехаЭ as IDЦЭ_г, Дерево_д.IDДерева as IDДд, Дерево_д.Обозначение as Обо_д, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол as Колд, Дерево_д.IDЦехаЭ as IDЦЭ_д, Дерево_е.IDДерева as IDДе, Дерево_е.Обозначение as Обо_е, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол as Коле, Дерево_е.IDЦехаЭ as IDЦЭ_е, Дерево_ё.IDДерева as IDДё, Дерево_ё.Обозначение as Обо_ё, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол as Колё, Дерево_ё.IDЦехаЭ as IDЦЭ_ё, Дерево_ж.IDДерева as IDДж, Дерево_ж.Обозначение as Обо_ж, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол as Колж, Дерево_ж.IDЦехаЭ as IDЦЭ_ж, Дерево_з.IDДерева as IDДз, Дерево_з.Обозначение as Обо_з, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол as Колз, Дерево_з.IDЦехаЭ as IDЦЭ_з, Дерево_и.IDДерева as IDДи, Дерево_и.Обозначение as Обо_и, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол as Коли, Дерево_и.IDЦехаЭ as IDЦЭ_и, Дерево_й.IDДерева as IDДй, Дерево_й.Обозначение as Обо_й, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол as Колй, Дерево_й.IDЦехаЭ as IDЦЭ_й, Дерево_к.IDДерева as IDДк, Дерево_к.Обозначение as Обо_к, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол as Колк, Дерево_к.IDЦехаЭ as IDЦЭ_к, Дерево_л.IDДерева, Дерево_л.Обозначение as Обо_л, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол as Колл, Дерево_л.IDЦехаЭ as IDЦЭ_л, Дерево_м.IDДерева as IDДм, Дерево_м.Обозначение as Обо_м, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол as Колм, Дерево_м.IDЦехаЭ as IDЦЭ_м, Дерево_н.IDДерева as IDДн, Дерево_н.Обозначение as Обо_н, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол*Дерево_н.Кол as Колн, Дерево_н.IDЦехаЭ as IDЦЭ_н\n"
            + "FROM ((((((((((((((Элементы LEFT JOIN Дерево AS Дерево_а ON Элементы.IDЭлементов = Дерево_а.Родитель) LEFT JOIN Дерево AS Дерево_б ON Дерево_а.Обозначение = Дерево_б.Родитель) LEFT JOIN Дерево AS Дерево_в ON Дерево_б.Обозначение = Дерево_в.Родитель) LEFT JOIN Дерево AS Дерево_г ON Дерево_в.Обозначение = Дерево_г.Родитель) LEFT JOIN Дерево AS Дерево_д ON Дерево_г.Обозначение = Дерево_д.Родитель) LEFT JOIN Дерево AS Дерево_е ON Дерево_д.Обозначение = Дерево_е.Родитель) LEFT JOIN Дерево AS Дерево_ё ON Дерево_е.Обозначение = Дерево_ё.Родитель) LEFT JOIN Дерево AS Дерево_ж ON Дерево_ё.Обозначение = Дерево_ж.Родитель) LEFT JOIN Дерево AS Дерево_з ON Дерево_ж.Обозначение = Дерево_з.Родитель) LEFT JOIN Дерево AS Дерево_и ON Дерево_з.Обозначение = Дерево_и.Родитель) LEFT JOIN Дерево AS Дерево_й ON Дерево_и.Обозначение = Дерево_й.Родитель) LEFT JOIN Дерево AS Дерево_к ON Дерево_й.Обозначение = Дерево_к.Родитель) LEFT JOIN Дерево AS Дерево_л ON Дерево_к.Обозначение = Дерево_л.Родитель) LEFT JOIN Дерево AS Дерево_м ON Дерево_л.Обозначение = Дерево_м.Родитель) LEFT JOIN Дерево AS Дерево_н ON Дерево_м.Обозначение = Дерево_н.Родитель\n"
            + "WHERE (((Элементы.Обозначение)=\"Узел\"))) as table4 where Обо_г is not null)\n"
            + "union all\n"
            + "(select DISTINCT IDДд, Обо_д, Колд, IDЦЭ_д, IDДа+\"\"+IDДб+\"\"+IDДв+\"\"+IDДг as IDД from (SELECT DISTINCT Дерево_а.IDДерева as IDДа, Дерево_а.Обозначение as Обо_а, Дерево_а.Кол as Кол, Дерево_а.IDЦехаЭ as IDЦЭ_а, Дерево_б.IDДерева as IDДб, Дерево_б.Обозначение as Обо_б, Дерево_а.Кол*Дерево_б.Кол as Колб, Дерево_б.IDЦехаЭ as IDЦЭ_б, Дерево_в.IDДерева as IDДв, Дерево_в.Обозначение as Обо_в, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол as Колв, Дерево_в.IDЦехаЭ as IDЦЭ_в, Дерево_г.IDДерева as IDДг, Дерево_г.Обозначение as Обо_г, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол as Колг, Дерево_г.IDЦехаЭ as IDЦЭ_г, Дерево_д.IDДерева as IDДд, Дерево_д.Обозначение as Обо_д, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол as Колд, Дерево_д.IDЦехаЭ as IDЦЭ_д, Дерево_е.IDДерева as IDДе, Дерево_е.Обозначение as Обо_е, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол as Коле, Дерево_е.IDЦехаЭ as IDЦЭ_е, Дерево_ё.IDДерева as IDДё, Дерево_ё.Обозначение as Обо_ё, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол as Колё, Дерево_ё.IDЦехаЭ as IDЦЭ_ё, Дерево_ж.IDДерева as IDДж, Дерево_ж.Обозначение as Обо_ж, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол as Колж, Дерево_ж.IDЦехаЭ as IDЦЭ_ж, Дерево_з.IDДерева as IDДз, Дерево_з.Обозначение as Обо_з, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол as Колз, Дерево_з.IDЦехаЭ as IDЦЭ_з, Дерево_и.IDДерева as IDДи, Дерево_и.Обозначение as Обо_и, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол as Коли, Дерево_и.IDЦехаЭ as IDЦЭ_и, Дерево_й.IDДерева as IDДй, Дерево_й.Обозначение as Обо_й, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол as Колй, Дерево_й.IDЦехаЭ as IDЦЭ_й, Дерево_к.IDДерева as IDДк, Дерево_к.Обозначение as Обо_к, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол as Колк, Дерево_к.IDЦехаЭ as IDЦЭ_к, Дерево_л.IDДерева, Дерево_л.Обозначение as Обо_л, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол as Колл, Дерево_л.IDЦехаЭ as IDЦЭ_л, Дерево_м.IDДерева as IDДм, Дерево_м.Обозначение as Обо_м, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол as Колм, Дерево_м.IDЦехаЭ as IDЦЭ_м, Дерево_н.IDДерева as IDДн, Дерево_н.Обозначение as Обо_н, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол*Дерево_н.Кол as Колн, Дерево_н.IDЦехаЭ as IDЦЭ_н\n"
            + "FROM ((((((((((((((Элементы LEFT JOIN Дерево AS Дерево_а ON Элементы.IDЭлементов = Дерево_а.Родитель) LEFT JOIN Дерево AS Дерево_б ON Дерево_а.Обозначение = Дерево_б.Родитель) LEFT JOIN Дерево AS Дерево_в ON Дерево_б.Обозначение = Дерево_в.Родитель) LEFT JOIN Дерево AS Дерево_г ON Дерево_в.Обозначение = Дерево_г.Родитель) LEFT JOIN Дерево AS Дерево_д ON Дерево_г.Обозначение = Дерево_д.Родитель) LEFT JOIN Дерево AS Дерево_е ON Дерево_д.Обозначение = Дерево_е.Родитель) LEFT JOIN Дерево AS Дерево_ё ON Дерево_е.Обозначение = Дерево_ё.Родитель) LEFT JOIN Дерево AS Дерево_ж ON Дерево_ё.Обозначение = Дерево_ж.Родитель) LEFT JOIN Дерево AS Дерево_з ON Дерево_ж.Обозначение = Дерево_з.Родитель) LEFT JOIN Дерево AS Дерево_и ON Дерево_з.Обозначение = Дерево_и.Родитель) LEFT JOIN Дерево AS Дерево_й ON Дерево_и.Обозначение = Дерево_й.Родитель) LEFT JOIN Дерево AS Дерево_к ON Дерево_й.Обозначение = Дерево_к.Родитель) LEFT JOIN Дерево AS Дерево_л ON Дерево_к.Обозначение = Дерево_л.Родитель) LEFT JOIN Дерево AS Дерево_м ON Дерево_л.Обозначение = Дерево_м.Родитель) LEFT JOIN Дерево AS Дерево_н ON Дерево_м.Обозначение = Дерево_н.Родитель\n"
            + "WHERE (((Элементы.Обозначение)=\"Узел\"))) as table5 where Обо_д is not null)\n"
            + "union all\n"
            + "(select DISTINCT IDДе, Обо_е, Коле, IDЦЭ_е, IDДа+\"\"+IDДб+\"\"+IDДв+\"\"+IDДг+\"\"+IDДд as IDД from (SELECT DISTINCT Дерево_а.IDДерева as IDДа, Дерево_а.Обозначение as Обо_а, Дерево_а.Кол as Кол, Дерево_а.IDЦехаЭ as IDЦЭ_а, Дерево_б.IDДерева as IDДб, Дерево_б.Обозначение as Обо_б, Дерево_а.Кол*Дерево_б.Кол as Колб, Дерево_б.IDЦехаЭ as IDЦЭ_б, Дерево_в.IDДерева as IDДв, Дерево_в.Обозначение as Обо_в, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол as Колв, Дерево_в.IDЦехаЭ as IDЦЭ_в, Дерево_г.IDДерева as IDДг, Дерево_г.Обозначение as Обо_г, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол as Колг, Дерево_г.IDЦехаЭ as IDЦЭ_г, Дерево_д.IDДерева as IDДд, Дерево_д.Обозначение as Обо_д, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол as Колд, Дерево_д.IDЦехаЭ as IDЦЭ_д, Дерево_е.IDДерева as IDДе, Дерево_е.Обозначение as Обо_е, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол as Коле, Дерево_е.IDЦехаЭ as IDЦЭ_е, Дерево_ё.IDДерева as IDДё, Дерево_ё.Обозначение as Обо_ё, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол as Колё, Дерево_ё.IDЦехаЭ as IDЦЭ_ё, Дерево_ж.IDДерева as IDДж, Дерево_ж.Обозначение as Обо_ж, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол as Колж, Дерево_ж.IDЦехаЭ as IDЦЭ_ж, Дерево_з.IDДерева as IDДз, Дерево_з.Обозначение as Обо_з, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол as Колз, Дерево_з.IDЦехаЭ as IDЦЭ_з, Дерево_и.IDДерева as IDДи, Дерево_и.Обозначение as Обо_и, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол as Коли, Дерево_и.IDЦехаЭ as IDЦЭ_и, Дерево_й.IDДерева as IDДй, Дерево_й.Обозначение as Обо_й, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол as Колй, Дерево_й.IDЦехаЭ as IDЦЭ_й, Дерево_к.IDДерева as IDДк, Дерево_к.Обозначение as Обо_к, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол as Колк, Дерево_к.IDЦехаЭ as IDЦЭ_к, Дерево_л.IDДерева, Дерево_л.Обозначение as Обо_л, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол as Колл, Дерево_л.IDЦехаЭ as IDЦЭ_л, Дерево_м.IDДерева as IDДм, Дерево_м.Обозначение as Обо_м, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол as Колм, Дерево_м.IDЦехаЭ as IDЦЭ_м, Дерево_н.IDДерева as IDДн, Дерево_н.Обозначение as Обо_н, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол*Дерево_н.Кол as Колн, Дерево_н.IDЦехаЭ as IDЦЭ_н\n"
            + "FROM ((((((((((((((Элементы LEFT JOIN Дерево AS Дерево_а ON Элементы.IDЭлементов = Дерево_а.Родитель) LEFT JOIN Дерево AS Дерево_б ON Дерево_а.Обозначение = Дерево_б.Родитель) LEFT JOIN Дерево AS Дерево_в ON Дерево_б.Обозначение = Дерево_в.Родитель) LEFT JOIN Дерево AS Дерево_г ON Дерево_в.Обозначение = Дерево_г.Родитель) LEFT JOIN Дерево AS Дерево_д ON Дерево_г.Обозначение = Дерево_д.Родитель) LEFT JOIN Дерево AS Дерево_е ON Дерево_д.Обозначение = Дерево_е.Родитель) LEFT JOIN Дерево AS Дерево_ё ON Дерево_е.Обозначение = Дерево_ё.Родитель) LEFT JOIN Дерево AS Дерево_ж ON Дерево_ё.Обозначение = Дерево_ж.Родитель) LEFT JOIN Дерево AS Дерево_з ON Дерево_ж.Обозначение = Дерево_з.Родитель) LEFT JOIN Дерево AS Дерево_и ON Дерево_з.Обозначение = Дерево_и.Родитель) LEFT JOIN Дерево AS Дерево_й ON Дерево_и.Обозначение = Дерево_й.Родитель) LEFT JOIN Дерево AS Дерево_к ON Дерево_й.Обозначение = Дерево_к.Родитель) LEFT JOIN Дерево AS Дерево_л ON Дерево_к.Обозначение = Дерево_л.Родитель) LEFT JOIN Дерево AS Дерево_м ON Дерево_л.Обозначение = Дерево_м.Родитель) LEFT JOIN Дерево AS Дерево_н ON Дерево_м.Обозначение = Дерево_н.Родитель\n"
            + "WHERE (((Элементы.Обозначение)=\"Узел\"))) as table6 where Обо_е is not null)\n"
            + "union all\n"
            + "(select DISTINCT IDДё, Обо_ё, Колё, IDЦЭ_ё, IDДа+\"\"+IDДб+\"\"+IDДв+\"\"+IDДг+\"\"+IDДд+\"\"+IDДе as IDД from (SELECT DISTINCT Дерево_а.IDДерева as IDДа, Дерево_а.Обозначение as Обо_а, Дерево_а.Кол as Кол, Дерево_а.IDЦехаЭ as IDЦЭ_а, Дерево_б.IDДерева as IDДб, Дерево_б.Обозначение as Обо_б, Дерево_а.Кол*Дерево_б.Кол as Колб, Дерево_б.IDЦехаЭ as IDЦЭ_б, Дерево_в.IDДерева as IDДв, Дерево_в.Обозначение as Обо_в, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол as Колв, Дерево_в.IDЦехаЭ as IDЦЭ_в, Дерево_г.IDДерева as IDДг, Дерево_г.Обозначение as Обо_г, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол as Колг, Дерево_г.IDЦехаЭ as IDЦЭ_г, Дерево_д.IDДерева as IDДд, Дерево_д.Обозначение as Обо_д, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол as Колд, Дерево_д.IDЦехаЭ as IDЦЭ_д, Дерево_е.IDДерева as IDДе, Дерево_е.Обозначение as Обо_е, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол as Коле, Дерево_е.IDЦехаЭ as IDЦЭ_е, Дерево_ё.IDДерева as IDДё, Дерево_ё.Обозначение as Обо_ё, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол as Колё, Дерево_ё.IDЦехаЭ as IDЦЭ_ё, Дерево_ж.IDДерева as IDДж, Дерево_ж.Обозначение as Обо_ж, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол as Колж, Дерево_ж.IDЦехаЭ as IDЦЭ_ж, Дерево_з.IDДерева as IDДз, Дерево_з.Обозначение as Обо_з, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол as Колз, Дерево_з.IDЦехаЭ as IDЦЭ_з, Дерево_и.IDДерева as IDДи, Дерево_и.Обозначение as Обо_и, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол as Коли, Дерево_и.IDЦехаЭ as IDЦЭ_и, Дерево_й.IDДерева as IDДй, Дерево_й.Обозначение as Обо_й, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол as Колй, Дерево_й.IDЦехаЭ as IDЦЭ_й, Дерево_к.IDДерева as IDДк, Дерево_к.Обозначение as Обо_к, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол as Колк, Дерево_к.IDЦехаЭ as IDЦЭ_к, Дерево_л.IDДерева, Дерево_л.Обозначение as Обо_л, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол as Колл, Дерево_л.IDЦехаЭ as IDЦЭ_л, Дерево_м.IDДерева as IDДм, Дерево_м.Обозначение as Обо_м, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол as Колм, Дерево_м.IDЦехаЭ as IDЦЭ_м, Дерево_н.IDДерева as IDДн, Дерево_н.Обозначение as Обо_н, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол*Дерево_н.Кол as Колн, Дерево_н.IDЦехаЭ as IDЦЭ_н\n"
            + "FROM ((((((((((((((Элементы LEFT JOIN Дерево AS Дерево_а ON Элементы.IDЭлементов = Дерево_а.Родитель) LEFT JOIN Дерево AS Дерево_б ON Дерево_а.Обозначение = Дерево_б.Родитель) LEFT JOIN Дерево AS Дерево_в ON Дерево_б.Обозначение = Дерево_в.Родитель) LEFT JOIN Дерево AS Дерево_г ON Дерево_в.Обозначение = Дерево_г.Родитель) LEFT JOIN Дерево AS Дерево_д ON Дерево_г.Обозначение = Дерево_д.Родитель) LEFT JOIN Дерево AS Дерево_е ON Дерево_д.Обозначение = Дерево_е.Родитель) LEFT JOIN Дерево AS Дерево_ё ON Дерево_е.Обозначение = Дерево_ё.Родитель) LEFT JOIN Дерево AS Дерево_ж ON Дерево_ё.Обозначение = Дерево_ж.Родитель) LEFT JOIN Дерево AS Дерево_з ON Дерево_ж.Обозначение = Дерево_з.Родитель) LEFT JOIN Дерево AS Дерево_и ON Дерево_з.Обозначение = Дерево_и.Родитель) LEFT JOIN Дерево AS Дерево_й ON Дерево_и.Обозначение = Дерево_й.Родитель) LEFT JOIN Дерево AS Дерево_к ON Дерево_й.Обозначение = Дерево_к.Родитель) LEFT JOIN Дерево AS Дерево_л ON Дерево_к.Обозначение = Дерево_л.Родитель) LEFT JOIN Дерево AS Дерево_м ON Дерево_л.Обозначение = Дерево_м.Родитель) LEFT JOIN Дерево AS Дерево_н ON Дерево_м.Обозначение = Дерево_н.Родитель\n"
            + "WHERE (((Элементы.Обозначение)=\"Узел\"))) as table7 where Обо_ё is not null)\n"
            + "union all\n"
            + "(select DISTINCT IDДж, Обо_ж, Колж, IDЦЭ_ж, IDДа+\"\"+IDДб+\"\"+IDДв+\"\"+IDДг+\"\"+IDДд+\"\"+IDДе+\"\"+IDДё as IDД from (SELECT DISTINCT Дерево_а.IDДерева as IDДа, Дерево_а.Обозначение as Обо_а, Дерево_а.Кол as Кол, Дерево_а.IDЦехаЭ as IDЦЭ_а, Дерево_б.IDДерева as IDДб, Дерево_б.Обозначение as Обо_б, Дерево_а.Кол*Дерево_б.Кол as Колб, Дерево_б.IDЦехаЭ as IDЦЭ_б, Дерево_в.IDДерева as IDДв, Дерево_в.Обозначение as Обо_в, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол as Колв, Дерево_в.IDЦехаЭ as IDЦЭ_в, Дерево_г.IDДерева as IDДг, Дерево_г.Обозначение as Обо_г, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол as Колг, Дерево_г.IDЦехаЭ as IDЦЭ_г, Дерево_д.IDДерева as IDДд, Дерево_д.Обозначение as Обо_д, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол as Колд, Дерево_д.IDЦехаЭ as IDЦЭ_д, Дерево_е.IDДерева as IDДе, Дерево_е.Обозначение as Обо_е, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол as Коле, Дерево_е.IDЦехаЭ as IDЦЭ_е, Дерево_ё.IDДерева as IDДё, Дерево_ё.Обозначение as Обо_ё, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол as Колё, Дерево_ё.IDЦехаЭ as IDЦЭ_ё, Дерево_ж.IDДерева as IDДж, Дерево_ж.Обозначение as Обо_ж, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол as Колж, Дерево_ж.IDЦехаЭ as IDЦЭ_ж, Дерево_з.IDДерева as IDДз, Дерево_з.Обозначение as Обо_з, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол as Колз, Дерево_з.IDЦехаЭ as IDЦЭ_з, Дерево_и.IDДерева as IDДи, Дерево_и.Обозначение as Обо_и, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол as Коли, Дерево_и.IDЦехаЭ as IDЦЭ_и, Дерево_й.IDДерева as IDДй, Дерево_й.Обозначение as Обо_й, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол as Колй, Дерево_й.IDЦехаЭ as IDЦЭ_й, Дерево_к.IDДерева as IDДк, Дерево_к.Обозначение as Обо_к, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол as Колк, Дерево_к.IDЦехаЭ as IDЦЭ_к, Дерево_л.IDДерева, Дерево_л.Обозначение as Обо_л, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол as Колл, Дерево_л.IDЦехаЭ as IDЦЭ_л, Дерево_м.IDДерева as IDДм, Дерево_м.Обозначение as Обо_м, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол as Колм, Дерево_м.IDЦехаЭ as IDЦЭ_м, Дерево_н.IDДерева as IDДн, Дерево_н.Обозначение as Обо_н, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол*Дерево_н.Кол as Колн, Дерево_н.IDЦехаЭ as IDЦЭ_н\n"
            + "FROM ((((((((((((((Элементы LEFT JOIN Дерево AS Дерево_а ON Элементы.IDЭлементов = Дерево_а.Родитель) LEFT JOIN Дерево AS Дерево_б ON Дерево_а.Обозначение = Дерево_б.Родитель) LEFT JOIN Дерево AS Дерево_в ON Дерево_б.Обозначение = Дерево_в.Родитель) LEFT JOIN Дерево AS Дерево_г ON Дерево_в.Обозначение = Дерево_г.Родитель) LEFT JOIN Дерево AS Дерево_д ON Дерево_г.Обозначение = Дерево_д.Родитель) LEFT JOIN Дерево AS Дерево_е ON Дерево_д.Обозначение = Дерево_е.Родитель) LEFT JOIN Дерево AS Дерево_ё ON Дерево_е.Обозначение = Дерево_ё.Родитель) LEFT JOIN Дерево AS Дерево_ж ON Дерево_ё.Обозначение = Дерево_ж.Родитель) LEFT JOIN Дерево AS Дерево_з ON Дерево_ж.Обозначение = Дерево_з.Родитель) LEFT JOIN Дерево AS Дерево_и ON Дерево_з.Обозначение = Дерево_и.Родитель) LEFT JOIN Дерево AS Дерево_й ON Дерево_и.Обозначение = Дерево_й.Родитель) LEFT JOIN Дерево AS Дерево_к ON Дерево_й.Обозначение = Дерево_к.Родитель) LEFT JOIN Дерево AS Дерево_л ON Дерево_к.Обозначение = Дерево_л.Родитель) LEFT JOIN Дерево AS Дерево_м ON Дерево_л.Обозначение = Дерево_м.Родитель) LEFT JOIN Дерево AS Дерево_н ON Дерево_м.Обозначение = Дерево_н.Родитель\n"
            + "WHERE (((Элементы.Обозначение)=\"Узел\"))) as table8 where Обо_ж is not null)";
    String strq2 = "union all\n"
            + "(select DISTINCT IDДз, Обо_з, Колз, IDЦЭ_з, IDДа+\"\"+IDДб+\"\"+IDДв+\"\"+IDДг+\"\"+IDДд+\"\"+IDДе+\"\"+IDДё+\"\"+IDДж as IDД from (SELECT DISTINCT Дерево_а.IDДерева as IDДа, Дерево_а.Обозначение as Обо_а, Дерево_а.Кол as Кол, Дерево_а.IDЦехаЭ as IDЦЭ_а, Дерево_б.IDДерева as IDДб, Дерево_б.Обозначение as Обо_б, Дерево_а.Кол*Дерево_б.Кол as Колб, Дерево_б.IDЦехаЭ as IDЦЭ_б, Дерево_в.IDДерева as IDДв, Дерево_в.Обозначение as Обо_в, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол as Колв, Дерево_в.IDЦехаЭ as IDЦЭ_в, Дерево_г.IDДерева as IDДг, Дерево_г.Обозначение as Обо_г, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол as Колг, Дерево_г.IDЦехаЭ as IDЦЭ_г, Дерево_д.IDДерева as IDДд, Дерево_д.Обозначение as Обо_д, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол as Колд, Дерево_д.IDЦехаЭ as IDЦЭ_д, Дерево_е.IDДерева as IDДе, Дерево_е.Обозначение as Обо_е, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол as Коле, Дерево_е.IDЦехаЭ as IDЦЭ_е, Дерево_ё.IDДерева as IDДё, Дерево_ё.Обозначение as Обо_ё, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол as Колё, Дерево_ё.IDЦехаЭ as IDЦЭ_ё, Дерево_ж.IDДерева as IDДж, Дерево_ж.Обозначение as Обо_ж, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол as Колж, Дерево_ж.IDЦехаЭ as IDЦЭ_ж, Дерево_з.IDДерева as IDДз, Дерево_з.Обозначение as Обо_з, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол as Колз, Дерево_з.IDЦехаЭ as IDЦЭ_з, Дерево_и.IDДерева as IDДи, Дерево_и.Обозначение as Обо_и, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол as Коли, Дерево_и.IDЦехаЭ as IDЦЭ_и, Дерево_й.IDДерева as IDДй, Дерево_й.Обозначение as Обо_й, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол as Колй, Дерево_й.IDЦехаЭ as IDЦЭ_й, Дерево_к.IDДерева as IDДк, Дерево_к.Обозначение as Обо_к, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол as Колк, Дерево_к.IDЦехаЭ as IDЦЭ_к, Дерево_л.IDДерева, Дерево_л.Обозначение as Обо_л, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол as Колл, Дерево_л.IDЦехаЭ as IDЦЭ_л, Дерево_м.IDДерева as IDДм, Дерево_м.Обозначение as Обо_м, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол as Колм, Дерево_м.IDЦехаЭ as IDЦЭ_м, Дерево_н.IDДерева as IDДн, Дерево_н.Обозначение as Обо_н, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол*Дерево_н.Кол as Колн, Дерево_н.IDЦехаЭ as IDЦЭ_н\n"
            + "FROM ((((((((((((((Элементы LEFT JOIN Дерево AS Дерево_а ON Элементы.IDЭлементов = Дерево_а.Родитель) LEFT JOIN Дерево AS Дерево_б ON Дерево_а.Обозначение = Дерево_б.Родитель) LEFT JOIN Дерево AS Дерево_в ON Дерево_б.Обозначение = Дерево_в.Родитель) LEFT JOIN Дерево AS Дерево_г ON Дерево_в.Обозначение = Дерево_г.Родитель) LEFT JOIN Дерево AS Дерево_д ON Дерево_г.Обозначение = Дерево_д.Родитель) LEFT JOIN Дерево AS Дерево_е ON Дерево_д.Обозначение = Дерево_е.Родитель) LEFT JOIN Дерево AS Дерево_ё ON Дерево_е.Обозначение = Дерево_ё.Родитель) LEFT JOIN Дерево AS Дерево_ж ON Дерево_ё.Обозначение = Дерево_ж.Родитель) LEFT JOIN Дерево AS Дерево_з ON Дерево_ж.Обозначение = Дерево_з.Родитель) LEFT JOIN Дерево AS Дерево_и ON Дерево_з.Обозначение = Дерево_и.Родитель) LEFT JOIN Дерево AS Дерево_й ON Дерево_и.Обозначение = Дерево_й.Родитель) LEFT JOIN Дерево AS Дерево_к ON Дерево_й.Обозначение = Дерево_к.Родитель) LEFT JOIN Дерево AS Дерево_л ON Дерево_к.Обозначение = Дерево_л.Родитель) LEFT JOIN Дерево AS Дерево_м ON Дерево_л.Обозначение = Дерево_м.Родитель) LEFT JOIN Дерево AS Дерево_н ON Дерево_м.Обозначение = Дерево_н.Родитель\n"
            + "WHERE (((Элементы.Обозначение)=\"Узел\"))) as table9 where Обо_з is not null)\n"
            + "union all\n"
            + "(select DISTINCT IDДи, Обо_и, Коли, IDЦЭ_и, IDДа+\"\"+IDДб+\"\"+IDДв+\"\"+IDДг+\"\"+IDДд+\"\"+IDДе+\"\"+IDДё+\"\"+IDДж+\"\"+IDДз as IDД from (SELECT DISTINCT Дерево_а.IDДерева as IDДа, Дерево_а.Обозначение as Обо_а, Дерево_а.Кол as Кол, Дерево_а.IDЦехаЭ as IDЦЭ_а, Дерево_б.IDДерева as IDДб, Дерево_б.Обозначение as Обо_б, Дерево_а.Кол*Дерево_б.Кол as Колб, Дерево_б.IDЦехаЭ as IDЦЭ_б, Дерево_в.IDДерева as IDДв, Дерево_в.Обозначение as Обо_в, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол as Колв, Дерево_в.IDЦехаЭ as IDЦЭ_в, Дерево_г.IDДерева as IDДг, Дерево_г.Обозначение as Обо_г, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол as Колг, Дерево_г.IDЦехаЭ as IDЦЭ_г, Дерево_д.IDДерева as IDДд, Дерево_д.Обозначение as Обо_д, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол as Колд, Дерево_д.IDЦехаЭ as IDЦЭ_д, Дерево_е.IDДерева as IDДе, Дерево_е.Обозначение as Обо_е, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол as Коле, Дерево_е.IDЦехаЭ as IDЦЭ_е, Дерево_ё.IDДерева as IDДё, Дерево_ё.Обозначение as Обо_ё, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол as Колё, Дерево_ё.IDЦехаЭ as IDЦЭ_ё, Дерево_ж.IDДерева as IDДж, Дерево_ж.Обозначение as Обо_ж, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол as Колж, Дерево_ж.IDЦехаЭ as IDЦЭ_ж, Дерево_з.IDДерева as IDДз, Дерево_з.Обозначение as Обо_з, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол as Колз, Дерево_з.IDЦехаЭ as IDЦЭ_з, Дерево_и.IDДерева as IDДи, Дерево_и.Обозначение as Обо_и, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол as Коли, Дерево_и.IDЦехаЭ as IDЦЭ_и, Дерево_й.IDДерева as IDДй, Дерево_й.Обозначение as Обо_й, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол as Колй, Дерево_й.IDЦехаЭ as IDЦЭ_й, Дерево_к.IDДерева as IDДк, Дерево_к.Обозначение as Обо_к, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол as Колк, Дерево_к.IDЦехаЭ as IDЦЭ_к, Дерево_л.IDДерева, Дерево_л.Обозначение as Обо_л, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол as Колл, Дерево_л.IDЦехаЭ as IDЦЭ_л, Дерево_м.IDДерева as IDДм, Дерево_м.Обозначение as Обо_м, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол as Колм, Дерево_м.IDЦехаЭ as IDЦЭ_м, Дерево_н.IDДерева as IDДн, Дерево_н.Обозначение as Обо_н, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол*Дерево_н.Кол as Колн, Дерево_н.IDЦехаЭ as IDЦЭ_н\n"
            + "FROM ((((((((((((((Элементы LEFT JOIN Дерево AS Дерево_а ON Элементы.IDЭлементов = Дерево_а.Родитель) LEFT JOIN Дерево AS Дерево_б ON Дерево_а.Обозначение = Дерево_б.Родитель) LEFT JOIN Дерево AS Дерево_в ON Дерево_б.Обозначение = Дерево_в.Родитель) LEFT JOIN Дерево AS Дерево_г ON Дерево_в.Обозначение = Дерево_г.Родитель) LEFT JOIN Дерево AS Дерево_д ON Дерево_г.Обозначение = Дерево_д.Родитель) LEFT JOIN Дерево AS Дерево_е ON Дерево_д.Обозначение = Дерево_е.Родитель) LEFT JOIN Дерево AS Дерево_ё ON Дерево_е.Обозначение = Дерево_ё.Родитель) LEFT JOIN Дерево AS Дерево_ж ON Дерево_ё.Обозначение = Дерево_ж.Родитель) LEFT JOIN Дерево AS Дерево_з ON Дерево_ж.Обозначение = Дерево_з.Родитель) LEFT JOIN Дерево AS Дерево_и ON Дерево_з.Обозначение = Дерево_и.Родитель) LEFT JOIN Дерево AS Дерево_й ON Дерево_и.Обозначение = Дерево_й.Родитель) LEFT JOIN Дерево AS Дерево_к ON Дерево_й.Обозначение = Дерево_к.Родитель) LEFT JOIN Дерево AS Дерево_л ON Дерево_к.Обозначение = Дерево_л.Родитель) LEFT JOIN Дерево AS Дерево_м ON Дерево_л.Обозначение = Дерево_м.Родитель) LEFT JOIN Дерево AS Дерево_н ON Дерево_м.Обозначение = Дерево_н.Родитель\n"
            + "WHERE (((Элементы.Обозначение)=\"Узел\"))) as table10 where Обо_и is not null)\n"
            + "union all\n"
            + "(select DISTINCT IDДй, Обо_й, Колй, IDЦЭ_й, IDДа+\"\"+IDДб+\"\"+IDДв+\"\"+IDДг+\"\"+IDДд+\"\"+IDДе+\"\"+IDДё+\"\"+IDДж+\"\"+IDДз+\"\"+IDДи as IDД from (SELECT DISTINCT Дерево_а.IDДерева as IDДа, Дерево_а.Обозначение as Обо_а, Дерево_а.Кол as Кол, Дерево_а.IDЦехаЭ as IDЦЭ_а, Дерево_б.IDДерева as IDДб, Дерево_б.Обозначение as Обо_б, Дерево_а.Кол*Дерево_б.Кол as Колб, Дерево_б.IDЦехаЭ as IDЦЭ_б, Дерево_в.IDДерева as IDДв, Дерево_в.Обозначение as Обо_в, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол as Колв, Дерево_в.IDЦехаЭ as IDЦЭ_в, Дерево_г.IDДерева as IDДг, Дерево_г.Обозначение as Обо_г, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол as Колг, Дерево_г.IDЦехаЭ as IDЦЭ_г, Дерево_д.IDДерева as IDДд, Дерево_д.Обозначение as Обо_д, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол as Колд, Дерево_д.IDЦехаЭ as IDЦЭ_д, Дерево_е.IDДерева as IDДе, Дерево_е.Обозначение as Обо_е, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол as Коле, Дерево_е.IDЦехаЭ as IDЦЭ_е, Дерево_ё.IDДерева as IDДё, Дерево_ё.Обозначение as Обо_ё, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол as Колё, Дерево_ё.IDЦехаЭ as IDЦЭ_ё, Дерево_ж.IDДерева as IDДж, Дерево_ж.Обозначение as Обо_ж, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол as Колж, Дерево_ж.IDЦехаЭ as IDЦЭ_ж, Дерево_з.IDДерева as IDДз, Дерево_з.Обозначение as Обо_з, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол as Колз, Дерево_з.IDЦехаЭ as IDЦЭ_з, Дерево_и.IDДерева as IDДи, Дерево_и.Обозначение as Обо_и, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол as Коли, Дерево_и.IDЦехаЭ as IDЦЭ_и, Дерево_й.IDДерева as IDДй, Дерево_й.Обозначение as Обо_й, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол as Колй, Дерево_й.IDЦехаЭ as IDЦЭ_й, Дерево_к.IDДерева as IDДк, Дерево_к.Обозначение as Обо_к, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол as Колк, Дерево_к.IDЦехаЭ as IDЦЭ_к, Дерево_л.IDДерева, Дерево_л.Обозначение as Обо_л, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол as Колл, Дерево_л.IDЦехаЭ as IDЦЭ_л, Дерево_м.IDДерева as IDДм, Дерево_м.Обозначение as Обо_м, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол as Колм, Дерево_м.IDЦехаЭ as IDЦЭ_м, Дерево_н.IDДерева as IDДн, Дерево_н.Обозначение as Обо_н, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол*Дерево_н.Кол as Колн, Дерево_н.IDЦехаЭ as IDЦЭ_н\n"
            + "FROM ((((((((((((((Элементы LEFT JOIN Дерево AS Дерево_а ON Элементы.IDЭлементов = Дерево_а.Родитель) LEFT JOIN Дерево AS Дерево_б ON Дерево_а.Обозначение = Дерево_б.Родитель) LEFT JOIN Дерево AS Дерево_в ON Дерево_б.Обозначение = Дерево_в.Родитель) LEFT JOIN Дерево AS Дерево_г ON Дерево_в.Обозначение = Дерево_г.Родитель) LEFT JOIN Дерево AS Дерево_д ON Дерево_г.Обозначение = Дерево_д.Родитель) LEFT JOIN Дерево AS Дерево_е ON Дерево_д.Обозначение = Дерево_е.Родитель) LEFT JOIN Дерево AS Дерево_ё ON Дерево_е.Обозначение = Дерево_ё.Родитель) LEFT JOIN Дерево AS Дерево_ж ON Дерево_ё.Обозначение = Дерево_ж.Родитель) LEFT JOIN Дерево AS Дерево_з ON Дерево_ж.Обозначение = Дерево_з.Родитель) LEFT JOIN Дерево AS Дерево_и ON Дерево_з.Обозначение = Дерево_и.Родитель) LEFT JOIN Дерево AS Дерево_й ON Дерево_и.Обозначение = Дерево_й.Родитель) LEFT JOIN Дерево AS Дерево_к ON Дерево_й.Обозначение = Дерево_к.Родитель) LEFT JOIN Дерево AS Дерево_л ON Дерево_к.Обозначение = Дерево_л.Родитель) LEFT JOIN Дерево AS Дерево_м ON Дерево_л.Обозначение = Дерево_м.Родитель) LEFT JOIN Дерево AS Дерево_н ON Дерево_м.Обозначение = Дерево_н.Родитель\n"
            + "WHERE (((Элементы.Обозначение)=\"Узел\"))) as table11 where Обо_й is not null)\n"
            + "union all\n"
            + "(select DISTINCT IDДк, Обо_к, Колк, IDЦЭ_к, IDДа+\"\"+IDДб+\"\"+IDДв+\"\"+IDДг+\"\"+IDДд+\"\"+IDДе+\"\"+IDДё+\"\"+IDДж+\"\"+IDДз+\"\"+IDДи+\"\"+IDДй as IDД from (SELECT DISTINCT Дерево_а.IDДерева as IDДа, Дерево_а.Обозначение as Обо_а, Дерево_а.Кол as Кол, Дерево_а.IDЦехаЭ as IDЦЭ_а, Дерево_б.IDДерева as IDДб, Дерево_б.Обозначение as Обо_б, Дерево_а.Кол*Дерево_б.Кол as Колб, Дерево_б.IDЦехаЭ as IDЦЭ_б, Дерево_в.IDДерева as IDДв, Дерево_в.Обозначение as Обо_в, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол as Колв, Дерево_в.IDЦехаЭ as IDЦЭ_в, Дерево_г.IDДерева as IDДг, Дерево_г.Обозначение as Обо_г, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол as Колг, Дерево_г.IDЦехаЭ as IDЦЭ_г, Дерево_д.IDДерева as IDДд, Дерево_д.Обозначение as Обо_д, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол as Колд, Дерево_д.IDЦехаЭ as IDЦЭ_д, Дерево_е.IDДерева as IDДе, Дерево_е.Обозначение as Обо_е, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол as Коле, Дерево_е.IDЦехаЭ as IDЦЭ_е, Дерево_ё.IDДерева as IDДё, Дерево_ё.Обозначение as Обо_ё, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол as Колё, Дерево_ё.IDЦехаЭ as IDЦЭ_ё, Дерево_ж.IDДерева as IDДж, Дерево_ж.Обозначение as Обо_ж, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол as Колж, Дерево_ж.IDЦехаЭ as IDЦЭ_ж, Дерево_з.IDДерева as IDДз, Дерево_з.Обозначение as Обо_з, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол as Колз, Дерево_з.IDЦехаЭ as IDЦЭ_з, Дерево_и.IDДерева as IDДи, Дерево_и.Обозначение as Обо_и, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол as Коли, Дерево_и.IDЦехаЭ as IDЦЭ_и, Дерево_й.IDДерева as IDДй, Дерево_й.Обозначение as Обо_й, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол as Колй, Дерево_й.IDЦехаЭ as IDЦЭ_й, Дерево_к.IDДерева as IDДк, Дерево_к.Обозначение as Обо_к, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол as Колк, Дерево_к.IDЦехаЭ as IDЦЭ_к, Дерево_л.IDДерева, Дерево_л.Обозначение as Обо_л, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол as Колл, Дерево_л.IDЦехаЭ as IDЦЭ_л, Дерево_м.IDДерева as IDДм, Дерево_м.Обозначение as Обо_м, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол as Колм, Дерево_м.IDЦехаЭ as IDЦЭ_м, Дерево_н.IDДерева as IDДн, Дерево_н.Обозначение as Обо_н, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол*Дерево_н.Кол as Колн, Дерево_н.IDЦехаЭ as IDЦЭ_н\n"
            + "FROM ((((((((((((((Элементы LEFT JOIN Дерево AS Дерево_а ON Элементы.IDЭлементов = Дерево_а.Родитель) LEFT JOIN Дерево AS Дерево_б ON Дерево_а.Обозначение = Дерево_б.Родитель) LEFT JOIN Дерево AS Дерево_в ON Дерево_б.Обозначение = Дерево_в.Родитель) LEFT JOIN Дерево AS Дерево_г ON Дерево_в.Обозначение = Дерево_г.Родитель) LEFT JOIN Дерево AS Дерево_д ON Дерево_г.Обозначение = Дерево_д.Родитель) LEFT JOIN Дерево AS Дерево_е ON Дерево_д.Обозначение = Дерево_е.Родитель) LEFT JOIN Дерево AS Дерево_ё ON Дерево_е.Обозначение = Дерево_ё.Родитель) LEFT JOIN Дерево AS Дерево_ж ON Дерево_ё.Обозначение = Дерево_ж.Родитель) LEFT JOIN Дерево AS Дерево_з ON Дерево_ж.Обозначение = Дерево_з.Родитель) LEFT JOIN Дерево AS Дерево_и ON Дерево_з.Обозначение = Дерево_и.Родитель) LEFT JOIN Дерево AS Дерево_й ON Дерево_и.Обозначение = Дерево_й.Родитель) LEFT JOIN Дерево AS Дерево_к ON Дерево_й.Обозначение = Дерево_к.Родитель) LEFT JOIN Дерево AS Дерево_л ON Дерево_к.Обозначение = Дерево_л.Родитель) LEFT JOIN Дерево AS Дерево_м ON Дерево_л.Обозначение = Дерево_м.Родитель) LEFT JOIN Дерево AS Дерево_н ON Дерево_м.Обозначение = Дерево_н.Родитель\n"
            + "WHERE (((Элементы.Обозначение)=\"Узел\"))) as table12 where Обо_к is not null)\n"
            + "union all\n"
            + "(select DISTINCT IDДл, Обо_л, Колл, IDЦЭ_л, IDДа+\"\"+IDДб+\"\"+IDДв+\"\"+IDДг+\"\"+IDДд+\"\"+IDДе+\"\"+IDДё+\"\"+IDДж+\"\"+IDДз+\"\"+IDДи+\"\"+IDДй+\"\"+IDДк as IDД from (SELECT DISTINCT Дерево_а.IDДерева as IDДа, Дерево_а.Обозначение as Обо_а, Дерево_а.Кол as Кол, Дерево_а.IDЦехаЭ as IDЦЭ_а, Дерево_б.IDДерева as IDДб, Дерево_б.Обозначение as Обо_б, Дерево_а.Кол*Дерево_б.Кол as Колб, Дерево_б.IDЦехаЭ as IDЦЭ_б, Дерево_в.IDДерева as IDДв, Дерево_в.Обозначение as Обо_в, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол as Колв, Дерево_в.IDЦехаЭ as IDЦЭ_в, Дерево_г.IDДерева as IDДг, Дерево_г.Обозначение as Обо_г, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол as Колг, Дерево_г.IDЦехаЭ as IDЦЭ_г, Дерево_д.IDДерева as IDДд, Дерево_д.Обозначение as Обо_д, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол as Колд, Дерево_д.IDЦехаЭ as IDЦЭ_д, Дерево_е.IDДерева as IDДе, Дерево_е.Обозначение as Обо_е, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол as Коле, Дерево_е.IDЦехаЭ as IDЦЭ_е, Дерево_ё.IDДерева as IDДё, Дерево_ё.Обозначение as Обо_ё, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол as Колё, Дерево_ё.IDЦехаЭ as IDЦЭ_ё, Дерево_ж.IDДерева as IDДж, Дерево_ж.Обозначение as Обо_ж, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол as Колж, Дерево_ж.IDЦехаЭ as IDЦЭ_ж, Дерево_з.IDДерева as IDДз, Дерево_з.Обозначение as Обо_з, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол as Колз, Дерево_з.IDЦехаЭ as IDЦЭ_з, Дерево_и.IDДерева as IDДи, Дерево_и.Обозначение as Обо_и, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол as Коли, Дерево_и.IDЦехаЭ as IDЦЭ_и, Дерево_й.IDДерева as IDДй, Дерево_й.Обозначение as Обо_й, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол as Колй, Дерево_й.IDЦехаЭ as IDЦЭ_й, Дерево_к.IDДерева as IDДк, Дерево_к.Обозначение as Обо_к, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол as Колк, Дерево_к.IDЦехаЭ as IDЦЭ_к, Дерево_л.IDДерева as IDДл, Дерево_л.Обозначение as Обо_л, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол as Колл, Дерево_л.IDЦехаЭ as IDЦЭ_л, Дерево_м.IDДерева as IDДм, Дерево_м.Обозначение as Обо_м, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол as Колм, Дерево_м.IDЦехаЭ as IDЦЭ_м, Дерево_н.IDДерева as IDДн, Дерево_н.Обозначение as Обо_н, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол*Дерево_н.Кол as Колн, Дерево_н.IDЦехаЭ as IDЦЭ_н\n"
            + "FROM ((((((((((((((Элементы LEFT JOIN Дерево AS Дерево_а ON Элементы.IDЭлементов = Дерево_а.Родитель) LEFT JOIN Дерево AS Дерево_б ON Дерево_а.Обозначение = Дерево_б.Родитель) LEFT JOIN Дерево AS Дерево_в ON Дерево_б.Обозначение = Дерево_в.Родитель) LEFT JOIN Дерево AS Дерево_г ON Дерево_в.Обозначение = Дерево_г.Родитель) LEFT JOIN Дерево AS Дерево_д ON Дерево_г.Обозначение = Дерево_д.Родитель) LEFT JOIN Дерево AS Дерево_е ON Дерево_д.Обозначение = Дерево_е.Родитель) LEFT JOIN Дерево AS Дерево_ё ON Дерево_е.Обозначение = Дерево_ё.Родитель) LEFT JOIN Дерево AS Дерево_ж ON Дерево_ё.Обозначение = Дерево_ж.Родитель) LEFT JOIN Дерево AS Дерево_з ON Дерево_ж.Обозначение = Дерево_з.Родитель) LEFT JOIN Дерево AS Дерево_и ON Дерево_з.Обозначение = Дерево_и.Родитель) LEFT JOIN Дерево AS Дерево_й ON Дерево_и.Обозначение = Дерево_й.Родитель) LEFT JOIN Дерево AS Дерево_к ON Дерево_й.Обозначение = Дерево_к.Родитель) LEFT JOIN Дерево AS Дерево_л ON Дерево_к.Обозначение = Дерево_л.Родитель) LEFT JOIN Дерево AS Дерево_м ON Дерево_л.Обозначение = Дерево_м.Родитель) LEFT JOIN Дерево AS Дерево_н ON Дерево_м.Обозначение = Дерево_н.Родитель\n"
            + "WHERE (((Элементы.Обозначение)=\"Узел\"))) as table13 where Обо_л is not null)\n"
            + "union all\n"
            + "(select DISTINCT IDДм, Обо_м, Колм, IDЦЭ_м, IDДа+\"\"+IDДб+\"\"+IDДв+\"\"+IDДг+\"\"+IDДд+\"\"+IDДе+\"\"+IDДё+\"\"+IDДж+\"\"+IDДз+\"\"+IDДи+\"\"+IDДй+\"\"+IDДк+\"\"+IDДл as IDД from (SELECT DISTINCT Дерево_а.IDДерева as IDДа, Дерево_а.Обозначение as Обо_а, Дерево_а.Кол as Кол, Дерево_а.IDЦехаЭ as IDЦЭ_а, Дерево_б.IDДерева as IDДб, Дерево_б.Обозначение as Обо_б, Дерево_а.Кол*Дерево_б.Кол as Колб, Дерево_б.IDЦехаЭ as IDЦЭ_б, Дерево_в.IDДерева as IDДв, Дерево_в.Обозначение as Обо_в, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол as Колв, Дерево_в.IDЦехаЭ as IDЦЭ_в, Дерево_г.IDДерева as IDДг, Дерево_г.Обозначение as Обо_г, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол as Колг, Дерево_г.IDЦехаЭ as IDЦЭ_г, Дерево_д.IDДерева as IDДд, Дерево_д.Обозначение as Обо_д, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол as Колд, Дерево_д.IDЦехаЭ as IDЦЭ_д, Дерево_е.IDДерева as IDДе, Дерево_е.Обозначение as Обо_е, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол as Коле, Дерево_е.IDЦехаЭ as IDЦЭ_е, Дерево_ё.IDДерева as IDДё, Дерево_ё.Обозначение as Обо_ё, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол as Колё, Дерево_ё.IDЦехаЭ as IDЦЭ_ё, Дерево_ж.IDДерева as IDДж, Дерево_ж.Обозначение as Обо_ж, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол as Колж, Дерево_ж.IDЦехаЭ as IDЦЭ_ж, Дерево_з.IDДерева as IDДз, Дерево_з.Обозначение as Обо_з, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол as Колз, Дерево_з.IDЦехаЭ as IDЦЭ_з, Дерево_и.IDДерева as IDДи, Дерево_и.Обозначение as Обо_и, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол as Коли, Дерево_и.IDЦехаЭ as IDЦЭ_и, Дерево_й.IDДерева as IDДй, Дерево_й.Обозначение as Обо_й, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол as Колй, Дерево_й.IDЦехаЭ as IDЦЭ_й, Дерево_к.IDДерева as IDДк, Дерево_к.Обозначение as Обо_к, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол as Колк, Дерево_к.IDЦехаЭ as IDЦЭ_к, Дерево_л.IDДерева as IDДл, Дерево_л.Обозначение as Обо_л, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол as Колл, Дерево_л.IDЦехаЭ as IDЦЭ_л, Дерево_м.IDДерева as IDДм, Дерево_м.Обозначение as Обо_м, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол as Колм, Дерево_м.IDЦехаЭ as IDЦЭ_м, Дерево_н.IDДерева as IDДн, Дерево_н.Обозначение as Обо_н, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол*Дерево_н.Кол as Колн, Дерево_н.IDЦехаЭ as IDЦЭ_н\n"
            + "FROM ((((((((((((((Элементы LEFT JOIN Дерево AS Дерево_а ON Элементы.IDЭлементов = Дерево_а.Родитель) LEFT JOIN Дерево AS Дерево_б ON Дерево_а.Обозначение = Дерево_б.Родитель) LEFT JOIN Дерево AS Дерево_в ON Дерево_б.Обозначение = Дерево_в.Родитель) LEFT JOIN Дерево AS Дерево_г ON Дерево_в.Обозначение = Дерево_г.Родитель) LEFT JOIN Дерево AS Дерево_д ON Дерево_г.Обозначение = Дерево_д.Родитель) LEFT JOIN Дерево AS Дерево_е ON Дерево_д.Обозначение = Дерево_е.Родитель) LEFT JOIN Дерево AS Дерево_ё ON Дерево_е.Обозначение = Дерево_ё.Родитель) LEFT JOIN Дерево AS Дерево_ж ON Дерево_ё.Обозначение = Дерево_ж.Родитель) LEFT JOIN Дерево AS Дерево_з ON Дерево_ж.Обозначение = Дерево_з.Родитель) LEFT JOIN Дерево AS Дерево_и ON Дерево_з.Обозначение = Дерево_и.Родитель) LEFT JOIN Дерево AS Дерево_й ON Дерево_и.Обозначение = Дерево_й.Родитель) LEFT JOIN Дерево AS Дерево_к ON Дерево_й.Обозначение = Дерево_к.Родитель) LEFT JOIN Дерево AS Дерево_л ON Дерево_к.Обозначение = Дерево_л.Родитель) LEFT JOIN Дерево AS Дерево_м ON Дерево_л.Обозначение = Дерево_м.Родитель) LEFT JOIN Дерево AS Дерево_н ON Дерево_м.Обозначение = Дерево_н.Родитель\n"
            + "WHERE (((Элементы.Обозначение)=\"Узел\"))) as table14 where Обо_м is not null)\n"
            + "union all\n"
            + "(select DISTINCT IDДн, Обо_н, Колн, IDЦЭ_н, IDДа+\"\"+IDДб+\"\"+IDДв+\"\"+IDДг+\"\"+IDДд+\"\"+IDДе+\"\"+IDДё+\"\"+IDДж+\"\"+IDДз+\"\"+IDДи+\"\"+IDДй+\"\"+IDДк+\"\"+IDДл+\"\"+IDДм as IDД from (SELECT DISTINCT Дерево_а.IDДерева as IDДа, Дерево_а.Обозначение as Обо_а, Дерево_а.Кол as Кол, Дерево_а.IDЦехаЭ as IDЦЭ_а, Дерево_б.IDДерева as IDДб, Дерево_б.Обозначение as Обо_б, Дерево_а.Кол*Дерево_б.Кол as Колб, Дерево_б.IDЦехаЭ as IDЦЭ_б, Дерево_в.IDДерева as IDДв, Дерево_в.Обозначение as Обо_в, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол as Колв, Дерево_в.IDЦехаЭ as IDЦЭ_в, Дерево_г.IDДерева as IDДг, Дерево_г.Обозначение as Обо_г, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол as Колг, Дерево_г.IDЦехаЭ as IDЦЭ_г, Дерево_д.IDДерева as IDДд, Дерево_д.Обозначение as Обо_д, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол as Колд, Дерево_д.IDЦехаЭ as IDЦЭ_д, Дерево_е.IDДерева as IDДе, Дерево_е.Обозначение as Обо_е, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол as Коле, Дерево_е.IDЦехаЭ as IDЦЭ_е, Дерево_ё.IDДерева as IDДё, Дерево_ё.Обозначение as Обо_ё, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол as Колё, Дерево_ё.IDЦехаЭ as IDЦЭ_ё, Дерево_ж.IDДерева as IDДж, Дерево_ж.Обозначение as Обо_ж, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол as Колж, Дерево_ж.IDЦехаЭ as IDЦЭ_ж, Дерево_з.IDДерева as IDДз, Дерево_з.Обозначение as Обо_з, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол as Колз, Дерево_з.IDЦехаЭ as IDЦЭ_з, Дерево_и.IDДерева as IDДи, Дерево_и.Обозначение as Обо_и, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол as Коли, Дерево_и.IDЦехаЭ as IDЦЭ_и, Дерево_й.IDДерева as IDДй, Дерево_й.Обозначение as Обо_й, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол as Колй, Дерево_й.IDЦехаЭ as IDЦЭ_й, Дерево_к.IDДерева as IDДк, Дерево_к.Обозначение as Обо_к, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол as Колк, Дерево_к.IDЦехаЭ as IDЦЭ_к, Дерево_л.IDДерева as IDДл, Дерево_л.Обозначение as Обо_л, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол as Колл, Дерево_л.IDЦехаЭ as IDЦЭ_л, Дерево_м.IDДерева as IDДм, Дерево_м.Обозначение as Обо_м, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол as Колм, Дерево_м.IDЦехаЭ as IDЦЭ_м, Дерево_н.IDДерева as IDДн, Дерево_н.Обозначение as Обо_н, Дерево_а.Кол*Дерево_б.Кол*Дерево_в.Кол*Дерево_г.Кол*Дерево_д.Кол*Дерево_е.Кол*Дерево_ё.Кол*Дерево_ж.Кол*Дерево_з.Кол*Дерево_и.Кол*Дерево_й.Кол*Дерево_к.Кол*Дерево_л.Кол*Дерево_м.Кол*Дерево_н.Кол as Колн, Дерево_н.IDЦехаЭ as IDЦЭ_н\n"
            + "FROM ((((((((((((((Элементы LEFT JOIN Дерево AS Дерево_а ON Элементы.IDЭлементов = Дерево_а.Родитель) LEFT JOIN Дерево AS Дерево_б ON Дерево_а.Обозначение = Дерево_б.Родитель) LEFT JOIN Дерево AS Дерево_в ON Дерево_б.Обозначение = Дерево_в.Родитель) LEFT JOIN Дерево AS Дерево_г ON Дерево_в.Обозначение = Дерево_г.Родитель) LEFT JOIN Дерево AS Дерево_д ON Дерево_г.Обозначение = Дерево_д.Родитель) LEFT JOIN Дерево AS Дерево_е ON Дерево_д.Обозначение = Дерево_е.Родитель) LEFT JOIN Дерево AS Дерево_ё ON Дерево_е.Обозначение = Дерево_ё.Родитель) LEFT JOIN Дерево AS Дерево_ж ON Дерево_ё.Обозначение = Дерево_ж.Родитель) LEFT JOIN Дерево AS Дерево_з ON Дерево_ж.Обозначение = Дерево_з.Родитель) LEFT JOIN Дерево AS Дерево_и ON Дерево_з.Обозначение = Дерево_и.Родитель) LEFT JOIN Дерево AS Дерево_й ON Дерево_и.Обозначение = Дерево_й.Родитель) LEFT JOIN Дерево AS Дерево_к ON Дерево_й.Обозначение = Дерево_к.Родитель) LEFT JOIN Дерево AS Дерево_л ON Дерево_к.Обозначение = Дерево_л.Родитель) LEFT JOIN Дерево AS Дерево_м ON Дерево_л.Обозначение = Дерево_м.Родитель) LEFT JOIN Дерево AS Дерево_н ON Дерево_м.Обозначение = Дерево_н.Родитель\n"
            + "WHERE (((Элементы.Обозначение)=\"Узел\"))) as table15 where Обо_н is not null);";

    private void updateNode(String sql, boolean bool)
    {

        try
        {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, bool);
            preparedStatement.executeUpdate();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    /*
    private ObservableList<Schedule> getScheduleFull(String sql)
    {
        ObservableList<ScheduleFull> dataScheduleFull = FXCollections.observableArrayList();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                dataScheduleFull.add(new ScheduleFull(resultSet.getString("Обозначение") + " " + nf.format(resultSet.getDouble("Кол")) + resultSet.getString("ЕИЭ"),
                        resultSet.getString("Ответственный"),
                        resultSet.getString("Выписано"),
                        resultSet.getString("Примечание"),
                        resultSet.getString("Заготовка"),
                        resultSet.getString("Сборка"),
                        resultSet.getString("Панели"),
                        resultSet.getString("Электрики")));

                id = resultSet.getInt("idЗаказа");
            }

            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
        return dataScheduleFull;
    }
     */
    private ObservableList<Schedule> getSchedule(String sql)
    {
        ObservableList<Schedule> dataSchedule = FXCollections.observableArrayList();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                dataSchedule.add(new Schedule(resultSet.getString("Обозначение") + " " + nf.format(resultSet.getDouble("Кол")) + resultSet.getString("ЕИЭ"),
                        resultSet.getString("Ответственный"),
                        resultSet.getString("Выписано"),
                        resultSet.getString("Примечание")));

                id = resultSet.getInt("idЗаказа");
            }

            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
        return dataSchedule;
    }

    private void fillTreeScheduleFull(TreeItem<ScheduleFull> treeItem)
    {
        for (int i = 0; i < treeItem.getChildren().size(); i++)
        {
            newNodeScheduleFull("SELECT Элементы_1.IDЭлементов, concat_ws(\" \",Элементы_1.Обозначение , Элементы_1.Наименование,\" \") AS Обозначение, Дерево.Кол, Элементы_1.ЕИЭ,График.Ответственный,График.Выписано, График.Примечание, График.Заготовка, График.Сборка, График.Панели, График.Электрики, График.Родитель, заказы.Заказ\n"
                    + "FROM (((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов) LEFT JOIN График ON Элементы_1.IDЭлементов = График.Потомок) LEFT JOIN заказы ON График.Родитель = заказы.idЗаказа\n"
                    + "WHERE (((concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=\"" + treeItem.getChildren().get(i).getValue().getОбозначение().substring(0, treeItem.getChildren().get(i).getValue().getОбозначение().lastIndexOf(" ")) + "\") AND ((Элементы_1.Узел)=True) AND ((заказы.Заказ)=\"" + comboboxSchedule.getSelectionModel().getSelectedItem() + "\" Or (заказы.Заказ) Is Null))\n"
                    + "ORDER BY Дерево.Поз;", treeItem.getChildren().get(i));
            treeItem.setExpanded(true);
            fillTreeScheduleFull(treeItem.getChildren().get(i));
        }
    }

    private void fillTreeSchedule(TreeItem<Schedule> treeItem)
    {
        for (int i = 0; i < treeItem.getChildren().size(); i++)
        {
            newNodeSchedule("SELECT Элементы_1.IDЭлементов, concat_ws(\" \",Элементы_1.Обозначение , Элементы_1.Наименование,\" \") AS Обозначение, Дерево.Кол, Элементы_1.ЕИЭ,График.Ответственный,График.Выписано, График.Примечание, График.Родитель, заказы.Заказ\n"
                    + "FROM (((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов) LEFT JOIN График ON Элементы_1.IDЭлементов = График.Потомок) LEFT JOIN заказы ON График.Родитель = заказы.idЗаказа\n"
                    + "WHERE (((concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=\"" + treeItem.getChildren().get(i).getValue().getОбозначение().substring(0, treeItem.getChildren().get(i).getValue().getОбозначение().lastIndexOf(" ")) + "\") AND ((Элементы_1.Узел)=True) AND ((заказы.Заказ)=\"" + comboboxSchedule.getSelectionModel().getSelectedItem() + "\" Or (заказы.Заказ) Is Null))\n"
                    + "ORDER BY Дерево.Поз;", treeItem.getChildren().get(i));
            treeItem.setExpanded(true);
            fillTreeSchedule(treeItem.getChildren().get(i));
        }
    }

    private void newNodeScheduleFull(String sql, TreeItem<ScheduleFull> treeItem)
    {
        if (!treeItem.getChildren().isEmpty())
        {
            return;
        }
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {

                treeItem.getChildren().add(new TreeItem<ScheduleFull>(new ScheduleFull(resultSet.getString("Обозначение") + nf.format(resultSet.getDouble("Кол")) + resultSet.getString("ЕИЭ"), resultSet.getString("Ответственный"), resultSet.getString("Выписано"), resultSet.getString("Примечание"), resultSet.getString("Заготовка"), resultSet.getString("Сборка"), resultSet.getString("Панели"), resultSet.getString("Электрики"))));
            }
            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }

        }
    }

    private void newNodeSchedule(String sql, TreeItem<Schedule> treeItem)
    {
        if (!treeItem.getChildren().isEmpty())
        {
            return;
        }
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {

                treeItem.getChildren().add(new TreeItem<Schedule>(new Schedule(resultSet.getString("Обозначение") + nf.format(resultSet.getDouble("Кол")) + resultSet.getString("ЕИЭ"), resultSet.getString("Ответственный"), resultSet.getString("Выписано"), resultSet.getString("Примечание"))));
            }
            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }

        }
    }

    private ObservableList<Specification> getElemntsTree(String sql)
    {
        dataSpecificationRow.clear();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                dataSpecificationRow.add(new Specification(
                        Integer.toString(resultSet.getInt(Дерево_Поз + 1)),
                        resultSet.getString(Элементы_1_Обозначение + 1),
                        resultSet.getString(Элементы_1_Наименование + 1),
                        resultSet.getString(Элементы_1_Примечание + 1),
                        nf.format(resultSet.getDouble(Дерево_Кол + 1)),
                        resultSet.getInt(Элементы_1_IDЭлементов + 1),
                        Integer.toString(resultSet.getInt(Материалы_IDМатериалов + 1)),
                        resultSet.getString(Материалы_Материал + 1),
                        Integer.toString(resultSet.getInt(Формулы_IDФормул + 1)),
                        resultSet.getString(Элементы_Обозначение + 1),
                        nf.format(resultSet.getDouble(Элементы_1_а + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_б + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_в + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_г + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_д + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_е + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_НРМ + 1)),
                        resultSet.getString(Материалы_ЕИМ + 1),
                        resultSet.getString(Формулы_ФормулаРасчета + 1),
                        resultSet.getString(Формулы_а + 1),
                        resultSet.getString(Формулы_б + 1),
                        resultSet.getString(Формулы_в + 1),
                        resultSet.getString(Формулы_г + 1),
                        resultSet.getString(Формулы_д + 1),
                        resultSet.getString(Формулы_е + 1),
                        resultSet.getString(Цех_НазваниеЦеха + 1),
                        resultSet.getInt(Дерево_IDДерева + 1),
                        resultSet.getString(Цех_1_НазваниеЦеха + 1),
                        nf.format(resultSet.getDouble(Элементы_1_КоэффициентЭ + 1)),
                        nf.format(resultSet.getDouble(Материалы_КоэффициентМ + 1)),
                        Integer.toString(resultSet.getInt(Элементы_1_КТДЭ + 1)),
                        resultSet.getString(Элементы_1_ЕИЭ + 1),
                        Integer.toString(resultSet.getInt(Материалы_КТДМ + 1)),
                        resultSet.getBoolean(Элементы_1_Узел + 1),
                        ""
                ));

            }
            resultSet.close();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }

        }
        return dataSpecificationRow;
    }

    private void fillTreeSpecification(TreeItem<String> treeItem)
    {
        for (int i = 0; i < treeItem.getChildren().size(); i++)
        {
            newNodeSpecification("SELECT Элементы_1.IDЭлементов, Дерево.Поз, Элементы_1.Обозначение, Элементы_1.Наименование, Элементы_1.примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы.Обозначение, Элементы_1.а, Элементы_1.б, Элементы_1.в, Элементы_1.г, Элементы_1.д, Элементы_1.е, Элементы_1.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы_1.КоэффициентЭ, Материалы.КоэффициентМ, Элементы_1.КТДЭ, Элементы_1.ЕИЭ, Материалы.КТДМ, Элементы_1.Узел\n"
                    + "FROM (Цех RIGHT JOIN ((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) LEFT JOIN ((Элементы AS Элементы_1 LEFT JOIN Материалы ON Элементы_1.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) ON Дерево.Обозначение = Элементы_1.IDЭлементов) ON Цех.IDЦехов = Дерево.IDЦехаЭ) LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов\n"
                    + "WHERE (((concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=\"" + treeItem.getChildren().get(i).getValue().substring(0, treeItem.getChildren().get(i).getValue().lastIndexOf(" ")) + "\"))\n"
                    + "ORDER BY Дерево.Поз;", treeItem.getChildren().get(i));
            fillTreeSpecification(treeItem.getChildren().get(i));
        }
    }

    private void newNodeSpecification(String sql, TreeItem<String> treeItem)
    {
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                dataSpecificationRow.add(new Specification(
                        Integer.toString(resultSet.getInt(Дерево_Поз + 1)),
                        resultSet.getString(Элементы_1_Обозначение + 1),
                        resultSet.getString(Элементы_1_Наименование + 1),
                        resultSet.getString(Элементы_1_Примечание + 1),
                        nf.format(resultSet.getDouble(Дерево_Кол + 1)),
                        resultSet.getInt(Элементы_1_IDЭлементов + 1),
                        Integer.toString(resultSet.getInt(Материалы_IDМатериалов + 1)),
                        resultSet.getString(Материалы_Материал + 1),
                        Integer.toString(resultSet.getInt(Формулы_IDФормул + 1)),
                        resultSet.getString(Элементы_Обозначение + 1),
                        nf.format(resultSet.getDouble(Элементы_1_а + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_б + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_в + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_г + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_д + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_е + 1)),
                        nf.format(resultSet.getDouble(Элементы_1_НРМ + 1)),
                        resultSet.getString(Материалы_ЕИМ + 1),
                        resultSet.getString(Формулы_ФормулаРасчета + 1),
                        resultSet.getString(Формулы_а + 1),
                        resultSet.getString(Формулы_б + 1),
                        resultSet.getString(Формулы_в + 1),
                        resultSet.getString(Формулы_г + 1),
                        resultSet.getString(Формулы_д + 1),
                        resultSet.getString(Формулы_е + 1),
                        resultSet.getString(Цех_НазваниеЦеха + 1),
                        resultSet.getInt(Дерево_IDДерева + 1),
                        resultSet.getString(Цех_1_НазваниеЦеха + 1),
                        nf.format(resultSet.getDouble(Элементы_1_КоэффициентЭ + 1)),
                        nf.format(resultSet.getDouble(Материалы_КоэффициентМ + 1)),
                        Integer.toString(resultSet.getInt(Элементы_1_КТДЭ + 1)),
                        resultSet.getString(Элементы_1_ЕИЭ + 1),
                        Integer.toString(resultSet.getInt(Материалы_КТДМ + 1)),
                        resultSet.getBoolean(Элементы_1_Узел + 1),
                        ""
                ));
                if (resultSet.getString("Наименование") == null)
                {
                    treeItem.getChildren().add(new TreeItem<String>(resultSet.getString("Обозначение") + "  " + nf.format(resultSet.getDouble("Кол")) + resultSet.getString("ЕИЭ")));
                }
                else
                {
                    treeItem.getChildren().add(new TreeItem<String>(resultSet.getString("Обозначение") + " " + resultSet.getString("Наименование") + " " + nf.format(resultSet.getDouble("Кол")) + resultSet.getString("ЕИЭ")));
                }
            }
            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }

        }
    }

    private void setElementRow()
    {/*
        if (!comboboxElement.getEditor().getText().equals(labelParent.getText()) && !labelParent.getText().isEmpty())
        {
            String str = comboboxElement.getEditor().getText().replaceAll("\"", "\"\"");

            updateString("INSERT INTO Элементы ( Обозначение )\n"
                    + "VALUES(?);", comboboxElement.getEditor().getText());

            if (tableViewSpecification.getItems().size() == 0)
            {
                addChildToParent("INSERT INTO snm.Дерево ( Родитель, Обозначение, Поз, Кол )\n"
                        + "VALUES(?,?," + 1 + ",0)",
                        getId("SELECT Элементы.IDЭлементов\n"
                                + "FROM Элементы\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + labelParent.getText() + "\"));", "IDЭлементов"),
                        getId("SELECT Элементы.IDЭлементов\n"
                                + "FROM Элементы\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + str + "\"));", "IDЭлементов"));
                dataSpecification.add(0, addRowElement("SELECT Элементы.IDЭлементов, Дерево.Поз, Элементы.Обозначение, Элементы.Наименование, Элементы.Примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы_1.Обозначение, Элементы.а, Элементы.б, Элементы.в, Элементы.г, Элементы.д, Элементы.е, Элементы.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы.КоэффициентЭ, Материалы.КоэффициентМ, Элементы.КТДЭ, Элементы.ЕИЭ, Материалы.КТДМ, Элементы.Узел\n"
                        + "FROM Цех AS Цех_1 RIGHT JOIN (((((Дерево LEFT JOIN Элементы ON Дерево.Обозначение = Элементы.IDЭлементов) LEFT JOIN Материалы ON Элементы.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) LEFT JOIN Элементы AS Элементы_1 ON Дерево.Родитель = Элементы_1.IDЭлементов) LEFT JOIN Цех ON Дерево.IDЦехаЭ = Цех.IDЦехов) ON Цех_1.IDЦехов = Дерево.IDЦехаМ\n"
                        + "WHERE (((Элементы.Обозначение)=\"" + str + "\") AND ((Элементы_1.Обозначение)=\"" + labelParent.getText() + "\"));"));
            }
            else if (tableViewSpecification.getSelectionModel().getSelectedIndex() != -1)
            {
                addChildToParent("INSERT INTO snm.Дерево ( Родитель, Обозначение, Поз, Кол )\n"
                        + "VALUES(?,?," + (1 + Integer.parseInt(((Specification) tableViewSpecification.getSelectionModel().getSelectedItem()).getДерево_Поз())) + ",0)",
                        getId("SELECT Элементы.IDЭлементов\n"
                                + "FROM Элементы\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + labelParent.getText() + "\"));", "IDЭлементов"),
                        getId("SELECT Элементы.IDЭлементов\n"
                                + "FROM Элементы\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + str + "\"));", "IDЭлементов"));
                dataSpecification.add(tableViewSpecification.getSelectionModel().getSelectedIndex() + 1, addRowElement("SELECT Элементы.IDЭлементов, Дерево.Поз, Элементы.Обозначение, Элементы.Наименование, Элементы.Примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы_1.Обозначение, Элементы.а, Элементы.б, Элементы.в, Элементы.г, Элементы.д, Элементы.е, Элементы.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы.КоэффициентЭ, Материалы.КоэффициентМ, Элементы.КТДЭ, Элементы.ЕИЭ, Материалы.КТДМ, Элементы.Узел\n"
                        + "FROM Цех AS Цех_1 RIGHT JOIN (((((Дерево LEFT JOIN Элементы ON Дерево.Обозначение = Элементы.IDЭлементов) LEFT JOIN Материалы ON Элементы.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) LEFT JOIN Элементы AS Элементы_1 ON Дерево.Родитель = Элементы_1.IDЭлементов) LEFT JOIN Цех ON Дерево.IDЦехаЭ = Цех.IDЦехов) ON Цех_1.IDЦехов = Дерево.IDЦехаМ\n"
                        + "WHERE (((Элементы.Обозначение)=\"" + str + "\") AND ((Элементы_1.Обозначение)=\"" + labelParent.getText() + "\"));"));
            }
            else
            {
                addChildToParent("INSERT INTO Дерево ( Родитель, Обозначение, Поз, Кол )\n"
                        + "VALUES(?,?," + (1 + Integer.parseInt(tableViewSpecification.getItems().get(tableViewSpecification.getItems().size() - 1).getДерево_Поз())) + ",0)",
                        getId("SELECT Элементы.IDЭлементов\n"
                                + "FROM Элементы\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + labelParent.getText() + "\"));", "IDЭлементов"),
                        getId("SELECT Элементы.IDЭлементов\n"
                                + "FROM Элементы\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + str + "\"));", "IDЭлементов"));
                dataSpecification.add(tableViewSpecification.getItems().size(), addRowElement("SELECT Элементы.IDЭлементов, Дерево.Поз, Элементы.Обозначение, Элементы.Наименование, Элементы.Примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы_1.Обозначение, Элементы.а, Элементы.б, Элементы.в, Элементы.г, Элементы.д, Элементы.е, Элементы.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы.КоэффициентЭ, Материалы.КоэффициентМ, Элементы.КТДЭ, Элементы.ЕИЭ, Материалы.КТДМ, Элементы.Узел\n"
                        + "FROM Цех AS Цех_1 RIGHT JOIN (((((Дерево LEFT JOIN Элементы ON Дерево.Обозначение = Элементы.IDЭлементов) LEFT JOIN Материалы ON Элементы.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) LEFT JOIN Элементы AS Элементы_1 ON Дерево.Родитель = Элементы_1.IDЭлементов) LEFT JOIN Цех ON Дерево.IDЦехаЭ = Цех.IDЦехов) ON Цех_1.IDЦехов = Дерево.IDЦехаМ\n"
                        + "WHERE (((Элементы.Обозначение)=\"" + str + "\") AND ((Элементы_1.Обозначение)=\"" + labelParent.getText() + "\"));"));
            }
            getCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                    + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                    + "WHERE (((Элементы.Обозначение)=\"" + labelParent.getText().toString().replaceAll("\"", "\"\"") + "\"));", true);
            tableViewSpecification.getSelectionModel().clearSelection();
        }*/
        if (!comboboxElement.getEditor().getText().equals(labelParent.getText()) && !labelParent.getText().isEmpty())
        {
            String str = comboboxElement.getEditor().getText().replaceAll("\"", "\"\"");
            System.out.println(str);
            updateString("INSERT INTO Элементы ( Обозначение )\n"
                    + "VALUES(?);", comboboxElement.getEditor().getText());
            int id;
            if (str.contains("ЛУИФ"))
            {
                id = getId("SELECT цех.IDЦехов, элементы.Обозначение\n"
                        + "FROM элементы LEFT JOIN (дерево LEFT JOIN цех ON дерево.IDЦехаЭ = цех.IDЦехов) ON элементы.IDЭлементов = дерево.Обозначение\n"
                        + "WHERE (((цех.IDЦехов) Is Not Null And (цех.IDЦехов)<>1 And (цех.IDЦехов)<>4 And (цех.IDЦехов)<>10 And (цех.IDЦехов)<>8 And (цех.IDЦехов)<>12 And (цех.IDЦехов)<>15 And (цех.IDЦехов)<>18) AND ((элементы.Обозначение) Like \"%ЛУИФ%\" And (элементы.Обозначение)=\"" + str + "\"))limit 1;", "IDЦехов");
            }
            else
            {
                id = 20;
            }
            if (tableViewSpecification.getItems().size() == 0)
            {
                addChildToParent("INSERT INTO snm.Дерево ( Родитель, Обозначение, Поз, Кол, IdЦехаЭ)\n"
                        + "VALUES(?,?," + 1 + ",0," + id + ")",
                        getId("SELECT Элементы.IDЭлементов\n"
                                + "FROM Элементы\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + labelParent.getText() + "\"));", "IDЭлементов"),
                        getId("SELECT Элементы.IDЭлементов\n"
                                + "FROM Элементы\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + str + "\"));", "IDЭлементов"));
                dataSpecification.add(0, addRowElement("SELECT Элементы.IDЭлементов, Дерево.Поз, Элементы.Обозначение, Элементы.Наименование, Элементы.Примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы_1.Обозначение, Элементы.а, Элементы.б, Элементы.в, Элементы.г, Элементы.д, Элементы.е, Элементы.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы.КоэффициентЭ, Материалы.КоэффициентМ, Элементы.КТДЭ, Элементы.ЕИЭ, Материалы.КТДМ, Элементы.Узел\n"
                        + "FROM Цех AS Цех_1 RIGHT JOIN (((((Дерево LEFT JOIN Элементы ON Дерево.Обозначение = Элементы.IDЭлементов) LEFT JOIN Материалы ON Элементы.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) LEFT JOIN Элементы AS Элементы_1 ON Дерево.Родитель = Элементы_1.IDЭлементов) LEFT JOIN Цех ON Дерево.IDЦехаЭ = Цех.IDЦехов) ON Цех_1.IDЦехов = Дерево.IDЦехаМ\n"
                        + "WHERE (((Элементы.Обозначение)=\"" + str + "\") AND ((Элементы_1.Обозначение)=\"" + labelParent.getText() + "\"));"));
            }
            else if (tableViewSpecification.getSelectionModel().getSelectedIndex() != -1)
            {
                addChildToParent("INSERT INTO snm.Дерево ( Родитель, Обозначение, Поз, Кол, IdЦехаЭ )\n"
                        + "VALUES(?,?," + (1 + Integer.parseInt(((Specification) tableViewSpecification.getSelectionModel().getSelectedItem()).getДерево_Поз())) + ",0," + id + ")",
                        getId("SELECT Элементы.IDЭлементов\n"
                                + "FROM Элементы\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + labelParent.getText() + "\"));", "IDЭлементов"),
                        getId("SELECT Элементы.IDЭлементов\n"
                                + "FROM Элементы\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + str + "\"));", "IDЭлементов"));
                dataSpecification.add(tableViewSpecification.getSelectionModel().getSelectedIndex() + 1, addRowElement("SELECT Элементы.IDЭлементов, Дерево.Поз, Элементы.Обозначение, Элементы.Наименование, Элементы.Примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы_1.Обозначение, Элементы.а, Элементы.б, Элементы.в, Элементы.г, Элементы.д, Элементы.е, Элементы.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы.КоэффициентЭ, Материалы.КоэффициентМ, Элементы.КТДЭ, Элементы.ЕИЭ, Материалы.КТДМ, Элементы.Узел\n"
                        + "FROM Цех AS Цех_1 RIGHT JOIN (((((Дерево LEFT JOIN Элементы ON Дерево.Обозначение = Элементы.IDЭлементов) LEFT JOIN Материалы ON Элементы.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) LEFT JOIN Элементы AS Элементы_1 ON Дерево.Родитель = Элементы_1.IDЭлементов) LEFT JOIN Цех ON Дерево.IDЦехаЭ = Цех.IDЦехов) ON Цех_1.IDЦехов = Дерево.IDЦехаМ\n"
                        + "WHERE (((Элементы.Обозначение)=\"" + str + "\") AND ((Элементы_1.Обозначение)=\"" + labelParent.getText() + "\"));"));
            }
            else
            {
                addChildToParent("INSERT INTO Дерево ( Родитель, Обозначение, Поз, Кол, IdЦехаЭ )\n"
                        + "VALUES(?,?," + (1 + Integer.parseInt(tableViewSpecification.getItems().get(tableViewSpecification.getItems().size() - 1).getДерево_Поз())) + ",0," + id + ")",
                        getId("SELECT Элементы.IDЭлементов\n"
                                + "FROM Элементы\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + labelParent.getText() + "\"));", "IDЭлементов"),
                        getId("SELECT Элементы.IDЭлементов\n"
                                + "FROM Элементы\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + str + "\"));", "IDЭлементов"));
                dataSpecification.add(tableViewSpecification.getItems().size(), addRowElement("SELECT Элементы.IDЭлементов, Дерево.Поз, Элементы.Обозначение, Элементы.Наименование, Элементы.Примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы_1.Обозначение, Элементы.а, Элементы.б, Элементы.в, Элементы.г, Элементы.д, Элементы.е, Элементы.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы.КоэффициентЭ, Материалы.КоэффициентМ, Элементы.КТДЭ, Элементы.ЕИЭ, Материалы.КТДМ, Элементы.Узел\n"
                        + "FROM Цех AS Цех_1 RIGHT JOIN (((((Дерево LEFT JOIN Элементы ON Дерево.Обозначение = Элементы.IDЭлементов) LEFT JOIN Материалы ON Элементы.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) LEFT JOIN Элементы AS Элементы_1 ON Дерево.Родитель = Элементы_1.IDЭлементов) LEFT JOIN Цех ON Дерево.IDЦехаЭ = Цех.IDЦехов) ON Цех_1.IDЦехов = Дерево.IDЦехаМ\n"
                        + "WHERE (((Элементы.Обозначение)=\"" + str + "\") AND ((Элементы_1.Обозначение)=\"" + labelParent.getText() + "\"));"));
            }
            getCommonMaterial("SELECT Дерево.Обозначение, ЭлементыВспомогательные.IDЭлементовВспомогательных, Вспомогательные.Вспомогательный, ЭлементыВспомогательные.НРМ, Вспомогательные.ЕИВ, Цех.НазваниеЦеха, Формулы.ФормулаРасчета, Формулы.а, ЭлементыВспомогательные.вспа, Формулы.б, ЭлементыВспомогательные.вспб, Формулы.в, ЭлементыВспомогательные.вспв, Формулы.г, ЭлементыВспомогательные.вспг, Формулы.д, ЭлементыВспомогательные.вспд, Формулы.е, ЭлементыВспомогательные.вспе, Вспомогательные.КоэффициентВМ, ЭлементыВспомогательные.КоэфВЭ, Вспомогательные.IDВспомогательных, Вспомогательные.КТДВ\n"
                    + "FROM ((Формулы RIGHT JOIN (ЭлементыВспомогательные LEFT JOIN Вспомогательные ON ЭлементыВспомогательные.IDВспомогательного = Вспомогательные.IDВспомогательных) ON Формулы.IDФормул = Вспомогательные.Формула) RIGHT JOIN (Элементы LEFT JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) ON ЭлементыВспомогательные.IDЭлемента = Дерево.Обозначение) LEFT JOIN Цех ON ЭлементыВспомогательные.IDЦехаВ = Цех.IDЦехов\n"
                    + "WHERE (((Элементы.Обозначение)=\"" + labelParent.getText().toString().replaceAll("\"", "\"\"") + "\"));", true);
            tableViewSpecification.getSelectionModel().clearSelection();
        }
    }

    private void setElementRow2()
    {
        String str = comboboxTree.getEditor().getText().replaceAll("\"", "\"\"");

        if (!treeView.getSelectionModel().isEmpty() && !str.equals(treeView.getSelectionModel().getSelectedItem().getValue()) && !comboboxTree.getEditor().getText().isEmpty())
        {
            updateString("INSERT INTO Элементы ( Обозначение )\n"
                    + "VALUES(?);", comboboxElement.getEditor().getText());

            if (!treeView.getSelectionModel().getSelectedItem().isLeaf())
            {
                String str2 = treeView.getSelectionModel().getSelectedItem().getChildren().get(treeView.getSelectionModel().getSelectedItem().getChildren().size() - 1).getValue().replaceAll("\"", "\"\"");;

                addChildToParent("INSERT INTO snm.Дерево ( Родитель, Обозначение, Поз, Кол )\n"
                        + "VALUES(?,?," + (getId("SELECT дерево.Поз\n"
                                + "FROM элементы LEFT JOIN дерево ON элементы.IDЭлементов = дерево.Обозначение\n"
                                + "WHERE (((дерево.Родитель)=" + dataElement.get(0).getЭлементы_1_IDЭлементов() + ") AND (concat_ws(\" \",элементы.Обозначение,элементы.Наименование)=\"" + str2.substring(0, str2.lastIndexOf(" ")) + "\"));\n"
                                + "", "дерево.Поз") + 1) + ",0)",
                        dataElement.get(0).getЭлементы_1_IDЭлементов(), id);
            }
            else
            {
                addChildToParent("INSERT INTO snm.Дерево ( Родитель, Обозначение, Поз, Кол )\n"
                        + "VALUES(?,?," + 1 + ",0)",
                        dataElement.get(0).getЭлементы_1_IDЭлементов(),
                        id);
            }
            treeView.getSelectionModel().getSelectedItem().getChildren().clear();
            newNode("SELECT concat_ws(\" \",Элементы_1.Обозначение , Элементы_1.Наименование, \" \") AS Обозначение, Дерево.Кол, Элементы_1.ЕИЭ\n"
                    + "FROM (Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов\n"
                    + "WHERE (((concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=\"" + treeView.getSelectionModel().getSelectedItem().getValue().substring(0, treeView.getSelectionModel().getSelectedItem().getValue().lastIndexOf(" ")) + " \")& (Элементы_1.Узел)!=0)\n"
                    + "ORDER BY Дерево.Поз;", treeItem, true);
            selectTreeViewItem();

        }
    }
//<Schedule

    private void setSchedule(String sql, int number1, int number2)
    {

        try
        {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number1);
            preparedStatement.setInt(2, number2);
            preparedStatement.executeUpdate();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void getIDУзла(String sql)
    {
        TreeItem<String> treeItem = new TreeItem<String>(stringComboboxSchedule);
        schedule.clear();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                if (schedule.isEmpty())
                {
                    schedule.add(resultSet.getInt("idЗаказа"));
                }
                schedule.add(resultSet.getInt("IDЭлементов"));
                treeItem.getChildren().add(new TreeItem<>(resultSet.getString("IDЭлементов")));
            }
            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
        fillTreeIDУзла(treeItem);
    }

    private void fillTreeIDУзла(TreeItem<String> treeItem)
    {
        for (int i = 0; i < treeItem.getChildren().size(); i++)
        {

            newNodeIDУзла("SELECT Элементы_1.IDЭлементов, заказы.Заказ, график.Выписано\n"
                    + "FROM (((Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов) LEFT JOIN График ON Элементы_1.IDЭлементов = График.Потомок) LEFT JOIN заказы ON График.Родитель = заказы.idЗаказа\n"
                    + "WHERE (((Элементы.IDЭлементов)= " + treeItem.getChildren().get(i).getValue() + ") AND ((Элементы_1.Узел)=True))\n"
                    + "ORDER BY Дерево.Поз;", treeItem.getChildren().get(i));
            fillTreeIDУзла(treeItem.getChildren().get(i));
        }
    }

    private void newNodeIDУзла(String sql, TreeItem<String> treeItem)
    {
        if (!treeItem.getChildren().isEmpty())
        {
            return;
        }
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                schedule.add(resultSet.getInt("Элементы_1.IDЭлементов"));

                if (!stringComboboxSchedule.equals(resultSet.getString("Заказ")))
                {
                    setSchedule("INSERT INTO График ( Родитель, Потомок,Выписано )\n"
                            + "VALUES(?,?,\"-\");", schedule.get(0), schedule.get(schedule.size() - 1));
                }
                treeItem.getChildren().add(new TreeItem<String>(resultSet.getString("IDЭлементов")));
            }
            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }

        }

    }

    private void updateSchedule(String sql, String str1, String str2)
    {
        try
        {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, str1);
            preparedStatement.setString(2, str2);
            preparedStatement.executeUpdate();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private void updateSchedule(String sql, String str1, Integer int1)
    {

        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, str1);
            preparedStatement.setInt(2, int1);
            preparedStatement.executeUpdate();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

//Schedule>
//<Orders
    private void insertOrder(String sql, int id)
    {
        try
        {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, textFieldOrder.getText());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    public int insertGetId(String sql)
    {
        int id = 0;
        try
        {

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, textFieldOrder.getText());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next())
            {
                id = resultSet.getInt(1);
            }
            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {
            id = getId("SELECT Элементы.IDЭлементов\n"
                    + "FROM Элементы\n"
                    + "WHERE (((Элементы.Обозначение)=\"" + textFieldOrder.getText() + "\"));", "IDЭлементов");
        }
        finally
        {
            try
            {
                preparedStatement.close();
            }
            catch (SQLException ex)
            {

            }

        }
        return id;
    }

    private void getOrders(String sql)
    {
        dataOrders.clear();
        try
        {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                dataOrders.add(new Orders(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));

            }
            resultSet.close();
        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }

        }
    }
// Orders>

    private void fillTreeScheduleExcel(TreeItem<Schedule> treeItem)
    {
        for (int i = 0; i < treeItem.getChildren().size(); i++)
        {
            dataSchedule.add(treeItem.getChildren().get(i).getValue());
            fillTreeScheduleExcel(treeItem.getChildren().get(i));
        }
    }

    private void selectTreeViewItem()
    {
        if (!DBConnection.connect())
        {
            bdNotConnected();
        }
        else
        {
            count1 = 0;
            try
            {
                TreeItem treeItem = treeView.getTreeItem(treeView.getSelectionModel().getSelectedIndex());
                String children = treeView.getSelectionModel().getSelectedItem().getValue().substring(0, treeView.getSelectionModel().getSelectedItem().getValue().lastIndexOf(" ")).replaceAll("\"", "\"\"");
                String parent;
                try
                {
                    parent = treeView.getSelectionModel().getSelectedItem().getParent().getValue().substring(0, treeView.getSelectionModel().getSelectedItem().getParent().getValue().lastIndexOf(" ")).replaceAll("\"", "\"\"");
                }
                catch (NullPointerException ex)
                {
                    parent = "";
                }
                if (checkBoxNodes.isSelected())
                {

                    newNode("SELECT concat_ws(\" \",Элементы_1.Обозначение , Элементы_1.Наименование, \" \") AS Обозначение, Дерево.Кол, Элементы_1.ЕИЭ\n"
                            + "FROM (Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов\n"
                            + "WHERE (((concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=\"" + children + " \")& (Элементы_1.Узел)!=0)\n"
                            + "ORDER BY Дерево.Поз;", treeItem, true);

                }
                else
                {
                    newNode("SELECT concat_ws(\" \",Элементы_1.Обозначение , Элементы_1.Наименование, \" \") AS Обозначение, Дерево.Кол, Элементы_1.ЕИЭ\n"
                            + "FROM (Элементы INNER JOIN Дерево ON Элементы.IDЭлементов = Дерево.Родитель) INNER JOIN Элементы AS Элементы_1 ON Дерево.Обозначение = Элементы_1.IDЭлементов\n"
                            + "WHERE (((concat_ws(\" \", Элементы.Обозначение , Элементы.Наименование))=\"" + children + " \"))\n"
                            + "ORDER BY Дерево.Поз;", treeItem, true);
                }
                try
                {
                    if (!parent.equals(""))
                    {
                        getElemnt("SELECT Элементы_1.IDЭлементов, Дерево.Поз, Элементы_1.Обозначение, Элементы_1.Наименование, Элементы_1.примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы.Обозначение, Элементы_1.а, Элементы_1.б, Элементы_1.в, Элементы_1.г, Элементы_1.д, Элементы_1.е, Элементы_1.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы_1.КоэффициентЭ, Материалы.КоэффициентМ, Элементы_1.КТДЭ, Элементы_1.ЕИЭ, Материалы.КТДМ, Элементы_1.Узел\n"
                                + "FROM элементы AS Элементы_2 INNER JOIN (Элементы INNER JOIN ((Цех RIGHT JOIN (Дерево LEFT JOIN ((Элементы AS Элементы_1 LEFT JOIN Материалы ON Элементы_1.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) ON Дерево.Обозначение = Элементы_1.IDЭлементов) ON Цех.IDЦехов = Дерево.IDЦехаЭ) LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов) ON Элементы.IDЭлементов = Дерево.Обозначение) ON Элементы_2.IDЭлементов = Дерево.Родитель\n"
                                + "WHERE ((concat_ws(\" \",Элементы.Обозначение,Элементы.Наименование)=\"" + children + "\") AND \n"
                                + "(concat_ws(\" \",Элементы_2.Обозначение,Элементы_2.Наименование)=\"" + parent + "\"));\n"
                                + "");
                    }
                    else
                    {
                        getElemnt("SELECT Элементы_1.IDЭлементов, Дерево.Поз, Элементы_1.Обозначение, Элементы_1.Наименование, Элементы_1.примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы.Обозначение, Элементы_1.а, Элементы_1.б, Элементы_1.в, Элементы_1.г, Элементы_1.д, Элементы_1.е, Элементы_1.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы_1.КоэффициентЭ, Материалы.КоэффициентМ, Элементы_1.КТДЭ, Элементы_1.ЕИЭ, Материалы.КТДМ, Элементы_1.Узел\n"
                                + "FROM элементы AS Элементы_2 INNER JOIN (Элементы INNER JOIN ((Цех RIGHT JOIN (Дерево LEFT JOIN ((Элементы AS Элементы_1 LEFT JOIN Материалы ON Элементы_1.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) ON Дерево.Обозначение = Элементы_1.IDЭлементов) ON Цех.IDЦехов = Дерево.IDЦехаЭ) LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов) ON Элементы.IDЭлементов = Дерево.Обозначение) ON Элементы_2.IDЭлементов = Дерево.Родитель\n"
                                + "WHERE (((Элементы.Обозначение)=\"" + children + "\"));");
                    }
                    treeItem.setExpanded(true);
                    getParameters(dataElement.get(0));

                }
                catch (IndexOutOfBoundsException ex)
                {
                    getElemnt("SELECT Элементы_1.IDЭлементов, Дерево.Поз, Элементы_1.Обозначение, Элементы_1.Наименование, Элементы_1.примечание, Дерево.Кол, Материалы.IDМатериалов, Материалы.Материал, Формулы.IDФормул, Элементы.Обозначение, Элементы_1.а, Элементы_1.б, Элементы_1.в, Элементы_1.г, Элементы_1.д, Элементы_1.е, Элементы_1.НРМ, Материалы.ЕИМ, Формулы.ФормулаРасчета, Формулы.а, Формулы.б, Формулы.в, Формулы.г, Формулы.д, Формулы.е, Цех.НазваниеЦеха, Дерево.IDДерева, Цех_1.НазваниеЦеха, Элементы_1.КоэффициентЭ, Материалы.КоэффициентМ, Элементы_1.КТДЭ, Элементы_1.ЕИЭ, Материалы.КТДМ, Элементы_1.Узел\n"
                            + "FROM элементы AS Элементы_2 INNER JOIN (Элементы INNER JOIN ((Цех RIGHT JOIN (Дерево LEFT JOIN ((Элементы AS Элементы_1 LEFT JOIN Материалы ON Элементы_1.IDМатериала = Материалы.IDМатериалов) LEFT JOIN Формулы ON Материалы.IDФормулы = Формулы.IDФормул) ON Дерево.Обозначение = Элементы_1.IDЭлементов) ON Цех.IDЦехов = Дерево.IDЦехаЭ) LEFT JOIN Цех AS Цех_1 ON Дерево.IDЦехаМ = Цех_1.IDЦехов) ON Элементы.IDЭлементов = Дерево.Обозначение) ON Элементы_2.IDЭлементов = Дерево.Родитель\n"
                            + "WHERE ((concat_ws(\" \",Элементы.Обозначение,Элементы.Наименование)=\"" + children + "\") AND \n"
                            + "((Элементы_2.Обозначение)=\"" + parent + "\"));\n"
                            + "");
                    treeItem.setExpanded(true);
                    getParameters(dataElement.get(0));
                }

                dataMaterialsValues.clear();

                dataMaterialsValues.add(new MaterialsValues(
                        dataElement.get(0).getФормулы_а(), dataElement.get(0).getЭлементы_1_а()
                ));
                dataMaterialsValues.add(new MaterialsValues(
                        dataElement.get(0).getФормулы_б(), dataElement.get(0).getЭлементы_1_б()
                ));
                dataMaterialsValues.add(new MaterialsValues(
                        dataElement.get(0).getФормулы_в(), dataElement.get(0).getЭлементы_1_в()
                ));
                dataMaterialsValues.add(new MaterialsValues(
                        dataElement.get(0).getФормулы_г(), dataElement.get(0).getЭлементы_1_г()
                ));
                dataMaterialsValues.add(new MaterialsValues(
                        dataElement.get(0).getФормулы_д(), dataElement.get(0).getЭлементы_1_д()
                ));
                dataMaterialsValues.add(new MaterialsValues(
                        dataElement.get(0).getФормулы_е(), dataElement.get(0).getЭлементы_1_е()
                ));

                getCommonParameters(tableViewCommonMaterial.getSelectionModel().getSelectedItem());

            }
            catch (StringIndexOutOfBoundsException ex)
            {

            }
            catch (NullPointerException ex)
            {

            }
        }
    }

    private void bdNotConnected()
    {
        if (alert == null)
        {
            ButtonType buttonTypeYes = new ButtonType("Да");
            ButtonType buttonTypeExit = new ButtonType("Выйти");
            ButtonType buttonTypeClose = new ButtonType("Закрыть", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert = new Alert(Alert.AlertType.CONFIRMATION, "Нет соединения с сервером! Востановить соединение?");
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeExit, buttonTypeClose);
            alert.initStyle(StageStyle.UTILITY);
            alert.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("resources/disconnect.png").toString()));
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes)
            {
                DBConnection.connect();
                connection = DBConnection.getConnection();
            }/*
            else if (result.get() == buttonTypeClose)
            {
            }*/
            else if (result.get() == buttonTypeExit)
            {
                System.exit(0);
            }
            alert = null;
        }
    }

    @FXML
    private void treeTableViewOnMouseReleased(MouseEvent event)
    {/*d
        treeTableView.getSelectionModel().getSelectedItem().getValue().setОтветственный(userSecondName);
        if (treeTableView.getSelectionModel().getSelectedItem().getValue().getВыписано().equals("-"))
        {
            Format format = new SimpleDateFormat("dd.MM.yy");
            treeTableView.getSelectionModel().getSelectedItem().getValue().setВыписано(format.format(new Date()));
        }
         */
    }

    @FXML
    private void tabScheduleOnSelectionChanged(Event event)
    {/*
        try
        {
            String str = InetAddress.getLocalHost().getHostAddress();
            userSecondName = getString("SELECT users.SecondName\n"
                    + "FROM users\n"
                    + "WHERE (((users.UserLocalIP)=\"" + str + "\"))");
            if (userSecondName == null)
            {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                TextField textFieldUserSecondName = new TextField();
                alert.setTitle("Пользователь");
                alert.setHeaderText(null);
                //alert.setContentText("Нет соединения с сервером!");
                alert.initStyle(StageStyle.UTILITY);
                alert.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("resources/icon.png").toString()));
                alert.getDialogPane().setContent(new VBox(new Label("Введите свою фамилию"), textFieldUserSecondName));
                Optional<ButtonType> result = alert.showAndWait();
                userSecondName = textFieldUserSecondName.getText();
                insertUserSecondName("INSERT INTO users ( UserLocalIP,SecondName)\n"
                        + "VALUES(?,?);", str);
            }
        }
        catch (UnknownHostException ex)
        {
        }
         */
    }

    private void insertUserSecondName(String sql, String str)
    {
        try
        {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, str);
            preparedStatement.setString(2, userSecondName);
            preparedStatement.executeUpdate();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException ex)
                {

                }
            }
        }
    }

    private String getString(String sql)
    {
        String str = null;
        try
        {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            str = resultSet.getString("SecondName");

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (SQLException ex)
            {

            }
        }
        return str;

    }

    private void getWriten(String sql) throws NullPointerException
    {
        try
        {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            int i = 0;
            while (resultSet.next())
            {
                specification = tableViewSpecification.getItems().get(i);
                specification.setВыписано(resultSet.getString("Выписано"));
                i++;
            }

            resultSet.close();

        }
        catch (CommunicationsException ex)
        {
            bdNotConnected();
        }
        catch (SQLException ex)
        {

        }
        catch (IndexOutOfBoundsException ex)
        {

        }
        finally
        {
            if (connection == null)
            {
                bdNotConnected();
            }
            else
            {
                try
                {
                    statement.close();
                }
                catch (SQLException ex)
                {

                }
            }

        }

    }

}
