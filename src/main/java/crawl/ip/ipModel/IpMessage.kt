package crawl.ip.ipModel

import java.io.Serializable

/**
 * Created by paranoid on 17-4-10.
 *
 * 显式地定义serialVersionUID有两种用途：
 * 　1、在某些场合，希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有相同的serialVersionUID；
 * 　2、在某些场合，不希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有不同的serialVersionUID。
 *
 * 具体详情希望大家百度
 */

//想要将该对象存储倒Redis List中，必须对其实现序列化于反序列化，操作Serializable接口
class IpMessage : Serializable {
    var ipAddress: String? = null
    var ipPort: String? = null
    var ipType: String? = null
    var ipSpeed: String? = null

    companion object {
        //关于这个UID希望大家可以下去自己查一查
        private const val serialVersionUID = 1L
    }

    override fun toString(): String {
        return "IpMessage(ipAddress=$ipAddress, ipPort=$ipPort, ipType=$ipType, ipSpeed=$ipSpeed)"
    }
}
