package com.eme22.discordcdn.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LinkData {
    private long channelID;
    private long fileID;
    private String fileName;
}