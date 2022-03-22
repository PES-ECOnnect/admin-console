package ECOnnect.UI.ProductTypes.Create;

import java.awt.*;

import javax.swing.*;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HorizontalBox;

public class CreateProductTypeView extends View {
    private CreateProductTypeController ctrl;
    
    private JTextField _nameTextField = new JTextField(20);
    private JTextArea _questionsTextArea = new JTextArea();
    
    CreateProductTypeView(CreateProductTypeController ctrl) {
        this.ctrl = ctrl;
        setUp();
    }
    
    
    private void setUp() {
        
        panel.add(Box.createVerticalStrut(50));
        
        // Set title alignment to center and increase font size
        JLabel title = new JLabel("Create new Product Type");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(title.getFont().deriveFont(24.0f));
        panel.add(HorizontalBox.create(title));
        
        panel.add(Box.createVerticalStrut(50));
        
        _nameTextField.setMaximumSize(new Dimension(150, 30));
        panel.add(HorizontalBox.create(new JLabel("Name of the new type: "), _nameTextField));
        // Pressing enter will select the password field
        _nameTextField.addActionListener(e -> _questionsTextArea.requestFocus());
        
        panel.add(Box.createVerticalStrut(20));
        
        JLabel questionsLabel = new JLabel("List of questions (one on each line):");
        panel.add(HorizontalBox.create(questionsLabel));
        
        JScrollPane scrollPane = new JScrollPane(_questionsTextArea);
        scrollPane.setMaximumSize(new Dimension(500, 300));
        panel.add(scrollPane);
        
        
        panel.add(Box.createVerticalStrut(50));
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(ctrl.okButton());
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(ctrl.cancelButton());
        panel.add(HorizontalBox.create(cancelButton, okButton));
        
        panel.add(Box.createVerticalStrut(50));
        
    }
}
