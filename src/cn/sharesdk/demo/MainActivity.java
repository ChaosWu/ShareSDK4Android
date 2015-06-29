/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2013年 mob.com. All rights reserved.
 */

package cn.sharesdk.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.sharesdk.demo.widget.SlidingMenu;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;

import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.UIHandler;

public class MainActivity extends Activity {
	private static final String TEST_TEXT = null;
	private static final String TEST_IMAGE = null;
	private static final String TEST_IMAGE_URL = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);

		LayoutParams buttonParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);

		Button button = new Button(this);
		button.setText("show Menu!!!");
		button.setLayoutParams(buttonParams);
		layout.addView(button);
		setContentView(layout);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.v("DDD", "Duang!");
				showShare();
			}
		});
	}

	protected void showShare() {
		OnekeyShare ok = new OnekeyShare();
		ok.setTheme(OnekeyShareTheme.CLASSIC);
		ok.show(this);

	}
}

// 使用快捷分享完成分享（请务必仔细阅读位于SDK解压目录下Docs文件夹中OnekeyShare类的JavaDoc）
/**
 * ShareSDK集成方法有两种</br>
 * 1、第一种是引用方式，例如引用onekeyshare项目，onekeyshare项目再引用mainlibs库</br>
 * 2、第二种是把onekeyshare和mainlibs集成到项目中，本例子就是用第二种方式</br> 请看“ShareSDK
 * 使用说明文档”，SDK下载目录中 </br> 或者看网络集成文档 http://wiki.mob.com/Android_%E5%BF%AB%E9%
 * 80%9F%E9%9B%86%E6%88%90%E6%8C%87%E5%8D%97
 * 3、混淆时，把sample或者本例子的混淆代码copy过去，在proguard-project.txt文件中
 * 
 * 
 * 平台配置信息有三种方式： 1、在我们后台配置各个微博平台的key 2、在代码中配置各个微博平台的key，http://mob.com/androidDoc
 * /cn/sharesdk/framework/ShareSDK.html 3、在配置文件中配置，本例子里面的assets/ShareSDK.conf,
 */
// private void showShare() {
// Context context = this;
// final OnekeyShare oks = new OnekeyShare();
//
// // oks.setAddress("12345678901");
// oks.setTitle(CustomShareFieldsPage.getString("title",
// context.getString(R.string.evenote_title)));
// oks.setTitleUrl(CustomShareFieldsPage.getString("titleUrl",
// "http://mob.com"));
// String customText = CustomShareFieldsPage.getString("text", null);
// if (customText != null) {
// oks.setText(customText);
// } else if (MainActivity.TEST_TEXT != null
// && MainActivity.TEST_TEXT.containsKey(0)) {
// oks.setText(MainActivity.TEST_TEXT.get(0));
// } else {
// oks.setText(context.getString(R.string.share_content));
// }
//
// oks.setImagePath(CustomShareFieldsPage.getString("imagePath",
// MainActivity.TEST_IMAGE));
// oks.setImageUrl(CustomShareFieldsPage.getString("imageUrl",
// MainActivity.TEST_IMAGE_URL));
// // oks.setImageArray(new String[]{MainActivity.TEST_IMAGE,
// // MainActivity.TEST_IMAGE_URL});
//
// oks.setUrl(CustomShareFieldsPage.getString("url", "http://www.mob.com"));
// oks.setFilePath(CustomShareFieldsPage.getString("filePath",
// MainActivity.TEST_IMAGE));
// oks.setComment(CustomShareFieldsPage.getString("comment",
// context.getString(R.string.share)));
// oks.setSite(CustomShareFieldsPage.getString("site",
// context.getString(R.string.app_name)));
// oks.setSiteUrl(CustomShareFieldsPage.getString("siteUrl",
// "http://mob.com"));
// oks.setVenueName(CustomShareFieldsPage.getString("venueName",
// "ShareSDK"));
// oks.setVenueDescription(CustomShareFieldsPage.getString(
// "venueDescription", "This is a beautiful place!"));
// oks.setLatitude(23.056081f);
// oks.setLongitude(113.385708f);
// oks.setSilent(false);
//
// String theme = CustomShareFieldsPage.getString("theme", "classic");
// if (OnekeyShareTheme.SKYBLUE.toString().toLowerCase().equals(theme)) {
// oks.setTheme(OnekeyShareTheme.SKYBLUE);
// } else {
// oks.setTheme(OnekeyShareTheme.CLASSIC);
// }
//
// // 令编辑页面显示为Dialog模式
// oks.setDialogMode();
//
// // 在自动授权时可以禁用SSO方式
// // if(!CustomShareFieldsPage.getBoolean("enableSSO", true))
// oks.disableSSOWhenAuthorize();
//
// // 去除注释，则快捷分享的操作结果将通过OneKeyShareCallback回调
// // oks.setCallback(new OneKeyShareCallback());
//
// // 去自定义不同平台的字段内容
// // oks.setShareContentCustomizeCallback(new
// // ShareContentCustomizeDemo());
//
// // 去除注释，演示在九宫格设置自定义的图标
// Bitmap enableLogo = BitmapFactory.decodeResource(getResources(),
// R.drawable.ic_launcher);
// Bitmap disableLogo = BitmapFactory.decodeResource(getResources(),
// R.drawable.sharesdk_unchecked);
// String label = getResources().getString(R.string.app_name);
// OnClickListener listener = new OnClickListener() {
// public void onClick(View v) {
// String text = "Customer Logo -- ShareSDK "
// + ShareSDK.getSDKVersionName();
// Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
// }
// };
// oks.setCustomerLogo(enableLogo, disableLogo, label, listener);
//
// // 去除注释，则快捷分享九宫格中将隐藏新浪微博和腾讯微博
// // oks.addHiddenPlatform(SinaWeibo.NAME);
// // oks.addHiddenPlatform(TencentWeibo.NAME);
//
// // 为EditPage设置一个背景的View
// oks.setEditPageBackground(getPage());
// oks.show(context);
// }
// }

