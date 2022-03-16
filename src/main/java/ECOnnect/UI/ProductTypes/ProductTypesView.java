package ECOnnect.UI.ProductTypes;

import javax.swing.*;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HorizontalBox;
import ECOnnect.UI.Utilities.ItemList;

public class ProductTypesView extends View {
    
    private ProductTypesController ctrl;
    private ItemList<ProductTypeItem> list;
    
    // Components
    
    
    public ProductTypesView(ProductTypesController ctrl) {
        this.ctrl = ctrl;
        setUp();
    }
    
    // Build the GUI
    private void setUp() {        
        list = new ItemList<>(ProductTypeItem.getHeaderNames());
        panel.add(list);
        
        JButton addButton = new JButton("Add new");
        addButton.addActionListener(ctrl.addButton());
        panel.add(HorizontalBox.create(addButton));
        
        // TODO: get data from model
        list.add(new ProductTypeItem("aaaa", 5));
    }
    
    void addItem(ProductTypeItem item) {
        list.add(item); 
        list.redraw();
    }
}
