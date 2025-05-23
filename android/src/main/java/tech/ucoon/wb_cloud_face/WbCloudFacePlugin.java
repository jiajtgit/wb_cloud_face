package tech.ucoon.wb_cloud_face;

import android.app.Activity;
import androidx.annotation.NonNull;
import com.tencent.cloud.huiyansdkface.facelight.api.WbCloudFaceVerifySdk;
import java.util.HashMap;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import tech.ucoon.wb_cloud_face.wbcloud.WbCloudFaceVerifyKit;

/**
 * WbCloudFacePlugin
 */
public class WbCloudFacePlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
    private MethodChannel channel;
    private Activity mContext;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        flutterPluginBinding.getApplicationContext();
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "wb_cloud_face");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("openCloudFaceService")) {
            HashMap<String, String> params = call.argument("params");
            HashMap<String, String> config = call.argument("config");
            WbCloudFaceVerifySdk.InputData inputData = WbCloudFaceVerifyKit.initWbCloudFaceInputData(params);
            WbCloudFaceVerifyKit.openCloudFaceService(mContext, inputData, config, (res) -> {
                result.success(WbCloudFaceVerifyKit.parameters(res));
            });
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding activityPluginBinding) {
        mContext = activityPluginBinding.getActivity();
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {

    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding activityPluginBinding) {

    }

    @Override
    public void onDetachedFromActivity() {

    }
}
