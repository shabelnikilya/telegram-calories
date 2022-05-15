package org.example.service;

import org.example.models.Product;
import org.example.repository.ProductRepository;
import org.example.repository.Store;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ProductService implements Service<Product> {
    private final Store<Product> repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Product findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    public void update(Product product) {
        repository.update(product);
    }

    @Override
    public void remove(Product product) {
        repository.remove(product);
    }
}
