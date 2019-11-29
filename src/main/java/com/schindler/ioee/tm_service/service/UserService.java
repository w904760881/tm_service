package com.schindler.ioee.tm_service.service;

import com.github.pagehelper.PageRowBounds;
import com.schindler.ioee.gdcsv3.common.model.pojo.list.PageQuery;
import com.schindler.ioee.tm_service.mybatis.mapper.UserMapper;
import com.schindler.ioee.tm_service.mybatis.model.User;
import com.schindler.ioee.tm_service.mybatis.model.UserExample;
import com.schindler.ioee.tm_service.util.Md5EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author weihao2
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean insert(User user) {
        log.info("receive user is {}", user);
        if (user == null) {
            return false;
        }
        try {
            if (selectByPrimaryKey(user.getUserName()).isPresent()) {
                return false;
            }
            Date currentDate = new Date();
            user.setCreateDate(currentDate);
            user.setUpdateDate(currentDate);
            user.setPassword(Md5EncryptionUtil.encrypt(user.getPassword()));
            if (userMapper.insert(user) <= 0) {
                return false;
            }
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
        return true;
    }

    public boolean deleteByName(String username) {
        log.info("receive username is {}", username);
        if (!selectByPrimaryKey(username).isPresent()) {
            return false;
        }
        try {
            if (userMapper.deleteByPrimaryKey(username) <= 0) {
                return false;
            }
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
        return true;
    }

    public boolean update(User user) {
        log.info("receive user is {}", user);
        Optional<User> userOptional = selectByPrimaryKey(user.getUserName());
        if (!userOptional.isPresent()) {
            return false;
        }
        try {
            user.setCreateDate(userOptional.get().getCreateDate());
            user.setUpdateDate(new Date());
            userMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
        return true;
    }

    public Optional<PageQuery> query(String search, Integer pageIndex, Integer pageSize) {
        UserExample example = new UserExample();
        if (!Strings.isEmpty(search)) {
            UserExample.Criteria criteria1 = example.createCriteria();
            UserExample.Criteria criteria2 = example.createCriteria();
            criteria1.andUserNameLike(search);
            criteria2.andNicknameLike(search);
            example.or(criteria2);
        }
        PageRowBounds pageRowBounds = new PageRowBounds(pageIndex * pageSize, pageSize);
        PageQuery<User> resultPageQuery = new PageQuery();
        try {
            resultPageQuery.setList(userMapper.selectByExampleWithRowbounds(example, pageRowBounds));
            resultPageQuery.setTotalCount(pageRowBounds.getTotal());
            resultPageQuery.setPageIndex(pageIndex);
            resultPageQuery.setPageSize(pageSize);
            return Optional.of(resultPageQuery);
        } catch (Exception e) {
            log.error(e.toString());
            return Optional.empty();
        }
    }

    Optional<User> selectByPrimaryKey(String username) {
        log.info("receive username is {}", username);
        if (Strings.isEmpty(username)) {
            return Optional.empty();
        }
        try {
            User user = userMapper.selectByPrimaryKey(username);
            if (user != null) {
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (Exception e) {
            log.error(e.toString());
            return Optional.empty();
        }
    }


}
