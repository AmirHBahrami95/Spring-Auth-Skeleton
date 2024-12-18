package com.amir.app.filters;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amir.app.user.impl.UserRepoImpl;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class IPLoggerFilter implements Filter{
	
	final Logger logger = LoggerFactory.getLogger(IPLoggerFilter.class);
	private static final String[] POSSIBLE_IP_HEADERS = {
      "X-Forwarded-For",
      "Proxy-Client-IP",
      "WL-Proxy-Client-IP",
      "HTTP_X_FORWARDED_FOR",
      "HTTP_X_FORWARDED",
      "HTTP_X_CLUSTER_CLIENT_IP",
      "HTTP_CLIENT_IP",
      "HTTP_FORWARDED_FOR",
      "HTTP_FORWARDED",
      "HTTP_VIA",
      "REMOTE_ADDR" };

	private String getClientIp(HttpServletRequest request) {
		StringBuffer buff=new StringBuffer();
		for (String header : POSSIBLE_IP_HEADERS) {
		  String ip = request.getHeader(header);
		  if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
		      buff.append(header+":"+ip+", ");
		  }
		}
		buff.append("remote-addr:"+request.getRemoteAddr());
	  return buff.toString();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq=(HttpServletRequest)request;
		logger.info(getClientIp(hreq));
		chain.doFilter(request, response);
	}

}
