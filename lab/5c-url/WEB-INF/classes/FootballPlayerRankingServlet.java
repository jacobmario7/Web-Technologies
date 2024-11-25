import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FootballPlayerRankingServlet")
public class FootballPlayerRankingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the request URL or use defaults
        String player = request.getParameter("player");
        String rank = request.getParameter("rank");

        if (player == null) {
            player = "Lionel Messi"; // Default player
        }
        if (rank == null) {
            rank = "1"; // Default rank
        }

        // Generate the next URL with updated rank
        int updatedRank = Integer.parseInt(rank) + 1;
        String nextUrl = "FootballPlayerRankingServlet?player=" + player + "&rank=" + updatedRank;

        // Build the response
        response.setContentType("text/html");
        response.getWriter().println("<html><body style='font-family: Arial, sans-serif;'>");
        response.getWriter().println("<h1>Football Player Ranking</h1>");
        response.getWriter().println("<p>Current Player: " + player + "</p>");
        response.getWriter().println("<p>Current Rank: " + rank + "</p>");
        response.getWriter().println("<a href='" + nextUrl + "'>Increase Rank</a>");
        response.getWriter().println("<p>Click on the link to view the updation</p>");
        response.getWriter().println("</body></html>");
    }
}
