package util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import controller.itinerary.DailyitinerariesUI;
//import controller.itinerary.DailyitineraryUI;
import controller.itinerary.ItinerariesUI;

public class ButtonEditor extends DefaultCellEditor {
	protected JButton button;
    private boolean clicked;
    private JTable table;
    private int row;
    private ItinerariesUI itinerariesUI;

	public ButtonEditor(JCheckBox checkBox, ItinerariesUI ui) {
		super(checkBox);
		this.itinerariesUI = ui;
		button = new JButton("每日行程");
        button.setOpaque(true);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                int itineraryId = Integer.parseInt(table.getValueAt(row, 0).toString());
                //JOptionPane.showMessageDialog(button, "你點了第 " + (row + 1) + " 行的每日行程 (ID=" + itineraryId + ")");
                new DailyitinerariesUI(itineraryId).setVisible(true);
                itinerariesUI.dispose();
                
            }
        });
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.table = table;
		this.row = row;
		button.setText("每日行程");
		clicked = true;
		return button;
	}
	
	@Override
	public Object getCellEditorValue() {
		return "每日行程";
	}
	
	@Override
	public boolean stopCellEditing() {
		clicked = false;
		return super.stopCellEditing();
	}
	
	@Override
	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}
