using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace Globomantics.UI.WPF
{
    public sealed class ThemeResourceDictionary : ResourceDictionary
    {
        public ThemeResourceDictionary()
        {
            MergedDictionaries.Add(Theme.ResourceDictionary);
        }
    }
}
