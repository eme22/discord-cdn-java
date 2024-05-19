package com.eme22.discordcdn.util;

import com.eme22.discordcdn.model.LinkData;
import com.eme22.discordcdn.model.LinkIssue;
import com.eme22.discordcdn.model.ParsedLink;
import com.eme22.discordcdn.model.RefreshUrlsRes;
import com.google.gson.Gson;

public class LinkParser {

    private static final Gson gson = new Gson();

    public static ParsedLink parseLink(String input) {
        if (input.contains("?"))
            input = input.split("\\?")[0];

        if (input.contains("attachments/"))
            input = input.split("attachments/")[1];

        String[] slashParts = input.split("/");
        if (slashParts.length != 3)
            return new ParsedLink(LinkIssue.INVALID_SLASH_AMOUNT, null);

        String channelID = slashParts[0];
        String fileID = slashParts[1];
        String fileName = slashParts[2];

        try {
            Long.parseLong(channelID);
        } catch (NumberFormatException e) {
            return new ParsedLink(LinkIssue.CHANNEL_ID_NAN, null);
        }

        try {
            Long.parseLong(fileID);
        } catch (NumberFormatException e) {
            return new ParsedLink(LinkIssue.FILE_ID_NAN, null);
        }

        if (!fileName.contains("."))
            return new ParsedLink(LinkIssue.FILENAME_NO_DOT, null);

        LinkData data = new LinkData(Long.parseLong(channelID), Long.parseLong(fileID), fileName);
        return new ParsedLink(LinkIssue.NONE, data);
    }


    public static RefreshUrlsRes parseRefreshUrlsRes(String json) {
        return gson.fromJson(json, RefreshUrlsRes.class);
    }

}