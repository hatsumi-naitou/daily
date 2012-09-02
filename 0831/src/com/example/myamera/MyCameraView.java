package com.example.myamera;

import java.io.FileOutputStream;


import android.content.Context;
import android.hardware.Camera;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MyCameraView extends SurfaceView 
	implements SurfaceHolder.Callback, Camera.PictureCallback{
		private SurfaceHolder holder;
		private Camera camera;
		
		public MyCameraView(Context context){
			super(context);
			holder =getHolder();
			holder.addCallback(this);
			holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		
		public void surfaceCreated(SurfaceHolder holder){
			camera = Camera.open();
			try{
				camera.setPreviewDisplay(holder);
			} catch (Exception e){
			}
		}
		public void surfaceChanged(
				SurfaceHolder holder, int format, int width, int height) {
			camera.startPreview();
		}
		
		public void surfaceDestroyed(SurfaceHolder holder){
			camera.setPreviewCallback(null);
			camera.stopPreview();
			camera.release();
			camera = null;
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event){
			if (event.getAction() == MotionEvent.ACTION_DOWN){
				camera.takePicture(null, null, this);
			}
			return true;			
		}
		
		public void onPitctureTaken(byte[] data, Camera camera){
			FileOutputStream fos = null;
			try{
				fos = new FileOutputStream("/sdcard/test.jpg");
				fos.write(data);
				fos.close();				
			}catch (Exception e){
			}
			camera.startPreview();
		}

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
	
}
