package gui;

import Controller.Controller;
import Model.Stmt.IStmt;
import Model.FileTable.IFileTable;
import Model.Heap.IHeap;
import Model.Output.IOutput;
import Model.PrgState;
import Model.Value.Value;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Pair;

import java.util.List;
import java.util.NoSuchElementException;

public class MainWindowController {
    Controller controller;
    IHeap heap;
    IOutput output;
    IFileTable fileTable;

    public MainWindowController(Controller controller) {
        this.controller = controller;
    }

    @FXML
    private Label programStatesLabel;

    @FXML
    private ListView<Integer> programStatesListView;

    @FXML
    private ListView<IStmt> executionStackListView;

    @FXML
    private ListView<String> fileTableListView;

    @FXML
    private ListView<String> outListView;

    @FXML
    private TableView<Pair<Integer, Value>> heapTableTableView;

    @FXML
    private TableColumn<Pair<Integer, Value>, Integer> heapAddressesColumn;

    @FXML
    private TableColumn<Pair<Integer, Value>, String> heapValuesColumn;

    @FXML
    private TableView<Pair<String, Value>> symbolTableTableView;

    @FXML
    private TableColumn<Pair<String, Value>, String> symbolNameColumn;

    @FXML
    private TableColumn<Pair<String, Value>, String> symbolValueColumn;

    @FXML
    private Button runButton;

    @FXML
    private Button oneStepButton;

    public void refresh() {
        Integer selectedProgramId = this.programStatesListView.getSelectionModel().getSelectedItem();
        this.programStatesListView.getItems().clear();
        this.heapTableTableView.getItems().clear();
        this.outListView.getItems().clear();
        this.fileTableListView.getItems().clear();
        this.symbolTableTableView.getItems().clear();
        this.executionStackListView.getItems().clear();

        this.programStatesLabel.setText("Program states: " + this.controller.getPrgList().size());
        this.controller.getPrgList().forEach(progState -> this.programStatesListView.getItems().add(progState.getId()));
        if (this.controller.getPrgList().size() > 0) {
            this.heap = this.controller.getPrgList().get(0).getHeap();
            this.output = this.controller.getPrgList().get(0).getOut();
            this.fileTable = this.controller.getPrgList().get(0).getFileTable();

        }

        if (this.heap != null) {
            this.heap.getContent().forEach((key, value) ->
                    this.heapTableTableView.getItems().add(new Pair<>(key, value)));
        }

        if (this.output != null) {
            this.output.getOutputAsList().forEach(output
                    -> this.outListView.getItems().add(output.toString()));
        }

        if (this.fileTable != null) {
            this.fileTable.getFileList().forEach(key
                    -> this.fileTableListView.getItems().add(key.toString()));
        }

        PrgState current;
        try{
            current = this.controller.getPrgList().stream().filter(x -> Integer.valueOf(x.getId()).equals(selectedProgramId)).findAny().get();
            current.getSymTable().getContent().forEach((x, y) -> this.symbolTableTableView.getItems().add(new Pair<>(x,y)));
            List<IStmt> statementList = current.getStack().toList();
            for(int i = statementList.size() - 1;i >= 0;i--){
                this.executionStackListView.getItems().add(statementList.get(i));
            }
            this.programStatesListView.getSelectionModel().select(selectedProgramId);
        } catch (NoSuchElementException e) {
            return ;
        } finally {
            this.programStatesListView.refresh();
            this.heapTableTableView.refresh();
            this.outListView.refresh();
            this.fileTableListView.refresh();
            this.symbolTableTableView.refresh();
            this.executionStackListView.refresh();
        }
    }


    @FXML
    public void initialize() {
        this.heapAddressesColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        this.heapValuesColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue().toString()));
        this.symbolNameColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getKey()));
        this.symbolValueColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue().toString()));
        this.refresh();

        this.oneStepButton.setOnAction(actionEvent -> {
            try {
                this.controller.oneStepForAllPrg(this.controller.getPrgList());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                System.out.println(e.getMessage());
                alert.show();
            }
            this.refresh();
        });

        this.runButton.setOnAction(actionEvent -> {
            try {
                this.controller.allStep();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
            this.refresh();
        });
        this.programStatesListView.setOnMouseClicked(x -> this.refresh());
    }
}