package ECOnnect.UI.ProductTypes;

import javax.swing.*;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HorizontalBox;
import ECOnnect.UI.Utilities.CustomComponents.ItemList;

public class ProductTypesView extends View {
    
    private final ProductTypesController _ctrl;
    private ItemList<ProductTypeItem> _list;
    
    
    public ProductTypesView(ProductTypesController ctrl) {
        this._ctrl = ctrl;
        setUp();
    }
    
    // Build the GUI
    private void setUp() {
        _list = new ItemList<>(ProductTypeItem.getHeaderNames(), ProductTypeItem.getWidths());
        panel.add(_list);
        
        panel.add(Box.createVerticalStrut(10));
        
        JButton addButton = new JButton("Add new");
        addButton.addActionListener(_ctrl.addButton());
        panel.add(HorizontalBox.create(addButton));
        
        panel.add(Box.createVerticalStrut(10));
    }
    
    void addItem(ProductTypeItem item) {
        _list.add(item);
        _list.redraw();
    }
    
    void addItems(ProductTypeItem[] items) {
        for (ProductTypeItem item : items) {
            _list.add(item);
        }
        _list.redraw();
    }

}
