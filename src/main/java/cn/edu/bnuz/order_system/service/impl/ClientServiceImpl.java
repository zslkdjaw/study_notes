package cn.edu.bnuz.order_system.service.impl;

import cn.edu.bnuz.order_system.dao.ClientDao;
import cn.edu.bnuz.order_system.dao.impl.ClientDaoImpl;
import cn.edu.bnuz.order_system.entity.Client;
import cn.edu.bnuz.order_system.service.ClientService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-05-07-19:53
 */
public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao = new ClientDaoImpl();

    @Override
    public Client getById(Integer id) throws SQLException, ClassNotFoundException {

        return clientDao.selectById(id);
    }

    @Override
    public List<Client> getAll() throws SQLException, ClassNotFoundException {
        return clientDao.selectAll();
    }

    @Override
    public Boolean addClient(Client client,String birthday) throws SQLException, ClassNotFoundException {
        return clientDao.insert(client,birthday);
    }

    @Override
    public Boolean removeClient(Client client) throws SQLException, ClassNotFoundException {
        return clientDao.deleteById(client);
    }

    @Override
    public Boolean updateClient(Client client, String birthday) throws SQLException, ClassNotFoundException {
        return clientDao.updateById(client,birthday);
    }
}
