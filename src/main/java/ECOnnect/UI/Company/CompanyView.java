package ECOnnect.UI.Company;

import javax.swing.*;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.ItemList;
import ECOnnect.UI.Utilities.HorizontalBox;

public class CompanyView extends View {
    private final CompanyController _ctrl;
    private ItemList<CompanyItem> _list;

    // Components

    public CompanyView(CompanyController ctrl){
        this._ctrl = ctrl;
        setUp();
    }

    private void setUp() {
        _list = new ItemList<CompanyItem>(CompanyItem.getHeaderNames());
        panel.add(_list);

        _list.add(new CompanyItem("Company 1", "location 1", "/path/image.png"));
        _list.add(new CompanyItem("Company 2", "location2", "/path/image2.png"));

        JButton addButton = new JButton("Add new Company");
        addButton.addActionListener(_ctrl.addButton());
        panel.add(HorizontalBox.create(addButton));

        // TODO: get data from model
    }

    void addItem(CompanyItem item){
        _list.add(item);
        System.out.println("Added item. " + _list.length());
        _list.redraw();
    }

}
