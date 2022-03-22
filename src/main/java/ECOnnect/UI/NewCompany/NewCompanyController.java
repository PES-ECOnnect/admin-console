package ECOnnect.UI.NewCompany;

import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.ScreenManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewCompanyController implements Controller {
    private final NewCompanyView _view = new NewCompanyView(this);
    private final NewCompanyModel _model = new NewCompanyModel();

    public View getView(){
        return _view;
    }

    public ActionListener saveButton(){
        return (ActionEvent e) -> {
            String name = _view.getNameText();
            String location = _view.getLocationText();
            String image = _view.getImageTxt();
            
            try {
                _model.validate(name, location, image);
            }
            catch (Exception ex) {
                _view.displayError("There has been an error:\n"+ex.getMessage());
                return;
            }
            // we should update CompanyScreen
            ScreenManager.getInstance().show(ScreenManager.COMPANY_SCREEN);
        };
    }

    public ActionListener cancelButton() {
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(ScreenManager.COMPANY_SCREEN);
        };
    }
}
