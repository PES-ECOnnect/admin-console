package ECOnnect.API;

import java.util.TreeMap;

import ECOnnect.API.Exceptions.ApiException;

public class ProductTypesService extends Service {
    
    // Only allow instantiating from ServiceFactory
    ProductTypesService() {}
    
    public static class ProductType {
        // Important: The name of these attributes must match the ones in the returned JSON
        public final String name;
        public final String[] questions;
        
        public ProductType(String name, String[] questions) {
            this.name = name;
            this.questions = questions;
        }
    }
    
    private static class QuestionsStruct {
        @SuppressWarnings("unused")
        public final String[] questions;
        public QuestionsStruct(String[] questions) {
            this.questions = questions;
        }
    }
    
    // Get all product types
    public ProductType[] getProductTypes() {
        // Call API
        super.needsToken = true;
        JsonResult result = get(ApiConstants.TYPES_PATH, null);
        
        // Parse result
        ProductType[] productTypes = result.getArray(ApiConstants.RET_RESULT, ProductType[].class);
        if (productTypes == null) {
            // This should never happen, the API should always return an array or an error
            throwInvalidResponseError(result, ApiConstants.RET_RESULT);
        }
        
        // Trim spaces in questions
        for (ProductType productType : productTypes) {
            for (int i = 0; i < productType.questions.length; i++) {
                productType.questions[i] = productType.questions[i].trim();
            }
        }
        
        return productTypes;
    }
    
    // Create a new product type
    public void createProductType(String name, String[] questions) {
        // Add parameters
        TreeMap<String, String> params = new TreeMap<>();
        params.put(ApiConstants.PRODUCT_TYPES_NAME, name);
        
        JsonResult result = null;
        try {
            // Call API
            super.needsToken = true;
            result = post(ApiConstants.TYPES_PATH, params, new QuestionsStruct(questions));
        }
        catch (ApiException e) {
            switch (e.getErrorCode()) {
                case ApiConstants.ERROR_TYPE_EXISTS:
                    throw new RuntimeException("There is already a product type with this name");
                default:
                    throw e;
            }
        }
        
        // Parse result
        String status = result.getAttribute(ApiConstants.RET_STATUS);
        if (status == null || !status.equals(ApiConstants.STATUS_OK)) {
            // This should never happen, the API should always return an array or an error
            throwInvalidResponseError(result, ApiConstants.RET_STATUS);
        }
    }
    
    // Update an existing product type
    // TODO
    
    // Delete an existing product type
    public void deleteProductType(String name) {
        JsonResult result = null;
        try {
            // Call API
            super.needsToken = true;
            result = delete(ApiConstants.TYPES_PATH + "/" + name, null);
        }
        catch (ApiException e) {
            switch (e.getErrorCode()) {
                case ApiConstants.ERROR_TYPE_NOT_EXISTS:
                    throw new RuntimeException("The product type " + name + " does not exist");
                default:
                    throw e;
            }
        }
        
        // Parse result
        String status = result.getAttribute(ApiConstants.RET_STATUS);
        if (status == null || !status.equals(ApiConstants.STATUS_OK)) {
            // This should never happen, the API should always return an ok status or an error
            throwInvalidResponseError(result, ApiConstants.RET_STATUS);
        }
    }
}
