package com.reel.reserve.respone;

import com.reel.reserve.models.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieResponse {
    private List<Movie> movies;
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalElement;
    private Integer totalPage;
    private boolean lastPage;
}
