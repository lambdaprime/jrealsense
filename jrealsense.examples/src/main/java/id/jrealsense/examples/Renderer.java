/*
 * Copyright 2021 jrealsense project
 * 
 * Website: https://github.com/lambdaprime/jrealsense
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Authors:
 * - lambdaprime <intid@protonmail.com>
 */
package id.jrealsense.examples;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.nio.ByteBuffer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Displays a Swing frame with a canvas inside.
 * All render operations update the canvas.
 */
public class Renderer {

    private JLabel label;
    private int width;
    private int height;
    private JFrame frame;

    /**
     * @param height Height of images we are going to render
     * @param width Width of images we are going to render
     */
    public Renderer(int width, int height) {
        this.height = height;
        this.width = width;
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(width + 150, height + 150);
        label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        frame.add(label);
        frame.setVisible(true);
    }

    /**
     * Exit app when user closes the window
     */
    public Renderer withExitOnClose() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return this;
    }
    
    /**
     * Set runnable to execute when window is closed
     */
    public Renderer withOnClose(Runnable r) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                r.run();
            }
        });
        return this;
    }
    
    public boolean isClosed() {
        return !frame.isVisible();
    }
    /**
     * @param data Image pixels
     * @param type One of the available types from BufferedImage
     */
    public void render(byte[] data, int type) {
        BufferedImage image = new BufferedImage(width, height, type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(data, 0, targetPixels, 0, data.length);  
        update(image);
    }

    private void update(BufferedImage image) {
        var img = new ImageIcon(image);
        label.setIcon(img);
    }

    /**
     * @param data Image pixels
     * @param type One of the available types from BufferedImage
     */
    public void render(ByteBuffer data, int type) {
        byte[] b = null;
        if (data.hasArray()) {
            b = data.array();
        } else {
            b = new byte[data.capacity()];
            data.get(b, 0, b.length);
        }
        render(b, type);
    }

    public void close() {
        frame.dispose();
    }

}
