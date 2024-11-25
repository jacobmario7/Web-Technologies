import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FootballSessionServlet")
public class FootballSessionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve session data from form fields
        String player = request.getParameter("player");
        String goals = request.getParameter("goals");

        // Default values if no input
        if (player == null || player.isEmpty()) {
            player = "Lionel Messi";
        }
        if (goals == null || goals.isEmpty()) {
            goals = "0";
        }

        // Update goals if form is submitted
        int updatedGoals = Integer.parseInt(goals) + 1;

        // Generate HTML with hidden fields for session tracking
        response.setContentType("text/html");
        response.getWriter().println("<html><body style='font-family: Arial, sans-serif;'>");
        response.getWriter().println("<h1>Football Player Session Handling</h1>");
        response.getWriter().println("<p>Player: " + player + "</p>");
        response.getWriter().println("<p>Goals Scored: " + updatedGoals + "</p>");

        response.getWriter().println("<form action='FootballSessionServlet' method='post'>");
        response.getWriter().println("<input type='hidden' name='player' value='" + player + "'>");
        response.getWriter().println("<input type='hidden' name='goals' value='" + updatedGoals + "'>");
        response.getWriter().println("<button type='submit'>Score Another Goal</button>");
        response.getWriter().println("</form>");

        response.getWriter().println("<form action='FootballSessionServlet' method='post'>");
        response.getWriter().println("<label for='player'>Choose another player:</label>");
        response.getWriter().println("<input type='text' id='player' name='player' placeholder='Enter player name'>");
        response.getWriter().println("<input type='hidden' name='goals' value='0'>");
        response.getWriter().println("<button type='submit'>Switch Player</button>");
        response.getWriter().println("</form>");

        response.getWriter().println("</body></html>");
    }
}
