import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class fetchData {
    private String ip;
    private String status;
    private String[] locations;
    private String country;
    private String countryCode;
    private String region;
    private String regionName;
    private String city;
    private String zip;
    private String lat;
    private String lon;
    private String timezone;

    private String mainWeather;
    private String description;
    private String mainWeatherIcon;
    private String temp;
    private String feelsTemp;
    private String humidity;



    private final String KEY = "073d98f9cb5238d549bbf35f7bb216c0";
    private String URL;

    fetchData(String ip){

        try{
            String ipJsonData = ip;
            JsonObject jsonObject = JsonParser.parseString(ipJsonData).getAsJsonObject();
            this.ip = jsonObject.get("ip").getAsString();
            String locationJson = getLocationData(this.ip);
            jsonObject = JsonParser.parseString(locationJson).getAsJsonObject();

            this.status = jsonObject.get("status").getAsString();
            this.country = jsonObject.get("country").getAsString();
            this.countryCode = jsonObject.get("countryCode").getAsString();
            this.region = jsonObject.get("region").getAsString();
            this.regionName = jsonObject.get("regionName").getAsString();
            this.city = jsonObject.get("city").getAsString();
            this.zip = jsonObject.get("zip").getAsString();
            this.lat = jsonObject.get("lat").getAsString();
            this.lon = jsonObject.get("lon").getAsString();
            this.timezone = jsonObject.get("timezone").getAsString();

            /*
            System.out.println("Status: " + this.status);
            System.out.println("Country: " + this.country);
            System.out.println("Country Code: " + this.countryCode);
            System.out.println("Region: " + this.region);
            System.out.println("Region Name: " + this.regionName);
            System.out.println("City: " + this.city);
            System.out.println("ZIP Code: " + this.zip);
            System.out.println("Latitude: " + this.lat);
            System.out.println("Longitude: " + this.lon);
            */


            this.URL = "https://api.openweathermap.org/data/2.5/weather?units=metric&lat=" + this.lat + "&lon=" + this.lon + "&lang=" + this.countryCode + "&appid=" + this.KEY;
            JsonObject weatherObject = JsonParser.parseString(getWeatherData()).getAsJsonObject();
            //System.out.println(weatherObject);
            JsonArray weatherArray = weatherObject.getAsJsonArray("weather");
            JsonObject mainTemp = weatherObject.getAsJsonObject("main");
            JsonObject weatherArrayObject = weatherArray.get(0).getAsJsonObject();

            this.mainWeather = weatherArrayObject.get("main").getAsString();
            this.mainWeatherIcon = weatherArrayObject.get("icon").getAsString();
            this.description = weatherArrayObject.get("description").getAsString();
            this.temp = mainTemp.get("temp").getAsString();
            this.feelsTemp = mainTemp.get("feels_like").getAsString();
            this.humidity= mainTemp.get("humidity").getAsString();

            /*
            System.out.println(mainWeather);
            System.out.println(description);
            System.out.println(temp);
            System.out.println(feelsTemp);
            System.out.println(humidity);
            System.out.println(weatherObject);
            System.out.println(weatherArray);
            System.out.println(mainTemp);
            */

        }
        catch (Exception e){
            System.out.println("Bir hata oluştu " + e);
        }

    }

    public Map<String, String > getData(){
        Map<String, String> data = new HashMap<>();

        data.put("country", country);
        data.put("countryCode", countryCode);
        data.put("region", region);
        data.put("regionName", regionName);
        data.put("city", city);
        data.put("mainWeather", mainWeather);
        data.put("description", description);
        data.put("mainWeatherIcon", mainWeatherIcon);
        data.put("temp", temp);
        data.put("feelsTemp", feelsTemp);
        data.put("humidity", humidity);
        return data;
    }
    public String getWeatherData(){
        try {
            // URL'yi oluştur
            URL url = new URL(this.URL);

            // HTTP bağlantısı oluştur
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Yanıtı oku
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // API yanıtını bir metin olarak döndür
            return response.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null; // Hata durumunda null döndürebilirsiniz
        }
    }
    public String getLocationData(String ipAddress) {
        try {
            String url = "http://ip-api.com/json/" + ipAddress;
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return response.toString();
        } catch (IOException e) {
            System.out.println("IP BULUNAMADI");
            return null;
        }
    }


}
