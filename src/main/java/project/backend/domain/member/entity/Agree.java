package project.backend.domain.member.entity;

public enum Agree {
    AGREE("agree"),
    DISAGREE("disagree");

    private final String status;

    Agree(String status) {
        this.status = status;
    }

}

