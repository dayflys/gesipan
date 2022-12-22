package board.controller;

import java.sql.Connection;
import java.util.List;

import board.model.DAO.memberDAO;
import board.model.VO.memberVO;

public class memberController implements Controller {
	
	private memberDAO dao;
	public memberController(Connection conn) {
		dao = new memberDAO(conn);	
	}
	@Override
	public void Inserting(String name,String title, String password) {
		memberVO vo = new memberVO();
		vo.setName(name);
		vo.setPassword(password);
		
		boolean result = dao.insert(vo);
		if (result)
			System.out.println("board 저장 성공");
		else 
			System.out.println("board 저장 실패");
		
	}

	@Override
	public void Listing(String name) {
		List<memberVO> list = dao.list(name); 
		for(memberVO vo : list) {
			System.out.println("아이디 :"+vo.getName()+"\t");
			System.out.println("가입 날짜 :"+vo.getCurdate()+"\t");
		}
	}

	@Override
	public void Searching(String name,String password) {
		List<memberVO> list = dao.search(name,password); 
		for(memberVO vo : list) {
			System.out.print((password != null) ? vo.getName()+"님이 로그인 하셨습니다.\n":vo.getName()+"님의 비밀번호는 "+vo.getPassword()+"입니다.\n" );
		}
	}


	public void Updating(String name, String password) {
		memberVO vo = new memberVO();
		vo.setName(name);
		vo.setPassword(password);
		boolean result = dao.update(vo);
		if (result)
			System.out.println("board 수정 성공");
		else 
			System.out.println("board 수정 실패"); 
		
	}

	@Override
	public void Deleting(int id, String name) {
		boolean result = dao.delete(0, name);
		if (result)
			System.out.println("삭제 성공");
		else 
			System.out.println("삭제 실패");
	}
	@Override
	public void Updating(int id, String content) {
		// TODO Auto-generated method stub
		
	}

}
