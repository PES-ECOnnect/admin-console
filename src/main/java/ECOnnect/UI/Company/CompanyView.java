package ECOnnect.UI.Company;

import javax.swing.*;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.ItemList;
import ECOnnect.UI.Utilities.HorizontalBox;

public class CompanyView extends View {
    private CompanyController ctrl;
    private ItemList<CompanyItem> list;

    // Components

    public CompanyView(CompanyController ctrl){
        this.ctrl = ctrl;
        setUp();
    }

    private void setUp() {
        list = new ItemList<CompanyItem>(CompanyItem.getHeaderNames());
        panel.add(list);

        list.add(new CompanyItem("Company 1", "location 1", "/path/image.png"));
        list.add(new CompanyItem("Company 2", "location2", "/path/image2.png"));

        JButton addButton = new JButton("Add new Company");
        addButton.addActionListener(ctrl.addButton());
        panel.add(HorizontalBox.create(addButton));

        JButton backButton = new JButton("Go back");
        backButton.addActionListener(ctrl.backButton());
        panel.add(HorizontalBox.create(backButton));

        // TODO: get data from model
    }

    void addItem(CompanyItem item){
        list.add(item);
        System.out.println("Added item. " + list.length());
        list.redraw();
    }

}
