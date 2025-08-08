package controller.itinerary;

import model.Dailyitineraries;
import service.impl.DailyitinerariesServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.Font;

public class DailyitinerariesUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField textDay, textTime, textLocation, textNote, textCost, textAddress;
    private JComboBox<String> comboCategory;
    private JTable table;
    private DefaultTableModel tableModel;
    private int itineraryId;
    private int selectedId = -1;
    private DailyitinerariesServiceImpl service = new DailyitinerariesServiceImpl();
    private JFrame parentFrame;

    /**
     * @wbp.parser.constructor
     */
    public DailyitinerariesUI(int itineraryId) {
        this(itineraryId, null);
    }

    public DailyitinerariesUI(int itineraryId, JFrame parent) {
        this.itineraryId = itineraryId;
        this.parentFrame = parent;
        setTitle("每日行程管理 - Itinerary ID: " + itineraryId);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

    
        JLabel lblDay = new JLabel("第");
        lblDay.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        lblDay.setBounds(20, 10, 20, 30);
        getContentPane().add(lblDay);
        textDay = new JTextField();
        textDay.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        textDay.setBounds(50, 11, 30, 30);
        getContentPane().add(textDay);

        JLabel lblTime = new JLabel("時間");
        lblTime.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        lblTime.setBounds(20, 50, 40, 30);
        getContentPane().add(lblTime);
        textTime = new JTextField();
        textTime.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        textTime.setBounds(20, 90, 60, 30);
        getContentPane().add(textTime);

        JLabel lblLocation = new JLabel("景點/交通");
        lblLocation.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        lblLocation.setBounds(90, 50, 100, 30);
        getContentPane().add(lblLocation);
        textLocation = new JTextField();
        textLocation.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        textLocation.setBounds(90, 90, 100, 30);
        getContentPane().add(textLocation);

        JLabel lblCategory = new JLabel("類別");
        lblCategory.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        lblCategory.setBounds(200, 49, 60, 30);
        getContentPane().add(lblCategory);
        comboCategory = new JComboBox<>(new String[]{"交通","住宿","景點","美食","購物"});
        comboCategory.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        comboCategory.setBounds(200, 89, 100, 30);
        getContentPane().add(comboCategory);

        JLabel lblNote = new JLabel("備註");
        lblNote.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        lblNote.setBounds(310, 56, 40, 25);
        getContentPane().add(lblNote);
        textNote = new JTextField();
        textNote.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        textNote.setBounds(310, 89, 100, 30);
        getContentPane().add(textNote);

        JLabel lblCost = new JLabel("費用");
        lblCost.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        lblCost.setBounds(418, 53, 60, 30);
        getContentPane().add(lblCost);
        textCost = new JTextField();
        textCost.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        textCost.setBounds(420, 89, 50, 30);
        getContentPane().add(textCost);

        JLabel lblAddress = new JLabel("地址");
        lblAddress.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        lblAddress.setBounds(488, 53, 60, 30);
        getContentPane().add(lblAddress);
        textAddress = new JTextField();
        textAddress.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        textAddress.setBounds(480, 89, 200, 30);
        getContentPane().add(textAddress);

        JButton btnAdd = new JButton("新增");
        btnAdd.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnAdd.setBounds(20, 159, 90, 30);
        getContentPane().add(btnAdd);

        JButton btnUpdate = new JButton("更新");
        btnUpdate.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnUpdate.setBounds(130, 159, 90, 30);
        getContentPane().add(btnUpdate);

        JButton btnDelete = new JButton("刪除");
        btnDelete.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnDelete.setBounds(240, 159, 90, 30);
        getContentPane().add(btnDelete);

        JButton btnExport = new JButton("匯出 Excel");
        btnExport.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnExport.setBounds(350, 159, 120, 30);
        getContentPane().add(btnExport);

        JButton btnBack = new JButton("返回行程表");
        btnBack.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnBack.setBounds(490, 159, 150, 30);
        getContentPane().add(btnBack);

     
        tableModel = new DefaultTableModel(new Object[]{"ID","Day","Time","Location","Category","Note","Cost","Address"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 219, 840, 311);
        getContentPane().add(sp);
        
        JLabel lblDay_1 = new JLabel("天");
        lblDay_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        lblDay_1.setBounds(90, 10, 20, 30);
        getContentPane().add(lblDay_1);

    
        loadTable();

   
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int r = table.getSelectedRow();
                if (r >= 0) {
                    selectedId = (int) tableModel.getValueAt(r, 0);
                    textDay.setText(tableModel.getValueAt(r, 1).toString());
                    textTime.setText(tableModel.getValueAt(r, 2).toString());
                    textLocation.setText(tableModel.getValueAt(r, 3).toString());
                    comboCategory.setSelectedItem(tableModel.getValueAt(r, 4).toString());
                    textNote.setText(tableModel.getValueAt(r, 5).toString());
                    textCost.setText(tableModel.getValueAt(r, 6).toString());
                    textAddress.setText(tableModel.getValueAt(r, 7).toString());
                }
            }
        });

   
        btnAdd.addActionListener(e -> {
            if (!validateInputs(true)) return;
            try {
                int dayNum = Integer.parseInt(textDay.getText().trim());
                int costVal = Integer.parseInt(textCost.getText().trim());
                Dailyitineraries d = new Dailyitineraries(itineraryId, dayNum, textTime.getText().trim(),
                        textLocation.getText().trim(), comboCategory.getSelectedItem().toString(),
                        textNote.getText().trim(), costVal, textAddress.getText().trim());
                boolean ok = service.addDaily(d);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "新增成功");
                    clearInputs();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(this, "新增失敗");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "新增發生錯誤: " + ex.getMessage());
            }
        });

        btnUpdate.addActionListener(e -> {
            if (selectedId == -1) {
                JOptionPane.showMessageDialog(this, "請先選擇要更新的列");
                return;
            }
            if (!validateInputs(false)) return;
            try {
                int dayNum = Integer.parseInt(textDay.getText().trim());
                int costVal = Integer.parseInt(textCost.getText().trim());
                Dailyitineraries d = new Dailyitineraries(selectedId, itineraryId, dayNum, textTime.getText().trim(),
                        textLocation.getText().trim(), comboCategory.getSelectedItem().toString(),
                        textNote.getText().trim(), costVal, textAddress.getText().trim());
                boolean ok = service.updateDaily(d);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "更新成功");
                    clearInputs();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(this, "更新失敗");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "更新發生錯誤: " + ex.getMessage());
            }
        });

        btnDelete.addActionListener(e -> {
            if (selectedId == -1) {
                JOptionPane.showMessageDialog(this, "請先選擇要刪除的列");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "確定要刪除嗎？", "刪除確認", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean ok = service.deleteDaily(selectedId);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "刪除成功");
                    clearInputs();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(this, "刪除失敗");
                }
            }
        });

        btnExport.addActionListener(e -> exportToExcel());

        btnBack.addActionListener(e -> {
         
            if (parentFrame != null) parentFrame.setVisible(true);
            dispose();
        });
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        List<Dailyitineraries> list = service.getByItineraryId(itineraryId);
        for (Dailyitineraries d : list) {
            tableModel.addRow(new Object[]{
                    d.getId(), d.getDay_number(), d.getTime(), d.getLocation(),
                    d.getCategory(), d.getNote(), d.getCost(), d.getAddress()
            });
        }
    }

    private boolean validateInputs(boolean forAdd) {
 
        String dayS = textDay.getText().trim();
        if (dayS.isEmpty()) {
            JOptionPane.showMessageDialog(this, "請輸入天數 (Day)");
            return false;
        }
        try {
            int day = Integer.parseInt(dayS);
            if (day <= 0) {
                JOptionPane.showMessageDialog(this, "天數必須為正整數");
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "天數必須是整數");
            return false;
        }

     
        String costS = textCost.getText().trim();
        if (!costS.isEmpty()) {
            try {
                Integer.parseInt(costS);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "費用必須為整數或留空");
                return false;
            }
        } else {
            textCost.setText("0"); 
        }

    
        return true;
    }

    private void clearInputs() {
        textDay.setText("");
        textTime.setText("");
        textLocation.setText("");
        comboCategory.setSelectedIndex(0);
        textNote.setText("");
        textCost.setText("");
        textAddress.setText("");
        selectedId = -1;
    }

    private void exportToExcel() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("儲存 Excel");
        int userChoice = chooser.showSaveDialog(this);
        if (userChoice != JFileChooser.APPROVE_OPTION) return;

        try (Workbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("DailyItineraries");
            Row h = sheet.createRow(0);
            String[] headers = {"ID","Day","Time","Location","Category","Note","Cost","Address"};
            for (int i=0;i<headers.length;i++) h.createCell(i).setCellValue(headers[i]);

            int rowIdx = 1;
            for (int r = 0; r < tableModel.getRowCount(); r++) {
                Row row = sheet.createRow(rowIdx++);
                for (int c = 0; c < tableModel.getColumnCount(); c++) {
                    Object val = tableModel.getValueAt(r, c);
                    row.createCell(c).setCellValue(val == null ? "" : val.toString());
                }
            }

            String path = chooser.getSelectedFile().getAbsolutePath();
            if (!path.toLowerCase().endsWith(".xlsx")) path += ".xlsx";
            try (FileOutputStream out = new FileOutputStream(path)) {
                wb.write(out);
            }
            JOptionPane.showMessageDialog(this, "匯出成功：" + path);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "匯出失敗: " + ex.getMessage());
        }
    }
}
