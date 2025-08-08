package controller.itinerary;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Itinerary;
import model.Members;
import service.impl.ItineraryServiceImpl;
import util.ButtonEditor;
import util.ButtonRenderer;
import util.ExcelExporter;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import controller.members.ProfileUI;
import dao.impl.ItineraryDaoImpl;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import java.awt.Font;

public class ItinerariesUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField textDestination;
	
	private ItineraryServiceImpl itineraryServiceImpl = new ItineraryServiceImpl();
	
	private int selectedId = -1;
	private int member_id;
	private JTextField textCountry;
	private Members currentMember;

	public ItinerariesUI(Members member) {
		this.currentMember = member;
		int member_id = member.getId();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("國家:");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_1.setBounds(226, 25, 60, 30);
		contentPane.add(lblNewLabel_1);
		
		textCountry = new JTextField();
		textCountry.setBounds(296, 25, 200, 30);
		contentPane.add(textCountry);
		textCountry.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("目的地:");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel.setBounds(226, 65, 60, 30);
		contentPane.add(lblNewLabel);
		
		textDestination = new JTextField();
		textDestination.setBounds(296, 65, 200, 30);
		contentPane.add(textDestination);
		textDestination.setColumns(10);
		
		JLabel lblStartDate = new JLabel("出發:");
		lblStartDate.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblStartDate.setBounds(226, 105, 60, 30);
		contentPane.add(lblStartDate);
		
		JDateChooser startDateChooser = new JDateChooser();
		startDateChooser.setBounds(296, 105, 120, 30);
		contentPane.add(startDateChooser);
		
		JDateChooser endDateChooser = new JDateChooser();
		endDateChooser.setBounds(426, 105, 120, 30);
		contentPane.add(endDateChooser);
		
		JLabel lblNote = new JLabel("備註:");
		lblNote.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNote.setBounds(226, 143, 60, 30);
		contentPane.add(lblNote);
		
		JTextArea textNote = new JTextArea();
		textNote.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		textNote.setBounds(296, 145, 250, 50);
		contentPane.add(textNote);
		
		
		
		
		// 表單
		JLabel lblItineraryList = new JLabel("行程列表:");
		lblItineraryList.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblItineraryList.setBounds(10, 236, 100, 30);
		contentPane.add(lblItineraryList);
		
		// 表單欄位設定
		tableModel = new DefaultTableModel(new Object[] {"id", "國家", "目的地", "起始日", "結束日", "備註", "每日行程"}, 0);
		//tableModel = new DefaultTableModel(new Object[] {"id", "國家", "目的地", "起始日", "結束日", "備註"}, 0);
		table = new JTable(tableModel);
		table.getColumn("每日行程").setCellRenderer(new ButtonRenderer());
		table.getColumn("每日行程").setCellEditor(new ButtonEditor(new JCheckBox(), this));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 269, 764, 147);
		getContentPane().add(scrollPane);
		
		// 清空資料
		tableModel.setRowCount(0);
		
		// 載入資料
		List<Itinerary> lists = new ItineraryServiceImpl().getItinerariesByMemberId(member_id);
	
		for (Itinerary i : lists) {
			Object[] row = {
					i.getId(),
					i.getCountry(),
					i.getDestination(),
					i.getStart_date(),
					i.getEnd_date(),
					i.getNote(),
					"每日行程"
			};
			tableModel.addRow(row);
		}
		
		// 設定表格欄寬
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0); // 隱藏 ID 欄
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		
		// 加入滑鼠點擊事件
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row >= 0) {
					selectedId = (int) table.getValueAt(row, 0);
					// 顯示其他資料到輸入欄位
					textCountry.setText(table.getValueAt(row, 1).toString());
					textDestination.setText(table.getValueAt(row, 2).toString());
					startDateChooser.setDate(java.sql.Date.valueOf(table.getValueAt(row, 3).toString()));
					endDateChooser.setDate(java.sql.Date.valueOf(table.getValueAt(row, 4).toString()));
					textNote.setText(table.getValueAt(row, 5).toString());
				}
			}
		});
		
		// 按鈕
		JButton btnAdd = new JButton("新增");
		btnAdd.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				    java.util.Date utilStartDate = startDateChooser.getDate();
				    java.util.Date utilEndDate = endDateChooser.getDate();

				    if (utilStartDate == null || utilEndDate == null) {
				        JOptionPane.showMessageDialog(null, "請選擇開始與結束日期！");
				        return;
				    }

				    java.sql.Date startDate = new java.sql.Date(utilStartDate.getTime());
				    java.sql.Date endDate = new java.sql.Date(utilEndDate.getTime());

				    Itinerary itinerary = new Itinerary(
				    		textCountry.getText(),
				    		textDestination.getText(),
				    		startDate,
				    		endDate,
				    		textNote.getText(),
				    		member_id
				    );

				    new ItineraryServiceImpl().addItinerary(itinerary);
			
				    JOptionPane.showMessageDialog(null, "新增成功！");
			
					tableModel.setRowCount(0);
					
					// 載入資料
					List<Itinerary> lists = new ItineraryServiceImpl().getItinerariesByMemberId(member_id);
					for (Itinerary r : lists) {
						Object[] row = {r.getId(), r.getCountry(), r.getDestination(), r.getStart_date(), r.getEnd_date(), r.getNote()};
						tableModel.addRow(row);
					}
				} catch (Exception ex) {
				    ex.printStackTrace();
				    JOptionPane.showMessageDialog(null, "新增失敗：" + ex.getMessage());
				}

				
			}
		});
		btnAdd.setBounds(141, 205, 100, 30);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("更新");
		btnUpdate.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
		            int row = table.getSelectedRow();
		            if (row < 0) {
		                JOptionPane.showMessageDialog(null, "請先選取一筆資料！");
		                return;
		            }

		            int id = Integer.parseInt(table.getValueAt(row, 0).toString());

		            java.util.Date utilStartDate = startDateChooser.getDate();
		            java.util.Date utilEndDate = endDateChooser.getDate();

		            if (utilStartDate == null || utilEndDate == null) {
		                JOptionPane.showMessageDialog(null, "請選擇開始與結束日期！");
		                return;
		            }

		            java.sql.Date startDate = new java.sql.Date(utilStartDate.getTime());
		            java.sql.Date endDate = new java.sql.Date(utilEndDate.getTime());

		            Itinerary itinerary = new Itinerary(
		            		id,
		            		textCountry.getText(),
		            		textDestination.getText(),
		            		startDate,
		            		endDate,
		            		textNote.getText(),
		            		member_id
		            );
		            
		            boolean success = new ItineraryServiceImpl().updateItinerary(itinerary);

		            if (success) {
		                JOptionPane.showMessageDialog(null, "更新成功！");
		            } else {
		            	System.out.println(success);
		                JOptionPane.showMessageDialog(null, "更新失敗！");
		            }
		            
		            // 清空輸入值
		            textCountry.setText("");
		            textDestination.setText("");
		            startDateChooser.setDate(null);
		            endDateChooser.setDate(null);
		            textNote.setText("");

		            // 重新載入表格
		            tableModel.setRowCount(0);
		            List<Itinerary> updatedList = new ItineraryServiceImpl().getItinerariesByMemberId(member_id);
		            for (Itinerary it : updatedList) {
		                tableModel.addRow(new Object[] {
		                    it.getId(), it.getCountry(), it.getDestination(), it.getStart_date(), it.getEnd_date(), it.getNote()
		                });
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "更新失敗：" + ex.getMessage());
		        }
			}
		});
		btnUpdate.setBounds(251, 205, 100, 30);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("刪除");
		btnDelete.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnDelete.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = table.getSelectedRow();

		        if (row >= 0) {
		            int confirm = JOptionPane.showConfirmDialog(null, "確定要刪除這筆資料嗎？", "確認刪除", JOptionPane.YES_NO_OPTION);
		            if (confirm == JOptionPane.YES_OPTION) {

		                int id = Integer.parseInt(table.getValueAt(row, 0).toString());

		                Itinerary i = new Itinerary();
		                i.setId(id);

		                boolean success = itineraryServiceImpl.deleteItinerary(i);
		               
		                if (success) {
		                    JOptionPane.showMessageDialog(null, "刪除成功！");
		                } else {
		                    JOptionPane.showMessageDialog(null, "刪除失敗！");
		                }

		                // 4. 重新載入表格資料
		                tableModel.setRowCount(0);
		                List<Itinerary> updatedList = new ItineraryServiceImpl().getItinerariesByMemberId(member_id);
		                for (Itinerary it : updatedList) {
		                    tableModel.addRow(new Object[] {
		                        it.getId(), it.getCountry(), it.getDestination(), it.getStart_date(), it.getEnd_date(), it.getNote()
		                    });
		                }
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "請先選擇一筆資料再刪除。");
		        }
		    }
		});
		btnDelete.setBounds(357, 205, 100, 30);
		contentPane.add(btnDelete);

		
		JButton btnClear = new JButton("清除");
		btnClear.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textCountry.setText("");
				textDestination.setText("");
				startDateChooser.setDate(null);
				endDateChooser.setDate(null);
				textNote.setText("");
				table.clearSelection();
				selectedId = -1;
			}
		});
		btnClear.setBounds(467, 206, 100, 30);
		contentPane.add(btnClear);
		
		JButton btnExport = new JButton("匯出 Excel");
		btnExport.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnExport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("儲存 Excel 檔案");
		        int userSelection = fileChooser.showSaveDialog(null);

		        if (userSelection == JFileChooser.APPROVE_OPTION) {
		            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
		            if (!filePath.endsWith(".xlsx")) {
		                filePath += ".xlsx";
		            }
		            ExcelExporter.exportTableToExcel(table, filePath);
		        }
			}
		});
		btnExport.setBounds(577, 426, 200, 30);
		contentPane.add(btnExport);
		
		JButton btnPrint = new JButton("列印");
		btnPrint.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnPrint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					table.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPrint.setBounds(467, 426, 100, 30);
		contentPane.add(btnPrint);
		
		JButton btnCancel = new JButton("取消");
		btnCancel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ProfileUI(member).setVisible(true);
				dispose();
			}
		});
		btnCancel.setBounds(577, 206, 100, 30);
		contentPane.add(btnCancel);
	}
}
