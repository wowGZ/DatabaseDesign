package com.jczdxt.guozhen.utils;

import javax.swing.*;
import java.awt.*;

public class FrameToCenter {
    public FrameToCenter(JFrame jFrame) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        jFrame.setLocation(screenSize.width/2-jFrame.getWidth()/2,screenSize.height/2-jFrame.getHeight()/2);
    }
}
