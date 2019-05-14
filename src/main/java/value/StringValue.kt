package value

import util.TimeUtil

object StringValue {
    object FilePath {
        const val data = "data\\"
        const val `in` = "${data}in\\"
        const val out = "${data}out\\"
        const val temp = "${data}temp\\"
        const val test = "${data}test\\"
        fun create(): String {
            return "$temp${TimeUtil.nowDateTime()}\\"
        }
    }
}