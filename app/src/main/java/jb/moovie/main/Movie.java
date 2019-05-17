package jb.moovie.main;

public class Movie {

    private String imdbRating;
    private String imdbVotes;
    private String runtime;
    private String language;
    private String released;
    private String imdbID;
    private String plot;
    private String director;
    private String title;
    private String actors;
    private String year;
    private String poster;
    private String genre;
    private String writer;

    public Movie(String poster, String title, String year, String imdbID, String imdbVotes, String imdbRating, String runtime,
                 String released, String plot, String director, String actors, String genre, String writer) {
        this.poster = poster;
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.runtime = runtime;
        this.language = language;
        this.released = released;
        this.plot = plot;
        this.director = director;
        this.actors = actors;
        this.genre = genre;
        this.writer = writer;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String Runtime) {
        this.runtime = Runtime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String Language) {
        this.language = Language;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String Released) {
        this.released = Released;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String Plot) {
        this.plot = Plot;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String Director) {
        this.director = Director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        this.title = Title;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String Actors) {
        this.actors = Actors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String Year) {
        this.year = Year;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String Poster) {
        this.poster = Poster;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String Genre) {
        this.genre = Genre;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String Writer) {
        this.writer = Writer;
    }

    @Override
    public String toString() {
        return "imdbRating = " + imdbRating + ", imdbVotes = " + imdbVotes + ", runtime = " + runtime + ", language = " + language + ", released = " + released + ", imdbID = " + imdbID + ", plot = " + plot + ", director = " + director + ", title = " + title + ", actors = " + actors + ", year = " + year + ", poster = " + poster + ", genre = " + genre + ", writer = " + writer + "]";
    }
}