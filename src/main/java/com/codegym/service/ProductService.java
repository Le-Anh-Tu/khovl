package com.codegym.service;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService{
    private static final List<Product> products = new ArrayList<>();
     static {
        products.add( new Product(1, "Samsung1", "001", "10000"));
         products.add( new Product(2, "Samsung2", "012", "15000"));
         products.add( new Product(3, "Samsung3", "123", "20000"));

    }
    @Override
    public List findAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }


    @Override
    public List<Product> findByName(String name) {
        List<Product> newProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().contains(name)) {
                newProducts.add(product);
            }
        }
        return newProducts;
    }

    @Override
    public void edit(int id, Product product) {
        int index = products.indexOf(findById(id));
        products.set(index, product);

    }
}
