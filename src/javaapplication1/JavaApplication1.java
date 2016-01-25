/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author NMoldabe
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws java.text.ParseException {
        String dbDate = "2016-01-11";
        String year;
        String month;
        String day;
        Pattern pattern = Pattern.compile("\\d{3,4}");
        Matcher matcher = pattern.matcher(dbDate);
        if (matcher.find()){
            year = matcher.group(0);
        }
        pattern = Pattern.compile("\\-\\d+\\-");
        matcher = pattern.matcher(dbDate);
        if (matcher.find()){
            month = matcher.group(0).replaceAll("-", "");
            System.out.println(month);
        }
        pattern = Pattern.compile("\\d+$");
        matcher = pattern.matcher(dbDate);
        if (matcher.find()){
            day = matcher.group(0);
            System.out.println(day);
        }
        
    }

}
