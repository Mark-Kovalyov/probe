package main

import "fmt"
import "reflect"

func sum(s []int, c chan int) {
    sum := 0
    for _, v := range s {
        sum += v
    }
    c <- sum // send sum to c
}

func permutate(

func main() {
    //s := []int{7, 2, 8, -9, 4, 0}
    c := make(chan int, 100)
    //go sum(s[:len(s)/2], c)
    //go sum(s[len(s)/2:], c)
    //x, y := <-c, <-c // receive from c
    //fmt.Println(x, y, x+y)
    x := 10
    y := ""
    fmt.Println("type c = ", reflect.TypeOf(c)) 
    fmt.Println("type x = ", reflect.TypeOf(x)) 
    fmt.Println("type y = ", reflect.TypeOf(y)) 
}


