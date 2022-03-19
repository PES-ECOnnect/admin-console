package ECOnnect.UI.NewCompany;

import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.ScreenManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewCompanyController implements Controller {
    NewCompanyView view = new NewCompanyView(this);
    NewCompanyModel model = new NewCompanyModel();

    public View getView(){return view;}

    public ActionListener saveButton(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = view.getNameText();
                String location = view.getLocationText();
                String image = view.getImageTxt();

                try{
                    model.validate(name, location, image);
                }
                catch (Exception ex){
                    view.displayError("There has been an error:\n"+ex.getMessage());
                    return;
                }
                // we should update CompanyScreen
                ScreenManager.getInstance().show(ScreenManager.COMPANY_SCREEN);
            }
        };
    }

    public ActionListener cancelButton() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenManager.getInstance().show(ScreenManager.COMPANY_SCREEN);
            }
        };
    }
}
