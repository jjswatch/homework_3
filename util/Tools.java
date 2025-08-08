package util;

import java.awt.Component;
import java.awt.GridLayout;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Tools {
	
	//private Map<String, java.util.List<JTextField[]>> itineraryMap = new LinkedHashMap<>();
	//private JPanel daysPanel;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public static void addDayPanel(int currentDay, JPanel container, Map<Integer, List<JTextField>> itineraryMap) {
	    JPanel panel = new JPanel(new GridLayout(0, 8, 5, 5));
	    panel.setBorder(BorderFactory.createTitledBorder("Day " + currentDay));
	    
	    
	    String[] labels = {"City", "Arrival", "Place/Transport", "Category", "Note", "Cost", "Address", "Time"};
	    List<JTextField> textFields = new ArrayList<>();

	    for (String label : labels) {
	        JTextField field = new JTextField();
	        field.setToolTipText(label);
	        textFields.add(field);
	        panel.add(field);
	    }

	    itineraryMap.put(currentDay, textFields);
	    container.add(panel);
	    container.revalidate();
	    container.repaint();
	}
	
	public static void exportToExcel(Map<Integer, List<JTextField>> itineraryMap, Component parent) {
        Workbook workbook = new XSSFWorkbook();

        for (Map.Entry<Integer, List<JTextField>> entry : itineraryMap.entrySet()) {
            Sheet sheet = workbook.createSheet("Day " + entry.getKey());
            Row header = sheet.createRow(0);
            String[] headers = {"City", "Arrival", "Place/Transport", "Category", "Note", "Cost", "Address", "Time"};

            for (int i = 0; i < headers.length; i++) {
                header.createCell(i).setCellValue(headers[i]);
            }

            Row row = sheet.createRow(1);
            List<JTextField> fields = entry.getValue();
            for (int i = 0; i < fields.size(); i++) {
                row.createCell(i).setCellValue(fields.get(i).getText());
            }
        }

        try {
            FileOutputStream out = new FileOutputStream("Daily_Itinerary.xlsx");
            workbook.write(out);
            out.close();
            workbook.close();
            JOptionPane.showMessageDialog(parent, "Excel Exported Successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parent, "Export Failed: " + ex.getMessage());
        }
    }

}
