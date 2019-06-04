package web.a

class types {

    var id: Int = 0
    var name: String? = null
    var teacher: String? = null
    var classroom: String? = null

    constructor()

    constructor(id: Int, name: String, teacher: String, classroom: String) {
        this.id = id
        this.name = name
        this.teacher = teacher
        this.classroom = classroom
    }

    constructor(name: String, teacher: String, classroom: String) {
        this.name = name
        this.teacher = teacher
        this.classroom = classroom
    }
}