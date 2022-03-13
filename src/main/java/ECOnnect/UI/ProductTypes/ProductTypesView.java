package ECOnnect.UI.ProductTypes;

import javax.swing.JLabel;

import ECOnnect.UI.Interfaces.View;

public class ProductTypesView extends View {
    
    private ProductTypesController ctrl;
    
    // Components
    
    
    public ProductTypesView(ProductTypesController ctrl) {
        this.ctrl = ctrl;
        setUp();
    }
    
    // Build the GUI
    private void setUp() {
        panel.add(new JLabel("Product Types"));
    }
}
