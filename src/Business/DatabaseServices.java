/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author leonardoalvesdemelo
 */
public class DatabaseServices {
    
    private String databaseName = "files/database/malotes.csv";
    
    private ArrayList<String[]> database;
    
    public DatabaseServices() {
        
        
    }
    
    public ArrayList<String[]> getDatabase() {
        //this.readDatabase(databaseName, "");
        return this.database;
    }
    
    
    public void setDatabase(String name, String transportatorName) {
        this.readDatabase(name, transportatorName);
        this.saveDatabase();
    }
    
    private void saveDatabase() {
        
    }
    
    private void readDatabase(String name, String transportatorName) {
        this.database = new ArrayList<String[]>();
        
        BufferedReader br = null;
        String line = "";
        String csvDivisor = ",";
        try {
            
            int index = 0;
            br = new BufferedReader(new FileReader(name));
            while ((line = br.readLine()) != null) {
                
                line +="," + transportatorName;
                
                //System.out.println(line.split(csvDivisor));
                
                if(index >= 4) {
                    System.out.println(line);
                    System.out.println("------------");
                    this.database.add(line.split(csvDivisor));
                }
                
                index+=1;

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
        
}
