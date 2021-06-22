using System;
using System.Threading;
using System.Threading.Tasks;

namespace AsyncAwait
{
    class Program
    {
        static async Task Main(string[] args)
        {
            Console.WriteLine("H1" + Thread.CurrentThread.ManagedThreadId);
            try
            {
                await sleep();
            }
            catch (Exception)
            {

                Console.WriteLine("try");
            }
  
            Console.WriteLine("H4" + Thread.CurrentThread.ManagedThreadId);
            Console.ReadKey();
        }


        static async Task sleep()
        {
            throw new InvalidOperationException();
            Console.WriteLine("H2" + Thread.CurrentThread.ManagedThreadId);
            await Task.Delay(10000);
            Console.WriteLine("H3" + Thread.CurrentThread.ManagedThreadId);
        }
    }
}
