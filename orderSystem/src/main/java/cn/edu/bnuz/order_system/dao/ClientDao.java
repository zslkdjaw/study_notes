package cn.edu.bnuz.order_system.dao;

import cn.edu.bnuz.order_system.entity.Client;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 张小豪
 * @create 2022-05-07-15:22
 */

public interface ClientDao {
    /**
     * function annotation:通过id查询指定Client
     * TODO
     * @param id
     * @return Client
     */
    Client selectById(Integer id) throws SQLException, ClassNotFoundException;
    /**
     * function annotation:查询所有Client
     * TODO
     * @return List<Client>
     * @date
     */
    List<Client> selectAll() throws SQLException, ClassNotFoundException;

    /**
     * function annotation:增加一个Client
     * TODO
     * @param client birthday
     * @return Boolean
     * @date
     */
    Boolean insert(Client client,String birthday) throws SQLException, ClassNotFoundException;
    /**
     * function annotation:删除指定Client
     * TODO
     * @param client
     * @return Boolean
     * @date
     */
    Boolean deleteById(Client client) throws SQLException, ClassNotFoundException;

    /**
     * function annotation:修改client
     * TODO
     * @param client
     * @return Boolean
     * @date
     */
    Boolean updateById(Client client,String birthday) throws SQLException, ClassNotFoundException;

}
