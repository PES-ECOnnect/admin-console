package ECOnnect.UI.ProductTypes.Create;

import java.awt.event.*;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.ProductTypes.ProductTypesModel;

public class CreateProductTypeController implements Controller {
    private CreateProductTypeView _view = new CreateProductTypeView(this);
    private ProductTypesModel _model = new ProductTypesModel();
    
    
    ActionListener okButton() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("OK");
            }
        };
    }
    
    ActionListener cancelButton() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ScreenManager.getInstance().show(ScreenManager.MAIN_MENU_SCREEN);
            }
        };
    }
    
    @Override
    public View getView() {
        return _view;
    }
}
