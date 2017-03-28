import java.io.IOException;
import java.util.List;
import java.time.ZoneOffset;
import java.time.OffsetDateTime;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.anteestudio.stock.StockTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.io.PrintWriter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class HelloWorldServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        ServletContext ctx = this.getServletContext();
        WebApplicationContext wtx = WebApplicationContextUtils.
            getWebApplicationContext(ctx);
        SessionFactory sessionFactory =
            wtx.getBean("mySessionFactory", SessionFactory.class);
        Session session = sessionFactory.openSession();

	resp.setContentType("text/html;charset=UTF-8");

	PrintWriter out = resp.getWriter();

    ZoneOffset zo = ZoneOffset.ofHours(8);

	out.println("<html>");
	out.println("<head><title>My Servlet</title></head>");
 	out.println("<link href='bootstrap-3.3.7-dist/css/bootstrap.min.css' rel='stylesheet'>");
	out.println("<body>");
	out.println("<table class='table table-striped'>");

	List<StockTransaction> result =
	    (List<StockTransaction>)session.
	    createQuery("from StockTransaction StockTransaction order by StockTransaction.date desc").list();

	for ( StockTransaction s: (List<StockTransaction>) result) {
	    out.println("<tr>");
	    out.println("<td>");
	    out.println(s.getTransId());
	    out.println("</td>");
	    out.println("<td>");
	    out.println(s.getSymbol());
	    out.println("</td>");
	    out.println("<td>");
	    out.println(s.getBuyOrSell());
	    out.println("</td>");
	    out.println("<td>");
	    out.println(s.getQuantity());
	    out.println("</td>");
	    out.println("<td>");
	    out.println(s.getPrice());
	    out.println("</td>");
	    out.println("<td>");
        OffsetDateTime dt1 = s.getDate().atOffset(zo);
        out.println(dt1);
	    out.println("</td>");
	    out.println("</tr>");
	}

	out.println("</table>");
 	out.println("<script src='js/jquery-3.1.1.min.js'></script>");
	out.println("<script src='bootstrap-3.3.7-dist/js/bootstrap.min.js'></script>");       
	out.println("</body>");
	out.println("</html>");

	session.close();

    }
}
