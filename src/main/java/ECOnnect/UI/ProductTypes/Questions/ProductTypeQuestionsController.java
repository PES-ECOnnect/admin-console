package ECOnnect.UI.ProductTypes.Questions;

import ECOnnect.API.ProductTypesService.ProductType.Question;
import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;

public class ProductTypeQuestionsController extends Controller {
    private final QuestionsView _view = new QuestionsView();
    
    @Override
    public View getView() {
        return _view;
    }
    
    @Override
    public void postInit(Object[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Expected 2 arguments: productType:String, questions:Question[]");
        }
        String type = (String) args[0];
        Question[] questions = (Question[]) args[1];
        
        // Set title
        _view.setTitle("Questions for product type '" + type + "'");
        
        // Set questions
        _view.setQuestions(questions);
    }
}
