// SimpleExample.cpp : This file contains the 'main' function. Program execution begins and ends there.
//



#define _WINSOCK_DEPRECATED_NO_WARNINGS

#ifdef  _WIN32
#define _WIN32_WINNT _WIN32_WINNT_WINTHRESHOLD
#endif //  _WIN


#define ASIO_STANDALONE

#include <chrono>
#include <iostream>
#include <asio.hpp>
#include <asio/ts/buffer.hpp>
#include <asio/ts/internet.hpp>



int main()
{

    asio::error_code ec;

    asio::io_context context;

    //asio::ip::tcp::endpoint endpoint(asio::ip::make_address("93.184.216.34", ec), 80);
    asio::ip::tcp::endpoint endpoint(asio::ip::make_address("51.38.81.49", ec), 80);

    asio::ip::tcp::socket socket(context);

    socket.connect(endpoint, ec);

    if (!ec)
    {
        std::cout << "Connected!" << std::endl;
    }
    else
    {
        std::cout << "Failed to connect to address: \n" << ec.message() << std::endl;
    }

    if (socket.is_open())
    {
        std::string sRequest =
            "GET /index.html HTTP/1.1\r\n"
            "Host: example.com\r\n"
            "Connection: close\r\n\r\n";

        socket.write_some(asio::buffer(sRequest.data(), sRequest.size()), ec);

        socket.wait(socket.wait_read);

        size_t bytes = socket.available();
        std::cout << "Bytes Available: " << bytes << std::endl;

        if (bytes > 0)
        {
            std::vector<char> vBuffer(bytes);
            socket.read_some(asio::buffer(vBuffer.data(), vBuffer.size()), ec);


            for (auto c : vBuffer)
                std::cout << c;
        }
    }

    return 0;
}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
