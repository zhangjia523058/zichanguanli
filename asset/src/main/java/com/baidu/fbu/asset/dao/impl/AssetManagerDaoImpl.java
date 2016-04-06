package com.baidu.fbu.asset.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.baidu.fbu.asset.dao.AssetManagerDao;
import com.baidu.fbu.asset.entity.AssetManager;
import com.baidu.fbu.asset.mapper.AssetManagerMapper;

@Repository
public class AssetManagerDaoImpl implements AssetManagerDao {
    @Resource
    private AssetManagerMapper assetManagerMapper;

    public void addByParam( AssetManager assetManager ) throws SQLException {
        assetManagerMapper.insertSelective(assetManager);
    }

    public void updateByParam(AssetManager assetManager) throws SQLException {
        assetManagerMapper.updateByPrimaryKeySelective(assetManager);
    }

    public void deleteById( Integer id ) throws SQLException {
        assetManagerMapper.deleteByPrimaryKey(id);
    }

    public void logicDeleteById( Integer id ) throws SQLException {
        assetManagerMapper.logicDeleteById(id);
    }

    public AssetManager findById( Integer id) throws SQLException {
        return (AssetManager) assetManagerMapper.selectByPrimaryKey(id);
    }

    public long countByParam(AssetManager assetManager) {
        return assetManagerMapper.countSelective( assetManager );
    }

    public List<Object> findByParam(Map<String, Object> map) {
        return assetManagerMapper.selectSelective(map);
    }

    public List<Object> findIdAndNameOfAssetManager() {
        return assetManagerMapper.findIdAndNameOfAssetManager();
    }


}