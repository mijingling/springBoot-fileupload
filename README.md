**应用环境：基于springBoot微服务/spring框架服务**  
**技术选型：采用jQuery File Upload Plugin插件提交（GIT地址：https://github.com/blueimp/jQuery-File-Upload）**  
**1、jquery图片上传(非跨域)**  
**2、jquery图片上传(跨域，XMLHTTPRequest提交方式，推荐)**  
&emsp;&emsp;a.Cross-site XHR file uploads don't require any work on client side, but are only supported by browsers supporting XHR File Uploads  
&emsp;&emsp;b.For cross-browser compatibility, the header must be set as response to both the file upload (POST) request as well as response to OPTIONS requests  
&emsp;&emsp;中文备注：  
&emsp;&emsp;a.client不需要配置；  
&emsp;&emsp;b.使用jQuery做跨域请求，client端要向server端要发出预请求校验(Preflighted requests)请求类型为OPTIONS，服务器必须允许并响应该请求，然后jQuery才开始真正提交  
&emsp;&emsp;c.server端允许预请求方案：  
&emsp;&emsp;方案1：Controller上添加@CrossOrigin注解(要求spring框架版本4以上)   
&emsp;&emsp;方案2：在拦截器/过滤器中遇到OPTIONS请求，添加响应头，即可通过预请求校验  
&emsp;&emsp;//在options校验时，附加允许表头   
&emsp;&emsp;if("OPTIONS".equals(request.getMethod())){  
&emsp;&emsp;&emsp;response.setHeader("Access-Control-Allow-Credentials", "true");  
&emsp;&emsp;&emsp;response.setHeader("Access-Control-Allow-Headers", "Content-Type");  
&emsp;&emsp;&emsp;response.setHeader("Access-Control-Allow-Methods", "POST");  
&emsp;&emsp;&emsp;response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));  
&emsp;&emsp;}  
**3、jquery图片上传(跨域，iframe提交方式，不推荐)**  
&emsp;&emsp;a.Cross-site iframe transport uploads don't require any additional server response headers.  
&emsp;&emsp;Unfortunately, it is not possible to access the response body of iframes on a different domain.  
&emsp;&emsp;b.The response should not exceed a certain length, as the redirect URL is limited by themaximum URL length that browsers will process.  
&emsp;&emsp;中文备注：  
&emsp;&emsp;a.无需跨域配置、无需预请求校验  
&emsp;&emsp;b.client端需要添加reult.html文件、server端Response中要设置redirect跳转  
&emsp;&emsp;c.传参是放在路径上的，可能会出现过长无法传值的情况