import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalculatePointsServlet")
public class CalculatePointsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Get input values
        int wins = Integer.parseInt(request.getParameter("wins"));
        int draws = Integer.parseInt(request.getParameter("draws"));
        int losses = Integer.parseInt(request.getParameter("losses"));

        // Calculate total points
        int points = (wins * 3) + (draws * 1);

        // Display result
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>Points Calculation</h1>");
        response.getWriter().println("<p>Total Wins: " + wins + "</p>");
        response.getWriter().println("<p>Total Draws: " + draws + "</p>");
        response.getWriter().println("<p>Total Losses: " + losses + "</p>");
        response.getWriter().println("<h2>Total Points: " + points + "</h2>");
        response.getWriter().println("<a href='index.html'>Go Back</a>");
        response.getWriter().println("</body></html>");
    }
}
