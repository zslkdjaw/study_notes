package cn.edu.bnuz.order_system.dao.impl;

import cn.edu.bnuz.order_system.dao.ClientDao;
import cn.edu.bnuz.order_system.entity.Client;
import cn.edu.bnuz.order_system.utils.DateCompareUtils;
import cn.edu.bnuz.order_system.utils.JdbcUtils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.Date;

/**
 * @author
 * @create 2022-05-07-15:33
 */
public class ClientDaoImpl implements ClientDao {
    @Override
    public Client selectById(Integer id) throws SQLException, ClassNotFoundException {
        String sql = "select * from client_TB where clientId = ?";
        //连接数据库
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet resultSet = ps.executeQuery();
        Client client = new Client();
        while (resultSet.next()) {
            client.setClientId(resultSet.getInt(1));
            client.setClientName(resultSet.getString(2));
            //数据转化 birthday 转化成age
            Date date = resultSet.getDate(3);
            int age = DateCompareUtils.compareYear(date.toString());
            client.setAge(age);
            client.setSex(resultSet.getString(4));

        }
        //释放资源
        JdbcUtils.release(resultSet,connection,ps);
        return client;
    }
    @Override
    public List<Client> selectAll() throws SQLException, ClassNotFoundException {

        List<Client> clientList = new ArrayList<>();

        String sql = "select * from client_TB";
        //数据库连接资源
        Connection connection = JdbcUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            Client client = new Client();

            client.setClientId(resultSet.getInt(1));
            client.setClientName(resultSet.getString(2));
            //数据转化 birthday 转化成age
            Date date = resultSet.getDate(3);
            int age = DateCompareUtils.compareYear(date.toString());
            client.setAge(age);
            client.setSex(resultSet.getString(4));

            //添加
            clientList.add(client);
        }
        //数据库释放资源
        JdbcUtils.release(resultSet,connection,statement);
        return clientList;
    }

    @Override
    public Boolean insert(Client client,String birthday) throws SQLException, ClassNotFoundException {
        String sql = "insert into client_TB value(?,?,?,?)";

        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,client.getClientId());
        ps.setString(2,client.getClientName());

        //LocalDate to Date
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.parse(birthday);
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        Date date = Date.from(zdt.toInstant());

        ps.setDate(3,new java.sql.Date(date.getTime()));
        ps.setString(4,client.getSex());
        //错误抓取
        try {
            ps.execute();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            JdbcUtils.release(connection,ps);
        }

        return true;
    }

    @Override
    public Boolean deleteById(Client client) throws SQLException, ClassNotFoundException {
        String sql = "delete from client_TB where clientId= ?";
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1,client.getClientId());

        //错误抓取
        try {
            ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            JdbcUtils.release(connection,ps);
        }
        JdbcUtils.release(connection,ps);

        return true;
    }

    @Override
    public Boolean updateById(Client client,String birthday) throws SQLException, ClassNotFoundException {
        //动态拼接
        String[] patch = {"clientName=","birthday=","sex="};
        String pat = "";
        if(client.getClientName()!=null&& !client.getClientName().equals("")){
            pat += patch[0]+"'"+client.getClientName()+"',";
        }
        if (birthday!=null && !birthday.equals("")){
            pat += patch[1]+"'"+birthday+"',";
        }
        if (client.getSex()!=null && !client.getSex().equals("")){
            pat += patch[2]+"'"+client.getSex()+"',";
        }
        //去掉最后一个 , 字符
        pat = pat.substring(0,pat.length()-1);
        String sql  = " update client_TB set "+pat+"where clientId='"+client.getClientId()+"'" ;
        //System.out.println(sql+client);
        Connection connection = JdbcUtils.getConnection();
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate(sql);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            JdbcUtils.release(connection,statement);
        }
        return true;
    }

}
