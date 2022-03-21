package ECOnnect.UI.ProductTypes;

import ECOnnect.API.ProductTypesService;
import ECOnnect.API.ServiceFactory;
import ECOnnect.API.ProductTypesService.ProductType;

public class ProductTypesModel {
    
    ProductType[] productTypes = null;

    // Return all product types from the API and store them in the model
    public ProductType[] getProductTypes() {
        ProductTypesService service = ServiceFactory.getInstance().getProductTypesService();
        productTypes = service.getProductTypes();
        
        return productTypes;
    }
    
}
