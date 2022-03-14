package ECOnnect.UI.ProductTypes;

import java.awt.event.*;

import ECOnnect.UI.Interfaces.*;

public class ProductTypesController implements Controller {
    private ProductTypesView view = new ProductTypesView(this);
    private ProductTypesModel model = new ProductTypesModel();
    
    ActionListener addButton() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: add to model
                view.addItem(new ProductTypeItem("abcd", 123));
            }
        };
    }
    
    public View getView() {
        return view;
    }
}
