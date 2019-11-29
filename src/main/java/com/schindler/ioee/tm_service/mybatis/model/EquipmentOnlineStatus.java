package com.schindler.ioee.tm_service.mybatis.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author litim
 */
public class EquipmentOnlineStatus implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EquipmentOnlineStatus.EquipmentId
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    private String equipmentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EquipmentOnlineStatus.ContractNo
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    private String contractNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EquipmentOnlineStatus.CubeNo
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    private String cubeNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EquipmentOnlineStatus.Technician
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    private String technician;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EquipmentOnlineStatus.PhoneNumber
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    private String phoneNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EquipmentOnlineStatus.Email
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EquipmentOnlineStatus.NotifyTime
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    private Date notifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EquipmentOnlineStatus.HeartbeatTime
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    private Date heartbeatTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EquipmentOnlineStatus.CreateDate
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EquipmentOnlineStatus.CreateBy
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    private String createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EquipmentOnlineStatus.UpdateDate
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    private Date updateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EquipmentOnlineStatus.UpdateBy
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EquipmentOnlineStatus.EquipmentId
     *
     * @return the value of EquipmentOnlineStatus.EquipmentId
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public String getEquipmentId() {
        return equipmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public EquipmentOnlineStatus withEquipmentId(String equipmentId) {
        this.setEquipmentId(equipmentId);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EquipmentOnlineStatus.EquipmentId
     *
     * @param equipmentId the value for EquipmentOnlineStatus.EquipmentId
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId == null ? null : equipmentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EquipmentOnlineStatus.ContractNo
     *
     * @return the value of EquipmentOnlineStatus.ContractNo
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public EquipmentOnlineStatus withContractNo(String contractNo) {
        this.setContractNo(contractNo);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EquipmentOnlineStatus.ContractNo
     *
     * @param contractNo the value for EquipmentOnlineStatus.ContractNo
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EquipmentOnlineStatus.CubeNo
     *
     * @return the value of EquipmentOnlineStatus.CubeNo
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public String getCubeNo() {
        return cubeNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public EquipmentOnlineStatus withCubeNo(String cubeNo) {
        this.setCubeNo(cubeNo);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EquipmentOnlineStatus.CubeNo
     *
     * @param cubeNo the value for EquipmentOnlineStatus.CubeNo
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public void setCubeNo(String cubeNo) {
        this.cubeNo = cubeNo == null ? null : cubeNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EquipmentOnlineStatus.Technician
     *
     * @return the value of EquipmentOnlineStatus.Technician
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public String getTechnician() {
        return technician;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public EquipmentOnlineStatus withTechnician(String technician) {
        this.setTechnician(technician);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EquipmentOnlineStatus.Technician
     *
     * @param technician the value for EquipmentOnlineStatus.Technician
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public void setTechnician(String technician) {
        this.technician = technician == null ? null : technician.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EquipmentOnlineStatus.PhoneNumber
     *
     * @return the value of EquipmentOnlineStatus.PhoneNumber
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public EquipmentOnlineStatus withPhoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EquipmentOnlineStatus.PhoneNumber
     *
     * @param phoneNumber the value for EquipmentOnlineStatus.PhoneNumber
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EquipmentOnlineStatus.Email
     *
     * @return the value of EquipmentOnlineStatus.Email
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public EquipmentOnlineStatus withEmail(String email) {
        this.setEmail(email);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EquipmentOnlineStatus.Email
     *
     * @param email the value for EquipmentOnlineStatus.Email
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EquipmentOnlineStatus.NotifyTime
     *
     * @return the value of EquipmentOnlineStatus.NotifyTime
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public Date getNotifyTime() {
        return notifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public EquipmentOnlineStatus withNotifyTime(Date notifyTime) {
        this.setNotifyTime(notifyTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EquipmentOnlineStatus.NotifyTime
     *
     * @param notifyTime the value for EquipmentOnlineStatus.NotifyTime
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EquipmentOnlineStatus.HeartbeatTime
     *
     * @return the value of EquipmentOnlineStatus.HeartbeatTime
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public Date getHeartbeatTime() {
        return heartbeatTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public EquipmentOnlineStatus withHeartbeatTime(Date heartbeatTime) {
        this.setHeartbeatTime(heartbeatTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EquipmentOnlineStatus.HeartbeatTime
     *
     * @param heartbeatTime the value for EquipmentOnlineStatus.HeartbeatTime
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public void setHeartbeatTime(Date heartbeatTime) {
        this.heartbeatTime = heartbeatTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EquipmentOnlineStatus.CreateDate
     *
     * @return the value of EquipmentOnlineStatus.CreateDate
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public EquipmentOnlineStatus withCreateDate(Date createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EquipmentOnlineStatus.CreateDate
     *
     * @param createDate the value for EquipmentOnlineStatus.CreateDate
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EquipmentOnlineStatus.CreateBy
     *
     * @return the value of EquipmentOnlineStatus.CreateBy
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public EquipmentOnlineStatus withCreateBy(String createBy) {
        this.setCreateBy(createBy);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EquipmentOnlineStatus.CreateBy
     *
     * @param createBy the value for EquipmentOnlineStatus.CreateBy
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EquipmentOnlineStatus.UpdateDate
     *
     * @return the value of EquipmentOnlineStatus.UpdateDate
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public EquipmentOnlineStatus withUpdateDate(Date updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EquipmentOnlineStatus.UpdateDate
     *
     * @param updateDate the value for EquipmentOnlineStatus.UpdateDate
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EquipmentOnlineStatus.UpdateBy
     *
     * @return the value of EquipmentOnlineStatus.UpdateBy
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public EquipmentOnlineStatus withUpdateBy(String updateBy) {
        this.setUpdateBy(updateBy);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EquipmentOnlineStatus.UpdateBy
     *
     * @param updateBy the value for EquipmentOnlineStatus.UpdateBy
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EquipmentOnlineStatus
     *
     * @mbg.generated Fri Nov 15 15:58:21 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", contractNo=").append(contractNo);
        sb.append(", cubeNo=").append(cubeNo);
        sb.append(", technician=").append(technician);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", email=").append(email);
        sb.append(", notifyTime=").append(notifyTime);
        sb.append(", heartbeatTime=").append(heartbeatTime);
        sb.append(", createDate=").append(createDate);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}