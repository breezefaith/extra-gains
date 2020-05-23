import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class LibrarySpider {
    public static void solution(String url) {
        try {
            Document doc = Jsoup.connect("http://www.lib.uestc.edu.cn/bysw/list60").get();
            doc.select("div.grid_1_of_3.images_1_of_3 h4").forEach((h4) -> {
                String[] ss = h4.text().split(" ");
                System.out.println("书名：" + ss[0] + ", 作者：" + ss[1]);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String url = "http://www.lib.uestc.edu.cn/bysw/list60";
        solution(url);
    }
}
