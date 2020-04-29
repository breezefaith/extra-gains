package ems.util;

import ems.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ResourcesUtil {
    public static List<String> getCoutriesList() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        String line;
        try {
            InputStream inputStream = Main.class.getResourceAsStream("/Countries.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
            bufferedReader.close();
            inputStream.close();
            return list;
        }catch (IOException e){
            throw e;
        }
    }

    public static List<String> getProvincesList(String country) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        String line;
        try {
            InputStream inputStream = Main.class.getResourceAsStream("/Provinces_States.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(",");
                if(strings[0].equals(country)){
                    list.add(strings[1]);
                }
            }
            bufferedReader.close();
            inputStream.close();
            return list;
        }catch (IOException e){
            throw e;
        }
    }

    public static List<String> getCityList(String country, String province) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        String line;
        try {
            InputStream inputStream = Main.class.getResourceAsStream("/Cities.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(",");
                if(strings[0].equals(country) && strings[1].equals(province)){
                    list.add(strings[2]);
                }
            }
            bufferedReader.close();
            inputStream.close();
            return list;
        }catch (IOException e){
            throw e;
        }
    }
}
