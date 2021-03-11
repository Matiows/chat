package servermati;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;


public class validation {
File file;
String[] data;//signup~username~passwored~securityId  //login~username~passwored  //forget~username~securityID 
FileInputStream dataBaseIn;
FileOutputStream dataBaseOut;
DataInputStream inputstream;
Socket socket;
DataOutputStream outputstream;

    
    
public validation(Socket x){
    file = new File("client");
    socket = x;
    try {
        inputstream = new DataInputStream(x.getInputStream());
        outputstream = new DataOutputStream(x.getOutputStream());
        acceptResponse();
        if(data[0].startsWith("sig")){
            registerUser();
        }
        else if(data[0].startsWith("log")){
            checkPassword();
        }
        else if(data[0].startsWith("find")){
            if(checkUserName())
                sendPassword();
            else sendRespons("no");
        }

    } catch (IOException ex) {
        ex.printStackTrace();
        
    }
}

public void closeStream(String what,boolean socket) throws IOException{
    if(socket){
        if(what.startsWith("out"))
           outputstream.close();
        else if(what.startsWith("in"))
           inputstream.close();
    }
    else{
        if(what.startsWith("out"))
           dataBaseOut.close();
        else if(what.startsWith("in"))
           dataBaseIn.close();
    }
    
}

public void sendRespons(String message) throws IOException{
    outputstream.writeUTF(message);
    outputstream.flush();
}

public String acceptResponse() throws IOException{
    String message="";
    message = inputstream.readUTF();
    data = message.split("~");
    System.out.println("adata have been recived"+message);
    return message;
}

public boolean checkUserName() throws FileNotFoundException{
    for(String s : file.list()){
        if(s.startsWith(data[1]))
            return true;
    }
    return false;
}
public void registerUser() throws FileNotFoundException, IOException{
    if(checkUserName()){
        sendRespons("no~");
        return;
    }
    else{
        System.out.println("Regisstering the user...");
        dataBaseOut = new FileOutputStream("client/"+data[1]+".txt");
        dataBaseOut.write((data[1]+"~"+data[2]+"~"+data[3]+"~").getBytes());
        dataBaseOut.flush();
        System.out.println("Sennding data...");
        sendRespons("yes~");
        System.out.println("file hase been send");
        closeStream("out",true);
    }
    
 }
public void openConnetion() throws IOException{
    inputstream = new DataInputStream(socket.getInputStream());
    outputstream = new DataOutputStream(socket.getOutputStream());
}

public  boolean checkPassword() throws FileNotFoundException, IOException {
        if(checkUserName())
            dataBaseIn = new FileInputStream("client/"+data[1]+".txt");
            byte[] b = new byte[dataBaseIn.available()];
            dataBaseIn.read(b);
            String message = new String(b);
            if (message.indexOf(data[2])!=-1){
                sendRespons(message);
                closeStream("in", false);
                return true;            
            }
            closeStream("in", false);
            return false;
        }

    public  void sendPassword() throws FileNotFoundException, IOException{
            dataBaseIn = new FileInputStream("client/"+data[1]+".txt");
            byte[] b = new byte[150];
            dataBaseIn.read(b);
            String message = new String(b);
            String passwored = message.split("~",2)[1];
                sendRespons("yes~"+passwored);
                System.out.println("passwored: "+passwored);
        
    }







}
