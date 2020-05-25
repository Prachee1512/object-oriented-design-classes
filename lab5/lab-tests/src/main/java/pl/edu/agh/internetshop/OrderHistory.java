package pl.edu.agh.internetshop;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderHistory {
    private final List<Order> orderList = new LinkedList<>();

    public void addOrder(Order o){
        this.orderList.add(o);
    }

    public List<Order> getOrders(){
        return orderList;
    }

    public List<Order> search(CompositeSearchStrategy searchStrategy){
        return orderList.stream()
                .filter(searchStrategy::filter)
                .collect(Collectors.toList());
    }
}
