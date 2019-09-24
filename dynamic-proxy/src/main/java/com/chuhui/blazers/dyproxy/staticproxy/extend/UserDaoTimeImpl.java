package com.chuhui.blazers.dyproxy.staticproxy.extend;

import static com.chuhui.blazers.commcustome.constant.Constaints.commonlyUserDateTimeFormat;
import static com.chuhui.blazers.commcustome.constant.Constaints.returnCurrentTimeFormated;

/**
 * UserDaoImpl
 * <p>
 * UserDao 执行时间
 *
 * @author: cyzi
 * @Date: 2019/9/24 0024
 * @Description:
 */
public class UserDaoTimeImpl extends UserDaoImpl {

    @Override
    public void saveOrUpdate(String id) {

       String startTime= returnCurrentTimeFormated(commonlyUserDateTimeFormat);

        System.err.println("start execute saveOrUpdate function, currentTime: "+startTime);

        super.saveOrUpdate(id);

        String endTime= returnCurrentTimeFormated(commonlyUserDateTimeFormat);
        System.err.println("end execute saveOrUpdate function, currentTime: "+endTime);
    }

}
