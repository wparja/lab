using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApp1
{
    public class RemoteCommandGroup
    {
        public string ApplicationName { get; set; }
        public string ApplicationKey { get; set; }        
        public List<RemoteCommand> Items { get; set; } = new List<RemoteCommand>();
    }
}
