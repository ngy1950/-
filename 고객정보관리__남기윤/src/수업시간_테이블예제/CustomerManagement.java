package 수업시간_테이블예제;

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
	// [중요]내부 클래스들 객체 생성 => 클래스 객체들 간의 상호작용을 위해서
	MenuMain menuMain = new MenuMain();
	West west = new West();
	Buttons buttons = new Buttons();
	ShowTable showTable = new ShowTable();

	int updateRow; // 전역 변수

	// 외부 클래스 생성자
	public CustomerManagement() {

		setTitle("고객관리 시스템");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(menuMain.mb, BorderLayout.NORTH);
		add(west, BorderLayout.WEST);
		add(showTable.scrollPane, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);

		setSize(600, 300);
		setLocation(700, 300);
		setVisible(true);

	}

	// MenuMain 내부 클래스 구현
	class MenuMain extends JPanel implements ActionListener, ItemListener {

		JMenuBar mb;
		JMenu file, sort, help;
		JMenuItem fopen, fsave, fexit, proinfo;
		JCheckBoxMenuItem sname;

		FileDialog readOpen, saveOpen;
		String fileDir, FileName, readFileName, saveFileName;

		// MenuMain 내부클래스 생성자
		public MenuMain() {
			mb = new JMenuBar();

			file = new JMenu("파일");
			sort = new JMenu("정렬");
			help = new JMenu("도움말");

			fopen = new JMenuItem("열기");
			fsave = new JMenuItem("저장");
			fexit = new JMenuItem("닫기");

			sname = new JCheckBoxMenuItem("이름");

			proinfo = new JMenuItem("프로그램 정보");

			file.add(fopen);
			file.add(fsave);
			file.addSeparator();
			file.add(fexit);

			sort.add(sname);
			help.add(proinfo);
			mb.add(file);
			mb.add(sort);
			mb.add(help);

			// 이벤트 연결
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

			if (itemPressed.equals("저장")) {
				String name = saveName(); // 사용자 정의 메소드 호출

				// 예외처리 필수
				try {
					save(name); // 사용자 정의 메소드 호출
				} catch (Exception e2) {
					System.out.println(e2);
				}
			} else if (itemPressed.equals("열기")) {
				String name = readName(); // 사용자 정의 메소드 호출
				try {
					load(name);
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}

		}

		public void load(String loadfile) throws IOException {
			BufferedReader read = new BufferedReader(new FileReader(loadfile));
			// BufferedReader <- 참조 스트림 FileReader <- 기본 출력스트림
			String line = read.readLine();

			while (line != null) {
				Vector vc = new Vector();
				vc.add(line);
				System.out.println(line);
			}
			read.close();
		} // end load()

		public String readName() {
			 readOpen = new FileDialog(new JFrame(), "문서열기", FileDialog.LOAD);
			readOpen.setVisible(true);

			String fileDir = readOpen.getDirectory();
			String fileName = readOpen.getFile();
			String loadfilename;
			loadfilename = fileDir + "//" + fileName;
			return loadfilename;
		} // end readName()

		public void save(String savefile) throws IOException {
			BufferedWriter save = new BufferedWriter(new FileWriter(savefile));
			// new FileWriter <- 가본 입력스틀
		
			for(int i=0;i<showTable.data.size();i++){
				save.write((String)showTable.data.get(i).toString());
			}

			// save.write(line);
			save.close();
		} // end save()

		public String saveName() {
			 saveOpen = new FileDialog(new JFrame(), "문서저장", FileDialog.SAVE); //저장 화면
			// 보여줌
			saveOpen.setVisible(true);
			String fileDir = saveOpen.getDirectory(); // 내가 선택한 디렉토리를 가져온다.
			String fileName = saveOpen.getFile(); // 파일 이름을 가져온다.
			String savefilename; // 선택한 파일 디렉토리 경로와 파일이름을 저장
			savefilename = fileDir + "//" + fileName;
			return savefilename; // "(파일경로)/(파일이름)" 반환
		} // end saveName()

	}// end MenuMain 내부클래스

	// West 내부 클래스
	class West extends JPanel {
		JLabel la;
		JTextField[] tf;

		// West 내부 클래스 생성자
		public West() {

			// [입력] 보더 만들기
			LineBorder line = new LineBorder(Color.BLUE, 2);
			setBorder(new TitledBorder(line, "입력"));

			String[] text = { "이 름", "핸드폰번호", "주민등록번호" };
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
	}// end West 내부 클래스
		// Buttons 내부 클래스 구현

	class Buttons extends JPanel implements ActionListener {
		Vector<String> vector;
		JButton addBtn, updateBtn, delBtn;
		String juminNo;

		// Button 내부 클래스 생성자
		public Buttons() {
			setLayout(new GridLayout(1, 3, 0, 0));
			addBtn = new JButton("추가");
			updateBtn = new JButton("수정");
			delBtn = new JButton("삭제");

			addBtn.setBackground(Color.GREEN);
			updateBtn.setBackground(Color.YELLOW);
			delBtn.setBackground(Color.LIGHT_GRAY);

			add(addBtn);
			add(updateBtn);
			add(delBtn);

			// 이벤트 연결
			addBtn.addActionListener(this);
			updateBtn.addActionListener(this);
			delBtn.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("추가"))
				addData();
			else if (e.getActionCommand().equals("수정"))
				updateData();
			else if (e.getActionCommand().equals("삭제"))
				deleteData();
		}

		// '추가' 기능 사용자 정의 메소드
		public void addData() {
			Vector<String> vector = new Vector<String>();

			vector.add(west.tf[0].getText()); // 이름 데이터
			vector.add(west.tf[1].getText()); // 핸드폰 데이터

			juminNo = west.tf[2].getText(); // 주민번호 데이터

			// 주민번호에 정규표현식 적용
			String regex = "[0-9]{6}-[0-9]{7}";
			/* boolean regex_check = juminNo.matches(regex); */
			boolean regex_check = Pattern.matches(regex, juminNo);

			if (regex_check == false) {
				JOptionPane.showMessageDialog(null, "주민번호가 정규표현식에 맞지않습니다.",
						"경고메시지", JOptionPane.ERROR_MESSAGE);
				west.tf[2].setText(null);
				west.tf[2].requestFocus(); // 커서 깜빡이는거
				return; // 그 상태 유지
			}
			// 주민번호 체크 공식 적용
			int[] weight = { 2, 3, 4, 5, 6, 7, 0, 8, 9, 2, 3, 4, 5 };
			int sum = 0; // 누계 변수는 0으로 초기화
			for (int i = 0; i < 13; i++) {
				if (juminNo.charAt(i) == '-')
					continue;
				sum += (juminNo.charAt(i) - 48) * weight[i];
			}
			int temp = 11 - (sum % 11);
			int result = temp % 10; // temp값이 두 자리인 경우 => 10, 11 => 0,1

			if (result == juminNo.charAt(13) - 48) {
				// 인자값이 2개인 경우는 => 긍정적인 메세지를 띄울 때
				JOptionPane.showMessageDialog(null, "주민번호 정상" + "\n"
						+ "'확인' 버튼을 누르면 정보가 출력됩니다.");

				vector.add(west.tf[2].getText());

				west.tf[0].setText(null);
				west.tf[1].setText(null);
				west.tf[2].setText(null);
				west.tf[0].requestFocus();

				showTable.data.addElement(vector);
				showTable.datamodel.fireTableDataChanged();
			} else {
				JOptionPane.showMessageDialog(null, "주민번호 틀림!", "경고메세지",
						JOptionPane.ERROR_MESSAGE);
				west.tf[2].setText(null);
				west.tf[2].requestFocus();
			}
		}

		// '수정' 이벤트 처리
		public void updateData() {
			// "이름" 수정인 경우
			showTable.table.setValueAt(west.tf[0].getText(), updateRow, 0);

			// "핸드폰번호" 수정인 경우
			showTable.table.setValueAt(west.tf[1].getText(), updateRow, 1);

			west.tf[0].setText(null);
			west.tf[1].setText(null);
			west.tf[2].setText(null);
			west.tf[0].requestDefaultFocus();
		}

		public void deleteData() {
			int yes_no_select = JOptionPane.showConfirmDialog(null, "정말 삭제??",
					"고객 데이터 삭제", JOptionPane.YES_NO_OPTION);

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
					// 주민번호를 활성화시킨다.
					west.tf[2].setEditable(true);
					west.tf[0].requestDefaultFocus();
				}
			} else {
				return;
			}
		}
	}// end Buttons 내부클래스
		// ShowTable 내부 클래스 구현

	class ShowTable extends MouseAdapter {

		DefaultTableModel datamodel;
		JTable table;
		JScrollPane scrollPane;

		String[] colName = { "이 름", "핸드폰번호", "주민등록번호" };
		// [중요]
		// Vector (Collection <? extends E > c)
		Vector<Vector<String>> data;
		Vector<String> colume_name;

		// ShowTable 내부 클래스 생성자
		public ShowTable() {
			data = new Vector<Vector<String>>();
			colume_name = new Vector<String>();

			for (int i = 0; i < colName.length; i++) {
				colume_name.add(colName[i]);
			}
			// [중요]
			// (1) 먼저JTable에 사용할 모델(Model) 결정
			datamodel = new DefaultTableModel(data, colume_name);
			table = new JTable(datamodel);
			scrollPane = new JScrollPane(table);

			// 셀 크기 조절
			table.getColumnModel().getColumn(0).setPreferredWidth(50);
			table.getColumnModel().getColumn(1).setPreferredWidth(50);
			table.getColumnModel().getColumn(2).setPreferredWidth(50);

			// 이벤트 연결
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
