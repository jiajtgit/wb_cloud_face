package tech.ucoon.wb_cloud_face.wbcloud;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.tencent.cloud.huiyansdkface.facelight.api.FaceVerifyConfig;
import com.tencent.cloud.huiyansdkface.facelight.api.WbCloudFaceContant;
import com.tencent.cloud.huiyansdkface.facelight.api.WbCloudFaceVerifySdk;
import com.tencent.cloud.huiyansdkface.facelight.api.listeners.WbCloudFaceVerifyLoginListener;
import com.tencent.cloud.huiyansdkface.facelight.api.result.WbFaceError;
import com.tencent.cloud.huiyansdkface.facelight.process.FaceVerifyStatus;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import tech.ucoon.wb_cloud_face.wbcloud.entity.WbCloudFaceParams;
import tech.ucoon.wb_cloud_face.wbcloud.entity.WbCloudFaceVerifyConfig;
import tech.ucoon.wb_cloud_face.wbcloud.entity.WbFaceVerifyResult;

/**
 * 腾讯活体检测工具类
 */
public class WbCloudFaceVerifyKit {
    private static final String TAG = WbCloudFaceVerifyKit.class.getSimpleName();

    /**
     * 初始化刷脸SDK输入参数
     *
     * @return SDK输入参数
     */
    public static WbCloudFaceVerifySdk.InputData initWbCloudFaceInputData(HashMap<String, String> paramsMap) {
        WbCloudFaceParams params = injectBean(WbCloudFaceParams.class, paramsMap);
        WbCloudFaceVerifySdk.InputData inputData = new WbCloudFaceVerifySdk.InputData(
                params.getFaceId(),
                params.getOrder(),
                params.getAppId(),
                "1.0.0",
                params.getNonce(),
                params.getUserId(),
                params.getSign(),
                FaceVerifyStatus.Mode.GRADE,
                params.getKeyLicence());
        return inputData;
    }

