package exceptionresolver;

import com.alibaba.druid.support.json.JSONUtils;import exception.BusinessException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MySimpleMappingExceptionResolver implements
		HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request,
										 HttpServletResponse response, Object object, Exception exception) {
		// �ж��Ƿ�ajax����
		if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
				.getHeader("X-Requested-With") != null && request.getHeader(
				"X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			// �������ajax��JSP��ʽ����
			// Ϊ��ȫ�����ֻ��ҵ���쳣���Ƕ�ǰ�˿ɼ����������ͳһ��Ϊϵͳ�쳣
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", false);
			if (exception instanceof BusinessException) {
				map.put("errorMsg", exception.getMessage());
			} else {
				map.put("errorMsg", "ϵͳ�쳣��");
			}
			//������Ҫ�ֶ����쳣��ӡ����������û������log��ʵ����������Ӧ�ô�ӡ��log����
			exception.printStackTrace();
			//���ڷ�ajax�������Ƕ�ͳһ��ת��error.jspҳ��
			return new ModelAndView("/error", map);
		} else {
			// �����ajax����JSON��ʽ����
			try {
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("success", false);
				// Ϊ��ȫ�����ֻ��ҵ���쳣���Ƕ�ǰ�˿ɼ�������ͳһ��Ϊϵͳ�쳣
				if (exception instanceof BusinessException) {
					map.put("errorMsg", exception.getMessage());
				} else {
					map.put("errorMsg", "ϵͳ�쳣��");
				}
				writer.write(JSONUtils.toJSONString(map));
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
