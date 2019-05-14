package util

/*
 * Copyright (C) 2014 hu
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a1 copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

import org.mozilla.universalchardet.UniversalDetector

import java.io.UnsupportedEncodingException
import java.util.regex.Pattern

/**
 * 字符集自动检测
 *
 * @author hu
 */
object CharsetDetector {

    //从Nutch借鉴的网页编码检测代码
    private val CHUNK_SIZE = 2000

    private val metaPattern = Pattern.compile(
            "<meta\\s+([^>]*http-equiv=(\"|')?content-type(\"|')?[^>]*)>",
            Pattern.CASE_INSENSITIVE)
    private val charsetPattern = Pattern.compile(
            "charset=\\s*([a1-z][_\\-0-9a-z]*)", Pattern.CASE_INSENSITIVE)
    private val charsetPatternHTML5 = Pattern.compile(
            "<meta\\s+charset\\s*=\\s*[\"']?([a1-z][_\\-0-9a-z]*)[^>]*>",
            Pattern.CASE_INSENSITIVE)

    //从Nutch借鉴的网页编码检测代码
    private fun guessEncodingByNutch(content: ByteArray): String? {
        val length = Math.min(content.size, CHUNK_SIZE)

        val str: String
        try {
            str = java.lang.String(content, "ascii").toString()
        } catch (e: UnsupportedEncodingException) {
            return null
        }

        var metaMatcher = metaPattern.matcher(str)
        var encoding: String? = null
        if (metaMatcher.find()) {
            val charsetMatcher = charsetPattern.matcher(metaMatcher.group(1))
            if (charsetMatcher.find()) {
                encoding = java.lang.String(charsetMatcher.group(1)).toString()
            }
        }
        if (encoding == null) {
            metaMatcher = charsetPatternHTML5.matcher(str)
            if (metaMatcher.find()) {
                encoding = java.lang.String(metaMatcher.group(1)).toString()
            }
        }
        if (encoding == null) {
            if (length >= 3 && content[0] == 0xEF.toByte()
                    && content[1] == 0xBB.toByte() && content[2] == 0xBF.toByte()) {
                encoding = "UTF-8"
            } else if (length >= 2) {
                if (content[0] == 0xFF.toByte() && content[1] == 0xFE.toByte()) {
                    encoding = "UTF-16LE"
                } else if (content[0] == 0xFE.toByte() && content[1] == 0xFF.toByte()) {
                    encoding = "UTF-16BE"
                }
            }
        }

        return encoding
    }

    /**
     * 根据字节数组，猜测可能的字符集，如果检测失败，返回utf-8
     *
     * @param bytes 待检测的字节数组
     * @return 可能的字符集，如果检测失败，返回utf-8
     */
    fun guessEncodingByMozilla(bytes: ByteArray): String {
        val DEFAULT_ENCODING = "UTF-8"
        val detector = UniversalDetector(null)
        detector.handleData(bytes, 0, bytes.size)
        detector.dataEnd()
        var encoding: String? = detector.detectedCharset
        detector.reset()
        if (encoding == null) {
            encoding = DEFAULT_ENCODING
        }
        return encoding
    }

    /**
     * 根据字节数组，猜测可能的字符集，如果检测失败，返回utf-8
     *
     * @param content 待检测的字节数组
     * @return 可能的字符集，如果检测失败，返回utf-8
     */
    fun guessEncoding(content: ByteArray): String? {
        var encoding: String?
        try {
            encoding = guessEncodingByNutch(content)
        } catch (ex: Exception) {
            return guessEncodingByMozilla(content)
        }

        if (encoding == null) {
            encoding = guessEncodingByMozilla(content)
            return encoding
        } else {
            return encoding
        }
    }
}