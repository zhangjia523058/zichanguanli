package com.baidu.fbu.asset.service;

import java.sql.SQLException;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baidu.fbu.asset.entity.AssetStageDetail;

public interface AssetStageDetailService {
    public void add(AssetStageDetail assetStageDetail) throws SQLException;
    public void update(AssetStageDetail assetStageDetail) throws SQLException;
    public void deleteById( AssetStageDetail id ) throws SQLException;
    public AssetStageDetail findById( AssetStageDetail id ) throws SQLException;

    public Map<String, Object> findByParam( AssetStageDetail assetStageDetail,
                                                int startRow, int pageSize ) throws SQLException;
    public Map<String, Object> findByParam( AssetStageDetail assetStageDetail ) throws SQLException;
    public Long countByParam( AssetStageDetail assetStageDetail ) throws SQLException;

    public Map<String, Object> findAssetStageDetailByLoanIds( String ids ) throws SQLException;
    public HSSFWorkbook generateAssetStageToExcel(String ids);

}