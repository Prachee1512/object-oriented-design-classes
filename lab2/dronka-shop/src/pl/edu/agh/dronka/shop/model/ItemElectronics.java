package pl.edu.agh.dronka.shop.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class ItemElectronics extends Item {
    private boolean mobile;
    private boolean warranty;

    public ItemElectronics(String name, Category category, int price, int quantity, boolean mobile, boolean warranty){
        super(name, category, price, quantity);
        this.mobile = mobile;
        this.warranty = warranty;
        this.registerHooks();
    }

    public ItemElectronics(){
        super();
        this.registerHooks();
    }

    private void registerHooks(){
        booleanSettersMap.put("Gwarancja", this::setWarranty);
        booleanGettersMap.put("Gwarancja", this::isWarranty);

        booleanSettersMap.put("Mobilny", this::setMobile);
        booleanGettersMap.put("Mobilny", this::isMobile);
    }

    public boolean isWarranty() {
        return warranty;
    }

    public void setWarranty(boolean warranty) {
        this.warranty = warranty;
    }

    public boolean isMobile() {
        return mobile;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }

    @Override
    public Map<String, Object>  getAdditionalProperties(){
        Map<String, Object> propMap = new LinkedHashMap<>();
        propMap.put("Mobilny", mobile);
        propMap.put("Gwarancja", warranty);
        return propMap;
    }
}
