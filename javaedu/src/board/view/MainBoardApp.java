package board.view;

import java.sql.Connection;
import java.util.Scanner;

import board.controller.BoardController;
import board.controller.Controller;
import board.controller.adminController;
import board.controller.memberController;
import board.model.DAO.Connection.ConnectDB;




public class MainBoardApp {

	public static void main(String[] args){
		Connection conn = ConnectDB.connect();
		Scanner scan = new Scanner(System.in);
		int[] list = new int[2];
		Controller member = new memberController(conn);
		Controller board = new BoardController(conn);
		Controller admin = new adminController(conn);
		String user ="";
		System.out.println("\n프로그램을 시작합니다.\n");
		main:while(true) {
			int fnum = mainScreen(scan);
			
			switch(fnum) {
			case 1:
				while(true) {
					if(list[0] == 0 && list[1] == 0) {
						int num =loginPage(scan);
						if(num == 1) {
							String[] list1 = login();
							String name = list1[0];
							String password = list1[1];
							try{
								member.Searching(name, password);
								list[0] = 1;
								user = name;
							}catch(Exception e){
								System.out.println("로그인 실패");
							}
							break;
						}else if(num == 2) {
							String Id = passSearch();
							member.Searching(Id, null);
							break;
						}else if(num == 3) {
							String[] list1 = register();
							String name = list1[0];
							String password = list1[1];
							member.Inserting(name,null, password);
							break;
						}else if(num == 4) {
							String[] list1 = login();
							String name = list1[0];
							String password = list1[1];
							try{
								admin.Searching(name, password);
								list[0] = 1;
								list[1] = 1;
								user = name;
							}catch(Exception e){
								System.out.println("로그인 실패");
							}
							break;
						}else if(num == 5) {
							break;
						}else {
						System.out.println("다시 입력해주세요. ");
						}
					}else if(list[0] == 1 && list[1] == 0){
						int num =loginPage2(scan);
						if(num == 1) {
							try{
								member.Listing(user);
							}catch(Exception e){
								System.out.println("내 정보 보기 실패");
							}
							break;
						}else if(num == 2) {
							System.out.println(user+"님이 로그아웃 하셨습니다. ");
							list[0] = 0;
							user = "";
							break;
						}else if(num == 3) {
							String aws = deleteID();
							if(aws != "") {
								member.Deleting(0, aws);
							}
							list[0] = 0;
							break;
						}else if(num == 4) {
							break;
						}else {
							System.out.println("다시 입력해주세요. ");
						}
					}else if(list[0] == 1 && list[1] == 1){
						int num =adminPage(scan);
						if(num == 1) {
							admin.Listing(user);
							break;
						}else if(num == 2) {
							System.out.println(user+"님이 로그아웃 하셨습니다. ");
							list[0] = 0;
							list[1] = 0;
							break;
						}else if(num == 3) {
							break;
						}else {
							System.out.println("다시 입력해주세요. ");
						}
					}
					
				}
				break;
			case 2:
				
				while(true) {
					int num =board(scan);
					if(num == 1) {
						board.Listing(user);
						break;
					}else if(num == 2) {
						if(list[0] == 1) {	
							String[] list1 = posting(user);
							String name = list1[0];
							String title = list1[1];
							String contents = list1[2];
							board.Inserting(name,title,contents);
						}else {
							System.out.println("로그인을 먼저 진행해 주세요.");
						}
						break;
					}else if(num == 3) {
						if(list[0] == 1) {
							System.out.print("수정하려는 글 번호를 적어 주세요 : ");
							int num2 = scan.nextInt();
							scan.nextLine();
							System.out.print("글 내용 : ");
							String content = scan.nextLine();
							board.Updating(num2, content);
						}else {
							System.out.println("로그인을 먼저 진행해 주세요.");
						}
						break;
					}else if(num == 4) {
						if(list[0] == 1) {
							int num1 = del_board();
							board.Deleting(num1, user);
						}else {
							System.out.println("로그인을 먼저 진행해 주세요.");
						}
						break;
					}else if(num == 5) {
							break;
					}else {
						System.out.println("다시 입력해주세요. ");
					}
				}
				break;
			case 3:
				scan.close();
				ConnectDB.close(conn);
				System.out.println("프로그램을 종료합니다. ");
				break main;
			default:
				System.out.println("다시 입력해 주세요");
			}
			
		}
	}
	public static int mainScreen(Scanner scan) {
		System.out.println("---------------------------");
		System.out.println("1. 로그인 화면");
		System.out.println("2. 게시판 보기 ");
		System.out.println("3. 프로그램 종료 ");
		System.out.println("---------------------------");
		System.out.print("원하는 메뉴의 번호 선택 :");
		int answer = scan.nextInt();
		return answer;
	}
	public static int loginPage(Scanner scan) {
		System.out.println("---------------------------");
		System.out.println("1. 로그인 ");
		System.out.println("2. 비밀번호 찾기 ");
		System.out.println("3. 회원가입 ");
		System.out.println("4. 관리자 로그인 ");
		System.out.println("5. 홈 화면으로 ");
		System.out.println("---------------------------");
		System.out.print("원하는 메뉴의 번호 선택 :");
		int answer = scan.nextInt();
		return answer;
	}
	public static int loginPage2(Scanner scan) {
		System.out.println("---------------------------");
		System.out.println("1. 내정보 보기 ");
		System.out.println("2. 로그아웃 ");
		System.out.println("3. 회원 탈퇴 ");
		System.out.println("4. 홈 화면으로 ");
		System.out.println("---------------------------");
		System.out.print("원하는 메뉴의 번호 선택 :");
		int answer = scan.nextInt();
		return answer;
	}
	
