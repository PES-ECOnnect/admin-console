package ECOnnect.UI.Company;

import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.ScreenManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanyController implements Controller {

    private final CompanyView _view = new CompanyView(this);
    private final CompanyModel _model = new CompanyModel();

    ActionListener addButton() {
        return (ActionEvent e) -> {
            // TODO: add to model
            ScreenManager.getInstance().show(ScreenManager.NEW_COMPANY_SCREEN);
        };
    }

    public View getView() {
        return _view;
    }
}
