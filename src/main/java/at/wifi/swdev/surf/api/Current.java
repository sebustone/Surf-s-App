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
public class Current implements Serializable{
    
    private float temp_c;
    private Condition condition;
    private float wind_kph;
    private String wind_dir;
    private float uv;
    private float gust_kph;

    public Current() {
    }

    public Current(float temp_c, Condition condition, float wind_kph, String wind_dir, float uv, float gust_kph) {
        this.temp_c = temp_c;
        this.condition = condition;
        this.wind_kph = wind_kph;
        this.wind_dir = wind_dir;
        this.uv = uv;
        this.gust_kph = gust_kph;
    }

    public float getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(float temp_c) {
        this.temp_c = temp_c;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public float getWind_kph() {
        return wind_kph;
    }

    public void setWind_kph(float wind_kph) {
        this.wind_kph = wind_kph;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public float getUv() {
        return uv;
    }

    public void setUv(float uv) {
        this.uv = uv;
    }

    public float getGust_kph() {
        return gust_kph;
    }

    public void setGust_kph(float gust_kph) {
        this.gust_kph = gust_kph;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Float.floatToIntBits(this.temp_c);
        hash = 37 * hash + Objects.hashCode(this.condition);
        hash = 37 * hash + Float.floatToIntBits(this.wind_kph);
        hash = 37 * hash + Objects.hashCode(this.wind_dir);
        hash = 37 * hash + Float.floatToIntBits(this.uv);
        hash = 37 * hash + Float.floatToIntBits(this.gust_kph);
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
        final Current other = (Current) obj;
        if (Float.floatToIntBits(this.temp_c) != Float.floatToIntBits(other.temp_c)) {
            return false;
        }
        if (Float.floatToIntBits(this.wind_kph) != Float.floatToIntBits(other.wind_kph)) {
            return false;
        }
        if (Float.floatToIntBits(this.uv) != Float.floatToIntBits(other.uv)) {
            return false;
        }
        if (Float.floatToIntBits(this.gust_kph) != Float.floatToIntBits(other.gust_kph)) {
            return false;
        }
        if (!Objects.equals(this.wind_dir, other.wind_dir)) {
            return false;
        }
        if (!Objects.equals(this.condition, other.condition)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Current{" + "temp_c=" + temp_c + ", condition=" + condition + ", wind_kph=" + wind_kph + ", wind_dir=" + wind_dir + ", uv=" + uv + ", gust_kph=" + gust_kph + '}';
    }
    
    
    
}
