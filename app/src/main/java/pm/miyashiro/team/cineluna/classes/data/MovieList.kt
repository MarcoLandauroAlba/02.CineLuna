package pm.miyashiro.team.cineluna.classes.data

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName(value="results")            var results             : List<Movie>
)


/*
public class Root{
    public int page;
    public ArrayList<Result> results;
    public int total_pages;
    public int total_results;
}

 */