package javaapplication1;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.utils.MyPictureManager;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.converter.HtmlDocumentFacade;
import org.apache.poi.hwpf.model.StyleSheet;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.w3c.dom.Document;

public class WordToHtml {

    public WordToHtml() {

    }

    public void convertWord() {
        try {
            //HWPFDocumentCore wordDocument = WordToHtmlUtils.loadDoc(new FileInputStream("D:\\input.doc"));
            //InputStream input = new FileInputStream (path + file);
            HWPFDocument wordDocument = new HWPFDocument(new FileInputStream("D:\\input.doc"));
            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                    DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .newDocument());
            
            MyPictureManager pictureManager = new MyPictureManager();
            wordToHtmlConverter.setPicturesManager(pictureManager);
            
            wordToHtmlConverter.processDocument(wordDocument);
            List<?> pics = wordDocument.getPicturesTable().getAllPictures();
            if (pics != null) {
                for (int i = 0; i < pics.size(); i++) {
                    Picture pic = (Picture) pics.get(i);
                    try {
                        pic.writeImageContent(new FileOutputStream("D:\\" + pic.suggestFullFileName()));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            StyleSheet style = wordDocument.getStyleSheet();
            
            Document htmlDocument = wordToHtmlConverter.getDocument();
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DOMSource domSource = new DOMSource(htmlDocument);
            
            StreamResult streamResult = new StreamResult(out);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
            out.close();
            ///////////////////////////////////////
            File file = new File("D:\\result.html");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            String result = new String(out.toByteArray());
            
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(result);
            bw.close();

            System.out.println("Done");
            //Files.write("",out);
            //System.out.println(result);

        } catch (TransformerException ex) {
            Logger.getLogger(WordToHtml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WordToHtml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(WordToHtml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static HWPFDocument replaceText(HWPFDocument doc, String findText, String replaceText) {
        Range r1 = doc.getRange();

        for (int i = 0; i < r1.numSections(); ++i) {
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
                    if (text.contains(findText)) {
                        run.replaceText(findText, replaceText);
                    }
                }
            }
        }
        return doc;
    }

    private static void saveWord(String filePath, HWPFDocument doc) throws FileNotFoundException, IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            doc.write(out);
        } finally {
            out.close();
        }
    }
}
