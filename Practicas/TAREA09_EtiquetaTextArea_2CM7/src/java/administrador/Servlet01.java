package administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String nombre = request.getParameter("clave_name");
        String comentario = request.getParameter("clave_coment");
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet02</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Nombre: "+nombre+"</p>");
        out.println("</br>");
        out.println("<p>Comentario: "+comentario+"</p>");
        out.println("</body>");
        out.println("</html>");
    }
}