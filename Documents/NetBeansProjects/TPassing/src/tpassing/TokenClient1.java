/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpassing;

    import java.io.*; 
    import java.net.*; 
    public class TokenClient1 {     
        public static void main(String arg[]) throws Exception         {             
            InetAddress lclhost;             
            BufferedReader br;             
            String str="";             
            TokenClient12 tkcl,tkser;             
            //boolean hasToken;             
            //boolean setSendData;                         

            while(true)             {             
                lclhost=InetAddress.getLocalHost();             
                tkcl = new TokenClient12(lclhost);             
                tkser = new TokenClient12(lclhost);                    
                //tkcl.setSendPort(9001);             
                tkcl.setSendPort(9004);             
                tkcl.setRecPort(8002);             
                lclhost=InetAddress.getLocalHost();             
                tkser.setSendPort(9000);             

                if(tkcl.hasToken == true)             {                 
                    System.out.println("Do you want to enter the Data --> YES/NO");                 
                    br=new BufferedReader(new InputStreamReader(System.in));                 
                    str=br.readLine();                 
                    if(str.equalsIgnoreCase("yes"))                 {                       
                        System.out.println("ready to send");                     
                        tkser.setSendData = true;                     
                        tkser.sendData();                     
                        tkser.setSendData = false;                 
                        }                 
                    else if(str.equalsIgnoreCase("no"))                 {                     
                        System.out.println("i m in else");                     
                        //tkcl.hasToken=false;                     
                        tkcl.sendData();                     
                        tkcl.recData();                 
                        System.out.println("i m leaving else");                 
                        }             
                    }             
                else             {             
                    System.out.println("ENTERING RECEIVING MODE...");                 
                    tkcl.recData();             
                    }     
                } 
            } 
        }                    
    class TokenClient12 {     
        InetAddress lclhost;     
        int sendport,recport;     
        boolean hasToken = true;     
        boolean setSendData = false;     
        TokenClient12 tkcl,tkser;     
        TokenClient12(InetAddress lclhost)     {                 
            this.lclhost = lclhost;     
            }         
        void setSendPort(int sendport)     {         
            this.sendport = sendport;     
            }     void setRecPort(int recport)        {         
                this.recport = recport;     
                }     
            void sendData() throws Exception         {         
                BufferedReader br;         
                String str="Token";         
                DatagramSocket ds;         
                DatagramPacket dp;                     

                if(setSendData == true)         {             
                    System.out.println("sending ");             
                    System.out.println("Enter the Data");             
                    br=new BufferedReader(new InputStreamReader(System.in));             
                    str = "ClientOne....." + br.readLine();             
                    System.out.println("now sending");                             
                    }             
                ds = new DatagramSocket(sendport);             
                dp = new DatagramPacket(str.getBytes(),str.length(),lclhost,sendport-1000);             
                ds.send(dp);             
                ds.close();             
                setSendData = false;             
                hasToken = false;     
                }         

            void recData()throws Exception     {         
                String msgstr;         
                byte buffer[] = new byte[256];         
                DatagramSocket ds;         
                DatagramPacket dp;         
                ds = new DatagramSocket(recport);         
                dp = new DatagramPacket(buffer,buffer.length);         
                ds.receive(dp);         
                ds.close();         
                msgstr = new String(dp.getData(),0,dp.getLength());         
                System.out.println("The data is "+msgstr);                 

                if(msgstr.equals("Token"))             {                 
                    hasToken = true;             
                    }     
                }     
    }
