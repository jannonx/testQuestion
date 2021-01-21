package com.guyuan.dear.test.bean;

import java.io.Serializable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2021/1/21 15:41
 * @company: 固远（深圳）信息技术有限公司
 */
public class Question implements Serializable {
    public String question;
    public  String answerA;
    public String answerB;
    public String answerC;
    public String answerD;
    public int answer;
    public String parse;
    public int id ;
    //用户选择的答案
    public int selectedAnswer;
}
