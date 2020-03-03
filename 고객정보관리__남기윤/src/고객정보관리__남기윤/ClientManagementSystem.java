package 고객정보관리__남기윤;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ClientManagementSystem extends JFrame {
	public String[] comboBox = { "선택", "학생", "직장인", "교수", "승무원", "주부" };
	public String[] colum = { "번호", "이름", "핸드폰번호", "E-Mail", "주민등록번호", "나이",
			"성별", "출신도", "생일", "직업" };
	public JTextField[] text = new JTextField[5];
	public DefaultTableModel model = new DefaultTableModel();
	public Vector col = new Vector();
	public Vector data = new Vector();
	public Vector select_data = new Vector();
	public int pageNo = 0;
	public JTable jtab = new JTable(model);
	public JLabel[] jl_data = new JLabel[4];
	public JComboBox jcom;
	Input in = new Input();
	int search_check = 0;
	CardLayout card; 
	JButton[] jb = new JButton[6];

	// ClientManagementSystem 생성자
	ClientManagementSystem() {
		setcol();
		showdata();
		
		add(in, BorderLayout.WEST);
		add(new menu(), BorderLayout.NORTH);
		add(new Table(), BorderLayout.CENTER);
		add(new Button(), BorderLayout.SOUTH);
		setTitle("고객정보관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1800, 800);
		setVisible(true);
	} // end ClientManagementSystem()

	// 신상정보,입력 panel
	class Input extends JPanel {
		JPanel input = new JPanel(); // 정보입력판넬
		// JPanel info = new JPanel(); //신상정보, 검색 판넬
		JLabel jl;
		search searc =new search();
		String[] in_data = { "번호", "이름", "핸드폰번호", "이메일", "주민등록번호", "직업" };

		TitledBorder inputPan = new TitledBorder(new LineBorder(Color.red),
				"입력");

		public Input() {
			setLayout(new GridLayout(2, 0));
			input.setLayout(new GridLayout(7, 2));
			input.setBorder(inputPan);
			for (int i = 0; i < in_data.length - 1; i++) {
				jl = new JLabel(in_data[i], SwingConstants.CENTER);
				input.add(jl);

				text[i] = new JTextField(15);
				input.add(text[i]);
			}
			input.add(new JLabel("직업", SwingConstants.CENTER));
			jcom = (JComboBox) (input.add(new JComboBox(comboBox)));
			add(input);
			add(searc);

		}

		class search extends JPanel implements ActionListener, ItemListener{
			JPanel p = new JPanel();
			JPanel search = new JPanel();
			ButtonGroup group;
			String[] if_data = { "나이:", "성별:", "출신도:", "생일:" };
			String[] radio_str = { "이름", "출신도", "직업" };
			TitledBorder infor_border = new TitledBorder(new LineBorder(
					Color.blue), "신상정보");
			TitledBorder search_border = new TitledBorder(new LineBorder(
					Color.blue), "정보검색");
			JButton searchBtn = new JButton("찾기");
			JButton exitBtn = new JButton("나가기");
			JTextField txt = new JTextField(10);
			JRadioButton search_jrb[] = new JRadioButton[3];
			String find_text;
			String find_col;
			public search() {
				card = new CardLayout();
				setLayout(card);

				p.setLayout(new GridLayout(4, 2));
				p.setBorder(infor_border);
				for (int i = 0; i < if_data.length; i++) {
					p.add(new JLabel(if_data[i]));
					jl_data[i] = new JLabel();
					p.add(jl_data[i]);
				}
				add(p);

				int x = -70;
				search.setLayout(null);
				search.setBorder(infor_border);
				group = new ButtonGroup();
				
				for (int i = 0; i < search_jrb.length; i++) {
					search_jrb[i] = new JRadioButton(radio_str[i]);
					search_jrb[i].setBounds(x = x + 105, 30, 65, 50);
					group.add(search_jrb[i]);
					search.add(search_jrb[i]);
					search_jrb[i].addItemListener(this);
				}
				txt.setBounds(25, 100, 280, 40);
				searchBtn.setBounds(25, 180, 120, 40);
				exitBtn.setBounds(182, 180, 120, 40);
				search.add(txt);
				search.add(searchBtn);
				search.add(exitBtn);
				searchBtn.addActionListener(this);
				exitBtn.addActionListener(this);
				add(search, "정보검색카드");
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == exitBtn){
					card.next(this);
					search_check = 0;
					showdata();
				}else if(e.getSource() == searchBtn){
					findData(find_col, txt.getText().toString());
					search_check = 1;
				}
			}
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource() == search_jrb[0]){
					find_col = "name";
				}else if(e.getSource() == search_jrb[1]){
					find_col = "home";
				}else if(e.getSource() == search_jrb[2]){
					find_col = "jon";
				}
			}
		}
	} // end class Input

	class Table extends JPanel {
		TitledBorder table = new TitledBorder(new LineBorder(Color.DARK_GRAY));

		public Table() {
			table.setTitleColor(Color.DARK_GRAY);
			setLayout(new GridLayout(1, 0));
			setBounds(0, 0, 500, 300);
			setBorder(table);
			model = new DefaultTableModel(data, col);
			jtab = new JTable(model);
			JScrollPane scoll = new JScrollPane(jtab);
			add(scoll);
			text_center();
			
			jtab.addMouseListener(new Mouse_click());
		}

		// ========Table 선택 셀 담기========== //
		class Mouse_click extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					select_data.clear();
					for (int i = 0; i < colum.length; i++)
						select_data.addElement(jtab.getValueAt(
								jtab.getSelectedRow(), i));
					setText();
				}
			}
		} // end class Mouse_click
	} // end class Table

	class menu extends JPanel implements ActionListener{
		JFrame main_fr = new JFrame("메뉴만들기");
		JMenuBar mb;
		JMenu file, sort, help;
		JMenuItem fopen, fsave, fexit, proinfo;
		JCheckBoxMenuItem sno, sname, schul, sjob;
		public menu() {
			mb = new JMenuBar();
			
			file = new JMenu("파일");
			sort = new JMenu("정렬");
			help = new JMenu("도움말");

			fopen = new JMenuItem("열기");
			fsave = new JMenuItem("저장");
			fexit = new JMenuItem("닫기");

			sno = new JCheckBoxMenuItem("번호");
			sname = new JCheckBoxMenuItem("이름");
			schul = new JCheckBoxMenuItem("출신");
			sjob = new JCheckBoxMenuItem("직업");

			proinfo = new JMenuItem("프로그램 정보");
			file.add(fopen);
			file.add(fsave);
			file.add(fexit);
			sort.add(sno);
			sort.add(sname);
			sort.add(schul);
			sort.add(sjob);
			help.add(proinfo);

			mb.add(file);
			mb.add(sort);
			mb.add(help);
			setJMenuBar(mb);
			sno.addActionListener(this);sname.addActionListener(this);
			schul.addActionListener(this);sjob.addActionListener(this);
			sno.getSelectedObjects();
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == sno) {
				showdata(pageNo, "num");
				sname.setState(false);schul.setState(false);sjob.setState(false);
			}else if(e.getSource() == sname){
				showdata(pageNo, "name");
				sno.setState(false);schul.setState(false);sjob.setState(false);
			}else if(e.getSource() == schul){
				showdata(pageNo, "home");
				sno.setState(false);sname.setState(false);sjob.setState(false);
			}else if(e.getSource() == sjob){
				showdata(pageNo, "jon");	
				sno.setState(false);schul.setState(false);sname.setState(false);
			}
		}
	}

	class Button extends JPanel implements ActionListener {
		String[] jb_text = { "추가", "삭제", "이전 페이지", "다음 페이지", "수정", "검색" };
		Button() {
			setLayout(new GridLayout(1, 6));
			for (int i = 0; i < jb_text.length; i++) {
				jb[i] = new JButton(jb_text[i]);
				add(jb[i]);
				jb[i].addActionListener(this);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == jb_text[0]) {
				if (check_data(text[text.length - 1].getText()) == true) {
					DBManger dbmanager = new DBManger();
					dbmanager.insert(getText());
					showdata(); // Table의 데이터 조회
					clear();
				}
			}
			else if (e.getActionCommand() == jb_text[1]) {
				DBManger dbmanager = new DBManger();
				dbmanager.delete(select_data.get(0));
				showdata(); // Table의 데이터 조회
				clear();
			} else if (e.getActionCommand() == jb_text[2]) {
				if (pageNo != 0) {
					pageNo--;
					showdata(); // Table의 데이터 조회
				}
			} else if (e.getActionCommand() == jb_text[3]) {
				pageNo++;
				showdata(); // Table의 데이터 조회
			} else if (e.getActionCommand() == jb_text[4]) {
				DBManger dbmanager = new DBManger();
				dbmanager.update(getText(), select_data.get(0));
				showdata(); // Table의 데이터 조회
				clear();
			} else if (e.getActionCommand() == jb_text[5]) {
				card.next(in.searc);
			}
		}
	}

	// Table의 데이터 값을 조회하는 함수
	void showdata(int pageNo, String sort) {
		DBManger dbmanger = new DBManger();
		data = dbmanger.showdata(pageNo, sort);

		model.setDataVector(data, col);
		text_center();
	} // end showdata()
	void showdata() {
		DBManger dbmanger = new DBManger();
		data = dbmanger.showdata(pageNo);
		model.setDataVector(data, col);
		text_center();
	} // end showdata()
	void findData(String find, String text){
		DBManger dbmanger = new DBManger();
		data = dbmanger.showdata(pageNo, find, text);
		model.setDataVector(data, col);
		text_center();
	}
	// Table 컴럼 지정 함수
	void setcol() {
		for (int i = 0; i < colum.length; i++)
			col.addElement(colum[i]);
	}
	void text_center(){
		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		
		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcmSchedule = jtab.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
			if(i==0 || i==1 || i==5 || i==6 || i==7 || i==9){
				jtab.getColumnModel().getColumn(i).setPreferredWidth(50);
			}else{
				jtab.getColumnModel().getColumn(i).setPreferredWidth(150);
			}
		}
	}
	// 주민번호 확인
	boolean check_data(String pin) {
		int sum = 0, temp;
		boolean t = false;
		for (int i = 0, x = 2; i < pin.length() - 1; i++, x++) {
			if (pin.charAt(i) == '-')
				x--;
			else
				sum += Integer.parseInt(pin.substring(i, i + 1)) * x;
			if (x == 9)
				x = 1;
		}
		temp = 11 - (sum % 11);
		if ((int) (pin.charAt(pin.length() - 1) - 48) == temp % 10)
			t = true;
		else {
			DBManger dbmanger = new DBManger();
			dbmanger.show_error(1);
		}
		return t;
	} // end check_data
	// 텍스트 필드 초기화 함수
	void clear() {
		for (int i = 0; i < text.length; i++) {
			text[i].setText("");
		}
		for (int i = 0; i < jl_data.length; i++) {
			jl_data[i].setText("");
		}
	} // end clear()
	
	void setText() {
		for (int i = 0; i < text.length; i++) {
			text[i].setText((String) (select_data.get(i).toString().replaceAll("[-]", "")));
		}
		for (int i = 0; i < jl_data.length; i++) {
			jl_data[i].setText((String) (select_data.get(i + text.length).toString().replaceAll("[.]", "")));
		}
		jcom.setSelectedItem(select_data.get(9));
	} // end setText()

	Vector getText() {
		Vector dd = new Vector();
		for (int i = 0; i < text.length; i++) {
			if (i == 2 || i == 4)
				dd.addElement(text[i].getText().replaceAll("[-]", ""));
			else if(i == 8)
				dd.addElement(text[i].getText().replaceAll("[.]", ""));
			else
				dd.addElement(text[i].getText());

		}
		
		dd.addElement(jcom.getSelectedItem().toString());
		return dd;
	} // end getText()
	
	/*
	public static void main(String[] args) {
		new ClientManagementSystem();
		
	}
	*/
}
