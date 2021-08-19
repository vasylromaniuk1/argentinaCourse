package com.solvd.insurance.interfaces;

import static com.solvd.insurance.Main.LOGGER;

public class BoatRisk implements IRiskyClient{
    @Override
    public boolean isRisky() {
        return false;
    }

    @Override
    public void description() {
        LOGGER.fatal("This is a boat, ensure safety vest!");
    }
}
