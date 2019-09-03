package com.example.StudentsViewHolder;

import com.example.ArticleModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseNewsModels {
    private String status;
    private String totalResults;
    @SerializedName("articles")
    List<ArticleModel> articleModels;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticleModel> getArticleModels() {
        return articleModels;
    }

    public void setArticleModels(List<ArticleModel> articleModels) {
        this.articleModels = articleModels;
    }
}
