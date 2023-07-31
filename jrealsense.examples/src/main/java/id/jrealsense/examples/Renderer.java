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
package id.jrealsense.examples;

import id.jrealsense.FormatType;
import id.jrealsense.frames.ColorFrame;
import id.jrealsense.utils.FrameUtils;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/** Displays a Swing frame with a canvas inside. All render operations update the canvas. */
public class Renderer {

    private FrameUtils frameUtils = new FrameUtils();
    private JLabel label;
    private JFrame frame;

    /**
     * @param height Height of images we are going to render
     * @param width Width of images we are going to render
     */
    public Renderer(int width, int height) {
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(width + 150, height + 150);
        label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        frame.add(label);
        frame.setVisible(true);
    }

    /** Exit app when user closes the window */
    public Renderer withExitOnClose() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return this;
    }

    /** Set runnable to execute when window is closed */
    public Renderer withOnClose(Runnable r) {
        frame.addWindowListener(
                new WindowAdapter() {
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

    private void update(BufferedImage image) {
        var img = new ImageIcon(image);
        label.setIcon(img);
    }

    public void render(ColorFrame frame, FormatType format) {
        update(frameUtils.toBufferedImage(frame, format));
    }

    public void close() {
        frame.dispose();
    }
}
