#include <stdio.h>
#include "../tray_lib.h"

//typedef struct  {
//	const char *text;
//} tray_submenu_ref;
//
//typedef struct {
//	const char *text;
//	int submenu_size;
//	tray_submenu_ref *submenu;
//} tray_menu_ref;
//
//struct tray_menu *submenu;

struct tray_menu_ref *my_menus = (struct tray_menu_ref[]){
           {.text = "menu1"},
           {.text = "Menu2"},
           {.text = "Menu3", .submenu_size = 3, .submenu = (struct tray_menu_ref[]){
                 {.text = "Sub1"},
                 {.text = "Sub2"},
                 {.text = "Sub3"}
           }},
           {.text = NULL}};


int main(){

    printf("ok");
    set_menu(my_menus, 3);
    start();

    return 0;
}