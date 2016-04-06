package com.baidu.fbu.asset.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.baidu.fbu.asset.entity.AssetDetail;
import com.baidu.fbu.asset.entity.vo.AssetDetailVo;

@Component
public interface AssetDetailMapper {
    int insertSelective(AssetDetail assetDetail);
    int updateByPrimaryKeySelective(AssetDetail assetDetail);
    int deleteByPrimaryKey( String id );
    AssetDetail selectByPrimaryKey( String id );

    long countSelective(Map<String, Object> map);
    List<Object> selectSelective(Map<String, Object> map);
    void batchUpdateByParam(Map<String, Object> map);
    void batchUpdateByRelatedId(Map<String, Object> map);
    void removeAssetDetailsNotBuyInAnAssetPlan(Map<String, Object> map);
    void batchUpdateByLoanIds(Map<String, Object> map);
    List<AssetDetailVo> exportAssets(AssetDetail assetDetail);
    void updateTransferPrincipalInterest(Map<String, Object> checkResult);
    Map<String, Object> checkLoanId(String loanId);
    long joinCountSelective(Map<String, Object> map);
    List<Object> joinSelectSelective(Map<String, Object> map);
    Map<String, Object> sumInterestInquiry(String apId);

}