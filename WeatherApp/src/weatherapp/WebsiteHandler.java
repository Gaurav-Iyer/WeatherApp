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
        //DATA WILL HOLD ALL THE LINES THAT CONTAIN DAYS OF THE WEEK
        ArrayList<String> data = new ArrayList<String>();
        //THE CODE THAT PRECEDES THE VALUE WE ARE SEARCHIGN FOR
        String pattern = "<div class=\"chart-daily-temp seven_"
                + "days_metric seven_days_metric_c\">";
       
        
        String line;

        //SCANNING THROUGH THE WEBSITE SOURCE CODE
        Scanner s = new Scanner(info.toString());
        
        //SIMPLIFYING OUR SEARCH TO ONLY LINES CONTAINING THE VALUES WE WANT
        while(s.hasNext()){
            line = s.nextLine();
            if(line.contains(pattern)){
                //ADDING LINES THAT CONTAIN THE VALUE WE WANT
                data.add(line);
            }
        }
        
        int[] weeklyForecast = new int[7];

      
        for(int i = 0; i < 7; i++){
            
            Scanner w = new Scanner(data.get(i));
            
            w.findInLine(pattern);  //MOVING THE SCANNER TO THE PATTERN IN THE LINE
            
            //REPLACING NON INTEGER VALUES IN THE LINE WITH BLANKSPACE
            weeklyForecast[i] = Integer.parseInt(w.next().replaceAll("[^0-9]", ""));
            
            //MOVING TO THE NEXT LINE
            w.nextLine();

        }

        return weeklyForecast;
    }
    
    
    
    //GETTING THE DAYS OF THE WEEK THAT TEMPERATURES ARE GOTTEN FOR
    public String [] getDaysOfTheWeek(){
        //VARIABLE DECLARATIONS
        ArrayList<String> data = new ArrayList<>();
        String pattern = "</span><span>";
        String line;
        String [] daysOfTheWeek = new String[7];
        
        Scanner s = new Scanner(info.toString());
        
        //ADDING LINES CONTAINING DAYS OF THE WEEK
        while(s.hasNext()){
            
            line = s.nextLine();
            if(line.contains(pattern)){
                data.add(line);
            }
        }
        //GETTING DAYS OF THE WEEK
        for(int i = 0; i < 7; i ++){
            
            Scanner w = new Scanner(data.get(i));
            w.findInLine(pattern);  
            daysOfTheWeek[i] = w.next() + " " + w.next().replaceAll("[^0-9]", "");

            
        }
        
        return daysOfTheWeek;
        
        
    }
    
    //GETTING THE COUNTRY DOMAIN EXTENSION USED IN BUILDING THE URL
    public String getCountryCode(String country){
        
        Scanner s = new Scanner(info.toString());
        String countryCode = "";
        
        while(s.hasNext()){
            //ONLY CHECKING THE LINE WITH THE COUNTRY IN QUESTION
            if(s.nextLine().toUpperCase().contains(country.toUpperCase())){
                //LINE IN TOTAL IS "<TD> COUNTRYNAME </TD>"
                //GETTING THE DOMAIN EXTENSION ITSELF
                countryCode = s.nextLine().replaceAll("<td>.", "").replaceAll("</td>", "");
                break;
            }
            
        }
        
        return countryCode;
    }
    
    //GETTING WHAT IT ACTUALLY FEELS LIKE OUTSIDE
    public int [] getFeelsLike(){
        //VARIABLE DECLARATIONS
        ArrayList<String> data = new ArrayList<>();
        String line;
        String pattern = "feels-like seven_days_metric seven_days_metric_c \">";
        
        
        Scanner s = new Scanner(info.toString());
        
        //ADDING LINES CONTAINING TEMPERATURES
        while(s.hasNext()){
            
            line = s.nextLine();
            
            if(line.contains(pattern)){
                data.add(line);
            }
        }
        
        int[] feelsLike = new int[7];

        //GETTING TEMPERATURES
        for(int i = 0; i < 7; i++){
            
            Scanner w = new Scanner(data.get(i));
            
            w.findInLine(pattern);
            
            feelsLike[i] = Integer.parseInt(w.next().replaceAll("[^0-9]", ""));
            
            w.nextLine();

        }

        return feelsLike;
    }
    
    //GETTING THE WEEKLY CHANCE OF RAIN FOR THE WEEK
    public String [] getChanceOfRain(){
        
        ArrayList<String> data = new ArrayList<>();
        String line;
        String pattern = "<div class=\"fx-details \">";
        
        
        Scanner s = new Scanner(info.toString());
        
        while(s.hasNext()){
            
            line = s.nextLine();
            
            if(line.contains(pattern)){
                data.add(line);
            }
        }
        
        String[] chanceOfRain = new String[7];

        String temp;

        for(int i = 0; i < 7; i++){
            
            Scanner w = new Scanner(data.get(i));
            
            w.findInLine(pattern);
            temp = w.next();
            
            chanceOfRain[i] = temp.replaceAll("[^0-9]", "") + "%";
            
            w.nextLine();

        }

        return chanceOfRain;
    }
    
    
    
    
    
    //PRIVATE METHODS FOR PRINTING DIFFERENT FEATURES USED IN TESTING
    private void printArrayList(ArrayList x){
        for(int i = 0; i < x.size(); i ++){
            System.out.println(x.get(i)); //turns out this method isnt useful
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
