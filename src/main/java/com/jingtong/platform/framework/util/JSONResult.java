package com.jingtong.platform.framework.util;

import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.jingtong.platform.framework.annotations.JsonResult;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.util.OgnlUtil;

/**
 * JSON Result
 * 
 * Two json format: 1. [ {...}, {...} ] or 2. { values: [ {...}, {...} ], total
 * : n }
 * 
 * @author tingjia.chentj
 * 
 */
public class JSONResult implements Result {

	private String APPLICATION_JSON = "application/json";

	/**
	 * Charset
	 */
	protected String charset;

	/**
	 * 
	 * @param charset
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * 
	 * @see com.opensymphony.xwork.Result#execute(com.opensymphony.xwork.ActionInvocation)
	 */
	 
	public void execute(ActionInvocation invocation) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		// 设置ContentType
		StringBuffer contentType = new StringBuffer();
		contentType.append(APPLICATION_JSON);
		contentType.append(isLegalCharSet() ? ("; charset=" + charset)
				: "; charset=utf-8");
		response.setContentType(contentType.toString());

		Object action = invocation.getAction();
		Method method = null;
		try {
			method = action.getClass().getDeclaredMethod(
					invocation.getProxy().getMethod(), new Class[0]);
		} catch (Exception e) {
			method = action.getClass().getDeclaredMethod(
					"do"
							+ invocation.getProxy().getMethod().substring(0, 1)
									.toUpperCase()
							+ invocation.getProxy().getMethod().substring(1),
					new Class[0]);
		}
		StringBuffer json = new StringBuffer();
		JsonResult result = method.getAnnotation(JsonResult.class);
		if (result != null) {
			Field field = action.getClass().getDeclaredField(result.field());
			field.setAccessible(true);
			Object obj = transToJSONObject(field.getType(), field.get(action),
					result);
			if (obj != null) {
				if (String.class.isAssignableFrom(obj.getClass())) {
					obj = JSONObject.quote((String) obj);
				}
				json.append(obj);
			}
			String fieldName = result.total();
			String type = result.type();
			String format = result.responseFormat();

			if (!"".equals(fieldName)) {
				Field total = action.getClass()
						.getDeclaredField(result.total());
				total.setAccessible(true);
				json.insert(0, "\"rows\":");
				json.insert(0, "{\"total\":" + total.get(action) + ",");
				json.append("}");
			}
			if (!"".equals(type)) {
				StringBuffer typesb = new StringBuffer();
				typesb.append("{").append(type).append(":true,data:")
						.append(json).append("}");
				json = typesb;
			}
			if ("jsonp".equals(format)) {
				StringBuffer formatsb = new StringBuffer();
				formatsb.append("callback").append("(").append(json)
						.append(")");
				json = formatsb;
			}
		}

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}

	private Object transToJSONObject(Class<?> cls, Object value)
			throws Exception {
		return transToJSONObject(cls, value, null);
	}

	// 将value转换成JSONObject
	private Object transToJSONObject(Class<?> cls, Object value,
			JsonResult tojson) throws Exception {
		Object json = null;
		if (value != null) {
			if (Collection.class.isAssignableFrom(cls)) {
				// 列表
				Iterator iter = ((Collection) value).iterator();
				JSONArray array = new JSONArray();
				while (iter.hasNext()) {
					Object element = iter.next();
					if (element != null) {
						array.put(transToJSONObject(element.getClass(),
								element, tojson));
					}
				}
				json = array;
			} else if (cls.isArray()) {
				// 数组
				JSONArray array = new JSONArray();
				int length = Array.getLength(value);
				for (int i = 0; i < length; i++) {
					Object element = Array.get(value, i);
					if (element != null) {
						array.put(transToJSONObject(element.getClass(),
								element, tojson));
					}
				}
				json = array;
			} else if (Map.class.isAssignableFrom(cls)) {
				// 映射
				Iterator iter = ((Map) value).entrySet().iterator();
				JSONObject object = new JSONObject();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					Object v = entry.getValue();
					if (v != null) {
						object.put(entry.getKey().toString(),
								transToJSONObject(v.getClass(), v, tojson));
					}
				}
				json = object;
			} else if (cls.isPrimitive() || Boolean.class == cls
					|| CharSequence.class.isAssignableFrom(cls)
					|| Number.class.isAssignableFrom(cls)) {
				if (cls == Boolean.TYPE || cls == Integer.TYPE
						|| cls == Float.TYPE || cls == Double.TYPE
						|| cls == Long.TYPE || cls == Long.class
						|| cls == Short.TYPE || cls == Byte.TYPE
						|| cls.isAssignableFrom(Number.class)) {
					json = value;
				} else {
					json = value.toString();
				}
			} else if (java.util.Date.class.isAssignableFrom(cls)
					|| java.sql.Date.class.isAssignableFrom(cls)) {
				json = value.toString();
			} else {
				Map map = OgnlUtil.getBeanMap(value);
				JSONObject object = new JSONObject();
				if (map != null) {
					if (tojson != null) {
						String[] include = tojson.include();
						String[] exclude = tojson.exclude();
						if (include.length > 0) {
							HashMap<String, Object> elements = new HashMap<String, Object>();
							for (int i = 0; i < include.length; i++) {
								elements.put(include[i], map.get(include[i]));
							}
							map = elements;
						} else if (exclude.length > 0) {
							for (int i = 0; i < exclude.length; i++) {
								map.remove(exclude[i]);
							}
						}
					}
					Iterator iter = map.entrySet().iterator();
					while (iter.hasNext()) {
						Map.Entry element = (Map.Entry) iter.next();
						Object v = element.getValue();
						if (v != null) {
							object.put((String) element.getKey(),
									transToJSONObject(v.getClass(), v));
						}
					}
					json = object;
				}
			}
		}
		return json == null ? "" : json;
	}

	protected boolean isLegalCharSet() {
		Charset tmp = null;
		if (charset != null) {
			if (Charset.isSupported(charset)) {
				tmp = Charset.forName(charset);
			} else {
				tmp = null;
			}
		}
		return tmp != null;
	}

	private static final long serialVersionUID = -4293657447642850227L;
}
