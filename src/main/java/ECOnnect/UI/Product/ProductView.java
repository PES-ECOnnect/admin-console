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

        JButton addButton = new JButton("Add new");
        addButton.addActionListener(_ctrl.addButton());
        panel.add(HorizontalBox.create(addButton));

        JButton backButton = new JButton("Go back");
        backButton.addActionListener(_ctrl.backButton());
        panel.add(HorizontalBox.create(backButton));

        // TODO: get data from model
        _list.add(new ProductItem("Product 1", "Manufacturer x", "path/image.png", "type 1"));
    }

    public void addItem(ProductItem item) {
        _list.add(item);
        _list.redraw();
    }

}