	public static int adminPage(Scanner scan) {
		System.out.println("---------------------------");
		System.out.println("1. 관리자 리스트 ");
		System.out.println("2. 로그아웃 ");
		System.out.println("3. 홈 화면으로 ");
		System.out.println("---------------------------");
		System.out.print("원하는 메뉴의 번호 선택 :");
		int answer = scan.nextInt();
		return answer;
	}
	
	public static int board(Scanner scan) {
		System.out.println("---------------------------");
		System.out.println("1. 글 보기 ");
		System.out.println("2. 글 쓰기 ");
		System.out.println("3. 글 수정 ");
		System.out.println("4. 글 삭제 ");
		System.out.println("5. 홈 화면으로 ");
		System.out.println("---------------------------");
		System.out.print("원하는 메뉴의 번호 선택 :");
		int answer = scan.nextInt();
		return answer;
	}
	
	public static String[] login() {
		String[] list = new String[2];
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디를 입력하세요(15자 이내) : ");
		String id = scan.next();
		System.out.print("패스워드를 입력하세요(30자 이내) : ");
		String password = scan.next();
		list[0] = id;
		list[1] = password;
		return list;
	}
	public static String[] register() {
		String[] list = new String[2];
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.print("아이디를 입력하세요(15자 이내) : ");
			String id = scan.next();
			System.out.print("패스워드를 입력하세요(30자 이내) : ");
			String password = scan.next();
			System.out.print("패스워드를 다시한번 입력해주세요 : ");
			String repassword = scan.next();
			if(password.equals(repassword)) {
				list[0] = id;
				list[1] = password;
				return list;
			}else {
				System.out.println("패스워드가 동일하지 않습니다. ");
			}
		}
		
	}
	public static String passSearch() {
		String id;
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디를 입력하세요(15자 이내) : ");
		id = scan.next();
		return id;
	}
	public static String deleteID() {
		String id;
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제할 아이디를 입력하세요(15자 이내) : ");
		id = scan.next();
		System.out.print("정말로 삭제하시겠습니까? (Y/N) : ");
		String aws = scan.next();
		if(aws.equals("Y") || aws.equals("y")) {
			return id;
		}else {
			return "";
		}
		
	}
	public static String[] posting(String id) {
		String[] list = new String[3];
		Scanner scan = new Scanner(System.in);
		System.out.print("글 제목을 적어 주세요 : ");
		String title = scan.nextLine();
		System.out.print("글 내용을 적어 주세요 : ");
		String post = scan.nextLine();
		list[0] = id;
		list[1] = title;
		list[2] = post;
		return list;
		}
	
	public static int del_board() {
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제할 글 번호를 적어 주세요 : ");
		int num = scan.nextInt();
		return num;
		}
}
