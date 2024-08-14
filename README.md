# Java SystemTray alternative

Java's implementation of systemtray does not work on all operating systems. This is an alternative if it doesn't work on the OS you want. 

This lib is a wrapper of the class tray implementation in C (https://github.com/zserge/tray), using JNA.

## Features:
 * Super tiny ( < 50kb ) + JNA
 * Menu Icon
 * Icon transparency support
 * Change Menu Icon
 * Sub-Menus (only 1 level)
 * Separators in the menu 


## OS Tested:
 * Ubuntu 22.04 - GNOME 3.36.8
 * [ ] Windows
 * [ ] Mac

## TODO
* [ ] Disabled/enabled menu items
* [ ] Checked/unchecked menu items
* [ ] Build for Windows
* [ ] Build for Mac
* [ ] Github Actions to build binary/lib
* [ ] Add https://jitpack.io/ release documentation
* [ ] Use Java Systemtray if enviroment is supported 

NOTE: This library is in alpha/experimental stage, if you use, please make leave a mensage(issue).

## Example

See [src/test/java/example/Main.java]()

## Alternatives

* dorkbox/SystemTray - https://github.com/dorkbox/SystemTray
  * But add a lot of dependencies (+ 8MB), like kotlin, javassist, which in my opinion are totally unnecessary

## License

This software is distributed under [MIT license](http://www.opensource.org/licenses/mit-license.php),
so feel free to integrate it in your commercial products.

## Build

### Windows Cross-Compile

> apt install mingw-w64 make wget
> cd src/main/native
> make CC=x86_64-w64-mingw32-gcc OS=Windows_NT