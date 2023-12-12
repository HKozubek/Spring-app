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


"todo-tree.highlights.enabled": true,
  "todo-tree.general.statusBar": "total",
  "todo-tree.general.tagGroups": {},
  "todo-tree.general.tags": [
    "BUG",
    "HACK",
    "FIXME",
    "TODO",
    "XXX",
    "[ ]",
    "[x]",
    "TESTME",
    "DEBUG"
  ],
  "todo-tree.highlights.customHighlight": {
    "TODO": {
      "foreground": "#000",
      "background": "#ffc43f",
      "overviewRulerColor": "#ffc43f",
      "rulerColour": "#ffc43f",
      "iconColour": "#ffc43f",
      "gutterIcon": true
    },
    "FIXME": {
      "foreground": "#000",
      "background": "#4CAF50",
      "overviewRulerColor": "#4CAF50",
      "rulerColour": "#4CAF50",
      "iconColour": "#4CAF50",
      "gutterIcon": true,
      "icon": "tools"
    },
    "BUG": {
      "foreground": "#fff",
      "background": "#b91c1c",
      "overviewRulerColor": "#b91c1c",
      "rulerColour": "#b91c1c",
      "iconColour": "#b91c1c",
      "gutterIcon": true
    },
    "TESTME": {
      "foreground": "#FFF",
      "background": "#005fa1",
      "overviewRulerColor": "#005fa1",
      "rulerColour": "#005fa1",
      "iconColour": "#005fa1",
      "gutterIcon": true,
      "icon": "beaker"
    },
    "DEBUG": {
      "foreground": "#FFF",
      "background": "#7518B6",
      "overviewRulerColor": "#7518B6",
      "rulerColour": "#7518B6",
      "iconColour": "#7518B6",
      "gutterIcon": true,
      "icon": "terminal"
    }
  },
