<html>
<head></head>
<body>
<h1>${hotel.title}</h1>
<h2>${hotel.id}</h2>
<h2>${hotel.city.getTitle()}</h2>
<#list hotel.rooms as room>
    <p>${room.cost}</p>
</#list>
</body>
</html>