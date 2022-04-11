package ECOnnect.UI.ProductTypes;

import java.awt.event.*;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.*;
import ECOnnect.UI.ProductTypes.Create.CreateProductTypeScreen;
import ECOnnect.UI.ProductTypes.Questions.ProductTypeQuestionsScreen;
import ECOnnect.API.ProductTypesService.ProductType;

public class ProductTypesController extends Controller {
    private final ProductTypesView _view = new ProductTypesView(this);
    private final ProductTypesModel _model = new ProductTypesModel();
    
    ActionListener addButton() {
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(CreateProductTypeScreen.class);
        };
    }
    
    @Override
    public void postInit(Object[] args) {
        
        // Get types from model
        ProductType[] items = null;
        try {
            items = _model.getProductTypes();
        }
        catch (Exception e) {
            _view.displayError("Could not fetch existing types: " + e.getMessage());
            return;
        }
        
        // Convert to type items
        
        ProductTypeItem[] productTypeItems = new ProductTypeItem[items.length];
        
        for (int i = 0; i < items.length; ++i) {
            productTypeItems[i] = new ProductTypeItem(this, i, items[i].name, items[i].questions.length);
        }
        
        _view.addItems(productTypeItems);
    }
    
    public void viewQuestions(int index) {
        String type = _model.getType(index).name;
        String[] questions = _model.getType(index).questions;
        ScreenManager.getInstance().show(ProductTypeQuestionsScreen.class, type, questions);
    }
    
    public void viewProducts(int index) {
        String type = _model.getType(index).name;
        ScreenManager.getInstance().show(ScreenManager.PRODUCT_SCREEN, type);
    }

    
    public View getView() {
        return _view;
    }
}
