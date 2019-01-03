package com.dachen.cboardpublic.dao;

import com.dachen.cboardpublic.model.base.DimensionMap;
import com.dachen.cboardpublic.model.base.Index;
import com.dachen.cboardpublic.util.ImpalaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Repository
public class CboardPublicDaoImpl {

    private static final Logger logger = LoggerFactory.getLogger(CboardPublicDaoImpl.class);

    @Autowired
    ImpalaUtil impalaUtil;

    public List<Index> getIndexList(String sql) {

        List<Index>list = new LinkedList<>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try{
            conn = impalaUtil.getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                Index index = new Index();
                index.setIndex(rs.getString(1));
                index.setText(rs.getString(2));
                list.add(index);
            }
        }catch(Exception e){
            logger.error("getClusterCount ERROR:{}",e.getMessage());
        } finally {
            closeConnection(conn,stat);
        }
        return list;
    }

    public List<String> getEventList(String sql) {
        List<String> list = new LinkedList<>();
        list.add("全部事件");
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try{
            conn = impalaUtil.getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                String event = rs.getString(1);
                list.add(event);
            }
        }catch(Exception e){
            logger.error("getClusterCount ERROR:{}",e.getMessage());
        } finally {
            closeConnection(conn,stat);
        }
        return list;
    }

    public DimensionMap getDimensionMap(String sql) {
        DimensionMap dMap = new DimensionMap();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try{
            conn = impalaUtil.getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                dMap.setDimtb(rs.getString(1));
                dMap.setField(rs.getString(2));
            }
        }catch(Exception e){
            logger.error("getClusterCount ERROR:{}",e.getMessage());
        } finally {
            closeConnection(conn,stat);
        }
        return dMap;
    }

    private void closeConnection(Connection conn,Statement stat){
        try {
            if(Objects.nonNull(conn)) conn.close();
            if(Objects.nonNull(stat)) stat.close();
        } catch (SQLException e) {
            logger.error("getClusterCount ERROR:{}",e.getMessage());
        }
    }
}
