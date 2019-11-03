package mayton

class GilbertPixelIterator : IPixIterator {

    private val u = 1

    private var glx: Int = 0
    private var gly: Int = 0

    private fun a(i: Int) {
        if (i > 0) {
            d(i - 1)
            linerel(+u, 0)
            a(i - 1)
            linerel(0, u)
            a(i - 1)
            linerel(-u, 0)
            c(i - 1)
        }
    }

    private fun b(i: Int) {
        if (i > 0) {
            c(i - 1)
            linerel(-u, 0)
            b(i - 1)
            linerel(0, -u)
            b(i - 1)
            linerel(u, 0)
            d(i - 1)
        }
    }

    private fun c(i: Int) {
        if (i > 0) {
            b(i - 1)
            linerel(0, -u)
            c(i - 1)
            linerel(-u, 0)
            c(i - 1)
            linerel(0, u)
            a(i - 1)
        }
    }

    private fun d(i: Int) {
        if (i > 0) {
            a(i - 1)
            linerel(0, u)
            d(i - 1)
            linerel(u, 0)
            d(i - 1)
            linerel(0, -u)
            b(i - 1)
        }
    }

    private fun linerel(x: Int, y: Int) {
        glx += x
        gly += y
    }

    private fun moveto(x: Int, y: Int) {
        glx = x
        gly = y
    }


    override fun getX(): Int {
        return 0
    }

    override fun getY(): Int {
        return 0
    }

    override fun next(): Boolean {
        return false
    }

    override fun reset() {

    }
}