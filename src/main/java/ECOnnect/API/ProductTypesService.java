package ECOnnect.API;

import java.util.TreeMap;

import ECOnnect.API.Exceptions.ApiException;

public class ProductTypesService extends Service {
    
    // Only allow instantiating from ServiceFactory
    ProductTypesService() {}
    
    public class ProductType {
        // Important: The name of these attributes must match the ones in the returned JSON
        private String name;
        private String[] questions;
        
        public ProductType(String name, String[] questions) {
            this.name = name;
            this.questions = questions;
        }
        
        public String getName() {
            return name;
        }
        
        public String[] getQuestions() {
            return questions;
        }
    }
    
    // Get all product types
    public ProductType[] getProductTypes() {
        // Call API
        super.needsToken = true;
        JsonResult result = get(ApiConstants.TYPES_PATH, null);
        
        // Parse result
        ProductType[] productTypes = result.getArray(ApiConstants.RET_PRODUCT_TYPES, ProductType[].class);
        if (productTypes == null) {
            // This should never happen, the API should always return an array or an error
            throwInvalidResponseError(result, ApiConstants.RET_PRODUCT_TYPES);
        }
        
        return productTypes;
    }
    
    // Create a new product type
    public void createProductType(String name, String[] questions) {
        // Add parameters
        TreeMap<String, String> params = new TreeMap<>();
        params.put(ApiConstants.PRODUCT_TYPES_NAME, name);
        params.put(ApiConstants.PRODUCT_TYPES_QUESTIONS, gson.toJson(questions));
        
        JsonResult result = null;
        try {
            // Call API
            super.needsToken = true;
            result = post(ApiConstants.TYPES_PATH, params, null);
        }
        catch (ApiException e) {
            switch (e.getErrorCode()) {
                case ApiConstants.ERROR_TYPE_EXISTS:
                    throw new RuntimeException("There is already a product type with this name");
                default:
                    throw e;
            }
        }
        
        if (result.size() != 0) {
            // This should never happen, the API should always return an error or an empty object
            throwInvalidResponseError(result);
        }
    }
}
