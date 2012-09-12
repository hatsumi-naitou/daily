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

		//�V�F�C�N����
		mShakeListener.setOnShakeListener(new OnShakeListener() {
			// �V�F�C�N�����m�����ꍇ
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
				TimerAction();	//�������s
			}
			public void setShakeSpeed(float values) {
				nShakeSpeed	= values;
			}
		});


	}

	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

	}

	//�^�b�`�C�x���g
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() != MotionEvent.ACTION_DOWN){return true;}
		float nTouchX		= event.getX();			//�^�b�`�ʒu�i���j
		float nTouchY		= event.getY();			//�^�b�`�ʒu�i�c�j
		int intCount		= 0;
		boolean	blnAction	= false;

		//�X�e�[�^�X�o�[�̍����擾
		Rect rect			= new Rect();
		Window window		= getWindow();
		window.getDecorView().getWindowVisibleDisplayFrame(rect);
		int statusBarHeight	= rect.top;

		try {
			Bitmap bitmap01		= BitmapFactory.decodeResource(getResources(), R.drawable.hurin01);	//�摜01�̍��W�擾�Ŏg�p
			Bitmap bitmap02		= BitmapFactory.decodeResource(getResources(), R.drawable.hurin02);	//�摜02�̍��W�擾�Ŏg�p
			WindowManager wm	= (WindowManager) getSystemService(Context.WINDOW_SERVICE);
			Display display		= wm.getDefaultDisplay();											//�f�B�X�v���C�̃T�C�Y�擾�Ŏg�p

			intStarImage01Width		= (display.getWidth() - bitmap01.getWidth()) / 2;
			intEndImage01Width		= (display.getWidth() + bitmap01.getWidth()) / 2;
			intStarImage01Height	= statusBarHeight+1;								//�摜01�̏�ʒu�i�c�j
			intEndImage01Height		= intStarImage01Height + bitmap01.getHeight();		//�摜01�̉��ʒu�i�c�j
			intStarImage02Width		= (display.getWidth() - bitmap02.getWidth()) / 2;	//�摜02�̍��ʒu�i���j
			intEndImage02Width		= (display.getWidth() + bitmap02.getWidth()) / 2;	//�摜02�̉E�ʒu�i���j
			intStarImage02Height	= intEndImage01Height + 1;							//�摜02�̏�ʒu�i�c�j
			intEndImage02Height		= intStarImage02Height + bitmap02.getHeight();		//�摜02�̉��ʒu�i�c�j

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
				TimerAction();	//�������s
			}
		}catch(Exception e){
			//�^�b�`�C�x���g�̌J��Ԃ������G���[
		}
		return true;
	}

	//����I�ɏ��������s����TimerTask����
	public void TimerAction() {
		stopAutoInvisible();					//TimerTask�̒�~����
		intShakeTypeCount	= intShakeType;
		mTimer	= new Timer(false);	// �^�C�}�[�𐶐�

		// �X�P�W���[����ݒ�
		mTimer.schedule(mTimerTask=new TimerTask() {
			@Override
			public void run() {
				mHandler.post(new Runnable() {
					public void run() {
						SetSoundPlayer(intShakeTypeCount);			//�Đ����鉹�t�@�C�����Z�b�g
						SoundPlayer();								//���Đ��E��~
						ImageMove(intMove01Array[intShakeTypeCount],intMove02Array[intShakeTypeCount]);		//�摜�̃A�j���[�V��������
						intShakeTypeCount	= intShakeTypeCount + 1;

						if(intShakeTypeCount >= intMove01Array.length) {
							mTimer.cancel();
						}
					}
				});
			}
		}, 0, intDuration); // ����N���̒x��(0sec)�Ǝ���(intDuration)�w��
	}

	//TimerTask�����̒�~�ݒ�i�A�����ď������s�����Ƃ��ɃG���[��������̂�h���j
	public void stopAutoInvisible() {
		//TimerTask���J�n��Ԃ̏ꍇ
		if (mTimerTask != null) {
			mTimerTask.cancel();
			mTimer.purge();
			mTimerTask	= null;
		}
	}

	//�摜�̃A�j���[�V��������
	public void ImageMove(int Rotate01, int Rotate02) {
		ImageView imgView01 		= (ImageView) findViewById(R.id.imageView1);	//���삳����Ώۂ��擾�i�摜01�j
		ImageView imgView02 		= (ImageView) findViewById(R.id.imageView2);	//���삳����Ώۂ��擾�i�摜02�j

		RotateAnimation rotateImg01 = new RotateAnimation(0, Rotate01, imgView01.getWidth()/2, 0);
		RotateAnimation rotateImg02 = new RotateAnimation(0, Rotate02, imgView02.getWidth()/2, 0);

		//�����b�ŉ�]����
		rotateImg01.setDuration(intDuration);
		rotateImg02.setDuration(intDuration);

		//����J��Ԃ�
		rotateImg01.setInterpolator(new CycleInterpolator(intDurationCount));
		rotateImg02.setInterpolator(new CycleInterpolator(intDurationCount));

		//�A�j���[�V�����X�^�[�g
		imgView01.startAnimation(rotateImg01);
		imgView02.startAnimation(rotateImg02);
	}

	//player�ɉ��t�@�C�����Z�b�g
	public void SetSoundPlayer(int Count) {
		switch(Count) {
			case 0:	//�ő�̉�
				mPlayer	= mp_1;
				break;
			case 1:	//�����傫����
				mPlayer	= mp_2;
				break;
			case 2:	//���̉�
				mPlayer	= mp_3;
				break;
			case 3:	//������������
				mPlayer	= mp_4;
				break;
			case 4:	//�ŏ��̉�
				mPlayer	= mp_5;
				break;
			default:
				break;
		}
		return;
	}

	//���t�@�C���̍Đ�
	public void SoundPlayer() {
		try {
			if (mPlayer.isPlaying()) { // �Đ���
				mPlayer.stop();
					try {
						mPlayer.prepare();
						mPlayer.start();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
			} else { // ��~��
				mPlayer.start();
			}
		} catch (Exception e) {
			//�Đ����s
		}
	}

	//SoundPlayer�̒�~����
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

	// �A�v���̊J�n
	@Override
	protected void onResume() {
		// �A�v���̊J�n
		super.onResume();
		mShakeListener.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mShakeListener.onPause();
		MediaPlayerStop();			//MediaPlayer���Ƃ߂�
		stopAutoInvisible();		//TimerTask��~����
		finish();
	}

	@Override
	public void onDestroy(){
		mShakeListener.onPause();
		MediaPlayerStop();			//MediaPlayer���Ƃ߂�
		stopAutoInvisible();		//TimerTask��~����
		super.onDestroy();
		// �A�N�e�B�r�e�B�̏I��
		finish();
	}
}