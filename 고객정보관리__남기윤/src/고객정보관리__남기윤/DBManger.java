package ����������__������;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JOptionPane;

public class DBManger {
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	Statement state = null;
	Connection conn = null;
	PreparedStatement pstm = null;
	Vector data = new Vector();
	public DBManger() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tt?serverTimezone=UTC", "root",
					"qwert12345");
			state = conn.createStatement();
		} catch (Exception e) {e.printStackTrace();}
	}
	Vector showdata(int pageNo) {
		try {
			String [] col_name = {"num","name","phone_number","e_mail","p_pin","age","sex","home","birth","jon"};
			data.clear();
			state = conn.createStatement();
			String sql;
			sql = "SELECT * FROM manage order by num * 1 limit " + pageNo * 20 + "," + (pageNo * 20 + 20);		

			ResultSet rs = state.executeQuery(sql); // sql ���� ����� ������ ����
			while (rs.next()) {
				Vector vector = new Vector(); // ��ȸ �� ���� ���� ���� ����
				for(int i=0;i<col_name.length;i++){
					if(i==2){
						StringBuffer str = new StringBuffer(rs.getString(col_name[i]));
						str.insert(3, "-");
						str.insert(8, "-");
						vector.addElement(str);
					}else if(i==4){
						StringBuffer str = new StringBuffer(rs.getString(col_name[i]));
						str.insert(6, "-");
						str.delete(8, 13);
						str.insert(9, "*");str.insert(9, "*");str.insert(9, "*");str.insert(9, "*");str.insert(9, "*");
							
						vector.addElement(str);
					}
					else if(i==8){
						StringBuffer str = new StringBuffer(rs.getString(col_name[i]));
						str.insert(2, ".");
						str.insert(5, ".");
						vector.addElement(str);
					}
					else{
						vector.addElement(rs.getString(col_name[i]));	
					}
				}
				data.addElement(vector); // ��ȸ �� ���� ���� vector ������ data ���ͺ����� ����
			}
			rs.close(); // ��ü ���� �������� ��ü �Ҹ�
			state.close();
			conn.close();
		} catch (Exception e) {e.printStackTrace();} 
		finally {
			try {
				if (state != null) state.close();
			} catch (SQLException e) {e.printStackTrace();}
		}
		return data;
	}
	Vector showdata(int pageNo, String sort) {
		try {
			String [] col_name = {"num","name","phone_number","e_mail","p_pin","age","sex","home","birth","jon"};
			data.clear();
			state = conn.createStatement();
			String sql;
			if(sort.equals("num")){
				sql = "SELECT * FROM manage order by " + sort + " * 1 limit " + pageNo * 20 + "," + (pageNo * 20 + 20);
			}else{
				sql = "SELECT * FROM manage order by " + sort + " asc limit " + pageNo * 20 + "," + (pageNo * 20 + 20);
			}
			ResultSet rs = state.executeQuery(sql); // sql ���� ����� ������ ����
			while (rs.next()) {
				Vector vector = new Vector(); // ��ȸ �� ���� ���� ���� ����
				for(int i=0;i<col_name.length;i++){
					if(i==2){
						StringBuffer str = new StringBuffer(rs.getString(col_name[i]));
						str.insert(3, "-");
						str.insert(8, "-");
						vector.addElement(str);
					}else if(i==4){
						StringBuffer str = new StringBuffer(rs.getString(col_name[i]));
						str.insert(6, "-");
						str.delete(8, 13);
						str.insert(9, "*");str.insert(9, "*");str.insert(9, "*");str.insert(9, "*");str.insert(9, "*");
							
						vector.addElement(str);
					}
					else if(i==8){
						StringBuffer str = new StringBuffer(rs.getString(col_name[i]));
						str.insert(2, ".");
						str.insert(5, ".");
						vector.addElement(str);
					}
					else{
						vector.addElement(rs.getString(col_name[i]));	
					}
				}
				data.addElement(vector); // ��ȸ �� ���� ���� vector ������ data ���ͺ����� ����
			}
			rs.close(); // ��ü ���� �������� ��ü �Ҹ�
			state.close();
			conn.close();
		} catch (Exception e) {e.printStackTrace();} 
		finally {
			try {
				if (state != null) state.close();
			} catch (SQLException e) {e.printStackTrace();}
		}
		return data;
	}
	Vector showdata(int pageNo, String find, String text) {
		try {
			String [] col_name = {"num","name","phone_number","e_mail","p_pin","age","sex","home","birth","jon"};
			data.clear();
			state = conn.createStatement();
			String sql;
			sql = "SELECT * " + "FROM manage where "+find+" LIKE '%"+text +"%' order by num * 1 limit " + pageNo * 20 + "," + (pageNo * 20 + 20);

			ResultSet rs = state.executeQuery(sql); // sql ���� ����� ������ ����
			while (rs.next()) {
				Vector vector = new Vector(); // ��ȸ �� ���� ���� ���� ����
				for(int i=0;i<col_name.length;i++){
					if(i==2){
						StringBuffer str = new StringBuffer(rs.getString(col_name[i]));
						str.insert(3, "-");
						str.insert(8, "-");
						vector.addElement(str);
					}else if(i==4){
						StringBuffer str = new StringBuffer(rs.getString(col_name[i]));
						str.insert(6, "-");
						str.delete(8, 13);
						str.insert(9, "*");str.insert(9, "*");str.insert(9, "*");str.insert(9, "*");str.insert(9, "*");
							
						vector.addElement(str);
					}
					else if(i==8){
						StringBuffer str = new StringBuffer(rs.getString(col_name[i]));
						str.insert(2, ".");
						str.insert(5, ".");
						vector.addElement(str);
					}
					else{
						vector.addElement(rs.getString(col_name[i]));	
					}
				}
				data.addElement(vector); // ��ȸ �� ���� ���� vector ������ data ���ͺ����� ����
			}
			rs.close(); // ��ü ���� �������� ��ü �Ҹ�
			state.close();
			conn.close();
		} catch (Exception e) {e.printStackTrace();} 
		finally {
			try {
				if (state != null) state.close();
			} catch (SQLException e) {e.printStackTrace();}
		}
		return data;
	}

	void insert(Vector dd) {
		try {
			state = conn.createStatement();
			info(dd);
			String sql;
			sql = "INSERT INTO manage VALUES(\"" + data.get(0) + "\",\"" + data.get(1) + "\",\"" + data.get(2) + "\",\""
					+ data.get(3) + "\",\"" + data.get(4) + "\",\"" + data.get(6) + "\",\"" + data.get(7) + "\",\"" + data.get(8)
					+ "\",\"" + data.get(9) + "\",\"" + data.get(5) + "\"" + ");";
			int rs = state.executeUpdate(sql); // sql ���� ����� ������ ����
			state.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			show_error(3);
		} 
		finally {
			try {
				if (state != null) state.close();
			} catch (SQLException e) {e.printStackTrace();}
		}
	}

	void delete(Object object) {
		try {
			state = conn.createStatement();
			String sql;
			sql = "delete from manage where num= " + object + ";";
			int rs = state.executeUpdate(sql); // sql ���� ����� ������ ����
			state.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			show_error(3);
		}
		finally {
			try {
				if (state != null) state.close();
			} catch (SQLException e) {e.printStackTrace();}
		}
	}

	void update(Vector dd, Object pk) {
		try {
			state = conn.createStatement();
			String sql;
			sql = "update manage set num=" + dd.get(0) + ", name=\"" + dd.get(1) + "\", phone_number=\"" + dd.get(2)
					+ "\", e_mail=\"" + dd.get(3) + "\", p_pin=\"" + dd.get(4) + "\", jon=\"" + dd.get(5)
					+ "\" where num=" + pk;
			int rs = state.executeUpdate(sql); // sql ���� ����� ������ ����
			state.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			show_error(3);
		} finally {
			try {
				if (state != null) state.close();
			} catch (SQLException e) {e.printStackTrace();}
		}
	}

	void info(Vector dd) {
		data.clear();
		for(int i=0;i<dd.size();i++) {
			data.addElement(dd.get(i));
		}
		String[][] localCode = { { "����", "00", "08" }, { "�λ�", "09", "12" }, { "��õ", "13", "15" },
				{ "��⵵ �ֿ䵵��", "16", "18" }, { "��⵵�� �� �ֿ䵵��", "19", "25" }, { "������", "26", "34" },
				{ "��û�ϵ�", "35", "39" }, { "��û����", "40", "47" }, { "����ϵ�", "48", "54" }, { "���󳲵�", "55", "66" },
				{ "���", "67", "90" }, };
			String confirm = dd.get(4).toString(); // �ֹε�� ��ȣ ������ ���� �� ����, ���� ���� �Ǵ�
			int year = Calendar.getInstance().get(Calendar.YEAR); // ���� �⵵ ����
			int age = Integer.parseInt(confirm.substring(1, 3)); //
			if (Integer.parseInt(confirm.substring(0, 1)) == 0) { // 99��� ���� ���̰��
				String agee = "20" + confirm.substring(0, 2);
				data.addElement(year - Integer.parseInt(agee));
			} else if (Integer.parseInt(confirm.substring(0, 1)) != 0) { // 00��� ���� ���� ���
				String agee = "19" + confirm.substring(0, 2);
				data.addElement(year - Integer.parseInt(agee));
			}

			// ����Ȯ��
			if (confirm.substring(6, 7).equals("1") || confirm.substring(6, 7).equals("3"))
				data.addElement("����");
			else if (confirm.substring(6, 7).equals("2") || confirm.substring(6, 7).equals("4"))
				data.addElement("����");

			// ����Ȯ��
			String localNo = confirm.substring(7, 9);
			int local = Integer.parseInt(localNo);
			for (int i = 0; i < localCode.length; i++) {
				if (local <= Integer.parseInt(localCode[i][2])) {
					data.addElement(localCode[i][0]);
					break;
				}
			}	
			data.addElement(confirm.substring(0, 6));
	}
	void show_error(int t) {
		switch (t) {
		case 1:
			JOptionPane.showMessageDialog(null, "�ֹι�ȣ ����ġ", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "�̸��� ������ �����ּ���", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "����", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}
}
