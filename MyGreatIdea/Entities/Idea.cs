using System;
using System.Collections.Generic;
using System.Text;

namespace MyGreatIdea.Entities
{
    public class Idea
    {
        public int Id { get; set; }
        public String Title { get; set; }
        public String Content { get; set; }
        public int PostTime { get; set; }
        public String UserName { get; set; }
        public String UserContact { get; set; }
        public int CommentCount { get; set; }
    }
}
