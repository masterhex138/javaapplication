/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.utils;

import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.w3c.dom.Document;

/**
 *
 * @author NMoldabe
 */
public class MyWordConverter extends WordToHtmlConverter {

    public MyWordConverter(Document document) {
        super(document);
    }
    
    
}
