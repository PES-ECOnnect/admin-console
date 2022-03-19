package ECOnnect.UI.Product;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HorizontalBox;
import ECOnnect.UI.Utilities.ItemList;

import javax.swing.*;

public class ProductView extends View {
    private ProductController ctrl;
    private ItemList<ProductItem> list;

    // Components

    public ProductView(ProductController ctrl) {
        this.ctrl = ctrl;
        setUp();
    }

    private void setUp() {
        list = new ItemList<>(ProductItem.getHeaderNames());
        panel.add(list);

        JButton addButton = new JButton("Add new");
        addButton.addActionListener(ctrl.addButton());
        panel.add(HorizontalBox.create(addButton));

        JButton backButton = new JButton("Go back");
        backButton.addActionListener(ctrl.backButton());
        panel.add(HorizontalBox.create(backButton));

        // TODO: get data from model
        list.add(new ProductItem("Product 1", "Manufacturer x", "path/image.png", "type 1"));
    }

    public void addItem(ProductItem item) {
        list.add(item);
        list.redraw();
    }

}
