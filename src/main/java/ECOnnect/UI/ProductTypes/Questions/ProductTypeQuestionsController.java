package ECOnnect.UI.ProductTypes.Questions;

import ECOnnect.API.ProductTypesService;
import ECOnnect.API.ServiceFactory;
import ECOnnect.API.ProductTypesService.ProductType;
import ECOnnect.API.ProductTypesService.ProductType.Question;
import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.ProductTypes.Questions.QuestionsView.INewQuestionCallback;

public class ProductTypeQuestionsController extends Controller {
    private final QuestionsView _view = new QuestionsView();
    private String _type; 
    
    @Override
    public View getView() {
        return _view;
    }
    
    @Override
    public void postInit(Object[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Expected 2 arguments: productType:String, questions:Question[]");
        }
        _type = (String) args[0];
        Question[] questions = (Question[]) args[1];
        
        // Set title
        _view.setTitle("Questions for product type '" + _type + "'");
        
        // Set questions
        _view.setQuestions(questions);
    }
    
    
    private INewQuestionCallback _callback = new INewQuestionCallback() {
        @Override
        public void onNewQuestion() {
            ProductTypesService service = ServiceFactory.getInstance().getProductTypesService();
            service.createQuestion(_type, "");
            
            ProductType[] types = service.getProductTypes();
            for (ProductType type : types) {
                if (type.name.equals(_type)) {
                    _view.setQuestions(type.questions);
                    break;
                }
            }
        }
    };
}
