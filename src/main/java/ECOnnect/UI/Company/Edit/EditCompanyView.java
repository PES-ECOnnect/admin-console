package ECOnnect.UI.Company.Edit;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HintTextFieldUI;
import ECOnnect.UI.Utilities.HorizontalBox;

import javax.swing.*;
import java.awt.*;

public class EditCompanyView extends View {
    private final IEditCompanyController _ctrl;

    private final JTextField _nameTxt = new JTextField(41);
    private final JTextField _imageUrl = new JTextField(41);
    private final JTextField _latitudeTxt = new JTextField(19);
    private final JTextField _longitudeTxt = new JTextField(19);
    
    private final Dimension _dim = new Dimension(120, 30);

    // COMPONENTS

    public EditCompanyView(IEditCompanyController ctrl, String title){
        this._ctrl=ctrl;
        setUp(title);
    }

    private void setUp(String title) {
        panel.add(Box.createVerticalGlue());
        
        // Set title alignment to center and increase font size
        JLabel _title = new JLabel(title, JLabel.CENTER);
        _title.setFont(_title.getFont().deriveFont(24.0f));
        panel.add(HorizontalBox.create(_title));
        
        panel.add(Box.createVerticalStrut(50));

        JLabel nameLbl = new JLabel("Company name:", JLabel.RIGHT);
        nameLbl.setPreferredSize(_dim);
        _nameTxt.setMaximumSize(_dim);
        panel.add(HorizontalBox.create(nameLbl, _nameTxt));
        // Pressing enter will select the next text field
        _nameTxt.addActionListener(e -> _imageUrl.requestFocus());
        
        panel.add(Box.createVerticalStrut(10));

        JLabel imageUrlLbl = new JLabel("Image URL:", JLabel.RIGHT);
        imageUrlLbl.setPreferredSize(_dim);
        _imageUrl.setMaximumSize(_dim);
        panel.add(HorizontalBox.create(imageUrlLbl, _imageUrl));
        // Pressing enter will select the next text field
        _imageUrl.addActionListener(e -> _latitudeTxt.requestFocus());

        panel.add(Box.createVerticalStrut(10));

        JLabel locationLbl = new JLabel("Coordinates:", JLabel.RIGHT);
        locationLbl.setPreferredSize(_dim);
        _latitudeTxt.setMaximumSize(_dim);
        _longitudeTxt.setMaximumSize(_dim);
        _latitudeTxt.setUI(new HintTextFieldUI("Latitude", true));
        _longitudeTxt.setUI(new HintTextFieldUI("Longitude", true));
        panel.add(HorizontalBox.create(locationLbl, _latitudeTxt, _longitudeTxt));
        // Pressing enter will select the next text field
        _latitudeTxt.addActionListener(e -> _longitudeTxt.requestFocus());

        panel.add(Box.createVerticalStrut(10));

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(_ctrl.saveButton());
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(_ctrl.cancelButton());
        panel.add(HorizontalBox.create(cancelButton, saveButton));

        panel.add(Box.createVerticalGlue());
    }
    
    public void setFields(String name, String imageUrl, double latitude, double longitude){
        _nameTxt.setText(name);
        _imageUrl.setText(imageUrl);
        _latitudeTxt.setText(Double.toString(latitude));
        _longitudeTxt.setText(Double.toString(longitude));
    }

    public String getNameText() {
        return _nameTxt.getText();
    }

    public String getLatitudeText() {
        return _latitudeTxt.getText();
    }
    
    public String getLongitudeText() {
        return _longitudeTxt.getText();
    }

    public String getImageTxt() {
        return _imageUrl.getText();
    }
}
