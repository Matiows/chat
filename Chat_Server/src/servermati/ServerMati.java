package servermati;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMati {
    File file = new File("client");
    FileInputStream dataBaseIn;
    FileOutputStream dataBaseOut;
    ServerSocket server;
    public ServerMati() throws IOException{
        startServer();
    }
    

    public static void main(String[] args) throws Exception {
        new ServerMati();
    }

    private void startServer() throws IOException {
        int i = 0;
        while(true){
        System.out.println("Stating server...");
        server = new ServerSocket(0);
        System.out.println(server.getLocalPort());
        Socket socket = server.accept();
        System.out.println(++i +" client have added");
        Thread t = new Thread(()->{
            validation v = new validation(socket);
        });
        t.start();
        }        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public class User{
        InputStream inputstream;
        OutputStream outputstream;
        
        
        
        public User(Socket socket){
            
            
        }
        
        
        
    }
    
}
