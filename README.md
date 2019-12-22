# SB-RESTAPI-CLIENT
<p>Project for Spring Boot REST API and REST Client</p>
<table>
  <tbody>
    <tr>
      <th>Acronym</th>
      <th>Description</th>
    </tr>
    <tr>
      <td>SB</td>
      <td>Spring Boot</td>
    </tr>
    <tr>
      <td>RESTAPI</td>
      <td>Rest API</td>
    </tr>
    <tr>
      <td>RESTCLIENT</td>
      <td>REST Client</td>
    </tr>
  </tbody>
</table>

<h4>JSON REST service</h4>

<p>Any Spring @RestController in a Spring Boot application will render JSON response by default as long as Jackson2 [jackson-databind] is on the classpath. In a web app [spring-boot-starter-web], it transitively gets included, no need to explicitly include it.</p>

<h4>XML REST service</h4>

<p>For enabling XML representations, Jackson XML extension (jackson-dataformat-xml) must be present on the classpath. Add the following dependency to your project:</p>

<pre class="brush: xml; title: ; notranslate" title="">
&lt;dependency&gt;
    &lt;groupId&gt;com.fasterxml.jackson.dataformat&lt;/groupId&gt;
    &lt;artifactId&gt;jackson-dataformat-xml&lt;/artifactId&gt;
&lt;/dependency&gt;
</pre>

<p>Note : In order to get XML response instead of JSON, client is expected to send appropriate &#8216;Accept&#8217; header with value &#8216;text/xml&#8217; or &#8216;application/xml&#8217;.</p>

<h3> Introduction to REST</h3>

<p>REST stands for Representational State Transfer. It&#8217;s an is an architectural style which can be used to design web services, that can be consumed from a variety of clients. The core idea is that, rather than using complex mechanisms such as CORBA, RPC or SOAP to connect between machines, simple HTTP is used to make calls among them.</p>

<p>In Rest based design, resources are being manipulated using a common set of verbs.</p>

<ul>
<li>To Create a resource : HTTP POST should be used</li>
<li>To Retrieve a resource : HTTP GET should be used</li>
<li>To Update a resource : HTTP PUT should be used</li>
<li>To Delete a resource : HTTP DELETE should be used</li>
</ul>

<p>That means, as a REST service developer or Client, should comply to above criteria, in order to be REST complained.</p>

<p>Often Rest based Web services return JSON or XML as response, although it is not limited to these types only. Clients can specify (using HTTP <strong>Accept header</strong>) the resource type they are interested in, and server may return the resource , specifying <strong>Content-Type</strong> of the resource it is serving.</p>
<hr/>

<h4>REST Controller</h4>

<p>Following is one possible Rest based controller, implementing REST API. I said possible, means Other&#8217;s may implement it in another way, still (or even more pure way) conforming to REST style.</p>

<p><strong>This is what our REST API does:</strong></p>

<ul>
<li><strong>GET</strong> request to /api/user/ returns a list of users</li>
<li><strong>GET</strong> request to /api/user/1 returns the user with ID 1</li>
<li><strong>POST</strong> request to /api/user/ with a user object as JSON creates a new user</li>
<li><strong>PUT</strong> request to /api/user/3 with a user object as JSON updates the user with ID 3</li>
<li><strong>DELETE</strong> request to /api/user/4 deletes the user with ID 4</li>
<li><strong>DELETE</strong> request to /api/user/ deletes all the users</li>
</ul>

<p><strong>Detailed Explanation :</strong></p>

<p><strong>@RestController</strong> : First of all, we are using Spring 4&#8217;s new @RestController annotation. This annotation eliminates the need of annotating each method with @ResponseBody. Under the hood, @RestController is itself annotated with @ResponseBody, and can be considered as combination of @Controller and @ResponseBody.</p>

<p><strong>@RequestBody</strong> : If a method parameter is annotated with @RequestBody, Spring will bind the incoming HTTP request body(for the URL mentioned in @RequestMapping for that method) to that parameter. While doing that, Spring will [behind the scenes] use <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#rest-message-conversion">HTTP Message converters</a> to convert the HTTP request body into domain object [deserialize request body to domain object], based on ACCEPT or Content-Type header present in request.</p>

<p><strong>@ResponseBody</strong> : If a method is annotated with @ResponseBody, Spring will bind the return value to outgoing HTTP response body. While doing that, Spring will [behind the scenes] use <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#rest-message-conversion">HTTP Message converters</a> to convert the return value to HTTP response body [serialize the object to response body], based on Content-Type present in request HTTP header. As already mentioned, in Spring 4, you may stop using this annotation.</p>

