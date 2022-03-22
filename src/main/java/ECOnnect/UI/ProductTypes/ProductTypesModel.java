package ECOnnect.UI.ProductTypes;

import java.util.ArrayList;
import java.util.Arrays;

import ECOnnect.API.ProductTypesService;
import ECOnnect.API.ServiceFactory;
import ECOnnect.API.ProductTypesService.ProductType;

public class ProductTypesModel {
    
    ArrayList<ProductType> _productTypes = null;

    // Return all product types from the API and store them in the model
    ProductType[] getProductTypes() {
        // Get product types from API
        ProductTypesService service = ServiceFactory.getInstance().getProductTypesService();
        ProductType[] pt = service.getProductTypes();
        
        // Store in model
        _productTypes = new ArrayList<>(Arrays.asList(pt));
        
        // Return product types
        return pt;
    }
    
    int getProductTypeCount() {
        return _productTypes.size();
    }
    
    void addProductType(String name, String[] questions) {
        // Add product type to API
        ProductTypesService service = ServiceFactory.getInstance().getProductTypesService();
        service.createProductType(name, questions);
    }
}
