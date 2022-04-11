package ECOnnect.UI.Company.Questions;

import java.awt.*;

import javax.swing.*;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HorizontalBox;

public class CompanyQuestionsView extends View {
    private final CompanyQuestionsController _ctrl;
    
    private final JTextArea _questionsTextArea = new JTextArea();
    
    CompanyQuestionsView(CompanyQuestionsController ctrl) {
        this._ctrl = ctrl;
        setUp();
    }
    
    
    private void setUp() {
        
        panel.add(Box.createVerticalGlue());
        
        // Increase title font size
        JLabel title = new JLabel("Questions for all companies", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont(24.0f));
        panel.add(HorizontalBox.create(title));
        
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
    
    void setQuestionsText(String questions) {
        _questionsTextArea.setText(questions);
    }
}
