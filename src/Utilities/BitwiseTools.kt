package Utilities

import org.junit.jupiter.api.Test

object BitwiseTools {

    /**
     * Great for setting a bit at whichever position in the byte array
     */
    fun setBitAtPosition(position: Int): Int {
        assert(position <= 32)
        return 1 shl position
    }

}

class BitwiseToolsTest {

    @Test
    fun `test setBitAtPosition - case 1`() {
        BitwiseTools.setBitAtPosition(32)
            .also { println(it) } // 0000 0000 0000 0000 0000 0000 0000 0001
        BitwiseTools.setBitAtPosition(31)
            .also { println(it) } // 1000 0000 0000 0000 0000 0000 0000 0000
        BitwiseTools.setBitAtPosition(30)
            .also { println(it) } // 0100 0000 0000 0000 0000 0000 0000 0000
        BitwiseTools.setBitAtPosition(1)
            .also { println(it) } // 0000 0000 0000 0000 0000 0000 0000 0010
    }

    @Test
    fun `bit manipulation sandbox`() {
        (1 shr 1).also { println(it) }
    }

}