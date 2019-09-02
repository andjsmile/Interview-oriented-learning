
from socket import  *
from time import ctime

# 向主机的服务器发送一段文字后，服务器戳上时间戳再发送回来
HOST='127.0.0.1'         # 'localhost'
PORT=21567
BUFFSIZE=1024
ADDR=(HOST,PORT)

tcpCliSock=socket(AF_INET,SOCK_STREAM)
tcpCliSock.connect(ADDR)

while True:
    data=input('>')
    if not data:
        break
    tcpCliSock.send(data)
    data=tcpCliSock.recv(BUFFSIZE)
    if not data:
        break
    print(data.decode('utf-8'))
tcpCliSock.close()