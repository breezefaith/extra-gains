cmake_minimum_required(VERSION 3.13)
project(TestDoubleLinkedList)

set(CMAKE_CXX_STANDARD 14)

find_package(GTest REQUIRED)
find_package(Threads REQUIRED)

add_subdirectory(DoublyLinkedList)
add_executable(DoubleLinkedListTester
        main.cpp
        DoubleLinkedListTest.cpp DoubleLinkedListTest.h)

target_include_directories(DoubleLinkedListTester PRIVATE ${GTEST_INCLUDE_DIRS})
target_link_libraries(DoubleLinkedListTester
        PRIVATE
        DoubleLinkedList
        ${GTEST_BOTH_LIBRARIES}
        Threads::Threads)
target_compile_options(DoubleLinkedListTester PRIVATE -Wall -Werror)
