package �����ð�_���̺���;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class CustomerManagement extends JFrame {
	FileDialog readOpen, saveOpen;
	// [�߿�]���� Ŭ������ ��ü ���� => Ŭ���� ��ü�� ���� ��ȣ�ۿ��� ���ؼ�
	MenuMain menuMain = new MenuMain();
	West west = new West();
	Buttons buttons = new Buttons();
	ShowTable showTable = new ShowTable();

	int updateRow; // ���� ����

	// �ܺ� Ŭ���� ������
	public CustomerManagement() {

		setTitle("������ �ý���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(menuMain.mb, BorderLayout.NORTH);
		add(west, BorderLayout.WEST);
		add(showTable.scrollPane, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);

		setSize(600, 300);
		setLocation(700, 300);
		setVisible(true);

	}

	// MenuMain ���� Ŭ���� ����
	class MenuMain extends JPanel implements ActionListener, ItemListener {

		JMenuBar mb;
		JMenu file, sort, help;
		JMenuItem fopen, fsave, fexit, proinfo;
		JCheckBoxMenuItem sname;

		FileDialog readOpen, saveOpen;
		String fileDir, FileName, readFileName, saveFileName;

		// MenuMain ����Ŭ���� ������
		public MenuMain() {
			mb = new JMenuBar();

			file = new JMenu("����");
			sort = new JMenu("����");
			help = new JMenu("����");

			fopen = new JMenuItem("����");
			fsave = new JMenuItem("����");
			fexit = new JMenuItem("�ݱ�");

			sname = new JCheckBoxMenuItem("�̸�");

			proinfo = new JMenuItem("���α׷� ����");

			file.add(fopen);
			file.add(fsave);
			file.addSeparator();
			file.add(fexit);

			sort.add(sname);
			help.add(proinfo);
			mb.add(file);
			mb.add(sort);
			mb.add(help);

			// �̺�Ʈ ����
			fopen.addActionListener(this);
			fsave.addActionListener(this);
			fexit.addActionListener(this);
			sname.addActionListener(this);

		}

		@Override
		public void itemStateChanged(ItemEvent e) {

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String itemPressed = e.getActionCommand();

			if (itemPressed.equals("����")) {
				String name = saveName(); // ����� ���� �޼ҵ� ȣ��

				// ����ó�� �ʼ�
				try {
					save(name); // ����� ���� �޼ҵ� ȣ��
				} catch (Exception e2) {
					System.out.println(e2);
				}
			} else if (itemPressed.equals("����")) {
				String name = readName(); // ����� ���� �޼ҵ� ȣ��
				try {
					load(name);
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}

		}

		public void load(String loadfile) throws IOException {
			BufferedReader read = new BufferedReader(new FileReader(loadfile));
			// BufferedReader <- ���� ��Ʈ�� FileReader <- �⺻ ��½�Ʈ��
			String line = read.readLine();

			while (line != null) {
				Vector vc = new Vector();
				vc.add(line);
				System.out.println(line);
			}
			read.close();
		} // end load()

		public String readName() {
			 readOpen = new FileDialog(new JFrame(), "��������", FileDialog.LOAD);
			readOpen.setVisible(true);

			String fileDir = readOpen.getDirectory();
			String fileName = readOpen.getFile();
			String loadfilename;
			loadfilename = fileDir + "//" + fileName;
			return loadfilename;
		} // end readName()

		public void save(String savefile) throws IOException {
			BufferedWriter save = new BufferedWriter(new FileWriter(savefile));
			// new FileWriter <- ���� �Է½�Ʋ
		
			for(int i=0;i<showTable.data.size();i++){
				save.write((String)showTable.data.get(i).toString());
			}

			// save.write(line);
			save.close();
		} // end save()

		public String saveName() {
			 saveOpen = new FileDialog(new JFrame(), "��������", FileDialog.SAVE); //���� ȭ��
			// ������
			saveOpen.setVisible(true);
			String fileDir = saveOpen.getDirectory(); // ���� ������ ���丮�� �����´�.
			String fileName = saveOpen.getFile(); // ���� �̸��� �����´�.
			String savefilename; // ������ ���� ���丮 ��ο� �����̸��� ����
			savefilename = fileDir + "//" + fileName;
			return savefilename; // "(���ϰ��)/(�����̸�)" ��ȯ
		} // end saveName()

	}// end MenuMain ����Ŭ����

	// West ���� Ŭ����
	class West extends JPanel {
		JLabel la;
		JTextField[] tf;

		// West ���� Ŭ���� ������
		public West() {

			// [�Է�] ���� �����
			LineBorder line = new LineBorder(Color.BLUE, 2);
			setBorder(new TitledBorder(line, "�Է�"));

			String[] text = { "�� ��", "�ڵ�����ȣ", "�ֹε�Ϲ�ȣ" };
			tf = new JTextField[3];
			setLayout(new GridLayout(3, 2, 5, 10));

			for (int i = 0; i < text.length; i++) {
				la = new JLabel(text[i]);
				tf[i] = new JTextField(20);
				la.setHorizontalAlignment(JLabel.CENTER);
				add(la);
				add(tf[i]);

			}
			setPreferredSize(new Dimension(230, 300));

		}
	}// end West ���� Ŭ����
		// Buttons ���� Ŭ���� ����

	class Buttons extends JPanel implements ActionListener {
		Vector<String> vector;
		JButton addBtn, updateBtn, delBtn;
		String juminNo;

		// Button ���� Ŭ���� ������
		public Buttons() {
			setLayout(new GridLayout(1, 3, 0, 0));
			addBtn = new JButton("�߰�");
			updateBtn = new JButton("����");
			delBtn = new JButton("����");

			addBtn.setBackground(Color.GREEN);
			updateBtn.setBackground(Color.YELLOW);
			delBtn.setBackground(Color.LIGHT_GRAY);

			add(addBtn);
			add(updateBtn);
			add(delBtn);

			// �̺�Ʈ ����
			addBtn.addActionListener(this);
			updateBtn.addActionListener(this);
			delBtn.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("�߰�"))
				addData();
			else if (e.getActionCommand().equals("����"))
				updateData();
			else if (e.getActionCommand().equals("����"))
				deleteData();
		}

		// '�߰�' ��� ����� ���� �޼ҵ�
		public void addData() {
			Vector<String> vector = new Vector<String>();

			vector.add(west.tf[0].getText()); // �̸� ������
			vector.add(west.tf[1].getText()); // �ڵ��� ������

			juminNo = west.tf[2].getText(); // �ֹι�ȣ ������

			// �ֹι�ȣ�� ����ǥ���� ����
			String regex = "[0-9]{6}-[0-9]{7}";
			/* boolean regex_check = juminNo.matches(regex); */
			boolean regex_check = Pattern.matches(regex, juminNo);

			if (regex_check == false) {
				JOptionPane.showMessageDialog(null, "�ֹι�ȣ�� ����ǥ���Ŀ� �����ʽ��ϴ�.",
						"���޽���", JOptionPane.ERROR_MESSAGE);
				west.tf[2].setText(null);
				west.tf[2].requestFocus(); // Ŀ�� �����̴°�
				return; // �� ���� ����
			}
			// �ֹι�ȣ üũ ���� ����
			int[] weight = { 2, 3, 4, 5, 6, 7, 0, 8, 9, 2, 3, 4, 5 };
			int sum = 0; // ���� ������ 0���� �ʱ�ȭ
			for (int i = 0; i < 13; i++) {
				if (juminNo.charAt(i) == '-')
					continue;
				sum += (juminNo.charAt(i) - 48) * weight[i];
			}
			int temp = 11 - (sum % 11);
			int result = temp % 10; // temp���� �� �ڸ��� ��� => 10, 11 => 0,1

			if (result == juminNo.charAt(13) - 48) {
				// ���ڰ��� 2���� ���� => �������� �޼����� ��� ��
				JOptionPane.showMessageDialog(null, "�ֹι�ȣ ����" + "\n"
						+ "'Ȯ��' ��ư�� ������ ������ ��µ˴ϴ�.");

				vector.add(west.tf[2].getText());

				west.tf[0].setText(null);
				west.tf[1].setText(null);
				west.tf[2].setText(null);
				west.tf[0].requestFocus();

				showTable.data.addElement(vector);
				showTable.datamodel.fireTableDataChanged();
			} else {
				JOptionPane.showMessageDialog(null, "�ֹι�ȣ Ʋ��!", "���޼���",
						JOptionPane.ERROR_MESSAGE);
				west.tf[2].setText(null);
				west.tf[2].requestFocus();
			}
		}

		// '����' �̺�Ʈ ó��
		public void updateData() {
			// "�̸�" ������ ���
			showTable.table.setValueAt(west.tf[0].getText(), updateRow, 0);

			// "�ڵ�����ȣ" ������ ���
			showTable.table.setValueAt(west.tf[1].getText(), updateRow, 1);

			west.tf[0].setText(null);
			west.tf[1].setText(null);
			west.tf[2].setText(null);
			west.tf[0].requestDefaultFocus();
		}

		public void deleteData() {
			int yes_no_select = JOptionPane.showConfirmDialog(null, "���� ����??",
					"�� ������ ����", JOptionPane.YES_NO_OPTION);

			if (yes_no_select == JOptionPane.YES_OPTION) {
				JTable tb = showTable.table;

				int deleteRow = tb.getSelectedRow();

				if (deleteRow == -1) {
					return;
				} else {
					DefaultTableModel model = (DefaultTableModel) tb.getModel();
					model.removeRow(deleteRow);

					west.tf[0].setText(null);
					west.tf[1].setText(null);
					west.tf[2].setText(null);
					// �ֹι�ȣ�� Ȱ��ȭ��Ų��.
					west.tf[2].setEditable(true);
					west.tf[0].requestDefaultFocus();
				}
			} else {
				return;
			}
		}
	}// end Buttons ����Ŭ����
		// ShowTable ���� Ŭ���� ����

	class ShowTable extends MouseAdapter {

		DefaultTableModel datamodel;
		JTable table;
		JScrollPane scrollPane;

		String[] colName = { "�� ��", "�ڵ�����ȣ", "�ֹε�Ϲ�ȣ" };
		// [�߿�]
		// Vector (Collection <? extends E > c)
		Vector<Vector<String>> data;
		Vector<String> colume_name;

		// ShowTable ���� Ŭ���� ������
		public ShowTable() {
			data = new Vector<Vector<String>>();
			colume_name = new Vector<String>();

			for (int i = 0; i < colName.length; i++) {
				colume_name.add(colName[i]);
			}
			// [�߿�]
			// (1) ����JTable�� ����� ��(Model) ����
			datamodel = new DefaultTableModel(data, colume_name);
			table = new JTable(datamodel);
			scrollPane = new JScrollPane(table);

			// �� ũ�� ����
			table.getColumnModel().getColumn(0).setPreferredWidth(50);
			table.getColumnModel().getColumn(1).setPreferredWidth(50);
			table.getColumnModel().getColumn(2).setPreferredWidth(50);

			// �̺�Ʈ ����
			table.addMouseListener(this);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			updateRow = table.getSelectedRow();

			west.tf[0].setText((String) showTable.table
					.getValueAt(updateRow, 0));
			west.tf[1].setText((String) showTable.table
					.getValueAt(updateRow, 1));
			west.tf[2].setText((String) showTable.table
					.getValueAt(updateRow, 2));

			west.tf[2].setEditable(false);
		}
	}

	public static void main(String[] args) {
		new CustomerManagement();

	}

}
