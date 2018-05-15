package com.example.weather;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Date：2018/3/1
 * Author：小康de生活
 * Signature:好好学习，天天向上
 * Description:
 */

@Entity
public class TaskEntity {
    @Id
    private String taskId;
    /** Not-null value. */
    private String applyId;
    private String cusName;
    private String contractNo;
    private String taskStatus;
    private String carGroup;
    private Integer curOverdueDays;
    private String idNo;
    private String phoneNum;
    private String provinceName;
    private String cityName;
    private String dealerName;
    private String assetBrand;
    private String contractId;
    private String curOverdueSum;
    private String assignTime;
    private String coBorrower;
    private String borrower;
    private String guarantor;
    private String colRecord;
    private String repRecord;
    private String visitReport;
    private String colOutHistory;
    private String repayPlan;
    private String taskInfo;
    private String overdueRecord;
    @Generated(hash = 1456323536)
    public TaskEntity(String taskId, String applyId, String cusName,
            String contractNo, String taskStatus, String carGroup,
            Integer curOverdueDays, String idNo, String phoneNum,
            String provinceName, String cityName, String dealerName,
            String assetBrand, String contractId, String curOverdueSum,
            String assignTime, String coBorrower, String borrower, String guarantor,
            String colRecord, String repRecord, String visitReport,
            String colOutHistory, String repayPlan, String taskInfo,
            String overdueRecord) {
        this.taskId = taskId;
        this.applyId = applyId;
        this.cusName = cusName;
        this.contractNo = contractNo;
        this.taskStatus = taskStatus;
        this.carGroup = carGroup;
        this.curOverdueDays = curOverdueDays;
        this.idNo = idNo;
        this.phoneNum = phoneNum;
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.dealerName = dealerName;
        this.assetBrand = assetBrand;
        this.contractId = contractId;
        this.curOverdueSum = curOverdueSum;
        this.assignTime = assignTime;
        this.coBorrower = coBorrower;
        this.borrower = borrower;
        this.guarantor = guarantor;
        this.colRecord = colRecord;
        this.repRecord = repRecord;
        this.visitReport = visitReport;
        this.colOutHistory = colOutHistory;
        this.repayPlan = repayPlan;
        this.taskInfo = taskInfo;
        this.overdueRecord = overdueRecord;
    }
    @Generated(hash = 397975341)
    public TaskEntity() {
    }
    public String getTaskId() {
        return this.taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getApplyId() {
        return this.applyId;
    }
    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }
    public String getCusName() {
        return this.cusName;
    }
    public void setCusName(String cusName) {
        this.cusName = cusName;
    }
    public String getContractNo() {
        return this.contractNo;
    }
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }
    public String getTaskStatus() {
        return this.taskStatus;
    }
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
    public String getCarGroup() {
        return this.carGroup;
    }
    public void setCarGroup(String carGroup) {
        this.carGroup = carGroup;
    }
    public Integer getCurOverdueDays() {
        return this.curOverdueDays;
    }
    public void setCurOverdueDays(Integer curOverdueDays) {
        this.curOverdueDays = curOverdueDays;
    }
    public String getIdNo() {
        return this.idNo;
    }
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    public String getPhoneNum() {
        return this.phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getProvinceName() {
        return this.provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public String getCityName() {
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getDealerName() {
        return this.dealerName;
    }
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }
    public String getAssetBrand() {
        return this.assetBrand;
    }
    public void setAssetBrand(String assetBrand) {
        this.assetBrand = assetBrand;
    }
    public String getContractId() {
        return this.contractId;
    }
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    public String getCurOverdueSum() {
        return this.curOverdueSum;
    }
    public void setCurOverdueSum(String curOverdueSum) {
        this.curOverdueSum = curOverdueSum;
    }
    public String getAssignTime() {
        return this.assignTime;
    }
    public void setAssignTime(String assignTime) {
        this.assignTime = assignTime;
    }
    public String getCoBorrower() {
        return this.coBorrower;
    }
    public void setCoBorrower(String coBorrower) {
        this.coBorrower = coBorrower;
    }
    public String getBorrower() {
        return this.borrower;
    }
    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }
    public String getGuarantor() {
        return this.guarantor;
    }
    public void setGuarantor(String guarantor) {
        this.guarantor = guarantor;
    }
    public String getColRecord() {
        return this.colRecord;
    }
    public void setColRecord(String colRecord) {
        this.colRecord = colRecord;
    }
    public String getRepRecord() {
        return this.repRecord;
    }
    public void setRepRecord(String repRecord) {
        this.repRecord = repRecord;
    }
    public String getVisitReport() {
        return this.visitReport;
    }
    public void setVisitReport(String visitReport) {
        this.visitReport = visitReport;
    }
    public String getColOutHistory() {
        return this.colOutHistory;
    }
    public void setColOutHistory(String colOutHistory) {
        this.colOutHistory = colOutHistory;
    }
    public String getRepayPlan() {
        return this.repayPlan;
    }
    public void setRepayPlan(String repayPlan) {
        this.repayPlan = repayPlan;
    }
    public String getTaskInfo() {
        return this.taskInfo;
    }
    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
    }
    public String getOverdueRecord() {
        return this.overdueRecord;
    }
    public void setOverdueRecord(String overdueRecord) {
        this.overdueRecord = overdueRecord;
    }
    
}
