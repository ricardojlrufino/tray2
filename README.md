# Java SystemTray alternative

Java's implementation of systemtray does not work on all operating systems. This is an alternative if it doesn't work on the OS you want. 

This lib is a wrapper of the class tray implementation in C (https://github.com/zserge/tray), using JNA.

## Features:
 * Java 1.8
 * Super tiny ( < 50kb ) + JNA
 * Menu Icon
 * Icon transparency support
 * Change Menu Icon
 * Sub-Menus (only 1 level)
 * Separators in the menu 

## OS Tested:
  * Ubuntu 22.04  - GNOME 3.36.8
  * Kubuntu 23.10 - KDE 2.27.8
  * Windows 7 x64
  * Windows 10 x64
  * [ ] Windows 11 x64
  * [ ] Mac (need tweeks)

## TODO
* [ ] Disabled/enabled menu items
* [ ] Checked/unchecked menu items
* [ ] Build for Mac
* [ ] Github Actions to build binary/lib
  * https://github.com/libuv/libuv/blob/v1.x/.github/workflows/CI-win.yml
* [ ] Add https://jitpack.io/ release documentation
* [ ] Use Java Systemtray if enviroment is supported 

NOTE: This library is in alpha/experimental stage, if you use, please make leave a mensage(issue).

## Example

See [src/test/java/example/Main.java]()

## Alternatives

* dorkbox/SystemTray - https://github.com/dorkbox/SystemTray
  * But add a lot of dependencies (+ 8MB), like kotlin, javassist, which in my opinion are totally unnecessary

## Build

Build jar for tests 

> mvn -Pdemo package  
> java -jar target/tray2-demo.jar

### Linux Compile

> apt install build-essential make  
> cd src/main/native  
> make

### Windows Cross-Compile

> apt install mingw-w64 make curl
> cd src/main/native  
> make CC=x86_64-w64-mingw32-gcc OS=Windows


### MacOS Cross-Compile (from Linux)

You can use Darling, from: https://github.com/darlinghq/darling/releases

> sudo dpkg -i /home/ricardo/Downloads/darling_0.1.20230310.jammy_amd64.deb
> darling shell
> xcode-select --install
> cd src/main/native
> make 

## License

This software is distributed under [MIT license](http://www.opensource.org/licenses/mit-license.php),
so feel free to integrate it in your commercial products.

