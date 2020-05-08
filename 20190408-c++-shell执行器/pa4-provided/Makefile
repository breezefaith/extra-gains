# If you are using C++, change shell.c to shell.cpp or shell.cc
SOURCES=parser.cpp shell.cpp
OBJECTS=parser.o shell.o

BINARIES=shell

CC=gcc
CXX=g++

CFLAGS = -std=c99 -D_GNU_SOURCE -Wall
CXXFLAGS = -std=c++11 -D_GNU_SOURCE -Wall -g

all: $(BINARIES)

clean:
	-rm -f $(BINARIES) $(OBJECTS) core

# If you are using C++, change the command to  $(CXX) $(CXXFLAGS) -o $@ $^
shell: $(OBJECTS)
	$(CXX) $(CXXFLAGS) -o $@ $^

# Rules for compiling.
%.o: %.c shell.h
	$(CC) $(CFLAGS) -c -o $@ $<

%.o: %.cpp shell.h
	$(CXX) $(CXXFLAGS) -c -o $@ $<

%.o: %.cc shell.h
	$(CXX) $(CXXFLAGS) -c -o $@ $<
