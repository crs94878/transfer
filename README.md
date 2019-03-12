# transfer
APP for Transfer money between bank accouts

База данных используется H2
Для того, чтобы хотябы тестово попользоваться -- нужно запустить приложение,
зайти по адресу http://localhost:8383/h2-console/ 
там вести в поле JDBC URL: ввести jdbc:h2:../data/transferdb
и там для заполнения хотя бы какими-то данными можно ввести SQL скрипт -

CREATE TABLE Bank_Accounts(
ID NVARCHAR(25) NOT NULL PRIMARY KEY,
AMOUNT INT NOT NULL,
CURRENCY NVARCHAR(25) NOT NULL,
);
INSERT INTO Bank_ACCOUNTS VALUES ('QWER', 2000, 'RUR');
INSERT INTO Bank_ACCOUNTS VALUES ('ASDF', 5000, 'RUR');
INSERT INTO Bank_ACCOUNTS VALUES ('PLMN', 5000, 'RUR')


И тогда можно тестить.
Контроллер слушает по адресу - localhost:8383/api/transfer
Запрос put
Body запроса - 
{
"accountId":"____",
"amount":"____",
"destinationAccountId":"___"
}
