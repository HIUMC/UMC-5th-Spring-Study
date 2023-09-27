- 서버란?
  - OS 에 의해 동작하는 프로세스

# IP 주소와 포트번호

- IP 주소 : 유일하게 식별이 가능한 수단 (고유)
 

- IPC (Inter-Process Communication) : 컴퓨터의 프로세스 간 통신
 

- 포트 번호 : 프로세스 식별 값

<!---------------------------------------------------->
<!---------------------------------------------------->

# 데이터 송신 과정

### 데이터를 송신할 때

1. write 시스템 콜을 통해 소켓에 데이터 전송
2. TCP/UDP, IP, Ethernet 을 거쳐 흐름제어, 라우팅 작업
3. NIC(랜 카드)를 통해 외부로 데이터 전홍

### 데이터를 수신할 때

1. NIC 에서 데이터 수신
2. 인터럽트를 통해 Driver 로 옮겨진 데이터는 이동하며 소켓에 담김
3. 수신 대상인 프로세스에 데이터가 도달

<!---------------------------------------------------->
<!---------------------------------------------------->

# 소켓

### TCP 전용 소켓 (stream 소켓)

- 신뢰성 있는 데이터 송수신
- ⭐ accept() 시스템 콜

### UDP 소켓 (datagram 소켓)

- 비연결지향

### 1. socket() 시스템 콜

- 소켓을 만드는 시스템 콜
- socket(domain, type, protocol)
  - domain: IPv4 or IPv6
  - type : stream or datagram
  - protocol : 0(시스템이 선택), 6(tcp), 17(udp)
- 리턴 값 : 파일 디스크립터
  - 파일 디스크립터가 소켓의 파일 디스크립터인 경우, 소켓 데이터 송신 or 수신을 하게 되는 것

```C
int socket_descriptor;
socket_descriptor = socket(AF_INET, SOCK_STREAM, 0);
```

### 2. bind() 시스템 콜

- 생성한 소켓에 실제 IP 주소와 포트번호를 부여하는 시스템 콜
- bind(sockfd, sockaddr, socklen_t)
  - sockfd : 소켓 파일 디스크립터
- 서버에서만 사용

```C
#include <sys/socket.h>
#include <netinet/in.h>

int main() {
    int sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd == -1) {
        perror("Socket creation failed");
        return 1;
    }

    struct sockaddr_in server_address;
    server_address.sin_family = AF_INET;         // IPv4 주소 체계
    server_address.sin_addr.s_addr = INADDR_ANY; // 모든 가능한 IP 주소
    server_address.sin_port = htons(80);       // 포트 번호 80

    if (bind(sockfd, (struct sockaddr *)&server_address, sizeof(server_address)) == -1) {
        perror("Bind failed");
        return 1;
    }

    // 바인딩 성공 처리 및 작업 수행

    return 0;
}
```

### ⭐ 3. listen() 시스템 콜 only for TCP

- 파라미터로 받은 backlog 크기만큼 TCP backlog queue 를 만드는 시스템 콜
- listen(sockfd, backlog)
- TCP 에서만 사용

```C
#include <sys/socket.h>

int main() {
    int sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd == -1) {
        perror("Socket creation failed");
        return 1;
    }

    // ... 서버 소켓의 주소와 바인딩 설정 ...

    int backlog = 10; // 최대 대기열 크기
    if (listen(sockfd, backlog) == -1) {
        perror("Listen failed");
        return 1;
    }

    // 리스닝 성공 처리 및 연결 요청 처리

    return 0;
}
```

### ⭐⭐ 4. accept() 시스템 콜

- backlog queue 에서 syn 을 보내와 대기 중인 요청을 선입선출로 연결을 수립해주는 시스템 콜
- accept(sockfd, sockaddr, socklen_t)
- 리턴 값 : 새로운 소켓의 파일 디스크립터

```C
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>

int main() {
    int server_socket = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in server_address;
    server_address.sin_family = AF_INET;
    server_address.sin_addr.s_addr = INADDR_ANY;
    server_address.sin_port = htons(80);

    bind(server_socket, (struct sockaddr *)&server_address, sizeof(server_address));

    listen(server_socket, 5);

    printf("Server: Waiting for client's connection...\n");

    struct sockaddr_in client_address;
    socklen_t client_addrlen = sizeof(client_address);

    int client_socket = accept(server_socket, (struct sockaddr *)&client_address, &client_addrlen);

    printf("Server: Accepted connection from %s:%d\n",
           inet_ntoa(client_address.sin_addr), ntohs(client_address.sin_port));

    // 3-way handshake의 나머지 두 단계 수행
    char buffer[1024];
    ssize_t bytes_received = recv(client_socket, buffer, sizeof(buffer), 0); // 클라이언트의 ACK 받기
    if (bytes_received > 0) {
        printf("Server: Received ACK from client.\n");
    }
```

##### 4.1 TCP 3-way handshake

- 클라이언트와 서버 간 신뢰성 있는 통신 준비 과정

##### 4.2 accept 시스템 콜 이후 멀티 프로세스/멀티 쓰레드

    ✔️ accept 시스템 콜의 응답을 받았다
     = SYN 요청을 보낸 클라이언트가 적어도 하나 backlog queue 에 있었다
     = 해당 클라이언트 요청의 응답을 위해 새로운 소켓을 만들었다

