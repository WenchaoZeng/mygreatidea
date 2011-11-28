package name.zwc.mygreatidea.android.activities;

import name.zwc.mygreatidea.android.R;
import name.zwc.mygreatidea.android.adapters.IdeaListAdapter;
import name.zwc.mygreatidea.android.common.ActivityBase;
import name.zwc.mygreatidea.android.common.Helpers;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActivityBase
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // The submit idea button.
        linkButtonToActivity(R.id.buttonSubmitIdea, "SubmitIdeaActivity");
        
        // The setting button.
        linkButtonToActivity(R.id.buttonSetting, "SettingActivity");

        // Click an item in the idea list to show its detail.
		ListView listView = (ListView)findViewById(R.id.listViewIdeas);
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Helpers.startActivity(context, "IdeaDetailActivity", position);
			}
		});
		
		// The refresh button.
        setBackgroundTask(R.id.buttonRefresh, new Runnable()
		{
			public void run()
			{
				// Show idea list.
				IdeaListAdapter adapter = new IdeaListAdapter(context);
				ideas = service.getIdeas(Integer.MAX_VALUE);
				adapter.datasource = ideas;
				setAdapter(R.id.listViewIdeas, adapter);
			}
		});
        
        // Perform a refresh action when this activity is created.
        doRefresh();
    }
    protected void doRefresh()
    {
    	button(R.id.buttonRefresh).performClick();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	super.onActivityResult(requestCode, resultCode, data);
    }
}
