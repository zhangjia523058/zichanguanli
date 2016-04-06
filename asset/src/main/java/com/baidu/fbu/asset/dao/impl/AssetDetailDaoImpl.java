package com.baidu.fbu.asset.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.baidu.fbu.asset.dao.AssetDetailDao;
import com.baidu.fbu.asset.entity.AssetDetail;
import com.baidu.fbu.asset.entity.vo.AssetDetailVo;
import com.baidu.fbu.asset.mapper.AssetDetailMapper;

@Repository
public class AssetDetailDaoImpl implements AssetDetailDao {
    @Resource
    private AssetDetailMapper assetDetailMapper;

    public void addByParam( AssetDetail assetDetail ) throws SQLException {
        assetDetailMapper.insertSelective(assetDetail);
    }

    public void updateByParam(AssetDetail assetDetail) throws SQLException {
        assetDetailMapper.updateByPrimaryKeySelective(assetDetail);
    }

    public void deleteById( String id ) throws SQLException {
        assetDetailMapper.deleteByPrimaryKey(id);
    }

    public AssetDetail findById( String id) throws SQLException {
        return (AssetDetail) assetDetailMapper.selectByPrimaryKey(id);
    }

    // public long countByParam(AssetDetail assetDetail) {
    //    return assetDetailMapper.countSelective( assetDetail );
    // }

    public long countByParam(Map<String, Object> map) {
        // return assetDetailMapper.countSelective( map );
        return assetDetailMapper.joinCountSelective(map);
    }

    public List<Object> findByParam(Map<String, Object> map) {
        // return assetDetailMapper.selectSelective(map);
        return assetDetailMapper.joinSelectSelective(map);
    }

    // 计算 资产计划 的 4 种本息的总金额
    public Map<String, Object> sumInterestInquiry(String apId) {
        return assetDetailMapper.sumInterestInquiry(apId);
    }

    public void batchUpdateAssetDetailByParam(Map<String, Object> map) {
        assetDetailMapper.batchUpdateByParam(map);
    }

    public void batchUpdateAssetDetailByAssetPlanId(Map<String, Object> map) {
        assetDetailMapper.batchUpdateByRelatedId(map);
    }

    public void removeAssetDetailsNotBuyInAnAssetPlan(Map<String, Object> map) {
        assetDetailMapper.removeAssetDetailsNotBuyInAnAssetPlan(map);
    }

    public void batchUpdateAssetDetailByLoanIds(Map<String, Object> map) {
        assetDetailMapper.batchUpdateByLoanIds(map);
    }

    public List<AssetDetailVo> exportAssets(AssetDetail assetDetail) {
        return assetDetailMapper.exportAssets(assetDetail);
    }

    public void updateTransferPrincipalInterest(Map<String, Object> checkResult) {
        assetDetailMapper.updateTransferPrincipalInterest(checkResult);
    }

    public Map<String, Object> checkLoanId(String loanId) {
        // System.out.println(loanId);
        return assetDetailMapper.checkLoanId(loanId);
    }

}