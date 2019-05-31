package com.example.cst_dev41.githubsearch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GithubResponse {
        @SerializedName("items")
        @Expose
        var items: List<Github>? = null
}

