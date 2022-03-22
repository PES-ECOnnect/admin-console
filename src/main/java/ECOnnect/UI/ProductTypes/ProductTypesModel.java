package ECOnnect.UI.ProductTypes;

import java.util.ArrayList;
import java.util.Arrays;

import ECOnnect.API.ProductTypesService;
import ECOnnect.API.ServiceFactory;
import ECOnnect.API.ProductTypesService.ProductType;

public class ProductTypesModel {
    
    private static ArrayList<ProductType> _productTypes = null;
    private static int _selectedIndex = -1;

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
    
    public void addProductType(String name, String[] questions) {
        // Add product type to API
        ProductTypesService service = ServiceFactory.getInstance().getProductTypesService();
        service.createProductType(name, questions);
    }
    
    void setSelectedType(int index) {
        if (index < 0 || index >= _productTypes.size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        _selectedIndex = index;
    }
    
    ProductType getSelectedType() {
        if (_selectedIndex == -1) {
            throw new IllegalStateException("No product type selected");
        }
        return _productTypes.get(_selectedIndex);
    }
}
