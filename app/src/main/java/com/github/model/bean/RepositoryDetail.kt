package client.yalantis.com.githubclient.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Alexey on 07.10.2016.
 */
data class RepositoryDetail(val name: String,
                            val description: String,
                            val stargazers_count: String,
                            val forks_count: String,
                            val created_at: String,
                            val updated_at: String,
                            @SerializedName("source")
                            val parent: Source?)