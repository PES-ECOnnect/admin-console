package ECOnnect.UI.Company.Questions;

import java.awt.event.*;

import ECOnnect.API.ProductTypesService.ProductType.Question;
import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Company.CompanyModel;
import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.ProductTypes.Questions.QuestionsView;

public class CompanyQuestionsController extends Controller {
    private final QuestionsView _view = new QuestionsView();
    private final CompanyModel _model = new CompanyModel();
     
        
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
        _view.setTitle("Questions for all companies");
        Question[] questions = _model.getQuestions();
        _view.setQuestions(questions);
    }
}
