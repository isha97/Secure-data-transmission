/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Javaminiproject1;

/**
 *
 * @author aparna
 */
import java.io.*;
import java.net.*;  
import java.util.*;
import java.lang.*;
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.nio.file.Files;
import java.security.InvalidKeyException;  
import java.security.NoSuchAlgorithmException;  
import javax.crypto.BadPaddingException;  
import javax.crypto.Cipher;  
import javax.crypto.IllegalBlockSizeException;  
import javax.crypto.KeyGenerator;  
import javax.crypto.NoSuchPaddingException;  
import javax.crypto.SecretKey;  
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import apache.commons.io.FilenameUtils;

public class DecryptFile {  
    static String extension;
    KeyGenerator keyGenerator = null;  
  //  SecretKey secretKey = null;  
    Cipher cipher = null;  
  
    public DecryptFile() {  
        try {  
           
                //keyGenerator = KeyGenerator.getInstance("AES");  
                // secretKey = keyGenerator.generateKey();  
                String stringKey = "sgyrd";
                byte[] encodedKey= stringKey.getBytes();
                // byte[] encodedKey     = Base64.decode(stringKey, Base64.DEFAULT);
                SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
                cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
        } catch (NoSuchPaddingException ex) {  
            System.out.println(ex);  
        } catch (NoSuchAlgorithmException ex) {  
            System.out.println(ex);  
        }  
    }     
  
    public static void Decrypt(String filename) throws IOException{  
	try{
            int filesize=1022386; 
	    int bytesRead;
	    int currentTot = 0;
            JOptionPane.showMessageDialog(null,
                "Waiting for server to connect... ","Message",
            JOptionPane.INFORMATION_MESSAGE);
            int i = filename.lastIndexOf('.');
            if (i > 0) {
                extension = filename.substring(i+1);
            }
	    Socket socket = new Socket("10.42.0.1",15120);
            JOptionPane.showMessageDialog(null,
                "Connection Established! ","Message",
            JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Connection Established!");
	    byte [] bytearray  = new byte [filesize];
	    InputStream is = socket.getInputStream();
            System.out.println(extension);
	    FileOutputStream fos = new FileOutputStream("copy." + extension);
	    BufferedOutputStream bos = new BufferedOutputStream(fos);
	    bytesRead = is.read(bytearray,0,bytearray.length);
	    currentTot = bytesRead;

	    do {
	       bytesRead =
	          is.read(bytearray, currentTot, (bytearray.length-currentTot));
	       if(bytesRead >= 0) currentTot += bytesRead;
	    } while(bytesRead > -1);

	    bos.write(bytearray, 0 , currentTot);
	    bos.flush();
	    bos.close();
	    socket.close();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null,
                "File Not Received ","Message",
            JOptionPane.INFORMATION_MESSAGE);
            System.out.println("IO Exception");
        }  
        int i = filename.lastIndexOf('.');
        if (i > 0) {
            extension = filename.substring(i+1);
        }
        String encryptedFile = "";
        System.out.println(extension);
        File file = new File("C:\\Users\\Huzaifa\\Documents\\NetBeansProjects\\OOP_client\\copy." + extension);
        /*if(extension.equals("txt"))
            encryptedFile = encryptedFile +"encryptedFile.txt";
        else
            encryptedFile = encryptedFile +"encryptedFile."+extension; */
        encryptedFile = filename ;
        String directoryPath = file.getPath();  
        Getkey f = new Getkey(directoryPath,encryptedFile);
        /*DecryptFile decryptFile = new DecryptFile();
        System.out.println("Starting Decryption..."); 
        JOptionPane.showMessageDialog(null,
            "Starting Decryption...... ","Message",
        JOptionPane.INFORMATION_MESSAGE);
        
        decryptFile.decrypt(directoryPath,  
            "C:\\Users\\Huzaifa\\Documents\\NetBeansProjects\\OOP_client\\" + encryptedFile,key);  
        //System.out.println("Encryption completed..."); 
        JOptionPane.showMessageDialog(null,
            "Decyption completed! ","Message",
        JOptionPane.INFORMATION_MESSAGE);
       
          */         // ae.setupscreen()
          
          
            
        
    }  
  
        /*
        * Encrypts the file in srcPath and creates a file in destPath 
        */  
    void decrypt(String srcPath, String destPath,String key) {  
	 
        //System.out.println("inside encrypt");
        String seckey;
        
        seckey =key;
        //System.out.println("\nEnter key : ");
        //seckey = in.next();
        if(seckey.length() >16)
            seckey = seckey.substring(0,16);
        else if(seckey.length() <16)
        {
            while(seckey.length()<16)
                seckey = seckey + seckey;
            seckey = seckey.substring(0,16);
        }
        byte[] encodedKey= seckey.getBytes();
            //byte[] encodedKey     = Base64.decode(seckey, Base64.DEFAULT);
        SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        File encryptedFile = new File(srcPath);  
        File decryptedFile = new File(destPath);  
        InputStream inStream = null;  
        OutputStream outStream = null;  
        try {  
            /** 
            * Initialize the cipher for encryption 
            */  
            cipher.init(Cipher.DECRYPT_MODE, secretKey);  
            /** 
            * Initialize input and output streams 
            */  
            inStream = new FileInputStream(encryptedFile);  
            outStream = new FileOutputStream(decryptedFile);  
            byte[] buffer = new byte[1024];  
            int len;  
            while ((len = inStream.read(buffer)) > 0) {  
                outStream.write(cipher.update(buffer, 0, len));  
                outStream.flush();  
            }  
            outStream.write(cipher.doFinal());  
            inStream.close();  
            outStream.close();  
            
        } catch (IllegalBlockSizeException ex) {  
            System.out.println(ex);  
        } catch (BadPaddingException ex) { 
            JOptionPane.showMessageDialog(null,
                "Wrong Key ","Message",
            JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Wrong Key");  
        } catch (InvalidKeyException ex) {  
            System.out.println(ex);  
        } catch (FileNotFoundException ex) {  
            System.out.println(ex);  
        } catch (IOException ex) {  
            System.out.println(ex);  
        }  
    }  
}  