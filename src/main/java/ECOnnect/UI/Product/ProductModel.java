package ECOnnect.UI.Product;

import ECOnnect.API.ProductService;
import ECOnnect.API.ServiceFactory;
import ECOnnect.API.ProductService.Product;

public class ProductModel {
        
    // Get products of a product type
    Product[] getProducts(String productType) {
        // Get products from API
        ProductService service = ServiceFactory.getInstance().getProductService();
        Product[] p = service.getProducts(productType);
        
        // No need to store in model
        
        return p;
    }
    
    // Get products of all product types
    Product[] getProducts() {
        return getProducts(null);
    }
    
    public void addProduct(String name, String manufacturer, String imageUrl, String type) {
        // Trim whitespace
        name = name.trim();
        manufacturer = manufacturer.trim();
        imageUrl = imageUrl.trim();
        type = type.trim();
        
        // There might be more aspects to validate
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (manufacturer.isEmpty()) {
            throw new IllegalArgumentException("Manufacturer cannot be empty");
        }
        if (imageUrl.isEmpty()) {
            throw new IllegalArgumentException("Image URL cannot be empty");
        }
        if (type.isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty");
        }
        
        
        // Add product type to API
        ProductService service = ServiceFactory.getInstance().getProductService();
        service.createProduct(name, manufacturer, imageUrl, type);
    }
}
