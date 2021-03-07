@*LCD-LCM

In mathematics, the greatest common divisor (gcd) of two or 
more integers, which are not all zero, is the largest 
positive integer that divides each of the integers. 
For example, the gcd of 8 and 12 is 4

@c*
#include <stdio.h>
int gcd(int a, int b) {
  if (b != 0)
    return gcd(b, a % b);
  else
    return a;
}

@
The greatest common divisor can be used to find the least 
common multiple of two numbers when the greatest common 
divisor is known, using the relation

@c*
int lcm(int a,int b) {
  return (a * b) % gcd(a,b);
}

@* Index.
