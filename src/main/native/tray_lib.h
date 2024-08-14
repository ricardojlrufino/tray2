#ifndef try2_h__
#define try2_h__

#if defined (_WIN32) || defined (_WIN64)
#define TRAY_WINAPI 1
#elif defined (__linux__) || defined (linux) || defined (__linux)
#define TRAY_APPINDICATOR 1
#elif defined (__APPLE__) || defined (__MACH__)
#define TRAY_APPKIT 1
#endif

#include "tray.h" // makefile will download on first run

#if TRAY_APPINDICATOR
#define TRAY_ICON1 "indicator-messages-new"
#elif TRAY_APPKIT
#define TRAY_ICON1 "icon.png"
#elif TRAY_WINAPI
#define TRAY_ICON1 "icon.ico"
#endif

// *************** types

typedef struct tray_menu_ref{
	const char *text;
	int submenu_size;
	struct tray_menu_ref *submenu;
} tray_menu_ref;

// callback
typedef void (*menu_handler_t) (const char*);

// *************** /types

#define MAX_MENU_ITEMS 15

int tray_started = 0;

struct tray tray;

menu_handler_t menu_handler;

struct tray_menu menus_cache[MAX_MENU_ITEMS];

int submenu_current_used = 0;
struct tray_menu submenu_cache[5][MAX_MENU_ITEMS];


extern void set_menu_handler(menu_handler_t handler);

extern void set_menu(const tray_menu_ref *menus, int size);

extern void set_menu_text(char* text, int index);

extern void set_icon(char* icon);

extern void start(void);

extern void stop(void);

// --------------------------------------------------------

void notify_cb(struct tray_menu *item);

#endif  // try2_h__