package pl.edu.agh.dronka.shop.model;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class ItemFood extends Item {
    private Date expiryDate;

    public ItemFood(String name, Category category, int price, int quantity, Date expiryDate){
        super(name, category, price, quantity);
        this.expiryDate = expiryDate;
    }

    public ItemFood(){
        super();
    }

    @Override
    public Map<String, Object>  getAdditionalProperties(){
        Map<String, Object> propMap = new LinkedHashMap<>();
        propMap.put("Data przydatności do spożycia", expiryDate);
        return propMap;
    }
}
