package ����������__������;

public class MainProcess {
	LoginView loginView;
	ClientManagementSystem clientManagementSystem;
	public static void main(String[] args) {
        // ����Ŭ���� ����
        MainProcess main = new MainProcess();
        main.loginView = new LoginView(); // �α���â ���̱�
        main.loginView.setMain(main); // �α���â���� ���� Ŭ����������
	}
    // �׽�Ʈ������â
    public void showFrameTest(){
        loginView.dispose(); // �α���â�ݱ�
        this.clientManagementSystem = new ClientManagementSystem(); // �׽�Ʈ������ ����
    }
}
