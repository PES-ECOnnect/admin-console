package ECOnnect.UI.Forum;

import java.util.ArrayList;

import javax.swing.*;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.ItemList;
import ECOnnect.UI.Utilities.ItemListElement;
import ECOnnect.UI.Utilities.HorizontalBox;

public class ForumView extends View {
    private final ForumController _ctrl;
    private ItemList<ForumPostItem> _list;

    // Components

    public ForumView(ForumController ctrl){
        this._ctrl = ctrl;
        setUp();
    }

    private void setUp() {
        _list = new ItemList<ForumPostItem>(ForumPostItem.getHeaderNames());
        panel.add(_list);

        panel.add(Box.createVerticalStrut(10));
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(_ctrl.refreshButton());
        JButton deleteButton = new JButton("Delete selected");
        deleteButton.addActionListener(_ctrl.deleteButton());
        panel.add(HorizontalBox.create(refreshButton, deleteButton));
        
        panel.add(Box.createVerticalStrut(10));
    }
    
    void clearList() {
        _list.clear();
    }

    void addItem(ForumPostItem item){
        _list.add(item);
        _list.redraw();
    }
    
    void addItems(ForumPostItem[] items){
        for (ForumPostItem item : items) {
            _list.add(item);
        }
        _list.redraw();
    }
    
    ArrayList<ForumPostItem> getSelected() {
        ArrayList<ForumPostItem> selected = new ArrayList<ForumPostItem>();
        for (ItemListElement item : _list.getSelected()) {
            selected.add((ForumPostItem) item);
        }
        return selected;
    }
}