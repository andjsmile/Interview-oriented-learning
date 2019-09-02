#!/usr/bin/env python
from socket import *
from time import ctime

HOST=''
PROT=21567
BUFFSIZE=1024
ADDR=(HOST,BUFFSIZE)

tcpSerSock=socket(AF_INET,SOCK_STREAM)
tcpSerSock.bind(ADDR)                     # 绑定端口
tcpSerSock.listen(5)

while True:
    print('waiting for connection...')
    tcpCliSock,addr=tcpSerSock.accept()   # 等待客户端连接
    print('...connection from:',addr)

    while True:
        data=tcpCliSock.recv(BUFFSIZE)    # 
        if not data:
            break
        tcpCliSock.send('[%s]%s'%(bytes(cctime(),'utf-8'),data))
    tcpCliSock.close()
tcpSerSock.close()




