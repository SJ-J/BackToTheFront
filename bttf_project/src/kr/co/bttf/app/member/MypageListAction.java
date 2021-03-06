package kr.co.bttf.app.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bttf.DAO.MemberDAO;
import kr.co.bttf.DTO.Bookmark_DTO;
import kr.co.bttf.DTO.UserDTO;
import kr.co.bttf.action.Action;
import kr.co.bttf.action.ActionForward;

public class MypageListAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		MemberDAO mdao = new MemberDAO();
		UserDTO udto = (UserDTO)session.getAttribute("session_id");
		
		try {
		String user_id = udto.getUser_id();
		// totalCnt
		int totalCnt = mdao.getBookmarkCnt();
		
		// 게시글의 페이징 처리
		// 현재 넘겨받은 페이지
		String temp = request.getParameter("page");
		int page = 0;
		
		// 삼항연산자
		page = temp == null ? 1 : Integer.parseInt(temp);
		
		// 페이징 처리 사이즈
		int pageSize = 10000;
		
		// 1 페이지 endRow = 10, 4 페이지 endRow = 40
		int endRow = page * 10000;
		// 1 페이지 startRow = 1, 4 페이지 startRow = 31
		int startRow = endRow - 9999;
		
		// [1][2]...[10] : [1], [21],[22],...[30] :[21]
		int startPage = (page - 1) / pageSize * pageSize + 1;
		// [1][2]...[10] : [10], [21],[22],...[30] :[30]
		int endPage = startPage + pageSize - 1;
		int totalPage = (totalCnt - 1) / pageSize + 1;
		
		endPage = endPage > totalPage ? totalPage : endPage;
		
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("nowPage", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalCnt", totalCnt);
//		List<Bookmark_DTO> umm = mdao.getBookmarkList(user_id, startRow, endRow);
		
		request.setAttribute("bookmarkList", mdao.getBookmarkList(user_id, startRow, endRow));
		request.setAttribute("mypostList", mdao.getMyPostList(user_id, startRow, endRow));
		request.setAttribute("getMyPostCount", mdao.getMyPostCount(user_id));
		request.setAttribute("getMyReplyCount", mdao.getMyReplyCount(user_id));
		request.setAttribute("getMyRecomendCount", mdao.getMyRecomendCount(user_id));
		
		forward.setRedirect(false);
		forward.setPath(request.getContextPath() + "/app/pages/mypage.jsp");
			
		} catch (Exception e) {
			forward.setRedirect(false);
			forward.setPath(request.getContextPath() + "/app/pages/login.jsp");
		}
		
		return forward;
	}
	
}
