package com.baidu.fbu.asset.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.baidu.fbu.asset.entity.AssetManager;

@Component
public interface AssetManagerMapper {
    int insertSelective(AssetManager assetManager);
    int updateByPrimaryKeySelective(AssetManager assetManager);
    int deleteByPrimaryKey( Integer id );
    AssetManager selectByPrimaryKey( Integer id );
    long countSelective(AssetManager assetManager);
    List<Object> selectSelective( Map<String, Object> map );
    void logicDeleteById( Integer id );
    List<Object> findIdAndNameOfAssetManager();

}