package mayton

interface IPixIterator {
    abstract fun getX(): Int
    abstract fun getY(): Int
    abstract operator fun next(): Boolean
    abstract fun reset()
}