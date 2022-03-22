package ECOnnect.UI.Product;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HorizontalBox;
import ECOnnect.UI.Utilities.ItemList;

import javax.swing.*;

public class ProductView extends View {
    private final ProductController _ctrl;
    private ItemList<ProductItem> _list;

    // Components

    public ProductView(ProductController ctrl) {
        this._ctrl = ctrl;
        setUp();
    }

    private void setUp() {
        _list = new ItemList<>(ProductItem.getHeaderNames());
        panel.add(_list);

        panel.add(Box.createVerticalStrut(10));
        
        JButton addButton = new JButton("Add new");
        addButton.addActionListener(_ctrl.addButton());
        panel.add(HorizontalBox.create(addButton));

        panel.add(Box.createVerticalStrut(10));
        
        JButton backButton = new JButton("Go back");
        backButton.addActionListener(_ctrl.backButton());
        panel.add(HorizontalBox.create(backButton));

        panel.add(Box.createVerticalStrut(10));
    }

    public void addItem(ProductItem item) {
        _list.add(item);
        _list.redraw();
    }
    
    @Override
    public void postInit() {
        for (ProductItem item : _ctrl.getProductItems()) {
            _list.add(item);
        }        
        _list.redraw();
    }

}
