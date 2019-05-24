package crawl.ip2.ipModel

import java.io.*

/**
 * Created by hg_yi on 17-8-9.
 *
 * java.io.ObjectOutputStream代表对象输出流，它的writeObject(Object obj)方法
 * 可对参数指定的obj对象进行序列化，把得到的字节序列写到一个目标输出流中。
 *
 * java.io.ObjectInputStream代表对象输入流，它的readObject()方法一个源输入流中读
 * 取字节序列，再把它们反序列化为一个对象，并将其返回。
 *
 * 对象序列化包括如下步骤：
 * 　1）创建一个对象输出流，它可以包装一个其他类型的目标输出流，如文件输出流(我这里是字节流)；
 * 　2）通过对象输出流的writeObject()方法写对象。
 *
 * 对象反序列化的步骤如下：
 * 　1）创建一个对象输入流，它可以包装一个其他类型的源输入流，如文件输入流(我这里是字节流)；
 * 　2）通过对象输入流的readObject()方法读取对象。
 */

object SerializeUtil {
    fun serialize(`object`: Any): ByteArray? {
        val oos: ObjectOutputStream
        val baos: ByteArrayOutputStream

        try {
            // 序列化
            baos = ByteArrayOutputStream()
            oos = ObjectOutputStream(baos)
            oos.writeObject(`object`)

            return baos.toByteArray()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    //反序列化
    fun unSerialize(bytes: ByteArray): Any? {
        val bais: ByteArrayInputStream
        val ois: ObjectInputStream

        try {
            // 反序列化
            bais = ByteArrayInputStream(bytes)
            ois = ObjectInputStream(bais)
            return ois.readObject()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}
