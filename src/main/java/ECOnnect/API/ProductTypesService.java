package ECOnnect.API;

import java.util.TreeMap;

import ECOnnect.API.Exceptions.ApiException;

public class ProductTypesService extends Service {
    
    // Only allow instantiating from ServiceFactory
    ProductTypesService() {}
    
    public class ProductType {
        // Important: The name of these attributes must match the ones in the returned JSON
        public final String name;
        public final String[] questions;
        
        public ProductType(String name, String[] questions) {
            this.name = name;
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
    
    private class QuestionsStruct {
        @SuppressWarnings("unused")
        public String[] questions;
        public QuestionsStruct(String[] questions) {
            this.questions = questions;
        }
    }
}
