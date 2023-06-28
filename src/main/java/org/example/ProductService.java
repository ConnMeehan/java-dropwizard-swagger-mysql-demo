package org.example;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDao productDao = new ProductDao();
    private ProductValidator productValidator = new ProductValidator();

    public int createProduct(ProductRequest product) throws FailedToCreateProductException, InvalidProductException {
        try {
            String validation = productValidator.isValidProduct(product);

            if (validation != null) {
                throw new InvalidProductException(validation);
            }

            int id = productDao.createProduct(product);

            if (id == -1) {
                throw new FailedToCreateProductException();
            }

            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToCreateProductException();
        }
    }
    public void updateProduct(int id, ProductRequest product) throws InvalidProductException, ProductDoesNotExistException, FailedToUpdateProductException {
        try {
            String validation = productValidator.isValidProduct(product);

            if (validation != null) {
                throw new InvalidProductException(validation);
            }

            Product productToUpdate = productDao.getProductById(id);

            if (productToUpdate == null) {
                throw new ProductDoesNotExistException();
            }

            productDao.updateProduct(id, product);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateProductException();
        }
    }
    public List<Product> getAllProducts() throws FailedToGetProductsException {
        List<Product> productList = null;
        try {
            productList = productDao.getAllProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // List<Product> cheapProducts = productList.stream().filter(product -> product.getPrice() < 10).collect(Collectors.toList());
        // cheapProducts.forEach(System.out::println);

        try {
            Product product = productList.get(10000);
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetProductsException();
        }

        System.out.println(productList);

        return productList;
    }
    public Product getProductById(int id) throws FailedToGetProductsException, ProductDoesNotExistException {
        try {
            Product product = productDao.getProductById(id);

            if (product == null) {
                throw new ProductDoesNotExistException();
            }
            return product;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetProductsException();
        }
    }

}

//        Product product1 = new Product(1, "Microwave", "A good microwave", 15.99);
//        Product product2 = new Product(2, "Hairbrush", "A decent hairbrush", 7.99);
//        Product product3 = new Product(3, "Kennel", "A great kennel for your dog", 44.50);
//
//        productList.add(product1);
//        productList.add(product2);
//        productList.add(product3);