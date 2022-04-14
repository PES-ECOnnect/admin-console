package ECOnnect.UI.Company;

import ECOnnect.UI.Interfaces.*;
import ECOnnect.API.CompanyService.Company;
import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Company.Create.NewCompanyScreen;
import ECOnnect.UI.Company.Questions.CompanyQuestionsScreen;

import java.awt.event.*;

public class CompanyController extends Controller {

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

    public View getView() {
        return _view;
    }
    
    @Override
    public void postInit(Object[] args) {
        // Get companies from model
        Company[] companies = null;
        try {
            companies = _model.getCompanies();
        }
        catch (Exception e) {
            _view.displayError("Could not fetch companies: " + e.getMessage());
            return;
        }
        
        // Convert to company items
        CompanyItem[] companyItems = new CompanyItem[companies.length];
        
        for (int i = 0; i < companies.length; ++i) {
            Company c = companies[i];
            companyItems[i] = new CompanyItem(c);
        }
        
        _view.addItems(companyItems);
    }
}
