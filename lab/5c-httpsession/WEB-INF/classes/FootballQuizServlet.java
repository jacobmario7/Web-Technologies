import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FootballQuizServlet")
public class FootballQuizServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String[][] QUESTIONS = {
        {"Which country has won the most FIFA World Cups?", "Brazil"},
        {"Who is known as the 'King of Football'?", "Pele"},
        {"Which club has won the most UEFA Champions League titles?", "Real Madrid"}
    };

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        // Check if restarting the quiz
        String restart = request.getParameter("restart");
        if (restart != null && restart.equals("true")) {
            session.invalidate(); // Invalidate session to restart
            response.sendRedirect("FootballQuizServlet");
            return;
        }

        // Retrieve current quiz state from the session
        Integer questionIndex = (Integer) session.getAttribute("questionIndex");
        Integer score = (Integer) session.getAttribute("score");

        if (questionIndex == null) {
            // Initialize quiz session
            questionIndex = 0;
            score = 0;
            session.setAttribute("questionIndex", questionIndex);
            session.setAttribute("score", score);
        }

        response.setContentType("text/html");
        response.getWriter().println("<html><body style='font-family: Arial; background-color: #263159; color: #FFFBEB; text-align: center; padding: 20px;'>");

        if (questionIndex < QUESTIONS.length) {
            // Display the current question
            String question = QUESTIONS[questionIndex][0];
            response.getWriter().println("<h1>Football Quiz</h1>");
            response.getWriter().println("<h2>Question " + (questionIndex + 1) + ": " + question + "</h2>");
            response.getWriter().println("<form action='FootballQuizServlet' method='post'>");
            response.getWriter().println("<input type='text' name='answer' placeholder='Your Answer' required>");
            response.getWriter().println("<button type='submit'>Submit Answer</button>");
            response.getWriter().println("</form>");
        } else {
            // Quiz finished, display final score
            response.getWriter().println("<h1>Quiz Completed!</h1>");
            response.getWriter().println("<h2>Your Final Score: " + score + "/" + QUESTIONS.length + "</h2>");
            response.getWriter().println("<a href='FootballQuizServlet?restart=true' style='color: #FFFBEB;'>Restart Quiz</a>");
        }

        response.getWriter().println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("FootballQuizServlet");
            return;
        }

        // Get current question index and score from session
        Integer questionIndex = (Integer) session.getAttribute("questionIndex");
        Integer score = (Integer) session.getAttribute("score");

        if (questionIndex != null && questionIndex < QUESTIONS.length) {
            // Check the answer
            String correctAnswer = QUESTIONS[questionIndex][1];
            String userAnswer = request.getParameter("answer");

            if (correctAnswer.equalsIgnoreCase(userAnswer.trim())) {
                score++;
            }

            // Update session attributes
            session.setAttribute("questionIndex", questionIndex + 1);
            session.setAttribute("score", score);
        }

        response.sendRedirect("FootballQuizServlet");
    }
}
