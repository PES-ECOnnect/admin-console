package ECOnnect.UI.NewProduct;

import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Product.ProductController;
import ECOnnect.UI.ScreenManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProductController implements Controller {
    private NewProductView _view = new NewProductView(this);
    private NewProductModel _model = new NewProductModel();
    //ProductController _prodCtrl = new ProductController();

    public View getView() {
        return _view;
    }

    public ActionListener saveButton() {
        return (ActionEvent e) -> {
            String name = _view.getNameText();
            String manufacturer = _view.getManufacturerText();
            String imageUrl = _view.getImageUrlText();
            String type = _view.getTypeText();

            try{
                _model.validate(name, manufacturer, imageUrl, type);
            }
            catch (Exception ex){
                _view.displayError("There has been an error:\n"+ex.getMessage());
                return;
            }
            // we should update ProductScreen
            //_prodCtrl.addProduct(name,manufacturer);
            ScreenManager.getInstance().show(ScreenManager.PRODUCT_SCREEN);
        };
    }

    public ActionListener cancelButton() {
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(ScreenManager.PRODUCT_SCREEN);
        };
    }

}
