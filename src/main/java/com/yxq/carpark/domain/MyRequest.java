package com.yxq.carpark.domain;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
 
/**
 * @author  �̳�HttpServletRequestWrapper�൱��ʵ����HttpServletRequest
 *         HttpServletRequestWrapper�࣬������ʵ��������HttpServletRequest�ķ���
 *         �̳���֮����Ҫ�޸ĵķ���MyRequest�����Լ����壬����Ҫ�޸ĵķ�����ֱ��ʹ�ø���ķ���
 *
 *         ��һ�����ܽ᣺�̳�HttpServletRequestWrapper��Ϊ��͵����
 *         �����Լ�ȥʵ������HttpServletRequest�ķ��� �ڶ�����ʹ�ù��캯����ԭ����request���󱣴浽��ǰ�Զ��������
 *         �����������Ҫ�޸ĵķ�����������ǿ ���Ĳ�������һ��flag��ǣ���ֹ�����ظ�ִ��
 */
public class MyRequest extends HttpServletRequestWrapper {
 
    // ������һ����Ա�������������湹�캯�������requset����
    private HttpServletRequest request = null;
 
    // ����һ����ǣ�������ע����ǰrequset�У�����������Ƿ��Ѿ��������
    private boolean flag = false;
 
    public MyRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
 
    }
 
    // �����󣺶�request����Ļ�ȡ���ݵķ�����������ǿ��ͳһ���룩
 
    @Override
    public Map<String, String[]> getParameterMap() {
        // �������ʽrequest.getMethod()����
        String method = this.request.getMethod();
        // post����
        if ("post".equalsIgnoreCase(method)) {
            // ���ñ����ʽ
            try {
                request.setCharacterEncoding("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Map<String, String[]> map = this.request.getParameterMap();
            return map;
 
        } else if ("get".equalsIgnoreCase(method)) {
            // get����
            // ������get������Ҫ��ÿһ������������ת���������Ҫ��map�е�ÿ��Ԫ�ؽ��б���
            // ���Ȼ��map����
            Map<String, String[]> map = this.request.getParameterMap();
 
            //��һ�λ�ȡ���������flag==false��ִ�к���Ķ����봦����
            //�ڶ��λ�ȡ���������ʱ��flag==true����ִ�к���Ĵ���ֱ�ӷ����Ѿ��������map����
            if (flag) {
                return map;
            }
            if (map == null) {
                return super.getParameterMap();
            } else {
                // Ȼ����map���ϵ�key
                Set<String> key = map.keySet();
                // ͨ��key��map�е�Ԫ��ȡ����
                for (String string : key) {
                    String[] value = map.get(string);
                    // ��������Ҫ��String�е�ÿһ�������б�����ת������
                    for (int i = 0; i < value.length; i++) {
                        try {
                            String string2 = new String(
                                    value[i].getBytes("iso-8859-1"), "utf-8");
                            value[i] = string2;
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                flag = true;
                return map;
            }
        } else {
            //λ������ʽ���Զ���������ˣ�ʹ�ø���ķ�������
            return super.getParameterMap();
        }
    }
 
    @Override
    public String[] getParameterValues(String name) {
        // ͨ��map���ϻ�ȡ����
        Map<String, String[]> map = this.getParameterMap();
        if (map == null) {
            return super.getParameterValues(name);
        } else {
            String[] strings = map.get(name);
            return strings;
        }
    }
 
    @Override
    public String getParameter(String name) {
        // ͨ��values��ȡ����
        String[] values = this.getParameterValues(name);
        if (values == null) {
            return super.getParameter(name);
        } else {
            return values[0];
        }
    }
 
}