<p><strong>ResponseEntity</strong>  is a real deal. It represents the entire HTTP response. Good thing about it is that you can control anything that goes into it. You can specify status code, headers, and body. It comes with several constructors to carry the information you want to sent in HTTP Response.</p>

<p><strong>@PathVariable</strong> This annotation indicates that a method parameter should be bound to a URI template variable [the one in &#8216;{}&#8217;].</p>

<p>Basically, @RestController , @RequestBody, ResponseEntity &#038; @PathVariable are all you need to know to implement a REST API in Spring. Additionally, spring provides several support classes to help you implement something customized.</p>

<p><strong>MediaType :</strong> Although we didn&#8217;t, with @RequestMapping annotation, you can additionally, specify the MediaType to be produced or consumed (using <strong>produces</strong> or <strong>consumes</strong> attributes) by that particular controller method, to further narrow down the mapping.</p>

<h4>Testing the API</h4>

<p>Let&#8217;s run the application[from IDE or on command line]. To test this API, i will use an external client POSTMAN (An extension from CHROME). We will write our own client in just few minutes.</p>

<div class="remarks">
Apart from IDE, you can also run this app using following approaches.</p>
<ul>
<li><strong>java -jar path-to-jar</strong></li>
<li>on Project root , <strong>mvn spring-boot:run</strong></li>
</ul>
</div>

<p>1. Retrieve all users</p>
<p>Open POSTMAN tool, select request type [GET for this use case], specify the uri <strong>http://localhost:8095/SpringBootRestApi/api/user/</strong> and Send. Should retrieve all users.</p>

<p>Now, let&#8217;s retry the GET, with an Accept header this time with value &#8216;application/xml&#8217;. You should get XML response, thanks to <code>jackson-dataformat-xml</code> available in classpath, server was able to return an XML response based on &#8216;Accept header&#8217;.</p>

<p>2. Retrieve a single user<br />
Use GET, specify the id of the user you are looking for and send.</p>

<p>3. Retrieve an unknown user<br />
Use GET, specify the wrong id of the user, and send.</p>

<p>4. Create a User<br />
Use POST, specify the content in body, select content-type as &#8216;application/json&#8217;<br />

<p>Send. New user would be created and will be accessible at the location mentioned in <strong>Location</strong> header.</p>

<p>5. Create a User with an existing user-name<br />
Use POST, specify the content in body with name of an existing user,Send, should get a 409/conflict. </p>

<p>6. Update an existing user<br />
Use PUT, specify the content in body and type as &#8216;application/json&#8217;.</p>

<p>Send, the user should be updated at server.</p>

<p>7. Delete an existing user<br />
Use DELETE, specify the id in url, send. User should be deleted from server.</p>

<p>8. Delete all users<br />
Use DELETE, do not specify any id, send. All users should be deleted from server.</p>

<p>9. Verify the results<br />
Use GET to retrieve all users, send, should not get any user in response.</p>

<h4>4. Writing REST Client using RestTemplate</h4>

<p>Postman tool used above is a wonderful Client to test Rest API. But if you want to consume REST based web services from your application, you would need a REST client for your application. We prefer to use RestTemplate.</p>

<p>Below shown are HTTP methods and corresponding RestTemplate methods to handle that type of HTTP request.</p>

<p><strong>HTTP Methods and corresponding RestTemplate methods:</strong></p>

<ul style="color: #333333;">
<li style="font-weight: inherit; font-style: inherit;">HTTP GET       : getForObject, getForEntity</li>
<li style="font-weight: inherit; font-style: inherit;">HTTP PUT       : put(String url, Object request, String…?urlVariables)</li>
<li style="font-weight: inherit; font-style: inherit;">HTTP DELETE    : delete</li>
<li style="font-weight: inherit; font-style: inherit;">HTTP POST      : postForLocation(String url, Object request, String…? urlVariables), postForObject(String url, Object request, Class<T> responseType, String…? uriVariables)</li>
<li style="font-weight: inherit; font-style: inherit;">HTTP HEAD      : headForHeaders(String url, String…? urlVariables)</li>
<li style="font-weight: inherit; font-style: inherit;">HTTP OPTIONS   : optionsForAllow(String url, String…? urlVariables)</li>
<li style="font-weight: inherit; font-style: inherit;">HTTP PATCH and others  : exchange execute</li>
</ul>

<p><strong>Custom Rest client , consuming the REST services created earlier.</strong></p>
