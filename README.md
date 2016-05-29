# clock-face

Shokunin May Challenge. Convert digital time to analog.

## Installation/Requirements

- leiningen

This can be installed using brew or most common package managers

```
brew install leiningen
```

## Example

You can run using the go.sh or with lein.

1) Run using one of the methods below

2) Enter desired time in HH:MM format

3) Hit enter

4) Observe the analog equivilant of the entered time

5) ???

6) Profit

Using go.sh
```
clock-face$ ./go.sh
11:30
      o
    h   o
  o       o
o   ｡◕‿◕｡   o
  o       o
    o   o
      m
```

Using lein
```
clock-face$ lein run src/clock_face/core.clj
11:30
      o
    h   o
  o       o
o   ｡◕‿◕｡   o
  o       o
    o   o
      m
```

## Tests

```
lein test
```
