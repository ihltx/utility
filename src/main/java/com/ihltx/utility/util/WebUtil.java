package com.ihltx.utility.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.util.Strings;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * WebUtil
 * Web utility class
 * @author liulin 84611913@qq.com
 *
 */

public class WebUtil {

	/**
	 * Mobile terminal flag array
	 */
	public static final String[] MOBILE_AGENT_FLAGS = new String[] { "ipad", "ipod", "iphone os", "rv:1.2.3.4", "ucweb",
			"android", "windows ce", "windows mobile", "symbianos", "windows phone", "iphone", "phone", "mobile",
			"wap" };
	/**
	 * Wechat terminal flag
	 */
	public static final String WECHAT_FLAG = "micromessenger";

	/**
	 * ShopId data name  of Application, from header/cookie/request/session,etc...
	 */
	public static final String APP_SHOP_ID_NAME = "APP_SHOP_ID";

	/**
	 * Default ShopId value: 0
	 */
	public static final long DEFAULT_SHOP_ID = 0;

	/**
	 * Current theme name of Application View
	 */
	public static final String APP_THEME_NAME = "APP_THEME";

	/**
	 * Default theme : default
	 */
	public static final String DEFAULT_THEME = "default";

	/**
	 * theme path name of Application view
	 */
	public static final String APP_THEME_PATH = "APP_THEME_PATH";

	/**
	 * global theme path name of Application view
	 */
	public static final String APP_GLOBAL_THEME_PATH = "APP_GLOBAL_THEME_PATH";

	/**
	 * real theme path name of Application view
	 */
	public static final String APP_REAL_THEME_PATH = "APP_REAL_THEME_PATH";

	/**
	 * static resource cdn url prefix name of Application view
	 */
	public static final String APP_STATIC_CDN_URL_PREFIX = "APP_STATIC_CDN_URL_PREFIX";

	/**
	 * web context path name of Application view
	 */
	public static final String APP_PATH = "APP_PATH";

	/**
	 * all langauges data name of Application view
	 */
	public static final String APP_LANGUAGES = "APP_LANGUAGES";

	/**
	 * all langauge resources name of Application view
	 */
	public static final String APP_All_Langs = "APP_All_Langs";

	/**
	 * shop langauge shopId name of Application view
	 */
	public static final String SHOP_LANG_SHOP_ID_NAME = "shopLangShopID";

	/**
	 * langauge resource shopId name of Application view
	 */
	public static final String LANG_RES_SHOP_ID_NAME = "langResShopId";


	/**
	 * 反向代理服务器通过header传递主机名称的头部名称
	 */
	public static final String X_PROXY_HOST = "host";

	/**
	 * 反向代理服务器通过header传递主机端口的头部名称
	 */
	public static final String X_PROXY_SERVER_PORT = "x-proxy-server-port";

	/**
	 * 反向代理服务器通过header传递请求协议的头部名称
	 */
	public static final String X_PROXY_SCHEME = "x-proxy-scheme";

	/**
	 * 反向代理服务器通过header传递请求服务器地址的头部名称
	 */
	public static final String X_PROXY_SERVER = "x-proxy-server";

