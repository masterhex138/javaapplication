/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class HWPFTest {
    public static void doStuff(){
        String filePath = "D:\\insiders_report4.doc";
        POIFSFileSystem fs = null;        
        try {            
            fs = new POIFSFileSystem(new FileInputStream(filePath));            
            HWPFDocument doc = new HWPFDocument(fs);
            doc = replaceText(doc, "#FIO#", "Молдабеков Нурлан Амиржанович");
            doc = replaceText(doc, "#BIN#", "900524300077");
            doc = replaceText(doc, "#INCDAY#", "05");
            doc = replaceText(doc, "#INCMONTH#", "марта");
            doc = replaceText(doc, "#INCYEAR#", "2016");
            doc = replaceText(doc, "#EXCDAY#", "05");
            doc = replaceText(doc, "#EXCMONTH#", "марта");
            doc = replaceText(doc, "#EXCYEAR#", "2016");
            doc = replaceText(doc, "#MAINCHIEF#", "Эгглтон Майкл Джеймс");
            saveWord("D:\\result.doc", doc);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private static HWPFDocument replaceText(HWPFDocument doc, String findText, String replaceText){
        Range r1 = doc.getRange(); 

        for (int i = 0; i < r1.numSections(); ++i ) { 
            Section s = r1.getSection(i); 
            for (int x = 0; x < s.numParagraphs(); x++) { 
                Paragraph p = s.getParagraph(x); 
                /*String text = p.text();
                if(text.contains(findText)) {
                    p.replaceText(replaceText, findText);
                }*/
                
                for (int z = 0; z < p.numCharacterRuns(); z++) { 
                    CharacterRun run = p.getCharacterRun(z); 
                    String text = run.text();
                    if(text.contains(findText)) {
                        run.replaceText(findText, replaceText);
                    } 
                }
            }
        } 
        return doc;
    }

    private static void saveWord(String filePath, HWPFDocument doc) throws FileNotFoundException, IOException{
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(filePath);
            doc.write(out);
        }
        finally{
            out.close();
        }
    }
}