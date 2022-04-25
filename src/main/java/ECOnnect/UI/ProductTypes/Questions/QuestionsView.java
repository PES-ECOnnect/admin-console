package ECOnnect.UI.ProductTypes.Questions;

import java.awt.event.*;

import javax.swing.*;

import ECOnnect.API.ProductTypesService.ProductType.Question;
import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HorizontalBox;
import ECOnnect.UI.Utilities.CustomComponents.ItemList;

public class QuestionsView extends View {
    
    private final JLabel _title = new JLabel("", JLabel.CENTER);
    private ItemList<QuestionItem> _list;
    
    public QuestionsView() {
        setUp();
    }
    
    
    private void setUp() {
        
        _list = new ItemList<>(QuestionItem.getHeaderNames());
        
        panel.add(Box.createVerticalStrut(20));
        
        // Increase title font size
        _title.setFont(_title.getFont().deriveFont(24.0f));
        panel.add(HorizontalBox.create(_title));
        
        panel.add(Box.createVerticalStrut(20));
        
        panel.add(_list);
        
        panel.add(Box.createVerticalStrut(20));
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener((ActionEvent e) -> {
            ScreenManager.getInstance().show(ScreenManager.MAIN_MENU_SCREEN);
        });
        panel.add(HorizontalBox.create(backButton));
        
        panel.add(Box.createVerticalStrut(20));
    }
    
    public void setQuestions(Question[] questions) {
        for (Question question : questions) {
            _list.add(new QuestionItem(question));
        }
    }
    
    public void setTitle(String title) {
        ScreenManager.getInstance().updateTitle(title);
        _title.setText(title);
    }
}
