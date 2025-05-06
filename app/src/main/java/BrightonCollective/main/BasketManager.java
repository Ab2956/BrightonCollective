package BrightonCollective.main;

import java.util.ArrayList;
import java.util.List;

public class BasketManager {
    // simple basket manager class to be able to buy items
    private List<BasketItem> basketItems;
    private static BasketManager instance;

    private BasketManager(){
        basketItems = new ArrayList<>();
    }

    public static BasketManager getInstance(){
        if (instance == null){
            instance = new BasketManager();
        }
        return instance;
    }

    public void clearBasket(){
        basketItems.clear();
    }

    public void addBasketItem(BasketItem item){
        basketItems.add(item);
    }

    public List<BasketItem> getBasketItems(){
        return basketItems;
    }
}
