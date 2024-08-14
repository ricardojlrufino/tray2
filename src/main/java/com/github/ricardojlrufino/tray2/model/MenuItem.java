package com.github.ricardojlrufino.tray2.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Systemtray definition/model for {@link com.github.ricardojlrufino.tray2.Tray2Library}
 * @author Ricardo JL Rufino
 */
public class MenuItem {

    public interface MenuListener {
        void call();
    }

    private String name;

    private final List<MenuItem> submenuList = new LinkedList<>();

    private MenuListener callback;

    public MenuItem(String name, MenuListener callback) {
        this.name = name;
        this.callback = callback;
    }

    public void setCallback(MenuListener callback) {
        this.callback = callback;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void fireCallback(){
        if(callback != null) callback.call();
    }

    public boolean add(MenuItem menuItem) {
        return submenuList.add(menuItem);
    }

    public List<MenuItem> getSubmenuList() {
        return submenuList;
    }
}
