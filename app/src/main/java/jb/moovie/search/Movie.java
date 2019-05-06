package jb.moovie.search;

public class Movie {

    private String poster;
    private String title;
    private String year;
    private String id;

    public Movie(String poster, String title, String year, String id) {
        this.poster = poster;
        this.title = title;
        this.year = year;
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
