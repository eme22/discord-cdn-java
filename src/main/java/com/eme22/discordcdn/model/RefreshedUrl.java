package com.eme22.discordcdn.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefreshedUrl {

    private String original;
    private String refreshed;

}
