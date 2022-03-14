package ECOnnect.UI.ProductTypes;

import ECOnnect.UI.Utilities.ItemListElement;

import java.awt.*;

import javax.swing.*;

public class ProductTypeItem extends ItemListElement {
    
    private String name;
    private int numQuestions;
    private JCheckBox deleteCheckBox = new JCheckBox();
    
    public ProductTypeItem(String name, int numQuestions) {
        this.name = name;
        this.numQuestions = numQuestions;
        
        super.init();
    }
    
    public static String[] getHeaderNames() {
        return new String[] {"Name", "# Questions", "Select for delete"};
    }
    
    protected Component[] getRowComponents() {
        JTextField nameField = new JTextField(name);
        nameField.setEditable(false);
        JTextField numQuestionsField = new JTextField(Integer.toString(numQuestions));
        numQuestionsField.setEditable(false);
        
        return new Component[] {
            nameField,
            numQuestionsField,
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
}
