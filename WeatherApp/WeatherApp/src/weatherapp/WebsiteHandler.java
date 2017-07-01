/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author iyerg0445
 */
public class WebsiteHandler {
    StringBuilder info;
    URL url;
    URLConnection urlConnection;
    static BufferedReader websiteReader;
    
    public WebsiteHandler(String websiteLink){
        try{
            
        
            //making the link into a url object
            url = new URL(websiteLink);
            //making a urlconnection object used to reference the link
            urlConnection = url.openConnection();

            //using a buffered reader to efficiently read data
            websiteReader = new BufferedReader
                    (new InputStreamReader(urlConnection.getInputStream()));

            info = new StringBuilder();
            int [] data = new int [7];


            String line;

            //reading the lines from the link
            while((line = websiteReader.readLine()) != null){

                info.append(line).append("\n");

            }
            websiteReader.close();
            
        }catch(Exception e){
            
        }
        
        
        
    }
   
    
    
    public int [] getWeeklyForecast() throws IOException{
        
        ArrayList<String> data = new ArrayList<String>();
        String line;
        String pattern = "<div class=\"chart-daily-temp seven_"
                + "days_metric seven_days_metric_c\">";
        
        
        Scanner s = new Scanner(info.toString());
        
        while(s.hasNext()){
            line = s.nextLine();
            if(line.contains(pattern)){
                data.add(line);
            }
        }
        
        int[] weeklyForecast = new int[7];

        String temp;

        for(int i = 0; i < 7; i++){
            
            Scanner w = new Scanner(data.get(i));
            
            w.findInLine(pattern);
            temp = w.next();
            
            weeklyForecast[i] = Integer.parseInt(temp.replaceAll("[^0-9]", ""));
            
            w.nextLine();

        }

        return weeklyForecast;
    }
    
    public String [] getDaysOfTheWeek(){
        ArrayList<String> data = new ArrayList<String>();
        String line;
        String pattern = "</span><span>";
        String temp = "";
        String [] daysOfTheWeek = new String[7];
        
        Scanner s = new Scanner(info.toString());
        
        while(s.hasNext()){
            
            line = s.nextLine();
            
            if(line.contains(pattern)){
                data.add(line);
            }
        }
        
        for(int i = 0; i < 7; i ++){
            Scanner w = new Scanner(data.get(i));
            w.findInLine(pattern);
         
            temp = w.next() + " " + w.next().replaceAll("[^0-9]", "");
            daysOfTheWeek[i] = temp;
            
        }
        
        return daysOfTheWeek;
        
        
    }
    
    
    public String getCountryCode(java.awt.event.ActionEvent evt){
        Scanner s = new Scanner(info.toString());
        String countryCode = "";
        while(s.hasNext()){
            if(s.nextLine().contains("<td>" + evt.getActionCommand() + "</td>")){
                countryCode = s.nextLine().replaceAll("<td>.", "").replaceAll("</td>", "");
            }
        }
        
        return countryCode;
    }
    
    
    public String returnWebsiteLink(String p1, String p2, String p3){
        return"";
    }
    
    private void printArrayList(ArrayList x){
        for(int i = 0; i < x.size(); i ++){
            System.out.println(x.get(i));
        }
    }
    
    private void printStringArray(String [] x){
        for(int i = 0; i < x.length; i ++){
            System.out.println(x[i]);
        }
    }
    
    private void printIntArray(int [] x){
        for(int i = 0; i < x.length; i ++){
            System.out.println(x[i]);
        }
    }
}
