<!DOCTYPE html>
<html>
<head>
<title>Site</title>
<style>
.main-form{
  border: thin solid black; 
  height: 400px; 
  width: 450px; 
  margin: 0 auto;
}
.input-type-text{
  height: 10px;
  width: 134px;

}
</style>
</head>
<h1 align="center">Employee Salary Increments</h1>
<body>

    <div class="main-form">

        <p>Upload employees file</p>
        <form action="upload" method="post"
            enctype="multipart/form-data">
            <input type="text" name = "textField" class= "input-type-text" value="Choose File" />
            <input type="file" name="Browse" size="50" /> <br /> <br /> <input
                type="submit" value="Upload File" name="uploadFile" />
        </form>
    </div>
</body>
</html>
