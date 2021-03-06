package com.jingtong.platform.framework.content.interceptor;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class EncodingInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6889956887525002576L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		ActionContext actionContext = arg0.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);
		if (request.getMethod().compareToIgnoreCase("post") >= 0) {
			try {
				request.setCharacterEncoding("GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			Iterator iter = request.getParameterMap().values().iterator();
			while (iter.hasNext()) {
				String[] parames = (String[]) iter.next();
				for (int i = 0; i < parames.length; i++) {
					try {
						parames[i] = new String(
								parames[i].getBytes("iso8859-1"), "GBK");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return arg0.invoke();
	}
}
