package com.github.ricardojlrufino.tray2;

import com.github.ricardojlrufino.tray2.model.MenuItem;
import com.github.ricardojlrufino.tray2.model.Tray2;
import com.sun.jna.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Native/JNA Wrapper for library <b>tray2</b><br>
 */
public interface Tray2Library extends Library {

    public static final Logger logger = Logger.getLogger(Tray2Library.class.getName());

    public static final Tray2Library INSTANCE = (Tray2Library) Native.load("tray2", Tray2Library.class);

    default void update(Tray2 tray) {
        Tray2Library.INSTANCE.set_icon(tray.getIcon());
    }

    default void create(Tray2 tray) {

        // Initialize menus
        menu_item menuRef = new menu_item();
        final menu_item[] array = (menu_item[]) menuRef.toArray(tray.getMenus().size());

        // Add to list
        List<MenuItem> menus = tray.getMenus();
        for (int i = 0; i < menus.size(); i++) {
            MenuItem menuItem = menus.get(i);
            array[i].text = menuItem.getName();
            List<MenuItem> submenuList = menuItem.getSubmenuList();

            if(!submenuList.isEmpty()){
                menu_item submenuRef = new menu_item();
                menu_item[] submenu = (menu_item[]) submenuRef.toArray(submenuList.size());
                for (int j = 0; j < submenuList.size(); j++) {
                    MenuItem submenuModel = submenuList.get(j);
                    submenu[j].text = submenuModel.getName();
                    submenuModel.setSubMenuNativeRef(submenu[j]);
                }

                array[i].submenu = submenu;
                array[i].submenu_size = submenuList.size();
                menuItem.setSubMenuNativeRef(submenuRef);
            }else{
                //array[i].submenu = Pointer.NULL;
            }
        }

        Tray2Library.INSTANCE.set_menu(menuRef, array.length);
        Tray2Library.INSTANCE.set_menu_handler(tray);
        Tray2Library.INSTANCE.set_icon(tray.getIcon());
        Tray2Library.INSTANCE.start();

//        while(Tray2Library.INSTANCE.tray2_loop() == 0){
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//            }
//        }
    }

    public interface MenuHandlerCallback extends Callback {
        void onMenuClicked(String text);
    }

    /**
     * Original signature : <code>void set_menu_handler(set_menu_handler_handler_callback*)</code><br>
     * <i>native declaration : line 29</i>
     */
    void set_menu_handler(MenuHandlerCallback handler);

    void set_icon(String icon);

    void set_menu_text(String text, int index);

    void set_menu(menu_item.ByReference menus, int size);

//    void tray2_init();
//
//    int tray2_loop();

    void start();

    @Structure.FieldOrder({"text", "submenu_size", "submenu"})
    public static class menu_item extends Structure implements Structure.ByReference{

        public String text;

        public int submenu_size = 0;

        public menu_item[] submenu = new menu_item[1];

    }
}
