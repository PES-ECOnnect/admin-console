package ECOnnect.UI.ProductTypes;

import ECOnnect.UI.Utilities.ItemListElement;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ProductTypeItem extends ItemListElement {
    
    private String name;
    private int numQuestions;
    private JCheckBox deleteCheckBox = new JCheckBox();
    private JButton viewButton = new JButton("Products");
    private JButton seeQuestionsButton = new JButton("Questions");

    public ProductTypeItem(String name, int numQuestions) {
        this.name = name;
        this.numQuestions = numQuestions;
        
        super.init();
    }
    
    public static String[] getHeaderNames() {
        return new String[] {"Name", "# Questions", "See questions", "View all products", "Select for delete"};
    }
    
    protected Component[] getRowComponents() {
        JTextField nameField = new JTextField(name);
        nameField.setEditable(false);
        JTextField numQuestionsField = new JTextField(Integer.toString(numQuestions));
        numQuestionsField.setEditable(false);
                
        return new Component[] {
            nameField,
            numQuestionsField,
            seeQuestionsButton,
            viewButton,
            deleteCheckBox
        };
    }
    
    @Override
    public boolean isSelected() {
        return deleteCheckBox.isSelected();
    }
    
    @Override
    public void uncheck() {
        deleteCheckBox.setSelected(false);
    }

    public void addViewButtonListener(ActionListener listener) {
        viewButton.addActionListener(listener);
    }
    
    public void addQuestionsButtonListener(ActionListener listener) {
        seeQuestionsButton.addActionListener(listener);
    }
}
