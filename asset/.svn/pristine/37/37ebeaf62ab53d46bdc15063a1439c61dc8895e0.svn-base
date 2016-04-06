package com.baidu.fbu.asset.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.baidu.fbu.asset.entity.AssetPlan;

@Component
public interface AssetPlanDao {
    public void addByParam(AssetPlan assetPlan) throws SQLException;
    public void updateByParam(AssetPlan assetPlan) throws SQLException;
    public void deleteById(String id) throws SQLException;
    public AssetPlan findById(String id) throws SQLException;
    public List<AssetPlan> findByManageId(Integer amId) throws SQLException;
    // 查询符合条件的记录的条数
    long countByParam(AssetPlan assetPlan);
    // 查询符合条件的记录
    List<Object> findByParam( Map<String, Object> map );
    // 查询符合条件的记录的条数
    long joinCountByParam(AssetPlan assetPlan);
    // 查询符合条件的记录
    List<Object> joinFindByParam( Map<String, Object> map );
    void batchUpdateByParam( Map<String, Object> map );

}