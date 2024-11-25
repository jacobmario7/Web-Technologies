import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FootballServlet")
public class FootballServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check if the logout parameter is present
        String logoutParam = request.getParameter("logout");
        if (logoutParam != null && logoutParam.equals("true")) {
            // Clear the cookie for logout
            Cookie cookie = new Cookie("favoriteTeam", "");
            cookie.setMaxAge(0); // Expire the cookie immediately
            response.addCookie(cookie);
            response.sendRedirect("FootballServlet");
            return;
        }

        // Check for existing cookies
        Cookie[] cookies = request.getCookies();
        String favoriteTeam = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("favoriteTeam")) {
                    favoriteTeam = cookie.getValue();
                }
            }
        }

        // Generate the response
        response.setContentType("text/html");
        response.getWriter().println("<html><body style='font-family: Arial; background-color: #263159; color: #FFFBEB; text-align: center; padding: 20px;'>");

        if (favoriteTeam == null) {
            // Show login form if no session exists
            response.getWriter().println("<h1>Welcome to Football Fan Zone</h1>");
            response.getWriter().println("<form action='FootballServlet' method='post'>");
            response.getWriter().println("<input type='text' name='team' placeholder='Enter your favorite team' required>");
            response.getWriter().println("<button type='submit'>Login</button>");
            response.getWriter().println("</form>");
        } else {
            // Display session details if cookie exists
            response.getWriter().println("<h1>Welcome Back, Football Fan!</h1>");
            response.getWriter().println("<h2>Your Favorite Team: " + favoriteTeam + "</h2>");
            response.getWriter().println("<a href='FootballServlet?logout=true' style='color: #FFFBEB;'>Logout</a>");
        }

        response.getWriter().println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Handle login
        String team = request.getParameter("team");

        // Create a cookie to store the favorite team
        Cookie teamCookie = new Cookie("favoriteTeam", team);
        teamCookie.setMaxAge(60 * 60 * 24); // Cookie valid for 1 day
        response.addCookie(teamCookie);

        // Redirect to the same servlet
        response.sendRedirect("FootballServlet");
    }
}
