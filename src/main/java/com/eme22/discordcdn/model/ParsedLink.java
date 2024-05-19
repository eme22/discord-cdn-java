package com.eme22.discordcdn.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParsedLink {

    private LinkIssue error;

    private LinkData data;

}
