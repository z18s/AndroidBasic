package com.example.weatherapp;

public final class Settings {

    private static Settings instance;

    static {
        instance = new Settings();
    }

    private Settings() {
    }

    private boolean showPressure = false;
    private boolean useTempC = true;
    private boolean useWindMS = true;

    public boolean isShowPressure() {
        return showPressure;
    }

    public void setShowPressure(boolean showPressure) {
        this.showPressure = showPressure;
    }

    public boolean isUseTempC() {
        return useTempC;
    }

    public void setUseTempC(boolean useTempC) {
        this.useTempC = useTempC;
    }

    public boolean isUseWindMS() {
        return useWindMS;
    }

    public void setUseWindMS(boolean useWindMS) {
        this.useWindMS = useWindMS;
    }

    public static Settings getInstance() {
        return instance;
    }
}