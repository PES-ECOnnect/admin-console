package ECOnnect.UI.Product.Create;

import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Product.ProductModel;
import ECOnnect.UI.ScreenManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProductController implements Controller {
    private NewProductView _view = new NewProductView(this);
    private ProductModel _model = new ProductModel();

    public View getView() {
        return _view;
    }

    ActionListener saveButton() {
        return (ActionEvent e) -> {
            String name = _view.getNameText();
            String manufacturer = _view.getManufacturerText();
            String imageUrl = _view.getImageUrlText();
            String type = getType();

            try{
                _model.addProduct(name, manufacturer, imageUrl, type);
            }
            catch (Exception ex) {
                _view.displayError("Could not create product:\n" + ex.getMessage());
                return;
            }
            
            ScreenManager.getInstance().show(ScreenManager.PRODUCT_SCREEN);
        };
    }

    ActionListener cancelButton() {
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(ScreenManager.PRODUCT_SCREEN);
        };
    }
    
    String getType() {
        return _model.getSelectedType();
    }
    
}
