package samudra.com.newsapigits;

/**
 * Created by ZAQI on 10/10/2018.
 */

public class NewsItem {
   // private
    /**
     * author
     * gambar
     * title
     * desrkipsi
     * url
     * */
    private String gambar , tittle , deskripsi , url , author , publish ;
    public NewsItem(String Mgambar , String Mtittle , String Murl , String Mauthor, String Mpublish){
        gambar = Mgambar;
        tittle = Mtittle;

        url = Murl;
        author = Mauthor;
        publish = Mpublish;
    }



    public String getAuthor() {
        return author;
    }

    public String getGambar() {
        return gambar;
    }

    public String getTittle() {
        return tittle;
    }

    public String getUrl() {
        return url;
    }

    public String getPublish() {
        return publish;
    }
}
