package com.mdem.komunalka.DAO.impl;

import com.mdem.komunalka.DAO.IRoleDao;
import com.mdem.komunalka.DAO.common.AbstractDao;
import com.mdem.komunalka.model.Role;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class RoleDao extends AbstractDao<Role, Long> implements IRoleDao {
    @Override
    public Role getRoleByName(String name) {
        Query query = getSession().createQuery("FROM " + Role.class.getName() + " where name = :name");
        query.setParameter("name", name);
        return (Role) query.uniqueResult();
    }
}
