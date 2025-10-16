package com.sirma.task;

import lombok.Getter;

@Getter
public enum Delimiter {
    COMMA(','),
    SEMICOLON(';'),
    TAB('\t'),
    PIPE('|'),
    COLON(':'),
    CARET('^');

    private final char delimiter;

    Delimiter(char delimiter) {
        this.delimiter = delimiter;
    }
}
