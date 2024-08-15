package com.github.ricardojlrufino.tray2;

import com.github.ricardojlrufino.tray2.model.MenuItem;
import com.github.ricardojlrufino.tray2.model.Tray2;
import com.github.ricardojlrufino.tray2.utils.IconUtils;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Demo {
    public static void main(String[] args) throws IOException {

        Tray2 tray = new Tray2();

        MenuItem menuTest1 = tray.addMenu(new MenuItem("Test1", () -> {
            JOptionPane.showMessageDialog(null, "OK");
        }));

        MenuItem setIconMenu = tray.addMenu(new MenuItem("SetIcon", () -> {
            JOptionPane.showMessageDialog(null, "OK2");
        }));

        setIconMenu.add(new MenuItem("Icon1", () -> {
            try {
                tray.setIcon(IconUtils.getIconPath("/notification1.png"));
            } catch (IOException e) {
            }
        }));

        setIconMenu.add(new MenuItem("Icon2", () -> {
            try {
                tray.setIcon(IconUtils.getIconPath("/notification2.png"));
            } catch (IOException e) {
            }
        }));


        MenuItem setText = tray.addMenu(new MenuItem("SetText", () -> {
        }));

        setText.add(new MenuItem("Text1", () -> {
            // TODO: change menu text
            JOptionPane.showMessageDialog(null, "Text1");
        }));

        setText.add(new MenuItem("Text2", () -> {
            // TODO: change menu text
            JOptionPane.showMessageDialog(null, "Text2");
        }));

        tray.addMenu(new MenuItem("-", () -> {}));

        tray.addMenu(new MenuItem("Exit", () -> {
            System.exit(0);
        }));

        tray.setIcon(IconUtils.getIconPath("/notification.png"));

        Tray2Library.INSTANCE.create(tray);

    }
}