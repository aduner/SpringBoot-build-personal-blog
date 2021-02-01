package com.aduner.util;
/**
 * 提取简介描述
 * */
public class ExtractDescription {
    //从html中提取纯文本
    public static String stripHT(String strHtml) {
        String txtcontent = strHtml.replaceAll("</?[^>]+>", ""); //剔出<html>的标签
        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符
        txtcontent= txtcontent.replaceAll("\n"," ");
        return txtcontent.strip();
    }
    public static String extract(String content){
        content=stripHT(content);
        content= content.replaceAll("toc]","");
        if(content.length()>70){
            content=content.substring(0,70)+"...";
        }
        return "摘要："+content.strip();
    }
}
