package pl.edu.agh.dronka.shop.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ItemFactory {
    public static Item getItem(Category category, Map<String, String> params){
        if(category == null){
            return null;
        }

        String name = params.get("Nazwa");
        int price = toInt(params.get("Cena"));
        int quantity = toInt(params.get("Ilość"));

        if(category == Category.BOOKS){
            int pages = toInt(params.get("Liczba stron"));
            boolean cover = toBoolean(params.get("Twarda oprawa"));

            return new ItemBook(name, category, price, quantity, pages, cover);
        }
        if(category == Category.ELECTRONICS){
            boolean mobile = toBoolean(params.get("Mobilny"));
            boolean warranty = toBoolean(params.get("Gwarancja"));

            return new ItemElectronics(name, category, price, quantity, mobile, warranty);
        }
        if(category == Category.FOOD){
            Date expiryDate = toDate(params.get("Data przydatności do spożycia"));

            return new ItemFood(name, category, price, quantity, expiryDate);
        }
        if(category == Category.MUSIC){
            boolean video = toBoolean(params.get("Wideo"));
            MusicGenre genre = MusicGenre.valueOf(params.get("Gatunek").toUpperCase());

            return new ItemMusic(name, category, price, quantity, video, genre);
        }
        if(category == Category.SPORT){
            return new ItemSport(name, category, price, quantity);
        }

        return null;
    }

    public static Item getItem(Category category){
        if(category == null){
            return new ItemBook();
        }

        if(category == Category.BOOKS){
            return new ItemBook();
        }
        if(category == Category.ELECTRONICS){
            return new ItemElectronics();
        }
        if(category == Category.FOOD){
            return new ItemFood();
        }
        if(category == Category.MUSIC){
            return new ItemMusic();
        }
        if(category == Category.SPORT){
            return new ItemSport();
        }

        return null;
    }

    public static int toInt(String s){
        return Integer.parseInt(s);
    }

    public static boolean toBoolean(String s){
        return Boolean.parseBoolean(s);
    }

    public static Date toDate(String s){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date parsedDate;
        try{
            parsedDate = formatter.parse(s);
        }catch (ParseException e) {
            e.printStackTrace();
            parsedDate = new Date();
        }
        return parsedDate;
    }
}