# webservice-json-xml

Pontifícia Universidade Católica do Paraná - PUCPR

Especialização / Pós-graduação: App development - desenvolvimento de aplicativos móveis

Disciplina: Desenvolvimento de Serviços Web

## App que consuma WebService JSON e XML

Desenvolver um aplicativo que consuma duas arquitetura diferentes de serviços:
* um que utilize JSON como resposta
* um que utilize XML como resposta

## JSON

Utilizado um mock, acessível por:

`GET https://helenadalmazo.free.beeceptor.com/temperatures`

E com a seguinte resposta:

```json
[
  {
    "name": "Celsius",
    "symbol": "C",
    "freezing_point_of_water": 0,
    "boiling_point_of_water": 100
  },
  {
    "name": "Fahrenheit",
    "symbol": "F",
    "freezing_point_of_water": 32,
    "boiling_point_of_water": 212
  },
  {
    "name": "Kelvin",
    "symbol": "K",
    "freezing_point_of_water": 273,
    "boiling_point_of_water": 373
  }
]
```

## XML

Utilizado o seguinte WebService, formato SOAP 1.1: https://www.w3schools.com/xml/tempconvert.asmx?op=CelsiusToFahrenheit

## Tecnologias

### Kotlin
  - [Reference - Kotlin Programming Language](https://kotlinlang.org/docs/reference/)
  - [Desenvolver apps para Android com o Kotlin](https://developer.android.com/kotlin)
  
  
### Retrofit
  - [GitHub](https://github.com/square/retrofit)

## Aplicativo

![device-2020-04-18-235944](https://user-images.githubusercontent.com/22308459/79678350-eac23c00-81d0-11ea-86c1-dc9d0c77a213.png)
