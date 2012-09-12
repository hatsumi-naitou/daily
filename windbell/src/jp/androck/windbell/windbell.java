package jp.androck.windbell;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import jp.androck.windbell.ShakeListener.OnShakeListener;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class windbell extends Activity {
	private MediaPlayer mPlayer;
	private MediaPlayer mp_1;
	private MediaPlayer mp_2;
	private MediaPlayer mp_3;
	private MediaPlayer mp_4;
	private MediaPlayer mp_5;
	private ShakeListener mShakeListener;
	private TimerTask mTimerTask;
	private Timer mTimer;
	private Handler mHandler = new Handler();
	private int[] intMove01Array = {2, 1, 1, 1, 0};
	private int[] intMove02Array = {5, 4, 3, 2, 1};
	private int intDuration = 2500;	
	private int intDurationCount = 3;
	private int intStarImage01Width = 0;
	private int intEndImage01Width = 0;
	private int intStarImage01Height = 0;
	private int intEndImage01Height = 0;
	private int intStarImage02Width = 0;
	private int intEndImage02Width = 0;
	private int intStarImage02Height = 0;
	private int intEndImage02Height = 0;
	private int intShakeType = 0;
	private int intShakeTypeCount = 0;
	private float[] nShakeOverListArray	= {30, 50, 80};
	private float nShakeSpeed = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(jp.androck.windbell.R.layout.main);
//		AndrockCover.start(this);


		mShakeListener = new ShakeListener(this);
		mp_1 = MediaPlayer.create(this, R.raw.furin001);	
		mp_2 = MediaPlayer.create(this, R.raw.furin002);	
		mp_3 = MediaPlayer.create(this, R.raw.furin003);
		mp_4 = MediaPlayer.create(this, R.raw.furin004);
		mp_5 = MediaPlayer.create(this, R.raw.furin005);
		mPlayer = mp_1;

		//シェイク動作
		mShakeListener.setOnShakeListener(new OnShakeListener() {
			// シェイクを検知した場合
			public void onShake() {
				if(nShakeOverListArray[0] > nShakeSpeed){
					intShakeType = 3;
				} else if(nShakeOverListArray[1] > nShakeSpeed) {
					intShakeType = 2;
				} else if(nShakeOverListArray[2] > nShakeSpeed) {
					intShakeType = 1;
				} else {
					intShakeType = 0;
				}
				TimerAction();	//処理実行
			}
			public void setShakeSpeed(float values) {
				nShakeSpeed	= values;
			}
		});


	}

	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

	}

	//タッチイベント
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() != MotionEvent.ACTION_DOWN){return true;}
		float nTouchX		= event.getX();			//タッチ位置（横）
		float nTouchY		= event.getY();			//タッチ位置（縦）
		int intCount		= 0;
		boolean	blnAction	= false;

		//ステータスバーの高さ取得
		Rect rect			= new Rect();
		Window window		= getWindow();
		window.getDecorView().getWindowVisibleDisplayFrame(rect);
		int statusBarHeight	= rect.top;

		try {
			Bitmap bitmap01		= BitmapFactory.decodeResource(getResources(), R.drawable.hurin01);	//画像01の座標取得で使用
			Bitmap bitmap02		= BitmapFactory.decodeResource(getResources(), R.drawable.hurin02);	//画像02の座標取得で使用
			WindowManager wm	= (WindowManager) getSystemService(Context.WINDOW_SERVICE);
			Display display		= wm.getDefaultDisplay();											//ディスプレイのサイズ取得で使用

			intStarImage01Width		= (display.getWidth() - bitmap01.getWidth()) / 2;
			intEndImage01Width		= (display.getWidth() + bitmap01.getWidth()) / 2;
			intStarImage01Height	= statusBarHeight+1;								//画像01の上位置（縦）
			intEndImage01Height		= intStarImage01Height + bitmap01.getHeight();		//画像01の下位置（縦）
			intStarImage02Width		= (display.getWidth() - bitmap02.getWidth()) / 2;	//画像02の左位置（横）
			intEndImage02Width		= (display.getWidth() + bitmap02.getWidth()) / 2;	//画像02の右位置（横）
			intStarImage02Height	= intEndImage01Height + 1;							//画像02の上位置（縦）
			intEndImage02Height		= intStarImage02Height + bitmap02.getHeight();		//画像02の下位置（縦）

			int [] TouchScopeStartWidth		= {intStarImage01Width, intStarImage02Width};
			int [] TouchScopeEndWidth		= {intEndImage01Width, intEndImage02Width};
			int [] TouchScopeStartHeight	= {intStarImage01Height, intStarImage02Height};
			int [] TouchScopeEndHeight		= {intEndImage01Height, intEndImage02Height};

			while(intCount < TouchScopeStartWidth.length) {
				if(TouchScopeStartWidth[intCount] <= nTouchX
					&& TouchScopeEndWidth[intCount] >= nTouchX
					&& TouchScopeStartHeight[intCount] <= nTouchY
					&& TouchScopeEndHeight[intCount] >= nTouchY
				) {
					blnAction	= true;
					break;
				}
				intCount	= intCount + 1;
			}

			if(blnAction == true){
				intShakeType	= 0;
				TimerAction();	//処理実行
			}
		}catch(Exception e){
			//タッチイベントの繰り返し処理エラー
		}
		return true;
	}

	//定期的に処理を実行するTimerTask処理
	public void TimerAction() {
		stopAutoInvisible();					//TimerTaskの停止処理
		intShakeTypeCount	= intShakeType;
		mTimer	= new Timer(false);	// タイマーを生成

		// スケジュールを設定
		mTimer.schedule(mTimerTask=new TimerTask() {
			@Override
			public void run() {
				mHandler.post(new Runnable() {
					public void run() {
						SetSoundPlayer(intShakeTypeCount);			//再生する音ファイルをセット
						SoundPlayer();								//音再生・停止
						ImageMove(intMove01Array[intShakeTypeCount],intMove02Array[intShakeTypeCount]);		//画像のアニメーション動作
						intShakeTypeCount	= intShakeTypeCount + 1;

						if(intShakeTypeCount >= intMove01Array.length) {
							mTimer.cancel();
						}
					}
				});
			}
		}, 0, intDuration); // 初回起動の遅延(0sec)と周期(intDuration)指定
	}

	//TimerTask処理の停止設定（連続して処理を行ったときにエラー落ちするのを防ぐ）
	public void stopAutoInvisible() {
		//TimerTaskが開始状態の場合
		if (mTimerTask != null) {
			mTimerTask.cancel();
			mTimer.purge();
			mTimerTask	= null;
		}
	}

	//画像のアニメーション動作
	public void ImageMove(int Rotate01, int Rotate02) {
		ImageView imgView01 		= (ImageView) findViewById(R.id.imageView1);	//動作させる対象を取得（画像01）
		ImageView imgView02 		= (ImageView) findViewById(R.id.imageView2);	//動作させる対象を取得（画像02）

		RotateAnimation rotateImg01 = new RotateAnimation(0, Rotate01, imgView01.getWidth()/2, 0);
		RotateAnimation rotateImg02 = new RotateAnimation(0, Rotate02, imgView02.getWidth()/2, 0);

		//○○秒で回転する
		rotateImg01.setDuration(intDuration);
		rotateImg02.setDuration(intDuration);

		//○回繰り返す
		rotateImg01.setInterpolator(new CycleInterpolator(intDurationCount));
		rotateImg02.setInterpolator(new CycleInterpolator(intDurationCount));

		//アニメーションスタート
		imgView01.startAnimation(rotateImg01);
		imgView02.startAnimation(rotateImg02);
	}

	//playerに音ファイルをセット
	public void SetSoundPlayer(int Count) {
		switch(Count) {
			case 0:	//最大の音
				mPlayer	= mp_1;
				break;
			case 1:	//少し大きい音
				mPlayer	= mp_2;
				break;
			case 2:	//中の音
				mPlayer	= mp_3;
				break;
			case 3:	//少し小さい音
				mPlayer	= mp_4;
				break;
			case 4:	//最小の音
				mPlayer	= mp_5;
				break;
			default:
				break;
		}
		return;
	}

	//音ファイルの再生
	public void SoundPlayer() {
		try {
			if (mPlayer.isPlaying()) { // 再生中
				mPlayer.stop();
					try {
						mPlayer.prepare();
						mPlayer.start();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
			} else { // 停止中
				mPlayer.start();
			}
		} catch (Exception e) {
			//再生失敗
		}
	}

	//SoundPlayerの停止処理
	public void MediaPlayerStop(){
		if (mPlayer.isPlaying()) {
			mPlayer.stop();
		}
		if (mp_1.isPlaying()) {
			mp_1.stop();
		}
		if (mp_2.isPlaying()) {
			mp_2.stop();
		}
		if (mp_3.isPlaying()) {
			mp_3.stop();
		}
		if (mp_4.isPlaying()) {
			mp_4.stop();
		}
		if (mp_5.isPlaying()) {
			mp_5.stop();
		}
	}

	// アプリの開始
	@Override
	protected void onResume() {
		// アプリの開始
		super.onResume();
		mShakeListener.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mShakeListener.onPause();
		MediaPlayerStop();			//MediaPlayerをとめる
		stopAutoInvisible();		//TimerTask停止処理
		finish();
	}

	@Override
	public void onDestroy(){
		mShakeListener.onPause();
		MediaPlayerStop();			//MediaPlayerをとめる
		stopAutoInvisible();		//TimerTask停止処理
		super.onDestroy();
		// アクティビティの終了
		finish();
	}
}