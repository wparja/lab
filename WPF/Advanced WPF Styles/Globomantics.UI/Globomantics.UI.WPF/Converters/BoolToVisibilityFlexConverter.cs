using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Data;
using System.Windows.Markup;

namespace Globomantics.UI.WPF.Converters
{
    public class BoolToVisibilityFlexConverter : MarkupExtension, IValueConverter
    {

        private static BoolToVisibilityFlexConverter converter;

        public bool IsInverse { get; set; }

        public BoolToVisibilityFlexConverter()
        {

        }

        public BoolToVisibilityFlexConverter(bool isInverse)
        {
            IsInverse = isInverse;
        }

        public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
        {
            var convertValue = false;

            if (value != null)
            {
                if (value is bool b)
                {
                    convertValue = b;
                }
            }

            return (convertValue ^ IsInverse) ? Visibility.Visible : Visibility.Collapsed;
        }

        public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            if (value is Visibility visibility)
            {
                return ((visibility == Visibility.Visible) ^ IsInverse);
            }

            return false;
        }

        public override object ProvideValue(IServiceProvider serviceProvider)
        {
           return converter ?? (new BoolToVisibilityFlexConverter());
        }
    }
}
