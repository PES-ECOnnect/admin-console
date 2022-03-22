package ECOnnect.UI.ProductTypes.Questions;

import java.awt.event.*;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.ProductTypes.ProductTypesModel;

public class ProductTypeQuestionsController implements Controller {
    private final ProductTypeQuestionsView _view = new ProductTypeQuestionsView(this);
    private final ProductTypesModel _model = new ProductTypesModel();
     
        
    ActionListener backButton() {
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(ScreenManager.MAIN_MENU_SCREEN);
        };
    }
    
    String getType() {
        return _model.getSelectedType().getName();
    }
    
    String getQuestions() {
        String[] questions = _model.getSelectedType().getQuestions();
        StringBuilder sb = new StringBuilder();
        for (String question : questions) {
            sb.append(question);
            sb.append("\n");
        }
        return sb.toString();
    }
    
    @Override
    public View getView() {
        return _view;
    }
}
