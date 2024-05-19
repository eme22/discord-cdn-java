package com.eme22.discordcdn;

import com.eme22.discordcdn.model.LinkIssue;
import com.eme22.discordcdn.model.ParsedLink;
import com.eme22.discordcdn.model.RefreshUrlsRes;
import com.eme22.discordcdn.util.LinkParser;
import lombok.extern.log4j.Log4j2;
import okhttp3.*;

import java.io.IOException;

@Log4j2
public class Discord {

    private final String discordToken;

    public Discord(String discordToken) {
        this.discordToken = discordToken;
    }

    public RefreshUrlsRes fetchLatestLink(String oldLink) {
        oldLink = prepareLink(oldLink);
        validateLink(oldLink);

        OkHttpClient client = new OkHttpClient();
        Request request = createRequest(oldLink);

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return LinkParser.parseRefreshUrlsRes(response.body().string());
        } catch (Exception ex) {
            log.error("Error fetching latest link: {}", ex.getMessage());
            return null;
        }
    }

    private String prepareLink(String oldLink) {
        return oldLink.contains("https://") ? oldLink : "https://cdn.discordapp.com/" + oldLink;
    }

    private void validateLink(String oldLink) {
        ParsedLink linkData = LinkParser.parseLink(oldLink);
        if (linkData.getError() != LinkIssue.NONE) {
            throw new Error(linkData.toString());
        }
    }

    private Request createRequest(String oldLink) {
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                "{\"attachment_urls\": [\"" + oldLink + "\"]}"
        );
        return new Request.Builder()
                .url("https://discord.com/api/v9/attachments/refresh-urls")
                .post(body)
                .addHeader("Authorization", discordToken)
                .build();
    }
}