package com.example.nvclothes.nvclothes.repository.interfaces;

import com.example.nvclothes.nvclothes.entity.products.HoodieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoodieRepositoryInterface extends JpaRepository<HoodieEntity, Long> {

    Optional<HoodieEntity> getHoodieEntitiesById(@Param("id") Long id);

    Optional<HoodieEntity> getHoodieEntityByBrand(@Param("brand") String brand);
    List<HoodieEntity> getHoodieEntitiesByBrand(@Param("brand") String brand);

    Optional<HoodieEntity> getHoodieEntityByName(@Param("name") String name);
    List<HoodieEntity> getHoodieEntitiesByName(@Param("name") String name);


    void deleteHoodieEntityById(@Param("id") Long id);
}