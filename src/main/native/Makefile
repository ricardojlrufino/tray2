# Variáveis
CC = gcc

ifeq ($(OS),Windows)
	TRAY_CFLAGS := -DTRAY_WINAPI=1
	TRAY_LDFLAGS :=
	JNA_ARCH := win32-x86-x64
	LIB_NAME = tray2.dll
else ifeq ($(shell uname -s),Linux)
	TRAY_CFLAGS := -DTRAY_APPINDICATOR=1 $(shell pkg-config --cflags appindicator3-0.1,gtk+-3.0)
	TRAY_LDFLAGS := $(shell pkg-config --libs appindicator3-0.1,gtk+-3.0 ) -lgtk-3 -lappindicator3
	JNA_ARCH := linux-x86-64
	LIB_NAME = libtray2.so
else ifeq ($(shell uname -s),Darwin)
	TRAY_CFLAGS := -DTRAY_APPKIT=1
	TRAY_LDFLAGS := -framework Cocoa -framework Foundation
	JNA_ARCH := darwin-x86-64
	LIB_NAME = libtray2.dylib
endif

CFLAGS = -Wall -Wextra -fPIC $(TRAY_CFLAGS)
LDFLAGS = $(TRAY_LDFLAGS)


# Diretórios
BUILD_DIR = build
DIST_DIR = dist
INSTALL_PATH = ../resources/$(JNA_ARCH)



# Arquivos fonte
SRC_FILES = $(wildcard *.c)

# Arquivos objeto
OBJ_FILES = $(patsubst %.c,$(BUILD_DIR)/%.o,$(notdir $(SRC_FILES)))

# Regra padrão
all: $(DIST_DIR) $(BUILD_DIR) tray.h $(DIST_DIR)/$(LIB_NAME) install

# Download real implementation
tray.h:
	# wget https://raw.githubusercontent.com/zserge/tray/master/tray.h
	# fix all menu items have a check box - https://github.com/zserge/tray/pull/9/commits/59fb20564f1d70a7f3da493cef4f332923d9539f
	curl -O  https://raw.githubusercontent.com/zserge/tray/59fb20564f1d70a7f3da493cef4f332923d9539f/tray.h
	touch $@

# Regra para criar o diretório de build
$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)

# Regra para criar o diretório de distribuição
$(DIST_DIR):
	mkdir -p $(DIST_DIR)
	mkdir -p $(INSTALL_PATH)

# Regra para compilar a biblioteca 
$(DIST_DIR)/$(LIB_NAME): $(OBJ_FILES)
	$(CC) -shared  $(LDFLAGS) -o $@ $^ 

# Regra para compilar os arquivos objeto
$(BUILD_DIR)/%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

# Regra de instalação
example:
	$(CC) $(CFLAGS) $(LDFLAGS)  example/example.c -o $(BUILD_DIR)/example

# Regra de instalação
install: $(DIST_DIR)/$(LIB_NAME)
	mkdir -p $(INSTALL_PATH)
	cp $(DIST_DIR)/$(LIB_NAME) $(INSTALL_PATH)/$(LIB_NAME)

# Regra de limpeza
clean:
	rm -rf $(BUILD_DIR) $(DIST_DIR) tray.h

.PHONY: all install clean example
