package name.zwc.mygreatidea.android;

import name.zwc.mygreatidea.android.entities.Idea;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends ActivityBase
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // 发布想法按钮
        button(R.id.buttonSubmitIdea).setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				startActivity("SubmitIdeaActivity");
			}
		});
        
        // 设置按钮
        button(R.id.buttonSetting).setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				startActivity("SettingActivity");
			}
		});
        
        // 刷新按钮
        button(R.id.buttonRefresh).setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				new Thread(new Runnable()
				{
					public void run()
					{
						IdeaListAdapter adapter = new IdeaListAdapter(context);
						adapter.ideas = service.getIdeas(Integer.MAX_VALUE);
						ListView listViewIdeas = (ListView)findViewById(R.id.listViewIdeas);
						listViewIdeas.setAdapter(adapter);
					}
				}).start();
			}
		});
    }
}
