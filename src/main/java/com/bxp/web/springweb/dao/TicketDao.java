package com.bxp.web.springweb.dao;

import com.bxp.web.springweb.train.Train;
import com.bxp.web.springweb.train.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: bbp(xiuping bao) on 2018/5/26.
 * @Date: 2018/5/26 15:57
 */
@Mapper
public interface TicketDao {
    /**
     * 获取所有的车票信息
     *
     * @return
     */
    @Select("SELECT * FROM train_info")
    @ResultType(value = Train.class)
    List<Train> getAllTrain();

    @Select("SELECT * FROM train_info where date = #{date}")
    @ResultType(value = Train.class)
    List<Train> getAllTrainByDate(@Param("date") String date);

    @Select("SELECT * FROM train_info WHERE DATE=#{date} AND ORIGIN_STATION=#{origin} AND TERMINAL_STATION=#{terminal}")
    @ResultType(value = Train.class)
    List<Train> getSomeTrain(@Param("date") String date,
                             @Param("origin") String origin,
                             @Param("terminal") String terminal);

    @Select("SELECT * FROM train_info WHERE DATE=#{date} AND NUMBER=#{number}")
    @ResultType(value = Train.class)
    Train getATrain(@Param("date") String date,
                    @Param("number") String number);

    @Update("UPDATE train_info SET TICKETS_LEFT=#{left} WHERE DATE=#{date} AND NUMBER=#{number}")
    void updateLeft(@Param("left") String left,
                    @Param("date") String date,
                    @Param("number") String number);
}
