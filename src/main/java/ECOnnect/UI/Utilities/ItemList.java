package ECOnnect.UI.Utilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.*;

public class ItemList<T extends ItemListElement> extends JScrollPane {
    private JPanel list = new JPanel();
    private String[] headerNames;

    public ItemList(String[] headerNames) {
        this.headerNames = headerNames;
        list.setLayout(new BoxLayout(list, BoxLayout.PAGE_AXIS));
        
        super.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        super.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        super.getVerticalScrollBar().setUnitIncrement(5);
        super.setViewportView(list);
        
        addHeader();
    }
    
    private void addHeader() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.LINE_AXIS));
        headerPanel.setMaximumSize(ItemListElement.DEFAULT_SIZE);
        headerPanel.setMinimumSize(ItemListElement.DEFAULT_SIZE);
        list.add(headerPanel);
        
        for (String name : headerNames) {
            JTextField text = new JTextField(name);
            text.setEditable(false);
            text.setFont(new Font(Font.MONOSPACED, Font.PLAIN,  13));
            text.setHorizontalAlignment(JTextField.LEFT);
            text.setMaximumSize(ItemListElement.DEFAULT_ELEMENT_SIZE);
            text.setMinimumSize(ItemListElement.DEFAULT_ELEMENT_SIZE);
            
            headerPanel.add(text);
        }
    }
    
    // Redraw the UI
    public void redraw() {
        super.revalidate();
        super.repaint();
    }
    
    
    // Add an item to the list
    public void add(ItemListElement item) {
        list.add(item);
    }
    
    // Return the item at the specified index
    public T get(int index) {
        @SuppressWarnings("unchecked")
        T item = (T) list.getComponent(index + 1);
        return item;
    }
    
    // Remove an item from the list
    public void delete(ItemListElement item) {
        list.remove(item);
    }
    
    // Remove all items from the list
    public void clear() {
        list.removeAll();
        addHeader();
    }
    
    // Return the number of elements in the list
    public int length() {
        // Remove header
        return list.getComponentCount() - 1;
    }
    
    
    // SELECTING ITEMS
    
    // Return all selected items
    public Collection<ItemListElement> getSelected() {
        ArrayList<ItemListElement> selected = new ArrayList<>();

        // Start at 1 to avoid the headers panel
        for (int i = 1; i < list.getComponentCount(); ++i) {
            ItemListElement itemStruct = (ItemListElement) list.getComponent(i);
            
            if (itemStruct.isSelected()) {
                selected.add(itemStruct);
            }
        }
        
        return selected;
    }
    
    // Unselect all items
    public void clearSelection() {
        // Start at 1 to avoid the headers panel
        for (int i = 1; i < list.getComponentCount(); ++i) {
            ItemListElement itemStruct = (ItemListElement) list.getComponent(i);
            itemStruct.uncheck();
        }
    }
    
    // Remove all selected items from the list and return them
    public Collection<ItemListElement> removeSelected() {
        Collection<ItemListElement> selected = getSelected();
        for (var str : selected) delete(str);
        
        return selected;
    }
}
