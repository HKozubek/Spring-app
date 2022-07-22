# Spring-app, Zad 1

## Uruchomienie aplikacji

Polecenie do budowania obrazu Doker
```
docker build -t springio/gs-spring-boot-docker .
```

Polecenie do uruchomienia kontenera
```
docker run -p 8080:8080 springio/gs-spring-boot-docker
```

## Korzystanie z aplikacji, przykładowe komendy

**read all scripts**

```
curl.exe -v localhost:8080/scripts | json_pp
```

**read script with id = 1**

```
curl.exe -v localhost:8080/scripts/1
```

---

**create new script**

```
curl.exe -d '{\"script\": \"return \"testing\"\", \"name\": \"test\"}' -H "Content-Type: application/json" -X POST http://localhost:8080/scripts
```

---

**update script with id = 1**

```
curl.exe -d '{\"script\": \"return \"working\"\", \"name\": \"hi\"}' -H "Content-Type: application/json" -X PUT http://localhost:8080/scripts/1
```

---

**deleate script with id = 2**
```
curl.exe -X DELETE localhost:8080/scripts/2
```
---

**run script named addition**  with parameters x=1 and y=3
```
curl.exe -d '{\"x\": 1, \"y\": 3}' -H "Content-Type: application/json" -X POST http://localhost:8080/scripts/run/addition
```
