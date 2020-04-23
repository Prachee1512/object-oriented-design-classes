package pl.edu.agh.dronka.shop.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class ItemBook extends Item {
    private int pageNumber;
    private boolean hardcover;

    public ItemBook(String name, Category category, int price, int quantity, int pages, boolean cover){
        super(name, category, price, quantity);
        this.pageNumber = pages;
        this.hardcover = cover;
        this.registerHooks();
    }

    public ItemBook(){
        super();
        this.registerHooks();
    }

    private void registerHooks(){
        booleanSettersMap.put("Twarda oprawa", this::setHardcover);
        booleanGettersMap.put("Twarda oprawa", this::isHardcover);
    }

    public boolean isHardcover() {
        return hardcover;
    }

    public void setHardcover(boolean hardcover) {
        this.hardcover = hardcover;
    }

    @Override
    public Map<String, Object>  getAdditionalProperties(){
        Map<String, Object> propMap = new LinkedHashMap<>();
        propMap.put("Liczba stron", pageNumber);
        propMap.put("Twarda oprawa", hardcover);
        return propMap;
    }
}
