package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.dao.UserDAO;
import data.entity.Notification;
import data.entity.User;
import service.UserService;
import util.SessionCtl;

/**
 * Servlet implementation class Mail
 */
@WebServlet("/mail")
public class Mail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int count = 0;
		List<Notification> notifs;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedin_user");

		if (user != null) {
			UserDAO userDao = new UserDAO();
			user = userDao.getEntityById(user.getUser_id());
			notifs = user.getRecv_notif();
			for (Notification notif : notifs){
				if( notif.getRead().equals("N")) {
					count++;
				}
			}
		}
		
		String output = "{" 
			+ "\"count\":\"" + count 
			+"\"}";
		
		response.setContentType("json");
		PrintWriter pw = response.getWriter();
		pw.println(output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
