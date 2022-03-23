package ECOnnect.UI.Company;

import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.API.CompanyService.Company;
import ECOnnect.UI.ScreenManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanyController implements Controller {

    private final CompanyView _view = new CompanyView(this);
    private final CompanyModel _model = new CompanyModel();

    ActionListener addButton() {
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(ScreenManager.NEW_COMPANY_SCREEN);
        };
    }
    
    ActionListener questionsButton(){
        return (ActionEvent e) -> {
            System.out.println("Questions button pressed");
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
            companyItems[i] = new CompanyItem(c.getName(), c.getImageUrl(), c.getAvgRating(), c.getLat(), c.getLon());
        }
        
        return companyItems;
    }

    public View getView() {
        return _view;
    }
}
