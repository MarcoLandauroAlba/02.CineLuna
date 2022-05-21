package pm.miyashiro.team.cineluna.classes.data

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName(value="backdrop_path")      var backdrop_path       : String,
    @SerializedName(value="original_title")     var original_title      : String,
    @SerializedName(value="poster_path")        var poster_path         : String,
    @SerializedName(value="vote_average")       var vote_average        : Double
)

/*
public class Result{
    public boolean adult;
    public String backdrop_path;
    public ArrayList<int> genre_ids;
    public int id;
    public String original_language;
    public String original_title;
    public String overview;
    public double popularity;
    public String poster_path;
    public String release_date;
    public String title;
    public boolean video;
    public double vote_average;
    public int vote_count;
}
 */