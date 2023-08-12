package com.example.deber_javafx;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ResourceBundle;





public class HelloController implements Initializable {

    @FXML
    private TextField idField;

    @FXML
    private TextField NombreField;

    @FXML
    private TextField CedulaField;


    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<users> TableView;

    @FXML
    private TableColumn<users, Integer> idColumn;

    @FXML
    private TableColumn<users, String> NombreColumn;

    @FXML
    private TableColumn<users, String> CedulaColumn;


    @FXML
    private void insertButton() {
        String query = "insert into datos values("+idField.getText()+",'"+NombreField.getText()+"','"+CedulaField.getText()+"'";
        executeQuery(query);
        showBooks();
    }


    @FXML
    private void updateButton() {
        String query = "UPDATE datos SET Nombre='"+NombreField.getText()+"',Cedula='"+CedulaField.getText()+" WHERE Id="+idField.getText()+"";
        executeQuery(query);
        showBooks();
    }

    @FXML
    private void deleteButton() {
        String query = "DELETE FROM datos WHERE ID="+idField.getText()+"";
        executeQuery(query);
        showBooks();
    }

    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showBooks();
    }

    public Connection getConnection() {
        Connection conn;
        try {
            //colocar tus credenciales del mysql IMPORTANTE
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios","root","root_bas3");
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<users> getUsersList(){
        ObservableList<users> booksList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM datos ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            users users2;
            while(rs.next()) {
                users2 = new users(rs.getInt("Id"),rs.getString("Nombre"),rs.getString("Cedula"));
                booksList.add(users2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booksList;
    }

    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showBooks() {
        ObservableList<users> list = getUsersList();

        idColumn.setCellValueFactory(new PropertyValueFactory<users,Integer>("id"));
        NombreColumn.setCellValueFactory(new PropertyValueFactory<users,String>("title"));
        CedulaColumn.setCellValueFactory(new PropertyValueFactory<users,String>("author"));
        TableView.setItems(list);
    }

}