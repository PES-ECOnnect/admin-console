package ECOnnect.UI.ProductTypes.Create;

import java.awt.event.*;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.ProductTypes.ProductTypesModel;

public class CreateProductTypeController implements Controller {
    private final CreateProductTypeView _view = new CreateProductTypeView(this);
    private final ProductTypesModel _model = new ProductTypesModel();
    
    
    ActionListener okButton() {
        return (ActionEvent e) -> {
            String name = _view.getName();
            String[] questions = _view.getQuestions();
            _model.addProductType(name, questions);
            ScreenManager.getInstance().show(ScreenManager.MAIN_MENU_SCREEN);
        };
    }
    
    ActionListener cancelButton() {
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(ScreenManager.MAIN_MENU_SCREEN);
        };
    }
    
    @Override
    public View getView() {
        return _view;
    }
}
