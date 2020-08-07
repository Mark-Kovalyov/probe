cmake_minimum_required(VERSION 3.5)

project(Primegen)

add_executable(primegen primegen.c)

set(CMAKE_CXX_COMPILER clang++)
set(CMAKE_C_COMPILER clang)

set_target_properties(
  primegen PROPERTIES
  CXX_STANDARD 11
  CXX_STANDARD_REQUIRED ON
)

