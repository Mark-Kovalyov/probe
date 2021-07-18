object ProbeMapReduce {

    def main(args:Array[String]) : Unit = {

        val list = 1.to(100)

        val filtered = list.filter( (x) => x % 2 != 0   &&   x % 3 !=0 )

        filtered.foreach(println)

    }

}
