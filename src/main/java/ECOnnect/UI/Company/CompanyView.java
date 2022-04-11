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

        panel.add(Box.createVerticalStrut(10));
        
        JButton questionsButton = new JButton("Set Company Questions");
        questionsButton.addActionListener(_ctrl.questionsButton());
        JButton addButton = new JButton("Add new Company");
        addButton.addActionListener(_ctrl.addButton());
        panel.add(HorizontalBox.create(questionsButton, addButton));
        
        panel.add(Box.createVerticalStrut(10));
    }

    void addItem(CompanyItem item){
        _list.add(item);
        _list.redraw();
    }
    
    void addItems(CompanyItem[] items){
        for (CompanyItem item : items) {
            _list.add(item);
        }
        _list.redraw();
    }
}
