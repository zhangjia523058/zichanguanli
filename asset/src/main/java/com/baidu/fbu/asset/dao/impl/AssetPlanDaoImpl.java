package com.baidu.fbu.asset.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.baidu.fbu.asset.dao.AssetPlanDao;
import com.baidu.fbu.asset.entity.AssetPlan;
import com.baidu.fbu.asset.mapper.AssetPlanMapper;

@Repository
public class AssetPlanDaoImpl implements AssetPlanDao {
    @Resource
    private AssetPlanMapper assetPlanMapper;

    public void addByParam( AssetPlan assetPlan ) throws SQLException {
        assetPlanMapper.insertSelective(assetPlan);
    }

    public void updateByParam(AssetPlan assetPlan) throws SQLException {
        assetPlanMapper.updateSelective(assetPlan);
    }

    public void deleteById( String id ) throws SQLException {
        assetPlanMapper.deleteByPrimaryKey(id);
    }

    public AssetPlan findById( String id) throws SQLException {
        return (AssetPlan) assetPlanMapper.selectByPrimaryKey(id);
    }

    public List<AssetPlan> findByManageId(Integer amId) throws SQLException {
        List<AssetPlan> assetPlanList = assetPlanMapper.selectByManagerId(amId);
        return assetPlanList;
    }

    public long countByParam(AssetPlan assetPlan) {
        return assetPlanMapper.countSelective( assetPlan );
    }

    public List<Object> findByParam(Map<String, Object> map) {
        return assetPlanMapper.selectSelective(map);
    }

    public long joinCountByParam(AssetPlan assetPlan) {
        return assetPlanMapper.joinCountSelective( assetPlan );
    }

    public List<Object> joinFindByParam(Map<String, Object> map) {
        return assetPlanMapper.joinSelectSelective(map);
    }

    public void batchUpdateByParam(Map<String, Object> map) {
        assetPlanMapper.batchUpdateByParam( map );
    }

}