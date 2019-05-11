package com.yxq.carpark.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.yxq.carpark.domain.MyRequest;
 
public class EncodingFilter implements Filter {
 
      @Override
      public void destroy() {
 
      }
 
      @Override
      public void doFilter(ServletRequest req, ServletResponse res,
                  FilterChain chain) throws IOException, ServletException {
            // ���������Ӧǿ��ת����Http��ʽ
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
 
            // ������Ӧ����
            response.setContentType("text/html;charset=utf-8");
 
            // �Զ���һ��request����MyRequest���Է�����ԭ����requset������ǿ��ʹ��װ�����ģʽ
            // Ҫ��ǿԭ����request���󣬱����Ȼ�ȡ��ԭ����request����
            MyRequest myRequest = new MyRequest(request);
 
            // ע�⣺���е�ʱ��Ӧ�ô�����ǿ���request����
            chain.doFilter(myRequest, response);
      }
 
      @Override
      public void init(FilterConfig arg0) throws ServletException {
 
      }
 
}