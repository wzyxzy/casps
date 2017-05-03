package com.hj.casps.entity.execcte;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 2017/5/2.
 */

public class ExecuteGain implements Serializable {
    private static final long serialVersionUID = -2152082572405015811L;
    private int return_code;//int	结果码，0 成功，101 无权限，201 数据库错误
    private int pagecount;//	int	符合条件记录总页数
    private List<ExecuteBills> mList;

    public ExecuteGain(int return_code, int pagecount, List<ExecuteBills> mList) {
        this.return_code = return_code;
        this.pagecount = pagecount;
        this.mList = mList;
    }

    public ExecuteGain() {
    }

    public int getReturn_code() {
        return return_code;
    }

    public void setReturn_code(int return_code) {
        this.return_code = return_code;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public List<ExecuteBills> getmList() {
        return mList;
    }

    public void setmList(List<ExecuteBills> mList) {
        this.mList = mList;
    }

    public static class ExecuteBills implements Serializable {

        private static final long serialVersionUID = -3908204127878341671L;

        private String id;//string	结款单id
        private String settleCode;//	string	结款单号
        private String mmbgetName;//	string		结款对方
        private String mmbpayName;//	string		结款对方（mmbpayId等于mmbgetName显示mmbpayName否则显示mmbgetName）
        private double settleMoney;//	Double		结款订单金额
        private String ctrTime;//Date		预计结款时间
        private double ctrMoney;//	Double	约定金额
        private double gotMoney;//double	已付金额
        private String status;//string	状态（status=4请求终止、status=5 && memberid=mmbpayid ? 撤回终止请求：同意终止、status=6 && memberid=mmbpayid ? 同意终止：撤回终止请求）

        public ExecuteBills(String id, String settleCode, String mmbgetName, String mmbpayName, double settleMoney, String ctrTime, double ctrMoney, double gotMoney, String status) {
            this.id = id;
            this.settleCode = settleCode;
            this.mmbgetName = mmbgetName;
            this.mmbpayName = mmbpayName;
            this.settleMoney = settleMoney;
            this.ctrTime = ctrTime;
            this.ctrMoney = ctrMoney;
            this.gotMoney = gotMoney;
            this.status = status;
        }

        public ExecuteBills() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSettleCode() {
            return settleCode;
        }

        public void setSettleCode(String settleCode) {
            this.settleCode = settleCode;
        }

        public String getMmbgetName() {
            return mmbgetName;
        }

        public void setMmbgetName(String mmbgetName) {
            this.mmbgetName = mmbgetName;
        }

        public String getMmbpayName() {
            return mmbpayName;
        }

        public void setMmbpayName(String mmbpayName) {
            this.mmbpayName = mmbpayName;
        }

        public double getSettleMoney() {
            return settleMoney;
        }

        public void setSettleMoney(double settleMoney) {
            this.settleMoney = settleMoney;
        }

        public String getCtrTime() {
            return ctrTime;
        }

        public void setCtrTime(String ctrTime) {
            this.ctrTime = ctrTime;
        }

        public double getCtrMoney() {
            return ctrMoney;
        }

        public void setCtrMoney(double ctrMoney) {
            this.ctrMoney = ctrMoney;
        }

        public double getGotMoney() {
            return gotMoney;
        }

        public void setGotMoney(double gotMoney) {
            this.gotMoney = gotMoney;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
