/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.wifi.swdev.surf.api;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Sebastian
 */
public class Location implements Serializable{
    
    private String name;
    private String region;
    private String country;
    private float lat;
    private float lon;
    private String tz_id;
    private int localtime_epoch;
    private String localtime;

    public Location() {
    }

    public Location(String name, String region, String country, float lat, float lon, String tz_id, int localtime_epoch, String localtime) {
        this.name = name;
        this.region = region;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
        this.tz_id = tz_id;
        this.localtime_epoch = localtime_epoch;
        this.localtime = localtime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getTz_id() {
        return tz_id;
    }

    public void setTz_id(String tz_id) {
        this.tz_id = tz_id;
    }

    public int getLocaltime_epoch() {
        return localtime_epoch;
    }

    public void setLocaltime_epoch(int localtime_epoch) {
        this.localtime_epoch = localtime_epoch;
    }

    public String getLocaltime() {
        return localtime;
    }

    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.region);
        hash = 83 * hash + Objects.hashCode(this.country);
        hash = 83 * hash + Float.floatToIntBits(this.lat);
        hash = 83 * hash + Float.floatToIntBits(this.lon);
        hash = 83 * hash + Objects.hashCode(this.tz_id);
        hash = 83 * hash + this.localtime_epoch;
        hash = 83 * hash + Objects.hashCode(this.localtime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (Float.floatToIntBits(this.lat) != Float.floatToIntBits(other.lat)) {
            return false;
        }
        if (Float.floatToIntBits(this.lon) != Float.floatToIntBits(other.lon)) {
            return false;
        }
        if (this.localtime_epoch != other.localtime_epoch) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.region, other.region)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.tz_id, other.tz_id)) {
            return false;
        }
        if (!Objects.equals(this.localtime, other.localtime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Location{" + "name=" + name + ", region=" + region + ", country=" + country + ", lat=" + lat + ", lon=" + lon + ", tz_id=" + tz_id + ", localtime_epoch=" + localtime_epoch + ", localtime=" + localtime + '}';
    }
    
    
}
