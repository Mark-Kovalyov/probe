public List<Product> getProductByCategory(String category, List<Order> orders) {
        List<Product> products = new ArrayList<>();
        for(Order order : orders) {
            for(Product product : order.getProducts()) {
                if (category.equals(product.getCategory())) {
                    products.add(product);
                }
            }
        }
        return products;
    }
