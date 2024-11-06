package com.silengzi.usercenter.mapper;
import com.silengzi.usercenter.model.domain.Region;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RegionMapper {

    @Select("SELECT * FROM gis_province")
    List<Region> getAllProvinces();

    @Select("SELECT * FROM gis_city WHERE parent_code = #{provinceId}")
    List<Region> getCitiesByProvinceId(String provinceId);

    @Select("SELECT * FROM gis_country WHERE parent_code = #{cityId}")
    List<Region> getCountrysByCityId(String cityId);
}