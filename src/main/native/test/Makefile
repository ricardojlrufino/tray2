ifeq ($(OS),Windows_NT)
	TRAY_CFLAGS := -DTRAY_WINAPI=1
	TRAY_LDFLAGS :=
else ifeq ($(shell uname -s),Linux)
	TRAY_CFLAGS := -DTRAY_APPINDICATOR=1 $(shell pkg-config --cflags appindicator3-0.1)
	TRAY_LDFLAGS := $(shell pkg-config --libs appindicator3-0.1) -L../dist -ltray2
else ifeq ($(shell uname -s),Darwin)
	TRAY_CFLAGS := -DTRAY_APPKIT=1
	TRAY_LDFLAGS := -framework Cocoa
endif


CFLAGS := -g $(TRAY_CFLAGS)  -std=c99 -pedantic
LDFLAGS := -g $(TRAY_LDFLAGS)

all: example run

example: example.o
	$(CC) $^ $(LDFLAGS) -o $@
	
example.o: example.c

clean:
	rm -f example.o example example.exe

run:
	LD_LIBRARY_PATH=../dist  ./example