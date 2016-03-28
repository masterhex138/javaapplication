/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 *
 * @author NMoldabe
 */
public class AnotherPOI {
    public static void doStuff(){
        
    }
    public static void replaceText(String findText,String replaceText){
        try {
            XWPFDocument doc = new XWPFDocument(OPCPackage.open("D:\\template.docx"));
            for (XWPFParagraph p : doc.getParagraphs()) {
                
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        if (text != null && text.contains(findText)) {
                            text = text.replace(findText, replaceText);
                            r.setText(text, 0);
                        }
                    }
                }
            }
            for (XWPFTable tbl : doc.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            for (XWPFRun r : p.getRuns()) {
                                String text = r.getText(0);
                                if (text.contains(findText)) {
                                    text = text.replace(findText, replaceText);
                                    r.setText(text);
                                }
                            }
                        }
                    }
                }
            }
            doc.write(new FileOutputStream("D:\\result.docx"));
        } catch (IOException ex) {
            Logger.getLogger(AnotherPOI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(AnotherPOI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
