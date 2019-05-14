package a.savefile

import java.io.*
import java.net.URL
import java.net.URLConnection
import java.util.ArrayList
import java.util.Scanner

import java.lang.System.out

/**
 * Created by hg_yi on 17-5-16.
 */
class ImageFile//设置线程需要的参数
(begin: Int, last: Int, imageUrls: ArrayList<String>) : Runnable {
    private var inputStream: InputStream? = null
    private var outputStream: FileOutputStream? = null
    private var begin = 0
    private var last = 0
    private var imageUrls: List<String>

    init {
        this.begin = begin
        this.last = last
        this.imageUrls = imageUrls
    }

    override fun run() {
        for (i in begin until last) {
            out.println(imageUrls[i])

            try {
                val url = URL(imageUrls[i])
                val conn = url.openConnection()
                conn.connectTimeout = 1000
                conn.readTimeout = 5000
                conn.connect()
                inputStream = conn.getInputStream()
            } catch (e: Exception) {
                continue
            }

            out.println("success!!!!!!!!!!!!!!!!!!!!!!!!!!")

            //创建文件，以url名为文件名
            val filename = dir + '/'.toString() + imageUrls[i].substring(imageUrls[i].lastIndexOf('/') + 1)
            val file1 = File(filename)
            if (!file1.exists()) {
                file1.createNewFile()

                outputStream = FileOutputStream(File(filename))
                val buf = ByteArray(102400)
                var length = 0

                while (length.run {
                            length = inputStream!!.read(buf, 0, buf.size)
                            length != -1
                        }
                ) {
                    outputStream!!.write(buf, 0, length)
                }
            }
        }
    }

    companion object {
        internal var dir: String? = null

        //创建文件夹
        fun createDir() {
            val scanner = Scanner(System.`in`)
            dir = scanner.nextLine()

            val file = File(dir!!)
            if (file.exists()) {
                out.println("dir is exists")
            } else {
                file.mkdir()
            }
        }
    }
}
