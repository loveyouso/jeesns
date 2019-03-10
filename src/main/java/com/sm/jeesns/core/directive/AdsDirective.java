package com.sm.jeesns.core.directive;


import com.sm.jeesns.model.common.Ads;
import com.sm.jeesns.service.common.IAdsService;
import com.sm.jeesns.core.handler.DirectiveHandler;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by zchuanzhao on 2017/09/08.
 */
@Component
public class AdsDirective extends BaseDirective {

    @Resource
    private IAdsService adsService;
    @Override
    public void execute(DirectiveHandler handler) throws TemplateException, IOException {
        int id = handler.getInteger("id",0);
        Ads ads = adsService.findById(id);
        handler.put("ad", ads).render();
    }

}
