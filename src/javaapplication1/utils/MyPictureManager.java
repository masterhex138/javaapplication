/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.usermodel.PictureType;

/**
 *
 * @author NMoldabe
 */
public class MyPictureManager implements PicturesManager {

    @Override
    public String savePicture(byte[] bytes, PictureType pt, String suggestedName, float f, float f1) {
        System.out.println(suggestedName);
        return "D:\\pics\\"+suggestedName;
    }

}
