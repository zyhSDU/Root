package value

import util.TimeUtil

object StringValue {
    object DirPath {
        const val data = "data\\"
        const val `in` = "${data}in\\"
        const val out = "${data}out\\"
        const val temp = "${data}temp\\"
        const val test = "${data}test\\"
        fun create(): String {
            return "$temp${TimeUtil.nowDateTime()}\\"
        }
    }

    object URL {
        const val baidu = "http://www.baidu.com"
        const val renren = "http://www.renren.com/"
        const val renrenLogin="http://www.renren.com/SysHome.response"
    }
}