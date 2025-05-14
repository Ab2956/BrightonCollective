package BrightonCollective.main;

import java.util.ArrayList;
import java.util.List;

public class ProductList {

    // product list in replacement for a firebase storage system
    private static ProductList instance;
    private final List<Product> productList;

    private ProductList() {
        productList = new ArrayList<>();
        initialList();
    }
    public static ProductList getInstance(){
        if (instance == null){
            instance = new ProductList();
        }
        return instance;
    }
    private void initialList(){
        productList.add(new Product(
                "bucket hat",
                "A bucket hat with a pink ribbon",
                "https://images.unsplash.com/photo-1733814489989-f7b6fa2a44cb?q=80&w=2030&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                19.99
        ));
        productList.add(new Product(
                "gray tracksuit",
                "A gray Tracksuit",
                "https://images.unsplash.com/photo-1715609105250-ee1a91942ee2?q=80&w=2076&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                22.99
        ));
        productList.add(new Product(
                "sunglasses",
                "Rayban's Sunglasses",
                "https://images.unsplash.com/photo-1621331122533-465bdcfa6e01?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8cmF5YmFuJTIwc3VuZ2xhc3Nlc3xlbnwwfHwwfHx8MA%3D%3D",
                79.99
        ));
        productList.add(new Product(
                "t-shirt",
                "Uniqlo Plain White T-Shirt",
                "https://images.unsplash.com/photo-1722310752951-4d459d28c678?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                24.99
        ));
        productList.add(new Product(
                "beanie",
                "A Black Beanie",
                "https://images.unsplash.com/photo-1648483092137-6e63796c8b06?q=80&w=1965&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                12.99
        ));
        productList.add(new Product(
                "blue jeans",
                "Baggy Blue Jeans",
                "https://images.unsplash.com/photo-1624378439575-d8705ad7ae80?q=80&w=1994&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                15.99
        ));
        productList.add(new Product(
                "floral scarf",
                "A red and black printed floral scarf",
                "https://images.unsplash.com/photo-1601244005535-a48d21d951ac?q=80&w=1970&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                7.99
        ));
        productList.add(new Product(
                "black wool scarf",
                "A Black wool Scarf",
                "https://images.unsplash.com/photo-1551381912-4e2e29c7fd17?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                4.99
        ));
        productList.add(new Product(
                "black leather jacket",
                "A black leather Jacket",
                "https://images.unsplash.com/photo-1727515546577-f7d82a47b51d?q=80&w=1936&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                29.99
        ));
        productList.add(new Product(
                "leather belt",
                "Black and Brown-bordered leather belt",
                "https://images.unsplash.com/photo-1664286074240-d7059e004dff?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                11.99
        ));
        productList.add(new Product(
                "swim shorts",
                "brown swim shorts",
                "https://images.unsplash.com/photo-1532788031780-73fddc7e94fe?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fHN3aW0lMjBzaG9ydHN8ZW58MHx8MHx8fDI%3D",
                9.99
        ));
        productList.add(new Product(
                "sliders",
                "Black Sliders with White stripes",
                "https://images.unsplash.com/photo-1651913506606-2cac8f5e69a9?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fHNsaWRlcnMlMjBzbGlwcGVyc3xlbnwwfHwwfHx8Mg%3D%3D",
                3.99
        )); productList.add(new Product("Vintage Shirt", "Classic old shirt", "https://images.unsplash.com/photo-1596755094514-f87e34085b2c?q=80&w=1976&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 25.00));
        productList.add(new Product("Leather Jacket", "Stylish winter jacket", "https://images.unsplash.com/photo-1551028719-00167b16eac5?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 58.00));
        productList.add(new Product("Nike Shoes", "Yellow Nike Shoes", "https://images.unsplash.com/photo-1628253747716-0c4f5c90fdda?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDZ8fE5pa2UlMjBTaG9lc3xlbnwwfHwwfHx8Mg%3D%3D", 39.99));
        productList.add(new Product("Black Socks", "Cool black Socks", "https://images.unsplash.com/photo-1640026199235-c24aa417b552?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTd8fFNvY2tzJTIwZm9yJTIwc2FsZXxlbnwwfHwwfHx8Mg%3D%3D", 10.50));
        productList.add(new Product("Omega Watch", "Lovely Omega watch", "https://images.unsplash.com/photo-1523170335258-f5ed11844a49?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8d2F0Y2h8ZW58MHx8MHx8fDI%3D", 2000.00));
        productList.add(new Product("Fluffy Coat", "Blue Long Fluffy Coat", "https://images.unsplash.com/photo-1608236159447-2d27116777f3?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8Q29hdCUyMGZvciUyMHNhbGV8ZW58MHx8MHx8fDI%3D", 190.00));


    }
    public List<Product> getProducts(){
        return productList;
    }
}
