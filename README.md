![Screenshot_20231202_045906](https://github.com/VoidMiner/AeonTask/assets/53277516/5cd98e1f-e893-4d07-84b3-650efe76cf20)
Требования к функционалу:
- если логин/пароль неправильные - выводим ошибку
- надо предусмотреть возможность logout'а
- корректный вывод списка платежей при ошибочных данных (пропущенные поля, несоответствие типу)

Требования к коду:
- Kotlin, Fragments, Retrofit, Coroutines
- простая архитектура и логика

API:

Базовый URL https://easypay.world/api-test/, в заголовках надо передавать app-key=12345 и v=1

POST /login - логин, передаем параметры в json ({"login": "demo", "password": "12345"}), при корректном запросе в ответ приходит токен.

GET /payments - список платежей, для корректного запроса в заголовках передаем ранее полученный токен token='...'
