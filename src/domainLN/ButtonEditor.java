package domainLN;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private static final long serialVersionUID = 1L;
    private JButton button;
    private Object value;

    public ButtonEditor(String buttonText) {
        button = new JButton(buttonText);
        button.setToolTipText("Click to perform action");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped(); // Notify table that editing is stopped
                handleButtonAction(); // Delegate logic to a separate method
            }
        });
    }

    @Override
    public Object getCellEditorValue() {
        return value; // Return value when editing is finished
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.value = value; // Set the value for the cell
        if (isSelected) {
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setBackground(table.getBackground());
        }
        return button; // Return the button as the editor component
    }

    private void handleButtonAction() {
        // Placeholder for actual logic, e.g., row deletion
        System.out.println("Button clicked! Value: " + value);
        // Here you can implement the actual logic or delegate it to another class
    }
}
