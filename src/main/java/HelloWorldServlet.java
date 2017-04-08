import java.io.IOException;
import java.util.List;
import java.util.TimeZone;
import java.time.ZoneOffset;
import java.time.OffsetDateTime;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.anteestudio.stock.StockClosedTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.io.PrintWriter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class HelloWorldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
        throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();

        ServletContext ctx = this.getServletContext();
        WebApplicationContext wtx = WebApplicationContextUtils.getWebApplicationContext(ctx);
        NewHelloWorld newHelloWorld = wtx.getBean("newHelloWorld", NewHelloWorld.class);

        SessionFactory sessionFactory =
            wtx.getBean("mySessionFactory", SessionFactory.class);
        Session session = sessionFactory.openSession();

    ZoneOffset zo = ZoneOffset.ofHours(8);
	out.println("<html>");
	out.println("<head><title>My Servlet</title></head>");
	out.println("<link href='bootstrap-3.3.7-dist/css/bootstrap.min.css' rel='stylesheet'>");
		    
	out.println("<body>");
	out.println(newHelloWorld.getMessage());
	out.println("<table class='table table-striped'>");

	List<StockClosedTransaction> result =
	    session.
	    createQuery("from StockClosedTransaction StockClosedTransaction order by StockClosedTransaction.sellDate desc", StockClosedTransaction.class).getResultList();

	for ( StockClosedTransaction s: (List<StockClosedTransaction>) result) {
	    out.println("<tr>");
	    out.println("<td>");
	    out.println(s.getTransId());
	    out.println("</td>");
	    out.println("<td>");
	    out.println(s.getSymbol());
	    out.println("</td>");
	    out.println("<td>");
	    out.println(s.getBuyPrice());
	    out.println("</td>");
	    out.println("<td>");
        OffsetDateTime dt1 = s.getBuyDate().atOffset(zo);
        out.println(dt1);
	    out.println("</td>");
	    out.println("<td>");
	    out.println(s.getSellPrice());
	    out.println("</td>");
	    out.println("<td>");
        OffsetDateTime dt2 = s.getSellDate().atOffset(zo);
	    out.println(dt2);
	    out.println("</td>");
	    out.println("<td>");
	    out.println(s.getQuantity());
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
