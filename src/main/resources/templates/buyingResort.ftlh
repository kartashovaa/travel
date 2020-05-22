<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>${resort.title}</title>
</HEAD>
<BODY>
<h1>Оформление покупки:</h1>
<h2>Данные о курорте:</h2>
<h3>${resort.title}</h3>

<p><a href="/hotels/${resort.hotel.id}">Гостиница ${resort.hotel.title} ${resort.hotel.stars} звезд</a></p>

<p><a href="/countries/${resort.hotel.city.country.id}">Страна: ${resort.hotel.city.country.title}</a></p>
<p><a href="/cities/${resort.arrivalCity.id}">Город: ${resort.arrivalCity.title}</a></p>
<p>Описание курорта: ${resort.description}</p>
<p>Длительность (в днях): ${resort.durationInDays} </p>
<p>Стоимость: ${resort.cost}</p>
<p>Теги:</p>
<ul>
    <#list resort.tags as tag>
        <li>${tag.title}</li>
    </#list >
</ul>

<p>Дата отправления: ${resort.startDate?string('dd.MM.yyyy')}</p>
<p>Дата прибытия: ${resort.endDate?string('dd.MM.yyyy')}</p>

<div>
    <h3>Введите ваши данные</h3>
    <form method="post" action="/searchResult">
        <p>Фамилия</p>
        <input type="text" name="lastName" required>
        <p>Имя</p>
        <input type="text" name="firstName" required>
        <p>Отчество</p>
        <input type="text" name="middleName" required>
        <p>Номер карты</p>
        <input type="text" name="cardNumber" required>
        <p>Имя владельца карты</p>
        <input type="text" name="cardHolderName" required>
        <p>Код cvv</p>
        <input type="text" name="cvv" required>
        <p>
            <button type="submit">Совершить покупку</button>
        </p>
    </form>
</div>
</BODY>

</HTML>