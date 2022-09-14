# TPE1-POD-G3

## Autores

-   [Arce, Julián Francisco](https://github.com/JuArce)
-   [Catalán, Roberto José](https://github.com/rcatalan98)
-   [Dell'Isola, Lucas](https://github.com/ldellisola)
-   [Pecile, Gian Luca](https://github.com/glpecile)
-   [Torrusio, Lucía](https://github.com/luciatorrusio)

## Compilación

Para compilar el proyecto, se debe ejecutar el siguiente comando en la raíz del proyecto:

```bash
mvn clean install
```

## Clientes

### Admin Client

```sh
./run-admin -DserverAddress=xx.xx.xx.xx:yyyy -Daction=actionName [ -DinPath=filename | -Dflight=flightCode ]
```

| Opción     | Opciones                                                           | Descripción                             |
| ---------- | ------------------------------------------------------------------ | --------------------------------------- |
| `-Daction` | `models`, `flights`, `status`, `confirm`, `cancel` y `reticketing` | Acción a realizar del cliente de admin. |
| `-DinPath` | \[OPCIONAL\] `String filename`                                     | Ruta del archivo de entrada.            |
| `-Dflight` | \[OPCIONAL\] `String flightCode`                                   | Código del vuelo.                       |

## Notification Client

```sh
./run-notifcations -DserverAddress=xx.xx.xx.xx:yyyy -Dflight=flightCode -Dpassenger=name
```

| Opción        | Opciones            | Descripción          |
| ------------- | ------------------- | -------------------- |
| `-Dflight`    | `String flightCode` | Código del vuelo.    |
| `-Dpassenger` | `String name`       | Nombre del pasajero. |

## SeatMapClient

```sh
./run-seatMap -DserverAddress=xx.xx.xx.xx:yyyy -Dflight=flightCode [ -Dcategory=catName | -Drow=rowNumber ] -DoutPath=output.csv
```

| Opción       | Opciones                                                | Descripción                 |
| ------------ | ------------------------------------------------------- | --------------------------- |
| `-Dflight`   | `String flightCode`                                     | Código del vuelo.           |
| `-Dcategory` | \[OPCIONAL\] `(BUSINESS \| PREMIUM_ECONOMY \| ECONOMY)` | Nombre de la categoría.     |
| `-Drow`      | \[OPCIONAL\] `int rowNumber`                            | Número de fila.             |
| `-DoutPath`  | `String output.csv`                                     | Ruta del archivo de salida. |

## SeatAssignmentClient

```sh
./run-seatAssign -DserverAddress=xx.xx.xx.xx:yyyy -Daction=actionName -Dflight=flightCode [ -Dpassenger=name | -Drow=num | -Dcol=L | -DoriginalFlight=originFlightCode ]
```

| Opción        | Opciones                     | Descripción                             |
| ------------- | ---------------------------- | --------------------------------------- |
| `-Daction`    | `assign`, `unassign`, `move` | Acción a realizar del cliente de admin. |
| `-Dflight`    | `String flightCode`          | Código del vuelo.                       |
| `-Dpassenger` | \[OPCIONAL\] `String name`   | Nombre del pasajero.                    |
| `-Drow`       | \[OPCIONAL\] `int rowNumber` | Número de fila.                         |
| `-Dcol`       | \[OPCIONAL\] `char col`      | Letra de columna.                       |
