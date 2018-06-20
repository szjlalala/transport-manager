/**
 * <p>Title: SessionHolder.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016, zj7687362@gmail.com All Rights Reserved. </p>
 * <p>Company: www.jcore.cn</p>
 * @author 张嘉杰
 * @date 2016年5月25日 下午2:45:27
 */
package com.tms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * <p>Title: SessionHolder.java</p>
 * <p>Description: </p>
 * @author 张嘉杰
 * @date 2016年5月25日 下午2:45:27
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
