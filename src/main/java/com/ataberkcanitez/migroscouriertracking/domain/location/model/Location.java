package com.ataberkcanitez.migroscouriertracking.domain.location.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Location {
    private double lat;
    private double lon;

    // Haversine formula for calculating distance between two coordinates
    public double calculateDistance(Location location) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(location.getLat() - this.lat);
        double lonDistance = Math.toRadians(location.getLon() - this.lon);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.lat)) * Math.cos(Math.toRadians(location.getLat()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // convert to km
        return distance;
    }
}
