package com.solvd.insurance.interfaces;

import com.solvd.insurance.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MotorcycleRisk implements IRiskyClient{
    private static final Logger logger = LogManager.getLogger(Main.class);
    @Override
    public boolean isRisky() {
        return true;
    }

    @Override
    public void description() {
        logger.info("This is a motorcycle, definitely a high risk!");
    }
}
