
package weatherapp;

import java.net.*;
import java.io.*;
import java.util.Scanner;


public class WeatherApp {

    
    public static void main(String[] args) throws IOException{
        WebsiteHandler w = new WebsiteHandler("https://www.theweathernetwork.com/ca/weather/ontario/waterloo");
        
        
        int [] weeklyForecast = w.getWeeklyForecast();
                
        
        
        String [] daysOfTheWeek = w.getDaysOfTheWeek();
        
        
        for(int i = 0 ; i < weeklyForecast.length; i ++){
            System.out.print(daysOfTheWeek[i] + "\t" );
            System.out.println("-->  " + weeklyForecast[i]);
        }
        
    }
}
