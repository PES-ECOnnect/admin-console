package ECOnnect.UI.ProductTypes.Questions;

import java.awt.event.*;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;

public class ProductTypeQuestionsController extends Controller {
    private final ProductTypeQuestionsView _view = new ProductTypeQuestionsView(this);
    
    ActionListener backButton() {
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(ScreenManager.MAIN_MENU_SCREEN);
        };
    }
    
    @Override
    public View getView() {
        return _view;
    }
    
    @Override
    public void postInit(Object[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Expected 2 arguments: productType:String, questions:String[]");
        }
        String type = (String) args[0];
        String[] questions = (String[]) args[1];
        
        // Set title
        _view.setTitle("Questions for product type '" + type + "'");
        
        // Set questions
        StringBuilder sb = new StringBuilder();
        for (String question : questions) {
            sb.append(question);
            sb.append("\n");
        }
        _view.setQuestionsText(sb.toString());
    }
}
