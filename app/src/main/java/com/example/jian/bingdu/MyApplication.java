package com.example.jian.bingdu;

import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import org.xutils.DbManager;
import org.xutils.x;

public class MyApplication extends Application {
    private DbManager.DaoConfig daoConfig;
    public DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }
    public static int width;
    public static int height;
    @Override
    public void onCreate() {
        super.onCreate();
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
         width = dm.widthPixels;
        height = dm.heightPixels;
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        daoConfig = new DbManager.DaoConfig()
                .setDbName("jian_db")//创建数据库的名称
                .setDbVersion(1)//数据库版本号
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                    }
                });//数据库更新操作
    }

}
