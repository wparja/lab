using Newtonsoft.Json;
using System.Collections.Generic;


namespace WindowsFormsApp1
{
    public class RemoteCommand
    {
        public Item Header { get; set; }

        public List<Item> Items { get; set; } = new List<Item>();

        [JsonIgnore]
        public bool IsSingleItem { get { return Items.Count == 0; } }
    }
}
