
package chat_client;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


class validate {
    NetworkConn con;
   private String froserver;//junk from server after loging in
   private String groupchat;//parsed group message from froserver data
   private String nametext;//parsed chat history along with names from froserver data
   private String password;//parsed password from froserver data
  public void openconn(){con=new NetworkConn();} 
   int validate(String ...t){
     int flag=-1;
     String all="";
    for(String a:t)
    {   if(a.isEmpty()){flag=0;break;  }
        else{flag=1;}
    all+=a;
    all+='~';
    }
   System.out.println(flag);
   if(flag==1){//if text passes first validation// create new conn to server and get data
       
         try {//sends message to the server 
             this.openconn();
             con.senddata(all);
         } catch (Exception ex) {
             Logger.getLogger(validate.class.getName()).log(Level.SEVERE, null, ex);
         }
   
         try {//recieve data from the server
                
                 froserver=con.recievedata();
                 
               System.out.println(froserver);

             } catch (Exception ex) {
                 Logger.getLogger(validate.class.getName()).log(Level.SEVERE, null, ex);
             }
    String[] tempdata = froserver.split("~"); //Temporary string to parse the elements in it
   if(tempdata[0].startsWith("no")){return flag=-1;}//the user is not on the  server
   else if(tempdata[0].equalsIgnoreCase("yes")){// the user does exist or in case of creation it is successful
   if(all.startsWith("login")){nametext=tempdata[1]; groupchat=tempdata[2];flag=1;}
   else if(all.startsWith("find")){password=tempdata[1];flag=1;}
   else if(all.startsWith("signup")){flag=2;}
       }
   }
    return flag;
    }
  
  String getgroupchat() {return groupchat;}
  String getnametext(){return nametext;}//returns string of name with whom current user has history
  String getpassword(){return password;}  
    }
    
    
   