```C
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

int main() {
    int server_socket = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in server_address;
    server_address.sin_family = AF_INET;
    server_address.sin_addr.s_addr = INADDR_ANY;
    server_address.sin_port = htons(80); // 웹 서버 포트인 80

    bind(server_socket, (struct sockaddr *)&server_address, sizeof(server_address));

    listen(server_socket, 5);

    printf("Server: Listening on port 80...\n");

    while (1) {
        struct sockaddr_in client_address;
        socklen_t client_addrlen = sizeof(client_address);

        int client_socket = accept(server_socket, (struct sockaddr *)&client_address, &client_addrlen);

        if (fork() == 0) { // 자식 프로세스 <- 이 부분에 집중!

            printf("Server: Accepted connection from %s:%d\n",
                   inet_ntoa(client_address.sin_addr), ntohs(client_address.sin_port));

            // 3-way handshake의 나머지 두 단계 수행
            // 여기서는 ACK를 보내는 과정만 간단히 보여줍니다.
            sleep(1); // 실제로는 필요한 로직 수행

            // 서버의 응답 전송
            char response[] = "HTTP/1.1 200 OK\r\nContent-Length: 13\r\n\r\nHello, world!";
            send(client_socket, response, strlen(response), 0);
            printf("Server: Sent response to client.\n");

            close(client_socket);
            exit(0);
        }

        close(client_socket);
    }

    close(server_socket);

    return 0;
}
```

- fork() : 자식 프로세스 생성
  - 리턴 값이 0 : 자식 프로세스
  - 0 이 아님 : 부모 프로세스 (본인)
 

- 부모 프로세스 : 연결 요청 받아주기
```C
#include <stdio.h#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

int main() {
    int server_socket = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in server_address;
    server_address.sin_family = AF_INET;
    server_address.sin_addr.s_addr = INADDR_ANY;
    server_address.sin_port = htons(80); // 웹 서버 포트인 80

    bind(server_socket, (struct sockaddr *)&server_address, sizeof(server_address));

    listen(server_socket, 5);

    printf("Server: Listening on port 80...\n");

    while (1) {
        struct sockaddr_in client_address;
        socklen_t client_addrlen = sizeof(client_address);

        int client_socket = accept(server_socket, (struct sockaddr *)&client_address, &client_addrlen);

        if (fork() == 0 -> false ) { 
           실행안함
        }

      
    }

    close(server_socket);

    return 0;
	}
```
 

- 자식 프로세스 : 부모 프로세스의 소켓을 이어받아 잔여 3-way handshake 수행 후 데이터 통신
```C
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

int main() {
    int server_socket = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in server_address;
    server_address.sin_family = AF_INET;
    server_address.sin_addr.s_addr = INADDR_ANY;
    server_address.sin_port = htons(80); // 웹 서버 포트인 80

    bind(server_socket, (struct sockaddr *)&server_address, sizeof(server_address));

    listen(server_socket, 5);

    printf("Server: Listening on port 80...\n");

    while (1) {
        struct sockaddr_in client_address;
        socklen_t client_addrlen = sizeof(client_address);

        int client_socket = accept(server_socket, (struct sockaddr *)&client_address, &client_addrlen);

        if (fork() == 0 -> true) { // 자식 프로세스
            close(server_socket);

            printf("Server: Accepted connection from %s:%d\n",
                   inet_ntoa(client_address.sin_addr), ntohs(client_address.sin_port));

            // 3-way handshake의 나머지 두 단계 수행
            // 여기서는 ACK를 보내는 과정만 간단히 보여줍니다.
            sleep(1); // 실제로는 필요한 로직 수행

            // 서버의 응답 전송
            char response[] = "HTTP/1.1 200 OK\r\nContent-Length: 13\r\n\r\nHello, world!";
            send(client_socket, response, strlen(response), 0);
            printf("Server: Sent response to client.\n");

            close(client_socket);
            exit(0); <-여기서 자식 프로세스가 종료됨
        }

        close(client_socket);
    }

    close(server_socket);

    return 0;
}
```
 

- 전체 코드 : HTTP 웹 서버 코드
```C
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

int main() {
    const char* server_ip = "127.0.0.1";
    int server_port = 8080;

    int server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket == -1) {
        perror("Socket creation failed");
        return 1;
    }

    struct sockaddr_in server_addr, client_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(server_port);
    server_addr.sin_addr.s_addr = inet_addr(server_ip);

    if (bind(server_socket, (struct sockaddr*)&server_addr, sizeof(server_addr)) == -1) {
        perror("Binding failed");
        return 1;
    }

    if (listen(server_socket, 5) == -1) {
        perror("Listening failed");
        return 1;
    }

    printf("Server listening on %s:%d\n", server_ip, server_port);

    while (1) {
        socklen_t client_addr_len = sizeof(client_addr);
        int client_socket = accept(server_socket, (struct sockaddr*)&client_addr, &client_addr_len);
        if (client_socket == -1) {
            perror("Accepting client failed");
            continue;
        }

        printf("Accepted connection from %s:%d\n", inet_ntoa(client_addr.sin_addr), ntohs(client_addr.sin_port));

        char request[1024];
        recv(client_socket, request, sizeof(request), 0);
        printf("Received request:\n%s\n", request);

        char response[] = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\nHello, World!";
        send(client_socket, response, sizeof(response), 0);

        close(client_socket);
    }

    close(server_socket);
    return 0;
}
```