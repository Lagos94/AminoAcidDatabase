/**
 * @author: Juan Lagos
 * @version: 1.0
 * @since 12/05/18
 * <p>
 * <p>
 * The purpose of this program is to have access to individual amino acids and plot them on a table.
 * The table properties are flexible enough to sort out data in alphabetical order in either
 * increasing order or decreasing order, after clicking any of the 3 available amino properties
 * (integer values still in testing). Furthermore, user is able to add any unlisted amino acids
 * and edit any altered discovery in the hydrophobicity values of each amino acid.
 * <p>
 * In a protein, hydrophobic amino acids are likely to be found internally, whereas hydrophilic amino acids
 * are likely to be found in contact with the aqueous solution.
 */

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.TOP_CENTER;


public class AminoAcidDatabase extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        aminodatabase(primaryStage,null);
    }

    public class Amino {

        public SimpleStringProperty aminoName;
        public SimpleStringProperty molecule;
        public SimpleStringProperty hydroP;

        public Amino(String aName, String mole, String hydroP) {
            this.aminoName = new SimpleStringProperty(aName);
            this.molecule = new SimpleStringProperty(mole);
            this.hydroP = new SimpleStringProperty(hydroP);
        }

        public String getAminoName() {
            return aminoName.get();
        }

        public void setAminoName(String fName) {
            aminoName.set(fName);
        }

        public String getMolecule() {
            return molecule.get();
        }

        public void setMolecule(String fName) {
            molecule.set(fName);
        }

        public String getHydroP() {
            return hydroP.get();
        }

        public void setHydroP(String fName) {
            hydroP.set(fName);
        }
    }

    /** Create an ArrayList in which to store the 20 essential amino acids.
     *
     * @param dataBase: Stores all Amino acid data into a table.
     * @param data: node containing 3 data field properties: name of the amino acid, molecule formula,
     * and hydrophobicity index.
     * @param presentData: takes care of formatting throughout the table.
     */

    private TableView<Amino> dataBase = new TableView<Amino>();
    private ObservableList<Amino> data =
            FXCollections.observableArrayList(
                    new Amino("Alanine", "C3H7NO2", "41"),
                    new Amino("Arginine", "C6H14N4O2", "-14"),
                    new Amino("Asparagine", "C4H8N2O3", "-18"),
                    new Amino("Aspartic Acid", "C4H7NO4", "7"),
                    new Amino("Cysteine", "C3H7NO2S", "63"),
                    new Amino("Glutamic Acid", "C5H9NO4", "8"),
                    new Amino("Glutamine", "C5H10N2O3", "-10"),
                    new Amino("Glycine", "C2H5NO2", "0"),
                    new Amino("Histidine", "C6H9N3O2", "-31"),
                    new Amino("Leucine", "C6H13NO2", "100"),
                    new Amino("Lysine", "C6H14N2O2", "-23"),
                    new Amino("Methionine", "C5H11NO2S", "74"),
                    new Amino("Phenylalanine", "C9H11NO2", "97"),
                    new Amino("Proline", "C5H9NO2", "-46"),
                    new Amino("Hydroxyproline", "C5H9NO3", "N/A"),
                    new Amino("Serine", "C3H7NO3", "-5"),
                    new Amino("Threonine", "C4H9NO3", "13"),
                    new Amino("Tryptophan", "C11H12N2O2", "84"),
                    new Amino("Tyrosine", "C9H11NO3", "49"),
                    new Amino("Valine", "C5H11NO2", "79")
            );
    private HBox presentData = new HBox();
    Stage window;
    Scene scene = new Scene(new Group()); //splits Juan Lagos's portion from Alex Coleman.

    public static void main(String[] args) {
        launch(args);
    }

    void aminodatabase(Stage stage, Scene yourScene) { //sets up user interface as specified by setter methods and table properties.
        window = stage;
        dataBase.refresh();
        dataBase.setEditable(true);
        stage.setTitle("AminoAcidCombiner");
        stage.setWidth(550);
        stage.setHeight(550);
        stage.setResizable(true);

        Label title = new Label("Amino Acids");
        title.setAlignment(CENTER);
        title.setFont(new Font("Times New Roman", 25));

        /**
         * 'Amino name' column with the ability to bubble sort data in increasing or decreasing value.
         */

        TableColumn aminoNameCol = new TableColumn("Amino Name");
        aminoNameCol.setMinWidth(150);
        aminoNameCol.setCellValueFactory(
                new PropertyValueFactory<Amino, String>("aminoName"));
        aminoNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        aminoNameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Amino, String>>() {
                    @Override

                    /**
                     * @param handle each column contains this method to rearrange data based
                     * on user input and bubbleSort increasing/decreasing order. Also enables the user to edit data.
                     */

                    public void handle(CellEditEvent<Amino, String> aN) {
                        ((Amino) aN.getTableView().getItems().get(aN.getTablePosition().getRow())).setAminoName(aN.getNewValue());
                    }
                }
        );

        /**
         * 'Molecule' column with the ability to bubble sort data in increasing or decreasing value.
         */

        TableColumn moleCol = new TableColumn("Molecule");
        moleCol.setMinWidth(150);
        moleCol.setCellValueFactory(
                new PropertyValueFactory<Amino, String>("molecule"));
        moleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        moleCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Amino, String>>() {
                    @Override
                    public void handle(CellEditEvent<Amino, String> mC) {
                        ((Amino) mC.getTableView().getItems().get(mC.getTablePosition().getRow())).setMolecule(mC.getNewValue());
                    }
                }
        );

        /**
         * 'Hydrophobicity' column with the ability to bubble sort data in increasing or decreasing value.
         */

        TableColumn hydroPCol = new TableColumn("Hydrophobicity");
        hydroPCol.setMinWidth(150);
        hydroPCol.setCellValueFactory(
                new PropertyValueFactory<Amino, String>("hydroP"));
        hydroPCol.setCellFactory(TextFieldTableCell.forTableColumn());
        hydroPCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Amino, String>>() {
                    @Override
                    public void handle(CellEditEvent<Amino, String> hP) {
                        ((Amino) hP.getTableView().getItems().get(hP.getTablePosition().getRow())).setHydroP(hP.getNewValue());
                    }
                }
        );

        //combine all 3 columns to form 1 table called dataBase.
        dataBase.setItems(data);
        dataBase.getColumns().addAll(aminoNameCol, moleCol, hydroPCol);

        // 3 textfields in which the user can input new amino acids or alter hydrophobic values.
        TextField addAminoName = new TextField();
        addAminoName.setMaxSize(400, 1);
        addAminoName.setPromptText("Amino name");
        addAminoName.setMaxWidth(aminoNameCol.getPrefWidth());
        TextField addMolecule = new TextField();
        addMolecule.setMaxWidth(moleCol.getPrefWidth());
        addMolecule.setPromptText("Molecule");
        TextField addHydroP = new TextField();
        addHydroP.setMaxWidth(hydroPCol.getPrefWidth());
        addHydroP.setPromptText("Hydrophobicity");

        /** two buttons are made accessible to the user: Add & Delete. Aside from column title button and
         * the ability to edit by double clicking textfied, these two buttons take care of manipulating data
         * more directly and organized.
         *
         * @param addButton takes care of adding new data field whenever user has filled all 3 textfields.
         * @param deleteButton takes care of erasing any data, including pre-existing data or newly added data.
         */

        Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new Amino(
                        addAminoName.getText(),
                        addMolecule.getText(),
                        addHydroP.getText()));
                addAminoName.clear();
                addMolecule.clear();
                addHydroP.clear();
            }
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int selectedIdx = dataBase.getSelectionModel().getSelectedIndex();
                if (selectedIdx != -1) {
                    Amino itemToRemove = dataBase.getSelectionModel().getSelectedItem();

                    int newSelectedIdx =
                            (selectedIdx == dataBase.getItems().size() - 1)
                                    ? selectedIdx - 1
                                    : selectedIdx;

                    dataBase.getItems().remove(selectedIdx);
                    dataBase.getSelectionModel().select(newSelectedIdx);

                    //Display removal procedure to console
                    System.out.println("Selected Index: " + selectedIdx);
                    System.out.println("Item: " + itemToRemove);
                }
            }
        });

        //@param presentData focuses on placement of buttons and textfield.
        presentData.getChildren().addAll(addAminoName, addMolecule, addHydroP, addButton, deleteButton);
        presentData.setSpacing(10);
        presentData.setAlignment(CENTER);

        //@para vbox consolidates all the separate contents together into 1.
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        if (stage.isMaximized())
            vbox.setPadding(new Insets(150, 650, 650, 650));
        else
            vbox.setPadding(new Insets(40,0,0,40));
        vbox.getChildren().addAll(title, dataBase, presentData);
        vbox.setAlignment(CENTER);

        ((Group) this.scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(this.scene);
        stage.show();
    }
}