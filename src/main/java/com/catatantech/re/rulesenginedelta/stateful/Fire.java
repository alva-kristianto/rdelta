/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.catatantech.re.rulesenginedelta.stateful;

/**
 *
 * mewakili event terjadinya kebakaran
 * 
 * @author Admin
 */
public class Fire {
    private Room room;
	
	
	public Fire(Room room) {
		this.room = room;
	}
	

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    
}
