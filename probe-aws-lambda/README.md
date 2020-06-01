# AWS Lambda usecases

```
class L1 implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {} // as Http Endpoint

class L2 implements RequestHandler<String, String> {}
class L3 implements RequestHandler<Void, Map<String, Object>> {}   // with empty InputParameter
class L4 implements RequestHandler<Request, Map<String, String>> {}
```

