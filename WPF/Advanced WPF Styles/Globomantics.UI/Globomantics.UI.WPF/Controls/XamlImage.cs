using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;

namespace Globomantics.UI.WPF.Controls
{
    public class XamlImage : Control 
    {
        static XamlImage()
        {
            DefaultStyleKeyProperty.OverrideMetadata(typeof(XamlImage), new FrameworkPropertyMetadata(typeof(XamlImage)));

            IsTabStopProperty.OverrideMetadata(typeof(XamlImage), new FrameworkPropertyMetadata(false));
        }
    }
}
