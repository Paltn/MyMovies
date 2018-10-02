package com.example.pcardoso.mymoviesproject.model;

import java.util.List;

public class Pagination<T> {

    List<T> results;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
