/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.catatantech.re.rulesenginedelta.stateful;

/**
 *
 * mewakili tindakan memadamkan api
 * 
 * @author Admin
 */
public class Sprinkler {
	
    private Room room;
    private boolean on;
	
	
	public Sprinkler(Room room) {
		this.room = room;
	}
	

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
    
}