// ****************************************************
/**
 * 项目的入口类，是侧栏控件的外壳
 * <p>
 * 侧栏的UI或者逻辑控制基本上都在{@link MainAdapter}中进行
 */
// public class MainActivity extends Activity implements Callback {
// private static final String FILE_NAME = "pic_lovely_cats.jpg";
// public static String TEST_IMAGE;
// public static String TEST_IMAGE_URL;
// public static HashMap<Integer, String> TEST_TEXT;
// private SlidingMenu menu;
// private int orientation;
//
// protected void onCreate(Bundle savedInstanceState) {
// super.onCreate(savedInstanceState);
// orientation = getResources().getConfiguration().orientation;
//
// menu = new SlidingMenu(this);
// menu.setMenuItemBackground(R.color.sliding_menu_item_down,
// R.color.sliding_menu_item_release);
// menu.setMenuBackground(R.color.sliding_menu_background);
// menu.setTtleHeight(com.mob.tools.utils.R.dipToPx(this, 44));
// menu.setBodyBackground(R.color.sliding_menu_body_background);
// menu.setShadowRes(R.drawable.sliding_menu_right_shadow);
// menu.setMenuDivider(R.drawable.sliding_menu_sep);
// menu.setAdapter(new MainAdapter(menu));
// setContentView(menu);
//
// ShareSDK.initSDK(this);
// ShareSDK.registerPlatform(LaiwangCustomize.class);
// ShareSDK.setConnTimeout(20000);
// ShareSDK.setReadTimeout(20000);
//
// new Thread() {
// public void run() {
// TEST_IMAGE_URL = "http://f1.sharesdk.cn/imgs/2014/05/21/oESpJ78_533x800.jpg";
// initImagePath();
// initTestText();
// UIHandler.sendEmptyMessageDelayed(1, 100, MainActivity.this);
// }
// }.start();
//
// }
//
// private void initImagePath() {
// try {
// String cachePath = com.mob.tools.utils.R.getCachePath(this, null);
// TEST_IMAGE = cachePath + FILE_NAME;
// File file = new File(TEST_IMAGE);
// if (!file.exists()) {
// file.createNewFile();
// Bitmap pic = BitmapFactory.decodeResource(getResources(), R.drawable.pic);
// FileOutputStream fos = new FileOutputStream(file);
// pic.compress(CompressFormat.JPEG, 100, fos);
// fos.flush();
// fos.close();
// }
// } catch(Throwable t) {
// t.printStackTrace();
// TEST_IMAGE = null;
// }
// Log.i("TEST_IMAGE path ==>>>", TEST_IMAGE);
// }
//
// private void initTestText() {
// TEST_TEXT = new HashMap<Integer, String>();
// try {
// NetworkHelper network = new NetworkHelper();
// String resp = network.httpGet("http://mob.com/Assets/snsplat.json", null,
// null, null);
// JSONObject json = new JSONObject(resp);
// int status = json.optInt("status");
// if (status == 200) {
// JSONArray democont = json.optJSONArray("democont");
// if (democont != null && democont.length() > 0) {
// for (int i = 0, size = democont.length(); i < size; i++) {
// JSONObject plat = democont.optJSONObject(i);
// if (plat != null) {
// int snsplat = plat.optInt("snsplat", -1);
// String cont = plat.optString("cont");
// TEST_TEXT.put(snsplat, cont);
// }
// }
// }
// }
// } catch(Throwable t) {
// t.printStackTrace();
// }
// }
//
// public boolean handleMessage(Message msg) {
// switch (msg.what) {
// case 1: {
// menu.triggerItem(MainAdapter.GROUP_DEMO, MainAdapter.ITEM_DEMO);
// }
// break;
// case 2: {
// String text = getString(R.string.receive_rewards, msg.arg1);
// Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
// }
// break;
// }
// return false;
// }
//
// /** 屏幕旋转后，此方法会被调用，以刷新侧栏的布局 */
// public void onConfigurationChanged(Configuration newConfig) {
// super.onConfigurationChanged(newConfig);
// if (orientation != newConfig.orientation) {
// orientation = newConfig.orientation;
// menu.refresh();
// }
// }
//
// public boolean onKeyDown(int keyCode, KeyEvent event) {
// if (keyCode == KeyEvent.KEYCODE_BACK
// && event.getAction() == KeyEvent.ACTION_DOWN
// && !menu.isMenuShown()) {
// menu.showMenu();
// return true;
// } else if (keyCode == KeyEvent.KEYCODE_BACK
// && event.getAction() == KeyEvent.ACTION_DOWN
// && menu.isMenuShown()) {
// }
// return super.onKeyDown(keyCode, event);
// }
//
// /** 将action转换为String */
// public static String actionToString(int action) {
// switch (action) {
// case Platform.ACTION_AUTHORIZING: return "ACTION_AUTHORIZING";
// case Platform.ACTION_GETTING_FRIEND_LIST: return
// "ACTION_GETTING_FRIEND_LIST";
// case Platform.ACTION_FOLLOWING_USER: return "ACTION_FOLLOWING_USER";
// case Platform.ACTION_SENDING_DIRECT_MESSAGE: return
// "ACTION_SENDING_DIRECT_MESSAGE";
// case Platform.ACTION_TIMELINE: return "ACTION_TIMELINE";
// case Platform.ACTION_USER_INFOR: return "ACTION_USER_INFOR";
// case Platform.ACTION_SHARE: return "ACTION_SHARE";
// default: {
// return "UNKNOWN";
// }
// }
// }
//
// }
