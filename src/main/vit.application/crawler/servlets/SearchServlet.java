package crawler.servlets;

import jpa.AnimeJPA;
import model.anime47.MovieEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet",urlPatterns = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String txtSearchValue = req.getParameter("txtSearchValue");
        AnimeJPA animeJPA = new AnimeJPA();
        try {
            List<MovieEntity> movieEntities = animeJPA.searchByLikeName(txtSearchValue);
            HttpSession session = req.getSession();
            session.setAttribute("MOVIES",movieEntities);
        }finally {
            req.getRequestDispatcher("searchPage.jsp").forward(req,resp);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
