package ECOnnect.UI.ProductTypes;

import java.awt.event.*;
import java.util.Collection;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.*;
import ECOnnect.UI.ProductTypes.Create.CreateProductTypeScreen;
import ECOnnect.UI.ProductTypes.Questions.ProductTypeQuestionsScreen;
import ECOnnect.UI.Utilities.ExecutionThread;
import ECOnnect.API.ProductTypesService.ProductType;
import ECOnnect.API.ProductTypesService.ProductType.Question;

public class ProductTypesController extends Controller {
    private final ProductTypesView _view = new ProductTypesView(this);
    private final ProductTypesModel _model = new ProductTypesModel();
    
    ActionListener addButton() {
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(CreateProductTypeScreen.class);
        };
    }
    
    ActionListener deleteButton() {
        return (ActionEvent e) -> {
            Collection<ProductTypeItem> selected = _view.getSelectedItems();
            
            // Display confirmation dialog
            String type_plural = selected.size() == 1 ? " product type?" : " product types?";
            _view.displayConfirmation("Are you sure you want to delete " + selected.size() + type_plural, () -> {
                // YES action, delete companies
                for (ProductTypeItem item : selected) {
                    try {
                        _model.deleteProductType(item.getName());
                        _view.removeItem(item);
                    }
                    catch (Exception ex) {
                        _view.displayError("Could not remove product type '" + item.getName() + "':\n" + ex.getMessage());
                    }
                }
            });
        };
    }
    
    @Override
    public void postInit(Object[] args) {
        ExecutionThread.nonUI(()->{
            try {
                // Get types from model
                ProductType[] items = _model.getProductTypes();
                
                // Convert to type items
                ProductTypeItem[] productTypeItems = new ProductTypeItem[items.length];
                
                for (int i = 0; i < items.length; ++i) {
                    productTypeItems[i] = new ProductTypeItem(this, i, items[i].name, items[i].questions.length);
                }
                
                ExecutionThread.UI(()->_view.addItems(productTypeItems));
            }
            catch (Exception e) {
                ExecutionThread.UI(()-> {
                    _view.displayError("Could not fetch existing types: " + e.getMessage());
                });
            } 
        });
    }
    
    public void viewQuestions(int index) {
        String type = _model.getType(index).name;
        Question[] questions = _model.getType(index).questions;
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
