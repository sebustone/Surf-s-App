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
public class Condition implements Serializable{
    
    private String icon;

    public Condition() {
    }

    public Condition(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.icon);
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
        final Condition other = (Condition) obj;
        if (!Objects.equals(this.icon, other.icon)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Condition{" + "icon=" + icon + '}';
    }
    
    
    
}
