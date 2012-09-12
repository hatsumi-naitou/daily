package jp.androck.windbell;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeListener implements SensorEventListener {
	private SensorManager mSensorManager;
	private OnShakeListener mListener;
	private long nBeforeTime;			//前回の時間
	private float nBeforeX;				//前回のx値
	private float nBeforeY;				//前回のy値
	private float nBeforeZ;				//前回のz値
	private int intCount;
	private int intSpeed		= 20;	//加速度の規定値（最低値）
	public float nShakeSpeed	= 0;	//加速度の値
	private int intMaxCount		= 1;	//計測回数（2回以上の場合）

	// シェイクを感知したときにonShakeメソッドを呼び出します
	public interface OnShakeListener {
		void onShake();
		void setShakeSpeed(float shakeSpeed);
	}

	// OnShakeListenerをセット
	public void setOnShakeListener(OnShakeListener Listener) {
		mListener	= Listener;
	}

	public ShakeListener(Context context) {
		// SensorManagerのインスタンスを取得
		mSensorManager	= (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
	}

	public void onResume() {
		// 加速度センサーを取得
		List<Sensor> list	= mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

		if (list.size() < 1)
			return;

		// 加速度センサーにリスナーを取得
		mSensorManager.registerListener(this, list.get(0),SensorManager.SENSOR_DELAY_UI);
	}

	public void onPause() {
		// リスナーを解除
		mSensorManager.unregisterListener(this);
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	// センサーの値が変わったら呼び出される
	public void onSensorChanged(SensorEvent event) {
		// センサーのタイプが加速度センサーじゃない場合
		if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER) {
			return;
		}

		long nNowTime	= System.currentTimeMillis();
		long nDiffTime	= nNowTime - nBeforeTime;

		if (nDiffTime > 500) {
			float x	= event.values[0];
			float y	= event.values[1];
			float z	= event.values[2];

			// 前回の値との差からスピードを計算
			float speed = Math.abs(x + y + z - nBeforeX - nBeforeY - nBeforeZ) / nDiffTime * 10000;
			nShakeSpeed	= speed;

			// スピードが規定値以上なら
			if (speed > intSpeed) {
				intCount++;		// シェイクカウントを足す

				// 4回連続スピードが規定値以上なら
				if (intCount > intMaxCount) {
					intCount = 0;
					mListener.setShakeSpeed(nShakeSpeed);

					// リスナーがセットされていれば
					if (mListener != null) {
						mListener.onShake();
					}
				}
			} else {
				// 規定値以下ならリセット
				intCount = 0;
			}
			// 前回値として保存
			nBeforeTime	= nNowTime;
			nBeforeX	= x;
			nBeforeY	= y;
			nBeforeZ	= z;
		}
	}
}