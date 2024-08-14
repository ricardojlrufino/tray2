# Java SystemTray alternative

Java's implementation of systemtray does not work on all operating systems. This is an alternative if it doesn't work on the OS you want. 

This lib is a wrapper of the class tray implementation in C (https://github.com/zserge/tray), using JNA.

## Features:
 * Super tiny
 * Menu Icon
 * Change Menu Icon
 * Sub-Menus (only 1 level)

## OS Tested:
 * Ubuntu 22.04 - GNOME 3.36.8
 * [ ] Windows
 * [ ] Mac

## TODO
* [ ] Build for Windows
* [ ] Build for Mac

NOTE: This library is in alpha/experimental stage, if you use, please make leave a mensage(issue).

## Example

See [src/test/java/example/Main.java]()

## License

This software is distributed under [MIT license](http://www.opensource.org/licenses/mit-license.php),
so feel free to integrate it in your commercial products.