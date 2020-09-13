package com.csmaxwell.tes.service;

import com.csmaxwell.tes.dao.TesUserMapper;
import com.csmaxwell.tes.domain.TesUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * S
 * Created by maxwell on 2020/9/13.
 */
@Service
public class TesUserService {

    @Autowired private TesUserMapper tesUserMapper;

    public List<TesUser> findAll() {
        List<TesUser> userList = tesUserMapper.selectAll();
        return userList;
    }

    public TesUser findById(Integer id) {
        // TesUser tesUser = tesUserMapper.selectByPrimaryKey(id);
        // return tesUser;
        return null;
    }
}
