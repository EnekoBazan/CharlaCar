package domainLN;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button;
    private Object value;

    public ButtonEditor() {
        button = new JButton("Eliminar");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped(); // Notify table that editing is stopped
                // Your logic when button is clicked can go here
                System.out.println("Button clicked!");
            }
        });
    }

    @Override
    public Object getCellEditorValue() {
        return value; // Return value when editing is finished
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.value = value;  // Set the value for the cell
        return button; // Return the button as the editor component
    }
}
