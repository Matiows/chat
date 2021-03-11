/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_client;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkConn {
    int port;
    String host;
    private String status;
    DataInputStream fromServer;
    DataOutputStream toServer;
    
 NetworkConn(){
try{
// Create a socket to connect to the server
Socket socket = new Socket(host="MENGSTAB-PC",port=59662);

fromServer = new DataInputStream(socket.getInputStream());
toServer =new DataOutputStream(socket.getOutputStream());
}
catch (Exception ex) {
    status="NO CONNECTION";
    System.out.println(status);
}
}
 public void senddata(String message)throws Exception{
 
 toServer.writeUTF(message);
 toServer.flush();
      System.out.println("finish seending data...");

 }
 public String recievedata() throws Exception{
    String message="";
            
message=fromServer.readUTF();
 return message;
 }
}
