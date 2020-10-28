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
public class WeatherData implements Serializable{
    
    private Location location;
    private Current current;

    public WeatherData() {
    }

    public WeatherData(Location location, Current current) {
        this.location = location;
        this.current = current;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.location);
        hash = 71 * hash + Objects.hashCode(this.current);
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
        final WeatherData other = (WeatherData) obj;
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.current, other.current)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Weatherdata{" + "location=" + location + ", current=" + current + '}';
    }
    
    
    
    
}





