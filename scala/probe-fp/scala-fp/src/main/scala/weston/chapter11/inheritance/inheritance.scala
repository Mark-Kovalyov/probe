package weston.chapter11.inheritance

import java.io.File
import java.nio.CharBuffer

trait Readable {
  def read(charbuffer : CharBuffer) : Int
}

class FileReader(file : File) extends Readable with AutoCloseable{
  override def read(charbuffer: CharBuffer): Int = {
    0
  }

  override def close(): Unit = {

  }
}

object scala {

  def main(args : Array[String]) = {

  }

}
