package tools;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import sq.can_greendao.Student;
import sq.can_greendao.StudentDao;

/**
 * Created by sp01 on 2017/3/30.
 */

public class DBTool {

    private static DBTool ourInstance = new DBTool();
    private static StudentDao studentDao;

    public static DBTool getInstance(){
        if (ourInstance == null){
            synchronized (DBTool.class){
                if (ourInstance == null){
                    ourInstance = new DBTool();
                }
            }
        }

        studentDao = MyApp.getDaoSession().getStudentDao();
        return ourInstance;

    }

    private DBTool(){

    }

    // 添加一条数据的方法
    public void insertPerson(Student student){
        studentDao.insert(student);
    }

    // 删除所有
    public void deleteAll(){
        studentDao.deleteAll();
    }

    // 根据某一字段删除
    public void deleteByName(String name){
        DeleteQuery<Student> deleteQuery = studentDao.queryBuilder()
                .where(StudentDao.Properties.Name.eq(name)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }

    // 查询所有
    public List<Student> queryAll(){
        List<Student> list = studentDao.loadAll();
        return list;
    }

    // 查重
    public boolean isSave(String name){
        QueryBuilder<Student> queryBuilder = studentDao.queryBuilder().where(StudentDao.Properties.Name.eq(name));
        Long size = queryBuilder.buildCount().count();
        return size > 0;
    }

}
