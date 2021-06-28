<html>
<head>
    <title>
        Social Network Graph Representation
    </title>
</head>
<body>

<div class="list">
    <h3>Users registered in the network:</h3>
    <#list userList as String>
        <div>
            - Username: ${String}
        </div>
    </#list>
</div>
<br>

<style>
    img{
        width:500px;
        height:500px;
    }

    body{
        padding:25px;
        display: flex;
        flex-direction: row;
        flex-wrap: nowrap;
    }

    .list
    {
        display: flex;
        flex-direction: column;
        flex-wrap: wrap;
    }
</style>

<div>
    <img src=${imgFilePath}/>
</div>


</body>
</html>