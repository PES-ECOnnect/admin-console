package ECOnnect.UI.Product.Create;

import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Product.ProductModel;
import ECOnnect.UI.ScreenManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProductController extends Controller {
    private NewProductView _view = new NewProductView(this);
    private ProductModel _model = new ProductModel();
    private String _type;

    public View getView() {
        return _view;
    }

    ActionListener saveButton() {
        return (ActionEvent e) -> {
            String name = _view.getNameText();
            String manufacturer = _view.getManufacturerText();
            String imageUrl = _view.getImageUrlText();

            try{
                _model.addProduct(name, manufacturer, imageUrl, _type);
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
    
    @Override
    public void postInit(Object[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Expected 1 argument: productType:String");
        }
        _type = (String) args[0];
        _view.setTitle("Create new Product of type '" + _type + "'");
    }
}
