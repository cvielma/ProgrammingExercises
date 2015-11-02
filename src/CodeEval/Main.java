package CodeEval;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package codeeval;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *  You are given several strings that may/may not be valid emails. 
 * You should write a regular expression that determines if the email id 
 * is a valid email id or not. You may assume all characters are from the 
 * english language. 
 * @author Christian Vielma <cvielma@librethinking.com>
 */
public class Main {
    private static Pattern pattern = Pattern.compile("(\".*\"|[0-9a-z]+[[-\\.\\+]?[0-9a-z]]*)@[0-9a-z]+[[-\\.][0-9a-z]+]*\\.[a-z][a-z][a-z]?");
    
    public static boolean checkEmail(String email){
        email = email.toLowerCase();        
        return pattern.matcher(email).matches();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
        String path = "";
        boolean test = false;
        if(test){ 
            path = "/home/cvielma/Documents/Development/ToGoogle/ToGoogle/src/codeeval/tests/testEmail.txt";;
        }
        else {path=args[0];}
        Scanner sc = new Scanner(new File(path));
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            //System.out.println(line+"="+checkEmail(line));
            System.out.println(checkEmail(line));
        }
    }
    
}