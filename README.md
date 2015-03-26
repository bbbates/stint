# stint

Simple perl/ruby/groovy-style string interpolation for clojure

## Install

### Leiningen

``` [stint "0.1.0"] ```

## Usage

```clj
(use 'stint.core)

(let [foo "return"
      bar "should"
      gah "identity"]
  (str-intern "${bar} ${foo} ${gah}"))

;; => "should return identity"

;; OR use one of the tagged reader synonyms:
(let [foo "return"
      bar "should"
      gah "identity"]
  [#st/int "${bar} ${foo} ${gah}"
   #s/<- "${bar} ${foo} ${gah}"
   #str/<- "${bar} ${foo} ${gah}"])

;; => ["should return identity" "should return identity" "should return identity"]
```

Stint can only interpolate bound symbols or vars at the moment (clojure expressions are coming.)

## TODO
- Clojurescript support.

## License

Copyright © 2015 ICM Consulting Pty Ltd.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
