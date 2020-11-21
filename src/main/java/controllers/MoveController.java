package controllers;

import model.Field;
import model.Figure;
import model.exceptions.AlreadyOccupiedException;
import model.exceptions.InvalidPointException;

import java.awt.*;

public class MoveController {

    public void applyFigure(final Field field,
                            final Point point,
                            final Figure figure)  throws InvalidPointException,
                                                         AlreadyOccupiedException {

        if (field.getFigure(point) != null) {
            throw new AlreadyOccupiedException("Point is Occupied");
        }
        field.setFigure(point,figure);


    }



}
