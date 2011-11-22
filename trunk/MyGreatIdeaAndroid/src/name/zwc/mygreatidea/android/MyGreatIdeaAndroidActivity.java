package name.zwc.mygreatidea.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyGreatIdeaAndroidActivity extends ActivityBase
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
    }
    
    @Override
    protected void onResume()
    {
    	super.onResume();
    	
    	new Thread(new Runnable()
		{
			public void run()
			{
				startActivity("MainActivity");
				finish();
			}
		}).start();
    }
}