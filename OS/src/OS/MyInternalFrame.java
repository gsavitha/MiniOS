package OS;

import javax.swing.JInternalFrame;

import java.awt.event.*;
import java.awt.*;
public class MyInternalFrame extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    public MyInternalFrame() {
    	
        super("", 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable

        setSize(500,300);
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
        ++openFrameCount;
    }
}
