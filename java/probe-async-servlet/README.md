# Servlet API 3.0 and Async Servlets

* async execution
  - @WebServlet(..., asyncSupport = true)
  - web.xml: 
    ```
    <async-support>true</async-support>
    ```

* methods
  - AsyncContext ServletRequest::StartAsync()
  - AsyncContext::setAsyncTimeout(long ms)
  

## Links

* https://www.slideshare.net/SimoneBordet/servlet-31-async-io

    