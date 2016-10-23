import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.anteestudio.stock.HibernateUtil;
import org.anteestudio.stock.StockClosedTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.io.PrintWriter;

public class HelloWorldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {

	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	Session session = sessionFactory.openSession();

	resp.setContentType("text/html;charset=UTF-8");

	PrintWriter out = resp.getWriter();

	out.println("<html>");
	out.println("<head><title>My Servlet</title></head>");
	out.println("<body>");
	out.println("<table>");

	List<StockClosedTransaction> result =
	    (List<StockClosedTransaction>)session.
	    createQuery("from StockClosedTransaction StockClosedTransaction order by StockClosedTransaction.sellDate desc").list();

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
	    out.println(s.getBuyDate());
	    out.println("</td>");
	    out.println("<td>");
	    out.println(s.getSellPrice());
	    out.println("</td>");
	    out.println("<td>");
	    out.println(s.getSellDate());
	    out.println("</td>");
	    out.println("<td>");
	    out.println(s.getQuantity());
	    out.println("</td>");
	    out.println("</tr>");
	}

	out.println("</table>");
	out.println("</body>");
	out.println("</html>");

	session.close();

    }
}
