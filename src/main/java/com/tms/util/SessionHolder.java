/**
 * <p>Title: SessionHolder.java</p>
 */
package com.tms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * <p>Title: SessionHolder.java</p>
 */
public class SessionHolder {
	
	private static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<HttpServletRequest>();
	private static final String SESSION_MUTEX_ATTRIBUTE = SessionHolder.class.getName() + ".MUTEX";

	public static HttpSession getSession() {
		HttpServletRequest request = threadLocal.get();
		HttpSession session = request.getSession(false);
		if (session == null) {
			synchronized (request) {
				session = request.getSession(false);
				if (session == null) {
					session = request.getSession(true);
					session.setAttribute(SESSION_MUTEX_ATTRIBUTE, new Mutex());
				}
			}
		}
		return session;
	}

	public static boolean sessionExists() {
		return threadLocal.get().getSession(false) != null;
	}

	public static Object getMutex() {
		return getSession().getAttribute(SESSION_MUTEX_ATTRIBUTE);
	}

	public static void setRequest(HttpServletRequest request) {
		threadLocal.set(request);
	}

	private static class Mutex implements Serializable {

		/** serialVersionUID */
		private static final long serialVersionUID = -4692716214830591362L;
		
	}

}
