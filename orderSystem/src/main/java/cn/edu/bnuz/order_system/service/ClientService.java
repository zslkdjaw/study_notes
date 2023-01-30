package cn.edu.bnuz.order_system.service;

import cn.edu.bnuz.order_system.entity.Client;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-05-07-19:52
 */

public interface ClientService {
    /**
     * function annotation:通过id查询指定Client
     * TODO
     * @param id
     * @return Client
     */
    Client getById(Integer id) throws SQLException, ClassNotFoundException;
    /**
     * function annotation:查询所有Client
     * TODO
     * @return List<Client>
     * @date
     */
    List<Client> getAll() throws SQLException, ClassNotFoundException;

    /**
     * function annotation:增加一个Client
     * TODO
     * @param client birthday
     * @return Boolean
     * @date
     */
    Boolean addClient(Client client,String birthday) throws SQLException, ClassNotFoundException;
    /**
     * function annotation:删除指定Client
     * TODO
     * @param client
     * @return Boolean
     * @date
     */
    Boolean removeClient(Client client) throws SQLException, ClassNotFoundException;

    /**
     * function annotation:修改client
     * TODO
     * @param client
     * @return Boolean
     * @date
     */
    Boolean updateClient(Client client,String birthday) throws SQLException, ClassNotFoundException;


}
