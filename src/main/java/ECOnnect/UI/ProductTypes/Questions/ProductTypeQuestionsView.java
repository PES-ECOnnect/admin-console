package ECOnnect.UI.ProductTypes.Questions;

import java.awt.*;

import javax.swing.*;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HorizontalBox;

public class ProductTypeQuestionsView extends View {
    private final ProductTypeQuestionsController _ctrl;
    
    private final JTextArea _questionsTextArea = new JTextArea();
    private final JLabel _title = new JLabel("", JLabel.CENTER);
    
    ProductTypeQuestionsView(ProductTypeQuestionsController ctrl) {
        this._ctrl = ctrl;
        setUp();
    }
    
    
    private void setUp() {
        
        panel.add(Box.createVerticalGlue());
        
        // Increase title font size
        _title.setFont(_title.getFont().deriveFont(24.0f));
        panel.add(HorizontalBox.create(_title));
        
        panel.add(Box.createVerticalStrut(50));
        
        JLabel questionsLabel = new JLabel("List of questions (separated by new line):");
        panel.add(HorizontalBox.create(questionsLabel));
        
        JScrollPane scrollPane = new JScrollPane(_questionsTextArea);
        scrollPane.setMaximumSize(new Dimension(500, 300));
        scrollPane.setPreferredSize(new Dimension(500, 300));
        panel.add(scrollPane);
        
        
        panel.add(Box.createVerticalStrut(20));
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(_ctrl.backButton());
        panel.add(HorizontalBox.create(backButton));
        
        panel.add(Box.createVerticalGlue());
        
    }
    
    @Override
    public void postInit() {
        _title.setText("Questions for product type '" + _ctrl.getType() + "'");
        _questionsTextArea.setText(_ctrl.getQuestions());
    }
}