	/**
	 * Get the user's real IP address
	 * 
	 * @return String
	 */
	public static String getIpAddress() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(servletRequestAttributes==null) return null;
		HttpServletRequest request = servletRequestAttributes.getRequest();
		return getIpAddress(request);
	}

	/**
	 * Get the user's real IP address from request
	 *
	 * @param request				HttpServletRequest request
	 * @return String
	 */
	public static String getIpAddress(HttpServletRequest request) {
		if (request == null)
			return "127.0.0.1";
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ip = inet.getHostAddress();
			}
		}
		return ip;
	}

	/**
	 * Determine whether the current request is an Ajax request
	 * 
	 * @return Boolean
	 * 		true	--	ajax request
	 * 		false	--	non ajax request
	 */
	public static Boolean isAjax() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(servletRequestAttributes==null) return false;
		HttpServletRequest request = servletRequestAttributes.getRequest();
		return isAjax(request);
	}

	/**
	 * Determine whether the current request is an Ajax request
	 *
	 * @param request				Current HttpServletRequest request
	 * @return Boolean
	 * 		true	--	ajax request
	 * 		false	--	non ajax request
	 */
	public static Boolean isAjax(HttpServletRequest request) {
		if (request == null)
			return false;
		return (request.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}

	/**
	 * Judge whether the current request is mobile terminal / PC terminal
	 * 
	 * @return Boolean
	 * 		true	--	mobile terminal
	 * 		false	--	PC terminal
	 */
	public static Boolean isMobile() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(servletRequestAttributes==null) return false;
		HttpServletRequest request = servletRequestAttributes.getRequest();
		return isMobile(request);
	}

	/**
	 * Judge whether the current request is mobile terminal / PC terminal
	 * 
	 * @param request				Current HttpServletRequest request
	 * @return Boolean
	 * 		true	--	mobile terminal
	 * 		false	--	PC terminal
	 */
	public static boolean isMobile(HttpServletRequest request) {
		if (request == null)
			return false;
		String UserAgent = request.getHeader("User-Agent");
		if (Strings.isEmpty(UserAgent)) {
			return false;
		}

		String ua = UserAgent.toLowerCase();
		for (String sua : MOBILE_AGENT_FLAGS) {
			if (ua.indexOf(sua) > -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Judge whether the current request is wechat browser
	 *
	 * @return Boolean
	 * 		true	--	wechat browser
	 * 		false	--	none wechat browser
	 */
	public static boolean isWechat() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(servletRequestAttributes==null) return false;
		HttpServletRequest request =servletRequestAttributes.getRequest();
		return isWechat(request);
	}

	/**
	 * Judge whether the current request is wechat browser
	 *
	 * @param request				HttpServletRequest request
	 * @return Boolean
	 * 		true	--	wechat browser
	 * 		false	--	none wechat browser
	 */
	public static boolean isWechat(HttpServletRequest request) {
		if (request == null)
			return false;
		String UserAgent = request.getHeader("User-Agent");
		if (Strings.isEmpty(UserAgent)) {
			return false;
		}
		String ua = UserAgent.toLowerCase();
		if (ua.indexOf(WECHAT_FLAG) > -1) {
			return true;
		}
		return false;

	}

	/**
	 * In the current request scope based on shopIdName save shopId value
	 *
	 * @param request					HttpServletRequest request
	 * @param shopIdName                Name variable
	 * @param shopId					Current customer shopId value. If it is empty, 0 will be saved
	 * @return long
	 * 		Shopid returned after saving successfully
	 */
	public static long setShopId(HttpServletRequest request , String shopIdName, long shopId) {
		if (request == null) {
			return DEFAULT_SHOP_ID;
		}
		if (shopId>=0) {
			request.getSession().setAttribute(shopIdName, shopId);
		} else {
			request.getSession().setAttribute(shopIdName, DEFAULT_SHOP_ID);
		}
		return Long.valueOf(request.getSession().getAttribute(shopIdName).toString());
	}

	/**
	 * In the current request scope based on shopIdName save shopId value
	 *
	 * @param shopIdName                Name variable
	 * @param shopId					Current customer shopId value. If it is empty, 0 will be saved
	 * @return long
	 * 		Shopid returned after saving successfully
	 */
	public static long setShopId(String shopIdName, long shopId) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(servletRequestAttributes==null) return DEFAULT_SHOP_ID;
		HttpServletRequest request = servletRequestAttributes.getRequest();
		return setShopId(request , shopIdName , shopId);
	}

	/**
	 * In the current request scope based on the fixed name variable [APP_SHOP_ID_NAME] save shopId value
	 *
	 * @param request			HttpServletRequest request
	 * @param shopId            Current customer shopId value. If it is empty, 0 will be saved
	 * @return long
	 * 		Shopid returned after saving successfully
	 */
	public static long setShopId(HttpServletRequest request ,long shopId) {
		return setShopId(request , APP_SHOP_ID_NAME, shopId);
	}


	/**
	 * In the current request scope based on the fixed name variable [APP_SHOP_ID_NAME] save shopId value
	 *
	 * @param shopId            Current customer shopId value. If it is empty, 0 will be saved
	 * @return long
	 * 		Shopid returned after saving successfully
	 */
	public static long setShopId(long shopId) {
		return setShopId(APP_SHOP_ID_NAME, shopId);
	}

	/**
	 * From the current request scope based on shopIdName get shopId value
	 *
	 * @param request					HttpServletRequest request
	 * @param shopIdName				Name variable
	 * @return long
	 * 		Returns shopId
	 */
	public static long getShopId(HttpServletRequest request ,String shopIdName) {
		if (request == null) {
			return DEFAULT_SHOP_ID;
		}
		if (request.getSession().getAttribute(shopIdName) == null) {
			return DEFAULT_SHOP_ID;
		}
		return Long.valueOf(request.getSession().getAttribute(shopIdName).toString());
	}

	/**
	 * From the current request scope based on shopIdName get shopId value
	 *
	 * @param shopIdName				Name variable
	 * @return long
	 * 		Returns shopId
	 */
	public static long getShopId(String shopIdName) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(servletRequestAttributes==null) return DEFAULT_SHOP_ID;
		HttpServletRequest request = servletRequestAttributes.getRequest();
		return getShopId(request , shopIdName);
	}

	/**
	 * From the current request scope based on the fixed name variable [APP_SHOP_ID_NAME] get shopId value
	 *
	 * @param request					HttpServletRequest request
	 * @return long
	 * 		Returns shopId
	 */
	public static long getShopId(HttpServletRequest request) {
		return getShopId(request , APP_SHOP_ID_NAME);
	}

	/**
	 * From the current request scope based on the fixed name variable [APP_SHOP_ID_NAME] get shopId value
	 * 
	 * @return long
	 * 		Returns shopId
	 */
	public static long getShopId() {
		return getShopId(APP_SHOP_ID_NAME);
	}



	/**
	 * In the current request scope based on themeName variable save theme
	 *
	 * @param request				HttpServletRequest request
	 * @param themeName				Theme name variable
	 * @param theme					Theme. If the topic is empty, the default theme will be used
	 * @return String
	 * 		theme returned after saving successfully
	 */
	public static String setTheme(HttpServletRequest request , String themeName, String theme) {
		if (request == null) {
			return null;
		}
		if (!Strings.isEmpty(theme)) {
			request.getSession().setAttribute(themeName, theme);
		} else {
			request.getSession().setAttribute(themeName, DEFAULT_THEME);
		}
		return request.getSession().getAttribute(themeName).toString();
	}

	/**
	 * In the current request scope based on themeName variable save theme
	 *
	 * @param themeName				Theme name variable
	 * @param theme					Theme. If the topic is empty, the default theme will be used
	 * @return String
	 * 		theme returned after saving successfully
	 */
	public static String setTheme(String themeName, String theme) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(servletRequestAttributes==null) return null;
		HttpServletRequest request = servletRequestAttributes.getRequest();
		return setTheme(request , themeName , theme);
	}

	/**
	 * In the current request scope based on the fixed name variable [APP_THEME_NAME] save theme
	 *
	 * @param theme                Theme. If the topic is empty, the default theme will be used
	 * @return String
	 * 		theme returned after saving successfully
	 */
	public static String setTheme(HttpServletRequest request , String theme) {
		return setTheme(request, APP_THEME_NAME, theme);
	}


	/**
	 * In the current request scope based on the fixed name variable [APP_THEME_NAME] save theme
	 *
	 * @param theme                Theme. If the topic is empty, the default theme will be used
	 * @return String
	 * 		theme returned after saving successfully
	 */
	public static String setTheme(String theme) {
		return setTheme(APP_THEME_NAME, theme);
	}


	/**
	 * In the current request scope based on themeName variable get theme
	 *
	 * @param request				HttpServletRequest request
	 * @param themeName				Theme name variable
	 * @return String
	 * 		Returns theme
	 */
	public static String getThemeOrNull(HttpServletRequest request , String themeName) {
		if (request == null) {
			return null;
		}
		if (request.getSession().getAttribute(themeName) == null) {
			return null;
		}
		return request.getSession().getAttribute(themeName).toString();
	}

	/**
	 * In the current request scope based on themeName variable get theme, or null
	 *
	 * @param request				HttpServletRequest request
	 * @return String
	 * 		Returns theme
	 */
	public static String getThemeOrNull(HttpServletRequest request) {
		return getThemeOrNull(request , APP_THEME_NAME);
	}

	/**
	 * In the current request scope based on themeName variable get theme , or default
	 *
	 * @param request				HttpServletRequest request
	 * @param themeName				Theme name variable
	 * @return String
	 * 		Returns theme
	 */
	public static String getTheme(HttpServletRequest request , String themeName) {
		if (request == null) {
			return DEFAULT_THEME;
		}
		if (request.getSession().getAttribute(themeName) == null) {
			return DEFAULT_THEME;
		}
		return request.getSession().getAttribute(themeName).toString();
	}

	/**
	 * In the current request scope based on themeName variable get theme , or default
	 *
	 * @param request				HttpServletRequest request
	 * @return String
	 * 		Returns theme
	 */
	public static String getTheme(HttpServletRequest request) {
		return getTheme(request , APP_THEME_NAME);
	}

	/**
	 * In the current request scope based on the fixed name variable [APP_THEME_NAME] get theme
	 *
	 * @param themeName				Theme name variable
	 * @return String
	 * 		Returns theme
	 * 		null --  get failure
	 */
	public static String getTheme(String themeName) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(servletRequestAttributes==null) return null;
		HttpServletRequest request = servletRequestAttributes.getRequest();
		return getTheme(request , themeName);
	}

	/**
	 * In the current request scope based on the fixed name variable [APP_THEME_NAME] get theme
	 *
	 * @return String
	 * 		Returns theme
	 * 		null --  get failure
	 */
	public static String getTheme() {
		return getTheme(APP_THEME_NAME);
	}



	/**
	 * from the request scope remove  theme set
	 *
	 * @param request				HttpServletRequest request
	 * @param themeName				Theme name variable
	 * @return String
	 * 		theme returned after saving successfully
	 */
	public static void removeTheme(HttpServletRequest request , String themeName) {
		if (request == null) {
			return;
		}
		request.getSession().removeAttribute(themeName);
	}

	/**
	 * from the request scope remove theme set
	 *
	 * @param request				HttpServletRequest request
	 * @return String
	 * 		theme returned after saving successfully
	 */
	public static void removeTheme(HttpServletRequest request) {
		removeTheme(request, APP_THEME_NAME);
	}

	/**
	 * from the current request scope remove  theme set
	 *
	 * @return String
	 * 		theme returned after saving successfully
	 */
	public static void removeTheme() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(servletRequestAttributes==null) return;
		HttpServletRequest request = servletRequestAttributes.getRequest();
		removeTheme(request, APP_THEME_NAME);
	}

	/**
	 * Get the header value according to the header name
	 *
	 * @param name					The header name
	 * @param request				HttpServletRequest request
	 * @return String
	 * 		Returns header value
	 *		null -- header name is not exists
	 */
	public static String getHeader(HttpServletRequest request , String name) {
		if (request != null) {
			return Strings.isEmpty(request.getHeader(name)) ? null : request.getHeader(name);
		}
		return null;
	}

	/**
	 * Get the header value according to the header name
	 * 
	 * @param name				The header name
	 * @return String
	 * 		Returns header value
	 *		null -- header name is not exists
	 */
	public static String getHeader(String name) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(servletRequestAttributes==null) return null;
		HttpServletRequest request =servletRequestAttributes.getRequest();
		return getHeader(request , name);
	}



	/**
	 * Return the view path according to the fully qualified class name of the controller and the action method of the controller
	 * 
	 * @param controllerClassName        Controller fully qualified class name
	 * @param method                     Controller action method name
	 * @return String
	 * 		Returns View path
	 */

	public static String getViewPath(String controllerClassName, String method) {
		controllerClassName = controllerClassName.toLowerCase();
		String str = controllerClassName.substring(controllerClassName.indexOf(".controller."))
				.replaceFirst("\\.controller\\.", "");
		String[] temps = str.split("\\.");
		StringBuffer path = new StringBuffer();
		for (String s : temps) {
			s = s.toLowerCase();
			path.append(s.replaceAll("controller", "")).append("/");
		}
		return path.toString() + method.toLowerCase();
	}


	

	/**
	 * get Cookie value
	 *
	 * @param request 			HttpServletRequest request
	 * @param name    			Cookie name
	 * @return String
	 * 		Returns cookie value
	 * 		null	--	Cookie name is not exists
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		if (request != null && request.getCookies() != null) {
			for (Cookie c : request.getCookies()) {
				if (c.getName().equals(name)) {
					return c.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * get Cookie value
	 *
	 * @param name    			Cookie name
	 * @return String
	 * 		Returns cookie value
	 * 		null	--	Cookie name is not exists
	 */
	public static String getCookie(String name) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(servletRequestAttributes==null) return null;
		HttpServletRequest request = servletRequestAttributes.getRequest();
		return getCookie(request, name);
	}

	/**
	 * set Cookie value
	 * 
	 * @param name			Cookie name
	 * @param value			Cookie value
	 * @param expire        Expiration time in seconds
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 */
	public static Boolean setCookie(String name, String value, int expire) {
		return setCookie(name, value, null, null, false, expire);
	}


	/**
	 * set Cookie value
	 *
	 * @param name			Cookie name
	 * @param value			Cookie value
	 * @param expire        Expiration time in seconds
	 * @param response 		Current response
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 */
	public static Boolean setCookie(String name, String value, int expire, HttpServletResponse response) {
		return setCookie(name, value, null, null, false, expire, response);
	}
	

	/**
	 * set Cookie value
	 *
	 * @param name			Cookie name
	 * @param value			Cookie value
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 */
	public static Boolean setCookie(String name, String value) {
		return setCookie(name, value, null, null, false, 0);
	}


	/**
	 * set Cookie value
	 *
	 * @param name			Cookie name
	 * @param value			Cookie value
	 * @param response 		Current response
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 */
	public static Boolean setCookie(String name, String value, HttpServletResponse response) {
		return setCookie(name, value, null, null, false, 0, response);
	}

	/**
	 * set Cookie value
	 * 
	 * @param name     		Cookie name
	 * @param value			Cookie value
	 * @param path     		Path
	 * @param domain		Domain
	 * @param httpOnly		Is httpOnly
	 * @param expire   		Expiration time in seconds
	 * @param response		Current response
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 */
	public static Boolean setCookie(String name, String value, String path, String domain, Boolean httpOnly, int expire,
			HttpServletResponse response) {
		if (response != null) {
			Cookie cookie = new Cookie(name, value);
			if (!StringUtil.isNullOrEmpty(path)) {
				cookie.setPath(path);
			}
			if (!StringUtil.isNullOrEmpty(domain)) {
				cookie.setDomain(domain);
			}
			cookie.setHttpOnly(httpOnly);
			if (expire > 0) {
				cookie.setMaxAge(expire);
			}
			response.addCookie(cookie);
			return true;
		}
		return false;
	}

	/**
	 * set Cookie value
	 *
	 * @param name     		Cookie name
	 * @param value			Cookie value
	 * @param path     		Path
	 * @param domain		Domain
	 * @param httpOnly		Is httpOnly
	 * @param expire   		Expiration time in seconds
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 */
	public static Boolean setCookie(String name, String value, String path, String domain, Boolean httpOnly,
			int expire) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(servletRequestAttributes==null) return false;
		HttpServletResponse response = servletRequestAttributes.getResponse();
		return setCookie(name, value, path, domain, httpOnly, expire, response);
	}

	
	/**
	 * Get the current language from the accept language of the current request header
	 * @return String
	 * 		Returns accept language
	 * 		null -- failure
	 */
	public static String getLanguageByAcceptLanguage() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(servletRequestAttributes==null) return null;
		return getLanguageByAcceptLanguage(servletRequestAttributes.getRequest());
	}

	/**
	 * Get the current language from the accept language of the Specify request header
	 * @param request		Specify request
	 * @return String
	 * 		Returns accept language
	 * 		null -- failure
	 */
	public static String getLanguageByAcceptLanguage(HttpServletRequest request) {
		if(request==null) return null;
		String AcceptLanguage = request.getHeader("Accept-Language");
		if(Strings.isEmpty(AcceptLanguage)) return null;
		String[] allLangs = AcceptLanguage.split(";");
		if(allLangs.length>0) {
			String[] langs = allLangs[0].split(",");
			if(langs.length>0) {
				return langs[0];
			}
			return allLangs[0];
		}
		return null;
	}


	/**
	 * 基于当前request及response跳转url,url中如果包含://则直接跳转，否则甚基于当前请求主机及端口进行跳转
	 * @param url
	 */
	public static void redirect(String url) throws IOException {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(servletRequestAttributes==null) return ;
	redirect(url , servletRequestAttributes.getRequest() , servletRequestAttributes.getResponse());

	}

	/**
	 * 基于指定request及response跳转url,url中如果包含://则直接跳转，否则甚基于当前请求主机及端口进行跳转
	 * @param url
	 * @param response
	 */
	public static void redirect(String url , HttpServletRequest request , HttpServletResponse response) throws IOException {
		if(url.contains("://")){
			if(response==null) return;
			response.sendRedirect(url);
		}else{
			if(request==null || response==null) return;
			StringBuilder sbd = new StringBuilder();
			String scheme = null;
			String serverName = null;
			int port = 80;
			if(StringUtil.isNullOrEmpty(request.getHeader(X_PROXY_SCHEME)) || StringUtil.isNullOrEmpty(request.getHeader(X_PROXY_HOST))  || StringUtil.isNullOrEmpty(request.getHeader(X_PROXY_SERVER_PORT))){
				scheme = request.getScheme();
				serverName = request.getServerName();
				port = request.getServerPort();
			}else{
				scheme = request.getHeader(X_PROXY_SCHEME);
				serverName = request.getHeader(X_PROXY_HOST);
				port = Integer.valueOf(request.getHeader(X_PROXY_SERVER_PORT));
			}
			if(port == 80 || port == 443){
				sbd.append(scheme).append("://").append(serverName).append(url);
			}else{
				sbd.append(scheme).append("://").append(serverName).append(":").append(port).append(url);
			}
			response.sendRedirect(sbd.toString());
		}
	}
}
