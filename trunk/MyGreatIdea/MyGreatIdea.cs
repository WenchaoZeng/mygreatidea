using System;
using System.Collections.Generic;
using System.Text;
using MyGreatIdea.Entities;
using fastJSON;
using MyGreatIdea;
using System.Web;
using System.Reflection;
using System.IO;
using System.Data.OleDb;

namespace name.zwc.Caller.Handlers
{
    public class MyGreatIdea
    {
        static DBHelper db = null;
        static MyGreatIdea()
        {
            // 
            // Initialize database connection.
            //
            String dbFilePath = String.Empty;
            if (HttpContext.Current != null) // The code is running in a web server.
            {
                dbFilePath = HttpContext.Current.Server.MapPath("~/App_Data/MyGreatIdea.mdb");
            }
            else // The code is running locally for test purpose.
            {
                dbFilePath = Assembly.GetExecutingAssembly().Location;
                dbFilePath = Path.GetDirectoryName(dbFilePath);
                dbFilePath += "\\App_Data\\MyGreatIdea.mdb";
            }
            db = new DBHelper(dbFilePath);

            //
            // Initialize JSON parser.
            //
            JSON.Instance.UseSerializerExtension = false;
        }

        public static String AddIdea(String input, Dictionary<String, String> args)
        {
            Idea idea = JSON.Instance.ToObject<Idea>(input);
            idea.PostTime = getCurrentMinutes();

            db.Execute("insert into ideas(title, content, posttime, username, usercontact) values(?, ?, ?, ?, ?)", 
                idea.Title, 
                idea.Content, 
                idea.PostTime, 
                idea.UserName, 
                idea.UserContact);
            return "true";
        }
        public static String AddComment(String input, Dictionary<String, String> args)
        {
            Comment comment = JSON.Instance.ToObject<Comment>(input);
            comment.PostTime = getCurrentMinutes();

            db.Execute("insert into comments(ideaid, content, posttime, username, usercontact) values(?, ?, ?, ?, ?)",
                comment.IdeaId,
                comment.Content,
                comment.PostTime,
                comment.UserName,
                comment.UserContact);

            db.Execute("update ideas set commentcount = commentcount + 1 where id = ?", comment.IdeaId);

            return "true";
        }
        public static String GetIdeas(String input, Dictionary<String, String> args)
        {
            int lastId = 0;
            int.TryParse(args["lastid"], out lastId);

            OleDbDataReader reader = db.Execute("select top 20 id, title, content, posttime, username, commentcount from ideas where id < ? order by id desc", lastId);
            List<Idea> ideas = parseIdeas(reader);
            return JSON.Instance.ToJSON(ideas);
        }
        public static String GetMyIdeas(String input, Dictionary<String, String> args)
        {
            String userName = args["username"];

            OleDbDataReader reader = db.Execute("select id, title, content, posttime, username, commentcount from ideas where username = ? order by id desc", userName);
            List<Idea> ideas = parseIdeas(reader);
            return JSON.Instance.ToJSON(ideas);
        }
        public static String GetComments(String input, Dictionary<String, String> args)
        {
            int ideaId = 0;
            int.TryParse(args["ideaid"], out ideaId);

            OleDbDataReader reader = db.Execute("select id, content, posttime, username from comments where ideaid = ?", ideaId);
            List<Comment> comments = parseComments(reader);
            return JSON.Instance.ToJSON(comments);
        }

        #region Helpers

        //
        // All datetime values stored in database are represented in minutes starting from 1970/01/01.
        //
        static DateTime minDateTime = DateTime.Parse("1970/01/01");
        static int getCurrentMinutes()
        {
            TimeSpan timeSpan = DateTime.Now - minDateTime;
            return (int)timeSpan.TotalMinutes;
        }

        static List<Idea> parseIdeas(OleDbDataReader reader)
        {
            List<Idea> ideas = new List<Idea>();
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    ideas.Add(new Idea()
                    {
                        Id = (int)reader["id"],
                        Title = (String)reader["title"],
                        Content = (String)reader["content"],
                        PostTime = (int)reader["posttime"],
                        UserName = (String)reader["username"],
                        CommentCount = (int)reader["commentcount"]
                    });
                }
            }
            return ideas;
        }
        static List<Comment> parseComments(OleDbDataReader reader)
        {
            List<Comment> comments = new List<Comment>();
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    comments.Add(new Comment()
                    {
                        Id = (int)reader["id"],
                        Content = (String)reader["content"],
                        PostTime = (int)reader["posttime"],
                        UserName = (String)reader["username"]
                    });
                }
            }
            return comments;
        }
        #endregion
    }
}
