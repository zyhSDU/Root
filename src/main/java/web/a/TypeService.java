//package web.a;
//
//import java.util.List;
//
//public class TypeService{
//    TypeDao cDao=new TypeDao();
//    public  boolean add(types t) {
//        boolean f=false;
//        if(!cDao.name(t.getName())) {
//            cDao.add(t);
//            f=true;
//        }
//        return f;
//    }
//    public void delete(String name) {
//        cDao.delete(name);
//    }
//    public void update(types t) {
//        cDao.update(t);
//    }
//    /**
//     * 通过ID得到一个Course
//     * @return
//     */
//    public types byId(int id) {
//        return cDao.byId(id);
//    }
//    /**
//     * 通过Name得到一个Course
//     * @return
//     */
//    public types byName(String name) {
//        return cDao.byName(name);
//    }
//    /**
//     * 查找
//     * @return
//     */
//    public List<types> select(String name, String teacher, String classroom) {
//        return cDao.select(name, teacher, classroom);
//    }
//    /**
//     * 全部数据
//     * @return
//     */
//    public List<types> list() {
//        return cDao.list();
//    }
//}
