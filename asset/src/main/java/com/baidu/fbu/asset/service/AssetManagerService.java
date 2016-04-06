package com.baidu.fbu.asset.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.baidu.fbu.asset.entity.AssetManager;

public interface AssetManagerService {
    public void add(AssetManager assetManager) throws SQLException;
    public void update(AssetManager assetManager) throws SQLException;
    public void deleteById( Integer id ) throws SQLException;
    public AssetManager findById( Integer id ) throws SQLException;
    public Map<String, Object> findByParam( AssetManager assetManager, int startRow, int pageSize ) throws SQLException;
    public Map<String, Object> findByParam( AssetManager assetManager ) throws SQLException;
    public Long countByParam( AssetManager assetManager ) throws SQLException;
    public void logicDeleteById(Integer id) throws SQLException;
    public List<Object> findIdAndNameOfAssetManager() throws SQLException;

}