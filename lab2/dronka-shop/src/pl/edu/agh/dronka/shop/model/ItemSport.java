package pl.edu.agh.dronka.shop.model;

import java.util.Collections;
import java.util.Map;

public class ItemSport extends Item {
    public ItemSport(String name, Category category, int price, int quantity){
        super(name, category, price, quantity);
    }

    public ItemSport(){
        super();
    }

    @Override
    public Map<String, Object> getAdditionalProperties() {
        return Collections.emptyMap();
    }
}
