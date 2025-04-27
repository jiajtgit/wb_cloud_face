import 'dart:convert';

import 'package:wb_cloud_face/src/model/enum.dart';

WbCloudFaceVerifyConfig wbCloudFaceVerifyConfigFromJson(String str) =>
    WbCloudFaceVerifyConfig.fromJson(json.decode(str));

String wbCloudFaceVerifyConfigToJson(WbCloudFaceVerifyConfig data) => json.encode(data.toJson());

class WbCloudFaceVerifyConfig {
  WbCloudFaceVerifyConfig({
    this.language = Language.languageZhCn,
    this.customerTipsLive = '',
    this.customerTipsUpload = '',
    this.customerTipsLoc = 0,
    this.videoCheck = true,
    this.colorMode = ColorMode.black,
    this.videoUpload = true,
    this.playVoice = true,
    this.compareType = CompareType.idCard,
  });

  Language language; //语言，默认中文
  String customerTipsLive; //活体检测提示语，默认空
  String customerTipsUpload; //上传提示语，默认空
  int customerTipsLoc; //上传提示语位置，默认中间
  bool videoCheck; //是否需要录制上传视频，默认需要
  bool playVoice; //是否播放提示音，默认播放
  bool videoUpload; //是否需要录制上传视频，默认需要
  ColorMode colorMode; //颜色设置，默认黑色
  CompareType compareType; //比对类型，默认为公安网纹图片对比

  factory WbCloudFaceVerifyConfig.fromJson(Map<String, dynamic> json) => WbCloudFaceVerifyConfig(
        language: Language.values.firstWhere((element) => element.name == json["language"]),
        customerTipsLive: json["customerTipsLive"],
        customerTipsUpload: json["customerTipsUpload"],
        customerTipsLoc: json["customerTipsLoc"],
        videoCheck: json["videoCheck"],
        colorMode: ColorMode.values.firstWhere((element) => element.name == json["colorMode"]),
        videoUpload: json["videoUpload"],
        playVoice: json["playVoice"],
        compareType:
            CompareType.values.firstWhere((element) => element.name == json["compareType"]),
      );

  Map<String, dynamic> toJson() => {
        "colorMode": colorMode.name,
        "videoUpload": videoUpload,
        "playVoice": playVoice,
        "compareType": compareType.name,
        "language": language.name,
        "customerTipsLive": customerTipsLive,
        "customerTipsUpload": customerTipsUpload,
        "customerTipsLoc": customerTipsLoc,
        "videoCheck": videoCheck,
      };
}
