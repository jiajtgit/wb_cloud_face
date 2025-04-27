package tech.ucoon.wb_cloud_face.wbcloud.entity;

import com.tencent.cloud.huiyansdkface.facelight.api.WbCloudFaceContant;

import org.jetbrains.annotations.NotNull;

/**
 * 人脸核身配置参数类
 */
public class WbCloudFaceVerifyConfig {
    /*
        * 语言包设置，默认简体中文
        * 可选值：
        * 简体中文  WbCloudFaceContant.LANGUAGE_ZH_CN
        * 繁体中文  WbCloudFaceContant.LANGUAGE_ZH_HK
        * 英语  WbCloudFaceContant.LANGUAGE_EN
        * 印尼语  WbCloudFaceContant.LANGUAGE_ID
        * 日语  WbCloudFaceContant.LANGUAGE_JA
        * 韩语  WbCloudFaceContant.LANGUAGE_KO
        * 泰语 WbCloudFaceContant.LANGUAGE_TH
     */
    private String language = WbCloudFaceContant.LANGUAGE_ZH_CN;

    /* 此处将设置人脸采集时的个性化提示语
    * WbCloudFaceContant.CUSTOMER_TIPS_LIVE
    */
    private String customerTipsLive = "";

    /* 此处将设置上传人脸时的个性化提示语
    * WbCloudFaceContant.CUSTOMER_TIPS_UPLOAD
     */
    private String customerTipsUpload = "";

    /* 设置合作方定制提示语的位置，默认为识别框的下方
    * 识别框的下方： WbCloudFaceContant.CUSTOMER_TIPS_LOC_BOTTOM
    * 识别框的上方：WbCloudFaceContant.CUSTOMER_TIPS_LOC_TOP
    *   WbCloudFaceContant.CUSTOMER_TIPS_LOC
    */
    private int customerTipsLoc = WbCloudFaceContant.CUSTOMER_TIPS_LOC_BOTTOM;

    /* 是否对录制视频进行检查,默认不检查，此处设置为不检查  */
    private boolean videoCheck = false;

    //设置是否打开语音提示，默认关闭，此处设置为关闭
    private boolean playVoice = false;

    /**
     * 颜色设置，默认黑色
     */
    private String colorMode = WbCloudFaceContant.BLACK;

    /**
     * 是否需要录制上传视频，默认需要
     */
    private boolean videoUpload = true;

    /**
     * 比对类型，默认为公安网纹图片对比
     */
    private String compareType = WbCloudFaceContant.ID_CARD;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCustomerTipsLive() {
        return customerTipsLive;
    }
    public void setCustomerTipsLive(String customerTipsLive) {
        this.customerTipsLive = customerTipsLive;
    }

    public String getCustomerTipsUpload() {
        return customerTipsUpload;
    }
    public void setCustomerTipsUpload(String customerTipsUpload) {
        this.customerTipsUpload = customerTipsUpload;
    }

    public int getCustomerTipsLoc() {
        return customerTipsLoc;
    }
    public void setCustomerTipsLoc(int customerTipsLoc) {
        this.customerTipsLoc = customerTipsLoc;
    }

    public boolean isVideoCheck() {
        return videoCheck;
    }
    public void setVideoCheck(boolean videoCheck) {
        this.videoCheck = videoCheck;
    }

    public boolean isPlayVoice() {
        return playVoice;
    }

    public void setPlayVoice(boolean playVoice) {
        this.playVoice = playVoice;
    }


    public String getColorMode() {
        return colorMode;
    }

    public void setColorMode(String colorMode) {
        this.colorMode = colorMode;
    }

    public boolean isVideoUpload() {
        return videoUpload;
    }

    public void setVideoUpload(boolean videoUpload) {
        this.videoUpload = videoUpload;
    }
    public String getCompareType() {
        return compareType;
    }

    public void setCompareType(String compareType) {
        this.compareType = compareType;
    }

    @NotNull
    @Override
    public String toString() {
        return "WbCloudFaceVerifyConfig{" +
                "language='" + language + '\'' +
                ", customerTipsLive='" + customerTipsLive + '\'' +
                ", customerTipsUpload='" + customerTipsUpload + '\'' +
                ", customerTipsLoc=" + customerTipsLoc +
                ", videoCheck=" + videoCheck +
                ", playVoice=" + playVoice +
                ", colorMode='" + colorMode + '\'' +
                ", videoUpload=" + videoUpload +
                ", playVoice=" + playVoice +
                ", compareType='" + compareType + '\'' +
                '}';
    }
}
