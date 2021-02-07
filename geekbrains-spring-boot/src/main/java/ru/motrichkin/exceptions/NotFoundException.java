package ru.motrichkin.exceptions;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String s) {
        super(s);
    }

}
