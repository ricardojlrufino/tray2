#include <stdio.h>
#include "tray_lib.h"
 
// struct tray tray = {
//     .icon = TRAY_ICON1,
//     .menu =
//         (struct tray_menu[]){
//             {.text = "Hello", .cb = notify_cb},
//             {.text = "Checked", .checked = 1, .cb = toggle_cb},
//             {.text = "Disabled", .disabled = 1},
//             {.text = "-"},
//             {.text = "SubMenu",
//              .submenu =
//                  (struct tray_menu[]){
//                      {.text = "FIRST", .checked = 1, .cb = submenu_cb},
//                      {.text = "SECOND",
//                       .submenu =
//                           (struct tray_menu[]){
//                               {.text = "THIRD",
//                                .submenu =
//                                    (struct tray_menu[]){
//                                        {.text = "7", .cb = submenu_cb},
//                                        {.text = "-"},
//                                        {.text = "8", .cb = submenu_cb},
//                                        {.text = NULL}}},
//                               {.text = "FOUR",
//                                .submenu =
//                                    (struct tray_menu[]){
//                                        {.text = "5", .cb = submenu_cb},
//                                        {.text = "6", .cb = submenu_cb},
//                                        {.text = NULL}}},
//                               {.text = NULL}}},
//                      {.text = NULL}}},
//             {.text = "-"},
//             {.text = "Quit", .cb = quit_cb},
//             {.text = NULL}},
// };

struct tray tray = {
    .icon = TRAY_ICON1,
    .menu =
        (struct tray_menu[]){
            {.text = "-"},
            {.text = "Quit", .cb = notify_cb},
            {.text = NULL}},
};

void set_menu_handler(menu_handler_t handler) {
    menu_handler = handler;
}

void set_icon(char *icon){
    tray.icon = icon;
    if(tray_started) tray_update(&tray);
}

void set_menu_text(char *text, int index){
    tray.menu[index].text = text;
    tray.menu[index].checked = !tray.menu[index].checked;
}


void set_menu(const tray_menu_ref *menus, int size){
    setvbuf(stdout, NULL, _IONBF, 0);
    for(int i = 0; i < size; i++){

         if(menus[i].submenu_size > 0){
            printf("Create submenu: %s - Size: %d \n",menus[i].text , menus[i].submenu_size);

            int submenu_size = menus[i].submenu_size;

            struct tray_menu *submenu = submenu_cache[submenu_current_used];

            for(int j = 0; j < submenu_size; j++){
                printf(" - %s \n", menus[i].submenu[j].text);
                submenu[j] = (struct tray_menu){.text = "menus[i].submenu[j].text", .cb = notify_cb};
                // menus[i].submenu[j].text
            }

            submenu[submenu_size] = (struct tray_menu){.text = NULL}; // terminator
            submenu_current_used+=submenu_size;
            menus_cache[i] = (struct tray_menu){.text = menus[i].text, .submenu = submenu};
         }else{

            menus_cache[i] = (struct tray_menu){.text = menus[i].text, .cb = notify_cb};

         }
    }

    menus_cache[size] = (struct tray_menu){.text = NULL};

    tray.menu = menus_cache;

    // tray_update(&tray);
}

void notify_cb(struct tray_menu *item) {
  (void)item;
  setvbuf(stdout, NULL, _IONBF, 0);
  printf("[notify_cb] menu: %s \n", item->text);
  if (menu_handler != NULL) {
     menu_handler(item->text);  // Call handler
  }
}

void stop() {
  tray_exit();
}

void start(void)
{
    if (tray_init(&tray) < 0) {
        printf("failed to create tray\n");
        return 1;
    }
    while (tray_loop(1) == 0) {
      tray_started = 1;
        // printf("iteration\n");
    }
}