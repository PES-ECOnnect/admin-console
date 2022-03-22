package ECOnnect.UI.ProductTypes;

import java.awt.event.*;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.*;
import ECOnnect.UI.ProductTypes.Create.CreateProductTypeScreen;
import ECOnnect.UI.ProductTypes.Questions.ProductTypeQuestionsScreen;
import ECOnnect.API.ProductTypesService.ProductType;

public class ProductTypesController implements Controller {
    private final ProductTypesView _view = new ProductTypesView(this);
    private final ProductTypesModel _model = new ProductTypesModel();
    
    ActionListener addButton() {
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(CreateProductTypeScreen.class);
        };
    }
    
    ProductTypeItem[] getProductTypeItems() {
        
        // Get types from model
        ProductType[] items = null;
        try {
            items = _model.getProductTypes();
        }
        catch (Exception e) {
            _view.displayError("Could not fetch existing types: " + e.getMessage());
            return new ProductTypeItem[0];
        }
        
        // Convert to type items
        
        ProductTypeItem[] productTypeItems = new ProductTypeItem[items.length];
        
        for (int i = 0; i < items.length; ++i) {
            productTypeItems[i] = new ProductTypeItem(this, i, items[i].getName(), items[i].getQuestions().length);
        }
        
        return productTypeItems;
    }
    
    public void viewQuestions(int index) {
        _model.setSelectedType(index);
        ScreenManager.getInstance().show(ProductTypeQuestionsScreen.class);
    }
    
    public void viewProducts(int index) {
        _model.setSelectedType(index);
        ScreenManager.getInstance().show(ScreenManager.PRODUCT_SCREEN);
    }

    
    public View getView() {
        return _view;
    }
}
