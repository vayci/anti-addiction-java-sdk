# 网络游戏防沉迷系统服务端Java SDK

## POM依赖

```xml
<dependency>
  <groupId>me.olook</groupId>
  <artifactId>anti-addiction-java-sdk</artifactId>
  <version>0.1.0</version>
</dependency>
```

## 使用示例

```java
// 初始化client参数
ClientContext clientContext = ClientContext.builder
                .aClientContext()
                .appId("")
                .bizId("")
                .secretKey("")
                .build();

// 初始化client请求配置
ClientConfig clientConfig = ClientConfig.builder
        .aClientConfig()
        .ignoreSSL(true)
        .connectTimeout(4000)
        .readTimeout(4000)
        .maxRetry(1)
        .build();

// 创建client对象
AntiAddictionClient antiAddictionClient = new AntiAddictionClient(clientContext,clientConfig);

// 实名认证
AuthenticationCheckRequest request = new AuthenticationCheckRequest();
request.setAi("200000000000002001");
request.setIdNum("110000190201010009");
request.setName("某二一");
Response response = antiAddictionClient.authenticationCheck(request);

```