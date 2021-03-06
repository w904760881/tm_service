package com.schindler.ioee.tm_service.mybatis.mapper;

import com.schindler.ioee.tm_service.mybatis.model.EquipmentOnlineStatus;
import com.schindler.ioee.tm_service.mybatis.model.EquipmentOnlineStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

/**
 * @author litim
 */
public interface EquipmentOnlineStatusMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     * @param example
     * @return
     */
    @SelectProvider(type=EquipmentOnlineStatusSqlProvider.class, method="countByExample")
    long countByExample(EquipmentOnlineStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     * @param example
     * @return
     */
    @DeleteProvider(type=EquipmentOnlineStatusSqlProvider.class, method="deleteByExample")
    int deleteByExample(EquipmentOnlineStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     * @param equipmentId
     * @return
     */
    @Delete({
        "delete from EquipmentOnlineStatus",
        "where EquipmentId = #{equipmentId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String equipmentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     * @param record
     * @return
     */
    @Insert({
        "insert into EquipmentOnlineStatus (EquipmentId, ContractNo, ",
        "CubeNo, Technician, ",
        "PhoneNumber, Email, ",
        "NotifyTime, HeartbeatTime, ",
        "CreateDate, CreateBy, ",
        "UpdateDate, UpdateBy)",
        "values (#{equipmentId,jdbcType=VARCHAR}, #{contractNo,jdbcType=VARCHAR}, ",
        "#{cubeNo,jdbcType=VARCHAR}, #{technician,jdbcType=VARCHAR}, ",
        "#{phoneNumber,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
        "#{notifyTime,jdbcType=TIMESTAMP}, #{heartbeatTime,jdbcType=TIMESTAMP}, ",
        "#{createDate,jdbcType=TIMESTAMP}, #{createBy,jdbcType=VARCHAR}, ",
        "#{updateDate,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=VARCHAR})"
    })
    int insert(EquipmentOnlineStatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     * @param record
     * @return
     */
    @InsertProvider(type=EquipmentOnlineStatusSqlProvider.class, method="insertSelective")
    int insertSelective(EquipmentOnlineStatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     * @param example
     * @param rowBounds
     * @return
     */
    @SelectProvider(type=EquipmentOnlineStatusSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="EquipmentId", property="equipmentId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ContractNo", property="contractNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="CubeNo", property="cubeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="Technician", property="technician", jdbcType=JdbcType.VARCHAR),
        @Result(column="PhoneNumber", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="Email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="NotifyTime", property="notifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="HeartbeatTime", property="heartbeatTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CreateDate", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CreateBy", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="UpdateDate", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UpdateBy", property="updateBy", jdbcType=JdbcType.VARCHAR)
    })
    List<EquipmentOnlineStatus> selectByExampleWithRowbounds(EquipmentOnlineStatusExample example, RowBounds rowBounds);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     * @param example
     * @return
     */
    @SelectProvider(type=EquipmentOnlineStatusSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="EquipmentId", property="equipmentId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ContractNo", property="contractNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="CubeNo", property="cubeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="Technician", property="technician", jdbcType=JdbcType.VARCHAR),
        @Result(column="PhoneNumber", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="Email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="NotifyTime", property="notifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="HeartbeatTime", property="heartbeatTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CreateDate", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CreateBy", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="UpdateDate", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UpdateBy", property="updateBy", jdbcType=JdbcType.VARCHAR)
    })
    List<EquipmentOnlineStatus> selectByExample(EquipmentOnlineStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     * @param equipmentId
     * @return
     */
    @Select({
        "select",
        "EquipmentId, ContractNo, CubeNo, Technician, PhoneNumber, Email, NotifyTime, ",
        "HeartbeatTime, CreateDate, CreateBy, UpdateDate, UpdateBy",
        "from EquipmentOnlineStatus",
        "where EquipmentId = #{equipmentId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="EquipmentId", property="equipmentId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ContractNo", property="contractNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="CubeNo", property="cubeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="Technician", property="technician", jdbcType=JdbcType.VARCHAR),
        @Result(column="PhoneNumber", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="Email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="NotifyTime", property="notifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="HeartbeatTime", property="heartbeatTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CreateDate", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CreateBy", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="UpdateDate", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UpdateBy", property="updateBy", jdbcType=JdbcType.VARCHAR)
    })
    EquipmentOnlineStatus selectByPrimaryKey(String equipmentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     * @param record
     * @param example
     * @return
     */
    @UpdateProvider(type=EquipmentOnlineStatusSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") EquipmentOnlineStatus record, @Param("example") EquipmentOnlineStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     * @param record
     * @param example
     * @return
     */
    @UpdateProvider(type=EquipmentOnlineStatusSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") EquipmentOnlineStatus record, @Param("example") EquipmentOnlineStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     * @param record
     * @return
     */
    @UpdateProvider(type=EquipmentOnlineStatusSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(EquipmentOnlineStatus record);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     * @param record
     * @return
     */
    @Update({
        "update EquipmentOnlineStatus",
        "set ContractNo = #{contractNo,jdbcType=VARCHAR},",
          "CubeNo = #{cubeNo,jdbcType=VARCHAR},",
          "Technician = #{technician,jdbcType=VARCHAR},",
          "PhoneNumber = #{phoneNumber,jdbcType=VARCHAR},",
          "Email = #{email,jdbcType=VARCHAR},",
          "NotifyTime = #{notifyTime,jdbcType=TIMESTAMP},",
          "HeartbeatTime = #{heartbeatTime,jdbcType=TIMESTAMP},",
          "CreateDate = #{createDate,jdbcType=TIMESTAMP},",
          "CreateBy = #{createBy,jdbcType=VARCHAR},",
          "UpdateDate = #{updateDate,jdbcType=TIMESTAMP},",
          "UpdateBy = #{updateBy,jdbcType=VARCHAR}",
        "where EquipmentId = #{equipmentId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(EquipmentOnlineStatus record);
}