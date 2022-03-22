package ECOnnect.UI.NewCompany;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HorizontalBox;

import javax.swing.*;
import java.awt.*;

public class NewCompanyView extends View {
    private final NewCompanyController _ctrl;

    private final JTextField _nameTxt = new JTextField(20);
    private final JTextField _locationTxt = new JTextField(20);
    private final JTextField _imageUrl = new JTextField(20);

    // COMPONENTS

    public NewCompanyView(NewCompanyController ctrl){
        this._ctrl=ctrl;
        setUp();
    }

    private void setUp(){
        panel.add(Box.createVerticalGlue());

        _nameTxt.setMaximumSize(new Dimension(100, 30));
        panel.add(HorizontalBox.create(new JLabel("Company Name:     "), _nameTxt));

        panel.add(Box.createVerticalStrut(10));

        _locationTxt.setMaximumSize(new Dimension(100,30));
        panel.add(HorizontalBox.create(new JLabel("Company Location:"), _locationTxt));

        panel.add(Box.createVerticalStrut(10));

        _imageUrl.setMaximumSize(new Dimension(100, 30));
        panel.add(HorizontalBox.create(new JLabel("Image URL:              "), _imageUrl));

        panel.add(Box.createVerticalStrut(10));

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(_ctrl.saveButton());
        panel.add(saveButton);

        panel.add(Box.createVerticalStrut(10));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(_ctrl.cancelButton());
        panel.add(cancelButton);

        panel.add(Box.createVerticalGlue());
    }

    public String getNameText() {
        return _nameTxt.getText();
    }

    public String getLocationText() {
        return _locationTxt.getText();
    }

    public String getImageTxt() {
        return _imageUrl.getText();
    }
}
