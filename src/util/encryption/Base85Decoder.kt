package util.encryption

import java.math.BigDecimal
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.regex.Pattern


class Base85Decoder {
    companion object {

        private const val ASCII_SHIFT = 33
        private val BASE85_PW = intArrayOf(
            1,
            85,
            85 * 85,
            85 * 85 * 85,
            85 * 85 * 85 * 85
        )
        private val REMOVE_WHITESPACE = Pattern.compile("\\s+")
        fun Base85Decode(`in`: String?): String {
            requireNotNull(`in`) { "null value" }
            val inputLength = `in`.length
            val zCount = `in`.chars().filter { c: Int -> c == 'z'.code }.count()
            val uncompressedZLength = BigDecimal.valueOf(zCount).multiply(BigDecimal.valueOf(4))
            val uncompressedNonZLength = BigDecimal.valueOf(inputLength - zCount)
                .multiply(BigDecimal.valueOf(4))
                .divide(BigDecimal.valueOf(5))
            val uncompressedLength = uncompressedZLength.add(uncompressedNonZLength)
            val byteBuff = ByteBuffer.allocate(uncompressedLength.toInt())
            val payload = `in`.toByteArray(StandardCharsets.US_ASCII)
            val chunk = ByteArray(5)
            var chunkIndex = 0
            for (i in payload.indices) {
                val currByte = payload[i]
                if (currByte == 'z'.code.toByte()) {
                    chunk[chunkIndex++] = '!'.code.toByte()
                    chunk[chunkIndex++] = '!'.code.toByte()
                    chunk[chunkIndex++] = '!'.code.toByte()
                    chunk[chunkIndex++] = '!'.code.toByte()
                    chunk[chunkIndex++] = '!'.code.toByte()
                } else {
                    chunk[chunkIndex++] = currByte
                }
                if (chunkIndex == 5) {
                    byteBuff.put(decodeChunk(chunk))
                    Arrays.fill(chunk, 0.toByte())
                    chunkIndex = 0
                }
            }
            if (chunkIndex > 0) {
                val numPadded = chunk.size - chunkIndex
                Arrays.fill(chunk, chunkIndex, chunk.size, 'u'.code.toByte())
                val paddedDecode = decodeChunk(chunk)
                for (i in 0 until paddedDecode.size - numPadded) {
                    byteBuff.put(paddedDecode[i])
                }
            }
            byteBuff.flip()
            return String(Arrays.copyOf(byteBuff.array(), byteBuff.limit()))
        }

        private fun intToByte(value: Int): ByteArray {
            return byteArrayOf((value ushr 24).toByte(), (value ushr 16).toByte(), (value ushr 8).toByte(), value.toByte())
        }

        private fun decodeChunk(chunk: ByteArray): ByteArray {
            require(chunk.size == 5) { "chunk size not 5" }
            var value = 0
            value += (chunk[0] - ASCII_SHIFT) * BASE85_PW[4]
            value += (chunk[1] - ASCII_SHIFT) * BASE85_PW[3]
            value += (chunk[2] - ASCII_SHIFT) * BASE85_PW[2]
            value += (chunk[3] - ASCII_SHIFT) * BASE85_PW[1]
            value += (chunk[4] - ASCII_SHIFT) * BASE85_PW[0]
            return intToByte(value)
        }
    }
}