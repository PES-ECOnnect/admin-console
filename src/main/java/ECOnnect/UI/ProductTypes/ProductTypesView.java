package ECOnnect.UI.ProductTypes;

import javax.swing.*;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HorizontalBox;
import ECOnnect.UI.Utilities.ItemList;

public class ProductTypesView extends View {
    
    private final ProductTypesController _ctrl;
    private ItemList<ProductTypeItem> _list;
    
    
    public ProductTypesView(ProductTypesController ctrl) {
        this._ctrl = ctrl;
        setUp();
    }
    
    // Build the GUI
    private void setUp() {
        _list = new ItemList<>(ProductTypeItem.getHeaderNames());
        panel.add(_list);
        
        JButton addButton = new JButton("Add new");
        addButton.addActionListener(_ctrl.addButton());
        panel.add(HorizontalBox.create(addButton));
    }
    
    void addItem(ProductTypeItem item) {
        _list.add(item);
        _list.redraw();
    }
    
    @Override
    public void postInit() {
        for (ProductTypeItem item : _ctrl.getProductTypeItems()) {
            _list.add(item);
        }
        _list.redraw();
    }

}
