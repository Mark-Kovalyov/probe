package samples

import org.junit._
import Assert._
import mayton.image.iterators.scala.{GilbertLazyStream, Point}

@Test
class AppTest {

    @Test
    def test4x4() : Unit = {
        val gilbertLazyStream : GilbertLazyStream = new GilbertLazyStream
        val result = gilbertLazyStream.gilbertPoinsStream(4).toList
        assertEquals(16, result.size)
        assertEquals(Point(0,0), result(0))        
        assertEquals(Point(0,1), result(1))        
        assertEquals(Point(1,1), result(2))
        assertEquals(Point(1,0), result(3))
        assertEquals(Point(2,0), result(4))
        assertEquals(Point(3,0), result(5))
        assertEquals(Point(3,1), result(6))
        assertEquals(Point(2,1), result(7))
        assertEquals(Point(2,2), result(8))
        assertEquals(Point(3,2), result(9))
        assertEquals(Point(3,3), result(10))
        assertEquals(Point(2,3), result(11))
        assertEquals(Point(1,3), result(12))
        assertEquals(Point(1,2), result(13))
        assertEquals(Point(0,2), result(14))
        assertEquals(Point(0,3), result(15))
    }



}


