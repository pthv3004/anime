package crawler;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constance.WebConstance;
import crawler.anime47.Anime47Crawler;
import crawler.anime47.Anime47DetailsCrawler;
import crawler.helper.CrawlingHelper;
import model.Anime;
import utils.HtmlNormalization;

@WebServlet(name = "CrawlerServlet", urlPatterns = { "/CrawlerServlet" })
public class CrawlerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
        String content = HtmlNormalization.refineHtml(CrawlingHelper.normalizeHTML(WebConstance.ANIME_PAGE));

        Anime47Crawler crawler = new Anime47Crawler();
        Anime47DetailsCrawler detailsCrawler = new Anime47DetailsCrawler();

        detailsCrawler.parseXMLToAnimeModel(content, new Anime());
        while (true) {
            Collection<Anime> animes = crawler.parseXMLToAnimeModel(content);

            for (Anime anime : animes) {
                content = HtmlNormalization.refineHtml(CrawlingHelper.normalizeHTML(anime.getLink()));
                String expression = "<a.*?>";
                content = content.replaceAll(expression, "").replaceAll("</a>", "");
                detailsCrawler.parseXMLToAnimeModel(content, anime);
                System.out.println(anime);
                System.out.println("*".repeat(10));
            }

            break;
        }
    }
}