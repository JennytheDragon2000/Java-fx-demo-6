package Controller;

import Model.customerTM;
import com.sun.media.jfxmediaimpl.HostUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;
import java.util.concurrent.LinkedTransferQueue;

public class TableFormController {

    //    public TableView tblCustomers;
    public TableView<customerTM> tblCustomers;
    public TextField txtID;
    public TextField txtName;
    public TextField txtAddress;
    public Button btnSaveCustomer;
    public Button btnNewCutomer;
    public Button btnDeleteCustomer;

    public void initialize() {
        btnDeleteCustomer.setDisable(true);
//        ObservableList olRows = tblCustomers.getItems();
//        olRows.add(20);
//        olRows.add(30);
//        olRows.add(40);
//        olRows.add(50);
//        olRows.add("Tharindu");
//        olRows.add("IJSE");

//        tblCustomers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<customerTM>() {
//            @Override
//            public void changed(ObservableValue<? extends customerTM> observableValue,
//                                customerTM previouslySelectedCustomer,
//                                customerTM NewlySelectedCustomer) {
//                System.out.println("Previous :" + previouslySelectedCustomer);
//                System.out.println("Current :" + NewlySelectedCustomer);
//            }
//        });

        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        ObservableList<customerTM> olCustomers = tblCustomers.getItems();

        olCustomers.add(new customerTM("C001", "Kasun", "Pandura"));
        olCustomers.add(new customerTM("C002", "Nalin", "Anuradhapura"));
        olCustomers.add(new customerTM("C003", "Malan", "Kurunagala"));

        btnSaveCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                ObservableList<customerTM> olCustomers = tblCustomers.getItems();

                //validation logic

                if (txtID.getText().isBlank()) {
                    new Alert(Alert.AlertType.ERROR, "Customer ID Can not be empty").showAndWait();
                    txtID.requestFocus();
                    return;
                }
                if (txtName.getText().isBlank()) {
                    new Alert(Alert.AlertType.ERROR, "Customer Name Can not be empty").showAndWait();
                    txtName.requestFocus();
                    return;
                }
                if (txtAddress.getText().isBlank()) {
                    new Alert(Alert.AlertType.ERROR, "Customer Address Can not be empty").showAndWait();
                    txtAddress.requestFocus();
                    return;
                }

                //itr + tab

                for (customerTM olcustomer :
                        olCustomers) {
                    System.out.println(olcustomer);
                    if (olcustomer.getId().equals(txtID.getText())) {
                        new Alert(Alert.AlertType.ERROR, "Duplicate Customer IDs are not Allowed").showAndWait();
                        txtID.requestFocus();
                        return;
                    }
                }

                String id = txtID.getText();
                String name = txtName.getText();
                String address = txtAddress.getText();


//                customerTM newCustomer = new customerTM(id, name, address);
                olCustomers.add(new customerTM(id, name, address));

                txtID.clear();
                txtName.clear();
                txtAddress.clear();

                txtID.requestFocus();
            }
        });

        btnNewCutomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                txtID.clear();
//                txtName.clear();
//                txtAddress.clear();

                txtID.requestFocus();
                tblCustomers.getSelectionModel().clearSelection();
            }
        });

        btnDeleteCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                customerTM selectedCustomer = tblCustomers.getSelectionModel().getSelectedItem();
//                System.out.println(selectedCustomer);
//                int selectedIndex = tblCustomers.getSelectionModel().getSelectedIndex();
//                System.out.println(selectedIndex);
//
////                tblCustomers.getSelectionModel().select(2);
//                tblCustomers.getSelectionModel().clearSelection();

                ObservableList<customerTM> olCustomers = tblCustomers.getItems();
                customerTM selectedItem = tblCustomers.getSelectionModel().getSelectedItem();
                if(selectedItem == null ) return;

                Optional<ButtonType> SelectedOption = new Alert(Alert.AlertType.CONFIRMATION,
                        "Are you sure to delete this customer?",ButtonType.NO, ButtonType.YES).showAndWait();

                if(SelectedOption.get() == ButtonType.YES){
                    olCustomers.remove(selectedItem);
                }

                tblCustomers.getSelectionModel().clearSelection();





            }
        });

        tblCustomers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<customerTM>() {
            @Override
            public void changed(ObservableValue<? extends customerTM> observableValue,
                                customerTM previous,
                                customerTM current) {

                if(current == null){
                    btnDeleteCustomer.setDisable(true);
                    txtID.clear();
                    txtName.clear();
                    txtAddress.clear();
                    btnSaveCustomer.setText("Save Customer");
                    txtID.setEditable(true);
                    return;
                }

                txtID.setText(current.getId());
//                txtID.setDisable(true);
                txtID.setEditable(false);
                txtName.setText(current.getName());
                txtAddress.setText(current.getAddress());
                btnDeleteCustomer.setDisable(false);
                btnSaveCustomer.setText("Update Customer");

            }
        });


    }
}
