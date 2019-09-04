package com.kjplus.dao;

import com.kjplus.model.SysAppKeyEbo;
import com.ybk.exception.DataException;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("keyDao")
public interface ISysAppKeyDao {



    /**
     *根据token获得
     * @param token
     *  用户令牌，系统交互时传输
     * @param flag
     *      是否有效
     * @return
     * @throws DataException
     */
    public List<SysAppKeyEbo> getKey(@Param("token") String token, @Param("flag")String flag) throws DataException;

    /**
     *  根据openid获得用户key对象
     * @param openid
     *      系统用户的标识
     * @param flag
     *      是否有效
     * @return
     * @throws DataException
     */
    public List<SysAppKeyEbo> getKeyByOpenid(@Param("openid")String openid,@Param("flag")String flag) throws DataException;


    /**
     *
     * @param uid
     *        通过uid查询最近token
     * @param flag
     *        是否有效
     * @return
     * @throws DataException
     */
    public SysAppKeyEbo getKeyByUid(@Param("uid")int uid,@Param("flag")String flag) throws DataException;

    /**
     * 获取openId的个数
     * @param openid
     * @return
     * @throws DataException
     */
    public Integer getKeyOpenidNum(@Param("openid") String openid) throws DataException;

    /**
     *添加一个openid和token的对应关系，本方法只添加，不负责对token的超时管理
     * @param key
     *    传入SysAppKeyEbo对象
     * @return
     * @throws DataException
     */
    public void addKey(SysAppKeyEbo key) throws DataException;


    /**
     *更新key的访问时间和访问次数
     * @param token
     * @param uid
     * @param orgid
     * @throws DataException
     */
    public void updateKey(@Param("token")String token, @Param("uid")int uid, @Param("orgid")int orgid , @Param("lastVisitTime") int lastVisitTime) throws DataException;




    /**
     *通过openid失效token
     * @param openid
     * @throws DataException
     */
    public void lapseTokenByOpenId(@Param("openid")String openid, @Param("flag") String flag) throws DataException;

}
