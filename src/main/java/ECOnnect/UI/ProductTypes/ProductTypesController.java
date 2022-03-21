package ECOnnect.UI.ProductTypes;

import java.awt.event.*;

import ECOnnect.UI.Interfaces.*;
import ECOnnect.API.ProductTypesService.ProductType;

public class ProductTypesController implements Controller {
    private ProductTypesView view = new ProductTypesView(this);
    private ProductTypesModel model = new ProductTypesModel();
    
    ActionListener addButton() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductTypeItem item = new ProductTypeItem("New product type", 0);
                addListeners(item);
                view.addItem(item);
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
            items = model.getProductTypes();
        }
        catch (Exception e) {
            view.displayError("Could not fetch existing types: " + e.getMessage());
        }
        
        // Convert to type items
        
        ProductTypeItem[] productTypeItems = new ProductTypeItem[items.length];
        
        for (int i = 0; i < items.length; ++i) {
            productTypeItems[i] = new ProductTypeItem(items[i].getName(), items[i].getQuestions().length);
            addListeners(productTypeItems[i]);
        }
        
        return productTypeItems;
    }
    
    private void addListeners(ProductTypeItem item) {
        //item.addQuestionsButtonListener(null);
    }

    
    public View getView() {
        return view;
    }
}
