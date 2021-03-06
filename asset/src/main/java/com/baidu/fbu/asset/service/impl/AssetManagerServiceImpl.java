package com.baidu.fbu.asset.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.fbu.asset.dao.AssetManagerDao;
import com.baidu.fbu.asset.entity.AssetManager;
import com.baidu.fbu.asset.service.AssetManagerService;
import com.baidu.fbu.asset.util.IOUtil;
import com.baidu.fbu.asset.util.PageUtil;
import com.baidu.fbu.asset.util.Util;

@Service("assetManagerService")
@Transactional
public class AssetManagerServiceImpl implements AssetManagerService {
    @Resource
    private AssetManagerDao assetManagerDao;

    public void add( AssetManager assetManager ) throws SQLException {
        assetManagerDao.addByParam(assetManager);
    }

    public void update(AssetManager assetManager) throws SQLException {
        assetManagerDao.updateByParam(assetManager);
    }

    public void deleteById( Integer id ) throws SQLException {
        assetManagerDao.deleteById(id);
    }

    public void logicDeleteById( Integer id ) throws SQLException {
        assetManagerDao.logicDeleteById(id);
    }

    public AssetManager findById( Integer id) throws SQLException {
        return (AssetManager) assetManagerDao.findById(id);
    }

    /** 查询符合条件的记录的条数  */
    public Long countByParam(AssetManager assetManager) throws SQLException {
        return assetManagerDao.countByParam( assetManager );
    }

    /** 查询符合条件的记录  */
    public Map<String, Object> findByParam( AssetManager assetManager ) throws SQLException {
        return findByParam( assetManager, -1, -1 );      // -1, -1 表示 不分页查询，查询全部。分页查询的话，默认参数是 0, 10
    }

    /** 查询符合条件的记录，分页  */
    public Map<String, Object> findByParam( AssetManager assetManager,
                    int startRow, int pageSize ) throws SQLException {
        long count = assetManagerDao.countByParam(assetManager);

        Map<String, Object> map = IOUtil.beanToMap(assetManager);
        map.put( "startRow", startRow );
        map.put( "pageSize", pageSize );

        List<Object> list = assetManagerDao.findByParam(map);

        Map<String, Object> result = Util.getHashMap();

        result.put( "list", list );
        result.put( "count", count );

        if ( startRow > -1 && pageSize > -1 ) {     // startRow 默认是 0 ， pageSize 默认是  10
            long howManyPages = PageUtil.calculateHowManyPages(count, pageSize);
            result.put( "howManyPages", howManyPages );
        }

        // Util.print( startRow+" --- "+pageSize+" --- "+count +" --- ");
        return result;
    }

    public List<Object> findIdAndNameOfAssetManager() {
        List<Object> list = assetManagerDao.findIdAndNameOfAssetManager();
        return list;
    }

}