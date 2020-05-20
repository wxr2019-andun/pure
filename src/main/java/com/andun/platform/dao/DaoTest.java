package com.andun.platform.dao;

import com.andun.platform.pojo.po.DoctorDetailedInfo;
import com.andun.platform.pojo.po.UT;
import com.andun.platform.pojo.po.UserDailyHabits;
import com.andun.platform.pojo.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author:wuxinrui
 * Date:2019-09-26  16:32
 * Description:
 */
@Mapper
public interface DaoTest {

    List<DoctorDetailedInfo> queryCertainDoctorDetailedInfo(Integer doctorId);
    UserInfoVo selectManagerByAccount(@Param("account") String account);

    @Select("select  * from andun_app.a_user_daily_habits  where id=#{id}   ")
    UserDailyHabits myAccount(@Param("id") String id);

    Integer DaySum(@Param("timeS") String timeS,
                   @Param("timeE") String timeE);

    Integer dateCount(@Param("timeS") String timeS,
                      @Param("timeE") String timeE);
    Integer dateCount2(@Param("timeS") String timeS,
                      @Param("timeE") String timeE);
    List<String> dateCount3();
    List<UT> UTQuery(@Param("wearUserId") String wearUserId);
    @Select("select username from andun_app.a_wear_user where id=#{id}")
    String userName(String id);
}
