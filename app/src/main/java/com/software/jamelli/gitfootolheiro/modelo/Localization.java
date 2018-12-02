package com.software.jamelli.gitfootolheiro.modelo;

public class Localization {
    private double latitude, longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Localization() {
    }

    public Localization(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Localization{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
