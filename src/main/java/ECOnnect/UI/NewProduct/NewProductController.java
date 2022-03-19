package ECOnnect.UI.NewProduct;

import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Product.ProductController;
import ECOnnect.UI.ScreenManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProductController implements Controller {
    NewProductView view = new NewProductView(this);
    NewProductModel model = new NewProductModel();
    //ProductController prodCtrl = new ProductController();

    public View getView() {
        return view;
    }

    public ActionListener saveButton() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = view.getNameText();
                String manufacturer = view.getManufacturerText();
                String imageUrl = view.getImageUrlText();
                String type = view.getTypeText();

                try{
                    model.validate(name, manufacturer, imageUrl, type);
                }
                catch (Exception ex){
                    view.displayError("There has been an error:\n"+ex.getMessage());
                    return;
                }
                // we should update ProductScreen
                //prodCtrl.addProduct(name,manufacturer);
                ScreenManager.getInstance().show(ScreenManager.PRODUCT_SCREEN);
            }
        };
    }

    public ActionListener cancelButton() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ScreenManager.getInstance().show(ScreenManager.PRODUCT_SCREEN);
            }
        };
    }

}
