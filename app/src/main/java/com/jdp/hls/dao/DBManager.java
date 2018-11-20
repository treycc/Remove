package com.jdp.hls.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jdp.hls.base.App;
import com.jdp.hls.greendaobean.Area;
import com.jdp.hls.greendaobean.TDict;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/19 0019 下午 4:43
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DBManager {
    private final static String dbName = "JDP_DB";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private final SQLiteDatabase db;

    private DBManager() {
        openHelper = new DaoMaster.DevOpenHelper(App.getContext(), dbName, null);
        db = getWritableDatabase();
    }

    public static DBManager getInstance() {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(App.getContext(), dbName, null);
        }
        return openHelper.getReadableDatabase();
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(App.getContext(), dbName, null);
        }
        return openHelper.getWritableDatabase();
    }

    private TDictDao getDictDao() {
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        return daoSession.getTDictDao();
    }

    public void addDict(TDict TDict) {
        TDictDao bleInfoDao = getDictDao();
        bleInfoDao.insert(TDict);
    }

    public void deleteAllDicts() {
        TDictDao dictDao = getDictDao();
        dictDao.deleteAll();
    }

    public List<TDict> getDictsByConfigType(int configType) {
        List<TDict> results = getDictDao().queryBuilder().where(TDictDao.Properties.ConfigType.eq(configType)).list();
        return results == null ? new ArrayList<>() : results;
    }

    public List<Area> getAreas() {
        List<Area> results = getAreaDao().loadAll();
        return results == null ? new ArrayList<>() : results;
    }

    public void addArea(Area area) {
        AreaDao areaDao = getAreaDao();
        areaDao.insert(area);
    }

    private AreaDao getAreaDao() {
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        return daoSession.getAreaDao();
    }

    public List<Area> getStreets(int parentId) {
        AreaDao areaDao = getAreaDao();
        List<Area> results = areaDao.queryBuilder().where(AreaDao.Properties.ParentId.eq(parentId)).list();
        return results == null ? new ArrayList<>() : results;
    }

}
