/*1:*/
#line 8 "./gcd-lcm.w"
*
#include <stdio.h> 
int gcd(int a,int b){
if(b!=0)
return gcd(b,a%b);
else
return a;
}

/*:1*//*2:*/
#line 22 "./gcd-lcm.w"
*
int lcm(int a,int b){
return(a*b)%gcd(a,b);
}

/*:2*/
