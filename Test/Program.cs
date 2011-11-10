using System;
using System.Collections.Generic;
using System.Text;
using MyGreatIdea.Entities;
using fastJSON;

namespace Test
{
    class Program
    {
        static void Main(string[] args2)
        {
            JSON.Instance.UseSerializerExtension = false;

            Idea idea = new Idea() { Title = "标题", Content = "内容", UserName = "用户名字", UserContact = "联系方式" };
            Comment comment = new Comment() { IdeaId = 1, Content = "内容", UserName = "名字", UserContact = "联系方式" };

            String input = JSON.Instance.ToJSON(idea);
            Dictionary<String, String> args = new Dictionary<string,string>();
            args.Add("lastid", "3");
   
            String result = name.zwc.Caller.Handlers.MyGreatIdea.GetIdeas(input, args);

            Console.WriteLine(result);
            Console.ReadKey();
        }
    }
}
