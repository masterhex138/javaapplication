/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.utils.MyWordToHtml;



/**
 *
 * @author NMoldabe
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Docxj docxj = new Docxj();
            //docxj.doStuff();
            //HWPFTest.doStuff();
            //AnotherPOI.doStuff("$FIO", "YOOOOOOOOO");
            //RTFTester.doStuff();
            //PDFTester.doStuff();
            //WordToHtml wth = new WordToHtml();
            //wth.convertWord();
            
            MyWordToHtml.convert("D:/", "input.doc");
            /*int[] a = {1,2,3,4};
            for (int c: a){
            System.out.println(""+c);
            }*/
        } catch (Throwable ex) {
            Logger.getLogger(JavaApplication1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    
   
    
}
