This a Weather service which fetches weather details of different cities using Rapid API weather APIs.
It has a limit of 10 requests per day
To fetch the weather details, use this curl in an app like postman.


curl --location 'http://localhost:8080/api/weather/summary?city=London' \
--header 'X-Client-Id: client1' \
--header 'X-Client-Secret: secret1'

Replace the city name in the curl to fetch the weather summary of the desired city.
Replace 'summary' with hourly to fetch deatailed hourly weather of the desired city.
Currently it works for 2 clients, with cliet id and client secret of client1, secret1 and
client2, secret2 which need to be included as header.
