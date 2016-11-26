package com.company;

import java.awt.Dimension;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class statusbar extends JLabel {

	/** Creates a new instance of StatusBar */
	public statusbar() {
		super();
		super.setPreferredSize(new Dimension(100, 20));
		setMessage("Ready");
	}

	public void setMessage(String message) {
		setText(" " + message);
	}
}