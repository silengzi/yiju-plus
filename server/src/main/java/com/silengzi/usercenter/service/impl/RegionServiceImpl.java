package com.silengzi.usercenter.service.impl;

import com.silengzi.usercenter.mapper.RegionMapper;
import com.silengzi.usercenter.model.domain.Region;
import com.silengzi.usercenter.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List getRegionTree() {
        List<Region> provinces = regionMapper.getAllProvinces();
        List<Region> result = new ArrayList<>();

        for (Region province : provinces) {
            Region provinceDTO = new Region();
            provinceDTO.setArea_code(province.getArea_code());
            provinceDTO.setLevel(province.getLevel());
            provinceDTO.setMerger_name(province.getMerger_name());
            provinceDTO.setName(province.getName());
            provinceDTO.setParent_code(province.getParent_code());
            provinceDTO.setPinyin(province.getPinyin());
            provinceDTO.setShort_name(province.getShort_name());

            List<Region> cities = regionMapper.getCitiesByProvinceId(province.getArea_code());
            List<Region> cityDTOs = new ArrayList<>();

            for (Region city : cities) {
                Region cityDTO = new Region();
                cityDTO.setArea_code(city.getArea_code());
                cityDTO.setLevel(city.getLevel());
                cityDTO.setMerger_name(city.getMerger_name());
                cityDTO.setName(city.getName());
                cityDTO.setParent_code(city.getParent_code());
                cityDTO.setPinyin(city.getPinyin());
                cityDTO.setShort_name(city.getShort_name());

                List<Region> countries = regionMapper.getCountrysByCityId(city.getArea_code());
                List<Region> countriesDTOs = new ArrayList<>();

                for (Region country : countries) {
                    Region countryDTO = new Region();
                    countryDTO.setArea_code(country.getArea_code());
                    countryDTO.setLevel(country.getLevel());
                    countryDTO.setMerger_name(country.getMerger_name());
                    countryDTO.setName(country.getName());
                    countryDTO.setParent_code(country.getParent_code());
                    countryDTO.setPinyin(country.getPinyin());
                    countryDTO.setShort_name(country.getShort_name());

                    countriesDTOs.add(countryDTO);
                }

                cityDTO.setChildren(countriesDTOs);
                cityDTOs.add(cityDTO);
            }

            provinceDTO.setChildren(cityDTOs);
            result.add(provinceDTO);
        }


        return result;
    }
}