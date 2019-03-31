package com.sm.jeesns.core.listener;

import com.sm.jeesns.core.utils.Const;
import com.sm.jeesns.core.utils.JeesnsConfig;
import com.sm.jeesns.model.system.Config;
import com.sm.jeesns.service.system.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/5/25.
 */
@WebListener
public class InitListener implements ServletContextListener {

    @Autowired
    private JeesnsConfig jeesnsConfig;

    @Autowired
    private IConfigService configService;

    public InitListener() {
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.err.println("listener============>>>>>>>>>>>>==============>>>>>");
            Const.PROJECT_PATH = sce.getServletContext().getContextPath();
            sce.getServletContext().setAttribute("basePath", Const.PROJECT_PATH);
            //JeesnsConfig jeesnsConfig = SpringContextHolderUtil.getBean("jeesnsConfig");
            System.err.println(jeesnsConfig);
            sce.getServletContext().setAttribute("jeesnsConfig",jeesnsConfig);

            String frontTemplate = jeesnsConfig.getFrontTemplate();
            sce.getServletContext().setAttribute("frontTemplate",frontTemplate);
            System.err.println(jeesnsConfig.getFrontTemplate().toString());
            String managePath = Const.PROJECT_PATH + "/" + jeesnsConfig.getManagePath();
            Const.GROUP_PATH = Const.PROJECT_PATH + "/" + jeesnsConfig.getGroupPath();
            Const.WEIBO_PATH = Const.PROJECT_PATH + "/" + jeesnsConfig.getWeiboPath();
            System.err.println(jeesnsConfig.getManagePath().toString());
            sce.getServletContext().setAttribute("managePath",managePath);
            sce.getServletContext().setAttribute("groupPath",Const.GROUP_PATH);
            sce.getServletContext().setAttribute("weiboPath",Const.WEIBO_PATH);
            //IConfigService configService = SpringContextHolderUtil.getBean("configService");
            List<Config> configList = configService.allList();
            for (Config config : configList) {
                sce.getServletContext().setAttribute(config.getJkey().toUpperCase(),config.getJvalue());
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
