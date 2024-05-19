package com.eme22.discordcdn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LinkIssue {
    NONE(""),
    INVALID_SLASH_AMOUNT("Invalid link due to not the right amount of forward slashes"),
    CHANNEL_ID_NAN("The Channel ID should be a valid integer."),
    FILE_ID_NAN("The File ID should be a valid integer."),
    FILENAME_NO_DOT("The File Name should include at least one dot.");

    private final String description;
}
