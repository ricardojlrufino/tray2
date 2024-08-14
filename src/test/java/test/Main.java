package test;

import com.github.ricardojlrufino.tray2.Tray2Library;
import com.github.ricardojlrufino.tray2.model.MenuItem;
import com.github.ricardojlrufino.tray2.model.Tray2;

import javax.swing.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) {

        Tray2 tray = new Tray2();

        MenuItem menuTest1 = tray.addMenu(new MenuItem("Test1", () -> {
            JOptionPane.showMessageDialog(null, "OK");
        }));

//        menuTest1.add(new MenuItem("Icon12", () -> {
//            tray.setIcon("indicator-messages-new");
//            JOptionPane.showMessageDialog(null, "Icon1");
//        }));
//
//        menuTest1.add(new MenuItem("Icon22", () -> {
//            tray.setIcon("indicator-messages");
//            JOptionPane.showMessageDialog(null, "Icon2");
//        }));

        MenuItem setIconMenu = tray.addMenu(new MenuItem("SetIcon", () -> {
            JOptionPane.showMessageDialog(null, "OK2");
        }));

//        setIconMenu.add(new MenuItem("Icon1", () -> {
//            tray.setIcon("indicator-messages-new");
//            JOptionPane.showMessageDialog(null, "Icon1");
//        }));
//
//        setIconMenu.add(new MenuItem("Icon2", () -> {
//            tray.setIcon("indicator-messages");
//            JOptionPane.showMessageDialog(null, "Icon2");
//        }));


        MenuItem setText = tray.addMenu(new MenuItem("SetText", () -> {
        }));

        setText.add(new MenuItem("Text1", () -> {
            tray.setIcon("indicator-messages-new");
            JOptionPane.showMessageDialog(null, "Text1");
        }));

        setText.add(new MenuItem("Text2", () -> {
            tray.setIcon("indicator-messages");
            JOptionPane.showMessageDialog(null, "Text2");
        }));

        tray.addMenu(new MenuItem("-", () -> {}));

        tray.addMenu(new MenuItem("Exit", () -> {
            System.exit(0);
        }));

        URL resource = Main.class.getResource("/icon.png");
        tray.setIcon(resource != null ? resource.getFile() : null);

        Tray2Library.INSTANCE.create(tray);
    }
}