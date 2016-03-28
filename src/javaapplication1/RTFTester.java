/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author NMoldabe
 */
public class RTFTester {
    public static void doStuff(){
        try {
            String content = IOUtils.toString(new FileInputStream("D:\\TemplateBuilder.rtf"));
            content = content.replaceAll("TAB_NUM", "9003");
            content = content.replaceAll("FULL_NAME", "Молдабеков Нурлан Амиржанович");
            content = content.replaceAll("LOGIN", "NMoldabe");
            content = content.replaceAll("ACCEPT_DATE", "2015-09-21");
            IOUtils.write(content, new FileOutputStream("D:\\result.rtf"));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RTFTester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RTFTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
