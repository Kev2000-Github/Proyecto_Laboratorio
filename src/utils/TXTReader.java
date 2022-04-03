package utils;

import java.util.Vector;
import java.io.File;  
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TXTReader {
    public Vector<String> readTXT(String path){
        Vector<String> txtContent = new Vector<String>();
        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String lineContent = scanner.nextLine();
                txtContent.add(lineContent);
            }
            scanner.close();
        }
        catch(FileNotFoundException err){
            err.printStackTrace();      
        }
        return txtContent;
    }

    //TODO: Implement readPersonas
    public void readPersonasEmpleados(String path){
        Vector<String> fileContent = readTXT(path);
    }

    //TODO: Implement readFundaciones y Servicios
    public void readFundacionesServicios(String path){
        Vector<String> fileContent = readTXT(path);
    }
}
