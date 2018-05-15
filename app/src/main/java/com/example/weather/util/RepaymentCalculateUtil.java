package com.example.weather.util;

import java.text.DecimalFormat;
import java.util.HashMap;


public class RepaymentCalculateUtil {

    //按揭贷款还款计算器rpycalculator  aloanamt:贷款金额  arate:月利率 aloanterm:贷款期限（月） arpyway：还款方式
    public static HashMap<String, Object> calculate(double loanamt, double arate, int loanterm, String rpyway) {

        HashMap<String, Object> results = new HashMap<>();

        double rate = arate / 1200;



        //定义计算变量
        //期初本金余额
        double[] beg_prin_bal = new double[loanterm];
        //期末本金余额
        double[] end_prin_bal = new double[loanterm];
        //本期应还款本金
        double[] cur_rpy_prin = new double[loanterm];
        //本期应还款利息
        double[] cur_rpy_int = new double[loanterm];
        //本期应还本息合计
        double[] cur_rpy_sum = new double[loanterm];
        double sumint = 0.00d;//总利息
        double totalamt = 0.00d;//还款总额

        for (int i = 0; i < loanterm; i++) {
            beg_prin_bal[i] = 0.00d;
            end_prin_bal[i] = 0.00d;
            cur_rpy_prin[i] = 0.00d;
            cur_rpy_int[i] = 0.00d;
            cur_rpy_sum[i] = 0.00d;
        }

        if (rpyway.equals("2")) {
            //等额本金
            double averprin = format(loanamt / loanterm);    //每期应还本金

            for (int term = 0; term < loanterm; term++) {
                if (term == 0) {
                    beg_prin_bal[term] = loanamt;
                } else {
                    beg_prin_bal[term] = end_prin_bal[term - 1];
                }
                cur_rpy_int[term] = format(beg_prin_bal[term] * rate);
                if (term == loanterm - 1) {
                    cur_rpy_prin[term] = beg_prin_bal[term];
                } else {
                    cur_rpy_prin[term] = averprin;
                }
                cur_rpy_sum[term] = format(cur_rpy_prin[term] + cur_rpy_int[term]);
                end_prin_bal[term] = format(beg_prin_bal[term] - cur_rpy_prin[term]);

                sumint = format(sumint + cur_rpy_int[term]);
            }
        }
        if (rpyway.equals("1")) {
            //等额本息
            double aversum = 0.00d;    //本期应还本息合计
            double tempval = 0.00d;    //等额本息计算系数 （1+rate）的期数次方
            tempval = Math.pow(1 + rate, loanterm);
            aversum = format(loanamt * rate * tempval / (tempval - 1));
            for (int term = 0; term < loanterm; term++) {
                if (term == 0) {
                    beg_prin_bal[term] = loanamt;
                } else {
                    beg_prin_bal[term] = end_prin_bal[term - 1];
                }
                cur_rpy_int[term] = format(beg_prin_bal[term] * rate);
                if (term == loanterm-1) {
                    cur_rpy_prin[term] = beg_prin_bal[term];
                } else {
                    cur_rpy_prin[term] = format(aversum - cur_rpy_int[term]);
                }
                cur_rpy_sum[term] = format(cur_rpy_prin[term] + cur_rpy_int[term]);
                end_prin_bal[term] = format(beg_prin_bal[term] - cur_rpy_prin[term]);

                sumint = format(sumint + cur_rpy_int[term]);
            }
        }
        totalamt = sumint + loanamt;
        results.put("beg_prin_bal",beg_prin_bal);
        results.put("end_prin_bal",end_prin_bal);
        results.put("cur_rpy_prin",cur_rpy_prin);
        results.put("cur_rpy_int",cur_rpy_int);
        results.put("cur_rpy_sum",cur_rpy_sum);

        results.put("totalamt",totalamt);
        results.put("sumint",sumint);

        return results;



    }

    private static Double format(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
      return  Double.parseDouble( df.format(d));
//        BigDecimal bigDecimal = new BigDecimal(d);
//        //这里的 2 就是你要保留几位小数。
//        return bigDecimal.setScale(2, BigDecimal.ROUND_UP).doubleValue();
    }
    public static String roundByScale(double v) {
        String formatStr = "0.";
        for(int i=0;i<2;i++){
            formatStr = formatStr + "0";
        }
        return new DecimalFormat(formatStr).format(v);
    }


}
