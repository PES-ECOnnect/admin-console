package ECOnnect.UI.ProductTypes;

import javax.swing.*;

import ECOnnect.UI.Company.CompanyItem;
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
        setViewAction(list);
    }
    
    void setViewAction(ItemList<ProductTypeItem> list){
        // Load action listener for every view button
        for (int i = 0; i < list.length();++i){
            ProductTypeItem pItem = list.get(i);
            if(pItem != null){
                pItem.getViewButton().addActionListener(ctrl.viewButton());
            }
        }
    }
    
    void addItem(ProductTypeItem item) {
        item.getViewButton().addActionListener(ctrl.viewButton());
        list.add(item);
        list.redraw();
    }

}
