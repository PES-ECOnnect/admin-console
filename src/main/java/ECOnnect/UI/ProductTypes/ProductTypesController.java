package ECOnnect.UI.ProductTypes;

import java.awt.event.*;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.*;
import ECOnnect.UI.ProductTypes.Create.CreateProductTypeScreen;
import ECOnnect.API.ProductTypesService.ProductType;

public class ProductTypesController implements Controller {
    private ProductTypesView _view = new ProductTypesView(this);
    private ProductTypesModel _model = new ProductTypesModel();
    
    ActionListener addButton() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ScreenManager.getInstance().show(CreateProductTypeScreen.class);
            }
        };
    }

    ActionListener viewButton(){
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: add to model
                //view.viewInfo();
                //ScreenManager.getInstance().show(ScreenManager.PRODUCT_SCREEN);
            }
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
        System.out.println("View questions for type " + index);
    }
    
    public void viewProducts(int index) {
        System.out.println("View products for type " + index);
    }

    
    public View getView() {
        return _view;
    }
}
