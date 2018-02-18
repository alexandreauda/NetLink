package com.corp.netlink.NetLink.main;

import com.corp.netlink.NetLink.graphic_design.MainWindowNetLink;

public class Main {
	public static void main(String[] args) throws Exception {
		org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);//Avoid log4j warning

		MainWindowNetLink window = new MainWindowNetLink(); //Display the main window
		
	}
}
