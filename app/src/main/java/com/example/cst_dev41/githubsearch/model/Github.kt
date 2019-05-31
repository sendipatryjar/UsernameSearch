package com.example.cst_dev41.githubsearch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Github(@field:SerializedName("login")
           @field:Expose
           var login: String?,
             @field:SerializedName("avatar_url")
           @field:Expose
           var avatarUrl: String?) {

}