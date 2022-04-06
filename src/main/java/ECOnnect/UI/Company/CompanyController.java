package ECOnnect.UI.Company;

import ECOnnect.UI.Interfaces.*;
import ECOnnect.API.CompanyService.Company;
import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Company.Create.NewCompanyScreen;
import ECOnnect.UI.Company.Questions.CompanyQuestionsScreen;

import java.awt.event.*;

public class CompanyController implements Controller {

    private final CompanyView _view = new CompanyView(this);
    private final CompanyModel _model = new CompanyModel();

    ActionListener addButton() {
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(NewCompanyScreen.class);
        };
    }
    
    ActionListener questionsButton(){
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(CompanyQuestionsScreen.class);
        };
    }
    
    CompanyItem[] getCompanyItems() {        
        // Get companies from model
        Company[] companies = null;
        try {
            companies = _model.getCompanies();
        }
        catch (Exception e) {
            _view.displayError("Could not fetch companies: " + e.getMessage());
            return new CompanyItem[0];
        }
        
        // Convert to company items
        CompanyItem[] companyItems = new CompanyItem[companies.length];
        
        for (int i = 0; i < companies.length; ++i) {
            Company c = companies[i];
            companyItems[i] = new CompanyItem(c.name, c.imageurl, c.avgrating, c.lat, c.lon);
        }
        
        return companyItems;
    }

    public View getView() {
        return _view;
    }
}
