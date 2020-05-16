package com.neighborsystem.mapper;

import com.neighborsystem.entity.Banner;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerMapper {
	Integer addBanner(Banner banner);
	Integer deleteBanner(Integer id);
	Integer changeBannerState(Integer state, Integer id);
	Integer updateBanner(Banner banner);
	Banner findBannerById(Integer id);
	List<Banner> findAllBanner();
	List<Banner> findAllShowBanner();
	List<Banner> findAllBannerByLikeName(String name);
}
