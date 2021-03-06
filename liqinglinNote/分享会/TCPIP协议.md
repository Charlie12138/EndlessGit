# TCP/IP协议

![](https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/3985563-e533b0cdd7fca359.png)

1.链路层：数据链路层或网络接口层（网络接口层和硬件层），通常包括操作系统中的设备驱动程序和计算机中对应的网络接口卡。处理与电缆（或其他任何传输媒介）的物理接口细节。转换IP层和网络接口层使用的地址。 



2.网络层：处理分组在网络中的活动，例如分组的选路。

IP是一种网络层协议，提供的是一种不可靠的服务，它只是尽可能快地把分组从源结点送到目的结点，但是并不提供任何可靠性保证。同时被TCP和UDP使用。TCP和UDP的每组数据都通过端系统和每个中间路由器中的IP层在互联网中进行传输。

ICMP是IP协议的附属协议。IP层用它来与其他主机或路由器交换错误报文和其他重要信息。

IGMP是Internet组管理协议。它用来把一个UDP数据报多播到多个主机。



3.传输层：TCP（传输控制协议）和UDP（用户数据报协议）。

TCP为两台主机提供高可靠性的数据通信。它所做的工作包括==把应用程序交给它的数据分成合适的小块交给下面的网络层==，确认接收到的分组，==设置发送最后确认分组的超时时钟==等。

UDP则为应用层提供一种非常简单的服务。它只是==把称作数据报的分组从一台主机发送到另一台主机==，但并不保证该数据报能到达另一端。一个数据报是指从发送方传输到接收方的一个信息单元（例如，发送方指定的一定字节数的信息）。



4. 应用层：决定了向用户提供应用服务时通信的活动。TCP/IP 协议族内预存了各类通用的应用服务。包括 HTTP，FTP（File Transfer Protocol，文件传输协议），DNS（Domain Name System，域名系统）服务。

![](https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/3985563-1891c256487e9d85.png)

当应用程序用TCP传送数据时，数据被送入协议栈中，然后逐个通过每一层直到被当作一串比特流送入网络。其中每一层对收到的数据都要增加一些首部信息（有时还要增加尾部信息），该过程如图所示。 

![](https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/3985563-5d534a249b4825a9.png)

当目的主机收到一个以太网数据帧时，数据就开始从协议栈中由底向上升，同时去掉各层协议加上的报文首部。每层协议盒都要去检查报文首部中的协议标识，以确定接收数据的上层协议。这个过程称作分用（Demultiplexing）。协议是通过目的端口号、源IP地址和源端口号进行解包的。