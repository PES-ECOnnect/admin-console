package ECOnnect.UI.ProductTypes;

import java.awt.event.*;

import ECOnnect.UI.Interfaces.*;
import ECOnnect.UI.Product.ProductScreen;
import ECOnnect.UI.ScreenManager;

public class ProductTypesController implements Controller {
    private ProductTypesView view = new ProductTypesView(this);
    private ProductTypesModel model = new ProductTypesModel();
    
    ActionListener addButton() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: add to model
                view.addItem(new ProductTypeItem("abcd", 123));
            }
        };
    }

    ActionListener viewButton(){
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: add to model
                //view.viewInfo();
                ScreenManager.getInstance().show(ScreenManager.PRODUCT_SCREEN);
            }
        };
    }

    public View getView() {
        return view;
    }
}
