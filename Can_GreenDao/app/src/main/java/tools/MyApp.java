package tools;

import android.app.Application;
import android.content.Context;

import sq.can_greendao.DaoMaster;
import sq.can_greendao.DaoSession;

/**
 * Created by sp01 on 2017/3/30.
 */

// 在清单文件中加入自己的App
public class MyApp extends Application{
    private static Context context;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    // 对外提供一个获取Context对象的方法
    public static Context getContext(){
        return context;
    }

    public static DaoMaster getDaoMaster(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(),"Food.db",null);
        daoMaster = new DaoMaster(helper.getWritableDb());
        return daoMaster;
    }

    public static DaoSession getDaoSession(){
        if (daoSession == null){
            if (daoMaster == null){
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }


}
