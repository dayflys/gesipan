package board.controller;

import java.sql.Connection;
import java.util.List;

import board.model.DAO.boardDAO;
import board.model.VO.boardVO;

public class BoardController implements Controller {
	private boardDAO dao;
	public BoardController(Connection conn) {
		dao = new boardDAO(conn);	
	}
	
	public void Inserting(String name,String title, String content) {
		boardVO vo = new boardVO();
		vo.setName(name);
		vo.setTitle(title);
		vo.setContents(content);
		
		boolean result = dao.insert(vo);
		if (result)
			System.out.println("board 저장 성공");
		else 
			System.out.println("board 저장 실패");
		
	}
	
	
	public void Listing(String name) {
		List<boardVO> list = dao.list(name); 
		for(boardVO vo : list) {
			System.out.print(vo.getId()+"\t");
			System.out.print(vo.getName()+"\t");
			System.out.print(vo.getTitle()+"\t");
			System.out.print(vo.getCurdate()+"\t");
			System.out.println(vo.getContents());
		}		
	}
	public void Searching(String name,String password) {
		List<boardVO> list = dao.search(name,null); 
		for(boardVO vo : list) {
			System.out.print(vo.getId()+"\t");
			System.out.print(vo.getName()+"\t");
			System.out.print(vo.getTitle()+"\t");
			System.out.print(vo.getCurdate()+"\t");
			System.out.println(vo.getContents());
		}	
	}

	public void Updating(int id, String content) {
		boardVO vo = new boardVO();
		vo.setId(id);
		vo.setContents(content);
		boolean result = dao.update(vo);
		if (result)
			System.out.println("board 수정 성공");
		else 
			System.out.println("board 수정 실패"); 
		
	}

	public void Deleting(int id,String name) {
		boolean result = dao.delete(id, name);
		if (result)
			System.out.println("삭제 성공");
		else 
			System.out.println("삭제 실패");
	}
}
