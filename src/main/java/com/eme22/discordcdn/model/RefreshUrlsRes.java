package com.eme22.discordcdn.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RefreshUrlsRes {

    @SerializedName("refreshed_urls")
    private List<RefreshedUrl> refreshedUrls;
}