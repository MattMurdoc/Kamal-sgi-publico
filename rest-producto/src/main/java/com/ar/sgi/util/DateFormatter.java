package com.ar.sgi.util;

import com.ar.sgi.exceptions.ValidationException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateFormatter {

    private static final Logger LOGGER = Logger.getLogger(DateFormatter.class.getName());

    public static Timestamp formatStringToSqlTimestamp(String fromString) throws ValidationException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date parsedDate = dateFormat.parse(fromString);
            return new java.sql.Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new ValidationException(ValidationException.FORMATO_FECHA_INVALIDO);
        }
    }
}
