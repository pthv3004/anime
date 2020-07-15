package crawler.servlets;

import constance.WebConstance;
import crawler.animehay.AnimeMoizCrawler;
import crawler.animehay.AnimeMoizDetailsCrawler;
import crawler.helper.CrawlingHelper;
import crawler.helper.ParsingResult;
import jpa.AnimeJPA;
import model.anime47.CategoryEntity;
import model.anime47.MovieEntity;
import model.phimmoiz.AnimeEntity;
import utils.HtmlNormalization;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "AnimeMoizCrawlerServlet",urlPatterns = "/AnimeMoizCrawlerServlet")
public class AnimeMoizCrawlerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // crawl AnimeMoiz
        String link = WebConstance.VIETANIME_PAGE;
        String content = "";
        AnimeJPA jpa = new AnimeJPA();
        AnimeMoizCrawler crawler = new AnimeMoizCrawler();
        List<CategoryEntity> categories = jpa.getCategories();
        AnimeMoizDetailsCrawler detailsCrawler = new AnimeMoizDetailsCrawler();

        for (int i = 2; i <= 21; i++) {
            content = HtmlNormalization.refineHtml(CrawlingHelper.normalizeHTML(link));
            Collection<MovieEntity> movieEntities = crawler.parseXMLToAnimeObject(content);
            for (MovieEntity movieEntity : movieEntities) {
                content = HtmlNormalization.refineDetailHtml(CrawlingHelper.normalizeHTML(movieEntity.getMovieLink()));
                //ghi file xml
//                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\vitpt\\Downloads\\study\\semester_8\\prx\\anime\\anime.html"));
//                bufferedWriter.write(content);
//                bufferedWriter.close();
                detailsCrawler.parseXMLToAnimeObject(content, movieEntity, new ArrayList<>(categories));

                    jpa.persistMovie(movieEntity);
                System.out.println(movieEntity);
                System.out.println("*".repeat(10));
            }
            link = "http://www.phimmoi.net/the-loai/phim-hoat-hinh/page-"+i+".html";
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
