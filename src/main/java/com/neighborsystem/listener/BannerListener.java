package com.neighborsystem.listener;

import com.neighborsystem.entity.Banner;
import com.neighborsystem.service.IBannerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class BannerListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		IBannerService bannerService = app.getBean(IBannerService.class);
		ServletContext application = arg0.getServletContext();
		List<Banner> bannerList = bannerService.findAllShowBanner();
		application.setAttribute("bannerList", bannerList);
	}

}
