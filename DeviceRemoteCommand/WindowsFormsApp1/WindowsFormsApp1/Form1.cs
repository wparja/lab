using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        List<RemoteCommandGroup> remoteCommandGroups = new List<RemoteCommandGroup>();
        public Form1()
        {
            InitializeComponent();
            ContextMenuStrip = contextMenuStrip1;
            LoadConfigFile();
            FillContextMenu(remoteCommandGroups);
        }

        private void LoadConfigFile()
        {
            string path = Path.Combine(Environment.CurrentDirectory, "DeviceRemoteCommandConfig.json");
            if (!File.Exists(path))
            {
                LoadDefaultConfigFile();
            }
            else
            {
                FillRemoteCommandGroupsFromFile(File.OpenRead(path));
            }
        }

        private void LoadDefaultConfigFile()
        {
            var assembly = Assembly.GetExecutingAssembly();
            var resourceName = "WindowsFormsApp1.Config.DeviceRemoteCommandConfig.json";
            FillRemoteCommandGroupsFromFile(assembly.GetManifestResourceStream(resourceName));
            SaveConfigFile();

        }

        private void FillRemoteCommandGroupsFromFile(Stream streamFile)
        {
            using (Stream stream = streamFile)
            {
                using (StreamReader reader = new StreamReader(stream))
                {
                    string result = reader.ReadToEnd();
                    remoteCommandGroups = JsonConvert.DeserializeObject<List<RemoteCommandGroup>>(result);
                }
            }
        }

        private void SaveConfigFile()
        {
            string path = Path.Combine(Environment.CurrentDirectory, "DeviceRemoteCommandConfig.json");
            string content = JsonConvert.SerializeObject(remoteCommandGroups.ToArray());
            File.WriteAllText(path, content);
        }
    

        private void FillContextMenu(List<RemoteCommandGroup> remoteCommandGroups)
        {
            RemoteCommandGroup lastRemoteGroup = remoteCommandGroups.Last();
            foreach(var remoteCommandGroup in remoteCommandGroups)
            {
                ContextMenuStrip.Items.Add(CreateHeaderContextMenu(remoteCommandGroup));

                
                foreach (var remoteCommand in remoteCommandGroup.Items)
                {
                    if (remoteCommand.IsSingleItem)
                    {
                        contextMenuStrip1.Items.Add(CreateAndSubscribeMenuItem(remoteCommand.Header));
                    }
                    else
                    {
                        contextMenuStrip1.Items.Add(CreateContextItemsMenu(remoteCommand));

                    }
                }

                if (!remoteCommandGroup.Equals(lastRemoteGroup))
                {
                    ContextMenuStrip.Items.Add(CreateSeparator());
                }
            }
        }

        private ToolStripMenuItem CreateContextItemsMenu(RemoteCommand remoteCommand)
        {
            ToolStripMenuItem itemGroup = CreateContextItemMenu(remoteCommand.Header);
            foreach (var item in remoteCommand.Items)
            {
               itemGroup.DropDownItems.Add(CreateAndSubscribeMenuItem(item));
            }
            return itemGroup;
        }

        private ToolStripMenuItem CreateAndSubscribeMenuItem(Item item)
        {
            var itemMenu = CreateContextItemMenu(item);
            SubscribeClickItemMenu(itemMenu);
            return itemMenu;
        }

        private ToolStripSeparator CreateSeparator()
        {
            return new ToolStripSeparator();
        }

        private ToolStripMenuItem CreateHeaderContextMenu(RemoteCommandGroup header)
        {
            ToolStripMenuItem toolStripMenuItem = new ToolStripMenuItem
            {
                Text = header.ApplicationName,
                Enabled = false
            };
            return toolStripMenuItem;
        }

        private ToolStripMenuItem CreateContextItemMenu(Item item)
        {
            ToolStripMenuItem toolStripMenuItem = new ToolStripMenuItem
            {
                Text = item.DisplayName,
                Tag = item.Command
            };
            return toolStripMenuItem;
        }

        private ToolStripMenuItem SubscribeClickItemMenu(ToolStripMenuItem item)
        {
            item.Click += toolStripComboBox1_Click;
            return item;
        }

        private void toolStripComboBox1_Click(object sender, EventArgs e)
        {
            string command = (sender as ToolStripMenuItem).Tag as string;
            Console.WriteLine(command);
        }
    }
}
