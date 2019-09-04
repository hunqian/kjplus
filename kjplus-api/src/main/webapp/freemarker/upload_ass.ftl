<#assign base=request.contextPath />
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>Document</title>
 </head>
 <body>
  <form name="upload_form" id="upload_form" method="POST" enctype="multipart/form-data" action="${base}/uploadAssignfile.html">
  <input type="file" name="file"></br>
	签约code <input type="text" name="assCode" value =""></br>
	文件类型 <input type="text" name="fileType" value ="image">
  <button type="sumbit">上传</button>
  </form>
 </body>
</html>
