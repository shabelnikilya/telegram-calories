package org.example.bots.send;

import org.example.models.Product;

import java.util.List;

public class ListNameProductAnswer implements SendAnswer {
    private final List<Product> products;

    public ListNameProductAnswer(List<Product> products) {
        this.products = products;
    }

    @Override
    public String getAnswer() {
        StringBuilder str = new StringBuilder();
        products.forEach(product -> str.append(product.getName()).append(", "));
        return str.substring(0, str.length() - 2);
    }
}
