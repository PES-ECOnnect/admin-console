package ECOnnect.UI.ProductTypes;

import java.awt.event.*;

import ECOnnect.UI.Interfaces.*;

public class ProductTypesController implements Controller {
    private ProductTypesView view = new ProductTypesView(this);
    private ProductTypesModel model = new ProductTypesModel();
    
    public ActionListener testButton() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement
                model.getClass();
            }
        };
    }
    
    public View getView() {
        return view;
    }
}
