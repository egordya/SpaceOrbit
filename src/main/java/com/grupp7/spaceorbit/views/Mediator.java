package com.grupp7.spaceorbit.views;

import java.io.FileNotFoundException;

public interface Mediator {
    void notify(Object pointer, MediatorCommand command) throws FileNotFoundException;

}
