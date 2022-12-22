package board.controller;

import java.sql.Connection;
import java.util.List;

import board.model.DAO.adminDAO;
import board.model.VO.adminVO;


public class adminController implements Controller {
	private adminDAO dao;
	public adminController(Connection conn) {
		dao = new adminDAO(conn);	
	}
	@Override
	public void Inserting(String name,String title, String content) {
		adminVO vo = new adminVO();
		vo.setName(name);
		vo.setAdmin(true);
		
		boolean result = dao.insert(vo);
		if (result)
			System.out.println("board 저장 성공");
		else 
			System.out.println("board 저장 실패");
		
	}

	
	public void Listing() {
		List<adminVO> list = dao.list(); 
		for(adminVO vo : list) {
			System.out.print(vo.getName()+"\n");
		}	
		
	}

	@Override
	public void Searching(String name,String password) {
		List<adminVO> list = dao.search(name,password); 
		for(adminVO vo : list) {
			System.out.print( vo.getName()+" 관리자 님이 로그인 하셨습니다.\n" );
		}
		
	}

	
	public void Updating(String name, String content ) {
		adminVO vo = new adminVO();
		vo.setName(name);
		vo.setAdmin(!vo.isAdmin());
		boolean result = dao.update(vo);
		if (result)
			System.out.println("admin 수정 성공");
		else 
			System.out.println("admin 수정 실패"); 
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
	@Override
	public void Listing(String id) {
		List<adminVO> list = dao.list(); 
		for(adminVO vo : list) {
			System.out.print(vo.getName()+"\n");
		}
		
	}


}
