package crawl.ip.database

import crawl.ip.ipModel.IpMessage
import crawl.ip.ipModel.SerializeUtil

/**
 * Created by hg_yi on 17-8-9.
 */
class MyRedis {
    private var jedis = RedisDB.jedis

    //将Redis中保存的对象进行反序列化
    val ipByList: IpMessage?
        get() {
            val rand = (Math.random() * jedis.llen("IpPool")!!).toInt()

            val o = SerializeUtil.unSerialize(jedis.lindex("IpPool".toByteArray(), 0))
            return if (o is IpMessage) {
                o
            } else {
                println("不是IPMessage的一个实例~")
                null
            }
        }

    //将ip信息保存在Redis列表中
    fun setIpToList(ipMessages: List<IpMessage>) {
        for (ipMessage in ipMessages) {
            //首先将ipMessage进行序列化
            val bytes = SerializeUtil.serialize(ipMessage)
            jedis.rpush("IpPool".toByteArray(), bytes)
        }
    }

    fun deleteKey(key: String) {
        jedis.del(key)
    }

    fun close() {
        RedisDB.close(jedis)
    }
}
