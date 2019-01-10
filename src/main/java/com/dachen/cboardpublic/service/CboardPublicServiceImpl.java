package com.dachen.cboardpublic.service;

import com.dachen.cboardpublic.dao.CboardPublicDaoImpl;
import com.dachen.cboardpublic.model.base.DimensionMap;
import com.dachen.cboardpublic.model.base.Index;
import com.dachen.cboardpublic.model.dto.DimensionDTO;
import com.dachen.cboardpublic.model.dto.EventDTO;
import com.dachen.cboardpublic.model.dto.IndexDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

@Service
public class CboardPublicServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(CboardPublicServiceImpl.class);

    @Autowired
    CboardPublicDaoImpl cboardPublicDaoImpl;

    public List<IndexDTO> getIndexList(){
        Long currentTime = System.currentTimeMillis();
        String sql = "select * from bds.bds_index";
        List<Index> indexList = cboardPublicDaoImpl.getIndexList(sql);
        logger.info("doTime:{} sql:{}",System.currentTimeMillis()-currentTime,sql);
        List<IndexDTO> mList = new LinkedList<>();
        if (CollectionUtils.isEmpty(indexList)){
            return mList;
        }
        indexList.forEach(i->{
            IndexDTO index = new IndexDTO();
            index.setModule(i.getIndex());
            index.setText(i.getText());
            mList.add(index);
        });
        return mList;
    }

    public List<DimensionDTO> getDimensionList(String module){
        Long currentTime = System.currentTimeMillis();
        String sql = "select b.id,a.dimension from bds.bds_module a join bds.bds_dimension b on a.dimension=b.dimension where a.module = '%s'";
        sql = String.format(sql,module);
        List<Index> dimensionList = cboardPublicDaoImpl.getIndexList(sql);
        logger.info("doTime:{} sql:{}",System.currentTimeMillis()-currentTime,sql);
        List<DimensionDTO> dList = new LinkedList<>();
        if (CollectionUtils.isEmpty(dimensionList)){
            return dList;
        }
        dimensionList.forEach(i->{
            DimensionDTO dimension = new DimensionDTO();
            dimension.setDimension_sub(i.getIndex());
            dimension.setText(i.getText());
            dList.add(dimension);
        });
        return dList;
    }

    public List<EventDTO> getEventList(){
        Long currentTime = System.currentTimeMillis();
        String sql = "select distinct module from dw.dw_user_event_r where module is not null and trim(module)<>'' and module<>'null' ";
        List<String> eventList = cboardPublicDaoImpl.getEventList(sql);
        logger.info("doTime:{} sql:{}",System.currentTimeMillis()-currentTime,sql);
        List<EventDTO> eList = new LinkedList<>();
        if (CollectionUtils.isEmpty(eventList)){
            return eList;
        }
        eventList.forEach(i->{
            EventDTO event = new EventDTO();
            event.setEvent(i);
            eList.add(event);
        });
        return eList;
    }

    public List<String> getDimensionSubList(String dimension){
        Long currentTime = System.currentTimeMillis();
        String sql = "select dimtb,field from bds.bds_dimension_map where id='%s'";
        sql = String.format(sql,dimension);
        DimensionMap dMap = cboardPublicDaoImpl.getDimensionMap(sql);
        logger.info("doTime:{} sql:{}",System.currentTimeMillis()-currentTime,sql);
        String sql2 = "select %s from %s";
        String table = dMap.getDimtb();
        String field = dMap.getField();
        sql2 = String.format(sql2,field,table);
        List<String> dimensionSubList = cboardPublicDaoImpl.getEventList(sql2);
        logger.info("doTime:{} sql:{}",System.currentTimeMillis()-currentTime,sql2);
        List<String> dsList = new LinkedList<>();
        if (CollectionUtils.isEmpty(dimensionSubList)){
            return dsList;
        }
        dsList.addAll(dimensionSubList);
        return dsList;
    }

}
