package pl.edu.agh.dronka.shop.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class ItemMusic extends Item {
    private boolean video;
    private MusicGenre genre;

    public ItemMusic(String name, Category category, int price, int quantity, boolean video, MusicGenre genre){
        super(name, category, price, quantity);
        this.video = video;
        this.genre = genre;
        this.registerHooks();
    }

    public ItemMusic(){
        super();
        this.registerHooks();
    }

    private void registerHooks(){
        booleanSettersMap.put("Wideo", this::setVideo);
        booleanGettersMap.put("Wideo", this::isVideo);
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    @Override
    public Map<String, Object>  getAdditionalProperties(){
        Map<String, Object> propMap = new LinkedHashMap<>();
        propMap.put("Wideo", video);
        propMap.put("Gatunek", (genre == null) ? "Brak" : genre.getDisplayName());
        return propMap;
    }
}
