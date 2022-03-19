package ECOnnect.UI.NewCompany;

import ECOnnect.UI.Interfaces.Screen;

public class NewCompanyScreen extends Screen {
    public NewCompanyScreen(){
        super(new NewCompanyController());
    }
    public String getTitle(){return "New Product Form";}
}
