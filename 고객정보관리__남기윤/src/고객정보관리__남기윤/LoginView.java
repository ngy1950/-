package 고객정보관리__남기윤;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame{
    private MainProcess main;
    private ClientManagementSystem clientManagementSystem;
   
    private JButton btnLogin;
    private JButton btnInit;
    private JPasswordField passText;
    private JTextField userText;
    private boolean bLoginCheck;
    ImageIcon icon;
	public LoginView(){
		 icon = new ImageIcon("images/b.png");

        setTitle("login");
        setSize(900, 700);
        setResizable(false);
        setLocation(400, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // panel
        JPanel panel = new JPanel(){
            public void paintComponent(Graphics g) {
                // Approach 1: Dispaly image at at full size
                g.drawImage(icon.getImage(), 0, 0, null);
                // Approach 2: Scale image to size of component
                // Dimension d = getSize();
                // g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                // Approach 3: Fix the image position in the scroll pane
                // Point p = scrollPane.getViewport().getViewPosition();
                // g.drawImage(icon.getImage(), p.x, p.y, null);
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
        placeLoginPanel(panel);
  
        // add
        add(panel);
        
       
        // visiible
        setVisible(true);

	}
	   public void placeLoginPanel(JPanel panel){
	        panel.setLayout(null);     
	        JLabel userLabel = new JLabel("User");
	        userLabel.setBounds(350, 200, 80, 25);
	        panel.add(userLabel);
	       
	        JLabel passLabel = new JLabel("Pass");
	        passLabel.setBounds(350, 230, 80, 25);
	        panel.add(passLabel);
	       
	        userText = new JTextField(20);
	        userText.setBounds(380, 200, 160, 25);
	        panel.add(userText);
	       
	        passText = new JPasswordField(20);
	        passText.setBounds(380, 230, 160, 25);
	        panel.add(passText);
	        passText.addActionListener(new ActionListener() {          
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                isLoginCheck();        
	            }
	        });
	       
	        btnInit = new JButton("Reset");
	        btnInit.setBounds(350, 260, 100, 25);
	        panel.add(btnInit);
	        btnInit.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                userText.setText("");
	                passText.setText("");
	            }
	        });
	       
	        btnLogin = new JButton("Login");
	        btnLogin.setBounds(460, 260, 100, 25);
	        panel.add(btnLogin);
	        btnLogin.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                isLoginCheck();
	            }
	        });
	    }
	   
	    public void isLoginCheck(){
	        if(userText.getText().equals("test") && new String(passText.getPassword()).equals("1234")){
	            JOptionPane.showMessageDialog(null, "Success");
	            bLoginCheck = true;
	           
	            // 로그인 성공이라면 매니져창 뛰우기
	            if(isLogin()){
	                main.showFrameTest(); // 메인창 메소드를 이용해 창뛰우기
	            }                  
	        }else{
	            JOptionPane.showMessageDialog(null, "Faild");
	        }
	    }
	 
	   
	    // mainProcess와 연동
	    public void setMain(MainProcess main) {
	        this.main = main;
	    }
	   
	 
	    public boolean isLogin() {     
	        return bLoginCheck;
	    }
}
