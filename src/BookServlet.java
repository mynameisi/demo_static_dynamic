import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class CookieExample
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static Logger logger = LoggerFactory.getLogger(BookServlet.class);

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		logger.debug("init() 方法运行");
		super.init();

	}

	

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("-------------------");
		logger.debug("service() 方法运行");

		String requestMethod = request.getMethod();
		String requestURL = request.getRequestURL().toString();
		String requestProtocol = request.getProtocol();

		logger.debug("收到了用户对以下资源:");
		logger.debug(requestURL);
		logger.debug("发出了基于[" + requestProtocol + "] 的  " + requestMethod + "  的请求");
		Enumeration<String> eu = request.getParameterNames();
		ArrayList<String> al = Collections.list(eu);
		if (!al.isEmpty()) {
			logger.debug("并且在请求中，附加了如下的参数");
			for (String name : al) {
				logger.debug("参数名称: [" + name + "] 参数值: [" + request.getParameter(name) + "]");
			}
		}

		String book = request.getParameter("book");
		if (book == null) {
			sendHTML(request, response, "<h1>你想要什么图书?用参数book=?告诉我</h1>");
			return;
		} else if (!book.equals("jsp")) {
			sendHTML(request, response, "<h1>我这里现在只有JSP的书</h1>");
			return;
		}
		super.service(request, response);

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("-------------------");
		logger.debug("doGet() 方法运行");
		sendHTML(request, response, "<h1>隆重推荐: Head First Servlet & JSP</h1><br><img src=\"res/h\"/>");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("-------------------");
		logger.debug("doPost() 方法运行");
		sendHTML(request, response, "<h1>隆重推荐: Head First Servlet & JSP</h1><br><img src=\"res/h\"/>");

	}


	public void sendHTML(HttpServletRequest request, HttpServletResponse response, String msg) {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		out.println("<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "<head>\n"
				+ "<meta charset=\"UTF-8\">\n"
				+ "</head>\n"
				+ "<body>\n");
		out.println(msg);
		out.println("</body>\n"
				+ "</html>");
	}
}
