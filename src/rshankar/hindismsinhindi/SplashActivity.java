package rshankar.hindismsinhindi;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

public class SplashActivity extends Activity {
	private static final String TAG = "SplashActivity";// 4 seconds
	private static final int TIME = 1000;// 4 seconds
	private Typeface splashTextFont;
	TextView title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
//		Typeface font = Typeface.createFromAsset(getAssets(), "JOKERMAN.TTF"); 
		splashTextFont=Typeface.createFromAsset(getAssets(),"JOKERMAN.TTF");
		
		title=(TextView)findViewById(R.id.title);
		title.setTypeface(splashTextFont);
		title.setText(getDayGreeting());
		 
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				Intent intent = new Intent(SplashActivity.this,
						MainActivity.class);
				startActivity(intent);

				SplashActivity.this.finish();

				overridePendingTransition(R.anim.scale_from_corner, R.anim.scale_to_corner);

			}
		}, TIME);
		
		new Handler().postDelayed(new Runnable() {
			  @Override
			  public void run() {
			         } 
			    }, TIME);

	}

	private String getDayGreeting(){
		int hrs = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		String greeting = "";
		if (hrs >=  0 && hrs <12) greeting = "सुप्रभात :\n हिंदी मैसेज में  आपका स्वागत हैं।";      // 
		if (hrs >= 12 && hrs <17) greeting = "\n हिंदी मैसेज में  आपका स्वागत हैं।";    // After 12pm
		if (hrs >= 17 && hrs <=23) greeting = "शुभ संध्या :\n हिंदी मैसेज में  आपका स्वागत हैं।";      // After 5pm
		Log.v(TAG, "Greeting Message"+greeting);
		return greeting;
	}
	
	@Override
	public void onBackPressed() {
		this.finish();
		super.onBackPressed();
	}
}