    /**
     * 调用腾讯云活体检测SDK, 比对类型 默认为公安网纹图片对比
     *
     * @param activity  上下文
     * @param inputData 输入参数
     * @param configMap 配置参数
     * @param listener  核验回调
     */
    public static void openCloudFaceService(Activity activity, WbCloudFaceVerifySdk.InputData inputData,
                                            HashMap<String, String> configMap, WbCloudFaceVerifyResultListener listener) {
        Log.d(TAG, "openCloudFaceService");
        WbCloudFaceVerifyConfig config = injectBean(WbCloudFaceVerifyConfig.class, configMap);
        Bundle data = new Bundle();
        WbCloudFaceVerifyResult wbCloudFaceVerifyResult = new WbCloudFaceVerifyResult();
        data.putSerializable(WbCloudFaceContant.INPUT_DATA, inputData);
        //个性化参数设置,此处均设置为与默认相反
        //默认设置为简体中文，此处设置为英文
        data.putString(WbCloudFaceContant.LANGUAGE, config.getLanguage());
        //sdk样式设置，默认为白色
        data.putString(WbCloudFaceContant.COLOR_MODE, config.getColorMode());
        //定制合作方个性化提示语
        //此处将设置人脸采集时的个性化提示语
        data.putString(WbCloudFaceContant.CUSTOMER_TIPS_LIVE, config.getCustomerTipsLive());
        //此处将设置上传人脸时的个性化提示语
        data.putString(WbCloudFaceContant.CUSTOMER_TIPS_UPLOAD, config.getCustomerTipsUpload());
        //设置合作方定制提示语的位置，默认为识别框的下方
        //识别框的下方： WbCloudFaceContant.CUSTOMER_TIPS_LOC_BOTTOM
        //识别框的上方：WbCloudFaceContant.CUSTOMER_TIPS_LOC_TOP
        //此处设置为识别框的上方
        data.putInt(WbCloudFaceContant.CUSTOMER_TIPS_LOC, config.getCustomerTipsLoc());
        //设置选择的比对类型  默认为权威数据源对比
        //此处设置权威数据源比对
        data.putString(WbCloudFaceContant.COMPARE_TYPE, config.getCompareType());
        //是否需要录制上传视频 默认不需要，此处设置为不需要
        data.putBoolean(WbCloudFaceContant.VIDEO_UPLOAD, config.isVideoUpload());
        //是否对录制视频进行检查,默认不检查，此处设置为不检查
        data.putBoolean(WbCloudFaceContant.VIDEO_CHECK, config.isVideoCheck());
        //设置是否打开语音提示，默认关闭，此处设置为关闭
        data.putBoolean(WbCloudFaceContant.PLAY_VOICE, config.isPlayVoice());
        // //是否展示刷脸成功页面，默认不展示
        // data.putBoolean(WbCloudFaceContant.SHOW_SUCCESS_PAGE, config.isShowSuccessPage());
        // //是否展示刷脸失败页面，默认不展示
        // data.putBoolean(WbCloudFaceContant.SHOW_FAIL_PAGE, config.isShowFailPage());
        // //颜色设置,sdk内置黑色和白色两种模式，默认黑色
        // //如果客户想定制自己的皮肤，可以传入WbCloudFaceContant.CUSTOM模式,此时可以配置ui里各种元素的色值
        // //定制详情参考app/res/colors.xml文件里各个参数
        // data.putString(WbCloudFaceContant.COLOR_MODE, config.getColorMode());
        // //是否需要录制上传视频 默认需要
        // data.putBoolean(WbCloudFaceContant.VIDEO_UPLOAD, config.isVideoUpload());
        // //是否使用ipv6网络
        // data.putBoolean(WbCloudFaceContant.IS_IPV6, config.isIpv6());
        // //是否开启闭眼检测，默认不开启
        // data.putBoolean(WbCloudFaceContant.ENABLE_CLOSE_EYES, config.isEnableCloseEyes());
        // //是否播放提示音，默认播放
        // data.putBoolean(WbCloudFaceContant.PLAY_VOICE, config.isPlayVoice());
        // //设置选择的比对类型  默认为公安网纹图片对比
        // data.putString(WbCloudFaceContant.COMPARE_TYPE, config.getCompareType());
        // //打开美颜功能
        // FaceVerifyConfig.getInstance().enableFaceBeauty(false);

        data.putBoolean(WbCloudFaceContant.IS_ENABLE_LOG, true);

        Log.d(TAG, "WbCloudFaceVerifySdk initSdk");
        WbCloudFaceVerifySdk.getInstance().initSdk(activity, data, new WbCloudFaceVerifyLoginListener() {
            @Override
            public void onLoginSuccess() {
                //登录sdk成功
                Log.i(TAG, "onLoginSuccess");
                //拉起刷脸页面
                WbCloudFaceVerifySdk.getInstance().startWbFaceVerifySdk(activity, result -> {
                    WbFaceVerifyResult wbFaceVerifyResult = WbFaceVerifyResult.setWbFaceVerifyResult(result);
                    //得到刷脸结果
                    if (result != null) {
                        if (result.isSuccess()) {
                            wbCloudFaceVerifyResult.setCode(WbCloudFaceVerifyResult.SUCCEED);
                            Log.d(TAG, "刷脸成功! Sign=" + result.getSign() + "; liveRate=" + result.getLiveRate() +
                                    "; similarity=" + result.getSimilarity() + "userImageString=" + result.getUserImageString());
                        } else {
                            WbFaceError error = result.getError();
                            if (error != null) {
                                wbCloudFaceVerifyResult.setCode(Integer.parseInt(error.getCode()));
                                wbCloudFaceVerifyResult.setDescription(error.getDesc());
                                wbCloudFaceVerifyResult.setErrorReason(error.getReason());
                                Log.d(TAG, "刷脸失败！domain=" + error.getDomain() + " ;code= " + error.getCode()
                                        + " ;desc=" + error.getDesc() + ";reason=" + error.getReason());
                                if (error.getDomain().equals(WbFaceError.WBFaceErrorDomainCompareServer)) {
                                    Log.d(TAG, "对比失败，liveRate=" + result.getLiveRate() +
                                            "; similarity=" + result.getSimilarity());
                                }
                            } else {
                                Log.e(TAG, "sdk返回error为空！");
                            }
                        }
                    } else {
                        Log.e(TAG, "sdk返回结果为空！");
                    }
                    wbCloudFaceVerifyResult.setVerifyResult(parameters(wbFaceVerifyResult));
                    listener.onVerifyResultListener(wbCloudFaceVerifyResult);
                });
            }

            @Override
            public void onLoginFailed(WbFaceError error) {
                //登录失败
                Log.i(TAG, "onLoginFailed!");
                if (error != null) {
                    Log.d(TAG, "登录失败！domain=" + error.getDomain() + " ;code= " + error.getCode()
                            + " ;desc=" + error.getDesc() + ";reason=" + error.getReason());
                    wbCloudFaceVerifyResult.setCode(Integer.parseInt(error.getCode()));
                    wbCloudFaceVerifyResult.setDescription(error.getDesc());
                    wbCloudFaceVerifyResult.setErrorReason(error.getReason());
                } else {
                    Log.e(TAG, "sdk返回error为空！");
                }
                listener.onVerifyResultListener(wbCloudFaceVerifyResult);
            }
        });
    }

    public static <T> T injectBean(Class<T> beanClass, HashMap<String, String> parasMap) {
        T bean = null;
        try {
            //通过反射生成对象
            bean = beanClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        Method[] methods = beanClass.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("set") && methodName.length() > 3) {
                Class[] types = method.getParameterTypes();
                if (types.length == 1) {
                    String attrName = firstCharToLowerCase(methodName.substring(3));
                    if (parasMap.containsKey(attrName)) {
                        Object value = parasMap.get(attrName);
                        try {
                            method.invoke(bean, value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return bean;
    }

    //取字段名且让其首字母小写
    public static String firstCharToLowerCase(String substring) {
        if (substring != null && substring.charAt(0) >= 'A' && substring.charAt(0) <= 'Z') {
            char[] arr = substring.toCharArray();
            arr[0] = (char) (arr[0] + 32);
            return new String(arr);
        } else {
            return substring;
        }
    }

    public static Map<String, Object> parameters(Object obj) {
        if (obj == null) return null;
        Map<String, Object> map = new HashMap<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (Exception ignored) {
            }
        }
        return map;
    }
}
