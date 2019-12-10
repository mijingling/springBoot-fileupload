**应用环境：基于springBoot微服务/spring框架服务**  
**技术选型：采用jQuery File Upload Plugin插件提交（GIT地址：https://github.com/blueimp/jQuery-File-Upload）**  
**1、jquery图片上传(非跨域)**  
**2、jquery图片上传(跨域，XMLHTTPRequest提交方式，推荐)**  

```
a.Cross-site XHR file uploads don't require any work on client side, but are only supported by browsers supporting XHR File Uploads  
b.For cross-browser compatibility, the header must be set as response to both the file upload (POST) request as well as response to OPTIONS requests  
中文备注：  
	a.client不需要配置；  
	b.使用jQuery做跨域请求，client端要向server端要发出预请求校验(Preflighted requests)请求类型为OPTIONS，服务器必须允许并响应该请求，然后jQuery才开始真正提交  
	c.server端允许预请求方案：  
		方案1：Controller上添加@CrossOrigin注解(要求spring框架版本4以上)   
		方案2：在拦截器/过滤器中遇到OPTIONS请求，添加响应头，即可通过预请求校验  
		//在options校验时，附加允许表头   
		if("OPTIONS".equals(request.getMethod())){  
			response.setHeader("Access-Control-Allow-Credentials", "true");  
			response.setHeader("Access-Control-Allow-Headers", "Content-Type");  
			response.setHeader("Access-Control-Allow-Methods", "POST");  
			response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));  
		}
```
**3、jquery图片上传(跨域，iframe提交方式，不推荐)**  

```
a.Cross-site iframe transport uploads don't require any additional server response headers.  
Unfortunately, it is not possible to access the response body of iframes on a different domain.  
b.The response should not exceed a certain length, as the redirect URL is limited by themaximum URL length that browsers will process.  
中文备注：  
	a.无需跨域配置、无需预请求校验  
	b.client端需要添加reult.html文件、server端Response中要设置redirect跳转  
	c.传参是放在路径上的，可能会出现过长无法传值的情况
```