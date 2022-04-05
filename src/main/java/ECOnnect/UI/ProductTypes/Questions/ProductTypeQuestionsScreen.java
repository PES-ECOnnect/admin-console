package ECOnnect.UI.ProductTypes.Questions;

import ECOnnect.UI.Interfaces.Screen;
import ECOnnect.UI.ProductTypes.ProductTypesModel;

public class ProductTypeQuestionsScreen extends Screen {
        
    public ProductTypeQuestionsScreen() {
        super(new ProductTypeQuestionsController());
    }
    
    public String getTitle() {
        return "Questions for product type '" + new ProductTypesModel().getSelectedType().name + "'";
    }
}
