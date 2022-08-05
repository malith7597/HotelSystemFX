package com.example.hotelsystemfx;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    @FXML
    public ListView<String> listView1;
    @FXML
    public Label labelOccupy;
    @FXML
    public ListView<String> listView2;



        @FXML
        private BorderPane mainPane2;
        @FXML
        public BorderPane bPane1;
        @FXML
        public AnchorPane ap;
    @FXML
    public TableView table1;

    @FXML
    public TableColumn<Room, Integer> roomNoCol;


    @FXML
    public TableColumn<Room,String> roomNameCol;



    @FXML
    private void testBtn(ActionEvent e){
        System.out.println("button pressed");
    }

    @FXML
    private void addCustomers(ActionEvent e){
        bPane1.setCenter(ap);
       loadPage("screen1");


    }













    private void loadPage(String screen){
        Parent root=null;
        try {
            root= FXMLLoader.load(getClass().getResource(screen+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bPane1.setCenter(root);
    }


    static Room[] r= new Room[8];






    @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {








    }
  public static   Room[] ROOMS = initialise();

    public void deleteCustomer(ActionEvent event) {

        bPane1.setCenter(ap);
        loadPage("screen2");
    }


    // controller functions for screen 1 and screen 2
    @FXML
    public TextField roomNo;
    @FXML
    public TextField roomName;
    @FXML
    public TextField payerSurname;
    @FXML
    public TextField payerCardNo;
    @FXML
    public TextField payerNoGuests;
    @FXML
    public TextField payerFName;

    private static Room[] initialise(){
        Room[]  r= new Room[8];
        r[0]= new Room(0,"e",null,0);
        r[1]= new Room(1,"e",null,0);
        r[2]= new Room(2,"e",null,0);
        r[3]= new Room(3,"e",null,0);
        r[4]= new Room(4,"e",null,0);
        r[5]= new Room(5,"e",null,0);
        r[6]= new Room(6,"e",null,0);
        r[7]= new Room(7,"e",null,0);


        return r;
    }





    @FXML
    private void btnSubmit(ActionEvent e){
        StringWriter sw = new StringWriter();
        try{
            int No= Integer.parseInt(roomNo.getText());
            int noGuests= Integer.parseInt(payerNoGuests.getText());
            if(No>7){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Room no must be less than or equal 7!");
                alert.showAndWait();
            }
            long cardNo = Long.parseLong(payerCardNo.getText());
            String Name= roomName.getText();
            String surname= payerSurname.getText();
            String firstname=payerFName.getText();

            var p = new Person(firstname,surname,cardNo);
            Room room= new Room(No,Name,p,noGuests);
            ROOMS[No]=room;

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Customer added successfully!");
            alert.showAndWait();



        }catch(NumberFormatException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Number input only!");
            ex.printStackTrace(new PrintWriter(sw));
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();


        }
    }

    @FXML
    public TextField deleteRoomNo;


    public void btnDelete(ActionEvent event) {
        Hotel h= new Hotel();

        try{
            int roomNo = Integer.parseInt(deleteRoomNo.getText());
            if(roomNo>7 && roomNo<0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Room No must be less than or equal 7!");
                alert.showAndWait();
            }
            else{

                 ROOMS[roomNo].setRoomName("e");
                 ROOMS[roomNo].setNoOfGuests(0);
                 ROOMS[roomNo].setPayee(null);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Customer deleted successfully!");
                alert.showAndWait();
            }

        }catch(NumberFormatException ex){
            StringWriter sw = new StringWriter();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Number input only!");
            ex.printStackTrace(new PrintWriter(sw));
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
        }

    }

    // screen 3 controller




    @FXML
    private void btnViewAll(ActionEvent e){
        bPane1.setCenter(ap);
        loadPage("screen3");


    }

    @FXML
    private void btnView(ActionEvent e){

        String[] roomNos;
        roomNos= new String[]{"0", "1", "2", "3", "4", "5", "6", "7"};

        listView1.getItems().addAll(roomNos);
        listView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(!ROOMS[Integer.parseInt(listView1.getSelectionModel().getSelectedItem())].getRoomName().equals("e")){
                    labelOccupy.setVisible(true);
                    labelOccupy.setText("Yes");
                }else{
                    labelOccupy.setVisible(true);
                    labelOccupy.setText("No");
                }
            }
        });


    }


    public void findCustomer(ActionEvent event) {
        bPane1.setCenter(ap);
        loadPage("screen4");


    }
    @FXML
    public TextField cusName;

    public void btnFind(ActionEvent event) {


       String customer= cusName.getText();
       boolean Isfind=false;
       for(Room room:ROOMS){
           if(room.getRoomName().equals(customer)){
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
               alert.setHeaderText("Room No" +room.getRoomNo()+" is occupied by :"+ customer);
               alert.showAndWait();
               Isfind=true;
               break;
           }

       }
       if(Isfind==false){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText("No results found!");
           alert.showAndWait();
       }

    }
    @FXML
    public void btnOrder(ActionEvent event) {


        ArrayList<String> arr = new ArrayList<String>(8);

        String[] roomNames=new String[8];
        for(Room rm:ROOMS){
            arr.add(rm.getRoomName());
        }




        Collections.sort(arr);
        listView2.getItems().addAll(arr);


    }







    @FXML
    public void ViewOrder(ActionEvent event) {

        bPane1.setCenter(ap);
        loadPage("screen5");


    }
    @FXML
    public void storeData(ActionEvent event) {
        bPane1.setCenter(ap);
        try {
            File file = new File("details.txt");
            FileWriter writer = new FileWriter(file);
            for (Room rm:ROOMS) {
                writer.write("Room No" + "-" + rm.getRoomNo() + "-" + rm.getRoomName()+"paying by"+ rm.payee.getSurName()+"-"+ rm.payee.getFirstName()+rm.payee.getCardNo()+" no of guests" +rm.getNoOfGuests() + "\n");
            }
            writer.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Successfully Write to the file!");
            alert.showAndWait();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }
    @FXML
    public     void loadData(ActionEvent event){
        bPane1.setCenter(ap);

        try{

            File file= new File("details.txt");
            Scanner rdr = new Scanner(file);
            while(rdr.hasNextLine()){
                String data= rdr.nextLine();
                String[] values= data.split("-");
                ROOMS[Integer.parseInt(values[1])].setRoomName(values[2]);
                ROOMS[Integer.parseInt(values[1])].setRoomNo(Integer.parseInt(values[1]));
                ROOMS[Integer.parseInt(values[1])].payee.setSurName(values[4]);
                ROOMS[Integer.parseInt(values[1])].payee.setFirstName(values[5]);
                ROOMS[Integer.parseInt(values[1])].payee.setFirstName(values[5]);
                ROOMS[Integer.parseInt(values[1])].payee.setCardNo(Integer.parseInt(values[6]));
                ROOMS[Integer.parseInt(values[1])].payee.setFirstName(values[5]);
                ROOMS[Integer.parseInt(values[1])].setNoOfGuests(Integer.parseInt(values[8]));
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Load the data successfully");
            alert.showAndWait();


        }catch(Exception ex){
            ex.getStackTrace();
        }

    }
        @FXML
        public Stage stage;

    @FXML
    public void btnExit(MouseEvent mouseEvent) {
        bPane1.setCenter(ap);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Exit");
        alert.setContentText("Do you want to exit?");
        alert.showAndWait();
        if(alert.showAndWait().get()==ButtonType.OK){
          stage= (Stage) bPane1.getScene().getWindow();
          stage.close();
        }
    }
}