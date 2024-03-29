//AutocompletadoServlet.java
package moduloautobd;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import moduloautobd.servicio.ServicioAutocompletado;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;

public class AutocompletadoServlet extends HttpServlet {

    private ArrayList palabras;

    public AutocompletadoServlet() {
        System.out.println("AutocompletadoServlet()");
    }

    public void init(ServletConfig config) { /*establece el tiempo de vida de un servlet
                                               Aqui se suele declarar la conexion de la BD.*/
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String prefijo = request.getParameter("names");

        //Obteniendo la lista de palabras del servicio de bd
        ServicioAutocompletado sa = new ServicioAutocompletado();
        palabras = sa.obtListaPalabras();

        //Obteniendo palabras similares
        ServicioComparador sc = ServicioComparador.obtInstancia(palabras);
        ArrayList similares = sc.buscarPalabras(prefijo);

        if (similares.size() > 0) {
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            /*Un header son meta-instrucciones, no dejan código
            Pueden ser tanto en la parte del servidor y la del cliente*/

            PrintWriter salida = response.getWriter();
            salida.println("<response>");

            for (int i = 0; i < similares.size(); i++) {
                String palabra = (String) similares.get(i);
                salida.println("<name>" + palabra + "</name>");
            }

            salida.println("</response>");

            sa.cerrarConexion();
            palabras = null;
            similares = null;
            sc = null;

            salida.close();
        } else {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }
}
