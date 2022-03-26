package ECOnnect.UI.Company.Questions;

import java.awt.event.*;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Company.CompanyModel;
import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;

public class CompanyQuestionsController implements Controller {
    private final CompanyQuestionsView _view = new CompanyQuestionsView(this);
    private final CompanyModel _model = new CompanyModel();
     
        
    ActionListener backButton() {
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(ScreenManager.MAIN_MENU_SCREEN);
        };
    }
    
    String getQuestions() {
        String[] questions = _model.getQuestions();
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
