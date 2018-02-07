package com.FrostedIsles.Notifier;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

import org.bukkit.craftbukkit.libs.jline.internal.Log;

public class Responder {
	private final char DELIMITER = '\0';
	
	String message;
	
	//
	synchronized public boolean NotifyAll(Socket connection) {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			
			String receive = input.readLine();
			
			if (receive == null) {
				return false;
			}
			
			output.writeBytes(message);
			return true;
		} catch (SocketException e) {
			Log.info("Client disconnected.");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void SetNotifyMessage(String title, String msg) {
		message = title + DELIMITER + msg;
	}
}
