package project.backend.domain.ticket.entity;

import lombok.Getter;

@Getter
public enum IsPrivate {
    PRIVATE("private"),
    PUBLIC("public");

    private final String status;

    IsPrivate(String status) {
        this.status = status;
    }

}
