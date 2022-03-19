package ECOnnect.UI.Company;

import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.ScreenManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanyController implements Controller {

    private CompanyView view = new CompanyView(this);
    private CompanyModel model = new CompanyModel();

    ActionListener addButton() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: add to model
                ScreenManager.getInstance().show(ScreenManager.NEW_COMPANY_SCREEN);
            }
        };
    }

    public ActionListener backButton() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenManager.getInstance().show(ScreenManager.MAIN_MENU_SCREEN);
            }
        };
    }

    public View getView() {
        return view;
    }
}
