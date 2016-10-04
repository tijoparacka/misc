package com.tijo.util;

import java.io.*;
import java.util.HashMap;

/**
 * Created by tijo on 1/10/16.
 */
public class IO {
    HashMap<String,FileWriter> filewriters = new HashMap<String ,FileWriter>();
    public  static String readInput() {
        try {
            StringBuffer sb = new StringBuffer();
            char c ;
            while ( (c = (char)System.in.read()) != '\n' ){
                sb.append(c);
            }
            return  sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String readFile(String filename ) throws IOException {

       BufferedReader r = new BufferedReader(new FileReader(filename));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = r.readLine()) != null) {
            //System.out.println(line);
            sb.append(line+'\n');
        }
        return sb.toString();
    }

    public void writeFile(String fileName,String data) throws IOException {

        FileWriter fw = filewriters.get(fileName);
        if(fw == null ){
            fw = new FileWriter(fileName);
            filewriters.put(fileName,fw);
        }
        fw.write(data);
        fw.flush();
    }
    public void closeFile(String fileName) throws IOException {
        FileWriter fw = filewriters.get(fileName);
        fw.close();
        filewriters.remove(fileName);
    }
}
