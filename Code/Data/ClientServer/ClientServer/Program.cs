using System;
using System.Net.Sockets;
using System.Threading;

namespace ClientServer
{
    class Program
    {
        static void Main(string[] args)
        {
            bool bRunning = true;

            Console.CancelKeyPress += (sender, e) =>
            {
                Console.WriteLine("CTRL+C detected!\n");
                bRunning = false;
            };

            Client client = new Client();
            

            while (true)
            {
                try
                {
                    while (bRunning)
                    {
                        string input = Console.ReadLine();
                        if (input == "exit")
                            break;

                        client.SendMsg(input);
                    }
                    Console.WriteLine("Disconnecting from server...");
                }
                catch (SocketException)
                {
                    Console.WriteLine("Unable to connect to server");
                    Thread.Sleep(5000);
                }
            }
        }
    }
    
}
