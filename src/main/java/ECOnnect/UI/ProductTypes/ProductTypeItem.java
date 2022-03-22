package ECOnnect.UI.ProductTypes;

import ECOnnect.UI.Utilities.ItemListElement;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ProductTypeItem extends ItemListElement {
    
    private int _index;
    private String _name;
    private int _numQuestions;
    private JCheckBox _deleteCheckBox = new JCheckBox();
    private ProductTypesController _owner;

    public ProductTypeItem(ProductTypesController owner, int index, String name, int numQuestions) {
        this._owner = owner;
        this._index = index;
        this._name = name;
        this._numQuestions = numQuestions;
        
        super.init();
    }
    
    public static String[] getHeaderNames() {
        return new String[] {"Name", "# Questions", "See questions", "View all products", "Select for delete"};
    }
    
    protected Component[] getRowComponents() {
        JTextField nameField = new JTextField(_name);
        nameField.setEditable(false);
        
        JTextField numQuestionsField = new JTextField(Integer.toString(_numQuestions));
        numQuestionsField.setEditable(false);
        
        JButton seeProductsButton = new JButton("Products");
        seeProductsButton.addActionListener(seeProductsButtonListener());
        
        JButton seeQuestionsButton = new JButton("Questions");
        seeQuestionsButton.addActionListener(seeQuestionsButtonListener());
                
        return new Component[] {
            nameField,
            numQuestionsField,
            seeQuestionsButton,
            seeProductsButton,
            _deleteCheckBox
        };
    }

    
    private ActionListener seeProductsButtonListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                _owner.viewProducts(_index);
            }
        };
    }
    
    private ActionListener seeQuestionsButtonListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                _owner.viewQuestions(_index);
            }
        };
    }
    
    
    @Override
    public boolean isSelected() {
        return _deleteCheckBox.isSelected();
    }
    
    @Override
    public void uncheck() {
        _deleteCheckBox.setSelected(false);
    }
}
