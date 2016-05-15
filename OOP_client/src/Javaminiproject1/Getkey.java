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
//import Javaminiproject1.Javaminiproject1.screen;
//import static Javaminiproject1.screen4.Seckey;
import java.awt.Desktop;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Getkey extends JFrame{
    
    static String Seckey;
    String Skey;
    String returnsecretkey(){
        return Seckey;
    }    
    JPanel p1;
    Container c;
    //public String Seckey;
    //p1.setlayout(new GridLayout(4,2,10,10));
    private JLabel key;
    private JTextField n;
    private JButton enter,clear;
    
    
    
    Getkey(String directoryPath,String encryptedFile)
    {
        
        setTitle("Enter Key");
        c=getContentPane();
        c.setLayout(new BoxLayout(c,3));
        
        p1=new JPanel();
        p1.setLayout(new GridLayout(6,2,10,10));
        setSize(400,400);
        setVisible(true);
        
        key=new JLabel("Enter Key");
        
        
        n=new JTextField(40);
        
        
        
        enter=new JButton("Enter");
        clear=new JButton("Reset");
       
        p1.add(key);p1.add(n);
        p1.add(enter);p1.add(clear);
       
        c.add(p1);
        
        clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ex)
            {
                n.setText("");
            }
        });
        enter.addActionListener(new ActionListener(){
            //System.out.println(Seckey);
            //Code for saving log in details to file
            public void actionPerformed(ActionEvent ex)
            {
                //try {
                        
                    Skey = n.getText();
                    dispose();
                    DecryptFile decryptFile = new DecryptFile();
                    System.out.println("Starting Decryption..."); 
                    JOptionPane.showMessageDialog(null,
                        "Starting Decryption...... ","Message",
                    JOptionPane.INFORMATION_MESSAGE);
        
                    decryptFile.decrypt(directoryPath,  
                        "C:\\Users\\Huzaifa\\Documents\\NetBeansProjects\\OOP_client\\" + encryptedFile,Skey);  
                    //System.out.println("Encryption completed..."); 
                    JOptionPane.showMessageDialog(null,
                        "Decyption completed! ","Message",
                    JOptionPane.INFORMATION_MESSAGE);
                    //DecryptFile.Decrypt(skey);
                    /*JOptionPane.showMessageDialog(null,
                        "The file path: C:\\Users\\Huzaifa\\Documents\\NetBeansProjects\\OOP_client\\" + encryptedFile ,"Message",
                    JOptionPane.INFORMATION_MESSAGE); */
                        /*} catch (IOException ex1) {
                        Logger.getLogger(GetFileName.class.getName()).log(Level.SEVERE, null, ex1);
                    }*/
            try{    
                File file = new File ("C:\\Users\\Huzaifa\\Documents\\NetBeansProjects\\OOP_client\\" + encryptedFile);
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            }catch(IOException ex1){
                Logger.getLogger(GetFileName.class.getName()).log(Level.SEVERE, null, ex1);
            }    
            }
        });
    } 
}    