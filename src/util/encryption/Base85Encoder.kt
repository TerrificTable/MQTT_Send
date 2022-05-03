package util.encryption

import java.nio.ByteBuffer
import java.util.*

class Base85Encoder {
    companion object {

        private const val ASCII_SHIFT = 33
        private val BASE85_PW = intArrayOf(
            1,
            85,
            85 * 85,
            85 * 85 * 85,
            85 * 85 * 85 * 85
        )


        fun Base85Encode(stringIn: String): String {
            val `in` = stringIn.toByteArray() ?: throw IllegalArgumentException("null value")
            val stringBuffer = StringBuffer(`in`.size * 5 / 4)
            val chunk = ByteArray(4)
            var chunkIndex = 0
            for (i in `in`.indices) {
                val currByte = `in`[i]
                chunk[chunkIndex++] = currByte
                if (chunkIndex == 4) {
                    val value = byteToInt(chunk)
                    if (value == 0) {
                        stringBuffer.append("z")
                    } else {
                        stringBuffer.append(encodeChunk(value))
                    }
                    Arrays.fill(chunk, 0.toByte())
                    chunkIndex = 0
                }
            }
            if (chunkIndex > 0) {
                val numPadded = chunk.size - chunkIndex
                Arrays.fill(chunk, chunkIndex, chunk.size, 0.toByte())
                val value = byteToInt(chunk)
                val encodedChunk = encodeChunk(value)
                for (i in 0 until encodedChunk.size - numPadded) {
                    stringBuffer.append(encodedChunk[i])
                }
            }
            return stringBuffer.toString()
        }

        private fun byteToInt(value: ByteArray?): Int {
            require(!(value == null || value.size != 4)) { "value not 4 bytes long" }
            return ByteBuffer.wrap(value).int
        }

        private fun encodeChunk(value: Int): CharArray {
            var longVal = value.toLong() and 0x00000000ffffffffL
            val encodedChunk = CharArray(5)
            for (i in encodedChunk.indices) {
                encodedChunk[i] = Char((longVal / BASE85_PW[4 - i] + ASCII_SHIFT).toUShort())
                longVal %= BASE85_PW[4 - i]
            }
            return encodedChunk
        }
    }
}
