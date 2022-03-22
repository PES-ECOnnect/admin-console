package ECOnnect.API;

import java.util.TreeMap;

import ECOnnect.API.Exceptions.ApiException;

public class ProductService extends Service {
    
    // Only allow instantiating from ServiceFactory
    ProductService() {}
    
    public class Product {
        // Important: The name of these attributes must match the ones in the returned JSON
        private int id;
        private String name;
        private String manufacturer;
        private String imageUrl;
        private String type;
        
        public Product(int id, String name, String manufacturer, String imageUrl, String type) {
            this.id = id;
            this.name = name;
            this.manufacturer = manufacturer;
            this.imageUrl = imageUrl;
            this.type = type;
        }
        
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String getManufacturer() {
            return manufacturer;
        }
        public String getImageUrl() {
            return imageUrl;
        }
        public String getType() {
            return type;
        }
    }
    
    // Get product of specific type (or all products if type is null)
    public Product[] getProducts(String type) {
        // Add parameters
        TreeMap<String, String> params = new TreeMap<>();
        if (type != null) {
            params.put(ApiConstants.PRODUCT_TYPE, type);
        }
        
        JsonResult result = null;
        try {
            // Call API
            super.needsToken = true;
            result = get(ApiConstants.PRODUCTS_PATH, params);
        }
        catch (ApiException e) {
            switch (e.getErrorCode()) {
                case ApiConstants.ERROR_TYPE_NOT_EXISTS:
                    throw new RuntimeException("The product type " + type + " does not exist");
                default:
                    throw e;
            }
        }
        
        // Parse result
        Product[] products = result.getArray(ApiConstants.RET_PRODUCTS, Product[].class);
        if (products == null) {
            // This should never happen, the API should always return an array or an error
            throwInvalidResponseError(result, ApiConstants.RET_PRODUCTS);
        }
        
        return products;
    }
    
    // Create a new product
    public void createProduct(String name, String manufacturer, String imageUrl, String type) {
        // Add parameters
        TreeMap<String, String> params = new TreeMap<>();
        params.put(ApiConstants.PRODUCT_NAME, name);
        params.put(ApiConstants.PRODUCT_MANUFACTURER, manufacturer);
        params.put(ApiConstants.PRODUCT_IMAGE_URL, imageUrl);
        params.put(ApiConstants.PRODUCT_TYPE, type);
        
        JsonResult result = null;
        try {
            // Call API
            super.needsToken = true;
            result = post(ApiConstants.PRODUCTS_PATH, params, null);
        }
        catch (ApiException e) {
            switch (e.getErrorCode()) {
                case ApiConstants.ERROR_TYPE_NOT_EXISTS:
                    throw new RuntimeException("The product type " + type + " does not exist");
                case ApiConstants.ERROR_PRODUCT_EXISTS:
                    throw new RuntimeException("The product " + name + " already exists");
                default:
                    throw e;
            }
        }
        
        // Parse result
        if (result.size() > 0) {
            // This should never happen, the API should always return an array or an error
            throwInvalidResponseError(result, ApiConstants.RET_PRODUCTS);
        }
    }
}
