package com.sm.jeesns.system.service.impl;

import com.sm.jeesns.core.exception.OpeErrorException;
import com.sm.jeesns.system.dao.IActionDao;
import com.sm.jeesns.system.model.Action;
import com.sm.jeesns.system.service.IActionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
@Service("actionService")
public class ActionServiceImpl implements IActionService {
    @Resource
    private IActionDao actionDao;

    @Override
    public List<Action> list() {
        return actionDao.allList();
    }

    @Override
    public Action findById(Integer id) {
        return actionDao.findById(id);
    }

    @Override
    public boolean update(Action action) {
        if(actionDao.update(action) == 0){
            throw new OpeErrorException();
        }
        return true;
    }

    @Override
    public boolean isenable(Integer id) {
        if(actionDao.isenable(id) == 0){
            throw new OpeErrorException();
        }
        return true;
    }

    /**
     * 状态是否能使用
     * @param id
     * @return true可以使用，false不能使用
     */
    @Override
    public boolean canuse(Integer id) {
        Action action = this.findById(id);
        if(action == null){
            return false;
        }
        if(action.getStatus() == 1){
            return false;
        }
        return true;
    }
}
