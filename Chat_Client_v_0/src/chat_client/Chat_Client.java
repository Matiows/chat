
package chat_client;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Chat_Client extends Application {
    
    
    //all the scene 
    Scene loginScene;
    Scene welcomeScene;
    Scene forgetPassword;
    Scene signupScene;
    Scene homeScene;
    Boolean out = false;
    validate va;
    chathistory newchat[];
    
    @Override
    @SuppressWarnings("empty-statement")
    public void start(Stage primaryStage) {
        
        
        //wlcomeScene
        ImageView welcomePNG = new ImageView("wel.png");
        Pane st1 = new Pane(welcomePNG);
        welcomeScene = new Scene(st1, 570, 540,Color.BLACK);
        
        //Forget Password Scene
        Button back2  =new Button("<");
        back2.setStyle("-fx-font-size:17px;-fx-background-color:darkgray;-fx-text-fill:white;");
        Label lb3 = new Label("Forget Password");
        Label warn2 = new Label("Warning");warn2.getStyleClass().add("warn");
        lb3.getStyleClass().add("title");
        TextField tfdUsername3 = new TextField();
        tfdUsername3.setMaxWidth(220);
        tfdUsername3.setPromptText("User Name");
        TextField tfdsq = new TextField();
        tfdsq.setMaxWidth(220);
        tfdsq.setPromptText("Recovery ID");
        Button btnFind = new Button("Find");
        btnFind.setMinWidth(160);
        btnFind.setPadding(new Insets(15,0,0,0 ));
        btnFind.getStyleClass().add("loginBtn");
        VBox vBox3 = new VBox(10);
        VBox.setMargin(btnFind,new Insets(25,0,5,0));
        vBox3.setAlignment(Pos.CENTER);
        vBox3.getChildren().addAll(lb3,warn2,tfdUsername3,tfdsq,btnFind);
        VBox vBoxforget=new VBox();
        vBoxforget.getChildren().addAll(back2,vBox3);
        VBox.setMargin(back2,new Insets(10));
        Scene forgetScene = new Scene(vBoxforget,570,540);
        forgetScene.getStylesheets().add("style.css");
        
        //Signup Scene
        Button back  =new Button("<");
        back.setStyle("-fx-font-size:17px;-fx-background-color:darkgray;-fx-text-fill:white;");
        Label lbS = new Label("Sign Up");
        lbS.getStyleClass().add("title");
        Label warn3 = new Label("");warn3.getStyleClass().add("warn");
        TextField tfdFullnameS = new TextField();
        tfdFullnameS.setMaxWidth(240);
        tfdFullnameS.setPromptText("Full Name");
        TextField tfdUsernameS = new TextField();
        tfdUsernameS.setMaxWidth(240);
        tfdUsernameS.setPromptText("Username");
        TextField tfdPasswordS = new TextField();
        tfdPasswordS.setPromptText("Password");
        tfdPasswordS.setMaxWidth(240);
        TextField tfdPassword2S = new TextField();
        tfdPassword2S.setPromptText("Confirm Password");
        tfdPassword2S.setMaxWidth(240);
        TextField tfdRecovery = new TextField();
        tfdRecovery.setPromptText("Recovery ID");
        tfdRecovery.setMaxWidth(240);
        Button btnLoginS = new Button("Create account");
        btnLoginS.setMinWidth(160);
        btnLoginS.setPadding(new Insets(15,0,0,0 ));
        btnLoginS.getStyleClass().add("loginBtn");
        VBox vBoxS = new VBox(10);
        VBox.setMargin(btnLoginS,new Insets(25,0,5,0));
        vBoxS.setAlignment(Pos.CENTER);
        vBoxS.getChildren().addAll(lbS,warn3,tfdFullnameS,tfdUsernameS,tfdPasswordS,tfdPassword2S,tfdRecovery,btnLoginS);
        VBox vBoxCreate = new VBox(0);
        VBox.setMargin(back,new Insets(10));
        vBoxCreate.getChildren().addAll(back,vBoxS);
        signupScene = new Scene(vBoxCreate,570,540);
        signupScene.getStylesheets().add("style.css");
        
        //Login Scene
        Label lb = new Label("LOGIN");
        Label warn = new Label("Warning");
        warn.getStyleClass().add("warn");
        lb.getStyleClass().add("title");
        TextField tfdUsername = new TextField();
        tfdUsername.setMaxWidth(220);
        tfdUsername.setPromptText("username");
        PasswordField tfdPassword = new PasswordField();
        tfdPassword.setMaxWidth(220);
        tfdPassword.setPromptText("password");
        Button btnLogin = new Button("Login");
        btnLogin.setMinWidth(160);
        btnLogin.setPadding(new Insets(15,0,0,0 ));
        btnLogin.getStyleClass().add("loginBtn");
        Label lbCreate = new Label("To create account.");
        Button btnCreate = new Button("Sing Up");
        Label lbCreate2 = new Label("or");
        Button btnForget = new Button("Forget password");
        btnCreate.getStyleClass().add("btnCreate");
        btnForget.getStyleClass().add("btnCreate");
        HBox hBox = new HBox(0);
        hBox.getChildren().addAll(lbCreate,btnCreate,lbCreate2,btnForget);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(10);
        VBox.setMargin(btnLogin,new Insets(25,0,5,0));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(lb,warn,tfdUsername,tfdPassword,btnLogin,hBox);
        loginScene = new Scene(vBox, 570, 540);
        loginScene.getStylesheets().add("style.css");
        
        //HOME scene 
        Label status = new Label("connected");status.setLayoutX(459);status.setLayoutY(515);status.setId("status");
        Label usernameLBL = new Label("Mengstab");
        usernameLBL.getStyleClass().add("profile");
        StackPane profilePane = new StackPane(usernameLBL);
        Button person_btn = new Button("Personal Chat");person_btn.getStyleClass().add("catagory");
        Button group_btn = new Button("Group Chat");group_btn.getStyleClass().add("catagory");
        Button online_btn = new Button("Online user");online_btn.getStyleClass().add("catagory");
        Button setting = new Button("Setting");setting.getStyleClass().add("catagory");
        Button logout = new Button("Logout");logout.setId("logout");
        VBox rectangle = new VBox(17,profilePane,person_btn,group_btn,online_btn,setting,logout);
        rectangle.setLayoutX(-270);rectangle.setLayoutY(0);
        rectangle.setMinHeight(540);rectangle.setMinWidth(270);
        rectangle.setOnMouseClicked(e->{e.consume();});
        TranslateTransition anim = new TranslateTransition(new Duration(250), rectangle);
        Button menubar = new Button();
        menubar.setOnAction(e->{if(out)return;anim.setFromX(-270);anim.setToX(270);anim.play();out=true;});
        menubar.getStyleClass().add("homebar");
        menubar.setGraphic(new ImageView("menu.png"));
        Pane homePane = new Pane(menubar,status,rectangle);
        homePane.setOnMouseClicked(e->{if(!out)return;anim.setFromX(270);anim.setToX(-270);anim.play();out=false;});
        homeScene = new Scene(homePane,570,540);
        homeScene.getStylesheets().add("styleHome.css");
    
            //chat Window for each user
        //*******User Scene
        TextArea chatWindowUser = new TextArea();chatWindowUser.setEditable(false);chatWindowUser.setPrefRowCount(20);chatWindowUser.setPrefColumnCount(25);chatWindowUser.setId("chatwindow");chatWindowUser.wrapTextProperty().set(true);
        TextField messageFieldUser = new TextField();messageFieldUser.setId("message_field");messageFieldUser.setPrefColumnCount(30);
        Button send_btnUser = new Button("Send");send_btnUser.setId("send_btn");send_btnUser.setDefaultButton(true);HBox hbox_GUser = new HBox(5,messageFieldUser,send_btnUser);
        VBox vbox_GUser = new VBox(chatWindowUser,hbox_GUser);
        vbox_GUser.setLayoutX(150); vbox_GUser.setLayoutY(50);
    
        
        
         //inside the home scene we have other subscene i wll creat each sub scene
         //******Personal scene this scene have a list of user name 
         ListView<String> usersList = new ListView<>(FXCollections.observableArrayList("Mengstab","Matiwos"));
         //editng each list cell used for adding event handler and to custmize each cell individually
         usersList.setCellFactory(e->{return new ListCell<String>(){
             @Override
             protected void updateItem(String item,boolean empty){
                 super.updateItem(item,empty);
                 if(empty)return;
                 setText(item);
                 setGraphic(new ImageView("picon.png"));
                 setOnMouseClicked(e->{
                    if(e.getClickCount()==2){
                    if(homePane.getChildren().contains(vbox_GUser))return;
                    if(homePane.getChildren().size()!=3)homePane.getChildren().remove(0);
                    homePane.getChildren().add(0,vbox_GUser);}});
                  }
         };});
         StackPane personalScene = new StackPane(usersList);
         personalScene.setLayoutX(150);
         personalScene.setLayoutY(50);
        
        //*******Group Scene
        TextArea chatWindow = new TextArea();chatWindow.setEditable(false);chatWindow.setPrefRowCount(20);chatWindow.setPrefColumnCount(25);chatWindow.setId("chatwindow");chatWindow.wrapTextProperty().set(true);
        TextField messageField = new TextField();messageField.setId("message_field");messageField.setPrefColumnCount(30);
        Button send_btn = new Button("Send");send_btn.setId("send_btn");send_btn.setDefaultButton(true);HBox hbox_G = new HBox(5,messageField,send_btn);
        VBox vbox_G = new VBox(chatWindow,hbox_G);
        vbox_G.setLayoutX(150); vbox_G.setLayoutY(50);
        
        //*********Online User
        //this one is the same with the personal scene we can just copy the code or we can figure some thign more accurate
        
        
        
        
        ListView<String> onlineusersList = new ListView<>(FXCollections.observableArrayList("Mengstab","Matiwos"));
         //editng each list cell used for adding event handler and to custmize each cell individually
         onlineusersList.setCellFactory(e->{return new ListCell<String>(){
             @Override
             protected void updateItem(String item,boolean empty){
                 super.updateItem(item,empty);
                 if(empty)return;
                 setText(item);
                 setGraphic(new ImageView("picon.png"));
                 setOnMouseClicked(e->{
                if(e.getClickCount()==2){if(homePane.getChildren().contains(vbox_GUser))return;
        if(homePane.getChildren().size()!=3)homePane.getChildren().remove(0);
        homePane.getChildren().add(0,vbox_GUser);
               }});
             }
         };});
         StackPane onlineusers = new StackPane(onlineusersList);
         onlineusers.setLayoutX(150);
         onlineusers.setLayoutY(50);
        
        //************Setting
        //for the time being we can't work on this dueto the time shortage but....bla bla bal .
        
        
        
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Ergb!");
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
         va=new validate();
        
        //all the Event Handler are here
        
        welcomeScene.setOnMouseClicked(e->{primaryStage.setScene(loginScene);vBox.requestFocus();});
        back2.setOnAction(e->{ primaryStage.setScene(loginScene);});
        btnForget.setOnAction(e->{primaryStage.setScene(forgetScene); });//goto
        btnFind.setOnAction((ActionEvent e) -> {
            int i=(va.validate("find",tfdUsername3.getText(),tfdsq.getText()));
            if(i==0){warn2.setText("One or more Fields Are Empty");}
            else if(i==-1){warn2.setText("Couldn't find your account");}
            else if(i==1){warn.setText("Here is your password: "+va.getpassword());primaryStage.setScene(loginScene);}
        });
        btnCreate.setOnAction(e->{ primaryStage.setScene(signupScene); });
        btnLoginS.setOnAction(e->{
           
            int i=(va.validate("signup",tfdUsernameS.getText(),tfdPasswordS.getText(),tfdRecovery.getText()));
            if(i==0){warn3.setText("One or more Fields Are Empty");}
            else if(i==-1){warn3.setText("Account Already Exists");}
            else if(i==2){warn3.setText("Account Created successful");}
        
        });
        //start of logining event handler
        btnLogin.setOnAction((ActionEvent e) -> {
            int i=(va.validate("login",tfdUsername.getText(),tfdPassword.getText()));
            if(i==0){warn.setText("One or more Fields Are Empty");primaryStage.setScene(loginScene);}
            else if(i==-1){warn.setText("Couldn't connect now");}
            else if(i==1){
                va.getnametext();
                
                primaryStage.setScene(homeScene);}//if successfull login
        });
        back.setOnAction(e->{primaryStage.setScene(loginScene);}); 
        
        person_btn.setOnAction(e->{if(homePane.getChildren().contains(personalScene))return;
        if(homePane.getChildren().size()!=3)homePane.getChildren().remove(0);
        homePane.getChildren().add(0,personalScene);if(!out)return;anim.setFromX(270);anim.setToX(-270);anim.play();out=false;});
        
        group_btn.setOnAction(e->{if(homePane.getChildren().contains(vbox_G))return;
        if(homePane.getChildren().size()!=3)homePane.getChildren().remove(0);
        homePane.getChildren().add(0,vbox_G);if(!out)return;anim.setFromX(270);anim.setToX(-270);anim.play();out=false;});
        
        online_btn.setOnAction(e->{if(homePane.getChildren().contains(onlineusers))return;
        if(homePane.getChildren().size()!=3)homePane.getChildren().remove(0);
        homePane.getChildren().add(0,onlineusers);if(!out)return;anim.setFromX(270);anim.setToX(-270);anim.play();out=false;});
        
        logout.setOnAction(e->{primaryStage.setScene(loginScene);if(!out)return;anim.setFromX(270);anim.setToX(-270);anim.play();out=false;});
        /*
        setting.setOnAction(e->{if(homePane.getChildren().contains(personalScene))return;
        if(homePane.getChildren().size()!=3)homePane.getChildren().remove(0);
        homePane.getChildren().add(0,personalScene);});*/
        
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
