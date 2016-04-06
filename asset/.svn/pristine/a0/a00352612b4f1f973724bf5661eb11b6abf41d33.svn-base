package com.baidu.fbu.asset.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.baidu.fbu.asset.entity.AssetManager;

@Component
public interface AssetManagerDao {
    public void addByParam(AssetManager assetManager) throws SQLException;
    public void updateByParam(AssetManager assetManager) throws SQLException;
    public void deleteById(Integer id) throws SQLException;
    public void logicDeleteById(Integer id) throws SQLException;
    public AssetManager findById(Integer id) throws SQLException;
    long countByParam(AssetManager assetManager);
    List<Object> findByParam( Map<String, Object> map );
    List<Object> findIdAndNameOfAssetManager();

}