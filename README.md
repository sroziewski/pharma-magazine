# How to setup DB

cd PROJECT_DIR && cd docker &&
docker-compose up -d

# How to run an app
cd PROJECT_DIR && gradlew.bat test  && gradlew.bat run 

# How to authorize

user: joe@biden.us
pass: password

Authorization: Basic am9lQGJpZGVuLnVzOnBhc3N3b3Jk

# A few examples

curl --request POST \
  --url http://localhost:8080/products \
  --header 'authorization: Basic am9lQGJpZGVuLnVzOnBhc3N3b3Jk' \
  --header 'cache-control: no-cache' \
  --header 'content-type: application/json' \
  --header 'postman-token: 8ef99351-c657-4378-2a35-f37131fbac91' \
  --data '{\n    "name": "Tabletki na lepsze samopoczucie",\n    "price": 11.212,\n    "active":true,\n    "brand":"StrongPills"\n}'

curl --request POST \
  --url http://localhost:8080/stocks \
  --header 'authorization: Basic am9lQGJpZGVuLnVzOnBhc3N3b3Jk' \
  --header 'cache-control: no-cache' \
  --header 'content-type: application/json' \
  --header 'postman-token: 5b07115a-eb63-bcdd-5ec1-4a005a70a1b3' \
  --data '{\n	"productId":2,\n	"storeId":1,\n	"quantity":121\n}'

curl --request PUT \
  --url http://localhost:8080/stocks \
  --header 'authorization: Basic am9lQGJpZGVuLnVzOnBhc3N3b3Jk' \
  --header 'cache-control: no-cache' \
  --header 'content-type: application/json' \
  --header 'postman-token: dce70d66-052e-e779-51b9-42d8d7d4731d' \
  --data '{\n	"productId":1,\n	"storeId":1,\n	"quantity":100\n}'
  
  
  
  curl --request GET \
    --url http://localhost:8080/stocks \
    --header 'authorization: Basic am9lQGJpZGVuLnVzOnBhc3N3b3Jk' \
    --header 'cache-control: no-cache' \
    --header 'content-type: application/json' \
    --header 'postman-token: 5a78d3a2-a879-a8f0-4f40-8a7f44da07a2' \
    --data '{\r\n"id":1,\r\n \r\n    "price": 0.12\r\n\r\n}'
