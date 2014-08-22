package rshankar.hindismsinhindi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashActivity extends Activity {
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

	
	@Override
	public void onBackPressed() {
		this.finish();
		super.onBackPressed();
	}
}