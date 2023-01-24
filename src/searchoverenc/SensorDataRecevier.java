/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchoverenc;

import jssc.SerialPort;
import jssc.SerialPortException;
import util.SensorDatakeeper;

/**
 *
 * @author welcome
 */
public class SensorDataRecevier extends Thread {
       public void run()
    {
         String str1;
         
        //In the constructor pass the name of the port with which we work
        SerialPort serialPort1 = new SerialPort("COM4");
      
        try {
            //Open port
            serialPort1.openPort();
         

            //We expose the settings. You can also use this line - serialPort.setParams(9600, 8, 1, 0);
            serialPort1.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
         

            Thread.sleep(1000);//Very important !!!


String dis=""; String mq=""; String temp="";
            //Read data from port
       for (int i = 0; i >-1; i++) 
            {
                str1 = serialPort1.readString();
          
              // System.out.println("sensor Value "+str1);
        
                if (str1 != null) {
                 //  System.out.println(str1);
                 
                 if(str1.contains(":"))
                 {
                    // System.out.println(str1);
                     String st[] = str1.split(":");
                     dis = st[0];
                     dis = dis.trim();
                     temp = st[1];
                     temp = temp.trim();
                  
                     System.out.println("Distance  : " + dis);
                       System.out.println("temp  : " + temp);

                       
                       
                   String date=new CurrentDate().getDate();
                   String time=new CurrentTime().getTime();
                   
                   
                           
                           
                   String str=dis+"#"+temp+"#"+date+"#"+time;
                  
                   SensorDatakeeper.q.add(str);
                  System.out.println("Added in Queue ====== "+str);
                      
                      
                       
                       
                 }
                    
               
                 
                
                }
                

                  
                Thread.sleep(500);
            }
            
            //Closing the port
            serialPort1.closePort();
          

            

        } catch (SerialPortException ex) {
            System.out.println(ex);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
    
}
