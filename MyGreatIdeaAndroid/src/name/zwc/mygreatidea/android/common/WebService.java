package name.zwc.mygreatidea.android.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

import name.zwc.mygreatidea.android.entities.Comment;
import name.zwc.mygreatidea.android.entities.Idea;

public class WebService
{
	protected String gateway = "http://zwc.name/caller/call.ashx?h=MyGreatIdea";
	protected String call(String method, String post, String get)
	{
		try
		{
			// The GET parameters.
			String targetUrl = gateway + "&m=" + method;
			if (get != null)
			{
				targetUrl += "&" + get;
			}

		    // The POST content.
		    URL url = new URL(targetUrl);
		    URLConnection conn = url.openConnection();
		    if (post != null)
		    {
		    	conn.setRequestProperty("Content-Type", "application/jsonrequest");
			    conn.setDoOutput(true);
			    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			    wr.write(post);
			    wr.flush();
			    wr.close();
		    }

		    // Read response.
		    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    StringBuilder builder = new StringBuilder();
		    String line;
		    while ((line = reader.readLine()) != null)
		    {
		    	builder.append(line + "\r\n");
		    }
		    reader.close();
		    
		    return builder.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	protected boolean submit(String method, Object obj)
	{
		String post = new Gson().toJson(obj);
		String response = call(method, post, null);
		return response.toLowerCase().trim().equals("true");
	}
	
	public boolean submitIdea(Idea idea)
	{
	    return submit("AddIdea", idea);
	}
	
	public Idea[] getIdeas(int lastID)
	{
		String response = call("GetIdeas", null, "lastid=" + String.valueOf(lastID));
		Idea[] ideas = new Gson().fromJson(response, Idea[].class);
		return ideas;
	}
	
	public Comment[] getComments(int ideaID)
	{
		String response = call("GetComments", null, "ideaid=" + String.valueOf(ideaID));
		Comment[] comments = new Gson().fromJson(response, Comment[].class);
		return comments;
	}
	
	public boolean submitComment(Comment comment)
	{
		return submit("AddComment", comment);
	}
}