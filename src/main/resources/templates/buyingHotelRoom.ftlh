<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="STYLESHEET.css">
    <title>Покупка номера</title>
</HEAD>
<BODY>
<h3><a href="/main">Вернуться на главную</a></h3>
<h1>Оформление покупки номера в !Название гостиницы!</h1>
<h2>Данные о номере:</h2>
<div>
    <p>Количество спальных мест: ${hotelRoom.numberOfSleepingPlaces}</p>
    <p>Удобства:</p>
    <#list hotelRoom.facilities as facility>
        <p>${facility.title}</p>
    </#list>
    <p>Стоимость: ${hotelRoom.cost}</p>
</div>
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