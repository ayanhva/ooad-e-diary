package ooad.project.ediary.model.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private String code;

    public NotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public NotFoundException(String message) {
        super(message);
    }
}
