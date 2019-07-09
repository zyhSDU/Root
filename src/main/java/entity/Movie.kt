package entity

class Movie {
    var id: Int = 0
    var name= ""
    var douban_pingfeng= ""
    var douban_pingfeng_renshu= ""
    var star= ""
    var releaseTime = ""
    var url= ""
    var url2= ""
    override fun toString(): String {
        return "Movie(id=$id, name='$name', douban_pingfeng='$douban_pingfeng', douban_pingfeng_renshu='$douban_pingfeng_renshu', star='$star', releaseTime='$releaseTime', url='$url', url2='$url2')"
    }
}
