package ECOnnect.UI.Product;

import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.ProductTypes.ProductTypeItem;
import ECOnnect.UI.ScreenManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductController implements Controller {
    private final ProductView _view = new ProductView(this);
    private final ProductModel _model = new ProductModel();

    ActionListener addButton() {
        return (ActionEvent e) -> {
            // TODO: add to model
            //view.addItem(new ProductItem("New Product", "Someone"));

            ScreenManager.getInstance().show(ScreenManager.NEW_PRODUCT_SCREEN);
        };
    }

    ActionListener backButton(){
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(ScreenManager.MAIN_MENU_SCREEN);
        };
    }
/*
    public void addProduct(String name, String manufacturer){
        view.addItem(new ProductItem(name, manufacturer));
    }
*/
    public View getView(){
        return _view;
    }
}
