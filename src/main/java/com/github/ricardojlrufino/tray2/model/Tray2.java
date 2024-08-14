package com.github.ricardojlrufino.tray2.model;

import com.github.ricardojlrufino.tray2.Tray2Library;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Systemtray definition/model for {@link Tray2Library}
 * @author Ricardo JL Rufino
 */
public class Tray2 implements Tray2Library.MenuHandlerCallback {

    public static final Logger logger = Logger.getLogger(Tray2.class.getName());

    private String icon;

    private List<MenuItem> menus = new LinkedList<>();

    public MenuItem addMenu(String name, MenuItem.MenuListener callback) {
        MenuItem menuItem = new MenuItem(name, callback);
        menus.add(menuItem);
        return menuItem;
    }

    public MenuItem addMenu(MenuItem s) {
        menus.add(s);
        return s;
    }

    public boolean removeMenu(MenuItem o) {
        return menus.remove(o);
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public List<MenuItem> getMenus() {
        return menus;
    }

    @Override
    public void onMenuClicked(String text) {

        logger.fine("Menu clicked: " + text);

        for (MenuItem item : menus) {
            if (item.getName().equals(text)) {
                item.fireCallback();
                Tray2Library.INSTANCE.update(this);
            }else{
                for (MenuItem menuItem : item.getSubmenuList()) {
                    if (menuItem.getName().equals(text)) {
                        menuItem.fireCallback();
                        Tray2Library.INSTANCE.update(this);
                    }
                }
            }
        }
    }
}
