package ru.motrichkin.exceptions;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException implements Supplier<NotFoundException> {

    public NotFoundException(String s) {
        super(s);
    }

    @Override
    public NotFoundException get() {
        return this;
    }
}
