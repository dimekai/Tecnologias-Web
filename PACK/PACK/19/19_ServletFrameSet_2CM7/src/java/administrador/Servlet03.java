package administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        
        String num_link = (String)session.getAttribute("atributo_link");
        int num_link_i = Integer.parseInt(num_link);
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet03</title>");
        out.println("</head>");
        out.println("<body>");
        for (int i = 0; i < num_link_i; i++)
            out.println("<a href='"+request.getParameter("link"+i)+"' target='contenidos' >"+request.getParameter("texto"+i)+" </a></br>");
        out.println("</body>");
        out.println("</html>");
    }
}
