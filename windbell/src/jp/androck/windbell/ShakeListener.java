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
	private long nBeforeTime;			//�O��̎���
	private float nBeforeX;				//�O���x�l
	private float nBeforeY;				//�O���y�l
	private float nBeforeZ;				//�O���z�l
	private int intCount;
	private int intSpeed		= 20;	//�����x�̋K��l�i�Œ�l�j
	public float nShakeSpeed	= 0;	//�����x�̒l
	private int intMaxCount		= 1;	//�v���񐔁i2��ȏ�̏ꍇ�j

	// �V�F�C�N�����m�����Ƃ���onShake���\�b�h���Ăяo���܂�
	public interface OnShakeListener {
		void onShake();
		void setShakeSpeed(float shakeSpeed);
	}

	// OnShakeListener���Z�b�g
	public void setOnShakeListener(OnShakeListener Listener) {
		mListener	= Listener;
	}

	public ShakeListener(Context context) {
		// SensorManager�̃C���X�^���X���擾
		mSensorManager	= (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
	}

	public void onResume() {
		// �����x�Z���T�[���擾
		List<Sensor> list	= mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

		if (list.size() < 1)
			return;

		// �����x�Z���T�[�Ƀ��X�i�[���擾
		mSensorManager.registerListener(this, list.get(0),SensorManager.SENSOR_DELAY_UI);
	}

	public void onPause() {
		// ���X�i�[������
		mSensorManager.unregisterListener(this);
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	// �Z���T�[�̒l���ς������Ăяo�����
	public void onSensorChanged(SensorEvent event) {
		// �Z���T�[�̃^�C�v�������x�Z���T�[����Ȃ��ꍇ
		if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER) {
			return;
		}

		long nNowTime	= System.currentTimeMillis();
		long nDiffTime	= nNowTime - nBeforeTime;

		if (nDiffTime > 500) {
			float x	= event.values[0];
			float y	= event.values[1];
			float z	= event.values[2];

			// �O��̒l�Ƃ̍�����X�s�[�h���v�Z
			float speed = Math.abs(x + y + z - nBeforeX - nBeforeY - nBeforeZ) / nDiffTime * 10000;
			nShakeSpeed	= speed;

			// �X�s�[�h���K��l�ȏ�Ȃ�
			if (speed > intSpeed) {
				intCount++;		// �V�F�C�N�J�E���g�𑫂�

				// 4��A���X�s�[�h���K��l�ȏ�Ȃ�
				if (intCount > intMaxCount) {
					intCount = 0;
					mListener.setShakeSpeed(nShakeSpeed);

					// ���X�i�[���Z�b�g����Ă����
					if (mListener != null) {
						mListener.onShake();
					}
				}
			} else {
				// �K��l�ȉ��Ȃ烊�Z�b�g
				intCount = 0;
			}
			// �O��l�Ƃ��ĕۑ�
			nBeforeTime	= nNowTime;
			nBeforeX	= x;
			nBeforeY	= y;
			nBeforeZ	= z;
		}
	}
}