package 고객정보관리__남기윤;

public class MainProcess {
	LoginView loginView;
	ClientManagementSystem clientManagementSystem;
	public static void main(String[] args) {
        // 메인클래스 실행
        MainProcess main = new MainProcess();
        main.loginView = new LoginView(); // 로그인창 보이기
        main.loginView.setMain(main); // 로그인창에게 메인 클래스보내기
	}
    // 테스트프레임창
    public void showFrameTest(){
        loginView.dispose(); // 로그인창닫기
        this.clientManagementSystem = new ClientManagementSystem(); // 테스트프레임 오픈
    }
}
