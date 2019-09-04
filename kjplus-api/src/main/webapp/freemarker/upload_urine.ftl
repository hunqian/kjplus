<#assign base=request.contextPath />
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>尿检图片上传</title>
 </head>
 <body>
  <form name="upload_form" id="upload_form" method="POST" enctype="multipart/form-data" action="${base}/uploadurineimgfile.html">
  	测量主表Code <input type="text" name="exmaincode" value =""></br>
  	<input type="file" name="imgfile">
  	<button type="sumbit">尿检图片上传</button>
  </form>
 </body>
</html>
