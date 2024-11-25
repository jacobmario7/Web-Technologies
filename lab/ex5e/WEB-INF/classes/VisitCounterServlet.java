import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/visitCounter")
public class VisitCounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Setting content type of response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve session object
        HttpSession session = request.getSession(true);

        // Get unique user identifier, here we're using a simple parameter for demo
        // purposes
        String userId = request.getParameter("userId");
        if (userId == null || userId.isEmpty()) {
            userId = "defaultUser"; // You can implement logic for real user identity here
        }

        // Retrieve visit count from session
        Integer visitCount = (Integer) session.getAttribute(userId);

        if (visitCount == null) {
            // If the user is visiting for the first time, initialize count to 1
            visitCount = 1;
        } else {
            // Increment the visit count if the user has visited before
            visitCount += 1;
        }

        // Store updated visit count in session
        session.setAttribute(userId, visitCount);

        // Output the visit information
        out.println("<h1>Hello, " + userId + "!</h1>");
        out.println("<h2>You have visited this page " + visitCount + " times.</h2>");

        // Optionally store visit count in a cookie for persistence across sessions
        Cookie visitCookie = new Cookie("visitCount", visitCount.toString());
        visitCookie.setMaxAge(60 * 60 * 24); // Cookie expiration in seconds (1 day)
        response.addCookie(visitCookie);

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}