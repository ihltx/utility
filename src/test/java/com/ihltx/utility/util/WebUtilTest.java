package com.ihltx.utility.util;

import static org.junit.Assert.assertEquals;

import com.ihltx.utility.UtilityApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

@SuppressWarnings("all")
@SpringBootTest(classes = {UtilityApplication.class})

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class WebUtilTest {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;


	@BeforeEach
	void setUp() {
		request = new MockHttpServletRequest();
		request.setCharacterEncoding(StringUtil.UTF_8);
		response = new MockHttpServletResponse();
		response.setCharacterEncoding(StringUtil.UTF_8);
	}
	
	@Test
	public void test_10_getIpAddress() {
		String remoteaddr="192.168.1.2";
		String x_forwarded_for ="";
		String ip = WebUtil.getIpAddress(null);
		assertEquals(ip.equals("127.0.0.1"), true);
		
		request.setRemoteAddr(remoteaddr);
		ip = WebUtil.getIpAddress(request);
		assertEquals(ip.equals(remoteaddr), true);
		
		request.addHeader("x-forwarded-for", "");
		ip = WebUtil.getIpAddress(request);
		System.out.println(ip);
		System.out.println("============================");
		assertEquals(ip.equals(remoteaddr), true);
		
		request.addHeader("x-forwarded-for", "unknown");
		ip = WebUtil.getIpAddress(request);
		System.out.println(ip);
		System.out.println("============================");
		assertEquals(ip.equals(remoteaddr), true);
		
		
		x_forwarded_for ="192.168.1.3";
		request.addHeader("Proxy-Client-IP", x_forwarded_for);
		System.out.println(request.getHeader("Proxy-Client-IP"));
		ip = WebUtil.getIpAddress(request);
		System.out.println(ip);
		System.out.println("============================");
		assertEquals(ip.equals(x_forwarded_for), true);

		
		request.removeHeader("Proxy-Client-IP");
		x_forwarded_for ="192.168.1.4";
		request.addHeader("WL-Proxy-Client-IP", x_forwarded_for);
		System.out.println(request.getHeader("WL-Proxy-Client-IP"));
		ip = WebUtil.getIpAddress(request);
		System.out.println(ip);
		System.out.println("============================");
		assertEquals(ip.equals(x_forwarded_for), true);
		
		request.removeHeader("Proxy-Client-IP");
		request.removeHeader("WL-Proxy-Client-IP");
		x_forwarded_for ="192.168.1.5";
		request.addHeader("HTTP_CLIENT_IP", x_forwarded_for);
		System.out.println(request.getHeader("HTTP_CLIENT_IP"));
		ip = WebUtil.getIpAddress(request);
		System.out.println(ip);
		System.out.println("============================");
		assertEquals(ip.equals(x_forwarded_for), true);

		request.removeHeader("Proxy-Client-IP");
		request.removeHeader("WL-Proxy-Client-IP");
		request.removeHeader("HTTP_CLIENT_IP");
		x_forwarded_for ="192.168.1.6";
		request.addHeader("HTTP_X_FORWARDED_FOR", x_forwarded_for);
		System.out.println(request.getHeader("HTTP_X_FORWARDED_FOR"));
		ip = WebUtil.getIpAddress(request);
		System.out.println(ip);
		System.out.println("============================");
		assertEquals(ip.equals(x_forwarded_for), true);
		
	}
	
	
	@Test
	public void test_11_isAjax() {
		boolean rs = WebUtil.isAjax(null);
		assertEquals(rs, false);
		
		rs = WebUtil.isAjax(request);
		assertEquals(rs, false);
		
		request.addHeader("X-Requested-With", "XMLHttpRequest");
		rs = WebUtil.isAjax(request);
		assertEquals(rs, true);
		
		request.removeHeader("X-Requested-With");
		request.addHeader("X-Requested-With", "");
		rs = WebUtil.isAjax(request);
		assertEquals(rs, false);
	}
	
	@Test
	public void test_12_isMobile() {
		boolean rs = WebUtil.isMobile(null);
		assertEquals(rs, false);
		
		rs = WebUtil.isMobile(request);
		assertEquals(rs, false);
		
		request.addHeader("User-Agent", "ipad");
		rs = WebUtil.isMobile(request);
		assertEquals(rs, true);
		
		request.removeHeader("User-Agent");
		request.addHeader("User-Agent", "android");
		rs = WebUtil.isMobile(request);
		assertEquals(rs, true);
		
		request.removeHeader("User-Agent");
		request.addHeader("User-Agent", "andr1oid");
		rs = WebUtil.isMobile(request);
		assertEquals(rs, false);
	}
  
	@Test
	public void test_13_isWechat() {
		boolean rs = WebUtil.isWechat(null);
		assertEquals(rs, false);
		
		rs = WebUtil.isWechat(request);
		assertEquals(rs, false);
		
		request.addHeader("User-Agent", "micromessenger");
		rs = WebUtil.isWechat(request);
		assertEquals(rs, true);
		
		request.removeHeader("User-Agent");
		request.addHeader("User-Agent", "micr1omessenger");
		rs = WebUtil.isWechat(request);
		assertEquals(rs, false);
		
	
	}
}
