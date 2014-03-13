package util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;
import data.entity.User;

public class SessionCtl {
	
	static final UserService userService = new UserService();
	/**
	 * Log in a user 
	 *   1. Clear old session
	 *   2. Get email and password from request object
	 *   3. Check and update user by calling UserDAO
	 *   4. Return a boolean to indicate the result
	 * @param request
	 * @param response
	 */
	public static boolean login(HttpServletRequest request,
			HttpServletResponse response) {

		// Clear old session
		request.getSession().invalidate();
		HttpSession session = request.getSession();
		
		// Check DB		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == null)
			username = "";
		if (password == null)
			password = "";

		// Using UserDAO to check login status
		// - If logged in successfully,
		// 1.store the user bean in current session
		// 2.store new session id into database
		User user = userService.login(username, password, session.getId());

		if (user != null) {
			// Add user information to session and cookie
			session.setAttribute("loggedin_user", user);
			addCookie(response, "JSESSIONID", session.getId());
			addCookie(response, "ACCOUNT", user.getEmail());			
			return true;
		} else {
			// Failed to log in.
			request.setAttribute("err_msg",
					"Username and password are incorrect.");	
			return false;	
		}
	}

	/**
	 * Log out current user.
	 *   1. Clear session id from database
	 *   2. Clear current session
	 * @param request
	 * @param response
	 */
	public static void logout(HttpServletRequest request,
			HttpServletResponse response) {
		// Update session id in database
		User user = (User) request.getSession().getAttribute("loggedin_user");
		userService.logout(user);
		
		// Clear old session
		request.getSession().invalidate();
	}

	/**
	 * For auto-login feature
	 *   1. Fetch JSESSIONID and email from cookie
	 *   2. Compare with database record by calling UserDAO
	 *   3. If matches, log in and return true
	 *   4. Otherwise, clear cookie and return false
	 * @param request
	 * @param response
	 */
	public static boolean checkLogIn(HttpServletRequest request,
			HttpServletResponse response) {
		
		User user = null;
		HttpSession session = request.getSession();
		
		user = (User) session.getAttribute("loggedin_user");
		if(user != null)
			return true;

		// Get JSESSIONID and email from Cookie
		String sessionId = getCookieValue(request, "JSESSIONID");
		String email = getCookieValue(request, "ACCOUNT");

		// Logged-in status checking
		user = userService.checkAutoLogin(email, sessionId, session.getId());
			
		if (user != null) {
			// Add user information to session and cookie
			session.setAttribute("loggedin_user", user);
			addCookie(response, "JSESSIONID", session.getId());
			addCookie(response, "ACCOUNT", user.getEmail());
			
			return true;
		} else {
			// Failed to log in.
			return false;
		}
	}

	// /
	// Cookie methods
	// /
	private static String getCookieValue(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (key.equals(cookie.getName()))
					return cookie.getValue();
			}
		}

		return null;
	}

	private static void addCookie(HttpServletResponse response, String key,
			String value) {
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
		response.addCookie(cookie);
	}

}
