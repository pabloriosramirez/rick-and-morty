package com.pabloriosramirez.api.rickandmorty.util;

import java.util.Date;

import org.slf4j.Logger;

public class DurationUtil {
    public DurationUtil() {
    }

    public static void log(Date start, Logger logger, String message) {
        long duration = (new Date()).getTime() - start.getTime();
        logger.info(String.format("%s completed. Duration = %d", message, duration));
    }
}
