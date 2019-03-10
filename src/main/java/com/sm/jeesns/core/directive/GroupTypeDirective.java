package com.sm.jeesns.core.directive;

import com.sm.jeesns.core.handler.DirectiveHandler;
import com.sm.jeesns.group.model.GroupType;
import com.sm.jeesns.group.service.IGroupTypeService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/16 下午16:14
 */
@Component
public class GroupTypeDirective extends BaseDirective {

    @Resource
    private IGroupTypeService groupTypeService;
    @Override
    public void execute(DirectiveHandler handler) throws TemplateException, IOException {
        List<GroupType> list = groupTypeService.list();
        handler.put("groupTypeList", list).render();
    }

}
